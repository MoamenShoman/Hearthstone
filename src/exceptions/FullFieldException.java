package exceptions;

public class FullFieldException extends HearthstoneException {
    public FullFieldException() {
        super("The Field is Full. You can't summon more minions");
    }

    public FullFieldException(String message) {
        super(message);
    }
}
