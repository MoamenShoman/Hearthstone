package model.cards.spells;

import model.cards.Rarity;

public class Flamestrike extends Spell implements AOESpell {
    public Flamestrike() {
        super("Flamestrike", 7, Rarity.BASIC);
    }
}
