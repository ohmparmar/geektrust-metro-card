package com.example.geektrust.entities;

public class Charge {
    private final MetroCard metroCard;
    private final Integer amount;

    public Charge(MetroCard metroCard, Integer amount) {
        this.metroCard = metroCard;
        this.amount = amount;
    }

    public MetroCard getMetroCard() {
        return this.metroCard;
    }

    public Integer getAmount() {
        return this.amount;
    }

}
