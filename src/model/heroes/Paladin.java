package model.heroes;

import exceptions.*;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.LevelUp;
import model.cards.spells.SealOfChampions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Paladin extends Hero {
    public Paladin() throws IOException, CloneNotSupportedException {
        super("Uther Lightbringer");
    }

    @Override
    public void buildDeck() throws IOException, CloneNotSupportedException {
        ArrayList<Minion> allNeutralMinions = getAllNeutralMinions("neutral_minions.csv");
        ArrayList<Minion> neutralMinions = getNeutralMinions(allNeutralMinions, 15);
        getDeck().addAll(neutralMinions);
        getDeck().add(new SealOfChampions());
        getDeck().add(new SealOfChampions());
        getDeck().add(new LevelUp());
        getDeck().add(new LevelUp());
        Minion minion = new Minion("Tirion Fordring", 4, Rarity.LEGENDARY, 6, 6, true, true,
                false);
        getDeck().add(minion);
        Collections.shuffle(getDeck());
        for (Card m: getDeck()){
            if (m instanceof Minion){
                ((Minion) m).setListener(this);
            }
        }
    }

    @Override
    public void useHeroPower() throws NotEnoughManaException, HeroPowerAlreadyUsedException,
            NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException {
        super.useHeroPower();
        Minion m = new Minion("Silver Hand Recruit" , 1 , Rarity.BASIC , 1,
                1 ,false , false , false);
        m.setListener(this);
        if(getField().size() == 7){
            throw new FullFieldException();
        }else{
            getField().add(m);
        }
    }
}
