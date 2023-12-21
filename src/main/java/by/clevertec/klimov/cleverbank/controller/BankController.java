package by.clevertec.klimov.cleverbank.controller;

import by.clevertec.klimov.cleverbank.command.Command;
import by.clevertec.klimov.cleverbank.command.factory.CommandFactory;
import by.clevertec.klimov.cleverbank.command.factory.impl.CommandFactoryImpl;
import by.clevertec.klimov.cleverbank.dto.BankDto;
import by.clevertec.klimov.cleverbank.exception.CommandException;
import by.clevertec.klimov.cleverbank.exception.CommandNotFoundException;
import by.clevertec.klimov.cleverbank.service.ServiceProvider;
import by.clevertec.klimov.cleverbank.util.Constants;
import by.clevertec.klimov.cleverbank.util.JsonUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

@Slf4j
@WebServlet(name = "bank", value = "/bank")
public class BankController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    try {
      processRequest(request, response);
    } catch (CommandNotFoundException e) {
      log.error("An error occurred while execute request", e);
      response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    } catch (CommandException e) {
      log.error("An error occurred while execute request", e);
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  private void processRequest(HttpServletRequest req, HttpServletResponse resp)
      throws CommandException, CommandNotFoundException {
    String name = req.getParameter("command");
    CommandFactory commandFactory = new CommandFactoryImpl();
    Command command = commandFactory.getCommand(name);
    command.execute(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    log.debug("Create bank");
    try {
      BankDto bank = JsonUtil.jsonToObject(IOUtils.toString(request.getReader()), BankDto.class);
      int httpServletResponse;
      if (Objects.nonNull(bank)) {
        httpServletResponse =
            ServiceProvider.getInstance().getBankService().create(bank) == 1
                ? HttpServletResponse.SC_CREATED
                : HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
      } else {
        httpServletResponse = HttpServletResponse.SC_BAD_REQUEST;
      }
      response.setStatus(httpServletResponse);
    } catch (Exception e) {
      log.error("An error occurred while create bank", e);
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response) {
    log.debug("Update bank");
    try {
      String body = IOUtils.toString(request.getReader());
      BankDto requestBank = JsonUtil.jsonToObject(body, BankDto.class);
      int httpServletResponse;
      httpServletResponse =
          ServiceProvider.getInstance().getBankService().update(requestBank) == 1
              ? HttpServletResponse.SC_CREATED
              : HttpServletResponse.SC_NOT_FOUND;
      response.setStatus(httpServletResponse);
    } catch (Exception e) {
      log.error("An error occurred while update bank", e);
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
    log.debug("Delete bank");
    try {
      long id = Integer.parseInt(request.getParameter(Constants.PARAM_NAME_ID));
      int httpServletResponse;
      httpServletResponse =
          ServiceProvider.getInstance().getBankService().deleteById(id) == 1
              ? HttpServletResponse.SC_OK
              : HttpServletResponse.SC_NOT_FOUND;
      response.setStatus(httpServletResponse);
    } catch (Exception e) {
      log.error("An error occurred while delete bank", e);
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }
}
