package com.example.geektrust.services;

import com.example.geektrust.entities.MetroCard;
import com.example.geektrust.entities.PassengerType;
import com.example.geektrust.entities.Station;
import com.example.geektrust.repository.IChargeRepository;
import com.example.geektrust.repository.IMetroCardRepository;
import com.example.geektrust.repository.IStationRepository;

public class PassengerService {
    private final IStationRepository stationRepository;
    private final IMetroCardRepository metroCardRepository;
    private final IChargeRepository chargeRepository;

    public PassengerService(IStationRepository stationRepository,
            IMetroCardRepository metroCardRepository, IChargeRepository chargeRepository) {
        this.stationRepository = stationRepository;
        this.metroCardRepository = metroCardRepository;
        this.chargeRepository = chargeRepository;
    }

    public void checkIn(String cardId, PassengerType ptype, String stationId) {
        MetroCard card = metroCardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card with id: " + cardId + " not found"));
        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new RuntimeException("Station with id: " + stationId + " not found"));

        debitBalance(card, station, ptype);
        station.incrementPassengerCount(ptype);
        card.setRecentJourney(stationId);
    }

    public void debitBalance(MetroCard card, Station station, PassengerType ptype) {
        Double charge = chargeRepository.findByPassengerType(ptype)
                .orElseThrow(() -> new RuntimeException("Station with id: " + station.getId() + " not found"));
        Double moneyToBeDebited = charge;
        Double discountGiven = 0.0;

        if (returnJourney(card, station)) {
            discountGiven = moneyToBeDebited * 0.5;
            moneyToBeDebited -= discountGiven;
        }
        if (!hasBalance(card, charge)) {
            Double amountMissing = charge - card.getBalance();
            moneyToBeDebited += amountMissing * 0.02; // 2% service fees
        }
        card.debitBalance(moneyToBeDebited);
        station.addChargesCollected(moneyToBeDebited);
        station.addDiscountGiven(discountGiven);
    }

    public boolean returnJourney(MetroCard card, Station station) {
        if (card.getRecentJourney() != null) {
            return card.getRecentJourney().equals("CENTRAL") && station.getId().equals("AIRPORT");
        } else {
            return false;
        }
    }

    public boolean hasBalance(MetroCard card, Double charge) {
        if (card.getBalance() < charge)
            return false;
        else
            return true;
    }
}