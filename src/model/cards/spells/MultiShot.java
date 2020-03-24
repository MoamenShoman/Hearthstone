package model.cards.spells;

import model.cards.Rarity;
import model.cards.minions.Minion;

import java.util.ArrayList;
import java.util.Random;

public class MultiShot extends Spell implements AOESpell {
    public MultiShot() {
        super("Multi-Shot", 4, Rarity.BASIC);
    }

    @Override
    public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
        if (oppField.size() == 1) {
            oppField.get(0).setCurrentHP(oppField.get(0).getCurrentHP() - 3);
        } else {
            if (oppField.size() != 0) {
                Random r = new Random();
                int idx = r.nextInt(oppField.size());
                int s = oppField.size();
                oppField.get(idx).setCurrentHP(oppField.get(idx).getCurrentHP() - 3);
                if (oppField.size() < s) {
                    idx = r.nextInt(oppField.size());
                    oppField.get(idx).setCurrentHP(oppField.get(idx).getCurrentHP() - 3);
                } else {
                    int idx2;
                    do {
                        idx2 = r.nextInt(oppField.size());
                    } while (idx2 == idx);
                    oppField.get(idx2).setCurrentHP(oppField.get(idx2).getCurrentHP() - 3);
                }

            }
        }
    }
}
