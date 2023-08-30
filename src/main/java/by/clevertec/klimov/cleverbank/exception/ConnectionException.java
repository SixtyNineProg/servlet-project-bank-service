package by.clevertec.klimov.cleverbank.exception;

public class ConnectionException extends RuntimeException {
    private static final String DEFAULT_ERROR_MESSAGE = "Connection exception";

    public ConnectionException(Exception e) {
        super(DEFAULT_ERROR_MESSAGE, e);
    }

    public ConnectionException(String errorMessage, Exception e) {
        super(errorMessage, e);
    }

    public ConnectionException(String errorMessage) {
        super(errorMessage);
    }

    public ConnectionException(Throwable cause) {
        super(cause);
    }
}
