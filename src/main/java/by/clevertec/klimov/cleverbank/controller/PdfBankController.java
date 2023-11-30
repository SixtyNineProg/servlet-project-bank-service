package by.clevertec.klimov.cleverbank.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PdfBankController extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("application/pdf");
    response.setHeader("Content-disposition", "attachment; filename=Bank.pdf");
    File my_file = new File("example.pdf");
    OutputStream out = response.getOutputStream();
    FileInputStream in = new FileInputStream(my_file);
    byte[] buffer = new byte[4096];
    int length;
    while ((length = in.read(buffer)) > 0) {
      out.write(buffer, 0, length);
    }
    in.close();
    out.flush();
  }
}
