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
        for (int i = 0; i < 3; i++) {
            currentHero.drawCard();
            opponent.drawCard();
        }
        opponent.drawCard();
    }

    @Override
    public void validateTurn(Hero user) throws NotYourTurnException {
        if (user != getCurrentHero()) {
            throw new NotYourTurnException();
        }
    }

    @Override
    public void validateAttack(Minion attacker, Minion target) throws CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException {
        if (!getCurrentHero().getField().contains(attacker)) {
            throw new NotSummonedException();
        }
        if (getCurrentHero().getField().contains(target)) {
            throw new InvalidTargetException("You can't attack a friendly minion");
        }
        if (!getOpponent().getField().contains(target)){
            throw new NotSummonedException();
        }
        if (attacker.isSleeping()) {
            throw new CannotAttackException("You can't attack with a sleeping minion");
        }else{
            if (attacker.isAttacked()){
                throw new CannotAttackException("Your minion has already attacked");
            }else if ( attacker.getAttack() == 0){
                throw new  CannotAttackException("Your minion's attack is zero");
            }
        }
        if (!target.isTaunt()) {
            boolean flag = false;
            for (Minion m : getOpponent().getField()) {
                if (m.isTaunt())
                    flag = true;
            }
            if (flag)
                throw new TauntBypassException();
        }
    }

    @Override
    public void validateAttack(Minion attacker, Hero target) throws CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException {
        if (!getCurrentHero().getField().contains(attacker)) {
            throw new NotSummonedException();
        }
        if (attacker.isSleeping()) {
            throw new CannotAttackException("You can't attack with a sleeping minion");
        }else{
            if (attacker.isAttacked()){
                throw new CannotAttackException("Your minion has already attacked");
            }else if ( attacker.getAttack() == 0){
                throw new  CannotAttackException("Your minion's attack is zero");
            }
        }
        boolean flag = false;
        for (Minion m : getOpponent().getField()) {
            if (m.isTaunt())
                flag = true;
        }
        if (flag)
            throw new TauntBypassException();
        if (getCurrentHero() == target)
            throw new InvalidTargetException("You can't attack your hero");

    }

    @Override
    public void validateManaCost(Card card) throws NotEnoughManaException {
        if (getCurrentHero().getCurrentManaCrystals() < card.getManaCost())
            throw new NotEnoughManaException();
    }

    @Override
    public void validatePlayingMinion(Minion minion) throws FullFieldException {
        if (getCurrentHero().getField().size() == 7)
            throw new FullFieldException();
    }

    @Override
    public void validateUsingHeroPower(Hero hero) throws NotEnoughManaException, HeroPowerAlreadyUsedException {
        if (hero.getCurrentManaCrystals() < 2)
            throw new NotEnoughManaException();
        if (hero.isHeroPowerUsed())
            throw new HeroPowerAlreadyUsedException();
    }

    @Override
    public void onHeroDeath() {
        listener.onGameOver();
    }

    @Override
    public void damageOpponent(int amount) {
        getOpponent().setCurrentHP(getOpponent().getCurrentHP() - amount);
    }

    @Override
    public void endTurn() throws FullHandException, CloneNotSupportedException {
        Hero temp = currentHero;
        currentHero = opponent;
        opponent = temp;
        currentHero.setTotalManaCrystals(currentHero.getTotalManaCrystals() + 1);
        currentHero.setHeroPowerUsed(false);
        for (Minion m : currentHero.getField()) {
            m.setAttacked(false);
            m.setSleeping(false);
        }
        currentHero.drawCard();
    }
}
