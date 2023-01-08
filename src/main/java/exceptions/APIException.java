package exceptions;

public class APIException extends Exception {
    public APIException(String message) {
        super(message);
    }

    public APIException() {
        super("Error occurred in API!");
    }

    public APIException(Throwable cause) {
        super(cause);
    }
}
