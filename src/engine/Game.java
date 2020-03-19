package engine;

import exceptions.*;
import model.cards.Card;
import model.cards.minions.Minion;
import model.heroes.Hero;
import model.heroes.HeroListener;

import java.util.Random;

public class Game implements HeroListener, ActionValidator {
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
        currentHero.setTotalManaCrystals(1);
    }

    @Override
    public void validateTurn(Hero user) throws NotYourTurnException {
        if(user == opponent){
            throw new NotYourTurnException();
        }
    }

    @Override
    public void validateAttack(Minion attacker, Minion target) throws CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException {
        if(currentHero.getHand().contains(attacker) && !currentHero.getField().contains(attacker)){
            throw new NotSummonedException();
        }
        if(attacker.isSleeping() || attacker.isAttacked() || attacker.getAttack() == 0){
            throw new CannotAttackException();
        }
        if(!target.isTaunt()){
            boolean flag = false;
            for(Minion m : opponent.getField()){
                if(m.isTaunt())
                    flag = true;
            }
            if(flag)
                throw new TauntBypassException();
        }
        if(currentHero.getField().contains(target)){
            throw new InvalidTargetException();
        }
    }

    @Override
    public void validateAttack(Minion attacker, Hero target) throws CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException {

    }

    @Override
    public void validateManaCost(Card card) throws NotEnoughManaException {

    }

    @Override
    public void validatePlayingMinion(Minion minion) throws FullFieldException {

    }

    @Override
    public void validateUsingHeroPower(Hero hero) throws NotEnoughManaException, HeroPowerAlreadyUsedException {

    }

    @Override
    public void onHeroDeath() {

    }

    @Override
    public void damageOpponent(int amount) {

    }

    @Override
    public void endTurn() throws FullHandException, CloneNotSupportedException {

    }
}
