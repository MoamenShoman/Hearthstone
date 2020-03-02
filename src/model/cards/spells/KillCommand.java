package model.cards.spells;

import model.cards.Rarity;

public class KillCommand extends Spell implements MinionTargetSpell, HeroTargetSpell {
    public KillCommand(String name, int manaCost, Rarity rarity) {
        super("Kill Command", 3, Rarity.COMMON);
    }
}
