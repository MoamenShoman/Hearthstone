package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.cards.minions.MinionListener;
import model.cards.spells.AOESpell;
import model.cards.spells.CurseOfWeakness;
import model.cards.spells.DivineSpirit;
import model.cards.spells.Flamestrike;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.HolyNova;
import model.cards.spells.KillCommand;
import model.cards.spells.LeechingSpell;
import model.cards.spells.LevelUp;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.MultiShot;
import model.cards.spells.Polymorph;
import model.cards.spells.Pyroblast;
import model.cards.spells.SealOfChampions;
import model.cards.spells.ShadowWordDeath;
import model.cards.spells.SiphonSoul;
import model.cards.spells.Spell;
import model.cards.spells.TwistingNether;
import model.heroes.Hero;
import model.heroes.HeroListener;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;

import org.junit.Before;
import org.junit.Test;

import engine.ActionValidator;
import engine.Game;
import engine.GameListener;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
public class M2PublicTests {

	String gamePath = "engine.Game";
	String cannotAttackExceptionPath = "exceptions.CannotAttackException";
	String fullFieldExceptionPath = "exceptions.FullFieldException";
	String fullHandExceptionPath = "exceptions.FullHandException";
	String hearthstoneExceptionPath = "exceptions.HearthstoneException";
	String heroPowerAlreadyUsedExceptionPath = "exceptions.HeroPowerAlreadyUsedException";
	String invalidTargetExceptionPath = "exceptions.InvalidTargetException";
	String notEnoughManaExceptionPath = "exceptions.NotEnoughManaException";
	String notSummonedExceptionPath = "exceptions.NotSummonedException";
	String notYourTurnExceptionPath = "exceptions.NotYourTurnException";
	String tauntBypassExceptionPath = "exceptions.TauntBypassException";
	String cardPath = "model.cards.Card";
	String icehowlPath = "model.cards.minions.Icehowl";
	String minionPath = "model.cards.minions.Minion";
	String rarityPath = "model.cards.Rarity";
	String aOESpellPath = "model.cards.spells.AOESpell";
	String curseOfWeaknessPath = "model.cards.spells.CurseOfWeakness";
	String divineSpiritPath = "model.cards.spells.DivineSpirit";
	String fieldSpellPath = "model.cards.spells.FieldSpell";
	String flamestrikePath = "model.cards.spells.Flamestrike";
	String heroTargetSpellPath = "model.cards.spells.HeroTargetSpell";
	String holyNovaPath = "model.cards.spells.HolyNova";
	String killCommandPath = "model.cards.spells.KillCommand";
	String leechingSpellPath = "model.cards.spells.LeechingSpell";
	String levelUpPath = "model.cards.spells.LevelUp";
	String minionTargetSpellPath = "model.cards.spells.MinionTargetSpell";
	String multiShotPath = "model.cards.spells.MultiShot";
	String polymorphPath = "model.cards.spells.Polymorph";
	String pyroblastPath = "model.cards.spells.Pyroblast";
	String sealOfChampionsPath = "model.cards.spells.SealOfChampions";
	String shadowWordDeathPath = "model.cards.spells.ShadowWordDeath";
	String siphonSoulPath = "model.cards.spells.SiphonSoul";
	String spellPath = "model.cards.spells.Spell";
	String twistingNetherPath = "model.cards.spells.TwistingNether";
	String heroPath = "model.heroes.Hero";
	String hunterPath = "model.heroes.Hunter";
	String magePath = "model.heroes.Mage";
	String paladinPath = "model.heroes.Paladin";
	String priestPath = "model.heroes.Priest";
	String warlockPath = "model.heroes.Warlock";
	String actionValidatorPath = "engine.ActionValidator";
	String gameListenerPath = "engine.GameListener";
	String minionListenerPath = "model.cards.minions.MinionListener";
	String heroListenerPath = "model.heroes.HeroListener";
	
	static ArrayList<Object> callParameters = new ArrayList<Object>();
	
	@Before
	public void reset()
	{
		callParameters = new ArrayList<Object>();
	}
	
	
	
	
	@Test(timeout = 3000)
	public void testMinionAttacksHeroUpdatingAttacked() throws Exception 
	{
		Minion attackerMinion = new Minion("test", 0, null, 1, 2, false, true, true);
		Mage attackedHero = new Mage();
		
		attackerMinion.attack(attackedHero);
		
		assertEquals("The attaker minion should update the attacked flag when attaking a hero.", true, attackerMinion.isAttacked());
	}

	
	@Test(timeout = 3000)
	public void testMinionAttacksMinionAttackerMinionLoseDivine() throws Exception 
	{
		Minion attackerMinion = new Minion("test", 0, null, 1, 2, false, true, true);
		Minion attackedMinion = new Minion("test", 0, null, 1, 2, false, true, true);
		
		attackerMinion.attack(attackedMinion);
		
		assertEquals("The attaker minion should lose divine.", false, attackerMinion.isDivine());
	}
	
	@Test(timeout = 3000)
	public void testMinionAttacksMinionAttackerMinionnotLosingDivine() throws Exception 
	{
		Minion attackerMinion = new Minion("test", 0, null, 1, 2, false, true, true);
		Minion attackedMinion = new Minion("test", 0, null, 0, 2, false, true, true);
		
		attackerMinion.attack(attackedMinion);
		
		assertEquals("The attaker minion should not lose divine.", true, attackerMinion.isDivine());
	}
	
	
	@Test(timeout = 3000)
	public void testMinionAttacksMinionAttackedMinionLoseHP() throws Exception 
	{
		Minion attackerMinion = new Minion("test", 0, null, 1, 2, false, false, true);
		Minion attackedMinion = new Minion("test", 0, null, 1, 2, false, false, true);
		
		attackerMinion.attack(attackedMinion);
		
		assertEquals("The attaked minion should lose HP.", 1, attackedMinion.getCurrentHP());
	}
	
	@Test(timeout = 3000)
	public void testMinionAttacksMinionAttackerMinionnotLosingHP() throws Exception 
	{
		Minion attackerMinion = new Minion("test", 0, null, 1, 2, false, false, true);
		Minion attackedMinion = new Minion("test", 0, null, 0, 2, false, false, true);
		
		attackerMinion.attack(attackedMinion);
		
		assertEquals("The attaker minion should not lose HP.", 2, attackerMinion.getCurrentHP());
	}
	
	
	@Test(timeout = 3000)
	public void testMinionAttacksMinionOneNotLosingDivine() throws Exception 
	{
		Minion attackerMinion = new Minion("test", 0, null, 1, 2, false, true, true);
		Minion attackedMinion = new Minion("test", 0, null, 0, 2, false, true, true);
		
		attackerMinion.attack(attackedMinion);
		
		assertEquals("The attaker minion should not lose divine if the target Minion has 0 attack.", true, attackerMinion.isDivine());
	}
	
	
	@Test(timeout = 3000)
	public void testMinionAttacksMinionBothLoseHP() throws Exception 
	{
		Minion attackerMinion = new Minion("test", 0, null, 1, 2, false, false, true);
		Minion attackedMinion = new Minion("test", 0, null, 1, 2, false, false, true);
		
		attackerMinion.attack(attackedMinion);
		
		assertEquals("The attaker minion should lose HP.", 1, attackerMinion.getCurrentHP());
		assertEquals("The attaked minion should lose HP.", 1, attackedMinion.getCurrentHP());
	}

	
	@Test(timeout = 3000)
	public void testMinionAttacksMinionLoseHPDivine() throws Exception 
	{
		Minion attackerMinion = new Minion("test", 0, null, 1, 2, false, false, true);
		Minion attackedMinion = new Minion("test", 0, null, 1, 2, false, true, true);
		
		attackerMinion.attack(attackedMinion);
		
		assertEquals("The attaker minion should lose HP.", 1, attackerMinion.getCurrentHP());
		assertEquals("The attaked minion should lose divine.", false, attackedMinion.isDivine());
	}
	
	@Test(timeout = 3000)
	public void testMinionAttacksMinionNotLosingHPDivine() throws Exception 
	{
		Minion attackerMinion = new Minion("test", 0, null, 0, 2, false, false, true);
		Minion attackedMinion = new Minion("test", 0, null, 0, 2, false, true, true);
		
		attackerMinion.attack(attackedMinion);
		
		assertEquals("The attaker minion should not lose HP.", 2, attackerMinion.getCurrentHP());
		assertEquals("The attaked minion should not lose divine.", true, attackedMinion.isDivine());
	}
	
	@Test(timeout = 3000)
	public void testMinionAttacksHeroLoseHP() throws Exception 
	{
		Minion attackerMinion = new Minion("test", 0, null, 1, 2, false, true, true);
		Mage attackedHero = new Mage();
		
		attackerMinion.attack(attackedHero);
		
		assertEquals("The attaked hero should lose HP.", 29, attackedHero.getCurrentHP());
	}

	
	@Test(timeout = 3000)
	public void testMinionListenerExist() throws Exception 
	{
		testInstanceVariableIsPresent(Minion.class, "listener", true);
		testInstanceVariableIsPrivate(Minion.class, "listener");
	}
	
	@Test(timeout = 3000)
	public void testMinionClassHasAttacksMinionMethod() throws Exception 
	{
		testExistsInClass(Minion.class, "attack", true, void.class, Minion.class);
	}
	
	@Test(timeout = 3000)
	public void testMinionClassHasAttacksHeroMethod() throws Exception 
	{
		testExistsInClass(Minion.class, "attack", true, void.class, Hero.class);
	}
	
	@Test(timeout = 3000)
	public void testMinionListenerSetterExist() throws Exception 
	{
		testSetterMethodExistsInClass(Minion.class, "setListener", MinionListener.class, true);
	}
	
	@Test(timeout = 1000)
	public void testInterfaceClonableIsImplementedByClassMinion() throws Exception
	{
		testClassImplementsInterface(Minion.class, Cloneable.class);
	}
	
	@Test(timeout = 1000)
	public void testCloningMinion() throws Exception
	{
		Minion firstMinion = new Minion(null, 3, null, 3, 3, false, false, false);
		Minion secondMinion = (Minion)firstMinion.clone();
		assertFalse("The Minion should be cloned properly.", firstMinion == secondMinion);
	}
	
	@Test(timeout = 3000)
	public void testMinionListenerSetterLogic() throws Exception 
	{
		MinionListener testListener = new MinionListener() {
			@Override
			public void onMinionDeath(Minion m) {
				callParameters.add(m);
			}
		};
		
		Minion minion = new Minion("test", 0, null, 1, 2, false, true, true);
		testSetterLogic(minion, "listener", testListener, testListener, MinionListener.class);
	}
	
	@Test(timeout = 3000)
	public void testMinionListenerCalledOnDeath() throws Exception 
	{
		MinionListener testListener = new MinionListener() {
			@Override
			public void onMinionDeath(Minion m) {
				callParameters.add(m);
			}
		};
		
		Minion minion = new Minion("test", 0, null, 1, 2, false, true, true);
		testSetterLogic(minion, "listener", testListener, testListener, MinionListener.class);
		minion.setCurrentHP(0);
		assertEquals("The minion should notify his death when current hp is 0.", minion, callParameters.get(0));
	}
	
	@Test(timeout = 3000)
	public void testMinionListenerHasOnMinionDeath() throws Exception 
	{
		testExistsInClass(Class.forName("model.cards.minions.MinionListener"), "onMinionDeath", true, void.class, Minion.class);
	}
	
	
	
	@Test(timeout = 3000)
	public void testIcehowlAttacksHero() throws Exception 
	{
		Icehowl attackerMinion = new Icehowl();
		Mage attackedHero = new Mage();
		
		try
		{
			attackerMinion.attack(attackedHero);
			fail("Icehowl should not attack heroes.");
		}
		catch(InvalidTargetException e)
		{
		}
	}
	
	
	@Test(timeout = 3000)
	public void testIcehowlAttacksMinion() throws Exception 
	{
		Icehowl attackerMinion = new Icehowl();
		Minion attackedMinion = new Minion("test", 0, null, 0, 15, false, false, true);
		
		try
		{
			attackerMinion.attack(attackedMinion);
		}
		catch(Exception e)
		{
			if(e instanceof InvalidTargetException)
				fail("Icehowl should attack minions normaly.");
		}
	}
	
	
	@Test(timeout = 3000)
	public void testHeroTargetSpellHasPerformActionMethod() throws Exception 
	{
		testExistsInClass(HeroTargetSpell.class, "performAction", true, void.class, Hero.class);
	}
	
	@Test(timeout = 3000)
	public void testLeechingSpellHasPerformActionMethod() throws Exception 
	{
		testExistsInClass(LeechingSpell.class, "performAction", true, int.class, Minion.class);
	}
	
	@Test(timeout = 3000)
	public void testMinionTargetSpellHasPerformActionMethod() throws Exception 
	{
		testExistsInClass(MinionTargetSpell.class, "performAction", true, void.class, Minion.class);
	}
	
	
	@Test(timeout = 3000)
	public void testCurseOfWeaknessAffectingOpponentField() throws Exception 
	{
		CurseOfWeakness curseOfWeakness = new CurseOfWeakness();
		ArrayList<Minion> opponentField = new ArrayList<Minion>();
		ArrayList<Minion> currentField = new ArrayList<Minion>();
		for(int i=0;i<5;i++)
			opponentField.add(new Minion("test", 0, null, i, 2, false, false, true));
		curseOfWeakness.performAction(opponentField, currentField);
		for(int i=0;i<5;i++)
			if(opponentField.get(i).getAttack()!=((i<=2)?0:i-2))
				fail("Curse Of Weakness should affect all minions on the opponent field with the proper amount of 2.");
	}
	
	@Test(timeout = 3000)
	public void testCurseOfWeaknessNotAffectingCurrentField() throws Exception 
	{
		CurseOfWeakness curseOfWeakness = new CurseOfWeakness();
		ArrayList<Minion> opponentField = new ArrayList<Minion>();
		ArrayList<Minion> currentField = new ArrayList<Minion>();
		for(int i=0;i<5;i++)
			currentField.add(new Minion("test", 0, null, 3, 2, false, false, true));
		curseOfWeakness.performAction(opponentField, currentField);
		for(int i=0;i<5;i++)
			if(currentField.get(i).getAttack()!=3)
				fail("Curse Of Weakness should not affect eny minion on the current field.");
	}
	
	
	@Test(timeout = 3000)
	public void testDivineSpiritHasPerformActionMethod() throws Exception 
	{
		testExistsInClass(DivineSpirit.class, "performAction", true, void.class, Minion.class);
	}
	
	@Test(timeout = 3000)
	public void testDivineSpiritAffectingMinion() throws Exception 
	{
		
		DivineSpirit divineSpirit = new DivineSpirit();
		
		Minion minion = new Minion("test", 0, null, 3, 2, false, false, true);
		minion.setMaxHP(3);
		
		divineSpirit.performAction(minion);
		
		assertEquals("The minion Max HP should be doubled.", 6, minion.getMaxHP());
		assertEquals("The minion current HP should be doubled.", 4, minion.getCurrentHP());
		
	}
	
	
	@Test(timeout = 3000)
	public void testFlamestrikeHasPerformActionMethod() throws Exception 
	{
		testExistsInClass(Flamestrike.class, "performAction", true, void.class, ArrayList.class, ArrayList.class);
	}
	
	@Test(timeout = 3000)
	public void testFlamestrikeAffectingOpponentField() throws Exception 
	{
		Flamestrike flamestrike = new Flamestrike();
		
		ArrayList<Minion> opponentField = new ArrayList<Minion>();
		ArrayList<Minion> currentField = new ArrayList<Minion>();
		
		for(int i=0;i<4;i++)
			opponentField.add(new Minion("test", 0, null, 1, 9, false, false, true));

		opponentField.add(new Minion("test", 0, null, 1, 9, false, true, true));
		
		flamestrike.performAction(opponentField, currentField);
		
		for(int i=0;i<4;i++)
			if(opponentField.get(i).getCurrentHP() != 5)
				fail("Flamestrike should damage all minions on the opponent field.");
		
		if(opponentField.get(4).isDivine())
			fail("Flamestrike should destroy the divine shield of opponent minions.");
		
		if(opponentField.get(4).getCurrentHP() != 9)
			fail("Flamestrike should not damage opponent minions with divine shield.");
	}
	
	
	
	@Test(timeout = 30000)
	public void testFlamestrikeAffectingOpponentFieldAndKillingOneFirstMinionOnField() throws Exception 
	{
		Flamestrike flamestrike = new Flamestrike();
		
		ArrayList<Minion> currentField = new ArrayList<Minion>();
		
		Mage minionListener = new Mage();
		
		minionListener.getField().clear();
		
		Minion minionToDie = new Minion("test", 0, null, 1, 4, false, false, true);
		minionListener.getField().add(minionToDie);
		
		for(int i=0;i<4;i++)
			minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		Minion.class.getMethod("setListener", MinionListener.class).invoke(minionToDie, minionListener);
		
		flamestrike.performAction(minionListener.getField(), currentField);
		
		if(minionListener.getField().contains(minionToDie))
			fail("As the first minion on the opponent field is at HP 4, the damage caused by Flamestrike should Kill it.");
		
		if(minionListener.getField().size()!= 4)
			fail("As only one minion in this case is at HP 4 or less, out of 5 minions, 4 should survive.");
		
		for(int i=0;i<minionListener.getField().size();i++)
			if(minionListener.getField().get(i).getCurrentHP() != 5)
				fail("After the damage of Flamestrike causes a minion to die, the other minions should be damage correctly.");
	}
	
	@Test(timeout = 30000)
	public void testFlamestrikeAffectingOpponentFieldAndKillingOneMinionOnMiddle() throws Exception 
	{
		Flamestrike flamestrike = new Flamestrike();
		
		ArrayList<Minion> currentField = new ArrayList<Minion>();
		
		Mage minionListener = new Mage();
		
		
		for(int i=0;i<2;i++)
			minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		Minion minionToDie = new Minion("test", 0, null, 1, 4, false, false, true);
		minionListener.getField().add(minionToDie);
		
		for(int i=0;i<2;i++)
			minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		Minion.class.getMethod("setListener", MinionListener.class).invoke(minionToDie, minionListener);
		
		flamestrike.performAction(minionListener.getField(), currentField);
		
		if(minionListener.getField().contains(minionToDie))
			fail("As the middle minion on the opponent field is at HP 4, the damage caused by Flamestrike should Kill it.");
	
		if(minionListener.getField().size()!= 4)
			fail("As only one minion in this case is at HP 4 or less, out of 5 minions, 4 should survive.");
		
		for(int i=0;i<minionListener.getField().size();i++)
			if(minionListener.getField().get(i).getCurrentHP() != 5)
				fail("After the damage of Flamestrike causes a minion to die, the other minions should be damage correctly.");
	}
	
	@Test(timeout = 30000)
	public void testFlamestrikeAffectingOpponentFieldAndKillingTwoMinionOnStart() throws Exception 
	{
		Flamestrike flamestrike = new Flamestrike();
		
		ArrayList<Minion> currentField = new ArrayList<Minion>();
		
		Mage minionListener = new Mage();
		
		Minion minionToDie1 = new Minion("test", 0, null, 1, 4, false, false, true);
		minionListener.getField().add(minionToDie1);
		Minion.class.getMethod("setListener", MinionListener.class).invoke(minionToDie1, minionListener);
		
		Minion minionToDie2 = new Minion("test", 0, null, 1, 4, false, false, true);
		minionListener.getField().add(minionToDie2);
		Minion.class.getMethod("setListener", MinionListener.class).invoke(minionToDie2, minionListener);
		
		for(int i=0;i<3;i++)
			minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		flamestrike.performAction(minionListener.getField(), currentField);
		
		if(minionListener.getField().contains(minionToDie1)||minionListener.getField().contains(minionToDie2))
			fail("As the two minion at the start of the opponent field are at HP 4, the damage caused by Flamestrike should Kill them.");
	
		if(minionListener.getField().size()!= 3)
			fail("As only two minion in this case are at HP 2 or less, out of 5 minions, 3 should survive.");
		
		for(int i=0;i<minionListener.getField().size();i++)
			if(minionListener.getField().get(i).getCurrentHP() != 5)
				fail("After the damage of Flamestrike causes a minion to die, the other minions should be damage correctly.");
	}
	
	@Test(timeout = 30000)
	public void testFlamestrikeAffectingOpponentFieldAndKillingTwoMinionOnEnd() throws Exception 
	{
		Flamestrike flamestrike = new Flamestrike();
		
		ArrayList<Minion> currentField = new ArrayList<Minion>();
		
		Mage minionListener = new Mage();
		
		for(int i=0;i<3;i++)
			minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		Minion minionToDie1 = new Minion("test", 0, null, 1, 4, false, false, true);
		minionListener.getField().add(minionToDie1);
		Minion.class.getMethod("setListener", MinionListener.class).invoke(minionToDie1, minionListener);
		
		Minion minionToDie2 = new Minion("test", 0, null, 1, 4, false, false, true);
		minionListener.getField().add(minionToDie2);
		Minion.class.getMethod("setListener", MinionListener.class).invoke(minionToDie2, minionListener);
		
		flamestrike.performAction(minionListener.getField(), currentField);
		
		if(minionListener.getField().contains(minionToDie1)||minionListener.getField().contains(minionToDie2))
			fail("As the two minion at the end of the opponent field are at HP 4, the damage caused by Flamestrike should Kill them.");
	
		if(minionListener.getField().size()!= 3)
			fail("As only two minion in this case are at HP 2 or less, out of 5 minions, 3 should survive.");
		
		for(int i=0;i<minionListener.getField().size();i++)
			if(minionListener.getField().get(i).getCurrentHP() != 5)
				fail("After the damage of Flamestrike causes a minion to die, the other minions should be damage correctly.");
	}
	
		
	@Test(timeout = 30000)
	public void testFlamestrikeAffectingOpponentFieldAndKillingAllMinionsOnField() throws Exception 
	{
		Flamestrike flamestrike = new Flamestrike();
		
		ArrayList<Minion> currentField = new ArrayList<Minion>();
		
		Mage minionListener = new Mage();
		
		for(int i=0;i<5;i++)
		{
			Minion minionToDie = new Minion("test", 0, null, 1, 4, false, false, true);
			minionListener.getField().add(minionToDie);
			minionToDie.setListener(minionListener);
		}
		
		flamestrike.performAction(minionListener.getField(), currentField);
		if(minionListener.getField().size() != 0)
			fail("As all the minions on the opponent field are at HP 4, the damage caused by Flamestrike should Kill them all.");
	}
	
	
	@Test(timeout = 3000)
	public void testHolyNovaHasPerformActionMethod() throws Exception 
	{
		testExistsInClass(HolyNova.class, "performAction", true, void.class, ArrayList.class, ArrayList.class);
	}
	
	
	
	@Test(timeout = 3000)
	public void testHolyNovaAffectingCurrentField() throws Exception 
	{
		HolyNova holyNova = new HolyNova();
		
		ArrayList<Minion> opponentField = new ArrayList<Minion>();
		ArrayList<Minion> currentField = new ArrayList<Minion>();
		
		for(int i=0;i<5;i++)
		{
			Minion minion = new Minion("test", 0, null, 1, 9, false, false, true);
			minion.setCurrentHP(5);
			currentField.add(minion);
		}
		
		holyNova.performAction(opponentField, currentField);
		
		for(int i=0;i<5;i++)
			if(currentField.get(i).getCurrentHP() != 7)
				fail("HolyNova should heal all minions on the current field.");
	}
	
	
	
	@Test(timeout = 30000)
	public void testHolyNovaAffectingOpponentFieldAndKillingOneMinionOnEnd() throws Exception 
	{
		HolyNova holyNova = new HolyNova();
		
		ArrayList<Minion> currentField = new ArrayList<Minion>();
		
		Mage minionListener = new Mage();
		
		for(int i=0;i<4;i++)
			minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		Minion minionToDie = new Minion("test", 0, null, 1, 2, false, false, true);
		minionListener.getField().add(minionToDie);
		
		minionToDie.setListener(minionListener);
		
		holyNova.performAction(minionListener.getField(), currentField);
		
		if(minionListener.getField().contains(minionToDie))
			fail("As the last minion on the opponent field is at HP 2, the damage caused by HolyNova should Kill it.");
	
		if(minionListener.getField().size()!= 4)
			fail("As only one minion in this case is at HP 2 or less, out of 5 minions, 4 should survive.");
		
		for(int i=0;i<minionListener.getField().size();i++)
			if(minionListener.getField().get(i).getCurrentHP() != 7)
				fail("After the damage of HolyNova causes a minion to die, the other minions should be damage correctly.");
	}
	
	@Test(timeout = 30000)
	public void testHolyNovaAffectingOpponentFieldAndKillingOneMinionOnMiddle() throws Exception 
	{
		HolyNova holyNova = new HolyNova();
		
		ArrayList<Minion> currentField = new ArrayList<Minion>();
		
		Mage minionListener = new Mage();
		
		
		for(int i=0;i<2;i++)
			minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		Minion minionToDie = new Minion("test", 0, null, 1, 2, false, false, true);
		minionListener.getField().add(minionToDie);
		
		for(int i=0;i<2;i++)
			minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		minionToDie.setListener(minionListener);
		
		holyNova.performAction(minionListener.getField(), currentField);
		
		if(minionListener.getField().contains(minionToDie))
			fail("As the middle minion on the opponent field is at HP 2, the damage caused by HolyNova should Kill it.");
	
		if(minionListener.getField().size()!= 4)
			fail("As only one minion in this case is at HP 2 or less, out of 5 minions, 4 should survive.");
		
		for(int i=0;i<minionListener.getField().size();i++)
			if(minionListener.getField().get(i).getCurrentHP() != 7)
				fail("After the damage of HolyNova causes a minion to die, the other minions should be damage correctly.");
	}
	
	@Test(timeout = 30000)
	public void testHolyNovaAffectingOpponentFieldAndKillingTwoMinionOnStart() throws Exception 
	{
		HolyNova holyNova = new HolyNova();
		
		ArrayList<Minion> currentField = new ArrayList<Minion>();
		
		Mage minionListener = new Mage();
		
		Minion minionToDie1 = new Minion("test", 0, null, 1, 2, false, false, true);
		minionListener.getField().add(minionToDie1);
		minionToDie1.setListener(minionListener);
		
		Minion minionToDie2 = new Minion("test", 0, null, 1, 2, false, false, true);
		minionListener.getField().add(minionToDie2);
		minionToDie2.setListener(minionListener);
		
		for(int i=0;i<3;i++)
			minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		holyNova.performAction(minionListener.getField(), currentField);
		
		if(minionListener.getField().contains(minionToDie1)||minionListener.getField().contains(minionToDie2))
			fail("As the two minion at the start of the opponent field are at HP 2, the damage caused by HolyNova should Kill them.");
	
		if(minionListener.getField().size()!= 3)
			fail("As only two minion in this case are at HP 2 or less, out of 5 minions, 3 should survive.");
		
		for(int i=0;i<minionListener.getField().size();i++)
			if(minionListener.getField().get(i).getCurrentHP() != 7)
				fail("After the damage of HolyNova causes some minions to die, the other minions should be damage correctly.");
	}
	
	@Test(timeout = 30000)
	public void testHolyNovaAffectingOpponentFieldAndKillingTwoMinionOnEnd() throws Exception 
	{
		HolyNova holyNova = new HolyNova();
		
		ArrayList<Minion> currentField = new ArrayList<Minion>();
		
		Mage minionListener = new Mage();
		
		for(int i=0;i<3;i++)
			minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		Minion minionToDie1 = new Minion("test", 0, null, 1, 2, false, false, true);
		minionListener.getField().add(minionToDie1);
		minionToDie1.setListener(minionListener);
		
		Minion minionToDie2 = new Minion("test", 0, null, 1, 2, false, false, true);
		minionListener.getField().add(minionToDie2);
		minionToDie2.setListener(minionListener);
		
		holyNova.performAction(minionListener.getField(), currentField);
		
		if(minionListener.getField().contains(minionToDie1)||minionListener.getField().contains(minionToDie2))
			fail("As the two minion at the end of the opponent field are at HP 2, the damage caused by HolyNova should Kill them.");
	
		if(minionListener.getField().size()!= 3)
			fail("As only two minion in this case are at HP 2 or less, out of 5 minions, 3 should survive.");
		
		for(int i=0;i<minionListener.getField().size();i++)
			if(minionListener.getField().get(i).getCurrentHP() != 7)
				fail("After the damage of HolyNova causes some minions to die, the other minions should be damage correctly.");
	}
	
	public void testHolyNovaAffectingOpponentFieldAndKillingTwoMinionOnMiddle() throws Exception 
	{
		HolyNova holyNova = new HolyNova();
		
		ArrayList<Minion> currentField = new ArrayList<Minion>();
		
		Mage minionListener = new Mage();
		
		
		minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		Minion minionToDie1 = new Minion("test", 0, null, 1, 2, false, false, true);
		minionListener.getField().add(minionToDie1);
		minionToDie1.setListener(minionListener);
		
		Minion minionToDie2 = new Minion("test", 0, null, 1, 2, false, false, true);
		minionListener.getField().add(minionToDie2);
		minionToDie2.setListener(minionListener);
		
		minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		holyNova.performAction(minionListener.getField(), currentField);
		
		for(int i=0;i<minionListener.getField().size();i++)
			if(minionListener.getField().get(i).getCurrentHP() != 7)
				fail("After the damage of HolyNova causes some minions to die, the other minions should be damage correctly.");

		if(minionListener.getField().contains(minionToDie1)||minionListener.getField().contains(minionToDie2))
			fail("As the two minion at the middle of the opponent field are at HP 2, the damage caused by HolyNova should Kill them.");
		
		if(minionListener.getField().size()!= 3)
			fail("As only two minion in this case are at HP 2 or less, out of 5 minions, 3 should survive.");
		
		for(int i=0;i<minionListener.getField().size();i++)
			if(minionListener.getField().get(i).getCurrentHP() != 7)
				fail("After the damage of HolyNova causes some minions to die, the other minions should be damage correctly.");
		
		minionListener.getField().clear();
		
		minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		minionToDie1 = new Minion("test", 0, null, 1, 2, false, false, true);
		minionListener.getField().add(minionToDie1);
		minionToDie1.setListener(minionListener);
		
		minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		minionToDie2 = new Minion("test", 0, null, 1, 2, false, false, true);
		minionListener.getField().add(minionToDie2);
		minionToDie2.setListener(minionListener);
		
		minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		holyNova.performAction(minionListener.getField(), currentField);
		
		for(int i=0;i<minionListener.getField().size();i++)
			if(minionListener.getField().get(i).getCurrentHP() != 7)
				fail("After the damage of HolyNova causes some minions to die, the other minions should be damage correctly.");

		if(minionListener.getField().contains(minionToDie1)||minionListener.getField().contains(minionToDie2))
			fail("As the two minion on the opponent field are at HP 2, the damage caused by HolyNova should Kill them.");
	
		if(minionListener.getField().size()!= 3)
			fail("As only two minion in this case are at HP 2 or less, out of 5 minions, 3 should survive.");
		
		for(int i=0;i<minionListener.getField().size();i++)
			if(minionListener.getField().get(i).getCurrentHP() != 7)
				fail("After the damage of HolyNova causes some minions to die, the other minions should be damage correctly.");
	}
	
	@Test(timeout = 3000)
	public void testKillCommandHasPerformActionOnHeroMethod() throws Exception 
	{
		testExistsInClass(KillCommand.class, "performAction", true, void.class, Hero.class);
	}
	
	@Test(timeout = 3000)
	public void testKillCommandAffectingMinion() throws Exception 
	{
		Minion minion = new Minion("test", 0, null, 1, 6, false, false, false);
		
		KillCommand killCommand = new KillCommand();
		
		killCommand.performAction(minion);
		
		assertEquals("KillCommand should hit minions with the right amount.", 1, minion.getCurrentHP());
	}
	
	@Test(timeout = 3000)
	public void testLevelUpHasPerformActionOnMinionMethod() throws Exception 
	{
		testExistsInClass(LevelUp.class, "performAction", true, void.class, ArrayList.class);
	}
	
	@Test(timeout = 3000)
	public void testLevelUpAffectingField() throws Exception 
	{
		ArrayList<Minion> field = new ArrayList<Minion>();
		
		for(int i=0;i<3;i++)
		{
			Minion minion = new Minion("Silver Hand Recruit", 1, null, 1, 1, false, false, false);
			field.add(minion);
		}
		
		field.add(new Minion("test", 1, null, 1, 1, false, false, false));
		
		LevelUp levelUp = new LevelUp();
		
		levelUp.performAction(field);
		
		for(int i=0;i<3;i++)
		{
			assertEquals("LevelUp should affect the attack of Silver Hand Recruit.", 2, field.get(i).getAttack());
			assertEquals("LevelUp should affect the max HP of Silver Hand Recruit.", 2, field.get(i).getMaxHP());
			assertEquals("LevelUp should affect the current HP of Silver Hand Recruit.", 2, field.get(i).getCurrentHP());
		}
		assertEquals("LevelUp should not affect the attack of minions which are not Silver Hand Recruit.", 1, field.get(3).getAttack());
		assertEquals("LevelUp should not affect the max HP of minions which are not Silver Hand Recruit.", 1, field.get(3).getMaxHP());
		assertEquals("LevelUp should not affect the current HP of minions which are not Silver Hand Recruit.", 1, field.get(3).getCurrentHP());
	}
	
	
	
	@Test(timeout = 3000)
	public void testMultiShotHasPerformActionMethod() throws Exception 
	{
		testExistsInClass(MultiShot.class, "performAction", true, void.class, ArrayList.class, ArrayList.class);
	}
	
	
	
	
	@Test(timeout = 3000)
	public void testMultiShotAffectingOpponentFieldWithOneTarget() throws Exception 
	{
		MultiShot multiShot = new MultiShot();
		
		ArrayList<Minion> opponentField = new ArrayList<Minion>();
		ArrayList<Minion> currentField = new ArrayList<Minion>();

		opponentField.add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		multiShot.performAction(opponentField, currentField);
		
		assertEquals("MultiShot should damage the minion with the right amount.",6,opponentField.get(0).getCurrentHP());
	}

	
	@Test(timeout = 3000)
	public void testMultiShotAffectingOpponentFieldWithOneDivineTarget() throws Exception 
	{
		MultiShot multiShot = new MultiShot();
		
		ArrayList<Minion> opponentField = new ArrayList<Minion>();
		ArrayList<Minion> currentField = new ArrayList<Minion>();

		opponentField.add(new Minion("test", 0, null, 1, 9, false, true, true));
		
		multiShot.performAction(opponentField, currentField);
		
		if(opponentField.get(0).isDivine())
			fail("MultiShot should destroy the divine shield of opponent minions.");
		
		if(opponentField.get(0).getCurrentHP() != 9)
			fail("MultiShot should not damage opponent minions with divine shield.");
	}
	
	@Test(timeout = 3000)
	public void testMultiShotAffectingOpponentFieldWithTwoDivineTargets() throws Exception 
	{
		MultiShot multiShot = new MultiShot();
		
		ArrayList<Minion> opponentField = new ArrayList<Minion>();
		ArrayList<Minion> currentField = new ArrayList<Minion>();

		opponentField.add(new Minion("test", 0, null, 1, 9, false, true, true));
		opponentField.add(new Minion("test", 0, null, 1, 9, false, true, true));
		
		multiShot.performAction(opponentField, currentField);
		
		if(opponentField.get(0).isDivine())
			fail("MultiShot should destroy the divine shield of opponent minions.");
		
		if(opponentField.get(0).getCurrentHP() != 9)
			fail("MultiShot should not damage opponent minions with divine shield.");
		
		if(opponentField.get(1).isDivine())
			fail("MultiShot should destroy the divine shield of opponent minions.");
		
		if(opponentField.get(1).getCurrentHP() != 9)
			fail("MultiShot should not damage opponent minions with divine shield.");
	}
	
	
	@Test(timeout = 30000)
	public void testMultiShotAffectingOpponentFieldWithFiveDivineTargets() throws Exception 
	{
		for(int i=0;i<50;i++)
		{
			MultiShot multiShot = new MultiShot();
		
			ArrayList<Minion> opponentField = new ArrayList<Minion>();
			ArrayList<Minion> currentField = new ArrayList<Minion>();

			for(int j=0;j<5;j++)
				opponentField.add(new Minion("test", 0, null, 1, 9, false, true, true));
		
			multiShot.performAction(opponentField, currentField);
			
			int minionsWithNoDivine=0;
			for(int j=0;j<5;j++)
			{
				if(!opponentField.get(j).isDivine())
					minionsWithNoDivine++;
				else if(opponentField.get(j).getCurrentHP() == 6)
					fail("MultiShot should never hit the same target twice.");
			}
			if(minionsWithNoDivine != 2)
				fail("Given more than two minions on field, exactly two of them should be affected.");
		}
		
	}
	
	@Test(timeout = 30000)
	public void testMultiShotAffectingOpponentFieldWithFiveKillableTargets() throws Exception 
	{
		for(int i=0;i<50;i++)
		{
			MultiShot multiShot = new MultiShot();
		
			ArrayList<Minion> currentField = new ArrayList<Minion>();
			Mage minionListener = new Mage();
			for(int j=0;j<5;j++)
			{
				Minion minion = new Minion("test", 0, null, 1, 3, false, false, true);
				minion.setListener(minionListener);
				minionListener.getField().add(minion);
			}
		
			multiShot.performAction(minionListener.getField(), currentField);
			
			assertEquals("In case of a field filled with minions with HP 3, only 2 of them should die.",3, minionListener.getField().size());
		}
		
	}
	
	


	@Test(timeout = 3000)
	public void testPolymorphAffectingMinion() throws Exception 
	{
		Minion minion = new Minion("ToBeTested",5,Rarity.LEGENDARY,5,10,true,true,true);
		Polymorph polymorph = new Polymorph();
		polymorph.performAction(minion);
		
		assertEquals("Polymorph should set the attack of the minion properly.",1, minion.getAttack());
		assertEquals("Polymorph should set the max HP of the minion properly.",1, minion.getMaxHP());
		assertEquals("Polymorph should set the current HP of the minion properly.",1, minion.getCurrentHP());
		assertEquals("Polymorph should set the name of the minion properly.","Sheep", minion.getName());
		assertEquals("Polymorph should set the mana cost of the minion properly.",1, minion.getManaCost());
		assertEquals("Polymorph should set the taunt of the minion properly.",false, minion.isTaunt());
		assertEquals("Polymorph should set the divine of the minion properly.",false, minion.isDivine());
		assertEquals("Polymorph should set the sleeping of the minion properly.",true, minion.isSleeping());
	}
	
	
	
	@Test(timeout = 3000)
	public void testPyroblastHasPerformActionOnMinionMethod() throws Exception 
	{
		testExistsInClass(Pyroblast.class, "performAction", true, void.class, Minion.class);
	}
	
	
	@Test(timeout = 3000)
	public void testPyroblastAffectingHero() throws Exception 
	{
		Mage hero = new Mage();
		
		Pyroblast pyroblast = new Pyroblast();
		
		pyroblast.performAction(hero);
		
		assertEquals("Pyroblast should hit heros with the right amount.", 20, hero.getCurrentHP());
	}
	
	@Test(timeout = 3000)
	public void testPyroblastKillingHero() throws Exception 
	{
		Mage hero = new Mage();
		hero.setCurrentHP(10);
		
		HeroListener heroListener = new HeroListener() {
			
			@Override
			public void onHeroDeath() {
				callParameters.add(true);
			}
			
			@Override
			public void endTurn() throws FullHandException, CloneNotSupportedException {
			}
			
			@Override
			public void damageOpponent(int amount) {
			}
		};
		hero.setListener(heroListener);
		
		Pyroblast pyroblast = new Pyroblast();
		
		pyroblast.performAction(hero);

		if(callParameters.size() != 1)
			fail("If Pyroblast resulted on killing a hero, they should announce thier death.");
	}
	
	
	
	
	@Test(timeout = 3000)
	public void testPyroblastAffectingMinionWithDivine() throws Exception 
	{
		Minion minion = new Minion("test", 0, null, 1, 11, false, true, false);
		MinionListener listener = new MinionListener() {
			@Override
			public void onMinionDeath(Minion m) {
			}
		};
		minion.setListener(listener);
		Pyroblast pyroblast = new Pyroblast();
		pyroblast.performAction(minion);
		
		assertEquals("Pyroblast should destroy thedivine shield of the minion.", false, minion.isDivine());
		
		assertEquals("Pyroblast should not hit minions with divine shield.", 11, minion.getCurrentHP());
	}
	
	
	
	
	
	@Test(timeout = 3000)
	public void testSealOfChampionsHasPerformActionOnMinionMethod() throws Exception 
	{
		testExistsInClass(SealOfChampions.class, "performAction", true, void.class, Minion.class);
	}
	
	@Test(timeout = 3000)
	public void testSealOfChampionsAffectingMinionAttack() throws Exception 
	{
		Minion minion = new Minion("test", 0, null, 0, 1, false, false, false);
		SealOfChampions sealOfChampions = new SealOfChampions();
		sealOfChampions.performAction(minion);
		assertEquals("SealOfChampions should affect the minion attack properly.", 3, minion.getAttack());
	}
	
	@Test(timeout = 3000)
	public void testSealOfChampionsAffectingMinionDivine() throws Exception 
	{
		Minion minion = new Minion("test", 0, null, 0, 1, false, false, false);
		SealOfChampions sealOfChampions = new SealOfChampions();
		
		sealOfChampions.performAction(minion);
		
		assertEquals("SealOfChampions should affect the minion divine properly.", true, minion.isDivine());
		
		minion = new Minion("test", 0, null, 0, 1, false, true, false);
		
		sealOfChampions.performAction(minion);
		
		assertEquals("SealOfChampions should not toggle the minion divine.", true, minion.isDivine());
	}
	
	
	
	@Test(timeout = 3000)
	public void testShadowWordDeathHasPerformActionOnMinionMethod() throws Exception 
	{
		testExistsInClass(SealOfChampions.class, "performAction", true, void.class, Minion.class);
	}
	@Test(timeout = 3000)
	public void testShadowWordDeathKillingMinion() throws Exception 
	{
		Minion minion = new Minion("test", 0, null, 5, 1, false, false, false);
		
		MinionListener minionListener = new MinionListener() {
			
			@Override
			public void onMinionDeath(Minion m) {
				callParameters.add(m);
			}
		};
		
		minion.setListener(minionListener);
		
		ShadowWordDeath shadowWordDeath = new ShadowWordDeath();
		
		shadowWordDeath.performAction(minion);

		if(callParameters.size()!=1||callParameters.get(0)!=minion)
			fail("ShadowWordDeath being cast on a minion with 5 or more attack should result in killing them.");
		
		reset();
		
		minion.setDivine(true);
		
		shadowWordDeath.performAction(minion);

		if(callParameters.size()!=1||callParameters.get(0)!=minion)
			fail("ShadowWordDeath being cast on a divine minion with 5 or more attack should result in killing them.");
	}
	
	
	
	@Test(timeout = 3000)
	public void testSiphonSoulHasPerformActionOnMinionMethod() throws Exception 
	{
		testExistsInClass(SiphonSoul.class, "performAction", true, int.class, Minion.class);
	}
	
	
	
	
	@Test(timeout = 3000)
	public void testSiphonSoulKillingMinionDivine() throws Exception 
	{
		Minion minion = new Minion("test", 0, null, 1, 5, false, true, false);
		
		MinionListener minionListener = new MinionListener() {
			
			@Override
			public void onMinionDeath(Minion m) {
				callParameters.add(m);
			}
		};
		
		minion.setListener(minionListener);
		
		SiphonSoul siphonSoul = new SiphonSoul();
		
		siphonSoul.performAction(minion);
		if(callParameters.size() != 1 || callParameters.get(0) != minion)
			fail("The minion targeted with SiphonSoul should announce its death even if divine.");
	}

	
	
	
	@Test(timeout = 3000)
	public void testTwistingNetherHasPerformActionOnMinionMethod() throws Exception 
	{
		testExistsInClass(TwistingNether.class, "performAction", true, void.class, ArrayList.class, ArrayList.class);
	}
	
	@Test(timeout = 3000)
	public void testTwistingNetherEffect() throws Exception 
	{
		TwistingNether twistingNether = new TwistingNether();
		
		Mage hero1 = new Mage();
		Mage hero2 = new Mage();
		
		int opponentFieldSize = 3;
		int currentFieldSize = 4;
		
		for(int i=0;i<opponentFieldSize;i++)
		{
			Minion minion = new Minion("test", 2, null, 2, 2, false, false, false);
			minion.setListener(hero1);
			hero1.getField().add(minion);
		}
			
		for(int i=0;i<currentFieldSize;i++)
		{
			Minion minion = new Minion("test", 2, null, 2, 2, false, false, false);
			minion.setListener(hero2);
			hero2.getField().add(minion);
		}
		
		
		
		twistingNether.performAction(hero1.getField(), hero2.getField());
		assertTrue("TwistingNether sould kill all minions on both fields.", hero1.getField().size()+hero2.getField().size()==0);
	}
	
	
	@Test(timeout = 3000)
	public void testHeroHasCastSpellWithMinionTargetSpellMinion() throws Exception 
	{
		testExistsInClass(Hero.class, "castSpell", true, void.class, MinionTargetSpell.class, Minion.class);
	}

	@Test(timeout = 3000)
	public void testHeroCastSpellWithMinionTargetSpellMinionCallsValidateTurn() throws Exception 
	{
		Hero hero = new Hero("testHero") 
		{
			@Override
			public void buildDeck() throws IOException 
			{
			}
		};
		ActionValidator validator = new ActionValidator() {
			
			@Override
			public void validateUsingHeroPower(Hero h) throws NotEnoughManaException,
					HeroPowerAlreadyUsedException {
				
			}
			
			@Override
			public void validateTurn(Hero user) throws NotYourTurnException {
				callParameters.add(user);
			}
			
			@Override
			public void validatePlayingMinion(Minion m) throws FullFieldException {
				
			}
			
			@Override
			public void validateManaCost(Card card) throws NotEnoughManaException {
				
			}
			
			@Override
			public void validateAttack(Minion m, Hero t) throws TauntBypassException,
					NotSummonedException, InvalidTargetException, CannotAttackException {
				
			}
			
			@Override
			public void validateAttack(Minion a, Minion t) throws TauntBypassException,
					InvalidTargetException, NotSummonedException, CannotAttackException {
				
			}
		};
		reset();
		hero.setValidator(validator);
		class TestCard extends Spell implements MinionTargetSpell
		{
			public TestCard() 
			{
				super("test", 0, null);
			}

			@Override
			public void performAction(Minion m) throws InvalidTargetException 
			{
				
			}
			
		}
		TestCard minionTargetSpell = new TestCard();
		hero.getHand().add(minionTargetSpell);
		hero.getField().clear();
		hero.castSpell(minionTargetSpell, new Icehowl());
		if(callParameters.isEmpty()||!callParameters.contains(hero))
			fail("The method castSpell(MinionTargetSpell, Minion) in class Hero should validate the turn before casting the spell.");
	}

	@Test(timeout = 3000)
	public void testHeroCastSpellWithMinionTargetSpellMinionValidatesManaCost() throws Exception 
	{
		Hero hero = new Hero("testHero") 
		{
			@Override
			public void buildDeck() throws IOException 
			{
			}
		};
		ActionValidator validator = new ActionValidator() {
			
			@Override
			public void validateUsingHeroPower(Hero h) throws NotEnoughManaException,
					HeroPowerAlreadyUsedException {
				
			}
			
			@Override
			public void validateTurn(Hero user) throws NotYourTurnException {
				
			}
			
			@Override
			public void validatePlayingMinion(Minion m) throws FullFieldException {
				
			}
			
			@Override
			public void validateManaCost(Card card) throws NotEnoughManaException {
				callParameters.add(card);
			}
			
			@Override
			public void validateAttack(Minion m, Hero t) throws TauntBypassException,
					NotSummonedException, InvalidTargetException, CannotAttackException {
				
			}
			
			@Override
			public void validateAttack(Minion a, Minion t) throws TauntBypassException,
					InvalidTargetException, NotSummonedException, CannotAttackException {
				
			}
		};
		reset();
		hero.setValidator(validator);
		class TestCard extends Spell implements MinionTargetSpell
		{
			public TestCard() 
			{
				super("test", 11, null);
			}

			@Override
			public void performAction(Minion m) 
			{
			}
			
		}
		TestCard minionTargetSpell = new TestCard();
		hero.getHand().add(minionTargetSpell);
		hero.getField().clear();
		hero.castSpell(minionTargetSpell, new Icehowl());
		if(callParameters.size()!=1||callParameters.get(0)!=minionTargetSpell)
			fail("The method castSpell(MinionTargetSpell, Minion) in class Hero should validate the mana cost before casting the spell.");
	}


	@Test(timeout = 3000)
	public void testHeroCastSpellWithMinionTargetSpellMinionWorksNormally() throws Exception 
	{
		Hero hero = new Hero("testHero") 
		{
			@Override
			public void buildDeck() throws IOException 
			{
			}
		};
		ActionValidator validator = new ActionValidator() {
			
			@Override
			public void validateUsingHeroPower(Hero h) throws NotEnoughManaException,
					HeroPowerAlreadyUsedException {
				
			}
			
			@Override
			public void validateTurn(Hero user) throws NotYourTurnException {
				
			}
			
			@Override
			public void validatePlayingMinion(Minion m) throws FullFieldException {
				
			}
			
			@Override
			public void validateManaCost(Card card) throws NotEnoughManaException {
				
			}
			
			@Override
			public void validateAttack(Minion m, Hero t) throws TauntBypassException,
					NotSummonedException, InvalidTargetException, CannotAttackException {
				
			}
			
			@Override
			public void validateAttack(Minion a, Minion t) throws TauntBypassException,
					InvalidTargetException, NotSummonedException, CannotAttackException {
				
			}
		};
		reset();
		hero.setValidator(validator);
		class TestCard extends Spell implements MinionTargetSpell
		{
			public TestCard() 
			{
				super("test", 3, null);
			}

			@Override
			public void performAction(Minion m) 
			{
				callParameters.add(m);
			}
			
		}
		TestCard minionTargetSpellPlayed = new TestCard();
		TestCard minionTargetSpellNotPlayed = new TestCard();
		hero.getHand().add(minionTargetSpellPlayed);
		hero.getHand().add(minionTargetSpellNotPlayed);
		hero.setCurrentManaCrystals(5);
		Icehowl minion = new Icehowl();
		hero.castSpell(minionTargetSpellPlayed, minion);
		if(hero.getCurrentManaCrystals() == 5)
			fail("The method castSpell(MinionTargetSpell, Minion) in class Hero should update the currentManaCrystals of the hero after playing the spell.");
		if(hero.getCurrentManaCrystals() != 2)
		{
			fail("The method castSpell(MinionTargetSpell, Minion) in class Hero should update the currentManaCrystals of the hero correctly with the minion cost after playing the spell.");
		}
		if(callParameters.size() != 1 || callParameters.get(0)!=minion)
			fail("The method castSpell(MinionTargetSpell, Minion) in class Hero should call performAction on the right target.");
		if(hero.getHand().size() == 2 || hero.getHand().contains(minionTargetSpellPlayed))
		{
			fail("The method castSpell(MinionTargetSpell, Minion) in class Hero should remove the spell from the hero's hand after playing it.");
		}
		if(hero.getHand().size() == 2 || !(hero.getHand().contains(minionTargetSpellNotPlayed)))
		{
			fail("The method castSpell(MinionTargetSpell, Minion) in class Hero should not remove the spell from the hero's hand if not played.");
		}
	}
	
	@Test(timeout = 3000)
	public void testHeroHasCastSpellWithLeechingSpellMinion() throws Exception 
	{
		testExistsInClass(Hero.class, "castSpell", true, void.class, LeechingSpell.class, Minion.class);
	}

	@Test(timeout = 3000)
	public void testHeroCastSpellWithLeechingSpellMinionCallsValidateTurn() throws Exception 
	{
		Hero hero = new Hero("testHero") 
		{
			@Override
			public void buildDeck() throws IOException 
			{
			}
		};
		ActionValidator validator = new ActionValidator() {
			
			@Override
			public void validateUsingHeroPower(Hero h) throws NotEnoughManaException,
					HeroPowerAlreadyUsedException {
				
			}
			
			@Override
			public void validateTurn(Hero user) throws NotYourTurnException {
				callParameters.add(user);
			}
			
			@Override
			public void validatePlayingMinion(Minion m) throws FullFieldException {
				
			}
			
			@Override
			public void validateManaCost(Card card) throws NotEnoughManaException {
				
			}
			
			@Override
			public void validateAttack(Minion m, Hero t) throws TauntBypassException,
					NotSummonedException, InvalidTargetException, CannotAttackException {
				
			}
			
			@Override
			public void validateAttack(Minion a, Minion t) throws TauntBypassException,
					InvalidTargetException, NotSummonedException, CannotAttackException {
				
			}
		};
		reset();
		hero.setValidator(validator);
		class TestCard extends Spell implements LeechingSpell
		{
			public TestCard() 
			{
				super("test", 0, null);
			}

			@Override
			public int performAction(Minion m) 
			{
				return 0;
			}
			
		}
		TestCard leechingSpell = new TestCard();
		hero.getHand().add(leechingSpell);
		hero.getField().clear();
		hero.castSpell(leechingSpell, new Icehowl());
		if(callParameters.isEmpty()||!callParameters.contains(hero))
			fail("The method castSpell(LeechingSpell, Minion) in class Hero should validate the turn before casting the spell.");
		reset();
	}

	@Test(timeout = 3000)
	public void testHeroCastSpellWithLeechingSpellMinionThrowsNotEnoughManaCrystals() throws Exception 
	{
		Hero hero = new Hero("testHero") 
		{
			@Override
			public void buildDeck() throws IOException 
			{
			}
		};
		ActionValidator validator = new ActionValidator() {
			
			@Override
			public void validateUsingHeroPower(Hero h) throws NotEnoughManaException,
					HeroPowerAlreadyUsedException {
				
			}
			
			@Override
			public void validateTurn(Hero user) throws NotYourTurnException {
				
			}
			
			@Override
			public void validatePlayingMinion(Minion m) throws FullFieldException {
				
			}
			
			@Override
			public void validateManaCost(Card card) throws NotEnoughManaException {
				callParameters.add(card);
				
			}
			
			@Override
			public void validateAttack(Minion m, Hero t) throws TauntBypassException,
					NotSummonedException, InvalidTargetException, CannotAttackException {
				
			}
			
			@Override
			public void validateAttack(Minion a, Minion t) throws TauntBypassException,
					InvalidTargetException, NotSummonedException, CannotAttackException {
				
			}
		};
		reset();
		hero.setValidator(validator);
		class TestCard extends Spell implements LeechingSpell
		{
			public TestCard() 
			{
				super("test", 11, null);
			}

			@Override
			public int performAction(Minion m) 
			{
				return 0;
			}
			
		}
		TestCard leechingSpell = new TestCard();
		hero.getHand().add(leechingSpell);
		hero.getField().clear();
		Icehowl minion = new Icehowl();
		minion.setListener(new MinionListener() {
			
			@Override
			public void onMinionDeath(Minion m) {
				
			}
		});
		hero.castSpell(leechingSpell, minion);
		if(callParameters.size()!=1||callParameters.get(0)!=leechingSpell)
			fail("The method castSpell(LeechingSpell, Minion) in class Hero should validate the mana cost before cast.");
	}


	@Test(timeout = 3000)
	public void testHeroCastSpellWithLeechingSpellMinionWorksNormally() throws Exception 
	{
		Hero hero = new Hero("testHero") 
		{
			@Override
			public void buildDeck() throws IOException 
			{
			}
		};
		ActionValidator validator = new ActionValidator() {
			
			@Override
			public void validateUsingHeroPower(Hero h) throws NotEnoughManaException,
					HeroPowerAlreadyUsedException {
				
			}
			
			@Override
			public void validateTurn(Hero user) throws NotYourTurnException {
				
			}
			
			@Override
			public void validatePlayingMinion(Minion m) throws FullFieldException {
				
			}
			
			@Override
			public void validateManaCost(Card card) throws NotEnoughManaException {
				
			}
			
			@Override
			public void validateAttack(Minion m, Hero t) throws TauntBypassException,
					NotSummonedException, InvalidTargetException, CannotAttackException {
				
			}
			
			@Override
			public void validateAttack(Minion a, Minion t) throws TauntBypassException,
					InvalidTargetException, NotSummonedException, CannotAttackException {
				
			}
		};
		reset();
		hero.setValidator(validator);
		class TestCard extends Spell implements LeechingSpell
		{
			public TestCard() 
			{
				super("test", 3, null);
			}

			@Override
			public int performAction(Minion m) 
			{
				callParameters.add(m);
				return 0;
			}
			
		}
		TestCard leechingSpellPlayed = new TestCard();
		TestCard leechingSpellNotPlayed = new TestCard();
		hero.getHand().add(leechingSpellPlayed);
		hero.getHand().add(leechingSpellNotPlayed);
		hero.setCurrentManaCrystals(5);
		Icehowl minion = new Icehowl();
		hero.castSpell(leechingSpellPlayed, minion);

		if(hero.getCurrentManaCrystals() == 5)
			fail("The method castSpell(LeechingSpell, Minion) in class Hero should update the currentManaCrystals of the hero after playing the spell.");
		if(hero.getCurrentManaCrystals() != 2)
		{
			fail("The method castSpell(LeechingSpell, Minion) in class Hero should update the currentManaCrystals of the hero correctly with the minion cost after playing the spell.");
		}
		if(callParameters.size() != 1 || callParameters.get(0)!=minion)
			fail("The method castSpell(LeechingSpell, Minion) in class Hero should call performAction on the right target.");
		if(hero.getHand().size() == 2 || hero.getHand().contains(leechingSpellPlayed))
		{
			fail("The method castSpell(LeechingSpell, Minion) in class Hero should remove the spell from the hero's hand after playing it.");
		}
		if(hero.getHand().size() == 2 || !(hero.getHand().contains(leechingSpellNotPlayed)))
		{
			fail("The method castSpell(LeechingSpell, Minion) in class Hero should not remove the spell from the hero's hand if not played.");
		}
	}
	
	
	@Test(timeout = 3000)
	public void testHeroHasCastSpellAOESpell() throws Exception 
	{
		testExistsInClass(Hero.class, "castSpell", true, void.class, AOESpell.class,ArrayList.class);
	}

	@Test(timeout = 3000)
	public void testHeroCastSpellWithAOESpellCallsValidateTurn() throws Exception 
	{
		Hero hero = new Hero("testHero") 
		{
			@Override
			public void buildDeck() throws IOException 
			{
			}
		};
		ActionValidator validator = new ActionValidator() {
			
			@Override
			public void validateUsingHeroPower(Hero h) throws NotEnoughManaException,
					HeroPowerAlreadyUsedException {
				
			}
			
			@Override
			public void validateTurn(Hero user) throws NotYourTurnException {
				callParameters.add(user);
			}
			
			@Override
			public void validatePlayingMinion(Minion m) throws FullFieldException {
				
			}
			
			@Override
			public void validateManaCost(Card card) throws NotEnoughManaException {
				
			}
			
			@Override
			public void validateAttack(Minion m, Hero t) throws TauntBypassException,
					NotSummonedException, InvalidTargetException, CannotAttackException {
				
			}
			
			@Override
			public void validateAttack(Minion a, Minion t) throws TauntBypassException,
					InvalidTargetException, NotSummonedException, CannotAttackException {
				
			}
		};
		reset();
		hero.setValidator(validator);
		class TestCard extends Spell implements AOESpell
		{
			public TestCard() 
			{
				super("test", 0, null);
			}

			@Override
			public void performAction(ArrayList<Minion> field1, ArrayList<Minion> field2) 
			{
			}
			
		}
		TestCard aOESpell = new TestCard();
		hero.getHand().add(aOESpell);
		hero.getField().clear();
		hero.castSpell(aOESpell, new ArrayList<Minion>());
		if(callParameters.isEmpty()||!callParameters.contains(hero))
			fail("The method castSpell(AOESpell) in class Hero should validate the mana cost before cast.");

	}

	@Test(timeout = 3000)
	public void testHeroCastSpellWithAOESpellValidtesManaCost() throws Exception 
	{
		Hero hero = new Hero("testHero") 
		{
			@Override
			public void buildDeck() throws IOException 
			{
			}
		};
		ActionValidator validator = new ActionValidator() {
			
			@Override
			public void validateUsingHeroPower(Hero h) throws NotEnoughManaException,
					HeroPowerAlreadyUsedException {
				
			}
			
			@Override
			public void validateTurn(Hero user) throws NotYourTurnException {
				
			}
			
			@Override
			public void validatePlayingMinion(Minion m) throws FullFieldException {
				
			}
			
			@Override
			public void validateManaCost(Card card) throws NotEnoughManaException {
				callParameters.add(card);
			}
			
			@Override
			public void validateAttack(Minion m, Hero t) throws TauntBypassException,
					NotSummonedException, InvalidTargetException, CannotAttackException {
				
			}
			
			@Override
			public void validateAttack(Minion a, Minion t) throws TauntBypassException,
					InvalidTargetException, NotSummonedException, CannotAttackException {
				
			}
		};
		reset();
		hero.setValidator(validator);
		class TestCard extends Spell implements AOESpell
		{
			public TestCard() 
			{
				super("test", 11, null);
			}

			@Override
			public void performAction(ArrayList<Minion> field1, ArrayList<Minion> field2) 
			{
			}
			
		}
		TestCard aOESpell = new TestCard();
		hero.getHand().add(aOESpell);
		hero.getField().clear();
		hero.castSpell(aOESpell, new ArrayList<Minion>());
		if(callParameters.size()!=1||callParameters.get(0)!=aOESpell)
			fail("The method castSpell(AOESpell) in class Hero should throw NotEnoughManaException if the hero does not have enough mana crystals to play a spell.");
	}


	@Test(timeout = 3000)
	public void testHeroCastSpellWithAOESpellWorksNormally() throws Exception 
	{
		Hero hero = new Hero("testHero") 
		{
			@Override
			public void buildDeck() throws IOException 
			{
			}
		};
		ActionValidator validator = new ActionValidator() {
			
			@Override
			public void validateUsingHeroPower(Hero h) throws NotEnoughManaException,
					HeroPowerAlreadyUsedException {
				
			}
			
			@Override
			public void validateTurn(Hero user) throws NotYourTurnException {
				
			}
			
			@Override
			public void validatePlayingMinion(Minion m) throws FullFieldException {
				
			}
			
			@Override
			public void validateManaCost(Card card) throws NotEnoughManaException {
				
			}
			
			@Override
			public void validateAttack(Minion m, Hero t) throws TauntBypassException,
					NotSummonedException, InvalidTargetException, CannotAttackException {
				
			}
			
			@Override
			public void validateAttack(Minion a, Minion t) throws TauntBypassException,
					InvalidTargetException, NotSummonedException, CannotAttackException {
				
			}
		};
		reset();
		hero.setValidator(validator);
		class TestCard extends Spell implements AOESpell
		{
			public TestCard() 
			{
				super("test", 3, null);
			}

			@Override
			public void performAction(ArrayList<Minion> field1, ArrayList<Minion> field2) 
			{
				callParameters.add(field1);
				callParameters.add(field2);
			}
			
		}
		TestCard aOESpellPlayed = new TestCard();
		TestCard fieldSpellNotPlayed = new TestCard();
		hero.getHand().add(aOESpellPlayed);
		hero.getHand().add(fieldSpellNotPlayed);
		hero.setCurrentManaCrystals(5);
		ArrayList<Minion> opponentFeild = new ArrayList<Minion>();
		hero.castSpell(aOESpellPlayed, opponentFeild);
		if(hero.getCurrentManaCrystals() == 5)
			fail("The method castSpell(AOESpell) in class Hero should update the currentManaCrystals of the hero after playing the spell.");
		if(hero.getCurrentManaCrystals() != 2)
		{
			fail("The method castSpell(AOESpell) in class Hero should update the currentManaCrystals of the hero correctly with the minion cost after playing the spell.");
		}
		if(hero.getHand().size() == 2 || hero.getHand().contains(aOESpellPlayed))
		{
			fail("The method castSpell(AOESpell) in class Hero should remove the spell from the hero's hand after playing it.");
		}
		if(hero.getHand().size() == 2 || !(hero.getHand().contains(fieldSpellNotPlayed)))
		{
			fail("The method castSpell(AOESpell) in class Hero should not remove the spell from the hero's hand if not played.");
		}
		if(callParameters.get(0)!=opponentFeild||callParameters.get(1)!=hero.getField())
			fail("The method castSpell(AOESpell) should perform its action on the right targets.");
	}
	
	@Test(timeout = 3000)
	public void testHeroUseHeroPowerValidatesUsingHeroPower() throws Exception 
	{
		Hero hero = new Hero("testHero") 
		{
			@Override
			public void buildDeck() throws IOException 
			{
			}
		};
		ActionValidator validator = new ActionValidator() {
			
			@Override
			public void validateUsingHeroPower(Hero h) throws NotEnoughManaException,
					HeroPowerAlreadyUsedException {
				callParameters.add(h);
			}
			
			@Override
			public void validateTurn(Hero user) throws NotYourTurnException {
				
			}
			
			@Override
			public void validatePlayingMinion(Minion m) throws FullFieldException {
				
			}
			
			@Override
			public void validateManaCost(Card card) throws NotEnoughManaException {
				
			}
			
			@Override
			public void validateAttack(Minion m, Hero t) throws TauntBypassException,
					NotSummonedException, InvalidTargetException, CannotAttackException {
				
			}
			
			@Override
			public void validateAttack(Minion a, Minion t) throws TauntBypassException,
					InvalidTargetException, NotSummonedException, CannotAttackException {
				
			}
		};
		reset();
		hero.setValidator(validator);
		hero.useHeroPower();
		if(callParameters.size()!=1||callParameters.get(0)!=hero)
			fail("The method useHeroPower in class Hero should validate before use.");
	}
	

	
	@Test(timeout = 3000)
	public void testHeroCastLeechingSpellHeals() throws Exception 
	{
		Hero hero = new Hero("test") {
			@Override
			public void buildDeck() throws IOException, CloneNotSupportedException {
			}
		};
		
		ActionValidator actionValidator = new ActionValidator() {
			
			@Override
			public void validateUsingHeroPower(Hero h) throws NotEnoughManaException,
					HeroPowerAlreadyUsedException {
				
			}
			
			@Override
			public void validateTurn(Hero user) throws NotYourTurnException {
				
			}
			
			@Override
			public void validatePlayingMinion(Minion m) throws FullFieldException {
				
			}
			
			@Override
			public void validateManaCost(Card card) throws NotEnoughManaException {
				
			}
			
			@Override
			public void validateAttack(Minion m, Hero t) throws TauntBypassException,
					NotSummonedException, InvalidTargetException, CannotAttackException {
				
			}
			
			@Override
			public void validateAttack(Minion a, Minion t) throws TauntBypassException,
					InvalidTargetException, NotSummonedException, CannotAttackException {
				
			}
		};
		
		hero.setValidator(actionValidator);
		
		class TestSpell extends Spell implements LeechingSpell
		{

			public TestSpell() {
				super("test", 0, null);
			}

			@Override
			public int performAction(Minion m) {
				return 5;
			}
			
		}
		
		TestSpell leechingSpell = new TestSpell();
		
		hero.setCurrentHP(2);
		
		
		hero.castSpell(leechingSpell, new Icehowl());
		
		if(hero.getCurrentHP()==2)
			fail("Casting leeching spells should result in healing the hero in the process.");
		if(hero.getCurrentHP()!=7)
			fail("Casting leeching spells should result in healing the hero with the right amount.");
		
	}
	
	

	@Test(timeout = 3000)
	public void testMageHasUseHeroPowerMinion() throws Exception 
	{
		testExistsInClass(Mage.class, "useHeroPower", true, void.class, Minion.class);
	}

	@Test(timeout = 3000)
	public void testMageHasUseHeroPowerHero() throws Exception 
	{
		testExistsInClass(Mage.class, "useHeroPower", true, void.class, Hero.class);
	}
	
	@Test(timeout = 3000)
	public void testMageCallsSuperUseHeroPower() throws Exception 
	{
		
		ActionValidator actionValidator = new ActionValidator() {
			
			@Override
			public void validateUsingHeroPower(Hero h) throws NotEnoughManaException,
					HeroPowerAlreadyUsedException {
				throw new HeroPowerAlreadyUsedException();
			}
			
			@Override
			public void validateTurn(Hero user) throws NotYourTurnException {
				
			}
			
			@Override
			public void validatePlayingMinion(Minion m) throws FullFieldException {
				
			}
			
			@Override
			public void validateManaCost(Card card) throws NotEnoughManaException {
				
			}
			
			@Override
			public void validateAttack(Minion m, Hero t) throws TauntBypassException,
					NotSummonedException, InvalidTargetException, CannotAttackException {
				
			}
			
			@Override
			public void validateAttack(Minion a, Minion t) throws TauntBypassException,
					InvalidTargetException, NotSummonedException, CannotAttackException {
				
			}
		};
		
		Mage mage = new Mage();
		mage.setValidator(actionValidator);
		try
		{
			mage.useHeroPower(new Icehowl());
			fail("UseHeroPower in class Mage targeting should call useHeroPower in the super class to validate action.");
		}
		catch(HeroPowerAlreadyUsedException e)
		{
			StackTraceElement [] stackTraceElement = e.getStackTrace();
			boolean found = false;
			for(int i=0;i<stackTraceElement.length&&!found;i++)
				if(stackTraceElement[i].getClassName().endsWith("Hero") && stackTraceElement[i].getMethodName().equals("useHeroPower"))
					found = true;
			if(!found)
				fail("UseHeroPower in class Mage targeting a minion should call useHeroPower in the super class to validate using it.");
		}
		
		try
		{
			mage.useHeroPower(new Mage());
			fail("UseHeroPower in class Mage targeting a hero should call useHeroPower in the super class to validate action.");
		}
		catch(HeroPowerAlreadyUsedException e)
		{
			StackTraceElement [] stackTraceElement = e.getStackTrace();
			boolean found = false;
			for(int i=0;i<stackTraceElement.length&&!found;i++)
				if(stackTraceElement[i].getClassName().endsWith("Hero") && stackTraceElement[i].getMethodName().equals("useHeroPower"))
					found = true;
			if(!found)
				fail("UseHeroPower in class Mage targeting a hero should call useHeroPower in the super class to validate using it.");
		}
		
		actionValidator = new ActionValidator() {
			
			@Override
			public void validateUsingHeroPower(Hero h) throws NotEnoughManaException,
					HeroPowerAlreadyUsedException {
			}
			
			@Override
			public void validateTurn(Hero user) throws NotYourTurnException {
				throw new NotYourTurnException();
			}
			
			@Override
			public void validatePlayingMinion(Minion m) throws FullFieldException {
				
			}
			
			@Override
			public void validateManaCost(Card card) throws NotEnoughManaException {
				
			}
			
			@Override
			public void validateAttack(Minion m, Hero t) throws TauntBypassException,
					NotSummonedException, InvalidTargetException, CannotAttackException {
				
			}
			
			@Override
			public void validateAttack(Minion a, Minion t) throws TauntBypassException,
					InvalidTargetException, NotSummonedException, CannotAttackException {
				
			}
		};
		
		try
		{
			mage.useHeroPower(new Icehowl());
			fail("UseHeroPower in class Mage targeting a minion should call useHeroPower in the super class to validate action.");
		}
		catch(HeroPowerAlreadyUsedException e)
		{
			StackTraceElement [] stackTraceElement = e.getStackTrace();
			boolean found = false;
			for(int i=0;i<stackTraceElement.length&&!found;i++)
				if(stackTraceElement[i].getClassName().endsWith("Hero") && stackTraceElement[i].getMethodName().equals("useHeroPower"))
					found = true;
			if(!found)
				fail("UseHeroPower in class Mage targeting a minion should call useHeroPower in the super class to validate the turn.");
		}
		
		try
		{
			mage.useHeroPower(new Mage());
			fail("UseHeroPower in class Mage targeting a hero should call useHeroPower in the super class to validate action.");
		}
		catch(HeroPowerAlreadyUsedException e)
		{
			StackTraceElement [] stackTraceElement = e.getStackTrace();
			boolean found = false;
			for(int i=0;i<stackTraceElement.length&&!found;i++)
				if(stackTraceElement[i].getClassName().endsWith("Hero") && stackTraceElement[i].getMethodName().equals("useHeroPower"))
					found = true;
			if(!found)
				fail("UseHeroPower in class Mage targeting a hero should call useHeroPower in the super class to validate the turn.");
		}
	}

	@Test(timeout = 3000)
	public void testMageUseHeroPowerCallsDamageMinion() throws Exception 
	{
		Mage mage = new Mage();
		ActionValidator validator = new ActionValidator() {
			
			@Override
			public void validateUsingHeroPower(Hero h) throws NotEnoughManaException,
					HeroPowerAlreadyUsedException {
				
			}
			
			@Override
			public void validateTurn(Hero user) throws NotYourTurnException {
				
			}
			
			@Override
			public void validatePlayingMinion(Minion m) throws FullFieldException {
				
			}
			
			@Override
			public void validateManaCost(Card card) throws NotEnoughManaException {
				
			}
			
			@Override
			public void validateAttack(Minion m, Hero t) throws TauntBypassException,
					NotSummonedException, InvalidTargetException, CannotAttackException {
				
			}
			
			@Override
			public void validateAttack(Minion a, Minion t) throws TauntBypassException,
					InvalidTargetException, NotSummonedException, CannotAttackException {
				
			}
		};
		Minion minion = new Minion("test", 0, null, 1, 3, false, false, false);
		mage.setCurrentManaCrystals(2);
		mage.setValidator(validator);
		mage.useHeroPower(minion);

		if(minion.getCurrentHP()!=2)
			fail("useHeroPower in class Mage should damage the target minion with the proper amount.");

		minion = new Minion("test", 0, null, 1, 3, false, true, false);
		mage.setCurrentManaCrystals(2);
		mage.setHeroPowerUsed(false);
		mage.useHeroPower(minion);

		if(minion.isDivine())
			fail("useHeroPower in class Mage should remove divine form divine minions.");

		if(minion.getCurrentHP()!=3)
			fail("useHeroPower in class Mage should not damage divine minions.");

	}

	@Test(timeout = 3000)
	public void testPaladinHasUseHeroPower() throws Exception 
	{
		testExistsInClass(Paladin.class, "useHeroPower", true, void.class);
	}
	
	@Test(timeout = 3000)
	public void testPaladinCallsSuperUseHeroPower() throws Exception 
	{
		
		ActionValidator actionValidator = new ActionValidator() {
			
			@Override
			public void validateUsingHeroPower(Hero h) throws NotEnoughManaException,
					HeroPowerAlreadyUsedException {
				throw new HeroPowerAlreadyUsedException();
			}
			
			@Override
			public void validateTurn(Hero user) throws NotYourTurnException {
				
			}
			
			@Override
			public void validatePlayingMinion(Minion m) throws FullFieldException {
				
			}
			
			@Override
			public void validateManaCost(Card card) throws NotEnoughManaException {
				
			}
			
			@Override
			public void validateAttack(Minion m, Hero t) throws TauntBypassException,
					NotSummonedException, InvalidTargetException, CannotAttackException {
				
			}
			
			@Override
			public void validateAttack(Minion a, Minion t) throws TauntBypassException,
					InvalidTargetException, NotSummonedException, CannotAttackException {
				
			}
		};
		
		Paladin paladin = new Paladin();
		paladin.setValidator(actionValidator);
		try
		{
			paladin.useHeroPower();
			fail("UseHeroPower in class Paladin should call useHeroPower in the super class to validate action.");
		}
		catch(HeroPowerAlreadyUsedException e)
		{
			StackTraceElement [] stackTraceElement = e.getStackTrace();
			boolean found = false;
			for(int i=0;i<stackTraceElement.length&&!found;i++)
				if(stackTraceElement[i].getClassName().endsWith("Hero") && stackTraceElement[i].getMethodName().equals("useHeroPower"))
					found = true;
			if(!found)
				fail("UseHeroPower in class Paladin should call useHeroPower in the super class to validate using it.");
		}
		
		actionValidator = new ActionValidator() {
			
			@Override
			public void validateUsingHeroPower(Hero h) throws NotEnoughManaException,
					HeroPowerAlreadyUsedException {
			}
			
			@Override
			public void validateTurn(Hero user) throws NotYourTurnException {
				throw new NotYourTurnException();
			}
			
			@Override
			public void validatePlayingMinion(Minion m) throws FullFieldException {
				
			}
			
			@Override
			public void validateManaCost(Card card) throws NotEnoughManaException {
				
			}
			
			@Override
			public void validateAttack(Minion m, Hero t) throws TauntBypassException,
					NotSummonedException, InvalidTargetException, CannotAttackException {
				
			}
			
			@Override
			public void validateAttack(Minion a, Minion t) throws TauntBypassException,
					InvalidTargetException, NotSummonedException, CannotAttackException {
				
			}
		};
		
		try
		{
			paladin.useHeroPower();
			fail("UseHeroPower in class Paladin should call useHeroPower in the super class to validate action.");
		}
		catch(HeroPowerAlreadyUsedException e)
		{
			StackTraceElement [] stackTraceElement = e.getStackTrace();
			boolean found = false;
			for(int i=0;i<stackTraceElement.length&&!found;i++)
				if(stackTraceElement[i].getClassName().endsWith("Hero") && stackTraceElement[i].getMethodName().equals("useHeroPower"))
					found = true;
			if(!found)
				fail("UseHeroPower in class Paladin should call useHeroPower in the super class to validate the turn.");
		}
	}

	@Test(timeout = 3000)
	public void testPaladinUseHeroPowerNormaly() throws Exception 
	{
		Paladin paladin = new Paladin();

		ActionValidator validator = new ActionValidator() {
			
			@Override
			public void validateUsingHeroPower(Hero h) throws NotEnoughManaException,
					HeroPowerAlreadyUsedException {
				
			}
			
			@Override
			public void validateTurn(Hero user) throws NotYourTurnException {
				
			}
			
			@Override
			public void validatePlayingMinion(Minion m) throws FullFieldException {
				
			}
			
			@Override
			public void validateManaCost(Card card) throws NotEnoughManaException {
				
			}
			
			@Override
			public void validateAttack(Minion m, Hero t) throws TauntBypassException,
					NotSummonedException, InvalidTargetException, CannotAttackException {
				
			}
			
			@Override
			public void validateAttack(Minion a, Minion t) throws TauntBypassException,
					InvalidTargetException, NotSummonedException, CannotAttackException {
				
			}
		};


		paladin.setValidator(validator);
		paladin.setCurrentManaCrystals(2);
		paladin.useHeroPower();

		if(paladin.getField().size()!=1)
			fail("useHeroPower in class Paladin should add Silver Hand Recruit to the field.");
		Minion silverHandRecruit = paladin.getField().get(0);
		assertEquals("The minion summoned by useHeroPower in class Paladin should be named properly.","Silver Hand Recruit", silverHandRecruit.getName());
		assertEquals("The minion summoned by useHeroPower in class Paladin should be assigned the right amount of attack.",1, silverHandRecruit.getAttack());
		assertEquals("The minion summoned by useHeroPower in class Paladin should be assigned the right amount of max HP.",1, silverHandRecruit.getMaxHP());
		assertEquals("The minion summoned by useHeroPower in class Paladin should be assigned the right amount of current HP.",1, silverHandRecruit.getCurrentHP());
		assertEquals("The minion summoned by useHeroPower in class Paladin should be assigned the right rarity.",Rarity.BASIC, silverHandRecruit.getRarity());
		assertEquals("The minion summoned by useHeroPower in class Paladin should not be taunt.",false, silverHandRecruit.isTaunt());
		assertEquals("The minion summoned by useHeroPower in class Paladin should be sleeping.",true, silverHandRecruit.isSleeping());
		assertEquals("The minion summoned by useHeroPower in class Paladin should not be divine.",false, silverHandRecruit.isDivine());

		for(int i=0;i<6;i++)
			paladin.getField().add(new Icehowl());

		try
		{
			paladin.setCurrentManaCrystals(2);
			paladin.setHeroPowerUsed(false);
			paladin.useHeroPower();
			fail("If the field is full, useHeroPower in class Paladin should throw FullFieldException");
		}
		catch(FullFieldException e)
		{
		}

	}


	@Test(timeout = 3000)
	public void testPriestHasUseHeroPowerMinion() throws Exception 
	{
		testExistsInClass(Priest.class, "useHeroPower", true, void.class, Minion.class);
	}

	@Test(timeout = 3000)
	public void testPriestHasUseHeroPowerHero() throws Exception 
	{
		testExistsInClass(Priest.class, "useHeroPower", true, void.class, Hero.class);
	}
	
	@Test(timeout = 3000)
	public void testPriestCallsSuperUseHeroPower() throws Exception 
	{
		
		ActionValidator actionValidator = new ActionValidator() {
			
			@Override
			public void validateUsingHeroPower(Hero h) throws NotEnoughManaException,
					HeroPowerAlreadyUsedException {
				throw new HeroPowerAlreadyUsedException();
			}
			
			@Override
			public void validateTurn(Hero user) throws NotYourTurnException {
				
			}
			
			@Override
			public void validatePlayingMinion(Minion m) throws FullFieldException {
				
			}
			
			@Override
			public void validateManaCost(Card card) throws NotEnoughManaException {
				
			}
			
			@Override
			public void validateAttack(Minion m, Hero t) throws TauntBypassException,
					NotSummonedException, InvalidTargetException, CannotAttackException {
				
			}
			
			@Override
			public void validateAttack(Minion a, Minion t) throws TauntBypassException,
					InvalidTargetException, NotSummonedException, CannotAttackException {
				
			}
		};
		
		Priest priest = new Priest();
		priest.setValidator(actionValidator);
		try
		{
			priest.useHeroPower(new Icehowl());
			fail("UseHeroPower in class Priest targeting a minion should call useHeroPower in the super class to validate action.");
		}
		catch(HeroPowerAlreadyUsedException e)
		{
			StackTraceElement [] stackTraceElement = e.getStackTrace();
			boolean found = false;
			for(int i=0;i<stackTraceElement.length&&!found;i++)
				if(stackTraceElement[i].getClassName().endsWith("Hero") && stackTraceElement[i].getMethodName().equals("useHeroPower"))
					found = true;
			if(!found)
				fail("UseHeroPower in class Priest targeting a minion should call useHeroPower in the super class to validate using it.");
		}
		
		try
		{
			priest.useHeroPower(new Mage());
			fail("UseHeroPower in class Priest treating a hero should call useHeroPower in the super class to validate action.");
		}
		catch(HeroPowerAlreadyUsedException e)
		{
			StackTraceElement [] stackTraceElement = e.getStackTrace();
			boolean found = false;
			for(int i=0;i<stackTraceElement.length&&!found;i++)
				if(stackTraceElement[i].getClassName().endsWith("Hero") && stackTraceElement[i].getMethodName().equals("useHeroPower"))
					found = true;
			if(!found)
				fail("UseHeroPower in class Priest targeting a hero should call useHeroPower in the super class to validate using it.");
		}
		
		actionValidator = new ActionValidator() {
			
			@Override
			public void validateUsingHeroPower(Hero h) throws NotEnoughManaException,
					HeroPowerAlreadyUsedException {
			}
			
			@Override
			public void validateTurn(Hero user) throws NotYourTurnException {
				throw new NotYourTurnException();
			}
			
			@Override
			public void validatePlayingMinion(Minion m) throws FullFieldException {
				
			}
			
			@Override
			public void validateManaCost(Card card) throws NotEnoughManaException {
				
			}
			
			@Override
			public void validateAttack(Minion m, Hero t) throws TauntBypassException,
					NotSummonedException, InvalidTargetException, CannotAttackException {
				
			}
			
			@Override
			public void validateAttack(Minion a, Minion t) throws TauntBypassException,
					InvalidTargetException, NotSummonedException, CannotAttackException {
				
			}
		};
		
		try
		{
			priest.useHeroPower(new Icehowl());
			fail("UseHeroPower in class Priest targeting a minion should call useHeroPower in the super class to validate action.");
		}
		catch(HeroPowerAlreadyUsedException e)
		{
			StackTraceElement [] stackTraceElement = e.getStackTrace();
			boolean found = false;
			for(int i=0;i<stackTraceElement.length&&!found;i++)
				if(stackTraceElement[i].getClassName().endsWith("Hero") && stackTraceElement[i].getMethodName().equals("useHeroPower"))
					found = true;
			if(!found)
				fail("UseHeroPower in class Priest targeting a minion should call useHeroPower in the super class to validate the turn.");
		}
		
		try
		{
			priest.useHeroPower(new Mage());
			fail("UseHeroPower in class Priest targeting a hero should call useHeroPower in the super class to validate action.");
		}
		catch(HeroPowerAlreadyUsedException e)
		{
			StackTraceElement [] stackTraceElement = e.getStackTrace();
			boolean found = false;
			for(int i=0;i<stackTraceElement.length&&!found;i++)
				if(stackTraceElement[i].getClassName().endsWith("Hero") && stackTraceElement[i].getMethodName().equals("useHeroPower"))
					found = true;
			if(!found)
				fail("UseHeroPower in class Priest targeting a hero should call useHeroPower in the super class to validate the turn.");
		}
	}

	
	@Test(timeout = 3000)
	public void testPriestUseHeroPowerOnHero() throws Exception 
	{
		Priest priest = new Priest();

		ActionValidator validator = new ActionValidator() {
			
			@Override
			public void validateUsingHeroPower(Hero h) throws NotEnoughManaException,
					HeroPowerAlreadyUsedException {
				
			}
			
			@Override
			public void validateTurn(Hero user) throws NotYourTurnException {
				
			}
			
			@Override
			public void validatePlayingMinion(Minion m) throws FullFieldException {
				
			}
			
			@Override
			public void validateManaCost(Card card) throws NotEnoughManaException {
				
			}
			
			@Override
			public void validateAttack(Minion m, Hero t) throws TauntBypassException,
					NotSummonedException, InvalidTargetException, CannotAttackException {
				
			}
			
			@Override
			public void validateAttack(Minion a, Minion t) throws TauntBypassException,
					InvalidTargetException, NotSummonedException, CannotAttackException {
				
			}
		};

		Mage heroToBeHealed = new Mage();
		heroToBeHealed.setCurrentHP(3);
		priest.setValidator(validator);
		priest.setCurrentManaCrystals(2);
		priest.useHeroPower(heroToBeHealed);

		if(heroToBeHealed.getCurrentHP()!=5)
			fail("useHeroPower in class Priest should heal the target hero with the proper amount.");

		priest.setCurrentHP(1);
		priest.setHeroPowerUsed(false);
		priest.setCurrentManaCrystals(2);
		priest.useHeroPower(priest);

		if(priest.getCurrentHP()!=3)
			fail("useHeroPower in class Priest can heal self with the normal amount.");

	}


	@Test(timeout = 3000)
	public void testWarlockHasUseHeroPower() throws Exception 
	{
		testExistsInClass(Warlock.class, "useHeroPower", true, void.class);
	}
	
	@Test(timeout = 3000)
	public void testWarlockCallsSuperUseHeroPower() throws Exception 
	{
		
		ActionValidator actionValidator = new ActionValidator() {
			
			@Override
			public void validateUsingHeroPower(Hero h) throws NotEnoughManaException,
					HeroPowerAlreadyUsedException {
				throw new HeroPowerAlreadyUsedException();
			}
			
			@Override
			public void validateTurn(Hero user) throws NotYourTurnException {
				
			}
			
			@Override
			public void validatePlayingMinion(Minion m) throws FullFieldException {
				
			}
			
			@Override
			public void validateManaCost(Card card) throws NotEnoughManaException {
				
			}
			
			@Override
			public void validateAttack(Minion m, Hero t) throws TauntBypassException,
					NotSummonedException, InvalidTargetException, CannotAttackException {
				
			}
			
			@Override
			public void validateAttack(Minion a, Minion t) throws TauntBypassException,
					InvalidTargetException, NotSummonedException, CannotAttackException {
				
			}
		};
		
		Warlock warlock = new Warlock();
		warlock.setValidator(actionValidator);
		try
		{
			warlock.useHeroPower();
			fail("UseHeroPower in class Warlock should call useHeroPower in the super class to validate action.");
		}
		catch(HeroPowerAlreadyUsedException e)
		{
			StackTraceElement [] stackTraceElement = e.getStackTrace();
			boolean found = false;
			for(int i=0;i<stackTraceElement.length&&!found;i++)
				if(stackTraceElement[i].getClassName().endsWith("Hero") && stackTraceElement[i].getMethodName().equals("useHeroPower"))
					found = true;
			if(!found)
				fail("UseHeroPower in class Warlock should call useHeroPower in the super class to validate using it.");
		}
		
		actionValidator = new ActionValidator() {
			
			@Override
			public void validateUsingHeroPower(Hero h) throws NotEnoughManaException,
					HeroPowerAlreadyUsedException {
			}
			
			@Override
			public void validateTurn(Hero user) throws NotYourTurnException {
				throw new NotYourTurnException();
			}
			
			@Override
			public void validatePlayingMinion(Minion m) throws FullFieldException {
				
			}
			
			@Override
			public void validateManaCost(Card card) throws NotEnoughManaException {
				
			}
			
			@Override
			public void validateAttack(Minion m, Hero t) throws TauntBypassException,
					NotSummonedException, InvalidTargetException, CannotAttackException {
				
			}
			
			@Override
			public void validateAttack(Minion a, Minion t) throws TauntBypassException,
					InvalidTargetException, NotSummonedException, CannotAttackException {
				
			}
		};
		
		try
		{
			warlock.useHeroPower();
			fail("UseHeroPower in class Warlock should call useHeroPower in the super class to validate action.");
		}
		catch(HeroPowerAlreadyUsedException e)
		{
			StackTraceElement [] stackTraceElement = e.getStackTrace();
			boolean found = false;
			for(int i=0;i<stackTraceElement.length&&!found;i++)
				if(stackTraceElement[i].getClassName().endsWith("Hero") && stackTraceElement[i].getMethodName().equals("useHeroPower"))
					found = true;
			if(!found)
				fail("UseHeroPower in class Warlock should call useHeroPower in the super class to validate the turn.");
		}
	}

	@Test(timeout = 3000)
	public void testWarlockUseHeroPower() throws Exception 
	{
		Warlock warlock = new Warlock();

		ActionValidator validator = new ActionValidator() {
			
			@Override
			public void validateUsingHeroPower(Hero h) throws NotEnoughManaException,
					HeroPowerAlreadyUsedException {
				
			}
			
			@Override
			public void validateTurn(Hero user) throws NotYourTurnException {
				
			}
			
			@Override
			public void validatePlayingMinion(Minion m) throws FullFieldException {
				
			}
			
			@Override
			public void validateManaCost(Card card) throws NotEnoughManaException {
				
			}
			
			@Override
			public void validateAttack(Minion m, Hero t) throws TauntBypassException,
					NotSummonedException, InvalidTargetException, CannotAttackException {
				
			}
			
			@Override
			public void validateAttack(Minion a, Minion t) throws TauntBypassException,
					InvalidTargetException, NotSummonedException, CannotAttackException {
				
			}
		};

		warlock.setValidator(validator);
		warlock.setCurrentManaCrystals(2);
		warlock.getHand().clear();
		warlock.useHeroPower();

		if(warlock.getCurrentHP()!=28)
			fail("useHeroPower in class Warlock should damage self by 2.");
		if(warlock.getHand().size()!=1)
			fail("useHeroPower in class Warlock should add a card to hand");

		class TestCrad extends Card
		{
			public TestCrad() 
			{
				super("test", 0, null);
			}
		}

		TestCrad testCard = new TestCrad();

		warlock.getDeck().clear();
		warlock.getHand().clear();
		warlock.getDeck().add(testCard);
		warlock.setCurrentManaCrystals(2);
		warlock.setHeroPowerUsed(false);
		warlock.useHeroPower();

		if(warlock.getHand().get(0)!=testCard)
			fail("useHeroPower in class Warlock should draw card from own deck.");

		warlock.setCurrentManaCrystals(2);
		warlock.setHeroPowerUsed(false);
		warlock.useHeroPower();

		if(warlock.getCurrentHP()!=23)
			fail("useHeroPower in class Warlock on empty deck should trigger fatigueDamage damage.");
	}

	
	@Test(timeout = 1000)
	public void testClassIsInterfaceMinionListener()
			throws ClassNotFoundException {
		testIsInterface(Class.forName(minionListenerPath));
	}
	
	
	@Test(timeout = 1000)
	public void testInterfaceHeroListenerIsImplementedByClassGame() throws Exception
	{
		testClassImplementsInterface(Class.forName(gamePath), Class.forName(heroListenerPath));
	}
	
	
	@Test(timeout = 1000)
	public void testGetterForMinionListenerNotExistsInClassMinion() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(minionPath), "getListener", MinionListener.class, false);
	}
	@Test(timeout = 1000)
	public void testGetterForActionValidatorNotExistsInClassHero() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(heroPath), "getValidator", ActionValidator.class, false);
	}
	

	@Test(timeout = 1000)
	public void testClassIsInterfaceActionValidator()
			throws ClassNotFoundException {
		testIsInterface(Class.forName(actionValidatorPath));
	}

	@Test(timeout = 3000)
	public void testActionValidatorHasActionValidateHero()
			throws NotYourTurnException, ClassNotFoundException {
		testExistsInClass(Class.forName(actionValidatorPath), "validateTurn",
				true, void.class, Class.forName(heroPath));
	}



	@Test(timeout = 3000)
	public void testActionValidatorHasAttackValidateMinionVsHero()
			throws NotYourTurnException, TauntBypassException,
			InvalidTargetException, NotSummonedException,
			ClassNotFoundException {
		testExistsInClass(Class.forName(actionValidatorPath), "validateAttack",
				true, void.class, Class.forName(minionPath),
				Class.forName(heroPath));
	}


	@Test(timeout = 1000)
	public void testClassIsInterfaceGameListener()
			throws ClassNotFoundException {
		testIsInterface(Class.forName(gameListenerPath));
	}

	@Test(timeout = 3000)
	public void testGameListenerHasOnGameOver() throws ClassNotFoundException {
		testExistsInClass(Class.forName(gameListenerPath), "onGameOver", true,
				void.class);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableListenerInClassGameIsPresentAndIsPrivateGame()
			throws Exception {
		testInstanceVariableIsPresent(Class.forName(gamePath), "listener", true);
		testInstanceVariableIsPrivate(Class.forName(gamePath), "listener");
	}

	

	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableListenerInClassGame()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);

		GameListener listener = new GameListener() {
			@Override
			public void onGameOver() {

			}
		};
		testSetterLogic(game, "listener", listener, listener,
				GameListener.class);
	}

	
	@Test(timeout = 3000)
	public void testGameHasValidateTurnHero() throws ClassNotFoundException {
		testExistsInClass(Class.forName(gamePath), "validateTurn", true,
				void.class, Hero.class);
	}

	@Test(timeout = 1000, expected = NotYourTurnException.class)
	public void testValidateTurnHeroThrowsNotYourTurnExceptionInClassGame()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);

		Hero currentHero = game.getCurrentHero();
		Hero testHero = (currentHero == hero1) ? hero2 : hero1;
		game.validateTurn(testHero);
	}



	
	@Test(timeout = 1000)
	public void testValidateAttackMinionNotInFieldOnMinionThrowsNotSummonedExceptionInClassGame()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);
		Hero currentHero = game.getCurrentHero();
		Hero testHero = (currentHero == hero1) ? hero2 : hero1;
		Minion attacker = new Minion("minionThatIsNotOnField", 0, Rarity.BASIC,
				2, 10, false, false, true);
		Minion target = new Minion("minionToBeAttacked", 0, Rarity.BASIC, 2,
				10, false, false, true);
		testHero.getField().add(target);
		try {
			game.validateAttack(attacker, target);
			fail("The minion that the current hero is attacking with should be on field and should throw a NotSummonedException");
		} catch (NotSummonedException e) {

		}
	}

	@Test(timeout = 1000)
	public void testValidateAttackMinionInHandOnMinionThrowsNotSummonedExceptionInClassGame()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);
		Hero currentHero = game.getCurrentHero();
		Hero testHero = (currentHero == hero1) ? hero2 : hero1;
		Minion attacker = new Minion("minionThatIsInHand", 0, Rarity.BASIC, 2,
				10, false, false, true);
		Minion target = new Minion("minionToBeAttacked", 0, Rarity.BASIC, 2,
				10, false, false, true);
		testHero.getField().add(target);
		currentHero.getHand().add(attacker);
		try {
			game.validateAttack(attacker, target);
			fail("The minion that the current hero is attacking with should be on field and should throw a NotSummonedException");
		} catch (NotSummonedException e) {

		}
	}

	@Test(timeout = 1000)
	public void testValidateAttackMinionInHandAndInFieldOnMinionInClassGame()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);
		Hero currentHero = game.getCurrentHero();
		Hero testHero = (currentHero == hero1) ? hero2 : hero1;
		Minion attacker = new Minion("minionThatIsInHand", 0, Rarity.BASIC, 2,
				10, false, false, true);
		Minion target = new Minion("minionToBeAttacked", 0, Rarity.BASIC, 2,
				10, false, false, true);
		testHero.getField().add(target);
		currentHero.getHand().add(attacker);
		currentHero.getField().add(attacker);
		try {
			game.validateAttack(attacker, target);

		} catch (NotSummonedException e) {
			fail("The minion that the current hero is attacking with is on field and should be able to attack.");
		}
	}

	@Test(timeout = 1000)
	public void testValidateAttackMinionInFieldOnMinionNotInFieldThrowsNotSummonedExceptionInClassGame()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);
		Hero currentHero = game.getCurrentHero();
		Minion attacker = new Minion("minionThatIsInHand", 1, Rarity.BASIC, 2,
				10, false, false, true);
		Minion target = new Minion("minionToBeAttacked", 0, Rarity.BASIC, 2,
				10, false, false, true);
		currentHero.getField().add(attacker);
		try {
			game.validateAttack(attacker, target);
			fail("The minion that the current hero is targeting should be on field and should throw a NotSummonedException");
		} catch (NotSummonedException e) {
		}
	}

	@Test(timeout = 1000)
	public void testValidateAttackMinionFriendlyOnMinionFriendlyThrowsNotInvalidTargetExceptionInClassGame()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);
		Hero currentHero = game.getCurrentHero();
		Minion attacker = new Minion("minionThatIsInHand", 0, Rarity.BASIC, 2,
				10, false, false, true);
		Minion target = new Minion("minionToBeAttacked", 0, Rarity.BASIC, 2,
				10, false, false, true);
		currentHero.getField().add(attacker);
		currentHero.getField().add(target);
		try {
			game.validateAttack(attacker, target);
			fail("The minion that the current hero is attacking with cannot target a friendly minion and should throw an InvalidTargetException");
		} catch (InvalidTargetException e) {}
	}

	@Test(timeout = 1000)
	public void testValidateAttackMinionOnMinionNotTauntThrowsTauntBypassExceptionInClassGame()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);
		Hero currentHero = game.getCurrentHero();
		Hero testHero = (currentHero == hero1) ? hero2 : hero1;
		Minion attacker = new Minion("minionThatIsInHand", 0, Rarity.BASIC, 2,
				10, false, false, true);
		Minion target = new Minion("minionToBeAttacked", 0, Rarity.BASIC, 2,
				10, false, false, true);
		Minion target2 = new Minion("minionToBeAttacked", 0, Rarity.BASIC, 2,
				10, true, false, true);
		currentHero.getField().add(attacker);
		testHero.getField().add(target);
		testHero.getField().add(target2);
		try {
			game.validateAttack(attacker, target);
			fail("The minion that the current hero is attacking cannot be targetted because there is another taunt minion and should throw a TauntBypassException");
		} catch (TauntBypassException e) {}
	}

	@Test(timeout = 1000)
	public void testValidateAttackMinionOnMinionTauntDoesntThrowTauntBypassExceptionInClassGame()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);
		Hero currentHero = game.getCurrentHero();
		Hero testHero = (currentHero == hero1) ? hero2 : hero1;
		Minion attacker = new Minion("minionThatIsInHand", 0, Rarity.BASIC, 2,
				10, false, false, true);
		Minion target = new Minion("minionToBeAttacked", 0, Rarity.BASIC, 2,
				10, true, false, true);
		Minion target2 = new Minion("minionToBeAttacked", 0, Rarity.BASIC, 2,
				10, true, false, true);
		currentHero.getField().add(attacker);
		testHero.getField().add(target);
		testHero.getField().add(target2);

		try {
			game.validateAttack(attacker, target);
		} catch (TauntBypassException e) {
			fail("The minion that the current hero is attacking is taunt and can be targetted.");
		}
	}

	@Test(timeout = 1000)
	public void testValidateAttackMinionOnMinionDoesntThrowTauntBypassExceptionInClassGame()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);

		Hero currentHero = game.getCurrentHero();
		Hero testHero = (currentHero == hero1) ? hero2 : hero1;
		Minion attacker = new Minion("minionThatIsInHand", 0, Rarity.BASIC, 2,
				10, false, false, true);
		Minion target = new Minion("minionToBeAttacked", 0, Rarity.BASIC, 2,
				10, false, false, true);
		Minion target2 = new Minion("minionToBeAttacked", 0, Rarity.BASIC, 2,
				10, false, false, true);
		currentHero.getField().add(attacker);
		testHero.getField().add(target);
		testHero.getField().add(target2);

		try {
			game.validateAttack(attacker, target);
		} catch (TauntBypassException e) {
			fail("The minion that the current hero is attacking can be targetted.");

		}
	}



	@Test(timeout = 1000)
	public void testValidateAttackMinionOnMinionAttack0ThrowsCannotAttackException()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);

		Hero currentHero = game.getCurrentHero();
		Hero testHero = (currentHero == hero1) ? hero2 : hero1;
		Minion attacker = new Minion("minionThatIsInHand", 0, Rarity.BASIC, 0,
				10, false, false, true);
		Minion target = new Minion("minionToBeAttacked", 0, Rarity.BASIC, 2,
				10, false, false, true);
		currentHero.getField().add(attacker);
		testHero.getField().add(target);
		try {
			game.validateAttack(attacker, target);
			fail("The minion that the current hero is attacking with cannot have 0 attack and if such case happens, a CannotAttackException should be thrown in the validateAttack(Minion,Minion) in class Game.");
		} catch (CannotAttackException e) {}
	}
	

	@Test(timeout = 1000)
	public void testValidateAttackMinionOnMinionAlreadyAttackedThrowsCannotAttackException()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);

		Hero currentHero = game.getCurrentHero();
		Hero testHero = (currentHero == hero1) ? hero2 : hero1;
		Minion attacker = new Minion("minionThatIsInHand", 0, Rarity.BASIC, 2,
				10, false, false, true);
		Minion target = new Minion("minionToBeAttacked", 0, Rarity.BASIC, 2,
				10, false, false, true);
		attacker.setAttacked(true);
		currentHero.getField().add(attacker);
		testHero.getField().add(target);
		try {
			game.validateAttack(attacker, target);
			fail("The minion that the current hero is attacking with can not attack in the same turn twice, so if the attacker minion has already attacked this turn and the hero attempts to attack with it again then a CannotAttackException should be thrown in the validateAttack(Minion,Minion) in class Game.");
		} catch (CannotAttackException e) {}
	}
	

	@Test(timeout = 3000)
	public void testGameHasValidaAttackMinionOnHero()
			throws ClassNotFoundException {
		testExistsInClass(Class.forName(gamePath), "validateAttack", true,
				void.class, Minion.class, Hero.class);
	}

	@Test(timeout = 1000)
	public void testValidateAttackMinionNotInFieldOnHeroThrowsNotSummonedExceptionInClassGame()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);
		Hero currentHero = game.getCurrentHero();
		Hero testHero = (currentHero == hero1) ? hero2 : hero1;
		Minion attacker = new Minion("minionThatIsNotOnField", 0, Rarity.BASIC,
				2, 10, false, false, true);
		Minion target = new Minion("minionToBeAttacked", 0, Rarity.BASIC, 2,
				10, false, false, true);
		testHero.getField().add(target);

		try {
			game.validateAttack(attacker, testHero);
			fail("The minion that the current hero is attacking with should be on field and should throw a NotSummonedException");
		} catch (NotSummonedException e) {

		}
	}

	
	@Test(timeout = 1000)
	public void testValidateAttackMinionOnHerowithTauntThrowsTauntBypassExceptionInClassGame()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);
		Hero currentHero = game.getCurrentHero();
		Hero testHero = (currentHero == hero1) ? hero2 : hero1;
		Minion attacker = new Minion("minionThatIsNotOnField", 0, Rarity.BASIC,
				2, 10, false, false, true);
		Minion target = new Minion("minionToBeAttacked", 0, Rarity.BASIC, 2,
				10, true, false, true);
		currentHero.getField().add(attacker);
		testHero.getField().add(target);

		try {
			game.validateAttack(attacker, testHero);
			fail("The minion that the current hero is attacking with should not be able to attack target Hero if it has a minion with taunt on field and should throw a TauntBypassException");
		} catch (TauntBypassException e) {

		}
	}

	@Test(timeout = 1000)
	public void testValidateAttackMinionInFieldOnHeroInClassGame()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);
		Hero currentHero = game.getCurrentHero();
		Hero testHero = (currentHero == hero1) ? hero2 : hero1;
		Minion attacker = new Minion("minionThatIsNotOnField", 0, Rarity.BASIC,
				2, 10, false, false, true);
		Minion target = new Minion("minionToBeAttacked", 0, Rarity.BASIC, 2,
				10, false, false, true);
		currentHero.getField().add(attacker);
		testHero.getField().add(target);

		try {
			game.validateAttack(attacker, testHero);
		} catch (NotSummonedException e) {
			fail("The minion that the current hero is attacking with is on field and should be able to attack normally");
		}
	}
	
	
	
	@Test(timeout = 1000)
	public void testValidateAttackMinionOnHeroSleepingThrowsCannotAttackException()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);

		Hero currentHero = game.getCurrentHero();
		Hero target = (currentHero == hero1) ? hero2 : hero1;
		Minion attacker = new Minion("minionThatIsInHand", 0, Rarity.BASIC, 2,
				10, false, false, false);
		currentHero.getField().add(attacker);
		try {
			game.validateAttack(attacker, target);
			fail("The minion that the current hero is attacking with should not be sleeping and if such case happens, a CannotAttackException should be thrown in the validateAttack(Minion,Minion) in class Game.");
		} catch (CannotAttackException e) {}
	}
	
	@Test(timeout = 1000)
	public void testValidateAttackMinionOnHeroAlreadyAttackedThrowsCannotAttackException()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);

		Hero currentHero = game.getCurrentHero();
		Hero target = (currentHero == hero1) ? hero2 : hero1;
		Minion attacker = new Minion("minionThatIsInHand", 0, Rarity.BASIC, 2,
				10, false, false, true);
		attacker.setAttacked(true);
		currentHero.getField().add(attacker);
		try {
			game.validateAttack(attacker, target);
			fail("The minion that the current hero is attacking with can not attack in the same turn twice, so if the attacker minion has already attacked this turn and the hero attempts to attack with it again then a CannotAttackException should be thrown in the validateAttack(Minion,Minion) in class Game.");
		} catch (CannotAttackException e) {}
	}

	
	@Test(timeout = 3000)
	public void testGameHasValidateManaCost()
			throws ClassNotFoundException {
		testExistsInClass(Class.forName(gamePath), "validateManaCost", true,
				void.class,Card.class);
	}

	@Test(timeout = 1000)
	public void testValidateCardManaCostThrowsNotEnoughManaExceptionInClassGame()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);
		Hero currentHero = game.getCurrentHero();
		Card card = new Card("cardToBeTested",3,null){};
		currentHero.setCurrentManaCrystals(2);
		try {
			game.validateManaCost(card);
			fail("The class Game should validate that the currentHero has enough mana before playing a card and should throw a NotEnoughManaException if it is not");
		} catch (NotEnoughManaException e) {}
	}


	
	@Test(timeout = 3000)
	public void testGameHasValidatePlayingMinion()
			throws ClassNotFoundException {
		testExistsInClass(Class.forName(gamePath), "validatePlayingMinion", true,
				void.class,Minion.class);
	}

	@Test(timeout = 1000)
	public void testValidatePlayingMinionThrowsFullFieldExceptionInClassGame()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);
		Hero currentHero = game.getCurrentHero();
		Minion minion = new Minion("testMinion", 4, Rarity.RARE, 4, 5, false, false, false);
		for(int i = 0; i < 7 ;i++)
			currentHero.getField().add(new Icehowl());
		
		currentHero.setCurrentManaCrystals(10);
		try {
			game.validatePlayingMinion(minion);
			fail("The class Game should validate that the currentHero's field has space for the minion to be played and should throw a FullFieldException if it does not");
		} catch (FullFieldException e) {}
	}
	@Test(timeout = 1000)
	public void testValidatePlayingMinionDoesNOTThrowFullFieldExceptionInClassGame()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);
		Hero currentHero = game.getCurrentHero();
		Minion minion = new Minion("testMinion", 4, Rarity.RARE, 4, 5, false, false, false);
		for(int i = 0; i < 4 ;i++)
			currentHero.getField().add(new Icehowl());
		
		currentHero.setCurrentManaCrystals(10);
		try {
			game.validatePlayingMinion(minion);
			} catch (FullFieldException e) {
				fail("The class Game should validate that the currentHero's field has space for the minion to be played and should NOT throw a FullFieldException if it does");
			}
	}

	
	
	@Test(timeout = 3000)
	public void testGameHasValidateUsingHeroPower()
			throws ClassNotFoundException {
		testExistsInClass(Class.forName(gamePath), "validateUsingHeroPower", true,
				void.class,Hero.class);
	}


	
	@Test(timeout = 1000)
	public void testValidateUsingHeroPowerDoesNOTThrowNotEnoughManaExceptionInClassGame()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);
		Hero currentHero = game.getCurrentHero();
		currentHero.setCurrentManaCrystals(2);
		try {
			game.validateUsingHeroPower(currentHero);
		} catch (NotEnoughManaException e) {
			fail("The class Game should validate that the currentHero's Mana Crystals are enough to use the hero power and should not throw a NotEnoughManaException if it is enough");
		}
	}
	
	@Test(timeout = 1000)
	public void testValidateUsingHeroPowerThrowsHeroPowerAlreadyUsedInClassGame()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);
		Hero currentHero = game.getCurrentHero();
		currentHero.setCurrentManaCrystals(10);
		currentHero.setHeroPowerUsed(true);
		try {
			game.validateUsingHeroPower(currentHero);
			fail("The class Game should validate that the currentHero's power has been used this turn and should throw a HeroPowerAlreadyUsed if it was.");
		} catch (HeroPowerAlreadyUsedException e) {}
	}
	
	@Test(timeout = 1000)
	public void testValidateUsingHeroPowerWorksNormally()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);
		Hero currentHero = game.getCurrentHero();
		currentHero.setCurrentManaCrystals(10);
		currentHero.setHeroPowerUsed(false);
		try {
			game.validateUsingHeroPower(currentHero);
		} catch (HeroPowerAlreadyUsedException e) {
			fail("The class Game should validate that the currentHero's power can be used and should not throw any Exception if it can");
		}
		catch(NotEnoughManaException e){
			fail("The class Game should validate that the currentHero's power can be used and should not throw any Exception if it can");
		}
	}
	
	@Test(timeout = 3000)
	public void testGameHasOnHeroDeath() throws ClassNotFoundException {
		testExistsInClass(Class.forName(gamePath), "onHeroDeath", true,
				void.class);
	}
	
	@Test( timeout = 3000)
	public void testGameOnHeroDeathCallsListenerOnGameOver() throws Exception{
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);
		GameListener listener = new GameListener() {
			@Override
			public void onGameOver() {
				callParameters.add(this);
			}
		};
		game.setListener(listener);
		game.onHeroDeath();
		if(callParameters.isEmpty())
			fail("The game onHeroDeath() method should call the listener onGameOver() method");
		reset();
	}
	
	
		@Test(timeout = 3000)
		public void testGameHasDamageOpponent() throws ClassNotFoundException {
			testExistsInClass(Class.forName(gamePath), "damageOpponent", true,
					void.class,int.class);
		}
		
		@Test( timeout = 3000)
		public void testGameDamageOpponentCallsHeroSetHp() throws Exception{
			Hero hero1 = new Hunter(){
				@Override
				public void setCurrentHP(int hp) {
					if(hp != 29 )
						fail("The method damageOpponent in class Game should call setCurrentHp in class Hero with the correct amount not " + (30 - hp));
					callParameters.add(this);
				}
			};
			Hero hero2 = new Hunter(){
				@Override
				public void setCurrentHP(int hp) {
					if(hp != 29 )
						fail("The method damageOpponent in class Game should call setCurrentHp in class Hero with the correct amount not " + (30 - hp));

					callParameters.add(this);
				}
			};
			Game game = new Game(hero1, hero2);
			reset();
			Hero currentHero = game.getCurrentHero();
			Hero testHero = (currentHero == hero1) ? hero2 : hero1;
			game.damageOpponent(1);
			if(callParameters.isEmpty())
				fail("The game damageOpponent() method should call the Hero setCurrentHP() method");
			if(!callParameters.contains(testHero)){
				reset();
				fail("the game damageOpponent() method should call the Hero setCurrentHP() method on the opponent");
			}
			reset();
		}
		
		
		
		@Test(timeout = 3000)
		public void testGameHasEndTurn() throws ClassNotFoundException {
			testExistsInClass(Class.forName(gamePath), "endTurn", true,
					void.class);
		}
		
		@Test( timeout = 3000)
		public void testGameEndTurnSwapsHeroes() throws Exception{
			Hero hero1 = new Hunter();
			Hero hero2 = new Hunter();
			Game game = new Game(hero1, hero2);
			Hero currentHero = game.getCurrentHero();
			Hero testHero = (currentHero == hero1) ? hero2 : hero1;
			if(testHero!= game.getOpponent())
				fail("You should initialize the currentHero and the opponent correctly.");
			game.endTurn();
			Hero currentHeroAfterTurn = game.getCurrentHero();
			Hero testHeroAfterTurn = (currentHeroAfterTurn == hero1) ? hero2 : hero1;

			if(testHeroAfterTurn!= game.getOpponent())
				fail("You should swap the currentHero and the opponent correctly.");
			
			if(currentHero == currentHeroAfterTurn)
				fail("The method endTurn in class Game should swap the current hero to be the opponent");
			if(testHero == testHeroAfterTurn)
				fail("The method endTurn in class Game should swap the opponent to be the previous current hero");
			if(!((currentHero == testHeroAfterTurn) && (testHero == currentHeroAfterTurn)))
				fail("The method endTurn in class Game should swap the current hero and the opponent correctly");
			
		}
		
		@Test( timeout = 3000)
		public void testGameEndTurnIncreasesTotalManaCrystalsLessThan10() throws Exception{
			Hero hero1 = new Hunter();
			Hero hero2 = new Hunter();
			Game game = new Game(hero1, hero2);
			Hero currentHero = game.getCurrentHero();
			Hero testHero = (currentHero == hero1) ? hero2 : hero1;
			int manaCrystals = testHero.getTotalManaCrystals();
			game.endTurn();
			Hero currentHeroAfterTurn = game.getCurrentHero();
			int manaCrystalsAfterTurn = currentHeroAfterTurn.getTotalManaCrystals();
			if(manaCrystals == manaCrystalsAfterTurn)
				fail("The method endTurn in class Game should increase the total mana crystals of the new currentHero");
			
			
		}
		
	
		
		@Test( timeout = 3000)
		public void testGameEndTurnSetsCurrentManaCrystalsToTotalManaCrystals() throws Exception{
			Hero hero1 = new Hunter();
			Hero hero2 = new Hunter();
			Game game = new Game(hero1, hero2);
			Hero currentHero = game.getCurrentHero();
			Hero testHero = (currentHero == hero1) ? hero2 : hero1;
			testHero.setTotalManaCrystals(5);
			int manaCrystals = testHero.getTotalManaCrystals();
			game.endTurn();
			Hero currentHeroAfterTurn = game.getCurrentHero();
			int manaCrystalsAfterTurn = currentHeroAfterTurn.getCurrentManaCrystals();
			if(manaCrystals+1 != manaCrystalsAfterTurn)
				fail("The method endTurn in class Game should set the current mana crystals of the new currentHero to be the new total mana crystals");
		}
		
		@Test( timeout = 3000)
		public void testGameEndTurnSetsHeroPowerUsedToFalse() throws Exception{
			Hero hero1 = new Hunter();
			Hero hero2 = new Hunter();
			Game game = new Game(hero1, hero2);
			Hero currentHero = game.getCurrentHero();
			Hero testHero = (currentHero == hero1) ? hero2 : hero1;
			testHero.setHeroPowerUsed(true);
			game.endTurn();
			Hero currentHeroAfterTurn = game.getCurrentHero();
			if(currentHeroAfterTurn.isHeroPowerUsed())
				fail("The method endTurn in class Game should set the heroPowerUsed of the new current hero to not used");
			
		}
		
		@Test( timeout = 3000)
		public void testGameEndTurnShouldCallDrawCardForTheNewCurrentHero() throws Exception{
			Hero hero1 = new Hunter(){
				@Override
				public Card drawCard() throws FullHandException,
						CloneNotSupportedException {
					callParameters.add(this);
					return null;
				}
			};
			Hero hero2 = new Hunter(){
				@Override
				public Card drawCard() throws FullHandException,
						CloneNotSupportedException {
					callParameters.add(this);
					return null;
				}
			};
			Game game = new Game(hero1, hero2);
			reset();
			game.endTurn();
			Hero currentHeroAfterTurn = game.getCurrentHero();
			Hero testHeroAfterTurn = (currentHeroAfterTurn == hero1) ? hero2 : hero1;
			if(callParameters.isEmpty())
				fail("The method endTurn in class Game should call the method drawCard in the class Hero");
			if(callParameters.contains(testHeroAfterTurn)){
				reset();
				fail("The method endTurn in class Game should call the method drawCard in the class Hero on the correct Hero");
			}
			reset();
			}
		
	
		
		@Test( timeout = 3000)
		public void testGameEndTurnShouldCallSetAttackedForTheNewCurrentHeroMinions() throws Exception{
			Hero hero1 = new Hunter();
			Hero hero2 = new Hunter();
			Game game = new Game(hero1, hero2);
			reset();
			Hero currentHero = game.getCurrentHero();
			Hero testHero = (currentHero == hero1) ? hero2 : hero1;
			ArrayList<Minion> toBeTested = new ArrayList<Minion>();
			for(int i = 0 ; i < 5 ; i++){
				Minion m = new Minion("minion"+i, i, Rarity.BASIC, i, 4, false, false, false){
					@Override
					public void setAttacked(boolean attacked) {
						if(!attacked)
							callParameters.add(this);
						else{
							reset();
							fail("The method endTurn in the class Game should call the method setAttacked in class Minion and change the currentHero's minions to not attacked");
						}
					}
				};
				testHero.getField().add(m);
				toBeTested.add(m);
			}
			
			game.endTurn();
			if(callParameters.isEmpty())
				fail("The method endTurn in class Game should call the method setAttacked in class Minion");
			for(int i = 0 ; i < toBeTested.size(); i++){
				Minion m = toBeTested.get(i);
				if(!callParameters.contains(m)){
					reset();
					fail("The method endTurn in class Game should call the method setAttacked in class Minion");
				}
			}
			reset();
			}
		
		@Test( timeout = 3000)
		public void testGameConstructorCallsDrawCard() throws Exception{
			Hero hero1 = new Hunter(){
				@Override
				public Card drawCard() throws FullHandException,
						CloneNotSupportedException {
					callParameters.add(this);
					return null;
				}
			};
			Hero hero2 = new Hunter(){
				@Override
				public Card drawCard() throws FullHandException,
						CloneNotSupportedException {
					callParameters.add(this);
					return null;
				}
			};
			reset();
			Game game = new Game(hero1, hero2);
			Hero currentHero = game.getCurrentHero();
			Hero testHero = (currentHero == hero1) ? hero2 : hero1;
			
			if(callParameters.isEmpty())
				fail("Game Constructor should call the method drawCard in class Hero");
			if(!callParameters.contains(currentHero)){
				reset();
				fail("Game Constructor should call the method drawCard in class Hero for the current hero");
			}
			if(!callParameters.contains(testHero)){
				reset();
				fail("Game Constructor should call the method drawCard in class Hero for the opponent");
			}
			reset();
		}
		
		@Test( timeout = 3000)
		public void testGameConstructorCallsDrawCardCorrectNumberOfTimes() throws Exception{
			Hero hero1 = new Hunter(){
				@Override
				public Card drawCard() throws FullHandException,
						CloneNotSupportedException {
					callParameters.add(this);
					return null;
				}
			};
			Hero hero2 = new Hunter(){
				@Override
				public Card drawCard() throws FullHandException,
						CloneNotSupportedException {
					callParameters.add(this);
					return null;
				}
			};
			reset();
			Game game = new Game(hero1, hero2);
			Hero currentHero = game.getCurrentHero();
			Hero testHero = (currentHero == hero1) ? hero2 : hero1;
			
			if(callParameters.isEmpty())
				fail("Game Constructor should call the method drawCard in class Hero");
			int occurrences = Collections.frequency(callParameters, currentHero);
			if(occurrences != 3){
				reset();
				fail("Game Constructor should call the method drawCard in class Hero for the current hero 3 times");
			}
			occurrences = Collections.frequency(callParameters, testHero);
			if(occurrences != 4){
				reset();
				fail("Game Constructor should call the method drawCard in class Hero for the opponent 4 times");
			}
			reset();
		}
		
		@Test( timeout = 3000)
		public void testGameConstructorCallsSetListenerForBothHeroes() throws Exception{
			Hero hero1 = new Hunter(){
				@Override
				public void setListener(HeroListener listener) {
						callParameters.add(this);
						callParameters.add(listener);
						super.setListener(listener);
				}
			};
			Hero hero2 = new Hunter(){
			@Override
			public void setListener(HeroListener listener) {
					callParameters.add(this);
					callParameters.add(listener);
					super.setListener(listener);
			}
			};
			reset();
			Game game = new Game(hero1, hero2);
			Hero currentHero = game.getCurrentHero();
			Hero testHero = (currentHero == hero1) ? hero2 : hero1;
			
			if(callParameters.isEmpty())
				fail("Game Constructor should call the method setListener in class Hero");
			int occurrences = Collections.frequency(callParameters, game);
			if(!callParameters.contains(currentHero)){
				reset();
				fail("Game Constructor should call the method setListener in class Hero for the currentHero");
			}
			if(!callParameters.contains(testHero)){
				reset();
				fail("Game Constructor should call the method setListener in class Hero for the opponent");
			}
			if(occurrences != 2){
				reset();
				fail("Game Constructor should call the method setListener in class Hero for both the currentHero and the opponent");
			}
			reset();
		}
		
		@Test(timeout = 1000)
		public void testInterfaceMinionListenerIsImplementedByClassHero() throws Exception
		{
			testClassImplementsInterface(Class.forName(heroPath), Class.forName(minionListenerPath));
		}
		
		@Test(timeout = 1000)
		public void testInstanceVariableListenerInClassHeroIsPresentAndIsPrivateGame()
				throws Exception {
			testInstanceVariableIsPresent(Class.forName(heroPath), "listener", true);
			testInstanceVariableIsPrivate(Class.forName(heroPath), "listener");
		}

		@Test(timeout = 1000)
		public void testSetterForInstanceVariableListenerExistsInClassHero()
				throws Exception {
			testSetterMethodExistsInClass(Class.forName(heroPath), "setListener",
					HeroListener.class, true);
		}

		@Test(timeout = 1000)
		public void testSetterLogicForInstanceVariableListenerInClassHero()
				throws Exception {
			Hero hero = new Hunter();
			HeroListener listener = new HeroListener() {

				@Override
				public void onHeroDeath() {
				}

				@Override
				public void damageOpponent(int amount) {
				}

				@Override
				public void endTurn() throws FullHandException,
						CloneNotSupportedException {
				}
			};
			testSetterLogic(hero, "listener", listener, listener,
					HeroListener.class);
		}

		
		@Test(timeout = 1000)
		public void testGetterForInstanceVariableListenerExistsInClassHero()
				throws Exception {
			testGetterMethodExistsInClass(Class.forName(heroPath), "getListener", HeroListener.class ,true);
		}

		@Test(timeout = 1000)
		public void testGetterLogicForInstanceVariableListenerInClassHero()
				throws Exception {
			Hero hero = new Hunter();
			HeroListener listener = new HeroListener() {

				@Override
				public void onHeroDeath() {
				}

				@Override
				public void damageOpponent(int amount) {
				}

				@Override
				public void endTurn() throws FullHandException,
						CloneNotSupportedException {
				}
			};
			testGetterLogic(hero, "listener", listener);
		}

		@Test(timeout = 1000)
		public void testInstanceVariableValidatorInClassHeroIsPresentAndIsPrivateGame()
				throws Exception {
			testInstanceVariableIsPresent(Class.forName(heroPath), "validator", true);
			testInstanceVariableIsPrivate(Class.forName(heroPath), "validator");
		}

		@Test(timeout = 1000)
		public void testSetterForInstanceVariableValidatorExistsInClassHero()
				throws Exception {
			testSetterMethodExistsInClass(Class.forName(heroPath), "setValidator",
					ActionValidator.class, true);
		}

		@Test(timeout = 1000)
		public void testSetterLogicForInstanceVariableValidatorInClassHero()
				throws Exception {
			Hero hero = new Hunter();
			ActionValidator validator = new ActionValidator() {
				@Override
				public void validateTurn(Hero user)
						throws NotYourTurnException {}
				@Override
				public void validateAttack(Minion a, Minion t)
						throws CannotAttackException, TauntBypassException,
						InvalidTargetException, NotSummonedException {}
				@Override
				public void validateAttack(Minion m, Hero t)
						throws CannotAttackException, TauntBypassException,
						NotSummonedException, InvalidTargetException {}
				@Override
				public void validateManaCost(Card card)
						throws NotEnoughManaException {}
				@Override
				public void validatePlayingMinion(Minion m)
						throws FullFieldException {}
				@Override
				public void validateUsingHeroPower(Hero h)
						throws NotEnoughManaException,
						HeroPowerAlreadyUsedException {}
			};
			testSetterLogic(hero, "validator", validator, validator,
					ActionValidator.class);
		}
		
		@Test(timeout = 3000)
		public void testGameHasOnMinionDeath() throws ClassNotFoundException {
			testExistsInClass(Class.forName(heroPath), "onMinionDeath", true,
					void.class,Minion.class);
		}
		
		@Test(timeout = 3000)
		public void testGameOnMinionDeathRemovesMinionFromField() throws Exception {
			Hero hero = new Hunter();
			Minion m1 = new Minion("minionToBeRemoved", 1, Rarity.LEGENDARY, 1, 2, true, false, false);
			Minion m2 = new Minion("minionToStay", 1, Rarity.LEGENDARY, 1, 2, true, false, false);
			hero.getField().add(m2);
			hero.getField().add(m1);
			hero.onMinionDeath(m1);
			if(hero.getField().size() == 0)
				fail("The method onMinionDeath in class Hero should remove only one Minion from the field");
			if(hero.getField().contains(m1)){
				reset();
				fail("The method onMinionDeath in class Hero should remove the correct Minion from the field");
			}
			reset();
		}
		
		
		@Test(timeout = 3000)
		public void testHeroSetHPToZeroCallsOnDeath() throws Exception {
			Hero hero = new Hunter();
			HeroListener listener = new HeroListener() {
				
				@Override
				public void onHeroDeath() {
					callParameters.add(this);
				}
				@Override
				public void endTurn() throws FullHandException, CloneNotSupportedException {
				}
				@Override
				public void damageOpponent(int amount) {
				}
			};
			hero.setListener(listener);
			reset();
			hero.setCurrentHP(0);
			if(callParameters.isEmpty())
				fail("The method setCurrentHp in class Hero should trigger the method onDeath from the listener if the hero's hp becomes 0");
			if(!callParameters.contains(listener)){
				reset();
				fail("The method setCurrentHp in class Hero should trigger the method onDeath from the listener if the hero's hp becomes 0 on the correct hero");
			}
			reset();
		}
		
		
		
		@Test(timeout = 3000)
		public void testHeroHasUseHeroPower() throws ClassNotFoundException {
			testExistsInClass(Class.forName(heroPath), "useHeroPower", true,
					void.class);
		}
		
		@Test(timeout = 3000)
		public void testHeroUseHeroPowerCallsValidateTurn() throws Exception {
			Hero hero = new Hero("testHero") {
				@Override
				public void buildDeck() throws IOException {
				}
			};
			ActionValidator validator = new ActionValidator() {
				@Override
				public void validateAttack(Minion m, Hero t) throws CannotAttackException,
						TauntBypassException, NotSummonedException, InvalidTargetException {
				}
				@Override
				public void validateAttack(Minion a, Minion t) throws CannotAttackException,
						TauntBypassException, InvalidTargetException, NotSummonedException {
				}
				@Override
				public void validateTurn(Hero user) throws NotYourTurnException {
					callParameters.add(user);
				}
				@Override
				public void validateManaCost(Card card)
						throws NotEnoughManaException {
				}
				@Override
				public void validatePlayingMinion(Minion m)
						throws FullFieldException {
				}
				@Override
				public void validateUsingHeroPower(Hero h)
						throws NotEnoughManaException,
						HeroPowerAlreadyUsedException {
				}
			};
			reset();
			hero.setValidator(validator);
			hero.setCurrentManaCrystals(3);
			hero.useHeroPower();
			if(callParameters.isEmpty()){
				fail("The method useHeroPower in class Hero should call the method validateTurn from the validator to check if it's the correct turn");
			}
			if(!callParameters.contains(hero)){
				reset();
				fail("The method useHeroPower in class Hero should call the method validateTurn from the validator to check if it's the correct turn from the correct hero");
			}
			reset();
		}
		
		@Test(timeout = 3000)
		public void testHeroUseHeroPowerThrowsNotEnoughManaException() throws Exception {
			Hero hero = new Hero("testHero") {
				@Override
				public void buildDeck() throws IOException {
				}
			};
			ActionValidator validator = new ActionValidator() {
				@Override
				public void validateAttack(Minion m, Hero t) throws CannotAttackException,
						TauntBypassException, NotSummonedException, InvalidTargetException {
				}
				@Override
				public void validateAttack(Minion a, Minion t) throws CannotAttackException,
						TauntBypassException, InvalidTargetException, NotSummonedException {
				}
				@Override
				public void validateTurn(Hero user) throws NotYourTurnException {
				}
				@Override
				public void validateManaCost(Card card)
						throws NotEnoughManaException {
				}
				@Override
				public void validatePlayingMinion(Minion m)
						throws FullFieldException {
				}
				@Override
				public void validateUsingHeroPower(Hero h)
						throws NotEnoughManaException,
						HeroPowerAlreadyUsedException {
					callParameters.add(h);
				}
			};
			reset();
			hero.setValidator(validator);
			hero.setCurrentManaCrystals(1);
			hero.useHeroPower();
			if(callParameters.isEmpty()){
				fail("The method useHeroPower in class Hero should call the method validateUsingHeroPower from the validator to check if it can use the hero power");
			}
			if(!callParameters.contains(hero)){
				reset();
				fail("The method useHeroPower in class Hero should call the method validateUsingHeroPower from the validator to check if it can use the hero power");
			}
			reset();
		}
		
		
		@Test(timeout = 3000)
		public void testHeroUseHeroPowerWorksNormally() throws Exception {
			Hero hero = new Hero("testHero") {
				@Override
				public void buildDeck() throws IOException {
				}
			};
			ActionValidator validator = new ActionValidator() {
				@Override
				public void validateAttack(Minion m, Hero t) throws CannotAttackException,
						TauntBypassException, NotSummonedException, InvalidTargetException {
				}
				@Override
				public void validateAttack(Minion a, Minion t) throws CannotAttackException,
						TauntBypassException, InvalidTargetException, NotSummonedException {
				}
				@Override
				public void validateTurn(Hero user) throws NotYourTurnException {
				}
				@Override
				public void validateManaCost(Card card)
						throws NotEnoughManaException {
				}
				@Override
				public void validatePlayingMinion(Minion m)
						throws FullFieldException {
				}
				@Override
				public void validateUsingHeroPower(Hero h)
						throws NotEnoughManaException,
						HeroPowerAlreadyUsedException {
					callParameters.add(h);
				}
			};
			reset();
			hero.setValidator(validator);
			hero.setCurrentManaCrystals(3);
			hero.useHeroPower();
			if(callParameters.isEmpty()){
				fail("The method useHeroPower in class Hero should call the method validateUsingHeroPower from the validator to check if it can use the hero power");
			}
			if(!callParameters.contains(hero)){
				reset();
				fail("The method useHeroPower in class Hero should call the method validateUsingHeroPower from the validator to check if it can use the hero power");
			}
			reset();
			
			if(hero.getCurrentManaCrystals() != 1){
				fail("The method useHeroPower in class Hero should decrease the currentManaCrystals by 2 after correct usage");
			}
			if(!hero.isHeroPowerUsed()){
				fail("The method useHeroPower in class Hero should set heroPowerUsed to true after correct usage");
			}
		}
		
		

		@Test(timeout = 3000)
		public void testHeroHasPlayMinion() throws ClassNotFoundException {
			testExistsInClass(Class.forName(heroPath), "playMinion", true,
					void.class, Minion.class);
		}
		
		@Test(timeout = 3000)
		public void testHeroPlayMinionCallsValidateAction() throws Exception {
			Hero hero = new Hero("testHero") {
				@Override
				public void buildDeck() throws IOException {
				}
			};
			ActionValidator validator = new ActionValidator() {
				@Override
				public void validateAttack(Minion m, Hero t) throws CannotAttackException,
						TauntBypassException, NotSummonedException, InvalidTargetException {
				}
				@Override
				public void validateAttack(Minion a, Minion t) throws CannotAttackException,
						TauntBypassException, InvalidTargetException, NotSummonedException {
				}
				@Override
				public void validateTurn(Hero user) throws NotYourTurnException {
					callParameters.add(user);
				}
				@Override
				public void validateManaCost(Card card)
						throws NotEnoughManaException {
				}
				@Override
				public void validatePlayingMinion(Minion m)
						throws FullFieldException {
				}
				@Override
				public void validateUsingHeroPower(Hero h)
						throws NotEnoughManaException,
						HeroPowerAlreadyUsedException {
				}
			};
			reset();
			hero.setValidator(validator);
			Minion m = new Minion("toBeTested",0,Rarity.LEGENDARY,5,6,false,false,true);
			hero.getHand().add(m);
			hero.getField().clear();
			hero.playMinion(m);
			if(callParameters.isEmpty()){
				fail("The method playMinion in class Hero should call the method validateTurn from the validator to check if it's the correct turn");
			}
			if(!callParameters.contains(hero)){
				reset();
				fail("The method playMinion in class Hero should call the method validateTurn from the validator to check if it's the correct turn from the correct hero");
			}
			reset();
		}
		
		@Test(timeout = 3000)
		public void testHeroPlayMinionThrowsNotEnoughManaCrystals() throws Exception {
			Hero hero = new Hero("testHero") {
				@Override
				public void buildDeck() throws IOException {
				}
			};
			ActionValidator validator = new ActionValidator() {
				@Override
				public void validateAttack(Minion m, Hero t) throws CannotAttackException,
						TauntBypassException, NotSummonedException, InvalidTargetException {
				}
				@Override
				public void validateAttack(Minion a, Minion t) throws CannotAttackException,
						TauntBypassException, InvalidTargetException, NotSummonedException {
				}
				@Override
				public void validateTurn(Hero user) throws NotYourTurnException {
				}
				@Override
				public void validateManaCost(Card card)
						throws NotEnoughManaException {
					callParameters.add(card);
				}
				@Override
				public void validatePlayingMinion(Minion m)
						throws FullFieldException {
				}
				@Override
				public void validateUsingHeroPower(Hero h)
						throws NotEnoughManaException,
						HeroPowerAlreadyUsedException {
				}
			};
			reset();
			hero.setValidator(validator);
			Minion m = new Minion("toBeTested",3,Rarity.LEGENDARY,5,6,false,false,true);
			hero.getHand().add(m);
			hero.getField().clear();
			hero.playMinion(m);
			if(callParameters.isEmpty())
				fail("The method playMinion in class Hero should call the method validateManaCost(Card) in class game to make sure that the card can be played.");
			if(!callParameters.contains(m)){
				reset();
				fail("The method playMinion in class Hero should call the method validateManaCost(Card) in class game to make sure that the card can be played.");
			}
		}
		
		@Test(timeout = 3000)
		public void testHeroPlayMinionThrowsFullFieldException() throws Exception {
			Hero hero = new Hero("testHero") {
				@Override
				public void buildDeck() throws IOException {
				}
			};
			ActionValidator validator = new ActionValidator() {
				@Override
				public void validateAttack(Minion m, Hero t) throws CannotAttackException,
						TauntBypassException, NotSummonedException, InvalidTargetException {
				}
				@Override
				public void validateAttack(Minion a, Minion t) throws CannotAttackException,
						TauntBypassException, InvalidTargetException, NotSummonedException {
				}
				@Override
				public void validateTurn(Hero user) throws NotYourTurnException {
				}
				@Override
				public void validateManaCost(Card card)
						throws NotEnoughManaException {
				}
				@Override
				public void validatePlayingMinion(Minion m)
						throws FullFieldException {
					callParameters.add(m);
				}
				@Override
				public void validateUsingHeroPower(Hero h)
						throws NotEnoughManaException,
						HeroPowerAlreadyUsedException {
				}
			};
			reset();
			hero.setValidator(validator);
			Minion m = new Minion("toBeTested",0,Rarity.LEGENDARY,5,6,false,false,true);
			hero.getHand().add(m);
			hero.getField().clear();
			
			for(int i = 0 ; i < 7 ; i++){
				Minion fieldMinion = new Minion("toBeTested",0,Rarity.LEGENDARY,5,6,false,false,true);
				hero.getField().add(fieldMinion);
			}
			hero.playMinion(m);
			if(callParameters.isEmpty())
				fail("The method playMinion in class Hero should call the method validateManaCost(Card) in class game to make sure that the card can be played.");
			if(!callParameters.contains(m)){
				reset();
				fail("The method playMinion in class Hero should call the method validateManaCost(Card) in class game to make sure that the card can be played.");
			}
		}
		
		
		
		@Test(timeout = 3000)
		public void testHeroHasAttackWithMinionOnMinion() throws ClassNotFoundException {
			testExistsInClass(Class.forName(heroPath), "attackWithMinion", true,
					void.class, Minion.class, Minion.class);
		}
		
		@Test(timeout = 3000)
		public void testHeroHasAttackWithMinionOnHero() throws ClassNotFoundException {
			testExistsInClass(Class.forName(heroPath), "attackWithMinion", true,
					void.class, Minion.class, Hero.class);
		}
		
		@Test(timeout = 3000)
		public void testHeroAttackWithMinionOnMinionCallsValidateTurn() throws Exception {
			Hero hero = new Hero("testHero") {
				@Override
				public void buildDeck() throws IOException {
				}
			};
			ActionValidator validator = new ActionValidator() {
				@Override
				public void validateAttack(Minion m, Hero t) throws CannotAttackException,
						TauntBypassException, NotSummonedException, InvalidTargetException {}
				@Override
				public void validateAttack(Minion a, Minion t) throws CannotAttackException,
						TauntBypassException, InvalidTargetException, NotSummonedException {}
				@Override
				public void validateTurn(Hero user) throws NotYourTurnException {
					callParameters.add(this);
					callParameters.add(user);
				}
				@Override
				public void validateManaCost(Card card)
						throws NotEnoughManaException {}
				@Override
				public void validatePlayingMinion(Minion m)
						throws FullFieldException {}
				@Override
				public void validateUsingHeroPower(Hero h)
						throws NotEnoughManaException,
						HeroPowerAlreadyUsedException {}
			};
			Hero hero2 = new Hero("testHero") {
				@Override
				public void buildDeck() throws IOException {
				}
			};
			ActionValidator validator2 = new ActionValidator() {
				@Override
				public void validateAttack(Minion m, Hero t) throws CannotAttackException,
						TauntBypassException, NotSummonedException, InvalidTargetException {}
				@Override
				public void validateAttack(Minion a, Minion t) throws CannotAttackException,
						TauntBypassException, InvalidTargetException, NotSummonedException {}
				@Override
				public void validateTurn(Hero user) throws NotYourTurnException {
					fail("The method validateTurn in class Game should be called with the currentHero not the opponent.");
				}
				@Override
				public void validateManaCost(Card card)
						throws NotEnoughManaException {}
				@Override
				public void validatePlayingMinion(Minion m)
						throws FullFieldException {}
				@Override
				public void validateUsingHeroPower(Hero h)
						throws NotEnoughManaException,
						HeroPowerAlreadyUsedException {}
			};
			
			reset();
			hero.setValidator(validator);
			hero2.setValidator(validator2);
			
			Minion attacker = new Minion("attacker", 2, Rarity.LEGENDARY, 2, 5, false, false, true);
			Minion target = new Minion("target", 3, Rarity.BASIC, 1, 4, false, false, true);
			hero.getField().add(attacker);
			
			hero.attackWithMinion(attacker, target);
			
			if(callParameters.isEmpty())
			{
				fail("The method AttackWithMinion(Minion,Minion) in class Hero should call the method validateTurn(Hero) in its validator");
			}
			if(!(callParameters.contains(validator) && callParameters.contains(hero)))
			{
				reset();
				fail("The method AttackWithMinion(Minion,Minion) in class Hero should call the method validateTurn(Hero) in its validator with the correct objects");
			}
			reset();
		}
		@Test(timeout = 3000)
		public void testHeroAttackWithMinionOnHeroCallsValidateTurn() throws Exception {
			Hero hero = new Hero("testHero") {
				@Override
				public void buildDeck() throws IOException {
				}
			};
			ActionValidator validator = new ActionValidator() {
				@Override
				public void validateAttack(Minion m, Hero t) throws CannotAttackException,
						TauntBypassException, NotSummonedException, InvalidTargetException {}
				@Override
				public void validateAttack(Minion a, Minion t) throws CannotAttackException,
						TauntBypassException, InvalidTargetException, NotSummonedException {}
				@Override
				public void validateTurn(Hero user) throws NotYourTurnException {
					callParameters.add(this);
					callParameters.add(user);
				}
				@Override
				public void validateManaCost(Card card)
						throws NotEnoughManaException {}
				@Override
				public void validatePlayingMinion(Minion m)
						throws FullFieldException {}
				@Override
				public void validateUsingHeroPower(Hero h)
						throws NotEnoughManaException,
						HeroPowerAlreadyUsedException {}
			};
			Hero hero2 = new Hero("testHero") {
				@Override
				public void buildDeck() throws IOException {
				}
			};
			ActionValidator validator2 = new ActionValidator() {
				@Override
				public void validateAttack(Minion m, Hero t) throws CannotAttackException,
						TauntBypassException, NotSummonedException, InvalidTargetException {}
				@Override
				public void validateAttack(Minion a, Minion t) throws CannotAttackException,
						TauntBypassException, InvalidTargetException, NotSummonedException {}
				@Override
				public void validateTurn(Hero user) throws NotYourTurnException {
					fail("The method validateTurn in class Game should be called with the currentHero not the opponent.");
				}
				@Override
				public void validateManaCost(Card card)
						throws NotEnoughManaException {}
				@Override
				public void validatePlayingMinion(Minion m)
						throws FullFieldException {}
				@Override
				public void validateUsingHeroPower(Hero h)
						throws NotEnoughManaException,
						HeroPowerAlreadyUsedException {}
			};
			
			reset();
			hero.setValidator(validator);
			hero2.setValidator(validator2);
			
			Minion attacker = new Minion("attacker", 2, Rarity.LEGENDARY, 2, 5, false, false, true);
			hero.getField().add(attacker);
			
			hero.attackWithMinion(attacker, hero2);
			
			if(callParameters.isEmpty())
			{
				fail("The method AttackWithMinion(Minion,Minion) in class Hero should call the method validateTurn(Hero) in its validator");
			}
			if(!(callParameters.contains(validator) && callParameters.contains(hero)))
			{
				reset();
				fail("The method AttackWithMinion(Minion,Minion) in class Hero should call the method validateTurn(Hero) in its validator with the correct objects");
			}
			reset();
		}
		@Test(timeout = 3000)
		public void testHeroAttackWithMinionOnMinionCallsValidateAttack() throws Exception {
			Hero hero = new Hero("testHero") {
				@Override
				public void buildDeck() throws IOException {
				}
			};
			ActionValidator validator = new ActionValidator() {
				@Override
				public void validateAttack(Minion m, Hero t) throws CannotAttackException,
						TauntBypassException, NotSummonedException, InvalidTargetException {}
				@Override
				public void validateAttack(Minion a, Minion t) throws CannotAttackException,
						TauntBypassException, InvalidTargetException, NotSummonedException {
					callParameters.add(this);
					callParameters.add(a);
					callParameters.add(t);
				}
				@Override
				public void validateTurn(Hero user) throws NotYourTurnException {}
				@Override
				public void validateManaCost(Card card)
						throws NotEnoughManaException {}
				@Override
				public void validatePlayingMinion(Minion m)
						throws FullFieldException {}
				@Override
				public void validateUsingHeroPower(Hero h)
						throws NotEnoughManaException,
						HeroPowerAlreadyUsedException {}
			};
			reset();
			hero.setValidator(validator);
			Minion attacker = new Minion("attacker", 2, Rarity.LEGENDARY, 2, 5, false, false, true);
			Minion target = new Minion("target", 3, Rarity.BASIC, 1, 4, false, false, true);
			hero.getField().add(attacker);
			
			hero.attackWithMinion(attacker, target);
			
			if(callParameters.isEmpty())
			{
				fail("The method AttackWithMinion(Minion,Minion) in class Hero should call the method validateAttack(Minion,Minion) in its validator");
			}
			if(!(callParameters.contains(validator) && callParameters.contains(attacker) && callParameters.contains(target)))
			{
				reset();
				fail("The method AttackWithMinion(Minion,Minion) in class Hero should call the method validateAttack(Minion,Minion) in its validator with the correct objects");
			}
			if(callParameters.indexOf(attacker) >= callParameters.indexOf(target)){
				reset();
				fail("The method AttackWithMinion(Minion,Minion) in class Hero should call the method validateAttack(Minion,Minion) in its validator with the correct objects in the correct order ( attacker first, target second)");
			}
			reset();
		}
		
	
		@Test(timeout = 3000)
		public void testHeroAttackWithMinionOnMinionThrowsCannotAttackException0Attack() throws Exception {
			Hero hero = new Hero("testHero") {
				@Override
				public void buildDeck() throws IOException {
				}
			};
			ActionValidator validator = new ActionValidator() {
				@Override
				public void validateAttack(Minion m, Hero t) throws CannotAttackException,
						TauntBypassException, NotSummonedException, InvalidTargetException {}
				@Override
				public void validateAttack(Minion a, Minion t) throws CannotAttackException,
						TauntBypassException, InvalidTargetException, NotSummonedException {
					callParameters.add(this);
					callParameters.add(a);
					callParameters.add(t);
				}
				@Override
				public void validateTurn(Hero user) throws NotYourTurnException {}
				@Override
				public void validateManaCost(Card card)
						throws NotEnoughManaException {}
				@Override
				public void validatePlayingMinion(Minion m)
						throws FullFieldException {}
				@Override
				public void validateUsingHeroPower(Hero h)
						throws NotEnoughManaException,
						HeroPowerAlreadyUsedException {}
			};
			reset();
			hero.setValidator(validator);
			Minion attacker = new Minion("attacker", 2, Rarity.LEGENDARY, 0, 5, false, false, true);
			Minion target = new Minion("target", 3, Rarity.BASIC, 1, 4, false, false, true);
			hero.getField().add(attacker);
			hero.attackWithMinion(attacker, target);
			
			if(callParameters.isEmpty())
			{
				fail("The method AttackWithMinion(Minion,Minion) in class Hero should call the method validateAttack(Minion,Minion) in its validator");
			}
			if(!(callParameters.contains(validator) && callParameters.contains(attacker) && callParameters.contains(target)))
			{
				reset();
				fail("The method AttackWithMinion(Minion,Minion) in class Hero should call the method validateAttack(Minion,Minion) in its validator with the correct objects");
			}
			if(callParameters.indexOf(attacker) >= callParameters.indexOf(target)){
				reset();
				fail("The method AttackWithMinion(Minion,Minion) in class Hero should call the method validateAttack(Minion,Minion) in its validator with the correct objects in the correct order ( attacker first, target second)");
			}
			reset();
		}
		
		

		@Test(timeout = 3000)
		public void testHeroAttackWithMinionOnHeroThrowsCannotAttackExceptionSleeping() throws Exception {
			Hero hero = new Hero("testHero") {
				@Override
				public void buildDeck() throws IOException {
				}
			};
			Hero target = new Hero("attackedHero") {
				@Override
				public void buildDeck() throws IOException {
				}
			};
			ActionValidator validator = new ActionValidator() {
				@Override
				public void validateAttack(Minion m, Hero t) throws CannotAttackException,
						TauntBypassException, NotSummonedException, InvalidTargetException {
					callParameters.add(this);
					callParameters.add(m);
					callParameters.add(t);
				}
				@Override
				public void validateAttack(Minion a, Minion t) throws CannotAttackException,
						TauntBypassException, InvalidTargetException, NotSummonedException {}
				@Override
				public void validateTurn(Hero user) throws NotYourTurnException {}
				@Override
				public void validateManaCost(Card card)
						throws NotEnoughManaException {}
				@Override
				public void validatePlayingMinion(Minion m)
						throws FullFieldException {}
				@Override
				public void validateUsingHeroPower(Hero h)
						throws NotEnoughManaException,
						HeroPowerAlreadyUsedException {}
			};
			hero.setValidator(validator);
			Minion attacker = new Minion("attacker", 2, Rarity.LEGENDARY, 2, 5, false, false, false);
			hero.getField().add(attacker);
			hero.attackWithMinion(attacker, target);
			if(callParameters.isEmpty())
			{
				fail("The method AttackWithMinion(Minion,Hero) in class Hero should call the method validateAttack(Minion,Hero) in its validator");
			}
			if(!(callParameters.contains(validator) && callParameters.contains(attacker) && callParameters.contains(target)))
			{
				reset();
				fail("The method AttackWithMinion(Minion,Hero) in class Hero should call the method validateAttack(Minion,Hero) in its validator with the correct objects");
			}
			if(callParameters.indexOf(attacker) >= callParameters.indexOf(target)){
				reset();
				fail("The method AttackWithMinion(Minion,Hero) in class Hero should call the method validateAttack(Minion,Hero) in its validator with the correct objects in the correct order ( attacker first, target second)");
			}
			reset();
		}
		
		
		@Test(timeout = 3000)
		public void testHeroAttackWithMinionOnMinionThrowsCannotAttackExceptionAlreadyAttacked() throws Exception {
			Hero hero = new Hero("testHero") {
				@Override
				public void buildDeck() throws IOException {
				}
			};
			ActionValidator validator = new ActionValidator() {
				@Override
				public void validateAttack(Minion m, Hero t) throws CannotAttackException,
						TauntBypassException, NotSummonedException, InvalidTargetException {}
				@Override
				public void validateAttack(Minion a, Minion t) throws CannotAttackException,
						TauntBypassException, InvalidTargetException, NotSummonedException {
					callParameters.add(this);
					callParameters.add(a);
					callParameters.add(t);
				}
				@Override
				public void validateTurn(Hero user) throws NotYourTurnException {}
				@Override
				public void validateManaCost(Card card)
						throws NotEnoughManaException {}
				@Override
				public void validatePlayingMinion(Minion m)
						throws FullFieldException {}
				@Override
				public void validateUsingHeroPower(Hero h)
						throws NotEnoughManaException,
						HeroPowerAlreadyUsedException {}
			};
			reset();
			hero.setValidator(validator);
			Minion attacker = new Minion("attacker", 2, Rarity.LEGENDARY, 2, 5, false, false, true);
			Minion target = new Minion("target", 3, Rarity.BASIC, 1, 4, false, false, true);
			hero.getField().add(attacker);
			attacker.setAttacked(true);
			hero.attackWithMinion(attacker, target);
			if(callParameters.isEmpty())
			{
				fail("The method AttackWithMinion(Minion,Minion) in class Hero should call the method validateAttack(Minion,Minion) in its validator");
			}
			if(!(callParameters.contains(validator) && callParameters.contains(attacker) && callParameters.contains(target)))
			{
				reset();
				fail("The method AttackWithMinion(Minion,Minion) in class Hero should call the method validateAttack(Minion,Minion) in its validator with the correct objects");
			}
			if(callParameters.indexOf(attacker) >= callParameters.indexOf(target)){
				reset();
				fail("The method AttackWithMinion(Minion,Minion) in class Hero should call the method validateAttack(Minion,Minion) in its validator with the correct objects in the correct order ( attacker first, target second)");
			}
			reset();
		}
		
	
		
		
		@Test(timeout = 3000)
		public void testHeroAttackWithMinionOnMinionCallsAttackInMinionNormally() throws Exception {
			Hero hero = new Hero("testHero") {
				@Override
				public void buildDeck() throws IOException {
				}
			};
			ActionValidator validator = new ActionValidator() {
				
				@Override
				public void validateAttack(Minion m, Hero t) throws CannotAttackException,
						TauntBypassException, NotSummonedException, InvalidTargetException {
				}
				@Override
				public void validateAttack(Minion a, Minion t) throws CannotAttackException,
						TauntBypassException, InvalidTargetException, NotSummonedException {
				}
				@Override
				public void validateTurn(Hero user) throws NotYourTurnException {
				}
				@Override
				public void validateManaCost(Card card)
						throws NotEnoughManaException {
				}
				@Override
				public void validatePlayingMinion(Minion m)
						throws FullFieldException {
				}
				@Override
				public void validateUsingHeroPower(Hero h)
						throws NotEnoughManaException,
						HeroPowerAlreadyUsedException {
				}
			};
			reset();
			hero.setValidator(validator);
			Minion attacker = new Minion("attacker", 2, Rarity.LEGENDARY, 2, 5, false, false, true){
				@Override
				public void attack(Minion target) {
					callParameters.add(this);
					callParameters.add(target);
				}
			};
			Minion target = new Minion("target", 3, Rarity.BASIC, 1, 4, false, false, true);
			hero.getField().add(attacker);
			try{
				hero.attackWithMinion(attacker, target);
			}catch(CannotAttackException e){
				fail("The method attackWithMinion(Minion,Minion) should not throw a CannotAttackException if the attacker minion attack conditions are satisfied");
			}
			if(callParameters.isEmpty())
			{
				fail("The method AttackWithMinion(Minion,Minion) in class Hero should call the method attack(Minion) in class Minion");
			}
			if(!(callParameters.contains(attacker) && callParameters.contains(target)))
			{
				reset();
				fail("The method AttackWithMinion(Minion,Minion) in class Hero should call the method attack(Minion) in class Minion with the correct objects");
			}
			if(callParameters.indexOf(attacker) >= callParameters.indexOf(target)){
				reset();
				fail("The method AttackWithMinion(Minion,Minion) in class Hero should call the method attack(Minion) in class Minion with the correct objects in the correct order ( attacker first, target second)");
			}
			reset();
			
		}
		
		@Test(timeout = 3000)
		public void testHeroAttackWithMinionOnHeroCallsAttackInMinionNormally() throws Exception {
			Hero hero = new Hero("testHero") {
				@Override
				public void buildDeck() throws IOException {
				}
			};
			Hero target = new Hero("attackedHero") {
				@Override
				public void buildDeck() throws IOException {
				}
			};
			ActionValidator validator = new ActionValidator() {
				@Override
				public void validateAttack(Minion m, Hero t) throws CannotAttackException,
						TauntBypassException, NotSummonedException, InvalidTargetException {
					callParameters.add(this);
					callParameters.add(m);
					callParameters.add(t);
				}
				@Override
				public void validateAttack(Minion a, Minion t) throws CannotAttackException,
						TauntBypassException, InvalidTargetException, NotSummonedException {}
				@Override
				public void validateTurn(Hero user) throws NotYourTurnException {}
				@Override
				public void validateManaCost(Card card)
						throws NotEnoughManaException {}
				@Override
				public void validatePlayingMinion(Minion m)
						throws FullFieldException {}
				@Override
				public void validateUsingHeroPower(Hero h)
						throws NotEnoughManaException,
						HeroPowerAlreadyUsedException {}
			};
			reset();
			hero.setValidator(validator);
			Minion attacker = new Minion("attacker", 2, Rarity.LEGENDARY, 2, 5, false, false, true){
				@Override
				public void attack(Minion target) {
					callParameters.add(this);
					callParameters.add(target);
				}
			};
			hero.getField().add(attacker);
			try{
				hero.attackWithMinion(attacker, target);
			}catch(CannotAttackException e){
				fail("The method attackWithMinion(Minion,Minion) should not throw a CannotAttackException if the attacker minion attack conditions are satisfied");
			}
			if(callParameters.isEmpty())
			{
				fail("The method AttackWithMinion(Minion,Hero) in class Hero should call the method attack(Hero) in class Minion");
			}
			if(!(callParameters.contains(attacker) && callParameters.contains(target)))
			{
				reset();
				fail("The method AttackWithMinion(Minion,Hero) in class Hero should call the method attack(Hero) in class Minion with the correct objects");
			}
			if(callParameters.indexOf(attacker) >= callParameters.indexOf(target)){
				reset();
				fail("The method AttackWithMinion(Minion,Hero) in class Hero should call the method attack(Hero) in class Minion with the correct objects in the correct order ( attacker first, target second)");
			}
			reset();
		}
		

		@Test(timeout = 3000)
		public void testHeroHasEndTurn() throws ClassNotFoundException {
			testExistsInClass(Class.forName(heroPath), "endTurn", true,
					void.class);
		}
		
		@Test(timeout = 3000)
		public void testHeroEndTurnCallsListenerEndTurn() throws Exception {
			Hero hero = new Hero("testHero") {
				@Override
				public void buildDeck() throws IOException {
				}
			};
			HeroListener listener = new HeroListener() {
				@Override
				public void onHeroDeath() {
				}
				@Override
				public void endTurn() throws FullHandException, CloneNotSupportedException {
					callParameters.add(this);
				}
				@Override
				public void damageOpponent(int amount) {
				}
			};
			hero.setListener(listener);
			
			hero.endTurn();
			if(callParameters.isEmpty())
				fail("The method endTurn in class Hero should call the method endTurn in its listener");
			if(!callParameters.contains(listener)){
				reset();
				fail("The method endTurn in class hero should call the method endTurn in its correct listener");
			}
			reset();
		}
		
		
			
		@Test(timeout = 3000)
		public void testHeroHasDrawCard() throws ClassNotFoundException {
			testExistsInClass(Class.forName(heroPath), "drawCard", true,
					Card.class);
		}
		
		@Test(timeout = 3000)
		public void testHeroDrawCardThrowsFullHandException() throws Exception {
			Hero hero = new Hero("testHero") {
				@Override
				public void buildDeck() throws IOException {
				}
			};
			for(int i = 0 ; i < 10;i++){
				Minion m = new Minion("fillingHand", 10, Rarity.LEGENDARY, 10, 10, true, true, true);
				hero.getHand().add(m);
			}
			for(int i = 0 ; i < 10;i++){
				Minion m = new Minion("fillingDeck", 10, Rarity.LEGENDARY, 10, 10, true, true, true);
				hero.getDeck().add(m);
			}
			
			try{
				hero.drawCard();
				fail("The method drawCard in class Hero should throw a FullHandException if the hero attempts to draw a card while having 10 cards in hand");
			}
			catch(FullHandException e){}
		}
		
		
		@Test(timeout = 3000)
		public void testHeroDrawCardAddsCardToHand() throws Exception {
			Hero hero = new Hero("testHero") {
				@Override
				public void buildDeck() throws IOException {
				}
			};
			for(int i = 0 ; i < 5;i++){
				Minion m = new Minion("fillingHand", 10, Rarity.LEGENDARY, 10, 10, true, true, true);
				hero.getHand().add(m);
			}
			for(int i = 0 ; i < 10;i++){
				Minion m = new Minion("fillingDeck", 10, Rarity.LEGENDARY, 10, 10, true, true, true);
				hero.getDeck().add(m);
			}
			
			try{
				hero.drawCard();
				}
			catch(FullHandException e){
				fail("The method drawCard in class Hero should not throw a FullHandException if the hero attempts to draw a card while having less than 10 cards in hand");
			}
			if(hero.getHand().size() == 5)
				fail("The method drawCard in class Hero should add the card to the hero's hand after drawing it");
			if(hero.getHand().size()!= 6)
				fail("The method drawCard in class Hero should add the card to the hero's hand after drawing it and not affect the hand in any other way");
			if(hero.getDeck().size() == 10)
				fail("The method drawCard in class Hero should remove the card from the hero's deck after drawing it");
			if(hero.getDeck().size()!= 9)
				fail("The method drawCard in class Hero should remove the card from the hero's deck after drawing it and not affect the deck in any other way");
		}
		
		@Test(timeout = 3000)
		public void testHeroDrawCardFatigueLogic() throws Exception {
			Hero hero = new Hero("testHero") {
				@Override
				public void buildDeck() throws IOException {
				}
			};
			Minion m = new Minion("fillingDeck", 10, Rarity.LEGENDARY, 10, 10, true, true, true);
			hero.getDeck().add(m);
			ArrayList<Integer> correctHeroHPValues = new ArrayList<Integer>();
			correctHeroHPValues.add(30);
			correctHeroHPValues.add(29);
			correctHeroHPValues.add(27);
			correctHeroHPValues.add(24);
			correctHeroHPValues.add(20);
			ArrayList<Integer> correctFatigueValues = new ArrayList<Integer>();
			correctFatigueValues.add(1);
			correctFatigueValues.add(2);
			correctFatigueValues.add(3);
			correctFatigueValues.add(4);
			correctFatigueValues.add(5);
			
			ArrayList<Integer> testHeroHPValues = new ArrayList<Integer>();
			
			
			ArrayList<Integer> testFatigueValues = new ArrayList<Integer>();
			
			
			for(int i = 0; i < 5 ; i++){
				hero.drawCard();
				Field f = Hero.class.getDeclaredField("fatigueDamage");
				f.setAccessible(true);
				int fatigue = f.getInt(hero);
				testFatigueValues.add(fatigue);
				testHeroHPValues.add(hero.getCurrentHP());
			}
			assertEquals("The method drawCard should update the fatigueDamage variable in class Hero correctly if the deck no longer contains cards",correctFatigueValues,testFatigueValues);
			assertEquals("The method drawCard should update the currentHP variable in class Hero correctly if the deck no longer contains cards",correctHeroHPValues,testHeroHPValues);
			
			
		}
		
		
			
			@Test(timeout = 3000)
			public void testChromaggusSpecialEffect() throws Exception {
				Hero justahn = new Hero("testHero") {
					@Override
					public void buildDeck() throws IOException {
					}
				};
				ActionValidator validator = new ActionValidator() {
					@Override
					public void validateAttack(Minion m, Hero t) throws CannotAttackException,
							TauntBypassException, NotSummonedException, InvalidTargetException {
					}
					@Override
					public void validateAttack(Minion a, Minion t) throws CannotAttackException,
							TauntBypassException, InvalidTargetException, NotSummonedException {
					}
					@Override
					public void validateTurn(Hero user)
							throws NotYourTurnException {
					}
					@Override
					public void validateManaCost(Card card)
							throws NotEnoughManaException {
					}
					@Override
					public void validatePlayingMinion(Minion m)
							throws FullFieldException {
					}
					@Override
					public void validateUsingHeroPower(Hero h)
							throws NotEnoughManaException,
							HeroPowerAlreadyUsedException {
					}
				};
				justahn.setValidator(validator);
				Card cardToBeDuplicated = new Card("toBeDuplicated",4,Rarity.RARE) {
				};
				justahn.getDeck().add(cardToBeDuplicated);
				Card fillingHand1 = new Card("fillingHand",3,Rarity.BASIC) {
				};
				Card fillingHand2 = new Card("fillingHand",2,Rarity.COMMON) {
				};
				justahn.getHand().add(fillingHand1);
				justahn.getHand().add(fillingHand2);
				
				Minion chromaggus = new Minion("Chromaggus", 8, Rarity.LEGENDARY, 6, 8, false, false, false);
				
				justahn.getHand().add(chromaggus);
				justahn.setCurrentManaCrystals(10);
				justahn.playMinion(chromaggus);
				if(!justahn.getField().contains(chromaggus))
					justahn.getField().add(chromaggus);
				justahn.setCurrentManaCrystals(10);
				
				justahn.drawCard();
				int toBeDuplicatedCount = 0;
				int fillingHandCount = 0;
				for(int i = 0 ; i < justahn.getHand().size();i++){
					if(justahn.getHand().get(i).getName().equals("toBeDuplicated"))
						toBeDuplicatedCount++;
					if(justahn.getHand().get(i).getName().equals("fillingHand"))
						fillingHandCount++;
				}
				if(toBeDuplicatedCount != 2)
					fail("Whenever a hero draws a card while the minion Chromaggus is on the ground, that card should be duplicated in the hero's hand.");
				if(fillingHandCount != 2)
					fail("Chromaggus effect should apply on the last card drawn only, the rest of the hero's hand should remain the same.");
				if(toBeDuplicatedCount+fillingHandCount != justahn.getHand().size())
					fail("Chromaggus should not any other cards to the hero's hand other than the latest drawn card.");
				if(justahn.getHand().get(justahn.getHand().size()-1)== justahn.getHand().get(justahn.getHand().size()-2))
					fail("Chromaggus should add a cloned copy of the card not the card itself.");
			}
			@Test(timeout = 3000)
			public void testChromaggusSpecialEffectOnFullHand() throws Exception {
				Hero justahn = new Hero("testHero") {
					@Override
					public void buildDeck() throws IOException {
					}
				};
				ActionValidator validator = new ActionValidator() {
					@Override
					public void validateAttack(Minion m, Hero t) throws CannotAttackException,
							TauntBypassException, NotSummonedException, InvalidTargetException {
					}
					@Override
					public void validateAttack(Minion a, Minion t) throws CannotAttackException,
							TauntBypassException, InvalidTargetException, NotSummonedException {
					}
					@Override
					public void validateTurn(Hero user)
							throws NotYourTurnException {
					}
					@Override
					public void validateManaCost(Card card)
							throws NotEnoughManaException {
					}
					@Override
					public void validatePlayingMinion(Minion m)
							throws FullFieldException {
					}
					@Override
					public void validateUsingHeroPower(Hero h)
							throws NotEnoughManaException,
							HeroPowerAlreadyUsedException {
					}
				};
				justahn.setValidator(validator);
				Card cardToBeDuplicated = new Card("toBeDuplicated",4,Rarity.RARE) {
				};
				justahn.getDeck().add(cardToBeDuplicated);
				
				Minion chromaggus = new Minion("Chromaggus", 8, Rarity.LEGENDARY, 6, 8, false, false, false);
				justahn.getHand().add(chromaggus);
				justahn.setCurrentManaCrystals(10);
				justahn.playMinion(chromaggus);
				if(!justahn.getField().contains(chromaggus))
					justahn.getField().add(chromaggus);
				justahn.setCurrentManaCrystals(10);
				
				for(int i = 0 ; i < 9 ; i++){	
					Card fillingHand = new Card("fillingHand",3,Rarity.BASIC) {};
					justahn.getHand().add(fillingHand);
				}
				justahn.drawCard();
				int toBeDuplicatedCount = 0;
				int fillingHandCount = 0;
				for(int i = 0 ; i < justahn.getHand().size();i++){
					if(justahn.getHand().get(i).getName().equals("toBeDuplicated"))
						toBeDuplicatedCount++;
					if(justahn.getHand().get(i).getName().equals("fillingHand"))
						fillingHandCount++;
				}
				if(justahn.getHand().size() == 11)
					fail("Whenever a hero draws a card while Chromaggus is on the ground and the hero's hand get to be full after drawing the card, the effect of Chromaggus should not apply.");
				if(toBeDuplicatedCount != 1)
					fail("Whenever a hero draws a card while Chromaggus is on the ground and the hero's hand get to be full after drawing the card, the effect of Chromaggus should not apply.");
				if(fillingHandCount != 9)
					fail("Chromaggus effect should apply on the last card drawn only, the rest of the hero's hand should remain the same.");
				if(toBeDuplicatedCount+fillingHandCount != justahn.getHand().size())
					fail("Chromaggus should not any other cards to the hero's hand other than the latest drawn card.");
				
			}
			@Test(timeout = 3000)
			public void testMageKalycgosSpecialEffectAOESpell() throws Exception {
				Mage diakao = new Mage();
				ActionValidator validator = new ActionValidator() {
					@Override
					public void validateAttack(Minion m, Hero t) throws CannotAttackException,
							TauntBypassException, NotSummonedException, InvalidTargetException {
					}
					@Override
					public void validateAttack(Minion a, Minion t) throws CannotAttackException,
							TauntBypassException, InvalidTargetException, NotSummonedException {
					}
					@Override
					public void validateTurn(Hero user)
							throws NotYourTurnException {
					}
					@Override
					public void validateManaCost(Card card)
							throws NotEnoughManaException {
					}
					@Override
					public void validatePlayingMinion(Minion m)
							throws FullFieldException {
					}
					@Override
					public void validateUsingHeroPower(Hero h)
							throws NotEnoughManaException,
							HeroPowerAlreadyUsedException {
					}
				};
				diakao.setValidator(validator);
				diakao.setCurrentManaCrystals(10);
				
				Flamestrike flamestrike = new Flamestrike();
				Minion kalycgos = new Minion("Kalycgos", 10, Rarity.LEGENDARY, 4, 12, false, false, false);
				kalycgos.setListener(diakao);
				diakao.getHand().add(kalycgos);
				diakao.setCurrentManaCrystals(10);
				diakao.playMinion(kalycgos);
				if(!diakao.getField().contains(kalycgos))
					diakao.getField().add(kalycgos);
				diakao.setCurrentManaCrystals(10);
				diakao.castSpell(flamestrike, new ArrayList<Minion>());
				if(flamestrike.getManaCost() == 7)
					fail("Whenever a Mage casts a spell while Kalycgos is on the mage's field, the spell's mana cost should be reduced by 4.");
				if(flamestrike.getManaCost()!= 3)
					fail("Whenever a Mage casts a spell while Kalycgos is on the mage's field, the spell's mana cost should be reduced by the correct value which is 4.");
				if(diakao.getCurrentManaCrystals() == 3)
					fail("Whenever a Mage casts a spell while Kalycgos is on the mage's field, the spell's mana cost should be reduced before casting it.");
				if(diakao.getCurrentManaCrystals() != 7)
					fail("Whenever a Mage casts a spell while Kalycgos is on the mage's field, the spell's mana cost should be reduced by 4 before casting it.");
			}
			
			
			
			
			@Test(timeout = 3000)
			public void testMageKalycgosSpecialEffectMinionTargetSpellPyroblast() throws Exception {
				Mage diakao = new Mage();
				ActionValidator validator = new ActionValidator() {
					@Override
					public void validateAttack(Minion m, Hero t) throws CannotAttackException,
							TauntBypassException, NotSummonedException, InvalidTargetException {
					}
					@Override
					public void validateAttack(Minion a, Minion t) throws CannotAttackException,
							TauntBypassException, InvalidTargetException, NotSummonedException {
					}
					@Override
					public void validateTurn(Hero user)
							throws NotYourTurnException {
					}
					@Override
					public void validateManaCost(Card card)
							throws NotEnoughManaException {
					}
					@Override
					public void validatePlayingMinion(Minion m)
							throws FullFieldException {
					}
					@Override
					public void validateUsingHeroPower(Hero h)
							throws NotEnoughManaException,
							HeroPowerAlreadyUsedException {
					}
				};
				diakao.setValidator(validator);
				diakao.setCurrentManaCrystals(10);
				
				Pyroblast pyroblast = new Pyroblast();
				Minion kalycgos = new Minion("Kalycgos", 10, Rarity.LEGENDARY, 4, 12, false, false, false);
				kalycgos.setListener(diakao);
				diakao.getHand().add(kalycgos);
				diakao.setCurrentManaCrystals(10);
				diakao.playMinion(kalycgos);
				if(!diakao.getField().contains(kalycgos))
					diakao.getField().add(kalycgos);
				diakao.setCurrentManaCrystals(10);
				diakao.castSpell(pyroblast, kalycgos);
				if(pyroblast.getManaCost() == 10)
					fail("Whenever a Mage casts a spell while Kalycgos is on the mage's field, the spell's mana cost should be reduced by 4.");
				if(pyroblast.getManaCost()!= 6)
					fail("Whenever a Mage casts a spell while Kalycgos is on the mage's field, the spell's mana cost should be reduced by the correct value which is 4.");
				if(diakao.getCurrentManaCrystals() == 0)
					fail("Whenever a Mage casts a spell while Kalycgos is on the mage's field, the spell's mana cost should be reduced before casting it.");
				if(diakao.getCurrentManaCrystals() != 4)
					fail("Whenever a Mage casts a spell while Kalycgos is on the mage's field, the spell's mana cost should be reduced by 4 before casting it.");
			
			}
			

			@Test(timeout = 3000)
			public void testMageKalycgosSpecialEffectMinionTargetSpellPolymorph() throws Exception {
				Mage diakao = new Mage();
				ActionValidator validator = new ActionValidator() {
					@Override
					public void validateAttack(Minion m, Hero t) throws CannotAttackException,
							TauntBypassException, NotSummonedException, InvalidTargetException {
					}
					@Override
					public void validateAttack(Minion a, Minion t) throws CannotAttackException,
							TauntBypassException, InvalidTargetException, NotSummonedException {
					}
					@Override
					public void validateTurn(Hero user)
							throws NotYourTurnException {
					}
					@Override
					public void validateManaCost(Card card)
							throws NotEnoughManaException {
					}
					@Override
					public void validatePlayingMinion(Minion m)
							throws FullFieldException {
					}
					@Override
					public void validateUsingHeroPower(Hero h)
							throws NotEnoughManaException,
							HeroPowerAlreadyUsedException {
					}
				};
				diakao.setValidator(validator);
				diakao.setCurrentManaCrystals(10);
				
				Polymorph polymorph = new Polymorph();
				Minion kalycgos = new Minion("Kalycgos", 10, Rarity.LEGENDARY, 4, 12, false, false, false);
				kalycgos.setListener(diakao);
				diakao.getHand().add(kalycgos);
				diakao.setCurrentManaCrystals(10);
				diakao.playMinion(kalycgos);
				if(!diakao.getField().contains(kalycgos))
					diakao.getField().add(kalycgos);
				diakao.setCurrentManaCrystals(10);
				diakao.castSpell(polymorph, kalycgos);
				if(polymorph.getManaCost() == 4)
					fail("Whenever a Mage casts a spell while Kalycgos is on the mage's field, the spell's mana cost should be reduced by 4.");
				if(polymorph.getManaCost()!= 0)
					fail("Whenever a Mage casts a spell while Kalycgos is on the mage's field, the spell's mana cost should be reduced by the correct value which is 4.");
				if(diakao.getCurrentManaCrystals() == 6)
					fail("Whenever a Mage casts a spell while Kalycgos is on the mage's field, the spell's mana cost should be reduced before casting it.");
				if(diakao.getCurrentManaCrystals() != 10)
					fail("Whenever a Mage casts a spell while Kalycgos is on the mage's field, the spell's mana cost should be reduced by 4 before casting it.");
			
			}
			
			@Test(timeout = 3000)
			public void testMageKalycgosSpecialEffectHeroTargetSpell() throws Exception {
				Mage diakao = new Mage();
				ActionValidator validator = new ActionValidator() {
					@Override
					public void validateAttack(Minion m, Hero t) throws CannotAttackException,
							TauntBypassException, NotSummonedException, InvalidTargetException {
					}
					@Override
					public void validateAttack(Minion a, Minion t) throws CannotAttackException,
							TauntBypassException, InvalidTargetException, NotSummonedException {
					}
					@Override
					public void validateTurn(Hero user)
							throws NotYourTurnException {
						
					}
					@Override
					public void validateManaCost(Card card)
							throws NotEnoughManaException {
						
					}
					@Override
					public void validatePlayingMinion(Minion m)
							throws FullFieldException {
						
					}
					@Override
					public void validateUsingHeroPower(Hero h)
							throws NotEnoughManaException,
							HeroPowerAlreadyUsedException {
						
					}
				};
				diakao.setValidator(validator);
				diakao.setCurrentManaCrystals(10);
				
				Pyroblast pyroblast = new Pyroblast();
				Minion kalycgos = new Minion("Kalycgos", 10, Rarity.LEGENDARY, 4, 12, false, false, false);
				kalycgos.setListener(diakao);
				diakao.getHand().add(kalycgos);
				diakao.setCurrentManaCrystals(10);
				diakao.playMinion(kalycgos);
				if(!diakao.getField().contains(kalycgos))
					diakao.getField().add(kalycgos);
				diakao.setCurrentManaCrystals(10);
				diakao.castSpell(pyroblast, diakao);
				if(pyroblast.getManaCost() == 10)
					fail("Whenever a Mage casts a spell while Kalycgos is on the mage's field, the spell's mana cost should be reduced by 4.");
				if(pyroblast.getManaCost()!= 6)
					fail("Whenever a Mage casts a spell while Kalycgos is on the mage's field, the spell's mana cost should be reduced by the correct value which is 4.");
				if(diakao.getCurrentManaCrystals() == 0)
					fail("Whenever a Mage casts a spell while Kalycgos is on the mage's field, the spell's mana cost should be reduced before casting it.");
				if(diakao.getCurrentManaCrystals() != 4)
					fail("Whenever a Mage casts a spell while Kalycgos is on the mage's field, the spell's mana cost should be reduced by 4 before casting it.");
			
			}
			
			@Test(timeout = 3000)
			public void testPriestProphetVelenSpecialEffectOnMinion() throws Exception {
				Priest valerienne = new Priest();
				Minion prophetVelen = new Minion("Prophet Velen", 7, Rarity.LEGENDARY, 7, 7, false, false, false);
				valerienne.getHand().add(prophetVelen);
				prophetVelen.setListener(valerienne);
				ActionValidator validator = new ActionValidator() {
					@Override
					public void validateAttack(Minion m, Hero t) throws CannotAttackException,
							TauntBypassException, NotSummonedException, InvalidTargetException {
					}
					@Override
					public void validateAttack(Minion a, Minion t) throws CannotAttackException,
							TauntBypassException, InvalidTargetException, NotSummonedException {
					}
					@Override
					public void validateTurn(Hero user)
							throws NotYourTurnException {
					}
					@Override
					public void validateManaCost(Card card)
							throws NotEnoughManaException {
					}
					@Override
					public void validatePlayingMinion(Minion m)
							throws FullFieldException {
					}
					@Override
					public void validateUsingHeroPower(Hero h)
							throws NotEnoughManaException,
							HeroPowerAlreadyUsedException {
					}
				};
				valerienne.setValidator(validator);
				valerienne.setCurrentManaCrystals(10);
				valerienne.playMinion(prophetVelen);
				if(!valerienne.getField().contains(prophetVelen))
					valerienne.getField().add(prophetVelen);
				Minion toBeTest = new Minion("testMinion", 5, Rarity.RARE, 2, 10, false, false, false){
					@Override
					public void setCurrentHP(int currentHP) {
						if(currentHP == 12)
							fail("Whenever a priest attempts to use their hero power on a minion while Prophet Velen is on the field, the hero power should heal for 8 instead of 2.");
						if(currentHP != 18)
							fail("Whenever a priest attempts to use their hero power on a minion while Prophet Velen is on the field, the hero power should heal for 8 instead of 2.");
						super.setCurrentHP(currentHP);
					}
				};
				valerienne.useHeroPower(toBeTest);
				
			}
			
			@Test(timeout = 3000)
			public void testPriestProphetVelenSpecialEffectOnHero() throws Exception {
				Priest valerienne = new Priest(){
					@Override
					public void setCurrentHP(int currentHP) {
						if(currentHP == 32)
							fail("Whenever a priest attempts to use their hero power on a hero while Prophet Velen is on the field, the hero power should heal for 8 instead of 2.");
						if(currentHP != 38)
							fail("Whenever a priest attempts to use their hero power on a hero while Prophet Velen is on the field, the hero power should heal for 8 instead of 2.");
						super.setCurrentHP(currentHP);
					}
				};
				Minion prophetVelen = new Minion("Prophet Velen", 7, Rarity.LEGENDARY, 7, 7, false, false, false);
				valerienne.getHand().add(prophetVelen);
				prophetVelen.setListener(valerienne);
				ActionValidator validator = new ActionValidator() {
					@Override
					public void validateAttack(Minion m, Hero t) throws CannotAttackException,
							TauntBypassException, NotSummonedException, InvalidTargetException {
					}
					@Override
					public void validateAttack(Minion a, Minion t) throws CannotAttackException,
							TauntBypassException, InvalidTargetException, NotSummonedException {
					}
					@Override
					public void validateTurn(Hero user)
							throws NotYourTurnException {
					}
					@Override
					public void validateManaCost(Card card)
							throws NotEnoughManaException {
					}
					@Override
					public void validatePlayingMinion(Minion m)
							throws FullFieldException {
					}
					@Override
					public void validateUsingHeroPower(Hero h)
							throws NotEnoughManaException,
							HeroPowerAlreadyUsedException {
					}
				};
				valerienne.setValidator(validator);
				valerienne.setCurrentManaCrystals(10);
				valerienne.playMinion(prophetVelen);
				if(!valerienne.getField().contains(prophetVelen))
					valerienne.getField().add(prophetVelen);
				valerienne.useHeroPower(valerienne);
				
			}
			
			@Test(timeout = 3000)
			public void testWarlockWilfredFizzlebangSpecialEffectMinion() throws Exception {
				Warlock kvit = new Warlock();
				kvit.getHand().add(new Icehowl());
				kvit.getDeck().clear();
				ActionValidator validator = new ActionValidator() {
					@Override
					public void validateAttack(Minion m, Hero t) throws CannotAttackException,
							TauntBypassException, NotSummonedException, InvalidTargetException {
					}
					@Override
					public void validateAttack(Minion a, Minion t) throws CannotAttackException,
							TauntBypassException, InvalidTargetException, NotSummonedException {
					}
					@Override
					public void validateTurn(Hero user)
							throws NotYourTurnException {
					}
					@Override
					public void validateManaCost(Card card)
							throws NotEnoughManaException {
					}
					@Override
					public void validatePlayingMinion(Minion m)
							throws FullFieldException {
					}
					@Override
					public void validateUsingHeroPower(Hero h)
							throws NotEnoughManaException,
							HeroPowerAlreadyUsedException {
					}
				};
				kvit.setValidator(validator);
				Minion wilfredFizzlebang = new Minion("Wilfred Fizzlebang", 5, Rarity.LEGENDARY, 4, 4, false, false, false);
				wilfredFizzlebang.setListener(kvit);
				kvit.setCurrentManaCrystals(10);
				kvit.getHand().add(wilfredFizzlebang);
				kvit.playMinion(wilfredFizzlebang);
				if(!kvit.getField().contains(wilfredFizzlebang))
					kvit.getField().add(wilfredFizzlebang);
				kvit.setCurrentManaCrystals(10);
				Minion toBeTested = new Minion("toBeTested", 2, Rarity.BASIC, 4, 5, false, false, false);
				kvit.getDeck().add(toBeTested);
				kvit.useHeroPower();
				if(toBeTested.getManaCost() == 2)
					fail("Whenever a Warlock draws a Minion while Wilfred Fizzlebang is on the warlock's field, the Minion's mana cost should be changed to 0.");
				if(toBeTested.getManaCost() != 0)
					fail("Whenever a Warlock draws a Minion while Wilfred Fizzlebang is on the warlock's field, the Minion's mana cost should be changed correctly to 0.");
				
			}
			
			@Test(timeout = 3000)
			public void testWarlockWilfredFizzlebangSpecialEffectSpell() throws Exception {
				Warlock kvit = new Warlock();
				kvit.getHand().add(new Icehowl());
				kvit.getDeck().clear();
				ActionValidator validator = new ActionValidator() {
					@Override
					public void validateAttack(Minion m, Hero t) throws CannotAttackException,
							TauntBypassException, NotSummonedException, InvalidTargetException {
					}
					@Override
					public void validateAttack(Minion a, Minion t) throws CannotAttackException,
							TauntBypassException, InvalidTargetException, NotSummonedException {
					}
					@Override
					public void validateTurn(Hero user)
							throws NotYourTurnException {
					}
					@Override
					public void validateManaCost(Card card)
							throws NotEnoughManaException {
					}
					@Override
					public void validatePlayingMinion(Minion m)
							throws FullFieldException {
					}
					@Override
					public void validateUsingHeroPower(Hero h)
							throws NotEnoughManaException,
							HeroPowerAlreadyUsedException {
					}
				};
				kvit.setValidator(validator);
				Minion wilfredFizzlebang = new Minion("Wilfred Fizzlebang", 5, Rarity.LEGENDARY, 4, 4, false, false, false);
				wilfredFizzlebang.setListener(kvit);
				kvit.setCurrentManaCrystals(10);
				kvit.getHand().add(wilfredFizzlebang);
				kvit.playMinion(wilfredFizzlebang);
				if(!kvit.getField().contains(wilfredFizzlebang))
					kvit.getField().add(wilfredFizzlebang);
				kvit.setCurrentManaCrystals(10);
				Spell toBeTested = new Spell("toBeTested", 2, Rarity.BASIC){
					
				};
				kvit.getDeck().add(toBeTested);
				kvit.drawCard();
				if(toBeTested.getManaCost() == 0)
					fail("Whenever a Warlock draws a Spell while Wilfred Fizzlebang is on the warlock's field, the Spell's mana cost should NOT be changed to 0.");
				if(toBeTested.getManaCost() != 2)
					fail("Whenever a Warlock draws a Spell while Wilfred Fizzlebang is on the warlock's field, the Spell's mana cost should NOT be changed.");
				
				int toBeTestedCount = 0;
				int toBeTested0ManaCount = 0;
				for(int i = 0 ;i < kvit.getHand().size(); i++){
					if(kvit.getHand().get(i).getName().equals("toBeTested")){
						toBeTestedCount ++;
						
					}
					if(kvit.getHand().get(i).getManaCost() == 0)
						toBeTested0ManaCount ++;
				}
				if(toBeTestedCount != 1)
					fail("Whenever a Warlock draws a Minion while Wilfred Fizzlebang is on the warlock's field it should activate its special effect.");
				if(toBeTested0ManaCount!= 0){
					fail("Whenever a Warlock draws a Minion while Wilfred Fizzlebang is on the warlock's field it should activate its special effect.");
				}
			}
			
			@Test(timeout = 3000)
			public void testWarlockWilfredFizzlebangAndChrommagusSpecialEffectMinion() throws Exception {
				Warlock kvit = new Warlock();
				kvit.getDeck().clear();
				kvit.getHand().add(new Icehowl());
				ActionValidator validator = new ActionValidator() {
					@Override
					public void validateAttack(Minion m, Hero t) throws CannotAttackException,
							TauntBypassException, NotSummonedException, InvalidTargetException {}
					@Override
					public void validateAttack(Minion a, Minion t) throws CannotAttackException,
							TauntBypassException, InvalidTargetException, NotSummonedException {}
					@Override
					public void validateTurn(Hero user)
							throws NotYourTurnException {}
					@Override
					public void validateManaCost(Card card)
							throws NotEnoughManaException {}
					@Override
					public void validatePlayingMinion(Minion m)
							throws FullFieldException {}
					@Override
					public void validateUsingHeroPower(Hero h)
							throws NotEnoughManaException,
							HeroPowerAlreadyUsedException {}
				};
				kvit.setValidator(validator);
				Minion wilfredFizzlebang = new Minion("Wilfred Fizzlebang", 5, Rarity.LEGENDARY, 4, 4, false, false, false);
				wilfredFizzlebang.setListener(kvit);
				Minion chromaggus = new Minion("Chromaggus", 8, Rarity.LEGENDARY, 6, 8, false, false, false);
				chromaggus.setListener(kvit);
				kvit.setCurrentManaCrystals(10);
				kvit.getHand().add(wilfredFizzlebang);
				kvit.playMinion(wilfredFizzlebang);
				kvit.setCurrentManaCrystals(10);
				kvit.getHand().add(chromaggus);
				kvit.playMinion(chromaggus);
				kvit.setCurrentManaCrystals(10);
				if(!kvit.getField().contains(chromaggus))
					kvit.getField().add(chromaggus);
				if(!kvit.getField().contains(wilfredFizzlebang))
					kvit.getField().add(wilfredFizzlebang);
				Minion toBeTested = new Minion("toBeTested", 2, Rarity.BASIC, 4, 5, false, false, false);
				kvit.getDeck().add(toBeTested);
				kvit.useHeroPower();
				if(toBeTested.getManaCost() == 2)
					fail("Whenever a Warlock draws a Minion while Wilfred Fizzlebang is on the warlock's field, the Minion's mana cost should be changed to 0.");
			
				if(toBeTested.getManaCost() != 0)
					fail("Whenever a Warlock draws a Minion while Wilfred Fizzlebang is on the warlock's field, the Minion's mana cost should be changed correctly to 0.");
				int toBeTestedCount = 0;
				int toBeTested0ManaCount = 0;
				
				for(int i = 0 ;i < kvit.getHand().size(); i++){
					if(kvit.getHand().get(i).getName().equals("toBeTested")){
						toBeTestedCount++;
						
					}
					if(kvit.getHand().get(i).getManaCost() == 0)
						toBeTested0ManaCount++;
				}
				if(toBeTestedCount != 2)
					fail("Whenever a Warlock draws a Minion while Wilfred Fizzlebang and Chromaggus are both on the warlock's field they should both activate their special effect.");
				if(toBeTested0ManaCount!= 2){
					fail("Whenever a Warlock draws a Minion while Wilfred Fizzlebang and Chromaggus are both on the warlock's field they should both activate their special effect, Wilfred Fizzlebang first then Chromaggus.");
				}
				if(kvit.getHand().get(kvit.getHand().size()-1)== kvit.getHand().get(kvit.getHand().size()-2))
					fail("Chromaggus should add a cloned copy of the card not the card itself.");
		
			}
			
			@Test(timeout = 3000)
			public void testWarlockWilfredFizzlebangAndChrommagusSpecialEffectSpell() throws Exception {
				Warlock kvit = new Warlock();
				kvit.getDeck().clear();
				kvit.getHand().add(new Icehowl());
				ActionValidator validator = new ActionValidator() {
					@Override
					public void validateAttack(Minion m, Hero t) throws CannotAttackException,
							TauntBypassException, NotSummonedException, InvalidTargetException {}
					@Override
					public void validateAttack(Minion a, Minion t) throws CannotAttackException,
							TauntBypassException, InvalidTargetException, NotSummonedException {}
					@Override
					public void validateTurn(Hero user)
							throws NotYourTurnException {}
					@Override
					public void validateManaCost(Card card)
							throws NotEnoughManaException {}
					@Override
					public void validatePlayingMinion(Minion m)
							throws FullFieldException {}
					@Override
					public void validateUsingHeroPower(Hero h)
							throws NotEnoughManaException,
							HeroPowerAlreadyUsedException {}
				};
				kvit.setValidator(validator);
				Minion wilfredFizzlebang = new Minion("Wilfred Fizzlebang", 5, Rarity.LEGENDARY, 4, 4, false, false, false);
				wilfredFizzlebang.setListener(kvit);
				Minion chromaggus = new Minion("Chromaggus", 8, Rarity.LEGENDARY, 6, 8, false, false, false);
				chromaggus.setListener(kvit);
				kvit.setCurrentManaCrystals(10);
				kvit.getHand().add(wilfredFizzlebang);
				kvit.playMinion(wilfredFizzlebang);
				kvit.setCurrentManaCrystals(10);
				kvit.getHand().add(chromaggus);
				kvit.playMinion(chromaggus);
				kvit.setCurrentManaCrystals(10);
				if(!kvit.getField().contains(chromaggus))
					kvit.getField().add(chromaggus);
				if(!kvit.getField().contains(wilfredFizzlebang))
					kvit.getField().add(wilfredFizzlebang);
				Spell toBeTested = new Spell("toBeTested", 4, Rarity.BASIC){
					
				};
				kvit.getDeck().add(toBeTested);
				kvit.useHeroPower();
				if(toBeTested.getManaCost() == 0)
					fail("Whenever a Warlock draws a Spell while Wilfred Fizzlebang is on the warlock's field, the Spell mana cost should NOT be changed to 0.");
			
				if(toBeTested.getManaCost() != 4)
					fail("Whenever a Warlock draws a Spell while Wilfred Fizzlebang is on the warlock's field, the Spell mana cost should NOT be changed correctly to 0.");
				int toBeTestedCount = 0;
				int toBeTested0ManaCount = 0;
				
				for(int i = 0 ;i < kvit.getHand().size(); i++){
					if(kvit.getHand().get(i).getName().equals("toBeTested")){
						toBeTestedCount++;
						
					}
					if(kvit.getHand().get(i).getManaCost() == 0)
						toBeTested0ManaCount++;
				}
				if(toBeTestedCount != 2)
					fail("Whenever a Warlock draws a Spell while Wilfred Fizzlebang and Chromaggus are both on the warlock's field they should both activate their special effect.");
				if(toBeTested0ManaCount!= 0){
					fail("Whenever a Warlock draws a Spell while Wilfred Fizzlebang and Chromaggus are both on the warlock's field they should both activate their special effect, Wilfred Fizzlebang first (Not affecting the spell) then Chromaggus.");
				}
			}
			
			@Test(timeout = 3000)
			public void testPriestMinionsHaveListeners() throws Exception {
				Priest valerienne = new Priest();
				for(int i = 0 ; i < valerienne.getDeck().size();i++){
					if(valerienne.getDeck().get(i) instanceof Minion)
					{
						Minion minion = (Minion)(valerienne.getDeck().get(i));
						Field f = Minion.class.getDeclaredField("listener");
						f.setAccessible(true);
						if(f.get(minion) == null)
							fail("The minions in the Warlock's deck should have their Warlock as their minionListener");
						if(f.get(minion) != valerienne)
							fail("The minions in the Warlock's deck should have their Warlock as their minionListener");
					}
				}
			}
			
			@Test(timeout = 3000)
			public void testHunterMinionsHaveListeners() throws Exception {
				Hunter alluka = new Hunter();
				for(int i = 0 ; i < alluka.getDeck().size();i++){
					if(alluka.getDeck().get(i) instanceof Minion)
					{
						Minion minion = (Minion)(alluka.getDeck().get(i));
						Field f = Minion.class.getDeclaredField("listener");
						f.setAccessible(true);
						if(f.get(minion) == null)
							fail("The minions in the Warlock's deck should have their Warlock as their minionListener");
						if(f.get(minion) != alluka)
							fail("The minions in the Warlock's deck should have their Warlock as their minionListener");
					}
				}
			}
			
			
			@Test(timeout = 3000)
			public void testPaladinMinionsHaveListeners() throws Exception {
				Paladin justahn = new Paladin();
				for(int i = 0 ; i < justahn.getDeck().size();i++){
					if(justahn.getDeck().get(i) instanceof Minion)
					{
						Minion minion = (Minion)(justahn.getDeck().get(i));
						Field f = Minion.class.getDeclaredField("listener");
						f.setAccessible(true);
						if(f.get(minion) == null)
							fail("The minions in the Warlock's deck should have their Warlock as their minionListener");
						if(f.get(minion) != justahn)
							fail("The minions in the Warlock's deck should have their Warlock as their minionListener");
					}
				}
			}
			
			
			@Test(timeout = 10000)
			public void testPaladinMinionsAreCloned()throws Exception{
				for(int i = 0 ; i < 100;i++){
					Paladin justahn = new Paladin();
					Set<Card> set = new HashSet<Card>(justahn.getDeck());
					if(set.size() < justahn.getDeck().size())
						fail("When creating the Paladin's deck, a clone of the card should be added to the deck instead of the card itself.");
					}
			}	
			
			
// HELPERS // 	

	private void testIsInterface(Class<?> aClass) {
	assertEquals(aClass.getName() + " should be an Interface",
	aClass.isInterface(), true);
	}
	private void testGetterMethodExistsInClass(Class<?> aClass, String methodName,
			Class<?> returnedType, boolean writeVariable) {
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName);
		} catch (Exception e) {
			found = false;
		}
		String varName = "";
		if (returnedType == boolean.class)
			varName = methodName.substring(2).toLowerCase();
		else
			varName = methodName.substring(3).toLowerCase();
		if (writeVariable) {
			assertTrue("The \"" + varName + "\" instance variable in class "
					+ aClass.getSimpleName() + " is a READ variable.", found);
			assertTrue("Incorrect return type for " + methodName
					+ " method in " + aClass.getSimpleName() + " class.", m
					.getReturnType().isAssignableFrom(returnedType));
		} else {
			assertFalse("The \"" + varName + "\" instance variable in class "
					+ aClass.getSimpleName() + " is not a READ variable.",
					found);
		}
	}
	private void testInstanceVariableIsPresent(Class<?> aClass, String varName, boolean implementedVar) throws SecurityException 
	{
		boolean thrown = false;
		try 
		{
			aClass.getDeclaredField(varName);
		} 
		catch (NoSuchFieldException e) 
		{
			thrown = true;
		}
		if (implementedVar) 
		{
			assertFalse("There should be \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + ".", thrown);
		} 
		else 
		{
			assertTrue("The instance variable \"" + varName + "\" should not be declared in class " + aClass.getSimpleName() + ".", thrown);
		}
	}
	
	private void testInstanceVariableIsPrivate(Class<?> aClass, String varName) throws NoSuchFieldException, SecurityException 
	{
		Field f = aClass.getDeclaredField(varName);
		assertEquals("The \""+ varName + "\" instance variable in class " + aClass.getSimpleName() + " should not be accessed outside that class.", 2, f.getModifiers());
	}
	
	private void testSetterMethodExistsInClass(Class<?> aClass, String methodName, Class<?> inputType, boolean writeVariable) 
	{
		Method[] methods = aClass.getDeclaredMethods();
		String varName = methodName.substring(3).toLowerCase();
		if (writeVariable) 
		{
			assertTrue("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + " is a WRITE variable.", containsMethodName(methods, methodName));
		} 
		else 
		{
			assertFalse("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + " is not a WRITE variable.", containsMethodName(methods, methodName));
			return;
		}
		Method m = null;
		boolean found = true;
		try 
		{
			m = aClass.getDeclaredMethod(methodName, inputType);
		} 
		catch (NoSuchMethodException e) 
		{
			found = false;
		}
		assertTrue(aClass.getSimpleName() + " class should have " + methodName + " method that takes one " + inputType.getSimpleName() + " parameter.", found);
		assertTrue("Incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + ".", m.getReturnType().equals(Void.TYPE));
	}
	
	private static boolean containsMethodName(Method[] methods, String name) 
	{
		for (Method method : methods) 
		{
			if (method.getName().equals(name))
				return true;
		}
		return false;
	}
	
	private void testSetterLogic(Object createdObject, String name, Object valueIn, Object valueOut, Class<?> type) throws Exception 
	{
		Field f = null;
		Class<?> curr = createdObject.getClass();
		while (f == null)
		{

			if (curr == Object.class)
				fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \"" + name + "\".");
			try 
			{
				f = curr.getDeclaredField(name);
			} 
			catch (NoSuchFieldException e) 
			{
				curr = curr.getSuperclass();
			}
		}
		f.setAccessible(true);
		Character c = name.charAt(0);
		String methodName = "set" + Character.toUpperCase(c) + name.substring(1, name.length());
		Method m = createdObject.getClass().getMethod(methodName, type);
		m.invoke(createdObject, valueIn);
		assertEquals("The method \"" + methodName + "\" in class " + createdObject.getClass().getSimpleName() + " should set the correct value of variable \"" + name + "\".", valueOut, f.get(createdObject));
	}
	
	private void testClassImplementsInterface(Class<?> aClass, Class<?> interfaceName)
	{
		Class<?>[] interfaces = aClass.getInterfaces();
		boolean implement = false;
		for (Class<?> i : interfaces)
		{
			if (i.toString().equals(interfaceName.toString()))
				implement = true;
		}
		assertTrue(aClass.getSimpleName() + " class should implement " + interfaceName.getSimpleName() + " interface.", implement);
	}
	
	private void testExistsInClass(Class<?> aClass, String methodName,
			boolean implementedMethod, Class<?> returnType, Class<?>... inputTypes) {

		Method[] methods = aClass.getDeclaredMethods();

		if (implementedMethod) {
			assertTrue(
					"The " + methodName + " method in class "
							+ aClass.getSimpleName()
							+ " should be implemented.",
					containsMethodName(methods, methodName));
		} else {
			assertFalse(
					"The "
							+ methodName
							+ " method in class "
							+ aClass.getSimpleName()
							+ " should not be implemented, only inherited from super class.",
					containsMethodName(methods, methodName));
			return;
		}
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName, inputTypes);
		} catch (Exception e) {
			found = false;
		}

		String inputsList = "";
		for (Class<?> inputType : inputTypes) {
			inputsList += inputType.getSimpleName() + ", ";
		}
		if (inputsList.equals(""))
			assertTrue(aClass.getSimpleName() + " class should have "
					+ methodName + " method that takes no parameters.", found);
		else {
			if (inputsList.charAt(inputsList.length() - 1) == ' ')
				inputsList = inputsList.substring(0, inputsList.length() - 2);
			assertTrue(aClass.getSimpleName() + " class should have "
					+ methodName + " method that takes " + inputsList
					+ " parameter(s).", found);
		}

		assertTrue("incorrect return type for " + methodName + " method in "
				+ aClass.getSimpleName() + ".",
				m.getReturnType().equals(returnType));

	}
	private void testGetterLogic(Object createdObject, String name, Object value)
			throws Exception {
		Field f = null;
		Class<?> curr = createdObject.getClass();
		while (f == null) {
			if (curr == Object.class)
				fail("Class " + createdObject.getClass().getSimpleName()
						+ " should have the instance variable \"" + name
						+ "\".");
			try {
				f = curr.getDeclaredField(name);
			} catch (NoSuchFieldException e) {
				curr = curr.getSuperclass();
			}
		}
		f.setAccessible(true);
		f.set(createdObject, value);
		Character c = name.charAt(0);
		String methodName = "get" + Character.toUpperCase(c)
				+ name.substring(1, name.length());
		if (value.getClass().equals(Boolean.class))
			methodName = "is" + Character.toUpperCase(c)
					+ name.substring(1, name.length());
		Method m = createdObject.getClass().getMethod(methodName);
		assertEquals("The method \"" + methodName + "\" in class "
				+ createdObject.getClass().getSimpleName()
				+ " should return the correct value of variable \"" + name
				+ "\".", value, m.invoke(createdObject));
	}

}
