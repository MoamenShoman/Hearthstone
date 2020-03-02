package model.cards.spells;

import model.cards.Rarity;

public class HolyNova extends Spell implements AOESpell {
    public HolyNova(String name, int manaCost, Rarity rarity) {
        super("Holy Nova", 5, Rarity.BASIC);
    }
}
