package model.heroes;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.cards.minions.MinionListener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

abstract public class Hero implements MinionListener {

    private String name;
    private int currentHP;
    private boolean heroPowerUsed;
    private int totalManaCrystals; //cannot exceed 10
    private int currentManaCrystals; //cannot exceed 10
    private ArrayList<Card> deck;
    private ArrayList<Minion> field;
    private ArrayList<Card> hand;
    private int fatigueDamage;

    public Hero(String name) throws IOException, CloneNotSupportedException {
        this.name = name;
        currentHP = 30;
        deck = new ArrayList<>();
        field = new ArrayList<>();
        hand = new ArrayList<>();
        buildDeck();
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
        if (currentHP <= 30)
            this.currentHP = currentHP;
        else this.currentHP = 30;
    }

    public void setHeroPowerUsed(boolean heroPowerUsed) {
        this.heroPowerUsed = heroPowerUsed;
    }

    public void setTotalManaCrystals(int totalManaCrystals) {
        if (totalManaCrystals <= 10 && totalManaCrystals >= 0)
            this.totalManaCrystals = totalManaCrystals;
        else if (totalManaCrystals > 10)
            this.totalManaCrystals = 10;
        else this.totalManaCrystals = 0;
        setCurrentManaCrystals(this.totalManaCrystals);
    }

    public void setCurrentManaCrystals(int currentManaCrystals) {
        if (currentManaCrystals >= 0 && currentManaCrystals <= 10)
            this.currentManaCrystals = currentManaCrystals;
        else if (currentManaCrystals > 10)
            this.currentManaCrystals = 10;
        else this.currentManaCrystals = 0;
    }

    public void onMinionDeath(Minion m) {
        field.remove(m);
    }

    public final static ArrayList<Minion> getAllNeutralMinions(String filePath) throws IOException {
        String currentLine = "";
        ArrayList<Minion> neutralMinions = new ArrayList<>();
        FileReader fileReader = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fileReader);
        while ((currentLine = br.readLine()) != null) {
            String[] result = currentLine.split(",");
            if (result[0].equals("Icehowl")) {
                neutralMinions.add(new Icehowl());
            } else {
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
        }
        return neutralMinions;
    }


    public final static ArrayList<Minion> getNeutralMinions(ArrayList<Minion> minions, int count) throws CloneNotSupportedException {
        ArrayList<Minion> result = new ArrayList<>();
        Random r = new Random();
        HashMap<Integer, Integer> mapMinions = new HashMap<>();

        while (result.size() < count) {
            //  0 <= idx < minions.size();
            int idx = r.nextInt(minions.size());
            // The minion must not appear more than 2 times in the list result
            if (mapMinions.containsKey(idx) && mapMinions.get(idx) == 1 && minions.get(idx).getRarity() != Rarity.LEGENDARY) {
                mapMinions.put(idx, 2);
                result.add(minions.get(idx).clone());
            } else if (!mapMinions.containsKey(idx)) {
                mapMinions.put(idx, 1);
                result.add(minions.get(idx).clone());
            }

        }
        return result;
    }

    public abstract void buildDeck() throws IOException, CloneNotSupportedException;
}
