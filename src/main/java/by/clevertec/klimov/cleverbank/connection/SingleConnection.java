package by.clevertec.klimov.cleverbank.connection;

import by.clevertec.klimov.cleverbank.exception.ConnectionException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SingleConnection {

  private static final String URL =
      "jdbc:postgresql://localhost:5432/clever_bank?autoReconnect=true";
  private static final String USER = "postgres";
  private static final String PASSWORD = "123456789";
  private static Connection connection = null;

  static {
    connect();
  }

  private static void connect() {
    try {
      if (connection == null) {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        connection.setAutoCommit(false);
      }
    } catch (Exception e) {
      throw new ConnectionException("Error connect to dataBase");
    }
  }
}
