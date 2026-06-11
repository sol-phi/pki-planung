(define (domain vhs-scheduling-1a)

  (:requirements :strips :typing)

  (:types
    teacher
    course
    room
    slot
  )

  (:predicates
    (can-teach ?t - teacher ?c - course)
    (course-scheduled ?c - course)
    (teacher-busy ?t - teacher ?s - slot)
    (room-busy ?r - room ?s - slot)
  )

  (:action schedule-course
    :parameters (
      ?t - teacher
      ?c - course
      ?r - room
      ?s - slot
    )
    :precondition (and
      (can-teach ?t ?c)              ;; teacher is qualified for this course
      (not (teacher-busy ?t ?s))     ;; teacher is free in this slot
      (not (room-busy    ?r ?s))     ;; room is free in this slot
      (not (course-scheduled ?c))    ;; course not yet placed in the week
    )
    :effect (and
      (course-scheduled ?c)          ;; course is now scheduled
      (teacher-busy     ?t ?s)       ;; teacher is blocked for this slot
      (room-busy        ?r ?s)       ;; room is blocked for this slot
    )
  )

)
