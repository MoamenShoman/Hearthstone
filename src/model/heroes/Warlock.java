package model.heroes;

import exceptions.*;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.CurseOfWeakness;
import model.cards.spells.SiphonSoul;
import model.cards.spells.TwistingNether;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Warlock extends Hero {

    public Warlock() throws IOException, CloneNotSupportedException {
        super("Gul'dan");
    }

    @Override
    public void buildDeck() throws IOException, CloneNotSupportedException {
        ArrayList<Minion> allNeutralMinions = getAllNeutralMinions("neutral_minions.csv");
        ArrayList<Minion> neutralMinions = getNeutralMinions(allNeutralMinions, 13);
        getDeck().addAll(neutralMinions);
        getDeck().add(new CurseOfWeakness());
        getDeck().add(new CurseOfWeakness());
        getDeck().add(new SiphonSoul());
        getDeck().add(new SiphonSoul());
        getDeck().add(new TwistingNether());
        getDeck().add(new TwistingNether());
        Minion minion = new Minion("Wilfred Fizzlebang", 6, Rarity.LEGENDARY, 4,4 , false, false,
                false);
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
        this.drawCard();
        target.getListener().damageOpponent(2);
    }

}
