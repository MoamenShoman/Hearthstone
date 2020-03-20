package model.cards.spells;

import model.cards.Rarity;
import model.cards.minions.Minion;

import java.util.ArrayList;

public class HolyNova extends Spell implements AOESpell {
    public HolyNova() {
        super("Holy Nova", 5, Rarity.BASIC);
    }

    @Override
    public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
        for (Minion m : oppField) {
            m.setCurrentHP(m.getCurrentHP() - 2);
        }
        for (Minion m : curField) {
            m.setCurrentHP(m.getCurrentHP() + 2);
        }
    }
}
