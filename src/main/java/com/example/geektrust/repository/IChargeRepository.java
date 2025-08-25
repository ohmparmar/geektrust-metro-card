package com.example.geektrust.repository;

import java.util.Optional;

import com.example.geektrust.entities.PassengerType;

public interface IChargeRepository {
    void save(PassengerType ptype, Double charge);

    Optional<Double> findByPassengerType(PassengerType ptype);
}
