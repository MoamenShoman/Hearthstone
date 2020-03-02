package exceptions;

public class TauntBypassException extends HearthstoneException {
    public TauntBypassException() {
        super("Opponent has a taunt minion(s).");
    }

    public TauntBypassException(String message) {
        super(message);
    }
}
