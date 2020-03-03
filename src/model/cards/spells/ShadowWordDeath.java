package model.cards.spells;

import model.cards.Rarity;

public class ShadowWordDeath extends Spell implements MinionTargetSpell{
    public ShadowWordDeath() {
        super("Shadow Word: Death", 3, Rarity.BASIC);
    }
}
