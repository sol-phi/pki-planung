(define (domain vhs-scheduling-2b)

  (:requirements :strips :typing :numeric-fluents :negative-preconditions)

  (:types
    teacher
    course
    room
    slot
    day
  )

  (:predicates
    (can-teach ?t - teacher ?c - course)
    (course-scheduled ?c - course ?s - slot)
    (teacher-busy ?t - teacher ?s - slot)
    (room-busy ?r - room ?s - slot)
    (slot-on-day ?s - slot ?d - day)
  )

  (:functions
    (remaining-capacity ?t - teacher)
    (total-courses-scheduled)
  )

  (:action schedule-course
    :parameters (?t - teacher ?c - course ?r - room ?s - slot ?d - day)
    :precondition (and
      (can-teach ?t ?c)
      (not (teacher-busy ?t ?s))
      (not (room-busy ?r ?s))
      (not (course-scheduled ?c ?s))
      (slot-on-day ?s ?d)
      (> (remaining-capacity ?t) 0)
    )
    :effect (and
      (course-scheduled ?c ?s)
      (teacher-busy ?t ?s)
      (room-busy ?r ?s)
      (decrease (remaining-capacity ?t) 1)
      (increase (total-courses-scheduled) 1)
    )
  )
)
