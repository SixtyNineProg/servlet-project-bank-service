package by.clevertec.klimov.cleverbank.command.impl;

import by.clevertec.klimov.cleverbank.command.Command;
import by.clevertec.klimov.cleverbank.command.SessionRequestContent;
import by.clevertec.klimov.cleverbank.exception.CommandException;
import by.clevertec.klimov.cleverbank.exception.DaoNotFoundException;
import by.clevertec.klimov.cleverbank.service.ServiceProvider;
import by.clevertec.klimov.cleverbank.util.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrintBankToPdf implements Command {

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws CommandException {
    log.debug("Print Bank to PDF");
    try {
      SessionRequestContent sessionRequestContent = new SessionRequestContent(request);
      Long id = sessionRequestContent.getParameter(Constants.PARAM_NAME_ID, Long.class);
      String filePath = ServiceProvider.getInstance().getBankService().writeToPdf(id);
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
    } catch (DaoNotFoundException e) {
      log.error(e.getMessage(), e);
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    } catch (Exception e) {
      log.error("An error occurred while print bank to pdf", e);
      throw new CommandException("An error occurred while get all banks", e);
    }
  }
}
