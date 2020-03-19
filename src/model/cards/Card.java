package model.cards;


import model.cards.minions.Minion;
import model.cards.spells.Spell;

abstract public class Card implements Cloneable {
    private String name;
    private int manaCost;   // 0 <= manaCost <= 10
    private Rarity rarity;

    public Card(String name, int manaCost, Rarity rarity) {
        this.name = name;
        if (manaCost <= 10 && manaCost >= 0)
            this.manaCost = manaCost;
        else if (manaCost > 10) this.manaCost = 10;
        else this.manaCost = 0;
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    @Override
    public Card clone() throws CloneNotSupportedException {
        return (Card) super.clone();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManaCost(int manaCost) {
        if (manaCost <= 10 && manaCost >= 0)
            this.manaCost = manaCost;
        else if (manaCost > 10)
            this.manaCost = 10;
        else this.manaCost = 0;
    }


    public int getManaCost() {
        return manaCost;
    }

    public Rarity getRarity() {
        return rarity;
    }
}
