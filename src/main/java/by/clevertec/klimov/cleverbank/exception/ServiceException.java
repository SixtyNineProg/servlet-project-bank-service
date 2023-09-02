package by.clevertec.klimov.cleverbank.exception;

public class ServiceException extends RuntimeException {
  private static final String DEFAULT_ERROR_MESSAGE = "Service exception";
  public ServiceException(Exception e) {
    super(DEFAULT_ERROR_MESSAGE, e);
  }

  public ServiceException(String errorMessage, Exception e) {
    super(errorMessage, e);
  }

  public ServiceException(String errorMessage) {
    super(errorMessage);
  }

  public ServiceException(Throwable cause) {
    super(cause);
  }
}
