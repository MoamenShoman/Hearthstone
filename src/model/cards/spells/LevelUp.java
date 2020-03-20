package model.cards.spells;

import model.cards.Rarity;
import model.cards.minions.Minion;

import java.util.ArrayList;

public class LevelUp extends Spell implements FieldSpell {
    public LevelUp() {
        super("Level Up!", 6, Rarity.EPIC);
    }

    @Override
    public void performAction(ArrayList<Minion> field) {
        for (Minion m :field){
            if (m.getName().equals("Silver Hand Recruit")){
                m.setMaxHP(m.getMaxHP()+1);
                m.setCurrentHP(m.getCurrentHP()+1);
                m.setAttack(m.getAttack()+1);
            }
        }
    }
}
