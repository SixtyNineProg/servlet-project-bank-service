package by.clevertec.klimov.cleverbank.exception;

public class CacheException extends RuntimeException {

  private static final String DEFAULT_ERROR_MESSAGE = "Cache exception";

  public CacheException(Exception e) {
    super(DEFAULT_ERROR_MESSAGE, e);
  }

  public CacheException(String errorMessage, Exception e) {
    super(errorMessage, e);
  }

  public CacheException(String errorMessage) {
    super(errorMessage);
  }

  public CacheException(Throwable cause) {
    super(cause);
  }
}
