package com.example.geektrust;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.example.geektrust.commands.BalanceCommand;
import com.example.geektrust.commands.CheckInCommand;
import com.example.geektrust.commands.CommandInvoker;
import com.example.geektrust.commands.PrintSummaryCommand;
import com.example.geektrust.entities.PassengerType;
import com.example.geektrust.repository.ChargeRepository;
import com.example.geektrust.repository.IChargeRepository;
import com.example.geektrust.repository.IMetroCardRepository;
import com.example.geektrust.repository.IStationRepository;
import com.example.geektrust.repository.MetroCardRepository;
import com.example.geektrust.repository.StationRepository;
import com.example.geektrust.services.MetroCardService;
import com.example.geektrust.services.PassengerService;
import com.example.geektrust.services.StationService;

public class Main {
    // initialize repository
    private final IChargeRepository chargeRepository = new ChargeRepository();
    private final IMetroCardRepository metroCardRepository = new MetroCardRepository();
    private final IStationRepository stationRepository = new StationRepository();

    // initilaize services
    private final MetroCardService metroCardServices = new MetroCardService(metroCardRepository);
    private final PassengerService passengerCardServices = new PassengerService(stationRepository, metroCardRepository,
            chargeRepository);
    private final StationService stationServices = new StationService(stationRepository);

    private final BalanceCommand balanceCommand = new BalanceCommand(metroCardServices);
    private final CheckInCommand checkInCommand = new CheckInCommand(passengerCardServices);
    private final PrintSummaryCommand printSummaryCommand = new PrintSummaryCommand(stationServices);
    private final CommandInvoker commandInvoker = new CommandInvoker();

    public void registerCommands() {
        commandInvoker.register("BALANCE", balanceCommand);
        commandInvoker.register("CHECK_IN", checkInCommand);
        commandInvoker.register("PRINT_SUMMARY", printSummaryCommand);
    }

    public void registerStations() {
        stationRepository.save("CENTRAL");
        stationRepository.save("AIRPORT");
    }

    public void initializeCharges() {
        chargeRepository.save(PassengerType.ADULT, 200.0);
        chargeRepository.save(PassengerType.SENIOR_CITIZEN, 100.0);
        chargeRepository.save(PassengerType.KID, 50.0);
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
            String inputFile = commandLineArgs.get(0);
            try {
                List<String> file_commands = Files.readAllLines(Paths.get(inputFile));
                // Execute the commands
                new Main().run(file_commands);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
    }

    public void run(List<String> commands) {

        registerCommands();
        initializeCharges();
        registerStations();
        Iterator<String> it = commands.iterator();
        while (it.hasNext()) {
            String line = it.next();
            if (line == null) {
                continue;
            }
            try {
                commandInvoker.invoke(line);
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }
}
