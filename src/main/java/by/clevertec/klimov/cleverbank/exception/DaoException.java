package by.clevertec.klimov.cleverbank.exception;

public class DaoException extends RuntimeException {

  private static final String DEFAULT_ERROR_MESSAGE = "Dao exception";
  public DaoException(Exception e) {
    super(DEFAULT_ERROR_MESSAGE, e);
  }

  public DaoException(String errorMessage, Exception e) {
    super(errorMessage, e);
  }

  public DaoException(String errorMessage) {
    super(errorMessage);
  }

  public DaoException(Throwable cause) {
    super(cause);
  }
}
