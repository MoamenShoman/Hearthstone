package model.heroes;

import exceptions.*;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Mage extends Hero {
    public Mage() throws IOException, CloneNotSupportedException {
        super("Jaina Proudmoore");
    }
    public void buildDeck() throws IOException, CloneNotSupportedException {
        ArrayList<Minion> allneutralMinions = getAllNeutralMinions("neutral_minions.csv") ;
        ArrayList<Minion> neutralMinions =getNeutralMinions(allneutralMinions,13);
        getDeck().addAll(neutralMinions);
        getDeck().add(new Polymorph());
        getDeck().add(new Polymorph());
        getDeck().add(new Flamestrike());
        getDeck().add(new Flamestrike());
        getDeck().add(new Pyroblast());
        getDeck().add(new Pyroblast());
        Minion minion =new Minion("Kalycgos",10, Rarity.LEGENDARY,4,12,false,false,false);
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
        getListener().damageOpponent(1);
    }
    public void useHeroPower(Minion target) throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException {
        super.useHeroPower();
        target.setCurrentHP(target.getCurrentHP() - 1);
    }

}
