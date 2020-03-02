package exceptions;

public class HearthstoneException extends Exception {
    public HearthstoneException() {
        super("Invalid action.");
    }

    public HearthstoneException(String message) {
        super(message);
    }
}
