package model.cards.spells;

import model.cards.Rarity;
import model.cards.minions.Minion;

import java.util.ArrayList;

public class TwistingNether extends Spell implements AOESpell{
    public TwistingNether(){
        super("Twisting Nether", 8 , Rarity.EPIC );
    }

    @Override
    public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
        for (int i = 0; i < oppField.size(); i++) {
            int s = oppField.size();
            Minion m = oppField.get(i);
            m.setCurrentHP(0);
            if (s > oppField.size())
                i--;
        }
        for (int i = 0; i < curField.size(); i++) {
            int s = curField.size();
            Minion m = curField.get(i);
            m.setCurrentHP(0);
            if (s > curField.size())
                i--;
        }
    }
}
