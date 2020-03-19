package model.cards.spells;
import model.cards.minions.Minion;
import java.util.ArrayList;

public interface AOESpell {
    public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField);

}
