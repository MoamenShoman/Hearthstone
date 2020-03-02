package model.cards.spells;

import model.cards.Rarity;

public class Polymorph extends Spell implements MinionTargetSpell {

    public Polymorph(String name, int manaCost, Rarity rarity) {
        super("Polymorph", 4, Rarity.BASIC);
    }
}
