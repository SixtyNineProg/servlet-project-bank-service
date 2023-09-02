package by.clevertec.klimov.cleverbank.connection;

import by.clevertec.klimov.cleverbank.exception.ConnectionException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class SingleConnection {

  private static final String URL =
      "jdbc:postgresql://localhost:5432/clever";
  private static final String USER = "postgres";
  private static final String PASSWORD = "123456789";
  private static Connection connection = null;

  private static void connect() {
    try {
      if (connection == null) {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
      }
    } catch (Exception e) {
      log.debug(e.getMessage());
      throw new ConnectionException("Error connect to dataBase", e);
    }
  }
  
  public static Connection getConnection() {
    try {
      if (Objects.isNull(connection)) {
        connect();
      }
      return connection;
    } catch (Exception e) {
      throw new ConnectionException("Error connect to dataBase");
    }
  }
}
