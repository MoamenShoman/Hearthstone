package model.cards.spells;

import model.cards.Rarity;

public class DivineSpirit extends Spell implements MinionTargetSpell {
    public DivineSpirit (String name , int manaStone , Rarity rarity){
        super("Divine Spirit" ,3 ,Rarity.BASIC);
    }
}
