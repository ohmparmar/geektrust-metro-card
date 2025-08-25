package com.example.geektrust.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.example.geektrust.entities.MetroCard;

public class MetroCardRepository implements IMetroCardRepository {
    private final Map<String, MetroCard> storage = new HashMap<>();

    @Override
    public void save(MetroCard card) {
        storage.putIfAbsent(card.getId(), card);
    }

    @Override
    public Optional<MetroCard> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

}
