package by.clevertec.klimov.cleverbank.listener;

import by.clevertec.klimov.cleverbank.configuration.ConfigurationLoader;
import by.clevertec.klimov.cleverbank.connection.SingleConnection;
import by.clevertec.klimov.cleverbank.util.FileUtil;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class DbInitializeListener implements ServletContextListener {

  public static final String SQL_BASE_FILE_NAME = "sql/Clever-bank.sql";

  @SneakyThrows
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    if (Boolean.TRUE.equals(ConfigurationLoader.getConfiguration().getDatasource().getIsInitDb())) {
      log.info("Init DataBase");
      String query = FileUtil.getFileContentAsStringFromResources(SQL_BASE_FILE_NAME);
      log.info("Query: {}", query);
      Connection connection = SingleConnection.getConnection();
      PreparedStatement select = connection.prepareStatement(query);
      select.executeQuery();
      log.info("Init DataBase finished successfully");
    }
  }
}
