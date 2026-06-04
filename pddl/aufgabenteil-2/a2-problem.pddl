(define (problem move-block)
  (:domain simple-blocks)

  (:objects blockA)

  (:init
    (on-table blockA)
    (arm-empty)
  )

  (:goal
    (holding blockA)
  )
)