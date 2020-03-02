package exceptions;

public class NotEnoughManaException extends HearthstoneException {

    public NotEnoughManaException() {
        super("Insufficient mana crystals.");
    }

    public NotEnoughManaException(String message) {
        super(message);
    }
}
