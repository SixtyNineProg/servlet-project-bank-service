package by.clevertec.klimov.cleverbank.command.impl;

import by.clevertec.klimov.cleverbank.command.Command;
import by.clevertec.klimov.cleverbank.dto.BankDto;
import by.clevertec.klimov.cleverbank.exception.CommandException;
import by.clevertec.klimov.cleverbank.service.ServiceProvider;
import by.clevertec.klimov.cleverbank.util.Constants;
import by.clevertec.klimov.cleverbank.util.JsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetBankByIdCommand implements Command {

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws CommandException {
    log.debug("Get bank by id");
    try {
      long id = Integer.parseInt(request.getParameter(Constants.PARAM_NAME_ID));
      Optional<BankDto> optionalBank = ServiceProvider.getInstance().getBankService().readById(id);
      int httpServletResponse;
      if (optionalBank.isPresent()) {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(JsonUtil.objToJson(optionalBank.get()));
        out.flush();
        httpServletResponse = HttpServletResponse.SC_OK;
      } else {
        httpServletResponse = HttpServletResponse.SC_NOT_FOUND;
      }
      response.setStatus(httpServletResponse);
    } catch (Exception e) {
      log.error("An error occurred while get bank by id", e);
      throw new CommandException("An error occurred while get all banks", e);
    }
  }
}
