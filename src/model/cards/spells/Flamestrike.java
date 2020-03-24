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
        for (int i = 0; i < oppField.size(); i++) {
            int s = oppField.size();
            Minion m = oppField.get(i);
            m.setCurrentHP(m.getCurrentHP() - 4);
            if (s > oppField.size())
                i--;
        }
    }
}
