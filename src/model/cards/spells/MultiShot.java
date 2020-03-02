package model.cards.spells;

import model.cards.Rarity;

public class MultiShot extends Spell implements AOESpell {
    public MultiShot(String name, int manaCost, Rarity rarity) {
        super("Multi-Shot", 4, Rarity.BASIC);
    }
}
