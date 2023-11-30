package by.clevertec.klimov.cleverbank.controller;

import by.clevertec.klimov.cleverbank.dto.BankDto;
import by.clevertec.klimov.cleverbank.service.BankService;
import by.clevertec.klimov.cleverbank.service.impl.BankServiceImpl;
import by.clevertec.klimov.cleverbank.util.JsonUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
      Optional<BankDto> optionalBank = bankService.readById(id);
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
      log.error("An error occurred while get bank", e);
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    log.debug("Create bank");
    try {
      BankDto bank = JsonUtil.jsonToObject(IOUtils.toString(request.getReader()), BankDto.class);
      int httpServletResponse;
      if (Objects.nonNull(bank)) {
        httpServletResponse =
            bankService.create(bank) == 1
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
          bankService.update(requestBank) == 1
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
      long id = Integer.parseInt(request.getParameter(PARAM_NAME_ID));
      int httpServletResponse;
      httpServletResponse =
          bankService.deleteById(id) == 1
              ? HttpServletResponse.SC_OK
              : HttpServletResponse.SC_NOT_FOUND;
      response.setStatus(httpServletResponse);
    } catch (Exception e) {
      log.error("An error occurred while delete bank", e);
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }
}
