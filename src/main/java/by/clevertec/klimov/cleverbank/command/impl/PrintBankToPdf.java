package by.clevertec.klimov.cleverbank.command.impl;

import by.clevertec.klimov.cleverbank.command.Command;
import by.clevertec.klimov.cleverbank.command.SessionRequestContent;
import by.clevertec.klimov.cleverbank.command.SessionResponseContent;
import by.clevertec.klimov.cleverbank.exception.CommandException;
import by.clevertec.klimov.cleverbank.exception.DaoNotFoundException;
import by.clevertec.klimov.cleverbank.service.ServiceProvider;
import by.clevertec.klimov.cleverbank.util.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class PrintBankToPdf implements Command {

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws CommandException {
    log.debug("Print Bank to PDF");
    try {
      SessionRequestContent sessionRequestContent = new SessionRequestContent(request);
      Long id = sessionRequestContent.getParameter(Constants.PARAM_NAME_ID, Long.class);
      if (Objects.isNull(id) || id < 0) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return;
      }
      String filePath = ServiceProvider.getInstance().getBankService().writeToPdf(id);
      SessionResponseContent sessionResponseContent = new SessionResponseContent(response);
      sessionResponseContent.prepareResponseWithFile(filePath);
    } catch (DaoNotFoundException e) {
      log.error(e.getMessage(), e);
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    } catch (Exception e) {
      log.error("An error occurred while print bank to pdf", e);
      throw new CommandException("An error occurred while get all banks", e);
    }
  }
}
