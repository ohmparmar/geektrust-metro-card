package com.example.geektrust.entities;

public class MetroCard {
    private final String id;
    private Double balance;
    private String recentJourney;

    // public MetroCard() {
    // this.id = null;
    // this.balance = 0.0;
    // this.recentJourney = null;
    // }

    // public MetroCard(String id, MetroCard card) {
    // this.id = id;
    // this.balance = card.getBalance();
    // this.recentJourney = card.getRecentJourney();
    // }

    public MetroCard(String id, Double balance) {
        this.id = id;
        this.balance = balance;
        this.recentJourney = null;
    }

    public String getId() {
        return this.id;
    }

    public Double getBalance() {
        return this.balance;
    }

    public String getRecentJourney() {
        return this.recentJourney;
    }

    public void debitBalance(Double amountToBeDebited) {
        if (amountToBeDebited > 0) {
            this.balance -= amountToBeDebited;
        }

    }

    public void setRecentJourney(String stationName) {
        this.recentJourney = stationName;
    }

}
