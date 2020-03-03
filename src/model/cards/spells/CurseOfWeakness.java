package model.cards.spells;

import model.cards.Rarity;

public class CurseOfWeakness extends Spell implements AOESpell {

    public CurseOfWeakness() {
        super("Curse of Weakness", 2, Rarity.RARE);
    }
}
