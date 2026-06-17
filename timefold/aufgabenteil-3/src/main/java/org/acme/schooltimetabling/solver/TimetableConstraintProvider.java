package org.acme.schooltimetabling.solver;

import java.time.Duration;
import java.time.LocalTime;

import ai.timefold.solver.core.api.score.HardSoftScore;
import ai.timefold.solver.core.api.score.stream.Constraint;
import ai.timefold.solver.core.api.score.stream.ConstraintCollectors;
import ai.timefold.solver.core.api.score.stream.ConstraintFactory;
import ai.timefold.solver.core.api.score.stream.ConstraintProvider;
import ai.timefold.solver.core.api.score.stream.Joiners;

import org.acme.schooltimetabling.domain.Lesson;
import org.acme.schooltimetabling.domain.Subject;
import org.acme.schooltimetabling.domain.Teacher;

public class TimetableConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                // Hard constraints
                roomConflict(constraintFactory),
                teacherConflict(constraintFactory),
                teacherWeeklyCapacity(constraintFactory),
                partTimeTeachers(constraintFactory),

                // Soft constraints
//                maximizeScheduledLessons(constraintFactory),
                maximizeRevenue(constraintFactory),

                roomCapacity(constraintFactory),
                teacherRoomStability(constraintFactory),
                teacherTimeEfficiency(constraintFactory),
        };
    }

    Constraint roomConflict(ConstraintFactory constraintFactory) {
        // A room can accommodate at most one lesson at the same time.
        return constraintFactory
                // Select each pair of 2 different lessons ...
                .forEachUniquePair(Lesson.class,
                        // ... in the same timeslot ...
                        Joiners.equal(Lesson::getTimeslot),
                        // ... in the same room ...
                        Joiners.equal(Lesson::getRoom))
                // ... and penalize each pair with a hard weight.
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("Room conflict");
    }

    Constraint teacherConflict(ConstraintFactory constraintFactory) {
        // A teacher can teach at most one lesson at the same time.
        return constraintFactory
                .forEachUniquePair(Lesson.class,
                        Joiners.equal(Lesson::getTimeslot),
                        Joiners.equal(Lesson::getTeacher))
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("Teacher conflict");
    }

    Constraint teacherWeeklyCapacity(ConstraintFactory constraintFactory) {
        // Assumes unique teacher names, due to the requirement of passing strings to the Lesson constructor for the frontend.
        // Counts the amount of assigned lessons a given teacher has.
        // Then, remove every teacher but the ones that are over their capacity limit, and penalize these.
        return constraintFactory
                .forEach(Lesson.class)
                .filter(lesson -> lesson.getTimeslot() != null && lesson.getRoom() != null)
                .join(Teacher.class,
                        Joiners.equal(Lesson::getTeacher, Teacher::getName))
                .groupBy((lesson, teacher) -> teacher, ConstraintCollectors.countBi())
                .filter((teacher, count) -> count > teacher.getWeeklyCapacity())
                .penalize(HardSoftScore.ONE_HARD,
                        (teacher, count) -> count - teacher.getWeeklyCapacity())
                .asConstraint("Teacher weekly capacity");
    }

    Constraint partTimeTeachers(ConstraintFactory constraintFactory) {
        // Collects teachers that are teaching assigned lessons when they shouldn't be, and penalize them.
        return constraintFactory
                .forEach(Lesson.class)
                .filter(lesson -> lesson.getTimeslot() != null && lesson.getRoom() != null)
                .join(Teacher.class,
                        Joiners.equal(Lesson::getTeacher, Teacher::getName))
                .filter((lesson, teacher) -> {
                    LocalTime start = lesson.getTimeslot().getStartTime();
                    return switch (teacher.getTimeAvailability()) {
                        case Teacher.TimeAvailability.AM_ONLY -> start.getHour() >= 13;
                        case Teacher.TimeAvailability.PM_ONLY -> start.getHour() < 13;
                        case Teacher.TimeAvailability.FULL_TIME -> false;
                    };
                })
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("Part-time teacher time restriction");
    }

    Constraint maximizeScheduledLessons(ConstraintFactory constraintFactory) {
        // Rewards every assigned lesson with a +2 weighted with the demand. This incentivizes the solver to assign a lot of them (soft constraint).
        // Weighed more strongly as this is the main objective.
        return constraintFactory
                .forEach(Lesson.class)
                .filter(lesson -> lesson.getTimeslot() != null && lesson.getRoom() != null)
                .join(Subject.class,
                        Joiners.equal(Lesson::getSubject, Subject::getName))
                .reward(HardSoftScore.of(0, 2), (lesson, subject) -> subject.getDemand())
                .asConstraint("Maximize scheduled lessons");
    }

    Constraint maximizeRevenue(ConstraintFactory constraintFactory) {
        // For any given subject, the room capacities are summed up to determine the total room capacity.
        // Then, the lesser of total room capacity and demand is the actual attendance,
        // which combined with the entry fee then produces the revenue, which we want a lot of (reward).
        // Weighed more strongly as this is the main objective.
        return constraintFactory
                .forEach(Lesson.class)
                .filter(lesson -> lesson.getTimeslot() != null && lesson.getRoom() != null)
                .join(Subject.class,
                        Joiners.equal(Lesson::getSubject, Subject::getName))
                .groupBy((lesson, subject) -> subject,
                        ConstraintCollectors.sum((lesson, subject) -> lesson.getRoom().getCapacity()))
                .reward(HardSoftScore.of(0, 2), (subject, totalCapacity) ->
                        Math.min(subject.getDemand(), totalCapacity) * subject.getEntryFee())
                .asConstraint("Maximize scheduled lessons");
    }

    Constraint roomCapacity(ConstraintFactory constraintFactory) {
        // Collect every scheduled lesson, group them by subject and determine the sum of room capacities for that subject.
        // Then, lessons where the subject's demand exceeds the total capacity are singled out.
        // That way the planner attempts to leave out as few people as possible.
        return constraintFactory
                .forEach(Lesson.class)
                .filter(lesson -> lesson.getRoom() != null && lesson.getTimeslot() != null)
                .join(Subject.class,
                        Joiners.equal(Lesson::getSubject, Subject::getName))
                .groupBy((lesson, subject) -> subject,
                        ConstraintCollectors.sum((lesson, subject) -> lesson.getRoom().getCapacity()))
                .filter((subject, totalCapacity) -> totalCapacity < subject.getDemand())
                .penalize(HardSoftScore.ONE_SOFT,
                        (subject, totalCapacity) -> subject.getDemand() - totalCapacity)
                .asConstraint("Room capacity");
    }

    Constraint teacherRoomStability(ConstraintFactory constraintFactory) {
        // A teacher prefers to teach in a single room.
        return constraintFactory
                .forEachUniquePair(Lesson.class,
                        Joiners.equal(Lesson::getTeacher))
                .filter((lesson1, lesson2) -> lesson1.getRoom() != lesson2.getRoom())
                .penalize(HardSoftScore.ONE_SOFT)
                .asConstraint("Teacher room stability");
    }

    Constraint teacherTimeEfficiency(ConstraintFactory constraintFactory) {
        // A teacher prefers to teach sequential lessons and dislikes gaps between lessons.
        return constraintFactory
                .forEach(Lesson.class)
                .join(Lesson.class, Joiners.equal(Lesson::getTeacher),
                        Joiners.equal((lesson) -> lesson.getTimeslot().getDayOfWeek()))
                .filter((lesson1, lesson2) -> {
                    Duration between = Duration.between(lesson1.getTimeslot().getEndTime(),
                            lesson2.getTimeslot().getStartTime());
                    return !between.isNegative() && between.compareTo(Duration.ofMinutes(30)) <= 0;
                })
                .reward(HardSoftScore.ONE_SOFT)
                .asConstraint("Teacher time efficiency");
    }

}
