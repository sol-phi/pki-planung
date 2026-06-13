(define (problem schedule-vhs-courses-2a)
  (:domain vhs-scheduling-2a)

  (:objects
    ahorn eiche birke kiefer - teacher
    edv_01 edv_02 webdesign malerei tonformen - course
    room_01 room_02 room_03 room_04 - room
    slot_mon_am slot_mon_pm
    slot_tue_am slot_tue_pm
    slot_wed_am slot_wed_pm
    slot_thu_am slot_thu_pm
    slot_fri_am slot_fri_pm - slot
  )

  (:init
    (can-teach ahorn edv_01)
    (can-teach ahorn edv_02)
    (can-teach eiche edv_02)
    (can-teach eiche webdesign)
    (can-teach birke webdesign)
    (can-teach birke malerei)
    (can-teach kiefer malerei)
    (can-teach kiefer tonformen)
  )

  (:goal
    (and
      (course-scheduled edv_01)
      (course-scheduled edv_02)
      (course-scheduled webdesign)
      (course-scheduled malerei)
      (course-scheduled tonformen)
    )
  )
)
