package by.clevertec.klimov.cleverbank.command.impl;

import by.clevertec.klimov.cleverbank.command.Command;
import by.clevertec.klimov.cleverbank.command.SessionRequestContent;
import by.clevertec.klimov.cleverbank.command.SessionResponseContent;
import by.clevertec.klimov.cleverbank.configuration.ConfigurationLoader;
import by.clevertec.klimov.cleverbank.dto.BankDto;
import by.clevertec.klimov.cleverbank.exception.CommandException;
import by.clevertec.klimov.cleverbank.service.ServiceProvider;
import by.clevertec.klimov.cleverbank.util.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetBanksWithPagination implements Command {

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws CommandException {
    log.debug("Get banks with pagination");
    try {
      SessionRequestContent sessionRequestContent = new SessionRequestContent(request);
      Integer offset =
          sessionRequestContent.getParameter(Constants.PARAM_NAME_OFFSET, Integer.class);
      if (Objects.isNull(offset) || offset < 0) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return;
      }
      Integer limit = sessionRequestContent.getParameter(Constants.PARAM_NAME_LIMIT, Integer.class);
      if (Objects.isNull(limit)) {
        limit = ConfigurationLoader.getConfiguration().getPagination().getPageSize();
      }
      if (limit < 0) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return;
      }
      List<BankDto> banks = ServiceProvider.getInstance().getBankService().read(offset, limit);
      SessionResponseContent sessionResponseContent = new SessionResponseContent(response);
      sessionResponseContent.prepareResponse(banks);
    } catch (Exception e) {
      log.error("An error occurred while get banks with pagination", e);
      throw new CommandException("An error occurred while get all banks", e);
    }
  }
}
