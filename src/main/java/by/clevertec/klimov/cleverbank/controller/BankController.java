package by.clevertec.klimov.cleverbank.controller;

import by.clevertec.klimov.cleverbank.entity.Bank;
import by.clevertec.klimov.cleverbank.service.BankService;
import by.clevertec.klimov.cleverbank.service.impl.BankServiceImpl;
import by.clevertec.klimov.cleverbank.utils.JsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

@Slf4j
@WebServlet(name = "bank", value = "/bank")
public class BankController extends HttpServlet {

  public static final String PARAM_NAME_ID = "id";

  private final BankService bankService = new BankServiceImpl();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse response) {
    log.debug("Get bank");
    try {
      long id = Integer.parseInt(req.getParameter(PARAM_NAME_ID));
      Optional<Bank> optionalBank = bankService.readById(id);
      int httpServletResponse;
      if (optionalBank.isPresent()) {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(JsonUtils.objToJson(optionalBank.get()));
        out.flush();
        httpServletResponse = HttpServletResponse.SC_OK;
      } else {
        httpServletResponse = HttpServletResponse.SC_NOT_FOUND;
      }
      response.setStatus(httpServletResponse);
    } catch (Exception e) {
      log.error("An error occurred while create bank", e);
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse response) {
    log.debug("Create bank");
    try {
      Bank bank = JsonUtils.jsonToObject(IOUtils.toString(req.getReader()), Bank.class);
      int httpServletResponse;
      if (Objects.nonNull(bank)) {
        httpServletResponse =
            bankService.create(bank) > 0
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
  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    super.doPut(req, resp);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    super.doDelete(req, resp);
  }
}
