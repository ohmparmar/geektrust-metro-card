package com.example.geektrust.repository;

import java.util.Optional;

import com.example.geektrust.entities.MetroCard;

public interface IMetroCardRepository {
    Optional<MetroCard> findById(String id);

    void save(MetroCard card);
}
