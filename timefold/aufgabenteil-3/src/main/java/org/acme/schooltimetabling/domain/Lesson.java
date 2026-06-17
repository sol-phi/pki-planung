package org.acme.schooltimetabling.domain;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.common.PlanningId;
import ai.timefold.solver.core.api.domain.variable.PlanningVariable;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

@PlanningEntity
public class Lesson {

    @PlanningId
    private String id;

    private String subject;
    private String teacher;
    private String studentGroup;
    private int demand;

    @JsonIdentityReference
    @PlanningVariable(allowsUnassigned = true)
    private Timeslot timeslot;

    @JsonIdentityReference
    @PlanningVariable(allowsUnassigned = true)
    private Room room;

    public Lesson() {
    }

    public Lesson(String id, String subject, String teacher, String studentGroup, int demand) {
        this.id = id;
        this.subject = subject;
        this.teacher = teacher;
        this.studentGroup = studentGroup;
        this.demand = demand;
    }

    public Lesson(String id, String subject, String teacher, String studentGroup, int demand, Timeslot timeslot, Room room) {
        this(id, subject, teacher, studentGroup, demand);
        this.timeslot = timeslot;
        this.room = room;
    }

    @Override
    public String toString() {
        return subject + "(" + id + ")";
    }

    // ************************************************************************
    // Getters and setters
    // ************************************************************************

    public String getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getStudentGroup() {
        return studentGroup;
    }

    public int getDemand() {
        return demand;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
