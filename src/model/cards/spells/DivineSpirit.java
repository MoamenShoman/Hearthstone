package model.cards.spells;

import model.cards.Rarity;

public class DivineSpirit extends Spell implements MinionTargetSpell {
    public DivineSpirit (){
        super("Divine Spirit" ,3 ,Rarity.BASIC);
    }
}
