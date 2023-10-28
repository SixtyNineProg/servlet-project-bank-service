package by.clevertec.klimov.cleverbank.servlet;

import by.clevertec.klimov.cleverbank.controller.command.Command;
import by.clevertec.klimov.cleverbank.controller.command.CommandProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

public class DispatcherServlet extends HttpServlet {

  @Serial private static final long serialVersionUID = 1L;
  public static final String COMMAND_KEY = "command";
  private final CommandProvider provider = new CommandProvider();

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    
    
    processRequest(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    processRequest(req, resp);
  }

  private void processRequest(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String name;
    Command command;
    // TODO think about paths https://stackoverflow.com/questions/8715474/servlet-and-path-parameters-like-xyz-value-test-how-to-map-in-web-xml
    name = req.getParameter(COMMAND_KEY);
    command = provider.takeCommand(name);
    command.execute(req, resp);
  }
}
