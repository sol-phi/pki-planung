package org.acme.MenuPlanning.solver;

import org.acme.MenuPlanning.domain.MealAssignment;
import org.acme.MenuPlanning.domain.Ingredient;
import ai.timefold.solver.core.api.score.HardSoftScore;
import ai.timefold.solver.core.api.score.stream.Constraint;
import ai.timefold.solver.core.api.score.stream.ConstraintFactory;
import ai.timefold.solver.core.api.score.stream.ConstraintProvider;
import ai.timefold.solver.core.api.score.stream.Joiners;

public class MenuConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                // HARD constraints
                atLeastOneVegetarianPerDay(constraintFactory),
                differentMealsSameDay(constraintFactory),
                noIdenticalMainComponentsConsecutiveDays(constraintFactory),

                // SOFT constraints - Balance zwischen Kosten, Gewinn und Kundenzufriedenheit
                maximizeSharedIngredientsSameDay(constraintFactory),
                antiMonotonyGerichte(constraintFactory),
                minimizeCosts(constraintFactory),
                maximizeProfits(constraintFactory)
        };
    }

    /**
     * HARD: Mindestens ein vegetarisches Gericht pro Tag
     * Wenn BEIDE Gerichte an einem Tag nicht vegetarisch sind → Strafe
     */
    protected Constraint atLeastOneVegetarianPerDay(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachUniquePair(MealAssignment.class,
                        Joiners.equal(MealAssignment::getDay))
                .filter((a1, a2) -> a1.getMeal() != null && a2.getMeal() != null
                        && !a1.getMeal().isVegetarian() && !a2.getMeal().isVegetarian())
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("Mindestens ein vegetarisches Gericht pro Tag");
    }

    /**
     * HARD: Keine gleichen Gerichte an einem Tag
     * Mittag und Abend müssen unterschiedliche Gerichte sein
     */
    protected Constraint differentMealsSameDay(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachUniquePair(MealAssignment.class,
                        Joiners.equal(MealAssignment::getDay))
                .filter((a1, a2) -> a1.getMeal() != null && a2.getMeal() != null
                        && a1.getMeal().equals(a2.getMeal()))
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("Mittags und abends unterschiedliche Gerichte");
    }

    /**
     * HARD: Keine identischen Hauptkomponenten an aufeinanderfolgenden Tagen
     * Kundenwunsch: nicht 2x die gleiche Kohlenhydrat oder das gleiche Protein nacheinander
     */
    protected Constraint noIdenticalMainComponentsConsecutiveDays(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachUniquePair(MealAssignment.class,
                        Joiners.equal(a -> a.getDay(), b -> b.getDay() - 1)) // Tag N vs Tag N+1
                .filter((a1, a2) -> a1.getMeal() != null && a2.getMeal() != null)
                .filter((a1, a2) -> {
                    // Prüfe: Haben sie die gleiche Kohlenhydrate?
                    boolean sameCarbOnConsecutiveDays =
                            a1.getMeal().getMainCarb() != null
                                    && a1.getMeal().getMainCarb() == a2.getMeal().getMainCarb();

                    // Prüfe: Haben sie das gleiche Protein?
                    boolean sameProteinOnConsecutiveDays =
                            a1.getMeal().getMainProtein() != null
                                    && a1.getMeal().getMainProtein() == a2.getMeal().getMainProtein();

                    return sameCarbOnConsecutiveDays || sameProteinOnConsecutiveDays;
                })
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("Keine identischen Hauptkomponenten an aufeinanderfolgenden Tagen");
    }

    /**
     * SOFT (30pt): Ähnliche Zutaten am selben Tag
     * Ziel: Reduziert Beschaffungskosten von Rohstoffen
     */
    protected Constraint maximizeSharedIngredientsSameDay(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachUniquePair(MealAssignment.class,
                        Joiners.equal(MealAssignment::getDay))
                .filter((a1, a2) -> a1.getMeal() != null && a2.getMeal() != null)
                .penalize(HardSoftScore.ofSoft(30), (a1, a2) -> {
                    // Zähle unterschiedliche Zutaten
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
                .asConstraint("Moeglich aehnliche Zutaten am selben Tag");
    }

    /**
     * SOFT (600pt): Keine Monotonie - Vermeidung, dass die gleiche Gericht innerhalb von 3 Tagen wiederholt wird
     */
    protected Constraint antiMonotonyGerichte(ConstraintFactory constraintFactory) {
        return constraintFactory.forEachUniquePair(MealAssignment.class,
                        Joiners.equal(MealAssignment::getMeal))
                .filter((a1, a2) -> a1.getMeal() != null
                        && Math.abs(a1.getDay() - a2.getDay()) <= 3
                        && Math.abs(a1.getDay() - a2.getDay()) > 0)
                .penalize(HardSoftScore.ofSoft(600))
                .asConstraint("Gleiches Gericht innerhalb von 3 Tagen vermeiden");
    }

    /**
     * SOFT (1pt pro Cent): Kostenminimierung
     * ABER: Mit lower weight weil Gewinn wichtiger ist!
     */
    protected Constraint minimizeCosts(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(MealAssignment.class)
                .filter(a -> a.getMeal() != null)
                .penalize(HardSoftScore.ONE_SOFT,
                        assignment -> assignment.getMeal().getCostInCents())
                .asConstraint("Gesamtkosten minimieren");
    }

    /**
     * SOFT (2pt pro Cent REWARD): Gewinnmaximierung
     *
     * Gewinn = Verkaufspreis - Materialkosten
     *
     * Mit 2pt pro Cent ist der Gewinn doppelt so wichtig wie die Kosten:
     * - Wenn  100ct Kosten gespart → -100 soft (gut)
     * - Wenn  100ct Gewinn gespart → +200 soft (BESSER!)
     *
     * Das motiviert den Solver, beliebte Gerichte zu wählen!
     */
    protected Constraint maximizeProfits(ConstraintFactory constraintFactory) {
        return constraintFactory.forEach(MealAssignment.class)
                .filter(a -> a.getMeal() != null)
                .reward(HardSoftScore.ofSoft(2),
                        assignment -> assignment.getMeal().getProfitInCents())
                .asConstraint("Gewinn maximieren");
    }
}