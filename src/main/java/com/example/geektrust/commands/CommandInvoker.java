package com.example.geektrust.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandInvoker {
    private final Map<String, ICommand> commands = new HashMap<>();

    public void register(String commandName, ICommand command) {
        commands.putIfAbsent(commandName, command);
    }

    public ICommand get(String commandName) {
        return commands.get(commandName);
    }

    public List<String> parse(String input) {
        return Arrays.asList(input.split(" "));
    }

    public void invoke(String input) {
        List<String> tokens = parse(input);
        ICommand command = get(tokens.get(0));
        if (command == null)
            throw new RuntimeException("INVALID_COMMAND");
        command.invoke(tokens);

    }
}
