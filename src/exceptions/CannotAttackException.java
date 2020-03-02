package exceptions;

public class CannotAttackException extends HearthstoneException {

    public CannotAttackException() {
        super("Current minion cannot be used to attack.");
    }

    public CannotAttackException(String message) {
        super(message);
    }
}
