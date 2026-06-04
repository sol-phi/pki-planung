(define (problem transport-parcels-1a)
  (:domain sunshine-logistics-1a)

  (:objects 
    spandau pankow lichtenberg neukoelln - location
    warehouse1 warehouse2 warehouse3 warehouse4 warehouse5 - warehouse
    parcel1 parcel2 parcel3 parcel4 parcel5 parcel6 parcel7 parcel8 - parcel
    truck1 truck2 - truck
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

    (person-at-warehouse fischer warehouse1)
    (person-at-warehouse berger warehouse1)
    (person-at-warehouse schmitz warehouse1)
    (person-at-warehouse meyer warehouse1)
    
    (has-drivers-license berger)
    (has-drivers-license schmitz)
    (has-drivers-license meyer)
  )

  (:goal
    (and
      (parcel-at-warehouse parcel1 warehouse2)
      (parcel-at-warehouse parcel2 warehouse4)
      (parcel-at-warehouse parcel3 warehouse2)
      (parcel-at-warehouse parcel4 warehouse2)
      (parcel-at-warehouse parcel5 warehouse2)
      (parcel-at-warehouse parcel6 warehouse4)
      (parcel-at-warehouse parcel7 warehouse3)
      (parcel-at-warehouse parcel8 warehouse4)
    )
  )
)