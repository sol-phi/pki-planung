(define (problem transport-parcels-1c)
  (:domain sunshine-logistics-1c)

  ; Comparing 1a to 1b, parcel-at-location is now also explicitly defined in :init and searched for in :goal.
  ; Comparing 1c to the previous two exercises, a third truck has been added and respectively initialized.
  ; The kilometers driven and the total wear of the trucks are also initialized. The planner will attempt to minimize the latter.

  (:objects 
    spandau pankow lichtenberg neukoelln - location
    warehouse1 warehouse2 warehouse3 warehouse4 warehouse5 - warehouse
    parcel1 parcel2 parcel3 parcel4 parcel5 parcel6 parcel7 parcel8 - parcel
    truck1 truck2 truck3 - truck
    fischer berger schmitz meyer - person
  )

  (:init
    (road spandau pankow)
    (road spandau lichtenberg)
    (road spandau neukoelln)

    (warehouse-at-location warehouse1 spandau)
    (warehouse-at-location warehouse2 lichtenberg)
    (warehouse-at-location warehouse3 pankow)
    (warehouse-at-location warehouse4 neukoelln)
    (warehouse-at-location warehouse5 neukoelln)

    (parcel-at-location parcel1 spandau)
    (parcel-at-location parcel2 spandau)
    (parcel-at-location parcel3 spandau)
    (parcel-at-location parcel4 spandau)
    (parcel-at-location parcel5 spandau)
    (parcel-at-location parcel6 spandau)
    (parcel-at-location parcel7 spandau)
    (parcel-at-location parcel8 spandau)

    (parcel-at-warehouse parcel1 warehouse1)
    (parcel-at-warehouse parcel2 warehouse1)
    (parcel-at-warehouse parcel3 warehouse1)
    (parcel-at-warehouse parcel4 warehouse1)
    (parcel-at-warehouse parcel5 warehouse1)
    (parcel-at-warehouse parcel6 warehouse1)
    (parcel-at-warehouse parcel7 warehouse1)
    (parcel-at-warehouse parcel8 warehouse1)

    (truck-at-warehouse truck1 warehouse1)
    (truck-at-warehouse truck2 warehouse1)
    (truck-at-warehouse truck3 warehouse1)

    (person-at-warehouse fischer warehouse1)
    (person-at-warehouse berger warehouse1)
    (person-at-warehouse schmitz warehouse1)
    (person-at-warehouse meyer warehouse1)
    
    (has-drivers-license berger)
    (has-drivers-license schmitz)
    (has-drivers-license meyer)

    (= (kilometers truck1) 14725)
    (= (kilometers truck2) 14690)
    (= (kilometers truck3) 150)
    (= (total-wear) 0)
  )

  (:goal
    (and
      (parcel-at-location parcel1 lichtenberg)
      (parcel-at-location parcel2 neukoelln)
      (parcel-at-location parcel3 lichtenberg)
      (parcel-at-location parcel4 lichtenberg)
      (parcel-at-location parcel5 lichtenberg)
      (parcel-at-location parcel6 neukoelln)
      (parcel-at-location parcel7 pankow)
      (parcel-at-location parcel8 neukoelln)
    )
  )

  (:metric minimize (total-wear))
)