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
import model.cards.spells.FieldSpell;
import model.cards.spells.Flamestrike;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.HolyNova;
import model.cards.spells.KillCommand;
import model.cards.spells.LevelUp;
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
public class M2PrivateTests {

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
	public void testMinionAttacksMinionUpdatingAttacked() throws Exception 
	{
		Minion attackerMinion = new Minion("test", 0, null, 1, 2, false, true, true);
		Minion attackedMinion = new Minion("test", 0, null, 1, 2, false, true, true);
		
		attackerMinion.attack(attackedMinion);
		
		assertEquals("The attaker minion should update the attacked flag when attaking another minion.", true, attackerMinion.isAttacked());
	}
	
	
	@Test(timeout = 3000)
	public void testMinionAttacksMinionAttackedMinionLoseDivine() throws Exception 
	{
		Minion attackerMinion = new Minion("test", 0, null, 1, 2, false, true, true);
		Minion attackedMinion = new Minion("test", 0, null, 1, 2, false, true, true);
		
		attackerMinion.attack(attackedMinion);
		
		assertEquals("The attaked minion should lose divine.", false, attackedMinion.isDivine());
	}
	
	
	@Test(timeout = 3000)
	public void testMinionAttacksMinionAttackerMinionLoseHP() throws Exception 
	{
		Minion attackerMinion = new Minion("test", 0, null, 0, 2, false, false, true);
		Minion attackedMinion = new Minion("test", 0, null, 1, 2, false, false, true);
		
		attackerMinion.attack(attackedMinion);
		
		assertEquals("The attaker minion should lose HP.", 1, attackerMinion.getCurrentHP());
	}
	
	
	@Test(timeout = 3000)
	public void testMinionAttacksMinionBothLoseDivine() throws Exception 
	{
		Minion attackerMinion = new Minion("test", 0, null, 1, 2, false, true, true);
		Minion attackedMinion = new Minion("test", 0, null, 1, 2, false, true, true);
		
		attackerMinion.attack(attackedMinion);
		
		assertEquals("The attaker minion should lose divine.", false, attackerMinion.isDivine());
		assertEquals("The attaked minion should lose divine.", false, attackedMinion.isDivine());
	}
	
	
	@Test(timeout = 3000)
	public void testMinionAttacksMinionLoseDivineHP() throws Exception 
	{
		Minion attackerMinion = new Minion("test", 0, null, 1, 2, false, true, true);
		Minion attackedMinion = new Minion("test", 0, null, 1, 2, false, false, true);
		
		attackerMinion.attack(attackedMinion);
		
		assertEquals("The attaker minion should lose divine.", false, attackerMinion.isDivine());
		assertEquals("The attaked minion should lose HP.", 1, attackedMinion.getCurrentHP());
	}
	
	@Test(timeout = 3000)
	public void testAOESpellHasPerformActionMethod() throws Exception 
	{
		testExistsInClass(AOESpell.class, "performAction", true, void.class, ArrayList.class, ArrayList.class);
	}
	
	@Test(timeout = 3000)
	public void testFieldSpellHasPerformActionMethod() throws Exception 
	{
		testExistsInClass(FieldSpell.class, "performAction", true, void.class, ArrayList.class);
	}
	
	
	@Test(timeout = 1000)
	public void testInterfaceClonableIsImplementedByClassCard() throws Exception
	{
		testClassImplementsInterface(Card.class, Cloneable.class);
	}
	
	@Test(timeout = 3000)
	public void testCardHasCloneMethod() throws Exception 
	{
		testExistsInClass(Card.class, "clone", true, Card.class);
	}
	
	
	@Test(timeout = 3000)
	public void testCurseOfWeaknessHasPerformActionMethod() throws Exception 
	{
		testExistsInClass(CurseOfWeakness.class, "performAction", true, void.class, ArrayList.class, ArrayList.class);
	}
	
	
	
	@Test(timeout = 3000)
	public void testDivineSpiritAffectingMinion() throws Exception 
	{
		
		DivineSpirit divineSpirit = new DivineSpirit();
		
		Minion minion = new Minion("test", 0, null, 4, 4, false, false, true);
		minion.setMaxHP(5);
		
		divineSpirit.performAction(minion);
		
		assertEquals("The minion Max HP should be doubled.", 10, minion.getMaxHP());
		assertEquals("The minion current HP should be doubled.", 8, minion.getCurrentHP());
		
	}

	@Test(timeout = 3000)
	public void testFlamestrikeNotAffectingCurrentField() throws Exception 
	{
		Flamestrike flamestrike = new Flamestrike();
		
		ArrayList<Minion> opponentField = new ArrayList<Minion>();
		ArrayList<Minion> currentField = new ArrayList<Minion>();
		
		for(int i=0;i<5;i++)
			currentField.add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		flamestrike.performAction(opponentField, currentField);
		
		for(int i=0;i<5;i++)
			if(currentField.get(i).getCurrentHP() != 9)
				fail("Flamestrike should Not damage any minion on the current field.");
	}
	

	@Test(timeout = 30000)
	public void testFlamestrikeAffectingOpponentFieldAndKillingOneLastMinionOnField() throws Exception 
	{
		Flamestrike flamestrike = new Flamestrike();
		
		ArrayList<Minion> currentField = new ArrayList<Minion>();
		
		Mage minionListener = new Mage();
		
		for(int i=0;i<4;i++)
			minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		Minion minionToDie = new Minion("test", 0, null, 1, 4, false, false, true);
		minionListener.getField().add(minionToDie);
		
		Minion.class.getMethod("setListener", MinionListener.class).invoke(minionToDie, minionListener);
		
		flamestrike.performAction(minionListener.getField(), currentField);
		
		if(minionListener.getField().contains(minionToDie))
			fail("As the last minion on the opponent field is at HP 4, the damage caused by Flamestrike should Kill it.");
	
		if(minionListener.getField().size()!= 4)
			fail("As only one minion in this case is at HP 4 or less, out of 5 minions, 4 should survive.");
		
		for(int i=0;i<minionListener.getField().size();i++)
			if(minionListener.getField().get(i).getCurrentHP() != 5)
				fail("After the damage of Flamestrike causes a minion to die, the other minions should be damage correctly.");
	}
	
	
	@Test(timeout = 30000)
	public void testFlamestrikeAffectingOpponentFieldAndKillingTwoMinionOnMiddle() throws Exception 
	{
		Flamestrike flamestrike = new Flamestrike();
		
		ArrayList<Minion> currentField = new ArrayList<Minion>();
		
		Mage minionListener = new Mage();
		
		
		minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		Minion minionToDie1 = new Minion("test", 0, null, 1, 4, false, false, true);
		minionListener.getField().add(minionToDie1);
		Minion.class.getMethod("setListener", MinionListener.class).invoke(minionToDie1, minionListener);
		
		Minion minionToDie2 = new Minion("test", 0, null, 1, 4, false, false, true);
		minionListener.getField().add(minionToDie2);
		Minion.class.getMethod("setListener", MinionListener.class).invoke(minionToDie2, minionListener);
		
		minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		flamestrike.performAction(minionListener.getField(), currentField);
		
		for(int i=0;i<minionListener.getField().size();i++)
			if(minionListener.getField().get(i).getCurrentHP() != 5)
				fail("After the damage of Flamestrike causes a minion to die, the other minions should be damage correctly.");

		if(minionListener.getField().contains(minionToDie1)||minionListener.getField().contains(minionToDie2))
			fail("As the two minion at the middle of the opponent field are at HP 4, the damage caused by Flamestrike should Kill them.");
		
		if(minionListener.getField().size()!= 3)
			fail("As only two minion in this case are at HP 4 or less, out of 5 minions, 3 should survive.");
		
		for(int i=0;i<minionListener.getField().size();i++)
			if(minionListener.getField().get(i).getCurrentHP() != 5)
				fail("After the damage of Flamestrike causes a minion to die, the other minions should be damage correctly.");
		
		
		minionListener.getField().clear();
		
		minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		minionToDie1 = new Minion("test", 0, null, 1, 4, false, false, true);
		minionListener.getField().add(minionToDie1);
		Minion.class.getMethod("setListener", MinionListener.class).invoke(minionToDie1, minionListener);
		
		minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		minionToDie2 = new Minion("test", 0, null, 1, 4, false, false, true);
		minionListener.getField().add(minionToDie2);
		Minion.class.getMethod("setListener", MinionListener.class).invoke(minionToDie2, minionListener);
		
		minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		flamestrike.performAction(minionListener.getField(), currentField);
		
		for(int i=0;i<minionListener.getField().size();i++)
			if(minionListener.getField().get(i).getCurrentHP() != 5)
				fail("After the damage of Flamestrike causes a minion to die, the other minions should be damage correctly.");

		if(minionListener.getField().contains(minionToDie1)||minionListener.getField().contains(minionToDie2))
			fail("As the two minion at the middle of the opponent field are at HP 4, the damage caused by Flamestrike should Kill them.");
	
		if(minionListener.getField().size()!= 3)
			fail("As only two minion in this case are at HP 4 or less, out of 5 minions, 3 should survive.");
		
		for(int i=0;i<minionListener.getField().size();i++)
			if(minionListener.getField().get(i).getCurrentHP() != 5)
				fail("After the damage of Flamestrike causes a minion to die, the other minions should be damage correctly.");
	}

	@Test(timeout = 3000)
	public void testHolyNovaAffectingOpponentField() throws Exception 
	{
		HolyNova holyNova = new HolyNova();
		
		ArrayList<Minion> opponentField = new ArrayList<Minion>();
		ArrayList<Minion> currentField = new ArrayList<Minion>();
		
		for(int i=0;i<4;i++)
			opponentField.add(new Minion("test", 0, null, 1, 9, false, false, true));

		opponentField.add(new Minion("test", 0, null, 1, 9, false, true, true));
		
		holyNova.performAction(opponentField, currentField);
		
		for(int i=0;i<4;i++)
			if(opponentField.get(i).getCurrentHP() != 7)
				fail("HolyNova should damage all minions on the opponent field.");
		
		if(opponentField.get(4).isDivine())
			fail("HolyNova should destroy the divine shield of opponent minions.");
		
		if(opponentField.get(4).getCurrentHP() != 9)
			fail("HolyNova should not damage opponent minions with divine shield.");
	}
	
	@Test(timeout = 30000)
	public void testHolyNovaAffectingOpponentFieldAndKillingOneMinionOnStart() throws Exception 
	{
		HolyNova holyNova = new HolyNova();
		
		ArrayList<Minion> currentField = new ArrayList<Minion>();
		
		Mage minionListener = new Mage();
		
		minionListener.getField().clear();
		
		Minion minionToDie = new Minion("test", 0, null, 1, 2, false, false, true);
		minionListener.getField().add(minionToDie);
		
		for(int i=0;i<4;i++)
			minionListener.getField().add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		minionToDie.setListener(minionListener);
		
		holyNova.performAction(minionListener.getField(), currentField);
		
		if(minionListener.getField().contains(minionToDie))
			fail("As the first minion on the opponent field is at HP 2, the damage caused by HolyNova should Kill it.");
	
		if(minionListener.getField().size()!= 4)
			fail("As only one minion in this case is at HP 2 or less, out of 5 minions, 4 should survive.");
		
		for(int i=0;i<minionListener.getField().size();i++)
			if(minionListener.getField().get(i).getCurrentHP() != 7)
				fail("After the damage of HolyNova causes a minion to die, the other minions should be damage correctly.");
	}
	
	
	@Test(timeout = 30000)
	public void testHolyNovaAffectingOpponentFieldAndKillingAllMinionsOnField() throws Exception 
	{
		HolyNova holyNove = new HolyNova();
		
		ArrayList<Minion> currentField = new ArrayList<Minion>();
		
		Mage minionListener = new Mage();
		
		for(int i=0;i<5;i++)
		{
			Minion minionToDie = new Minion("test", 0, null, 1, 2, false, false, true);
			minionListener.getField().add(minionToDie);
			minionToDie.setListener(minionListener);
		}
		
		holyNove.performAction(minionListener.getField(), currentField);
		if(minionListener.getField().size() != 0)
			fail("As all the minions on the opponent field are at HP 2, the damage caused by HolyNova should Kill them all.");
	}
	
	
	@Test(timeout = 3000)
	public void testKillCommandHasPerformActionOnMinionMethod() throws Exception 
	{
		testExistsInClass(KillCommand.class, "performAction", true, void.class, Minion.class);
	}
	
	
	@Test(timeout = 3000)
	public void testKillCommandAffectingHero() throws Exception 
	{
		Mage hero = new Mage();
		
		KillCommand killCommand = new KillCommand();
		
		killCommand.performAction(hero);
		
		assertEquals("KillCommand should hit heros with the right amount.", 27, hero.getCurrentHP());
	}
	
	
	
	@Test(timeout = 3000)
	public void testKillCommandAffectingMinionWithDivine() throws Exception 
	{
		Minion minion = new Minion("test", 0, null, 1, 6, false, true, false);
		
		KillCommand killCommand = new KillCommand();
		
		killCommand.performAction(minion);
		
		assertEquals("KillCommand should destroy thedivine shield of the minion.", false, minion.isDivine());
		
		assertEquals("KillCommand should not hit minions with divine shield.", 6, minion.getCurrentHP());
	}
	
	@Test(timeout = 3000)
	public void testKillCommandKillingHero() throws Exception 
	{
		Mage hero = new Mage();
		hero.setCurrentHP(3);
		
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
		
		KillCommand killCommand = new KillCommand();
		
		killCommand.performAction(hero);

		if(callParameters.size() != 1)
			fail("If KillCommand resulted on killing a hero, they should announce thier death.");
	}
	
	
	

	@Test(timeout = 3000)
	public void testLevelUpAffectingField() throws Exception 
	{
		ArrayList<Minion> field = new ArrayList<Minion>();
		
		for(int i=0;i<3;i++)
		{
			Minion minion = new Minion("Silver Hand Recruit", 1, null, 2, 3, false, false, false);
			field.add(minion);
		}
		
		field.add(new Minion("test", 1, null, 1, 1, false, false, false));
		
		LevelUp levelUp = new LevelUp();
		
		levelUp.performAction(field);
		
		for(int i=0;i<3;i++)
		{
			assertEquals("LevelUp should affect the attack of Silver Hand Recruit.", 3, field.get(i).getAttack());
			assertEquals("LevelUp should affect the max HP of Silver Hand Recruit.", 4, field.get(i).getMaxHP());
			assertEquals("LevelUp should affect the current HP of Silver Hand Recruit.", 4, field.get(i).getCurrentHP());
		}
		assertEquals("LevelUp should not affect the attack of minions which are not Silver Hand Recruit.", 1, field.get(3).getAttack());
		assertEquals("LevelUp should not affect the max HP of minions which are not Silver Hand Recruit.", 1, field.get(3).getMaxHP());
		assertEquals("LevelUp should not affect the current HP of minions which are not Silver Hand Recruit.", 1, field.get(3).getCurrentHP());
	}
	
	@Test(timeout = 30000)
    public void testMultiShotAffectingOpponentFieldWithRandomTargets() throws Exception 
    {
        ArrayList<Minion> currentField = new ArrayList<Minion>();
        ArrayList<Minion> opponentField = new ArrayList<Minion>();
        MultiShot multiShot = new MultiShot();

        for(int j=0;j<6;j++)
        {
            opponentField.add(new Minion("test", 0, null, 1, 200, false, false, true));
        }

        for(int i=0;i<50;i++)
        {
            multiShot.performAction(opponentField, currentField);
        }

        int affectedMinion = 0;

        for(int j=0;j<6;j++)
            if(opponentField.get(j).getCurrentHP()!=200)
                affectedMinion++;

        if(affectedMinion<=3)
            fail("MultiShot should choose the targets randomly.");
    }
	
	
	@Test(timeout = 3000)
	public void testMultiShotNotAffectingCurrentField() throws Exception 
	{
		MultiShot multiShot = new MultiShot();
		
		ArrayList<Minion> opponentField = new ArrayList<Minion>();
		ArrayList<Minion> currentField = new ArrayList<Minion>();
		
		for(int i=0;i<5;i++)
			currentField.add(new Minion("test", 0, null, 1, 9, false, false, true));
		
		multiShot.performAction(opponentField, currentField);
		
		for(int i=0;i<5;i++)
			if(currentField.get(i).getCurrentHP() != 9)
				fail("MultiShot should Not affect any minion on the current field.");
	}
	
	
	@Test(timeout = 3000)
	public void testMultiShotAffectingOpponentFieldWithOneKillableTarget() throws Exception 
	{
		MultiShot multiShot = new MultiShot();
		
		ArrayList<Minion> opponentField = new ArrayList<Minion>();
		ArrayList<Minion> currentField = new ArrayList<Minion>();

		Minion minion = new Minion("test", 0, null, 1, 3, false, false, true);
		
		MinionListener minionListener = new MinionListener() {
			
			@Override
			public void onMinionDeath(Minion m) {
				callParameters.add(m);
			}
		};
		minion.setListener(minionListener);
		opponentField.add(minion);
		
		multiShot.performAction(opponentField, currentField);
		
		assertEquals("If MultiShot kills a minion, it should announce its own death.", callParameters.get(0),minion);
	}
	
	@Test(timeout = 30000)
	public void testMultiShotAffectingOpponentFieldWithFiveTargets() throws Exception 
	{
		for(int i=0;i<50;i++)
		{
			MultiShot multiShot = new MultiShot();
		
			ArrayList<Minion> opponentField = new ArrayList<Minion>();
			ArrayList<Minion> currentField = new ArrayList<Minion>();

			for(int j=0;j<5;j++)
				opponentField.add(new Minion("test", 0, null, 1, 9, false, false, true));
		
			multiShot.performAction(opponentField, currentField);
			
			int minionsWith6HP=0;
			for(int j=0;j<5;j++)
			{
				if(opponentField.get(j).getCurrentHP() == 6)
					minionsWith6HP++;
				else if(opponentField.get(j).getCurrentHP() == 3)
					fail("MultiShot should never hit the same target twice.");
			}
			if(minionsWith6HP != 2)
				fail("Given more than two minions on field, exactly two of them should be affected.");
		}
		
	}
	
	@Test(timeout = 3000)
	public void testPolymorphHasPerformActionMethod() throws Exception 
	{
		testExistsInClass(Polymorph.class, "performAction", true, void.class, Minion.class);
	}
	
	
	
	@Test(timeout = 3000)
	public void testPolymorphAffectingMinionIcehowl() throws Exception 
	{
		Icehowl minion = new Icehowl();
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
	public void testPyroblastHasPerformActionOnHeroMethod() throws Exception 
	{
		testExistsInClass(Pyroblast.class, "performAction", true, void.class, Hero.class);
	}
	
	@Test(timeout = 3000)
	public void testPyroblastAffectingMinion() throws Exception 
	{
		Minion minion = new Minion("test", 0, null, 1, 11, false, false, false);
		
		Pyroblast pyroblast = new Pyroblast();
		
		pyroblast.performAction(minion);
		
		assertEquals("Pyroblast should hit minions with the right amount.", 1, minion.getCurrentHP());
	}
	
	@Test(timeout = 3000)
	public void testPyroblastKillingMinion() throws Exception 
	{
		Minion minion = new Minion("test", 0, null, 1, 5, false, false, false);
		
		MinionListener minionListener = new MinionListener() {
			
			@Override
			public void onMinionDeath(Minion m) {
				callParameters.add(m);
			}
		};
		
		minion.setListener(minionListener);
		
		Pyroblast pyroblast = new Pyroblast();
		
		pyroblast.performAction(minion);
		if(callParameters.size() != 1 || callParameters.get(0) != minion)
			fail("If Pyroblast resulted on killing a minion, they should announce thier death.");
	}
	
	
	@Test(timeout = 3000)
	public void testSealOfChampionsAffectingMinionAttack() throws Exception 
	{
		Minion minion = new Minion("test", 0, null, 1, 1, false, false, false);
		SealOfChampions sealOfChampions = new SealOfChampions();
		sealOfChampions.performAction(minion);
		assertEquals("SealOfChampions should affect the minion attack properly.", 4, minion.getAttack());
	}
	
	@Test(timeout = 3000)
	public void testShadowWordDeathNotKillingMinion() throws Exception 
	{
		Minion minion = new Minion("test", 0, null, 4, 1, false, false, false);
		
		MinionListener minionListener = new MinionListener() {
			
			@Override
			public void onMinionDeath(Minion m) {
				callParameters.add(m);
			}
		};
		
		minion.setListener(minionListener);
		
		ShadowWordDeath shadowWordDeath = new ShadowWordDeath();
		
		try
		{
			shadowWordDeath.performAction(minion);
			fail("ShadowWordDeath being cast on a minion with less than 5 attack should throw an InvalidTargetException.");
		}
		catch(InvalidTargetException e)
		{
			
		}
	}
	
	
	@Test(timeout = 3000)
	public void testSiphonSoulKillingMinion() throws Exception 
	{
		Minion minion = new Minion("test", 0, null, 1, 5, false, false, false);
		
		MinionListener minionListener = new MinionListener() {
			
			@Override
			public void onMinionDeath(Minion m) {
				callParameters.add(m);
			}
		};
		
		minion.setListener(minionListener);
		
		SiphonSoul siphonSoul = new SiphonSoul();
		
		int returned = siphonSoul.performAction(minion);
		if(callParameters.size() != 1 || callParameters.get(0) != minion)
			fail("The minion targeted with SiphonSoul should announce its death.");
		
		assertEquals("SiphonSoul should return 3.", 3, returned);
	}
	
	
	@Test(timeout = 3000)
	public void testTwistingNetherEffectWithDivines() throws Exception 
	{
		TwistingNether twistingNether = new TwistingNether();
		
		Mage hero1 = new Mage();
		Mage hero2 = new Mage();
		
		int opponentFieldSize = 3;
		int currentFieldSize = 4;
		
		for(int i=0;i<opponentFieldSize;i++)
		{
			Minion minion = new Minion("test", 2, null, 2, 2, false, true, false);
			minion.setListener(hero1);
			hero1.getField().add(minion);
		}
			
		for(int i=0;i<currentFieldSize;i++)
		{
			Minion minion = new Minion("test", 2, null, 2, 2, false, true, false);
			minion.setListener(hero2);
			hero2.getField().add(minion);
		}
		
		
		
		twistingNether.performAction(hero1.getField(), hero2.getField());
		assertTrue("TwistingNether sould kill all minions on both fields.", hero1.getField().size()+hero2.getField().size()==0);
	}
	
	
	
	
	@Test(timeout = 3000)
	public void testHeroHasCastSpellWithFieldSpell() throws Exception 
	{
		testExistsInClass(Hero.class, "castSpell", true, void.class, FieldSpell.class);
	}

	@Test(timeout = 3000)
	public void testHeroCastSpellWithFieldSpellCallsValidateTurn() throws Exception 
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
		class TestCard extends Spell implements FieldSpell
		{
			public TestCard() 
			{
				super("test", 0, null);
			}

			@Override
			public void performAction(ArrayList<Minion> field) 
			{
			}
			
		}
		TestCard fieldSpell = new TestCard();
		hero.getHand().add(fieldSpell);
		hero.getField().clear();
		hero.castSpell(fieldSpell);
		if(callParameters.isEmpty()||!callParameters.contains(hero)){
			fail("The method castSpell(FeieldSpell) in class Hero should validate the turn before casting the spell.");
		}
		reset();
	}

	@Test(timeout = 3000)
	public void testHeroCastSpellWithFieldSpellvalidatesManaCost() throws Exception 
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
		class TestCard extends Spell implements FieldSpell
		{
			public TestCard() 
			{
				super("test", 11, null);
			}

			@Override
			public void performAction(ArrayList<Minion> field) 
			{
			}
			
		}
		TestCard fieldSpell = new TestCard();
		hero.getHand().add(fieldSpell);
		hero.getField().clear();

		hero.castSpell(fieldSpell);
		if(callParameters.size()!=1||callParameters.get(0)!=fieldSpell)
			fail("The method castSpell(FeieldSpell) in class Hero should validate the mana cost before casting it.");
	}


	@Test(timeout = 3000)
	public void testHeroCastSpellWithFieldSpellWorksNormally() throws Exception 
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
		class TestCard extends Spell implements FieldSpell
		{
			public TestCard() 
			{
				super("test", 3, null);
			}

			@Override
			public void performAction(ArrayList<Minion> field) 
			{
				callParameters.add(field);
			}
			
		}
		TestCard fieldSpellPlayed = new TestCard();
		TestCard fieldSpellNotPlayed = new TestCard();
		hero.getHand().add(fieldSpellPlayed);
		hero.getHand().add(fieldSpellNotPlayed);
		hero.setCurrentManaCrystals(5);
		hero.castSpell(fieldSpellPlayed);
		if(hero.getCurrentManaCrystals() == 5)
			fail("The method castSpell(FeieldSpell) in class Hero should update the currentManaCrystals of the hero after playing the spell.");
		if(hero.getCurrentManaCrystals() != 2)
		{
			fail("The method castSpell(FeieldSpell) in class Hero should update the currentManaCrystals of the hero correctly with the spell cost after playing the spell.");
		}
		if(hero.getHand().size() == 2 || hero.getHand().contains(fieldSpellPlayed))
		{
			fail("The method castSpell(FeieldSpell) in class Hero should remove the spell from the hero's hand after playing it.");
		}
		if(hero.getHand().size() == 2 || !(hero.getHand().contains(fieldSpellNotPlayed)))
		{
			fail("The method castSpell(FeieldSpell) in class Hero should not remove the spell from the hero's hand if not played.");
		}
		if(callParameters.get(0)!=hero.getField())
			fail("The method castSpell(FeieldSpell) should perform its action on the right targets.");
	}
	
	
	@Test(timeout = 3000)
	public void testHeroHasCastSpellWithHeroTargetSpellHero() throws Exception 
	{
		testExistsInClass(Hero.class, "castSpell", true, void.class, HeroTargetSpell.class, Hero.class);
	}

	@Test(timeout = 3000)
	public void testHeroCastSpellWithHeroTargetSpellHeroCallsValidateTurn() throws Exception 
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
		class TestCard extends Spell implements HeroTargetSpell
		{
			public TestCard() 
			{
				super("test", 0, null);
			}

			@Override
			public void performAction(Hero m) 
			{
			}
			
		}
		TestCard heroTargetSpell = new TestCard();
		hero.getHand().add(heroTargetSpell);
		hero.getField().clear();
		hero.castSpell(heroTargetSpell, new Mage());
		if(callParameters.isEmpty()||!callParameters.contains(hero))
			fail("The method castSpell(HeroTargetSpell, Hero) in class Hero should validate the turn before cast.");
	}

	@Test(timeout = 3000)
	public void testHeroCastSpellWithHeroTargetSpellHerovalidatesManaCost() throws Exception 
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
		class TestCard extends Spell implements HeroTargetSpell
		{
			public TestCard() 
			{
				super("test", 11, null);
			}

			@Override
			public void performAction(Hero m) 
			{
			}
			
		}
		TestCard heroTargetSpell = new TestCard();
		hero.getHand().add(heroTargetSpell);
		hero.getField().clear();
		hero.castSpell(heroTargetSpell, hero);
		if(callParameters.size()!=1||callParameters.get(0)!=heroTargetSpell)
			fail("The method castSpell(HeroTargetSpell, Hero) in class Hero should throw NotEnoughManaException if the hero does not have enough mana crystals to play a spell.");
	}


	@Test(timeout = 3000)
	public void testHeroCastSpellWithHeroTargetSpellHeroWorksNormally() throws Exception 
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
		class TestCard extends Spell implements HeroTargetSpell
		{
			public TestCard() 
			{
				super("test", 3, null);
			}

			@Override
			public void performAction(Hero m) 
			{
				callParameters.add(m);
			}
			
		}
		TestCard heroTargetSpellPlayed = new TestCard();
		TestCard heroTargetSpellNotPlayed = new TestCard();
		hero.getHand().add(heroTargetSpellPlayed);
		hero.getHand().add(heroTargetSpellNotPlayed);
		hero.setCurrentManaCrystals(5);
		Mage heroToHit = new Mage();
		hero.castSpell(heroTargetSpellPlayed, heroToHit);
		if(hero.getCurrentManaCrystals() == 5)
			fail("The method castSpell(HeroTargetSpell, Hero) in class Hero should update the currentManaCrystals of the hero after playing the spell.");
		if(hero.getCurrentManaCrystals() != 2)
		{
			fail("The method castSpell(HeroTargetSpell, Hero) in class Hero should update the currentManaCrystals of the hero correctly with the minion cost after playing the spell.");
		}
		if(callParameters.size() != 1 || callParameters.get(0)!=heroToHit)
			fail("The method castSpell(HeroTargetSpell, Hero) in class Hero should call performAction on the right target.");
		if(hero.getHand().size() == 2 || hero.getHand().contains(heroTargetSpellPlayed))
		{
			fail("The method castSpell(HeroTargetSpell, Hero) in class Hero should remove the spell from the hero's hand after playing it.");
		}
		if(hero.getHand().size() == 2 || !(hero.getHand().contains(heroTargetSpellNotPlayed)))
		{
			fail("The method castSpell(HeroTargetSpell, Hero) in class Hero should not remove the spell from the hero's hand if not played.");
		}
		reset();
		hero.setCurrentManaCrystals(3);
		try
		{
			hero.castSpell(heroTargetSpellPlayed, hero);
		}
		catch(NotEnoughManaException e)
		{
		}
		if(callParameters.size() != 1 || callParameters.get(0)!=hero)
			fail("A hero can hit himself with a HeroTargetSpell.");
	}
	
	
	@Test(timeout = 3000)
	public void testHunterHasUseHeroPower() throws Exception 
	{
		testExistsInClass(Hunter.class, "useHeroPower", true, void.class);
	}
	

	@Test(timeout = 3000)
	public void testHunterCallsSuperUseHeroPower() throws Exception 
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
		
		HeroListener heroListener = new HeroListener() {
			
			@Override
			public void onHeroDeath() {
				
			}
			
			@Override
			public void endTurn() throws FullHandException, CloneNotSupportedException {
				
			}
			
			@Override
			public void damageOpponent(int amount) {
				
			}
		};
		
		Hunter hunter = new Hunter();
		hunter.setValidator(actionValidator);
		hunter.setListener(heroListener);
		try
		{
			hunter.useHeroPower();
			fail("UseHeroPower in class Hunter should call useHeroPower in the super class to validate action.");
		}
		catch(HeroPowerAlreadyUsedException e)
		{
			StackTraceElement [] stackTraceElement = e.getStackTrace();
			boolean found = false;
			for(int i=0;i<stackTraceElement.length&&!found;i++)
				if(stackTraceElement[i].getClassName().endsWith("Hero") && stackTraceElement[i].getMethodName().equals("useHeroPower"))
					found = true;
			if(!found)
				fail("UseHeroPower in class Hunter should call useHeroPower in the super class to validate using it.");
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
			hunter.useHeroPower();
			fail("UseHeroPower in class Hunter should call useHeroPower in the super class to validate action.");
		}
		catch(HeroPowerAlreadyUsedException e)
		{
			StackTraceElement [] stackTraceElement = e.getStackTrace();
			boolean found = false;
			for(int i=0;i<stackTraceElement.length&&!found;i++)
				if(stackTraceElement[i].getClassName().endsWith("Hero") && stackTraceElement[i].getMethodName().equals("useHeroPower"))
					found = true;
			if(!found)
				fail("UseHeroPower in class Hunter should call useHeroPower in the super class to validate the turn.");
		}
	}

	@Test(timeout = 3000)
	public void testHunterUseHeroPowerCallsDamageOpponentHero() throws Exception 
	{
		Hunter hunter = new Hunter();

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

		HeroListener heroListener = new HeroListener() 
		{
			@Override
			public void onHeroDeath() 
			{
				callParameters.add(true);
			}

			@Override
			public void endTurn() throws FullHandException, CloneNotSupportedException 
			{
			}

			@Override
			public void damageOpponent(int amount) 
			{
				callParameters.add(amount);
			}
		};

		hunter.setListener(heroListener);
		hunter.setValidator(validator);
		hunter.setCurrentManaCrystals(2);
		hunter.useHeroPower();

		if(callParameters.size()!=1)
			fail("useHeroPower in class Hunter should notify the listener when the hero power is used.");
		if((int)callParameters.get(0)!=2)
			fail("useHeroPower in class Hunter should notify the proper amount of damage.");
	}


	@Test(timeout = 3000)
	public void testMageUseHeroPowerCallsDamageHero() throws Exception 
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

		Mage heroToBeHit = new Mage();

		mage.setValidator(validator);
		mage.setCurrentManaCrystals(2);
		mage.useHeroPower(heroToBeHit);

		if(heroToBeHit.getCurrentHP()!=29)
			fail("useHeroPower in class Mage should damage the target hero with the proper amount.");

		mage.setCurrentManaCrystals(2);
		mage.setHeroPowerUsed(false);
		mage.useHeroPower(mage);

		if(mage.getCurrentHP()!=29)
			fail("useHeroPower in class Mage should be able to hit himself.");
		reset();
		HeroListener heroListener = new HeroListener() 
		{
			@Override
			public void onHeroDeath() 
			{
				callParameters.add(true);
			}

			@Override
			public void endTurn() throws FullHandException, CloneNotSupportedException 
			{
			}

			@Override
			public void damageOpponent(int amount) 
			{
				callParameters.add(amount);
			}
		};
		mage.setValidator(validator);
		mage.setCurrentManaCrystals(2);
		mage.setHeroPowerUsed(false);
		heroToBeHit.setCurrentHP(1);
		heroToBeHit.setListener(heroListener);
		mage.useHeroPower(heroToBeHit);

		if(callParameters.size()!=1)
			fail("In case a hero is at 1 HP and got hit with Mage hero power, this hero should notify death.");
	}
	
	@Test(timeout = 3000)
	public void testPriestUseHeroPowerOnMinion() throws Exception 
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

		Minion minion = new Minion("test", 0, null, 1, 3, false, false, false);
		minion.setCurrentHP(1);
		priest.setCurrentManaCrystals(2);
		priest.setValidator(validator);
		priest.useHeroPower(minion);

		if(minion.getCurrentHP()!=3)
			fail("useHeroPower in class Priest should heal the target minion with the proper amount.");

	}

	
	@Test(timeout = 1000)
	public void testInterfaceActionValidatorIsImplementedByClassGame() throws Exception
	{
		testClassImplementsInterface(Class.forName(gamePath), Class.forName(actionValidatorPath));
	}
	
	@Test(timeout = 1000)
	public void testGetterForGameListenerNotExistsInClassGame() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(gamePath), "getGameListener", GameListener.class, false);
	}

	
	@Test(timeout = 3000)
	public void testActionValidatorHasAttackValidateMinionVsMinion()
			throws NotYourTurnException, TauntBypassException,
			InvalidTargetException, NotSummonedException,
			ClassNotFoundException {
		testExistsInClass(Class.forName(actionValidatorPath), "validateAttack",
				true, void.class, Class.forName(minionPath),
				Class.forName(minionPath));
	}
	
	@Test(timeout = 1000)
	public void testSetterForInstanceVariableListenerExistsInClassGame()
			throws Exception {
		testSetterMethodExistsInClass(Class.forName(gamePath), "setListener",
				GameListener.class, true);
	}
	

	@Test(timeout = 1000)
	public void testValidateTurnnHeroInClassGame() throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);

		Hero currentHero = game.getCurrentHero();
		game.validateTurn(currentHero);
	}
	
	@Test(timeout = 3000)
	public void testGameHasValidateAttackMinionOnMinion()
			throws ClassNotFoundException {
		testExistsInClass(Class.forName(gamePath), "validateAttack", true,
				void.class, Minion.class, Minion.class);
	}

	@Test(timeout = 1000)
	public void testValidateAttackMinionOnMinionSleepingThrowsCannotAttackException()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);

		Hero currentHero = game.getCurrentHero();
		Hero testHero = (currentHero == hero1) ? hero2 : hero1;
		Minion attacker = new Minion("minionThatIsInHand", 0, Rarity.BASIC, 2,
				10, false, false, false);
		Minion target = new Minion("minionToBeAttacked", 0, Rarity.BASIC, 2,
				10, false, false, true);
		currentHero.getField().add(attacker);
		testHero.getField().add(target);
		try {
			game.validateAttack(attacker, target);
			fail("The minion that the current hero is attacking with should not be sleeping and if such case happens, a CannotAttackException should be thrown in the validateAttack(Minion,Minion) in class Game.");
		} catch (CannotAttackException e) {}
	}
	
	
	@Test(timeout = 1000)
	public void testValidateAttackMinionInFieldAndInHandOnHeroThrowsNotSummonedExceptionInClassGame()
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
		currentHero.getHand().add(attacker);
		testHero.getField().add(target);

		try {
			game.validateAttack(attacker, testHero);
		} catch (NotSummonedException e) {
			fail("The minion that the current hero is attacking with is on field and should be able to attack normally");
		}
	}

	@Test(timeout = 1000)
	public void testValidateAttackMinionOnHeroFriendlyThrowsInvalidTargetExceptionInClassGame()
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
			game.validateAttack(attacker, currentHero);
			fail("The minion that the current hero is attacking with should not attack its own Hero and should throw InvalidTargetException");
		} catch (InvalidTargetException e) {

		}
	}

	@Test(timeout = 1000)
	public void testValidateAttackMinionOnHeroAttack0ThrowsCannotAttackException()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);

		Hero currentHero = game.getCurrentHero();
		Hero target = (currentHero == hero1) ? hero2 : hero1;
		Minion attacker = new Minion("minionThatIsInHand", 0, Rarity.BASIC, 0,
				10, false, false, true);
		currentHero.getField().add(attacker);
		try {
			game.validateAttack(attacker, target);
			fail("The minion that the current hero is attacking with cannot have 0 attack and if such case happens, a CannotAttackException should be thrown in the validateAttack(Minion,Minion) in class Game.");
		} catch (CannotAttackException e) {}
	}
	
	@Test(timeout = 1000)
	public void testValidateCardManaCostDoesNOTThrowNotEnoughManaExceptionInClassGame()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);
		Hero currentHero = game.getCurrentHero();
		Card card = new Card("cardToBeTested",3,null){};
		currentHero.setCurrentManaCrystals(3);
		try {
			game.validateManaCost(card);
		} catch (NotEnoughManaException e) {
			fail("The class Game should validate that the currentHero has enough mana before playing a card and should NOT throw a NotEnoughManaException if it is");
		}
	}
	
	@Test(timeout = 1000)
	public void testValidateUsingHeroPowerThrowsNotEnoughManaExceptionInClassGame()
			throws Exception {
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);
		Hero currentHero = game.getCurrentHero();
		currentHero.setCurrentManaCrystals(1);
		try {
			game.validateUsingHeroPower(currentHero);
			fail("The class Game should validate that the currentHero's Mana Crystals are enough to use the hero power and should throw a NotEnoughManaException if it is not enough");
		} catch (NotEnoughManaException e) {}
		
	}
	
	@Test( timeout = 3000)
	public void testGameEndTurnIncreasesTotalManaCrystalsEqual10() throws Exception{
		Hero hero1 = new Hunter();
		Hero hero2 = new Hunter();
		Game game = new Game(hero1, hero2);
		Hero currentHero = game.getCurrentHero();
		Hero testHero = (currentHero == hero1) ? hero2 : hero1;
		testHero.setTotalManaCrystals(10);
		game.endTurn();
		Hero currentHeroAfterTurn = game.getCurrentHero();
		int manaCrystalsAfterTurn = currentHeroAfterTurn.getTotalManaCrystals();
		if(manaCrystalsAfterTurn != 10)
			fail("The method endTurn in class Game should increase the total mana crystals of the new current hero as long as it is under 10");
		
	}

	@Test( timeout = 3000)
	public void testGameEndTurnShouldCallSetSleepingForTheNewCurrentHeroMinions() throws Exception{
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
				public void setSleeping(boolean sleeping) {
					if(!sleeping)
						callParameters.add(this);
					else
						fail("The method endTurn in the class Game should call the method setSleeping in class Minion and change the currentHero's minions to not sleeping");
				}
			};
			testHero.getField().add(m);
			toBeTested.add(m);
		}
		
		game.endTurn();
		if(callParameters.isEmpty())
			fail("The method endTurn in class Game should call the method setSleeping in class Minion");
		for(int i = 0 ; i < toBeTested.size(); i++){
			Minion m = toBeTested.get(i);
			if(!callParameters.contains(m)){
				reset();
				fail("The method endTurn in class Game should call the method setSleeping in class Minion");
			}
		}
		reset();
		}
	
	
	@Test( timeout = 3000)
	public void testGameConstructorCallsSetValidatorForBothHeroes() throws Exception{
		Hero hero1 = new Hunter(){
			@Override
			public void setValidator(ActionValidator validator) {
					callParameters.add(this);
					callParameters.add(validator);
					super.setValidator(validator);
			}
		};
		Hero hero2 = new Hunter(){
			@Override
			public void setValidator(ActionValidator validator) {
					callParameters.add(this);
					callParameters.add(validator);
					super.setValidator(validator);
			}
		};
		reset();
		Game game = new Game(hero1, hero2);
		Hero currentHero = game.getCurrentHero();
		Hero testHero = (currentHero == hero1) ? hero2 : hero1;
		
		if(callParameters.isEmpty())
			fail("Game Constructor should call the method setValidator in class Hero");
		int occurrences = Collections.frequency(callParameters, game);
		if(!callParameters.contains(currentHero)){
			reset();
			fail("Game Constructor should call the method setValidator in class Hero for the currentHero");
		}
		if(!callParameters.contains(testHero)){
			reset();
			fail("Game Constructor should call the method setValidator in class Hero for the opponent");
		}
		if(occurrences != 2){
			reset();
			fail("Game Constructor should call the method setValidator in class Hero for both the currentHero and the opponent");
		}
		reset();
	}
	
	@Test(timeout = 3000)
	public void testHeroUseHeroPowerThrowsHeroPowerAlreadyUsedException() throws Exception {
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
		hero.setHeroPowerUsed(true);
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
	public void testHeroPlayMinionWorksNormally() throws Exception {
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
		Minion m1 = new Minion("toBeTested",3,Rarity.LEGENDARY,5,6,false,false,true);
		Minion m2 = new Minion("shouldRemainAsIs",5,Rarity.BASIC,10,10,false,false,true);
		hero.getHand().add(m1);
		hero.getHand().add(m2);
		hero.setCurrentManaCrystals(5);
		hero.getField().clear();
		for(int i = 0 ; i < 5 ; i++){
			Minion fieldMinion = new Minion("toBeTested",0,Rarity.LEGENDARY,5,6,false,false,true);
			hero.getField().add(fieldMinion);
		}
		
		hero.playMinion(m1);
		if(callParameters.isEmpty())
			fail("The method playMinion in class Hero should call the methods for validation in class game to make sure that the minion can be played.");
		if(!callParameters.contains(m1) || Collections.frequency(callParameters, m1) != 2){
			reset();
			fail("The method playMinion in class Hero should call the methods for validation in class game to make sure that the minion can be played.");
		}
		if(hero.getCurrentManaCrystals() == 5)
			fail("The method playMinion in class Hero should update the currentManaCrystals of the hero after playing the minion");
		if(hero.getCurrentManaCrystals() != 2){
			fail("The method playMinion in class Hero should update the currentManaCrystals of the hero correctly with the minion cost after playing the minion");
		}
		if(hero.getHand().size() == 2 || hero.getHand().contains(m1)){
			fail("The method playMinion in class Hero should remove the minion from the hero's hand after playing it");
		}
		if(hero.getField().size()==5 || !(hero.getField().contains(m1))){
			fail("The method playMinion in class Hero should update the current hero's field and place the minion in it after playing the minion");
		}
	}
	
	@Test(timeout = 3000)
	public void testHeroAttackWithMinionOnHeroCallsValidateAttack() throws Exception {
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
		Minion attacker = new Minion("attacker", 2, Rarity.LEGENDARY, 2, 5, false, false, true);
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
	public void testHeroAttackWithMinionOnHeroThrowsCannotAttackException0Attack() throws Exception {
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
		Minion attacker = new Minion("attacker", 2, Rarity.LEGENDARY, 0, 5, false, false, true);
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
	public void testHeroAttackWithMinionOnMinionThrowsCannotAttackExceptionSleeping() throws Exception {
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
		Minion attacker = new Minion("attacker", 2, Rarity.LEGENDARY, 2, 5, false, false, false);
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
	public void testHeroAttackWithMinionOnHeroThrowsCannotAttackExceptionAlreadyAttacked() throws Exception {
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
		Minion attacker = new Minion("attacker", 2, Rarity.LEGENDARY, 2, 5, false, false, true);
		attacker.setAttacked(true);
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
	public void testWarlockMinionsHaveListeners() throws Exception {
		Warlock kvit = new Warlock();
		for(int i = 0 ; i < kvit.getDeck().size();i++){
			if(kvit.getDeck().get(i) instanceof Minion)
			{
				Minion minion = (Minion)(kvit.getDeck().get(i));
				Field f = Minion.class.getDeclaredField("listener");
				f.setAccessible(true);
				if(f.get(minion) == null)
					fail("The minions in the Warlock's deck should have their Warlock as their minionListener");
				if(f.get(minion) != kvit)
					fail("The minions in the Warlock's deck should have their Warlock as their minionListener");
			}
		}
	}
	
	@Test(timeout = 3000)
	public void testMageMinionsHaveListeners() throws Exception {
		Mage diakao = new Mage();
		for(int i = 0 ; i < diakao.getDeck().size();i++){
			if(diakao.getDeck().get(i) instanceof Minion)
			{
				Minion minion = (Minion)(diakao.getDeck().get(i));
				Field f = Minion.class.getDeclaredField("listener");
				f.setAccessible(true);
				if(f.get(minion) == null)
					fail("The minions in the Warlock's deck should have their Warlock as their minionListener");
				if(f.get(minion) != diakao)
					fail("The minions in the Warlock's deck should have their Warlock as their minionListener");
			}
		}
	}
	
	@Test(timeout = 10000)
	public void testPriestMinionsAreCloned()throws Exception{
		for(int i = 0 ; i < 100;i++){
			Priest valerienne = new Priest();
			Set<Card> set = new HashSet<Card>(valerienne.getDeck());
			if(set.size() < valerienne.getDeck().size())
				fail("When creating the Priest's deck, a clone of the card should be added to the deck instead of the card itself.");
		}
	}	
	
	
	@Test(timeout = 10000)
	public void testMageMinionsAreCloned()throws Exception{
		for(int i = 0 ; i < 100;i++){
			Mage diakao = new Mage();
			Set<Card> set = new HashSet<Card>(diakao.getDeck());
			if(set.size() < diakao.getDeck().size())
				fail("When creating the Mage's deck, a clone of the card should be added to the deck instead of the card itself.");
		}
	}	
	
	
	@Test(timeout = 10000)
	public void testWarlockMinionsAreCloned()throws Exception{
		for(int i = 0 ; i < 100;i++){
			Warlock kvit = new Warlock();
			Set<Card> set = new HashSet<Card>(kvit.getDeck());
			if(set.size() < kvit.getDeck().size())
				fail("When creating the Warlock's deck, a clone of the card should be added to the deck instead of the card itself.");
		}
	}	
	
	
	@Test(timeout = 10000)
	public void testHunterMinionsAreCloned()throws Exception{
		for(int i = 0 ; i < 100;i++){
			Hunter alluka = new Hunter();
			Set<Card> set = new HashSet<Card>(alluka.getDeck());
			if(set.size() < alluka.getDeck().size())
				fail("When creating the Hunter's deck, a clone of the card should be added to the deck instead of the card itself.");
		}
	}	
	
	@Test(timeout = 3000)
	public void testPolymorphedIcehowlAttacksHero() throws Exception 
	{
		Icehowl attackerMinion = new Icehowl();
		Polymorph polymorph = new Polymorph();
		polymorph.performAction(attackerMinion);
		Mage attackedHero = new Mage();
		
		try
		{
			attackerMinion.attack(attackedHero);
		}
		catch(InvalidTargetException e)
		{
			fail("A polymorphed Icehowl should be able to attack heros.");
		}
	}
	
	@Test(timeout = 3000)
	public void testShadowWordDeathKillingMinion() throws Exception 
	{
		Minion minion = new Minion("test", 0, null, 9, 1, false, false, false);
		
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
		public void testKillCommandKillingMinion() throws Exception 
		{
			Minion minion = new Minion("test", 0, null, 1, 5, false, false, false);
			
			MinionListener minionListener = new MinionListener() {
				
				@Override
				public void onMinionDeath(Minion m) {
					callParameters.add(m);
				}
			};
			
			minion.setListener(minionListener);
			
			KillCommand killCommand = new KillCommand();
			
			killCommand.performAction(minion);
			if(callParameters.size() != 1 || callParameters.get(0) != minion)
				fail("If KillCommand resulted on killing a minion, they should announce thier death.");
		}
	//////////////////////// HELPERS ///////////////////////////
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

}
