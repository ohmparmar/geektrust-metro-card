package com.example.geektrust.services;

import java.util.Optional;

import com.example.geektrust.entities.MetroCard;
import com.example.geektrust.repository.IMetroCardRepository;

public class MetroCardService {
    private final IMetroCardRepository metroCardRepository;

    public MetroCardService(IMetroCardRepository metroCardRepository) {
        this.metroCardRepository = metroCardRepository;
    }

    public Double getBalance(String cardId) {
        MetroCard card = metroCardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card with id " + cardId + " not found"));
        return card.getBalance();
    }

    public void create(String cardId, Double balance) {
        Optional<MetroCard> existingCard = metroCardRepository.findById(cardId);
        if (existingCard.isPresent()) {
            throw new IllegalArgumentException("Card with ID " + cardId + " already exists");
        }
        MetroCard newCard = new MetroCard(cardId, balance);
        metroCardRepository.save(newCard);
    }

}
