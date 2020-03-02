package model.heroes;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    final static ArrayList<Minion> getAllNeutralMinions(String filePath) throws IOException {
        String currentLine = "";
        ArrayList<Minion> neutralMinions = new ArrayList<>();
        FileReader fileReader = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fileReader);
        while ((currentLine = br.readLine()) != null) {
            String[] result = currentLine.split(",");
            String name = result[0];
            int manaCost = Integer.parseInt(result[1]);
            Rarity rarity = result[2].equals("b") ? Rarity.BASIC :
                            result[2].equals("c") ? Rarity.COMMON :
                            result[2].equals("r") ? Rarity.RARE :
                            result[2].equals("e") ? Rarity.EPIC :
                            Rarity.LEGENDARY;
            int attack = Integer.parseInt(result[3]);
            int maxHP = Integer.parseInt(result[4]);
            boolean taunt = result[5].equals("TRUE");
            boolean divine = result[6].equals("TRUE");
            boolean charge = result[7].equals("TRUE");
            Minion minion = new Minion(name, manaCost, rarity, attack, maxHP, taunt, divine, charge);
            neutralMinions.add(minion);
        }
        return neutralMinions;
    }
}
