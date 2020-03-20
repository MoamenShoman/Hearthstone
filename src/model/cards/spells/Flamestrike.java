package model.cards.spells;

import model.cards.Rarity;
import model.cards.minions.Minion;

import java.util.ArrayList;

public class Flamestrike extends Spell implements AOESpell {
    public Flamestrike() {
        super("Flamestrike", 7, Rarity.BASIC);
    }

    @Override
    public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
        for (Minion m :oppField){
            m.setCurrentHP(m.getCurrentHP()-4);
        }
    }
}
