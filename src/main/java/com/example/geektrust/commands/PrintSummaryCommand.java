package com.example.geektrust.commands;

import java.util.List;

import com.example.geektrust.services.StationService;

public class PrintSummaryCommand implements ICommand {
    private final StationService stationService;

    public PrintSummaryCommand(StationService stationService) {
        this.stationService = stationService;
    }

    @Override
    public void invoke(List<String> tokens) {
        stationService.printSummary();
    }
}
