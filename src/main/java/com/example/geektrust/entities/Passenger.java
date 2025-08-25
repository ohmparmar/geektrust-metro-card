package com.example.geektrust.entities;

public class Passenger {
    private final MetroCard metroCard;
    private final PassengerType passengerType;

    public Passenger(MetroCard metroCard, PassengerType passengerType) {
        this.metroCard = metroCard;
        this.passengerType = passengerType;
    }

    public MetroCard getMetroCard() {
        return this.metroCard;
    }

    public PassengerType getPassengerType() {
        return this.passengerType;
    }

}
