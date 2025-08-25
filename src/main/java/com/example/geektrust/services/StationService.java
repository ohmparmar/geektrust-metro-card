package com.example.geektrust.services;

import java.util.List;
import java.util.Map;

import com.example.geektrust.entities.PassengerType;
import com.example.geektrust.entities.Station;
import com.example.geektrust.repository.IStationRepository;

public class StationService {
    private final IStationRepository stationRepository;

    public StationService(IStationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public void printSummary() {
        List<Station> stations = stationRepository.findAll("DESC");

        for (Station station : stations) {
            System.out.println("TOTAL_COLLECTION " + station.getId() + " " + station.getChargesCollected() + " "
                    + station.getDiscountGiven());
            System.out.println("PASSENGER_TYPE_SUMMARY");
            Map<PassengerType, Long> passengerTypeMap = station.getPassengerTypeMap();
            for (Map.Entry<PassengerType, Long> entry : passengerTypeMap.entrySet()) {
                System.out.println(entry.getKey() + "  " + entry.getValue());
            }
            System.out.println();
        }
    }

}
