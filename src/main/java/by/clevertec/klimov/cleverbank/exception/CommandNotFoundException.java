package by.clevertec.klimov.cleverbank.exception;

public class CommandNotFoundException extends Exception {

  /** Instantiates a new command exception. */
  public CommandNotFoundException() {
    super();
  }

  /**
   * Instantiates a new command exception.
   *
   * @param message the message
   */
  public CommandNotFoundException(String message) {
    super(message);
  }

  /**
   * Instantiates a new command exception.
   *
   * @param message the message
   * @param cause the cause
   */
  public CommandNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Instantiates a new command exception.
   *
   * @param cause the cause
   */
  public CommandNotFoundException(Throwable cause) {
    super(cause);
  }
}
