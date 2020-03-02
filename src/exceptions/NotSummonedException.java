package exceptions;

public class NotSummonedException extends HearthstoneException {
    public NotSummonedException() {
    super ("This minion can't attack. It is not yet on the field");
    }

    public NotSummonedException(String message) {
        super(message);
    }
}
