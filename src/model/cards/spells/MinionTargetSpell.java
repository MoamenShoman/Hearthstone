package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.minions.Minion;

public interface MinionTargetSpell {
    public void performAction(Minion m) throws InvalidTargetException;
}
