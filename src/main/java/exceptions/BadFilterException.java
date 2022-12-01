package exceptions;

public class BadFilterException extends RuntimeException {
    public BadFilterException() {
        super("Crucial filter missing");
    }
}