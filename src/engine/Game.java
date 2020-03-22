package engine;

import exceptions.*;
import model.cards.Card;
import model.cards.minions.Minion;
import model.heroes.*;
import java.util.Random;

public class Game implements HeroListener, ActionValidator {
    private Hero firstHero, secondHero, currentHero, opponent;
    private GameListener listener;
    public Hero getCurrentHero() {
        return currentHero;
    }

    public Hero getOpponent() {
        return opponent;
    }

    public void setListener(GameListener listener) {
        this.listener = listener;
    }

    public Game(Hero p1, Hero p2) throws FullHandException, CloneNotSupportedException {
        firstHero = p1;
        secondHero = p2;
        firstHero.setListener(this);
        secondHero.setListener(this);
        firstHero.setValidator(this);
        secondHero.setValidator(this);
        Random r = new Random();
        int x = r.nextInt(2);
        if (x == 0) {
            currentHero = p1;
            opponent = p2;
        } else {
            currentHero = p2;
            opponent = p1;
        }
        currentHero.setTotalManaCrystals(1);
        for(int i = 0; i < 3; i++){
            currentHero.drawCard();
            opponent.drawCard();
        }
    }

    @Override
    public void validateTurn(Hero user) throws NotYourTurnException {
        if (user == opponent) {
            throw new NotYourTurnException();
        }
    }

    @Override
    public void validateAttack(Minion attacker, Minion target) throws CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException {
        if (currentHero.getHand().contains(attacker) && !currentHero.getField().contains(attacker)) {
            throw new NotSummonedException();
        }
        if (attacker.isSleeping() || attacker.isAttacked() || attacker.getAttack() == 0) {
            throw new CannotAttackException();
        }
        if (!target.isTaunt()) {
            boolean flag = false;
            for (Minion m : opponent.getField()) {
                if (m.isTaunt())
                    flag = true;
            }
            if (flag)
                throw new TauntBypassException();
        }
        if (currentHero.getField().contains(target)) {
            throw new InvalidTargetException();
        }
    }

    @Override
    public void validateAttack(Minion attacker, Hero target) throws CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException {
        if (currentHero.getHand().contains(attacker) && !currentHero.getField().contains(attacker)) {
            throw new NotSummonedException();
        }
        if (attacker.isSleeping() || attacker.isAttacked() || attacker.getAttack() == 0) {
            throw new CannotAttackException();
        }
        boolean flag = false;
        for (Minion m : opponent.getField()) {
            if (m.isTaunt())
                flag = true;
        }
        if (flag)
            throw new TauntBypassException();
        if(currentHero == target || attacker.getName().equals("Icehowl"))
            throw new InvalidTargetException();
    }

    @Override
    public void validateManaCost(Card card) throws NotEnoughManaException {
        if(currentHero.getCurrentManaCrystals() < card.getManaCost())
            throw new NotEnoughManaException();
        currentHero.setCurrentManaCrystals(currentHero.getCurrentManaCrystals() - card.getManaCost());
    }

    @Override
    public void validatePlayingMinion(Minion minion) throws FullFieldException {
        if(currentHero.getField().size() == 7)
            throw new FullFieldException();
    }

    @Override
    public void validateUsingHeroPower(Hero hero) throws NotEnoughManaException, HeroPowerAlreadyUsedException {
        if(hero.getCurrentManaCrystals() < 2 )
            throw new NotEnoughManaException();
        if(hero.isHeroPowerUsed())
            throw new HeroPowerAlreadyUsedException();
        hero.setCurrentManaCrystals(hero.getCurrentManaCrystals() - 2);
    }

    @Override
    public void onHeroDeath() {
        listener.onGameOver();
    }

    @Override
    public void damageOpponent(int amount) {
        opponent.setCurrentHP(opponent.getCurrentHP()-amount);
        if(opponent.getCurrentHP() <= 0)
            onHeroDeath();
    }

    @Override
    public void endTurn() throws FullHandException, CloneNotSupportedException {
        Hero temp = currentHero;
        currentHero = opponent;
        opponent = temp;
        currentHero.setTotalManaCrystals(currentHero.getTotalManaCrystals()+1);
        currentHero.setHeroPowerUsed(false);
        for (Minion m : currentHero.getField()){
            m.setAttacked(false);
            m.setSleeping(false);
        }
        currentHero.drawCard();
    }
}
