package by.clevertec.klimov.cleverbank.command.impl;

import by.clevertec.klimov.cleverbank.command.Command;
import by.clevertec.klimov.cleverbank.command.SessionRequestContent;
import by.clevertec.klimov.cleverbank.dto.BankDto;
import by.clevertec.klimov.cleverbank.exception.CommandException;
import by.clevertec.klimov.cleverbank.service.ServiceProvider;
import by.clevertec.klimov.cleverbank.util.Constants;
import by.clevertec.klimov.cleverbank.util.JsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
      Integer limit = sessionRequestContent.getParameter(Constants.PARAM_NAME_LIMIT, Integer.class);
      List<BankDto> banks = ServiceProvider.getInstance().getBankService().read(offset, limit);
      prepareResponse(response, banks);
    } catch (Exception e) {
      log.error("An error occurred while get banks with pagination", e);
      throw new CommandException("An error occurred while get all banks", e);
    }
  }

  private void prepareResponse(HttpServletResponse response, List<BankDto> banks)
      throws IOException {
    int httpServletResponse;
    if (banks.isEmpty()) {
      httpServletResponse = HttpServletResponse.SC_NOT_FOUND;
    } else {
      PrintWriter out = response.getWriter();
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      out.print(JsonUtil.objToJson(banks));
      out.flush();
      httpServletResponse = HttpServletResponse.SC_OK;
    }
    response.setStatus(httpServletResponse);
  }
}
