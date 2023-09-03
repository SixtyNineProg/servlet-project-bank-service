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
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

@Slf4j
@WebServlet(name = "bank", value = "/bank")
public class BankController extends HttpServlet {

  public static final String PARAM_NAME_ID = "id";

  private final BankService bankService = new BankServiceImpl();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    long id = Integer.parseInt(req.getParameter(PARAM_NAME_ID));
    bankService.readById(id);
    super.doGet(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
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
      resp.setStatus(httpServletResponse);
    } catch (Exception e) {
      log.error("An error occurred while create bank", e);
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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
