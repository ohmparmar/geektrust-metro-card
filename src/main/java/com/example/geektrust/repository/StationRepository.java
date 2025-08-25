package com.example.geektrust.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.geektrust.entities.Station;

//storage

public class StationRepository implements IStationRepository {
    private final Map<String, Station> storage = new HashMap<>();

    @Override
    public Station save(String stationId) {
        Station createdStation = new Station(stationId);
        storage.putIfAbsent(stationId, createdStation);
        return createdStation;
    }

    @Override
    public Optional<Station> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Station> findAll(String orderBy) {
        List<Station> stations = new ArrayList<>(storage.values());
        if (orderBy.equals("ASC")) {
            stations.sort(Comparator.comparing(Station::getId));
        }
        if (orderBy.equals("DESC")) {
            stations.sort(Comparator.comparing(Station::getId).reversed());
        }

        return stations;
    }
}
