package by.clevertec.klimov.cleverbank.command.impl;

import by.clevertec.klimov.cleverbank.command.Command;
import by.clevertec.klimov.cleverbank.command.SessionResponseContent;
import by.clevertec.klimov.cleverbank.dto.BankDto;
import by.clevertec.klimov.cleverbank.exception.CommandException;
import by.clevertec.klimov.cleverbank.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetAllBanksCommand implements Command {

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws CommandException {
    log.debug("Get all banks");
    try {
      List<BankDto> banks = ServiceProvider.getInstance().getBankService().readAll();
      SessionResponseContent sessionResponseContent = new SessionResponseContent(response);
      sessionResponseContent.prepareResponse(banks);
    } catch (Exception e) {
      log.error("An error occurred while get all banks", e);
      throw new CommandException("An error occurred while get all banks", e);
    }
  }
}
