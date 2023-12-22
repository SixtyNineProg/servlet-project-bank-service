package by.clevertec.klimov.cleverbank.command.factory.impl;

import by.clevertec.klimov.cleverbank.command.Command;
import by.clevertec.klimov.cleverbank.command.factory.CommandFactory;
import by.clevertec.klimov.cleverbank.command.impl.GetAllBanksCommand;
import by.clevertec.klimov.cleverbank.command.impl.GetBankByIdCommand;
import by.clevertec.klimov.cleverbank.command.impl.GetBanksWithPagination;
import by.clevertec.klimov.cleverbank.exception.CommandNotFoundException;

public class CommandFactoryImpl implements CommandFactory {

  @Override
  public Command getCommand(String command) throws CommandNotFoundException {
    return switch (command) {
      case "getAll" -> new GetAllBanksCommand();
      case "getById" -> new GetBankByIdCommand();
      case "getWithPagination" -> new GetBanksWithPagination();
      default -> throw new CommandNotFoundException("Command not found");
    };
  }
}
