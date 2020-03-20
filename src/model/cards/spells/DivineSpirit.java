package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;

public class DivineSpirit extends Spell implements MinionTargetSpell {
    public DivineSpirit() {
        super("Divine Spirit", 3, Rarity.BASIC);
    }

    @Override
    public void performAction(Minion m) throws InvalidTargetException {
        m.setMaxHP(m.getMaxHP()*2);
        m.setCurrentHP(m.getCurrentHP()*2);
    }
}
