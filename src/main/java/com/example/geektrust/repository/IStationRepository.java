package com.example.geektrust.repository;

import java.util.List;
import java.util.Optional;

import com.example.geektrust.entities.Station;

public interface IStationRepository {
    Station save(String stationId);

    Optional<Station> findById(String id);

    List<Station> findAll(String orderBy);

}
