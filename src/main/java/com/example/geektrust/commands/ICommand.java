package com.example.geektrust.commands;

import java.util.List;

public interface ICommand {
    void invoke(List<String> token);
}
