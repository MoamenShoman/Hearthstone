package model.cards.spells;

import model.cards.Rarity;

public class SealOfChampions extends Spell implements MinionTargetSpell{
    public SealOfChampions() {
        super("Seal of Champions", 3, Rarity.COMMON);
    }
}
