package org.acme.schooltimetabling.domain;

import ai.timefold.solver.core.api.domain.common.PlanningId;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(scope = Subject.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Subject {

    @PlanningId
    private String id;

    private String name;
    private int demand;
    private int entryFee;

    public Subject() {
    }

    public Subject(String id, String name, int demand, int entryFee) {
        this.id = id;
        this.name = name;
        this.demand = demand;
        this.entryFee = entryFee;
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

    public int getDemand() {
        return demand;
    }

    public int getEntryFee() {
        return entryFee;
    }
}
