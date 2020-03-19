package model.heroes;
import exceptions.FullHandException;

public interface HeroListener {
    public void onHeroDeath();
    public void damageOpponent(int amount);
    public void endTurn() throws FullHandException, CloneNotSupportedException;
}
