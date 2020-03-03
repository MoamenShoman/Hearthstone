package model.heroes;

import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Mage extends Hero {
    public Mage() throws IOException {
        super("Jaina Proudmoore");
    }
    public void buildDeck() throws IOException {
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


    }
}
