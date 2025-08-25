package com.example.geektrust.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Station {
    private final String id;
    private Double chargesCollected;
    private Double discountGiven;
    private List<Passenger> passengers;
    private Map<PassengerType, Long> passengerTypeMap;

    public Station(String id) {
        this.id = id;
        this.chargesCollected = 0.0;
        this.discountGiven = 0.0;
        this.passengers = new ArrayList<>();
        this.passengerTypeMap = new HashMap<>();
    }

    // public Station(String id, Station station) {
    // this.id = id;
    // this.chargesCollected = station.getChargesCollected();
    // this.discountGiven = station.getDiscountGiven();
    // this.passengers = new ArrayList(station.getPassengers());
    // }

    public String getId() {
        return this.id;
    }

    public Double getChargesCollected() {
        return this.chargesCollected;
    }

    public Double getDiscountGiven() {
        return this.discountGiven;
    }

    public List<Passenger> getPassengers() {
        return Collections.unmodifiableList(this.passengers);
    }

    public Map<PassengerType, Long> getPassengerTypeMap() {
        return Collections.unmodifiableMap(this.passengerTypeMap);
    }

    public void addChargesCollected(Double chargeCollected) {
        if (chargeCollected > 0)
            this.chargesCollected += chargeCollected;
    }

    public void addDiscountGiven(Double discountGiven) {
        if (discountGiven > 0)
            this.discountGiven += discountGiven;
    }

    public void incrementPassengerCount(PassengerType ptype) {
        long current = passengerTypeMap.getOrDefault(ptype, 0L);
        passengerTypeMap.put(ptype, current + 1);
    }

}
