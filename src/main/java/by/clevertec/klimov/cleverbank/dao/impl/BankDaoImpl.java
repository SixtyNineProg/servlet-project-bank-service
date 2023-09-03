package by.clevertec.klimov.cleverbank.dao.impl;

import by.clevertec.klimov.cleverbank.connection.SingleConnection;
import by.clevertec.klimov.cleverbank.dao.BankDao;
import by.clevertec.klimov.cleverbank.entity.Bank;
import by.clevertec.klimov.cleverbank.exception.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringEscapeUtils;

@Slf4j
public class BankDaoImpl implements BankDao {

  public static final String TEMPLATE_INSERT_QUERY = "INSERT INTO public.bank (name) VALUES (?)";
  public static final String TEMPLATE_SELECT_QUERY =
      "SELECT id, name FROM public.bank WHERE id = (?)";
  public static final String TEMPLATE_DELETE_QUERY = "DELETE FROM public.bank WHERE id = (?)";
  public static final String TEMPLATE_UPDATE_QUERY =
      "UPDATE public.bank SET name = (?) WHERE id = (?)";

  public static final String ERROR_OCCURRED_WHILE_EXECUTING_SQL_QUERY =
      "An error occurred while executing sql query";

  public static final String ATTRIBUTE_KEY_ID = "id";
  public static final String ATTRIBUTE_KEY_NAME = "name";

  @Override
  public int save(Bank bank) {
    Connection connection = SingleConnection.getConnection();
    try {
      PreparedStatement insert = connection.prepareStatement(TEMPLATE_INSERT_QUERY);
      insert.setString(1, StringEscapeUtils.escapeSql(bank.getName()));
      return insert.executeUpdate();
    } catch (SQLException e) {
      log.error(ERROR_OCCURRED_WHILE_EXECUTING_SQL_QUERY, e);
      try {
        connection.rollback();
      } catch (SQLException e1) {
        log.error(ERROR_OCCURRED_WHILE_EXECUTING_SQL_QUERY, e1);
      }
      throw new DaoException(ERROR_OCCURRED_WHILE_EXECUTING_SQL_QUERY, e);
    }
  }

  @Override
  public Optional<Bank> findById(Long id) {
    Connection connection = SingleConnection.getConnection();
    try {
      PreparedStatement select = connection.prepareStatement(TEMPLATE_SELECT_QUERY);
      select.setLong(1, id);
      ResultSet res = select.executeQuery();
      if (res.next()) {
        return Optional.of(
            Bank.builder()
                .id(Long.parseLong(res.getString(ATTRIBUTE_KEY_ID)))
                .name(res.getString(ATTRIBUTE_KEY_NAME))
                .build());
      } else {
        return Optional.empty();
      }
    } catch (SQLException e) {
      log.error(ERROR_OCCURRED_WHILE_EXECUTING_SQL_QUERY, e);
      throw new DaoException(ERROR_OCCURRED_WHILE_EXECUTING_SQL_QUERY, e);
    }
  }

  @Override
  public int deleteById(Long id) {
    Connection connection = SingleConnection.getConnection();
    try {
      PreparedStatement delete = connection.prepareStatement(TEMPLATE_DELETE_QUERY);
      delete.setLong(1, id);
      return delete.executeUpdate();
    } catch (SQLException e) {
      log.error(ERROR_OCCURRED_WHILE_EXECUTING_SQL_QUERY, e);
      throw new DaoException(ERROR_OCCURRED_WHILE_EXECUTING_SQL_QUERY, e);
    }
  }

  @Override
  public int update(Bank bank) {
    Connection connection = SingleConnection.getConnection();
    try {
      PreparedStatement update = connection.prepareStatement(TEMPLATE_UPDATE_QUERY);
      update.setString(1, StringEscapeUtils.escapeSql(bank.getName()));
      update.setLong(2, bank.getId());
      return update.executeUpdate();
    } catch (SQLException e) {
      log.error(ERROR_OCCURRED_WHILE_EXECUTING_SQL_QUERY, e);
      try {
        connection.rollback();
      } catch (SQLException sqlException) {
        log.error(ERROR_OCCURRED_WHILE_EXECUTING_SQL_QUERY, sqlException);
      }
      throw new DaoException(ERROR_OCCURRED_WHILE_EXECUTING_SQL_QUERY, e);
    }
  }
}
