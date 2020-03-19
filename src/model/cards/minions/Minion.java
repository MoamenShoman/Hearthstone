package model.cards.minions;

import exceptions.InvalidTargetException;
import model.cards.Card;
import model.cards.Rarity;
import model.heroes.Hero;

public class Minion extends Card {

    private int attack; //can never fall below 0
    private int maxHP;
    private int currentHP; //starts with maxhp
    private boolean taunt;
    private boolean divine;
    private boolean sleeping;
    private boolean attacked;
    private boolean charge;
    private MinionListener listener;

    public void setListener(MinionListener listener) {
        this.listener = listener;
    }

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

    @Override
    public Minion clone() throws CloneNotSupportedException {
        return (Minion) super.clone();
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

    public void attack(Minion target) {
        if (!isDivine()) {
            setCurrentHP(getCurrentHP() - target.getAttack());
        } else {
            setDivine(false);
        }
        if (!target.isDivine()) {
            target.setCurrentHP(target.getCurrentHP() - getAttack());
        } else {
            setDivine(false);
        }
        if (target.getCurrentHP() <= 0) {
            target.listener.onMinionDeath(target);
        }
        if (getCurrentHP() <= 0) {
            listener.onMinionDeath(this);
        }
    }
    public void attack(Hero target) throws InvalidTargetException{
        if (getName().equals("Icehowl")){
            throw new InvalidTargetException();
        }
        target.setCurrentHP(target.getCurrentHP()-getAttack());
    }

}
