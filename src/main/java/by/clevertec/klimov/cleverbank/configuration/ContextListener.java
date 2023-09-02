package by.clevertec.klimov.cleverbank.configuration;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener("application context listener")
public class ContextListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent event) {}
}
