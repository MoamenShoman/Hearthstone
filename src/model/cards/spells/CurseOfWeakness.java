package model.cards.spells;

import model.cards.Rarity;
import model.cards.minions.Minion;

import java.util.ArrayList;

public class CurseOfWeakness extends Spell implements AOESpell {

    public CurseOfWeakness() {
        super("Curse of Weakness", 2, Rarity.RARE);
    }

    @Override
    public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
        for (Minion m :oppField){
            m.setAttack(m.getAttack()-2);
        }
    }
}
