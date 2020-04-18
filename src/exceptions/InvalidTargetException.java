package exceptions;

public class InvalidTargetException extends HearthstoneException {

    public InvalidTargetException(){
        super("This target is invalid to attack");
    }

    public InvalidTargetException(String s){
        super(s);
    }

}
