package exceptions;

public class NotYourTurnException extends HearthstoneException {

    public NotYourTurnException(){
        super("Sorry , it is not your turn.");
    }
    public NotYourTurnException(String s){
        super(s);
    }
}
