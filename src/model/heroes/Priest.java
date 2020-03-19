package model.heroes;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Priest extends Hero {

    public Priest() throws IOException {
        super("Anduin Wrynn");

    }

    public void buildDeck() throws IOException {
        ArrayList<Minion> allNeutralMinions = getAllNeutralMinions("neutral_minions.csv");
        ArrayList<Minion> neutralMinions = getNeutralMinions(allNeutralMinions, 13);
        getDeck().addAll(neutralMinions);
        getDeck().add(new DivineSpirit());
        getDeck().add(new DivineSpirit());
        getDeck().add(new HolyNova());
        getDeck().add(new HolyNova());
        getDeck().add(new ShadowWordDeath());
        getDeck().add(new ShadowWordDeath());

        Minion minion = new Minion("Prophet Velen", 7, Rarity.LEGENDARY, 7,7, false, false,
                false);
        getDeck().add(minion);
        Collections.shuffle(getDeck());
        for (Card m: getDeck()){
            if (m instanceof Minion){
                ((Minion) m).setListener(this);
            }
        }

    }
}
