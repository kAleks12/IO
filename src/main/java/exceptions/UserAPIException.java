package exceptions;

public class UserAPIException extends Exception {
    public UserAPIException(String message) {
        super(message);
    }

    public UserAPIException() {
        super("Error occurred in API!");
    }

    public UserAPIException(Throwable cause) {
        super(cause);
    }
}
