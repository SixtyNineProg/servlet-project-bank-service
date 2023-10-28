package by.clevertec.klimov.cleverbank.controller.command;

import by.clevertec.klimov.cleverbank.controller.command.impl.Deposit;
import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
  private final Map<CommandName, Command> commands = new HashMap<>();

  public CommandProvider() {
    commands.put(CommandName.DEPOSIT, new Deposit());
  }

  public Command takeCommand(String name) {
    CommandName commandName;
    commandName = CommandName.valueOf(name.toUpperCase());
    return commands.get(commandName);
  }
}
