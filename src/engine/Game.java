package engine;

import model.heroes.Hero;

import java.util.Random;

public class Game {
    private Hero firstHero, secondHero, currentHero, opponent;

    public Hero getCurrentHero() {
        return currentHero;
    }

    public Hero getOpponent() {
        return opponent;
    }

    public Game(Hero p1, Hero p2) {
        firstHero = p1;
        secondHero = p2;
        Random r = new Random();
        int x = r.nextInt(2);
        if (x == 0) {
            currentHero = p1;
            opponent = p2;
        } else {
            currentHero = p2;
            opponent = p1;
        }
        currentHero.setCurrentManaCrystals(1);
    }
}
