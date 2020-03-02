package model.cards;


abstract public class Card {
    private String name;
    private int manaCost;   // 0 < manaCost <= 10
    private Rarity rarity;

    public Card(String name, int manaCost ,Rarity rarity){
        this.name=name;
        this.manaCost=manaCost;
        this.rarity=rarity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public int getManaCost() {
        return manaCost;
    }

    public Rarity getRarity() {
        return rarity;
    }
}
