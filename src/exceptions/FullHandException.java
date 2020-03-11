package exceptions;

import model.cards.Card;

public class FullHandException extends  HearthstoneException{

    private Card burned;

    public FullHandException(Card burned){
        super("You reached the max number of cards.");
        this.burned = burned;
    }

    public FullHandException(String s, Card burned ){
        super(s);
        this.burned = burned;
    }




}
