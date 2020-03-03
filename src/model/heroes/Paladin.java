package model.heroes;

import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.LevelUp;
import model.cards.spells.SealOfChampions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Paladin extends Hero {
    public Paladin() throws IOException {
        super("Uther Lightbringer");
    }

    @Override
    public void buildDeck() throws IOException {
        ArrayList<Minion> allNeutralMinions = getAllNeutralMinions("neutral_minions.csv");
        ArrayList<Minion> neutralMinions = getNeutralMinions(allNeutralMinions, 15);
        getDeck().addAll(neutralMinions);
        getDeck().add(new SealOfChampions());
        getDeck().add(new SealOfChampions());
        getDeck().add(new LevelUp());
        getDeck().add(new LevelUp());
        Minion minion = new Minion("TirionFordring", 4, Rarity.LEGENDARY, 6, 6, true, true,
                false);
        getDeck().add(minion);
        Collections.shuffle(getDeck());
    }
}
