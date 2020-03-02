package model.cards.spells;

import model.cards.Rarity;

public class MultiShot extends Spell implements AOESpell {
    public MultiShot() {
        super("Multi-Shot", 4, Rarity.BASIC);
    }
}
