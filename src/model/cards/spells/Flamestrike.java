package model.cards.spells;

import model.cards.Rarity;

public class Flamestrike extends Spell implements AOESpell {
    public Flamestrike(String name, int manaCost, Rarity rarity) {
        super("Flamestrike", 7, Rarity.BASIC);
    }
}
