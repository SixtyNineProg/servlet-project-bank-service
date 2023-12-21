package by.clevertec.klimov.cleverbank.command;

import by.clevertec.klimov.cleverbank.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Command {

  void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
