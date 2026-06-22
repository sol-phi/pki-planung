package org.acme.MenuPlanning.domain;

import ai.timefold.solver.core.api.domain.common.PlanningId;
import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.variable.PlanningVariable;


@PlanningEntity
public class MealAssignment {

    @PlanningId
    private Long id;
    private int day;
    private int indexOfDay; // 1 = Gericht 1, 2 = Gericht 2

    @PlanningVariable(valueRangeProviderRefs = "mealRange")
    private Meal meal;

    public MealAssignment() {}

    public MealAssignment(Long id, int day, int indexOfDay) {
        this.id = id;
        this.day = day;
        this.indexOfDay = indexOfDay;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getDay() { return day; }
    public void setDay(int day) { this.day = day; }
    public int getIndexOfDay() { return indexOfDay; }
    public void setIndexOfDay(int indexOfDay) { this.indexOfDay = indexOfDay; }
    public Meal getMeal() { return meal; }
    public void setMeal(Meal meal) { this.meal = meal; }
}