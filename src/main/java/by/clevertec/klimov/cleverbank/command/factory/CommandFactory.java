package by.clevertec.klimov.cleverbank.command.factory;

import by.clevertec.klimov.cleverbank.command.Command;
import by.clevertec.klimov.cleverbank.exception.CommandNotFoundException;

public interface CommandFactory {

  Command getCommand(String command) throws CommandNotFoundException;
}
