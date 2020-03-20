package model.heroes;

import exceptions.*;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.KillCommand;
import model.cards.spells.MultiShot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Hunter extends Hero {
    public Hunter() throws IOException, CloneNotSupportedException {
        super("Rexxar");
    }

    public void buildDeck() throws IOException, CloneNotSupportedException {
        ArrayList<Minion> allneutralMinions = getAllNeutralMinions("neutral_minions.csv") ;
        ArrayList<Minion> neutralMinions = getNeutralMinions(allneutralMinions,15);
        getDeck().addAll(neutralMinions);
        getDeck().add(new KillCommand());
        getDeck().add(new KillCommand());
        getDeck().add(new MultiShot());
        getDeck().add(new MultiShot());
        Minion minion =new Minion("King Krush",9, Rarity.LEGENDARY,8,8,false,false,true);
        getDeck().add(minion);
        Collections.shuffle(getDeck());
        for (Card m: getDeck()){
            if (m instanceof Minion){
                ((Minion) m).setListener(this);
            }
        }

    }
    public void useHeroPower(Hero target) throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException {
        super.useHeroPower();
        getListener().damageOpponent(2);
    }
}
