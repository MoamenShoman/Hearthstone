package model.cards.minions;

import model.cards.Card;
import model.cards.Rarity;

public class Minion extends Card {

    private int attack; //can never fall below 0
    private int maxHP;
    private int currentHP; //starts with maxhp
    private boolean taunt;
    private boolean divine;
    private boolean sleeping;
    private boolean attacked;
    private boolean charge;

    public Minion(String name, int manaCost, Rarity rarity, int attack, int maxHP, boolean taunt, boolean divine, boolean charge) {
        super(name, manaCost, rarity);
        if (attack >= 0)
            this.attack = attack;
        else this.attack = 0;
        this.maxHP = maxHP;
        currentHP = maxHP;
        this.taunt = taunt;
        this.divine = divine;
        this.charge = charge;
        sleeping = !charge;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        if (attack >= 0)
            this.attack = attack;
        else this.attack = 0;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        if (currentHP <= maxHP)
            this.currentHP = currentHP;
        else this.currentHP = maxHP;
    }

    public boolean isTaunt() {
        return taunt;
    }

    public void setTaunt(boolean taunt) {
        this.taunt = taunt;
    }

    public boolean isDivine() {
        return divine;
    }

    public void setDivine(boolean divine) {
        this.divine = divine;
    }

    public boolean isSleeping() {
        return sleeping;
    }

    public void setSleeping(boolean sleeping) {
        this.sleeping = sleeping;
    }

    public boolean isAttacked() {
        return attacked;
    }

    public void setAttacked(boolean attacked) {
        this.attacked = attacked;
    }

    public boolean isCharge() {
        return charge;
    }

    public void setCharge(boolean charge) {
        this.charge = charge;
    }
}
