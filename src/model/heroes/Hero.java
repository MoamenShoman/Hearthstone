package model.heroes;

import model.cards.Card;
import model.cards.minions.Minion;
import java.util.ArrayList;

abstract public class Hero {

    private String name;
    private int currentHP;
    private boolean heroPowerUsed;
    private int totalManaCrystals; //cannot exceed 10
    private int currentManaCrystals; //cannot exceed 10
    private ArrayList<Card> deck;
    private ArrayList<Minion> field;
    private ArrayList<Card> hand;
    private int fatigueDamage;

    public Hero(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public boolean isHeroPowerUsed() {
        return heroPowerUsed;
    }

    public int getTotalManaCrystals() {
        return totalManaCrystals;
    }

    public int getCurrentManaCrystals() {
        return currentManaCrystals;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public ArrayList<Minion> getField() {
        return field;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public void setHeroPowerUsed(boolean heroPowerUsed) {
        this.heroPowerUsed = heroPowerUsed;
    }

    public void setTotalManaCrystals(int totalManaCrystals) {
        this.totalManaCrystals = totalManaCrystals;
    }

    public void setCurrentManaCrystals(int currentManaCrystals) {
        this.currentManaCrystals = currentManaCrystals;
    }

    public void setField(ArrayList<Minion> field) {
        this.field = field;
    }
}
