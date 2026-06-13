(define (problem schedule-vhs-courses-2b)
  (:domain vhs-scheduling-2b)

  (:objects
    ahorn eiche birke kiefer - teacher
    edv_01 edv_02 webdesign malerei tonformen - course
    room_01 room_02 room_03 room_04 - room
    slot_mon_am slot_mon_pm
    slot_tue_am slot_tue_pm
    slot_wed_am slot_wed_pm
    slot_thu_am slot_thu_pm
    slot_fri_am slot_fri_pm - slot
    mon tue wed thu fri - day
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

    (slot-on-day slot_mon_am mon) (slot-on-day slot_mon_pm mon)
    (slot-on-day slot_tue_am tue) (slot-on-day slot_tue_pm tue)
    (slot-on-day slot_wed_am wed) (slot-on-day slot_wed_pm wed)
    (slot-on-day slot_thu_am thu) (slot-on-day slot_thu_pm thu)
    (slot-on-day slot_fri_am fri) (slot-on-day slot_fri_pm fri)

    (= (remaining-capacity ahorn) 7)
    (= (remaining-capacity eiche) 5)
    (= (remaining-capacity birke) 4)
    (= (remaining-capacity kiefer) 5)

    (= (total-courses-scheduled) 0)
  )

  (:goal
    (= (total-courses-scheduled) 21)
  )
)
