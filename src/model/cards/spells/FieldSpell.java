package model.cards.spells;
import model.cards.minions.Minion;
import java.util.ArrayList;

public interface FieldSpell {
    public void performAction(ArrayList<Minion> field);
}
