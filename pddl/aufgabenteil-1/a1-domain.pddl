(define (domain simple-blocks)
  (:requirements :strips)

  (:predicates
    (on-table ?x)
    (holding ?x)
    (arm-empty)
  )

  (:action pick-up
    :parameters (?x)
    :precondition (and (on-table ?x) (arm-empty))
    :effect (and (holding ?x)
                 (not (on-table ?x))
                 (not (arm-empty)))
  )

  (:action put-down
    :parameters (?x)
    :precondition (holding ?x)
    :effect (and (on-table ?x)
                 (arm-empty)
                 (not (holding ?x)))
  )
)