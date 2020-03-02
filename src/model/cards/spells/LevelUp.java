package model.cards.spells;

import model.cards.Rarity;

public class LevelUp extends Spell implements FieldSpell {
    public LevelUp() {
        super("Level Up!", 6, Rarity.EPIC);
    }
}
