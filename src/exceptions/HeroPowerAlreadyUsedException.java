package exceptions;

public class HeroPowerAlreadyUsedException extends HearthstoneException {
    public HeroPowerAlreadyUsedException() {
        super("You already used your hero power this turn");
    }

    public HeroPowerAlreadyUsedException(String message) {
        super(message);
    }
}
