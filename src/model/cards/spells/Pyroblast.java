package model.cards.spells;

import model.cards.Rarity;

public class Pyroblast extends Spell implements HeroTargetSpell, MinionTargetSpell {
    public Pyroblast() {
        super("Pyroblast", 10, Rarity.EPIC);
    }
}
