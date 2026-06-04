(define (domain sunshine-logistics-1b)
  (:requirements :strips :typing :disjunctive-preconditions :negative-preconditions)

  (:types
    location
    warehouse
    parcel
    truck
    person
  )

  (:predicates
    (road ?l1 - location ?l2 - location)
    (warehouse-at-location ?w - warehouse ?l - location)
    (parcel-at-location ?p - parcel ?l - location)
    (parcel-at-warehouse ?p - parcel ?w - warehouse)
    (parcel-on-truck ?p - parcel ?t - truck)
    (truck-at-warehouse ?t - truck ?w - warehouse)
    (person-at-warehouse ?p - person ?w - warehouse)
    (has-drivers-license ?p - person)
  )

  ; Requires the parcel and truck to be in the same warehouse, and the parcel to not already be loaded on the truck.  
  ; Then, the parcel is removed from the warehouse and added to the truck.
  ; 1b: parcel-at-location has been added so that the planner can recognize when the goal has been reached.
  (:action load-truck
    :parameters (?l - location ?w - warehouse ?p - parcel ?t - truck)
    :precondition (and (warehouse-at-location ?w ?l)
                       (parcel-at-location ?p ?l)
                       (parcel-at-warehouse ?p ?w)
                       (not(parcel-on-truck ?p ?t))
                       (truck-at-warehouse ?t ?w))
    :effect (and (not(parcel-at-location ?p ?l))
                 (not(parcel-at-warehouse ?p ?w))
                 (parcel-on-truck ?p ?t))
  )

  ; Requires the parcel to be in a truck, and not already unloaded into the warehouse.
  ; Then, the parcel is removed from the truck and added to the warehouse.
  ; 1b: parcel-at-location has been added so that the planner can recognize when the goal has been reached.
  (:action unload-truck
    :parameters (?l - location ?w - warehouse ?p - parcel ?t - truck)
    :precondition (and (warehouse-at-location ?w ?l)
                       (not(parcel-at-location ?p ?l))
                       (not(parcel-at-warehouse ?p ?w))
                       (parcel-on-truck ?p ?t)
                       (truck-at-warehouse ?t ?w))
    :effect (and (parcel-at-location ?p ?l)
                 (parcel-at-warehouse ?p ?w)
                 (not(parcel-on-truck ?p ?t)))
  )

  ; Because this checks for both permutations of road, they only need to be defined one way in the problem file.
  ; If the person and truck are at the same warehouse, and the person has a driver's license, then drive.
  ; Changes the warehouse the person and truck are in.
  (:action drive-to-location
    :parameters (?l_from - location ?l_to - location ?w_from - warehouse ?w_to - warehouse ?t - truck ?p - person)
    :precondition (and (or (road ?l_from ?l_to) (road ?l_to ?l_from))
                       (warehouse-at-location ?w_from ?l_from)
                       (warehouse-at-location ?w_to ?l_to)
                       (truck-at-warehouse ?t ?w_from)
                       (person-at-warehouse ?p ?w_from)
                       (has-drivers-license ?p))
    :effect (and (not(truck-at-warehouse ?t ?w_from))
                 (truck-at-warehouse ?t ?w_to)
                 (not(person-at-warehouse ?p ?w_from))
                 (person-at-warehouse ?p ?w_to))
  )
)