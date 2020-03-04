package exceptions;

public class InvalidTargetException extends HearthstoneException {

    public InvalidTargetException(){
        super("You can not attack the target.");
    }

    public InvalidTargetException(String s){
        super(s);
    }

}
