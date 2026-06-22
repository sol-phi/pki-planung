package org.acme.MenuPlanning.domain;

import ai.timefold.solver.core.api.domain.solution.PlanningEntityCollectionProperty;
import ai.timefold.solver.core.api.domain.solution.PlanningScore;
import ai.timefold.solver.core.api.domain.solution.PlanningSolution;
import ai.timefold.solver.core.api.domain.solution.ProblemFactCollectionProperty;
import ai.timefold.solver.core.api.domain.valuerange.ValueRangeProvider;
import ai.timefold.solver.core.api.score.HardSoftScore;
import ai.timefold.solver.core.api.solver.SolverStatus;

import java.util.List;

@PlanningSolution
public class MenuPlan {

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "mealRange")
    private List<Meal> meals;

    @PlanningEntityCollectionProperty
    private List<MealAssignment> assignments;

    @PlanningScore
    private HardSoftScore score;

    private SolverStatus solverStatus;

    public MenuPlan() {}

    public MenuPlan(List<Meal> meals, List<MealAssignment> assignments) {
        this.meals = meals;
        this.assignments = assignments;
    }

    public MenuPlan(HardSoftScore score, SolverStatus solverStatus) {
        this.score = score;
        this.solverStatus = solverStatus;
    }

    public List<Meal> getMeals() { return meals; }
    public void setMeals(List<Meal> meals) { this.meals = meals; }
    public List<MealAssignment> getAssignments() { return assignments; }
    public void setAssignments(List<MealAssignment> assignments) { this.assignments = assignments; }
    public HardSoftScore getScore() { return score; }
    public void setScore(HardSoftScore score) { this.score = score; }
    public SolverStatus getSolverStatus() { return solverStatus; }
    public void setSolverStatus(SolverStatus solverStatus) { this.solverStatus = solverStatus; }
}