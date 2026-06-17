package org.acme.schooltimetabling.domain;

import ai.timefold.solver.core.api.domain.common.PlanningId;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(scope = Teacher.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Teacher {

    public enum TimeAvailability {
        AM_ONLY,
        PM_ONLY,
        FULL_TIME
    }

    @PlanningId
    private String id;

    private String name;
    private int weeklyCapacity;
    private TimeAvailability timeAvailability;

    public Teacher() {
    }

    public Teacher(String id, String name, int weeklyCapacity, TimeAvailability timeAvailability) {
        this.id = id;
        this.name = name;
        this.weeklyCapacity = weeklyCapacity;
        this.timeAvailability = timeAvailability;
    }

    @Override
    public String toString() {
        return name;
    }

    // ************************************************************************
    // Getters and setters
    // ************************************************************************

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeeklyCapacity() {
        return weeklyCapacity;
    }

    public TimeAvailability getTimeAvailability() {
        return timeAvailability;
    }
}
