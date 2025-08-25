package com.example.geektrust.commands;

import java.util.List;

import com.example.geektrust.entities.PassengerType;
import com.example.geektrust.services.PassengerService;

public class CheckInCommand implements ICommand {
    private final PassengerService passengerServices;

    public CheckInCommand(PassengerService passengerServices) {
        this.passengerServices = passengerServices;
    }

    @Override
    public void invoke(List<String> tokens) {
        passengerServices.checkIn(tokens.get(1), PassengerType.valueOf(tokens.get(2)), tokens.get(3));
    }
}
