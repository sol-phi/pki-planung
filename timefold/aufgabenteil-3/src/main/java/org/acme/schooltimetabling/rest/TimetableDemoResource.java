package org.acme.schooltimetabling.rest;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.acme.schooltimetabling.domain.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "Demo data", description = "Timefold-provided demo school timetable data.")
@Path("demo-data")
public class TimetableDemoResource {

    public enum DemoData {
        SMALL,
        LARGE
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "List of demo data represented as IDs.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = DemoData.class, type = SchemaType.ARRAY))) })
    @Operation(summary = "List demo data.")
    @GET
    public DemoData[] list() {
        return DemoData.values();
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Unsolved demo timetable.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = Timetable.class)))})
    @Operation(summary = "Find an unsolved demo timetable by ID.")
    @GET
    @Path("/{demoDataId}")
    public Response generate(@Parameter(description = "Unique identifier of the demo data.",
            required = true) @PathParam("demoDataId") DemoData demoData) {
        List<Timeslot> timeslots = new ArrayList<>(10);
        long nextTimeslotId = 0L;
        timeslots.add(new Timeslot(Long.toString(nextTimeslotId++), DayOfWeek.MONDAY, LocalTime.of(8, 0), LocalTime.of(12, 0)));
        timeslots.add(new Timeslot(Long.toString(nextTimeslotId++), DayOfWeek.MONDAY, LocalTime.of(13, 0), LocalTime.of(17, 0)));
        timeslots.add(new Timeslot(Long.toString(nextTimeslotId++), DayOfWeek.TUESDAY, LocalTime.of(8, 0), LocalTime.of(12, 0)));
        timeslots.add(new Timeslot(Long.toString(nextTimeslotId++), DayOfWeek.TUESDAY, LocalTime.of(13, 0), LocalTime.of(17, 0)));
        timeslots.add(new Timeslot(Long.toString(nextTimeslotId++), DayOfWeek.WEDNESDAY, LocalTime.of(8, 0), LocalTime.of(12, 0)));
        timeslots.add(new Timeslot(Long.toString(nextTimeslotId++), DayOfWeek.WEDNESDAY, LocalTime.of(13, 0), LocalTime.of(17, 0)));
        timeslots.add(new Timeslot(Long.toString(nextTimeslotId++), DayOfWeek.THURSDAY, LocalTime.of(8, 0), LocalTime.of(12, 0)));
        timeslots.add(new Timeslot(Long.toString(nextTimeslotId++), DayOfWeek.THURSDAY, LocalTime.of(13, 0), LocalTime.of(17, 0)));
        timeslots.add(new Timeslot(Long.toString(nextTimeslotId++), DayOfWeek.FRIDAY, LocalTime.of(8, 0), LocalTime.of(12, 0)));
        timeslots.add(new Timeslot(Long.toString(nextTimeslotId++), DayOfWeek.FRIDAY, LocalTime.of(13, 0), LocalTime.of(17, 0)));

        List<Room> rooms = new ArrayList<>(3);
        long nextRoomId = 0L;
        rooms.add(new Room(Long.toString(nextRoomId++), "Raum01", 10));
        rooms.add(new Room(Long.toString(nextRoomId++), "Raum02", 12));
        rooms.add(new Room(Long.toString(nextRoomId++), "Raum03", 8));
        rooms.add(new Room(Long.toString(nextRoomId++), "Raum04", 5));

        List<Subject> subjects = new ArrayList<>();
        long nextSubjectId = 0L;
        subjects.add(new Subject(Long.toString(nextSubjectId++),"EDV_01",  123, 20));
        subjects.add(new Subject(Long.toString(nextSubjectId++),"EDV_02",  50, 30));
        subjects.add(new Subject(Long.toString(nextSubjectId++),"Webdesign",  84, 25));
        subjects.add(new Subject(Long.toString(nextSubjectId++),"Malerei", 105, 30));
        subjects.add(new Subject(Long.toString(nextSubjectId++),"Tonformen", 39, 50));

        List<Teacher> teachers = new ArrayList<>();
        long nextTeacherId = 0L;
        teachers.add(new Teacher(Long.toString(nextTeacherId++),"Ahorn",  7, Teacher.TimeAvailability.FULL_TIME));
        teachers.add(new Teacher(Long.toString(nextTeacherId++),"Eiche",  5, Teacher.TimeAvailability.FULL_TIME));
        teachers.add(new Teacher(Long.toString(nextTeacherId++),"Birke",  4, Teacher.TimeAvailability.AM_ONLY));
        teachers.add(new Teacher(Long.toString(nextTeacherId++),"Kiefer", 5, Teacher.TimeAvailability.PM_ONLY));

        // One more lesson than max capacity of the respective teacher is scheduled each, so that the soft constraints can kick in.
        List<Lesson> lessons = new ArrayList<>();
        long nextLessonId = 0L;
        lessons.add(new Lesson(Long.toString(nextLessonId++), "EDV_01", "Ahorn", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "EDV_01", "Ahorn", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "EDV_01", "Ahorn", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "EDV_01", "Ahorn", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "EDV_02", "Ahorn", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "EDV_02", "Ahorn", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "EDV_02", "Ahorn", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "EDV_02", "Ahorn", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "EDV_02", "Eiche", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "EDV_02", "Eiche", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "EDV_02", "Eiche", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "Webdesign", "Eiche", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "Webdesign", "Eiche", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "Webdesign", "Eiche", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "Webdesign", "Birke", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "Webdesign", "Birke", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "Malerei", "Birke", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "Malerei", "Birke", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "Malerei", "Birke", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "Malerei", "Kiefer", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "Malerei", "Kiefer", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "Malerei", "Kiefer", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "Tonformen", "Kiefer", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "Tonformen", "Kiefer", "all students"));
        lessons.add(new Lesson(Long.toString(nextLessonId++), "Tonformen", "Kiefer", "all students"));

        return Response.ok(new Timetable(demoData.name(), timeslots, rooms, lessons, subjects, teachers)).build();
    }

}
