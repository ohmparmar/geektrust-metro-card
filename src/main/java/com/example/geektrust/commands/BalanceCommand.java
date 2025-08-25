package com.example.geektrust.commands;

import java.util.List;

import com.example.geektrust.services.MetroCardService;

public class BalanceCommand implements ICommand {
    private final MetroCardService metroCardServices;

    public BalanceCommand(MetroCardService metroCardServices) {
        this.metroCardServices = metroCardServices;
    }

    @Override
    public void invoke(List<String> tokens) {
        metroCardServices.create(tokens.get(1), Double.parseDouble(tokens.get(2)));
    }
}
