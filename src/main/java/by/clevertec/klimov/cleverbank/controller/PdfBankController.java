package by.clevertec.klimov.cleverbank.controller;

import by.clevertec.klimov.cleverbank.service.BankService;
import by.clevertec.klimov.cleverbank.service.impl.BankServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "pdf", value = "/print_bank")
public class PdfBankController extends HttpServlet {

  public static final String PARAM_NAME_ID = "id";

  private final BankService bankService = new BankServiceImpl();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) {
    log.debug("Print Bank to PDF");
    try {
      long id = Integer.parseInt(request.getParameter(PARAM_NAME_ID));
      String filePath = bankService.writeToPdf(id);
      response.setContentType("application/pdf");
      response.setHeader("Content-disposition", "attachment; filename=Bank.pdf");
      File myFile = new File(filePath);
      OutputStream out = response.getOutputStream();
      FileInputStream in = new FileInputStream(myFile);
      byte[] buffer = new byte[4096];
      int length;
      while ((length = in.read(buffer)) > 0) {
        out.write(buffer, 0, length);
      }
      in.close();
      out.flush();
    } catch (Exception e) {
      log.error("An error occurred while print bank", e);
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }
}
