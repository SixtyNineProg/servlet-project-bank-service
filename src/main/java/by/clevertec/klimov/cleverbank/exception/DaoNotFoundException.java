package by.clevertec.klimov.cleverbank.exception;

public class DaoNotFoundException extends RuntimeException {

  private static final String DEFAULT_ERROR_MESSAGE = "Dao not found exception";

  public DaoNotFoundException(Exception e) {
    super(DEFAULT_ERROR_MESSAGE, e);
  }

  public DaoNotFoundException(String errorMessage, Exception e) {
    super(errorMessage, e);
  }

  public DaoNotFoundException(String errorMessage) {
    super(errorMessage);
  }

  public DaoNotFoundException(Throwable cause) {
    super(cause);
  }
}
