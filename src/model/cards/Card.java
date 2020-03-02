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



}
