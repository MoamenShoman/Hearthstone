package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;

public class Polymorph extends Spell implements MinionTargetSpell {
    public Polymorph() {
        super("Polymorph", 4, Rarity.BASIC);
    }

    @Override
    public void performAction(Minion m) throws InvalidTargetException {
        m =new Minion("Sheep",1,Rarity.BASIC,1,1,false,false,false);
    }
}
