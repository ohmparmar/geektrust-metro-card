package com.example.geektrust.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.example.geektrust.entities.PassengerType;

public class ChargeRepository implements IChargeRepository {
    private final Map<PassengerType, Double> storage = new HashMap<>();

    @Override
    public void save(PassengerType ptype, Double charge) {
        storage.putIfAbsent(ptype, charge);
    }

    @Override
    public Optional<Double> findByPassengerType(PassengerType ptype) {
        return Optional.ofNullable(storage.get(ptype));
    }
}
