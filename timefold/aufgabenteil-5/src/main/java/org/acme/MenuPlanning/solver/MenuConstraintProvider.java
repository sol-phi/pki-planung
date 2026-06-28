package org.acme.MenuPlanning.solver;

import org.acme.MenuPlanning.domain.MealAssignment;
import org.acme.MenuPlanning.domain.Ingredient;
import ai.timefold.solver.core.api.score.HardSoftScore;
import ai.timefold.solver.core.api.score.stream.Constraint;
import ai.timefold.solver.core.api.score.stream.ConstraintFactory;
import ai.timefold.solver.core.api.score.stream.ConstraintProvider;
import ai.timefold.solver.core.api.score.stream.Joiners;
import static ai.timefold.solver.core.api.score.stream.ConstraintCollectors.count;

public class MenuConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                // HARD constraints
                atLeastOneVegetarianPerDay(constraintFactory),
                noIdenticalMainComponentsSameDay(constraintFactory),
                noIdenticalMainComponentsConsecutiveDays(constraintFactory),

                // SOFT constraints
                limitMealFrequency(constraintFactory),
                maximizeSharedIngredientsSameDay(constraintFactory),
                antiMonotony(constraintFactory),
                minimizeCosts(constraintFactory),
                maximizeProfits(constraintFactory)
        };
    }

    /**
     * HARD: At least one vegetarian menu a day
     */
    protected Constraint atLeastOneVegetarianPerDay(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachUniquePair(MealAssignment.class,
                        Joiners.equal(MealAssignment::getDay))
                .filter((a1, a2) -> a1.getMeal() != null && a2.getMeal() != null
                        && !a1.getMeal().isVegetarian() && !a2.getMeal().isVegetarian())
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("At least one vegetarian menu a day");
    }

    /**
     * HARD: No identical main components on the same day
     */
    protected Constraint noIdenticalMainComponentsSameDay(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachUniquePair(MealAssignment.class,
                        // Leaves only two unique assignments to compare, Menu 1 and Menu 2 on the same day.
                        Joiners.equal(MealAssignment::getDay))
                .filter((a1, a2) -> a1.getMeal() != null && a2.getMeal() != null)
                .filter((a1, a2) -> {
                    boolean sameCarbOnSameDay =
                            a1.getMeal().getMainCarb() != null
                                    && a2.getMeal().getMainCarb() != null
                                    && a1.getMeal().getMainCarb().getName().equals(a2.getMeal().getMainCarb().getName());

                    boolean sameProteinOnSameDay =
                            a1.getMeal().getMainProtein() != null
                                    && a2.getMeal().getMainProtein() != null
                                    && a1.getMeal().getMainProtein().getName().equals(a2.getMeal().getMainProtein().getName());

                    return sameCarbOnSameDay || sameProteinOnSameDay;
                })
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("Identical main components on same day");
    }

    /**
     * HARD: No identical main components on consecutive days
     */
    protected Constraint noIdenticalMainComponentsConsecutiveDays(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachUniquePair(MealAssignment.class,
                        Joiners.equal(MealAssignment::getDay, b -> b.getDay() - 1))
                .filter((a1, a2) -> a1.getMeal() != null && a2.getMeal() != null)
                .filter((a1, a2) -> {
                    boolean sameCarbOnConsecutiveDays =
                                a1.getMeal().getMainCarb() != null
                                && a2.getMeal().getMainCarb() != null
                                && a1.getMeal().getMainCarb().getName().equals(a2.getMeal().getMainCarb().getName());

                    boolean sameProteinOnConsecutiveDays =
                                a1.getMeal().getMainProtein() != null
                                && a2.getMeal().getMainProtein() != null
                                && a1.getMeal().getMainProtein().getName().equals(a2.getMeal().getMainProtein().getName());

                    return sameCarbOnConsecutiveDays || sameProteinOnConsecutiveDays;
                })
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("Identical main components on consecutive days");
    }

    /**
     * SOFT: Limiting the maximum number per meal over 30 days
     */
    protected Constraint limitMealFrequency(ConstraintFactory constraintFactory) {
        // 60 slots / 31 meals ~= 1.93
        int maxOccurrencesPerMonth = 2;

        return constraintFactory.forEach(MealAssignment.class)
                .filter(assignment -> assignment.getMeal() != null)
                // Group by meal and count the occurrences across the plan
                .groupBy(MealAssignment::getMeal, count())
                // Penalty applies if a meal is chosen more than the limit
                .filter((meal, count) -> count > maxOccurrencesPerMonth)
                // Penalty: 3000 soft points per excess occurrence
                .penalize(HardSoftScore.ofSoft(3000), (meal, count) -> count - maxOccurrencesPerMonth)
                .asConstraint("Meal comes up too often in the month");
    }

    /**
     * SOFT: Similar ingredients on the same day to reduce procurement overhead
     */
    protected Constraint maximizeSharedIngredientsSameDay(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachUniquePair(MealAssignment.class,
                        Joiners.equal(MealAssignment::getDay))
                .filter((a1, a2) -> a1.getMeal() != null && a2.getMeal() != null)
                .penalize(HardSoftScore.ofSoft(30), (a1, a2) -> {
                    int distinctCount = 0;
                    for (Ingredient i : a1.getMeal().getIngredients()) {
                        if (!a2.getMeal().getIngredients().contains(i)) {
                            distinctCount++;
                        }
                    }
                    for (Ingredient i : a2.getMeal().getIngredients()) {
                        if (!a1.getMeal().getIngredients().contains(i)) {
                            distinctCount++;
                        }
                    }
                    return distinctCount;
                })
                .asConstraint("Possibly similar ingredients on the same day");
    }

    /**
     * SOFT: No monotony - avoid short-term repetition of the exact same meal
     */
    protected Constraint antiMonotony(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachUniquePair(MealAssignment.class,
                        Joiners.equal(MealAssignment::getMeal))
                .filter((a1, a2) -> a1.getMeal() != null
                        && Math.abs(a1.getDay() - a2.getDay()) <= 3
                        && Math.abs(a1.getDay() - a2.getDay()) > 0)
                .penalize(HardSoftScore.ofSoft(600))
                .asConstraint("Avoid the same meal within 3 days");
    }

    /**
     * SOFT: Material cost minimization
     */
    protected Constraint minimizeCosts(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(MealAssignment.class)
                .filter(a -> a.getMeal() != null)
                .penalize(HardSoftScore.ONE_SOFT,
                        assignment -> assignment.getMeal().getCostInCents())
                .asConstraint("Minimize total costs");
    }

    /**
     * SOFT: Financial profit maximization
     */
    protected Constraint maximizeProfits(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(MealAssignment.class)
                .filter(a -> a.getMeal() != null)
                .reward(HardSoftScore.ofSoft(2),
                        assignment -> assignment.getMeal().getProfitInCents())
                .asConstraint("Maximize profit");
    }
}