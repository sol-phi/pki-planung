(define (problem schedule-vhs-courses-1c)
  (:domain vhs-scheduling-1c)

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

    (morning-only birke)
    (afternoon-only kiefer)

    (is-morning-slot slot_mon_am) (is-morning-slot slot_tue_am)
    (is-morning-slot slot_wed_am) (is-morning-slot slot_thu_am)
    (is-morning-slot slot_fri_am)

    (slot-on-day slot_mon_am mon) (slot-on-day slot_mon_pm mon)
    (slot-on-day slot_tue_am tue) (slot-on-day slot_tue_pm tue)
    (slot-on-day slot_wed_am wed) (slot-on-day slot_wed_pm wed)
    (slot-on-day slot_thu_am thu) (slot-on-day slot_thu_pm thu)
    (slot-on-day slot_fri_am fri) (slot-on-day slot_fri_pm fri)

    (= (remaining-capacity ahorn) 7)
    (= (remaining-capacity eiche) 5)
    (= (remaining-capacity birke) 4)
    (= (remaining-capacity kiefer) 5)

    (= (daily-courses ahorn mon) 0) (= (daily-courses ahorn tue) 0)
    (= (daily-courses ahorn wed) 0) (= (daily-courses ahorn thu) 0)
    (= (daily-courses ahorn fri) 0)

    (= (daily-courses eiche mon) 0) (= (daily-courses eiche tue) 0)
    (= (daily-courses eiche wed) 0) (= (daily-courses eiche thu) 0)
    (= (daily-courses eiche fri) 0)

    (= (daily-courses birke mon) 0) (= (daily-courses birke tue) 0)
    (= (daily-courses birke wed) 0) (= (daily-courses birke thu) 0)
    (= (daily-courses birke fri) 0)

    (= (daily-courses kiefer mon) 0) (= (daily-courses kiefer tue) 0)
    (= (daily-courses kiefer wed) 0) (= (daily-courses kiefer thu) 0)
    (= (daily-courses kiefer fri) 0)

    (= (total-courses-scheduled) 0)
  )

  (:goal
    (= (total-courses-scheduled) 21)
  )

  (:metric maximize (total-courses-scheduled))
)
