package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class M1PublicTests
{

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

	@Test(timeout = 1000)
	public void testClassIsEnumRarity() throws Exception
	{
		testIsEnum(Class.forName(rarityPath));
	}


	@Test(timeout = 1000)
	public void testEnumValuesRarity() throws Exception
	{
		String[] inputs = {"BASIC", "COMMON", "RARE", "EPIC", "LEGENDARY"};
		testEnumValues(Class.forName(rarityPath), inputs);
	}


	@Test(timeout = 1000)
	public void testClassIsInterfaceAOESpell() throws Exception
	{
		testIsInterface(Class.forName(aOESpellPath));
	}


	@Test(timeout = 1000)
	public void testClassIsInterfaceFieldSpell() throws Exception
	{
		testIsInterface(Class.forName(fieldSpellPath));
	}


	@Test(timeout = 1000)
	public void testClassIsInterfaceLeechingSpell() throws Exception
	{
		testIsInterface(Class.forName(leechingSpellPath));
	}


	@Test(timeout = 1000)
	public void testClassIsInterfaceMinionTargetSpell() throws Exception
	{
		testIsInterface(Class.forName(minionTargetSpellPath));
	}


	@Test(timeout = 1000)
	public void testClassIsAbstractHero() throws Exception
	{
		testClassIsAbstract(Class.forName(heroPath));
	}


	@Test(timeout = 1000)
	public void testClassIsAbstractCard() throws Exception
	{
		testClassIsAbstract(Class.forName(cardPath));
	}


	@Test(timeout = 1000)
	public void testClassIsAbstractHearthstoneException() throws Exception
	{
		testClassIsAbstract(Class.forName(hearthstoneExceptionPath));
	}


	@Test(timeout = 1000)
	public void testHeroIsSuperClassOfWarlock() throws Exception
	{
		testClassIsSubclass(Class.forName(warlockPath), Class.forName(heroPath));
	}


	@Test(timeout = 1000)
	public void testHeroIsSuperClassOfPriest() throws Exception
	{
		testClassIsSubclass(Class.forName(priestPath), Class.forName(heroPath));
	}


	@Test(timeout = 1000)
	public void testHeroIsSuperClassOfMage() throws Exception
	{
		testClassIsSubclass(Class.forName(magePath), Class.forName(heroPath));
	}


	@Test(timeout = 1000)
	public void testHeroIsSuperClassOfHunter() throws Exception
	{
		testClassIsSubclass(Class.forName(hunterPath), Class.forName(heroPath));
	}


	@Test(timeout = 1000)
	public void testSpellIsSuperClassOfTwistingNether() throws Exception
	{
		testClassIsSubclass(Class.forName(twistingNetherPath), Class.forName(spellPath));
	}


	@Test(timeout = 1000)
	public void testSpellIsSuperClassOfSiphonSoul() throws Exception
	{
		testClassIsSubclass(Class.forName(siphonSoulPath), Class.forName(spellPath));
	}


	@Test(timeout = 1000)
	public void testSpellIsSuperClassOfSealOfChampions() throws Exception
	{
		testClassIsSubclass(Class.forName(sealOfChampionsPath), Class.forName(spellPath));
	}


	@Test(timeout = 1000)
	public void testSpellIsSuperClassOfPyroblast() throws Exception
	{
		testClassIsSubclass(Class.forName(pyroblastPath), Class.forName(spellPath));
	}


	@Test(timeout = 1000)
	public void testSpellIsSuperClassOfMultiShot() throws Exception
	{
		testClassIsSubclass(Class.forName(multiShotPath), Class.forName(spellPath));
	}


	@Test(timeout = 1000)
	public void testSpellIsSuperClassOfLevelUp() throws Exception
	{
		testClassIsSubclass(Class.forName(levelUpPath), Class.forName(spellPath));
	}


	@Test(timeout = 1000)
	public void testSpellIsSuperClassOfHolyNova() throws Exception
	{
		testClassIsSubclass(Class.forName(holyNovaPath), Class.forName(spellPath));
	}


	@Test(timeout = 1000)
	public void testSpellIsSuperClassOfFlamestrike() throws Exception
	{
		testClassIsSubclass(Class.forName(flamestrikePath), Class.forName(spellPath));
	}


	@Test(timeout = 1000)
	public void testSpellIsSuperClassOfCurseOfWeakness() throws Exception
	{
		testClassIsSubclass(Class.forName(curseOfWeaknessPath), Class.forName(spellPath));
	}


	@Test(timeout = 1000)
	public void testCardIsSuperClassOfMinion() throws Exception
	{
		testClassIsSubclass(Class.forName(minionPath), Class.forName(cardPath));
	}


	@Test(timeout = 1000)
	public void testHearthstoneExceptionIsSuperClassOfTauntBypassException() throws Exception
	{
		testClassIsSubclass(Class.forName(tauntBypassExceptionPath), Class.forName(hearthstoneExceptionPath));
	}


	@Test(timeout = 1000)
	public void testHearthstoneExceptionIsSuperClassOfNotYourTurnException() throws Exception
	{
		testClassIsSubclass(Class.forName(notYourTurnExceptionPath), Class.forName(hearthstoneExceptionPath));
	}


	@Test(timeout = 1000)
	public void testHearthstoneExceptionIsSuperClassOfNotEnoughManaException() throws Exception
	{
		testClassIsSubclass(Class.forName(notEnoughManaExceptionPath), Class.forName(hearthstoneExceptionPath));
	}


	@Test(timeout = 1000)
	public void testHearthstoneExceptionIsSuperClassOfInvalidTargetException() throws Exception
	{
		testClassIsSubclass(Class.forName(invalidTargetExceptionPath), Class.forName(hearthstoneExceptionPath));
	}


	@Test(timeout = 1000)
	public void testHearthstoneExceptionIsSuperClassOfFullHandException() throws Exception
	{
		testClassIsSubclass(Class.forName(fullHandExceptionPath), Class.forName(hearthstoneExceptionPath));
	}


	@Test(timeout = 1000)
	public void testHearthstoneExceptionIsSuperClassOfFullFieldException() throws Exception
	{
		testClassIsSubclass(Class.forName(fullFieldExceptionPath), Class.forName(hearthstoneExceptionPath));
	}


	@Test(timeout = 1000)
	public void testInterfaceAOESpellIsImplementedByClassTwistingNether() throws Exception
	{
		testClassImplementsInterface(Class.forName(twistingNetherPath), Class.forName(aOESpellPath));
	}


	@Test(timeout = 1000)
	public void testInterfaceLeechingSpellIsImplementedByClassSiphonSoul() throws Exception
	{
		testClassImplementsInterface(Class.forName(siphonSoulPath), Class.forName(leechingSpellPath));
	}


	@Test(timeout = 1000)
	public void testInterfaceMinionTargetSpellIsImplementedByClassSealOfChampions() throws Exception
	{
		testClassImplementsInterface(Class.forName(sealOfChampionsPath), Class.forName(minionTargetSpellPath));
	}


	@Test(timeout = 1000)
	public void testInterfaceHeroTargetSpellIsImplementedByClassPyroblast() throws Exception
	{
		testClassImplementsInterface(Class.forName(pyroblastPath), Class.forName(heroTargetSpellPath));
	}


	@Test(timeout = 1000)
	public void testInterfaceMinionTargetSpellIsImplementedByClassPolymorph() throws Exception
	{
		testClassImplementsInterface(Class.forName(polymorphPath), Class.forName(minionTargetSpellPath));
	}


	@Test(timeout = 1000)
	public void testInterfaceAOESpellIsImplementedByClassMultiShot() throws Exception
	{
		testClassImplementsInterface(Class.forName(multiShotPath), Class.forName(aOESpellPath));
	}


	@Test(timeout = 1000)
	public void testInterfaceHeroTargetSpellIsImplementedByClassKillCommand() throws Exception
	{
		testClassImplementsInterface(Class.forName(killCommandPath), Class.forName(heroTargetSpellPath));
	}


	@Test(timeout = 1000)
	public void testInterfaceMinionTargetSpellIsImplementedByClassKillCommand() throws Exception
	{
		testClassImplementsInterface(Class.forName(killCommandPath), Class.forName(minionTargetSpellPath));
	}


	@Test(timeout = 1000)
	public void testInterfaceAOESpellIsImplementedByClassFlamestrike() throws Exception
	{
		testClassImplementsInterface(Class.forName(flamestrikePath), Class.forName(aOESpellPath));
	}


	@Test(timeout = 1000)
	public void testInterfaceMinionTargetSpellIsImplementedByClassDivineSpirit() throws Exception
	{
		testClassImplementsInterface(Class.forName(divineSpiritPath), Class.forName(minionTargetSpellPath));
	}


	@Test(timeout = 1000)
	public void testConstructor0Hero() throws Exception
	{
		Class[] inputs = {String.class};
		testConstructorExists(Class.forName(heroPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0Warlock() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(warlockPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0Paladin() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(paladinPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0Mage() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(magePath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0Card() throws Exception
	{
		Class[] inputs = {String.class, int.class, Class.forName(rarityPath)};
		testConstructorExists(Class.forName(cardPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0Spell() throws Exception
	{
		Class[] inputs = {String.class, int.class, Class.forName(rarityPath)};
		testConstructorExists(Class.forName(spellPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0SiphonSoul() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(siphonSoulPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0ShadowWordDeath() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(shadowWordDeathPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0Pyroblast() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(pyroblastPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0Polymorph() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(polymorphPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0LevelUp() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(levelUpPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0KillCommand() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(killCommandPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0Flamestrike() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(flamestrikePath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0DivineSpirit() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(divineSpiritPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0Minion() throws Exception
	{
		Class[] inputs = {String.class, int.class, Class.forName(rarityPath), int.class, int.class, boolean.class, boolean.class, boolean.class};
		testConstructorExists(Class.forName(minionPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0Icehowl() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(icehowlPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor1HearthstoneException() throws Exception
	{
		Class[] inputs = {String.class};
		testConstructorExists(Class.forName(hearthstoneExceptionPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0TauntBypassException() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(tauntBypassExceptionPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0NotYourTurnException() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(notYourTurnExceptionPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor1NotYourTurnException() throws Exception
	{
		Class[] inputs = {String.class};
		testConstructorExists(Class.forName(notYourTurnExceptionPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor1NotSummonedException() throws Exception
	{
		Class[] inputs = {String.class};
		testConstructorExists(Class.forName(notSummonedExceptionPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0NotEnoughManaException() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(notEnoughManaExceptionPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0InvalidTargetException() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(invalidTargetExceptionPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor1InvalidTargetException() throws Exception
	{
		Class[] inputs = {String.class};
		testConstructorExists(Class.forName(invalidTargetExceptionPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor1HeroPowerAlreadyUsedException() throws Exception
	{
		Class[] inputs = {String.class};
		testConstructorExists(Class.forName(heroPowerAlreadyUsedExceptionPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0FullHandException() throws Exception
	{
		Class[] inputs = {Class.forName(cardPath)};
		testConstructorExists(Class.forName(fullHandExceptionPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0FullFieldException() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(fullFieldExceptionPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor1FullFieldException() throws Exception
	{
		Class[] inputs = {String.class};
		testConstructorExists(Class.forName(fullFieldExceptionPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor1CannotAttackException() throws Exception
	{
		Class[] inputs = {String.class};
		testConstructorExists(Class.forName(cannotAttackExceptionPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0Game() throws Exception
	{
		Class[] inputs = {Class.forName(heroPath), Class.forName(heroPath)};
		testConstructorExists(Class.forName(gamePath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization1700Warlock() throws Exception
	{
		Object warlock00 = Class.forName(warlockPath).getConstructor().newInstance();

		String guldanString = "Gul'dan";
		int int30 = 30;
		boolean boolean02 = false;
		int int00 = 0;
		String[] names = {"name", "currentHP", "heroPowerUsed", "totalManaCrystals", "currentManaCrystals", "fatigueDamage"};
		Object[] values = {guldanString, int30, boolean02, int00, int00, int00};

		testConstructorInitialization(warlock00, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization1800Priest() throws Exception
	{
		Object priest00 = Class.forName(priestPath).getConstructor().newInstance();

		String anduinWrynnString = "Anduin Wrynn";
		int int30 = 30;
		boolean boolean02 = false;
		int int00 = 0;
		String[] names = {"name", "currentHP", "heroPowerUsed", "totalManaCrystals", "currentManaCrystals", "fatigueDamage"};
		Object[] values = {anduinWrynnString, int30, boolean02, int00, int00, int00};

		testConstructorInitialization(priest00, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization2000Mage() throws Exception
	{
		Object mage00 = Class.forName(magePath).getConstructor().newInstance();

		String jainaProudmooreString = "Jaina Proudmoore";
		int int30 = 30;
		boolean boolean02 = false;
		int int00 = 0;
		String[] names = {"name", "currentHP", "heroPowerUsed", "totalManaCrystals", "currentManaCrystals", "fatigueDamage"};
		Object[] values = {jainaProudmooreString, int30, boolean02, int00, int00, int00};

		testConstructorInitialization(mage00, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization2100Hunter() throws Exception
	{
		Object hunter00 = Class.forName(hunterPath).getConstructor().newInstance();

		String rexxarString = "Rexxar";
		int int30 = 30;
		boolean boolean02 = false;
		int int00 = 0;
		String[] names = {"name", "currentHP", "heroPowerUsed", "totalManaCrystals", "currentManaCrystals", "fatigueDamage"};
		Object[] values = {rexxarString, int30, boolean02, int00, int00, int00};

		testConstructorInitialization(hunter00, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization2500SiphonSoul() throws Exception
	{
		Object siphonSoul00 = Class.forName(siphonSoulPath).getConstructor().newInstance();

		String siphonSoulString = "Siphon Soul";
		int int06 = 6;
		Object rarity02 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "RARE");
		String[] names = {"name", "manaCost", "rarity"};
		Object[] values = {siphonSoulString, int06, rarity02};

		testConstructorInitialization(siphonSoul00, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization2600ShadowWordDeath() throws Exception
	{
		Object shadowWordDeath00 = Class.forName(shadowWordDeathPath).getConstructor().newInstance();

		String shadowWordDeathString = "Shadow Word: Death";
		int int03 = 3;
		Object rarity00 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "BASIC");
		String[] names = {"name", "manaCost", "rarity"};
		Object[] values = {shadowWordDeathString, int03, rarity00};

		testConstructorInitialization(shadowWordDeath00, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization2800Pyroblast() throws Exception
	{
		Object pyroblast00 = Class.forName(pyroblastPath).getConstructor().newInstance();

		String pyroblastString = "Pyroblast";
		int int10 = 10;
		Object rarity03 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "EPIC");
		String[] names = {"name", "manaCost", "rarity"};
		Object[] values = {pyroblastString, int10, rarity03};

		testConstructorInitialization(pyroblast00, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization2900Polymorph() throws Exception
	{
		Object polymorph00 = Class.forName(polymorphPath).getConstructor().newInstance();

		String polymorphString = "Polymorph";
		int int04 = 4;
		Object rarity00 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "BASIC");
		String[] names = {"name", "manaCost", "rarity"};
		Object[] values = {polymorphString, int04, rarity00};

		testConstructorInitialization(polymorph00, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization3100LevelUp() throws Exception
	{
		Object levelUp00 = Class.forName(levelUpPath).getConstructor().newInstance();

		String levelUpString = "Level Up!";
		int int06 = 6;
		Object rarity03 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "EPIC");
		String[] names = {"name", "manaCost", "rarity"};
		Object[] values = {levelUpString, int06, rarity03};

		testConstructorInitialization(levelUp00, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization3200KillCommand() throws Exception
	{
		Object killCommand00 = Class.forName(killCommandPath).getConstructor().newInstance();

		String killCommandString = "Kill Command";
		int int03 = 3;
		Object rarity01 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "COMMON");
		String[] names = {"name", "manaCost", "rarity"};
		Object[] values = {killCommandString, int03, rarity01};

		testConstructorInitialization(killCommand00, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization3400Flamestrike() throws Exception
	{
		Object flamestrike00 = Class.forName(flamestrikePath).getConstructor().newInstance();

		String flamestrikeString = "Flamestrike";
		int int07 = 7;
		Object rarity00 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "BASIC");
		String[] names = {"name", "manaCost", "rarity"};
		Object[] values = {flamestrikeString, int07, rarity00};

		testConstructorInitialization(flamestrike00, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization3500DivineSpirit() throws Exception
	{
		Object divineSpirit00 = Class.forName(divineSpiritPath).getConstructor().newInstance();

		String divineSpiritString = "Divine Spirit";
		int int03 = 3;
		Object rarity00 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "BASIC");
		String[] names = {"name", "manaCost", "rarity"};
		Object[] values = {divineSpiritString, int03, rarity00};

		testConstructorInitialization(divineSpirit00, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization3700Minion() throws Exception
	{
		String string00 = "d02";
		int int75 = 75;
		Object rarity04 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		int int83 = 83;
		int int41 = 41;
		boolean boolean01 = true;
		boolean boolean02 = false;
		Object minion00 = Class.forName(minionPath).getConstructor(String.class, int.class, Class.forName(rarityPath), int.class, int.class, boolean.class, boolean.class, boolean.class).newInstance(string00, int75, rarity04, int83, int41, boolean01, boolean01, boolean02);
		int int10 = 10;
		String[] names = {"attack", "maxHP", "currentHP", "taunt", "divine", "sleeping", "attacked", "name", "manaCost", "rarity"};
		Object[] values = {int83, int41, int41, boolean01, boolean01, boolean01, boolean02, string00, int10, rarity04};

		testConstructorInitialization(minion00, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization3800Icehowl() throws Exception
	{
		Object icehowl00 = Class.forName(icehowlPath).getConstructor().newInstance();

		int int10 = 10;
		boolean boolean02 = false;
		String icehowlString = "Icehowl";
		int int09 = 9;
		Object rarity04 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		String[] names = {"attack", "maxHP", "currentHP", "taunt", "divine", "sleeping", "attacked", "name", "manaCost", "rarity"};
		Object[] values = {int10, int10, int10, boolean02, boolean02, boolean02, boolean02, icehowlString, int09, rarity04};

		testConstructorInitialization(icehowl00, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization4001TauntBypassException() throws Exception
	{
		Object tauntBypassException01 = Class.forName(tauntBypassExceptionPath).getConstructor().newInstance();

		String[] names = {};
		Object[] values = {};

		testConstructorInitialization(tauntBypassException01, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization4100NotYourTurnException() throws Exception
	{
		String string00 = "d02";
		Object notYourTurnException00 = Class.forName(notYourTurnExceptionPath).getConstructor(String.class).newInstance(string00);

		testConstructorInitializationException(notYourTurnException00, string00);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization4200NotSummonedException() throws Exception
	{
		String string00 = "d02";
		Object notSummonedException00 = Class.forName(notSummonedExceptionPath).getConstructor(String.class).newInstance(string00);

		testConstructorInitializationException(notSummonedException00, string00);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization4201NotSummonedException() throws Exception
	{
		Object notSummonedException01 = Class.forName(notSummonedExceptionPath).getConstructor().newInstance();
		
		String[] names = {};
		Object[] values = {};

		testConstructorInitialization(notSummonedException01, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization4301NotEnoughManaException() throws Exception
	{
		Object notEnoughManaException01 = Class.forName(notEnoughManaExceptionPath).getConstructor().newInstance();
		
		String[] names = {};
		Object[] values = {};

		testConstructorInitialization(notEnoughManaException01, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization4400InvalidTargetException() throws Exception
	{
		String string00 = "d02";
		Object invalidTargetException00 = Class.forName(invalidTargetExceptionPath).getConstructor(String.class).newInstance(string00);

		testConstructorInitializationException(invalidTargetException00, string00);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization4500HeroPowerAlreadyUsedException() throws Exception
	{
		String string00 = "d02";
		Object heroPowerAlreadyUsedException00 = Class.forName(heroPowerAlreadyUsedExceptionPath).getConstructor(String.class).newInstance(string00);

		testConstructorInitializationException(heroPowerAlreadyUsedException00, string00);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization4501HeroPowerAlreadyUsedException() throws Exception
	{
		Object heroPowerAlreadyUsedException01 = Class.forName(heroPowerAlreadyUsedExceptionPath).getConstructor().newInstance();
		
		String[] names = {};
		Object[] values = {};

		testConstructorInitialization(heroPowerAlreadyUsedException01, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization4601FullHandException() throws Exception
	{
		Object multiShot00 = Class.forName(multiShotPath).getConstructor().newInstance();

		Object fullHandException01 = Class.forName(fullHandExceptionPath).getConstructor(Class.forName(cardPath)).newInstance(multiShot00);

		String[] names = {"burned"};
		Object[] values = {multiShot00};

		testConstructorInitialization(fullHandException01, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization4800CannotAttackException() throws Exception
	{
		String string00 = "d02";
		Object cannotAttackException00 = Class.forName(cannotAttackExceptionPath).getConstructor(String.class).newInstance(string00);

		testConstructorInitializationException(cannotAttackException00, string00);
	}


	

	@Test(timeout = 1000)
	public void testInstanceVariableNameInClassIsPresentAndIsPrivateHero() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(heroPath), "name", true);
		testInstanceVariableIsPrivate(Class.forName(heroPath), "name");
	}


	@Test(timeout = 1000)
	public void testInstanceVariableCurrentHPInClassIsPresentAndIsPrivateHero() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(heroPath), "currentHP", true);
		testInstanceVariableIsPrivate(Class.forName(heroPath), "currentHP");
	}


	@Test(timeout = 1000)
	public void testInstanceVariableTotalManaCrystalsInClassIsPresentAndIsPrivateHero() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(heroPath), "totalManaCrystals", true);
		testInstanceVariableIsPrivate(Class.forName(heroPath), "totalManaCrystals");
	}


	@Test(timeout = 1000)
	public void testInstanceVariableCurrentManaCrystalsInClassIsPresentAndIsPrivateHero() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(heroPath), "currentManaCrystals", true);
		testInstanceVariableIsPrivate(Class.forName(heroPath), "currentManaCrystals");
	}


	@Test(timeout = 1000)
	public void testInstanceVariableFieldInClassIsPresentAndIsPrivateHero() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(heroPath), "field", true);
		testInstanceVariableIsPrivate(Class.forName(heroPath), "field");
	}


	@Test(timeout = 1000)
	public void testInstanceVariableHandInClassIsPresentAndIsPrivateHero() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(heroPath), "hand", true);
		testInstanceVariableIsPrivate(Class.forName(heroPath), "hand");
	}


	@Test(timeout = 1000)
	public void testInstanceVariableNameInClassIsPresentAndIsPrivateCard() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(cardPath), "name", true);
		testInstanceVariableIsPrivate(Class.forName(cardPath), "name");
	}


	@Test(timeout = 1000)
	public void testInstanceVariableManaCostInClassIsPresentAndIsPrivateCard() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(cardPath), "manaCost", true);
		testInstanceVariableIsPrivate(Class.forName(cardPath), "manaCost");
	}


	@Test(timeout = 1000)
	public void testInstanceVariableAttackInClassIsPresentAndIsPrivateMinion() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(minionPath), "attack", true);
		testInstanceVariableIsPrivate(Class.forName(minionPath), "attack");
	}


	@Test(timeout = 1000)
	public void testInstanceVariableMaxHPInClassIsPresentAndIsPrivateMinion() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(minionPath), "maxHP", true);
		testInstanceVariableIsPrivate(Class.forName(minionPath), "maxHP");
	}


	@Test(timeout = 1000)
	public void testInstanceVariableTauntInClassIsPresentAndIsPrivateMinion() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(minionPath), "taunt", true);
		testInstanceVariableIsPrivate(Class.forName(minionPath), "taunt");
	}


	@Test(timeout = 1000)
	public void testInstanceVariableDivineInClassIsPresentAndIsPrivateMinion() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(minionPath), "divine", true);
		testInstanceVariableIsPrivate(Class.forName(minionPath), "divine");
	}


	@Test(timeout = 1000)
	public void testInstanceVariableAttackedInClassIsPresentAndIsPrivateMinion() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(minionPath), "attacked", true);
		testInstanceVariableIsPrivate(Class.forName(minionPath), "attacked");
	}


	@Test(timeout = 1000)
	public void testInstanceVariableBurnedInClassIsPresentAndIsPrivateFullHandException() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(fullHandExceptionPath), "burned", true);
		testInstanceVariableIsPrivate(Class.forName(fullHandExceptionPath), "burned");
	}


	@Test(timeout = 1000)
	public void testInstanceVariableSecondHeroInClassIsPresentAndIsPrivateGame() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(gamePath), "secondHero", true);
		testInstanceVariableIsPrivate(Class.forName(gamePath), "secondHero");
	}


	@Test(timeout = 1000)
	public void testInstanceVariableCurrentHeroInClassIsPresentAndIsPrivateGame() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(gamePath), "currentHero", true);
		testInstanceVariableIsPrivate(Class.forName(gamePath), "currentHero");
	}


	@Test(timeout = 10000)
	public void testInstanceVariablePresentInClassFatigueDamageHandFieldDeckManaCrystalsCurrentTotalHeroPowerUsedHPName() throws Exception
	{
		testInstanceVariableIsPresent(Class.forName(warlockPath), "name", false);
		testInstanceVariableIsPresent(Class.forName(priestPath), "name", false);
		testInstanceVariableIsPresent(Class.forName(paladinPath), "name", false);
		testInstanceVariableIsPresent(Class.forName(magePath), "name", false);
		testInstanceVariableIsPresent(Class.forName(hunterPath), "name", false);
		testInstanceVariableIsPresent(Class.forName(warlockPath), "currentHP", false);
		testInstanceVariableIsPresent(Class.forName(priestPath), "currentHP", false);
		testInstanceVariableIsPresent(Class.forName(paladinPath), "currentHP", false);
		testInstanceVariableIsPresent(Class.forName(magePath), "currentHP", false);
		testInstanceVariableIsPresent(Class.forName(hunterPath), "currentHP", false);
		testInstanceVariableIsPresent(Class.forName(warlockPath), "heroPowerUsed", false);
		testInstanceVariableIsPresent(Class.forName(priestPath), "heroPowerUsed", false);
		testInstanceVariableIsPresent(Class.forName(paladinPath), "heroPowerUsed", false);
		testInstanceVariableIsPresent(Class.forName(magePath), "heroPowerUsed", false);
		testInstanceVariableIsPresent(Class.forName(hunterPath), "heroPowerUsed", false);
		testInstanceVariableIsPresent(Class.forName(warlockPath), "totalManaCrystals", false);
		testInstanceVariableIsPresent(Class.forName(priestPath), "totalManaCrystals", false);
		testInstanceVariableIsPresent(Class.forName(paladinPath), "totalManaCrystals", false);
		testInstanceVariableIsPresent(Class.forName(magePath), "totalManaCrystals", false);
		testInstanceVariableIsPresent(Class.forName(hunterPath), "totalManaCrystals", false);
		testInstanceVariableIsPresent(Class.forName(warlockPath), "currentManaCrystals", false);
		testInstanceVariableIsPresent(Class.forName(priestPath), "currentManaCrystals", false);
		testInstanceVariableIsPresent(Class.forName(paladinPath), "currentManaCrystals", false);
		testInstanceVariableIsPresent(Class.forName(magePath), "currentManaCrystals", false);
		testInstanceVariableIsPresent(Class.forName(hunterPath), "currentManaCrystals", false);
		testInstanceVariableIsPresent(Class.forName(warlockPath), "deck", false);
		testInstanceVariableIsPresent(Class.forName(priestPath), "deck", false);
		testInstanceVariableIsPresent(Class.forName(paladinPath), "deck", false);
		testInstanceVariableIsPresent(Class.forName(magePath), "deck", false);
		testInstanceVariableIsPresent(Class.forName(hunterPath), "deck", false);
		testInstanceVariableIsPresent(Class.forName(warlockPath), "field", false);
		testInstanceVariableIsPresent(Class.forName(priestPath), "field", false);
		testInstanceVariableIsPresent(Class.forName(paladinPath), "field", false);
		testInstanceVariableIsPresent(Class.forName(magePath), "field", false);
		testInstanceVariableIsPresent(Class.forName(hunterPath), "field", false);
		testInstanceVariableIsPresent(Class.forName(warlockPath), "hand", false);
		testInstanceVariableIsPresent(Class.forName(priestPath), "hand", false);
		testInstanceVariableIsPresent(Class.forName(paladinPath), "hand", false);
		testInstanceVariableIsPresent(Class.forName(magePath), "hand", false);
		testInstanceVariableIsPresent(Class.forName(hunterPath), "hand", false);
		testInstanceVariableIsPresent(Class.forName(warlockPath), "fatigueDamage", false);
		testInstanceVariableIsPresent(Class.forName(priestPath), "fatigueDamage", false);
		testInstanceVariableIsPresent(Class.forName(paladinPath), "fatigueDamage", false);
		testInstanceVariableIsPresent(Class.forName(magePath), "fatigueDamage", false);
		testInstanceVariableIsPresent(Class.forName(hunterPath), "fatigueDamage", false);
	}


	@Test(timeout = 10000)
	public void testInstanceVariablePresentInClassRarityManaCostName() throws Exception
	{
		testInstanceVariableIsPresent(Class.forName(spellPath), "name", false);
		testInstanceVariableIsPresent(Class.forName(twistingNetherPath), "name", false);
		testInstanceVariableIsPresent(Class.forName(siphonSoulPath), "name", false);
		testInstanceVariableIsPresent(Class.forName(shadowWordDeathPath), "name", false);
		testInstanceVariableIsPresent(Class.forName(sealOfChampionsPath), "name", false);
		testInstanceVariableIsPresent(Class.forName(pyroblastPath), "name", false);
		testInstanceVariableIsPresent(Class.forName(polymorphPath), "name", false);
		testInstanceVariableIsPresent(Class.forName(multiShotPath), "name", false);
		testInstanceVariableIsPresent(Class.forName(levelUpPath), "name", false);
		testInstanceVariableIsPresent(Class.forName(killCommandPath), "name", false);
		testInstanceVariableIsPresent(Class.forName(holyNovaPath), "name", false);
		testInstanceVariableIsPresent(Class.forName(flamestrikePath), "name", false);
		testInstanceVariableIsPresent(Class.forName(divineSpiritPath), "name", false);
		testInstanceVariableIsPresent(Class.forName(curseOfWeaknessPath), "name", false);
		testInstanceVariableIsPresent(Class.forName(minionPath), "name", false);
		testInstanceVariableIsPresent(Class.forName(icehowlPath), "name", false);
		testInstanceVariableIsPresent(Class.forName(spellPath), "manaCost", false);
		testInstanceVariableIsPresent(Class.forName(twistingNetherPath), "manaCost", false);
		testInstanceVariableIsPresent(Class.forName(siphonSoulPath), "manaCost", false);
		testInstanceVariableIsPresent(Class.forName(shadowWordDeathPath), "manaCost", false);
		testInstanceVariableIsPresent(Class.forName(sealOfChampionsPath), "manaCost", false);
		testInstanceVariableIsPresent(Class.forName(pyroblastPath), "manaCost", false);
		testInstanceVariableIsPresent(Class.forName(polymorphPath), "manaCost", false);
		testInstanceVariableIsPresent(Class.forName(multiShotPath), "manaCost", false);
		testInstanceVariableIsPresent(Class.forName(levelUpPath), "manaCost", false);
		testInstanceVariableIsPresent(Class.forName(killCommandPath), "manaCost", false);
		testInstanceVariableIsPresent(Class.forName(holyNovaPath), "manaCost", false);
		testInstanceVariableIsPresent(Class.forName(flamestrikePath), "manaCost", false);
		testInstanceVariableIsPresent(Class.forName(divineSpiritPath), "manaCost", false);
		testInstanceVariableIsPresent(Class.forName(curseOfWeaknessPath), "manaCost", false);
		testInstanceVariableIsPresent(Class.forName(minionPath), "manaCost", false);
		testInstanceVariableIsPresent(Class.forName(icehowlPath), "manaCost", false);
		testInstanceVariableIsPresent(Class.forName(spellPath), "rarity", false);
		testInstanceVariableIsPresent(Class.forName(twistingNetherPath), "rarity", false);
		testInstanceVariableIsPresent(Class.forName(siphonSoulPath), "rarity", false);
		testInstanceVariableIsPresent(Class.forName(shadowWordDeathPath), "rarity", false);
		testInstanceVariableIsPresent(Class.forName(sealOfChampionsPath), "rarity", false);
		testInstanceVariableIsPresent(Class.forName(pyroblastPath), "rarity", false);
		testInstanceVariableIsPresent(Class.forName(polymorphPath), "rarity", false);
		testInstanceVariableIsPresent(Class.forName(multiShotPath), "rarity", false);
		testInstanceVariableIsPresent(Class.forName(levelUpPath), "rarity", false);
		testInstanceVariableIsPresent(Class.forName(killCommandPath), "rarity", false);
		testInstanceVariableIsPresent(Class.forName(holyNovaPath), "rarity", false);
		testInstanceVariableIsPresent(Class.forName(flamestrikePath), "rarity", false);
		testInstanceVariableIsPresent(Class.forName(divineSpiritPath), "rarity", false);
		testInstanceVariableIsPresent(Class.forName(curseOfWeaknessPath), "rarity", false);
		testInstanceVariableIsPresent(Class.forName(minionPath), "rarity", false);
		testInstanceVariableIsPresent(Class.forName(icehowlPath), "rarity", false);
	}


	@Test(timeout = 1000)
	public void testGetterForInstanceVariableNameExistsInClassHero() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(heroPath), "getName", String.class, true);
	}


	@Test(timeout = 1000)
	public void testGetterForInstanceVariableCurrentHPExistsInClassHero() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(heroPath), "getCurrentHP", int.class, true);
	}


	@Test(timeout = 1000)
	public void testGetterForInstanceVariableTotalManaCrystalsExistsInClassHero() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(heroPath), "getTotalManaCrystals", int.class, true);
	}


	@Test(timeout = 1000)
	public void testGetterForInstanceVariableCurrentManaCrystalsExistsInClassHero() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(heroPath), "getCurrentManaCrystals", int.class, true);
	}


	@Test(timeout = 1000)
	public void testGetterForInstanceVariableFieldExistsInClassHero() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(heroPath), "getField", ArrayList.class, true);
	}


	@Test(timeout = 1000)
	public void testGetterForInstanceVariableHandExistsInClassHero() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(heroPath), "getHand", ArrayList.class, true);
	}


	@Test(timeout = 1000)
	public void testGetterForInstanceVariableManaCostExistsInClassCard() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(cardPath), "getManaCost", int.class, true);
	}


	@Test(timeout = 1000)
	public void testGetterForInstanceVariableRarityExistsInClassCard() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(cardPath), "getRarity", Class.forName(rarityPath), true);
	}


	@Test(timeout = 1000)
	public void testGetterForInstanceVariableMaxHPExistsInClassMinion() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(minionPath), "getMaxHP", int.class, true);
	}


	@Test(timeout = 1000)
	public void testGetterForInstanceVariableCurrentHPExistsInClassMinion() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(minionPath), "getCurrentHP", int.class, true);
	}


	@Test(timeout = 1000)
	public void testGetterForInstanceVariableDivineExistsInClassMinion() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(minionPath), "isDivine", boolean.class, true);
	}


	@Test(timeout = 1000)
	public void testGetterForInstanceVariableSleepingExistsInClassMinion() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(minionPath), "isSleeping", boolean.class, true);
	}

	@Test(timeout = 1000)
	public void testGetterAndSetterForInstanceVariablesExistsInClassCard() throws Exception
	{
		testSetterMethodExistsInClass(Class.forName(cardPath), "setRarity", Class.forName(rarityPath), false);
	}
	
	@Test(timeout = 1000)
	public void testGetterAndSetterForInstanceVariablesExistsInClassHero() throws Exception
	{
		testSetterMethodExistsInClass(Class.forName(heroPath), "setName", String.class, false);
		testSetterMethodExistsInClass(Class.forName(heroPath), "setDeck", ArrayList.class, false);
		testSetterMethodExistsInClass(Class.forName(heroPath), "setHand", ArrayList.class, false);
		
		testSetterMethodExistsInClass(Class.forName(heroPath), "setFatigueDamage:", int.class, false);
		testGetterMethodExistsInClass(Class.forName(heroPath), "getFatigueDamage:", int.class, false);
	}

	@Test(timeout = 1000)
	public void testGetterAndSetterForInstanceVariablesExistsInClassGame() throws Exception
	{
		testSetterMethodExistsInClass(Class.forName(gamePath), "setFirstHero", Class.forName(heroPath), false);
		testGetterMethodExistsInClass(Class.forName(gamePath), "getFirstHero", Class.forName(heroPath), false);
		
		testSetterMethodExistsInClass(Class.forName(gamePath), "setSecondHero", Class.forName(heroPath), false);
		testGetterMethodExistsInClass(Class.forName(gamePath), "getSecondHero", Class.forName(heroPath), false);
		
		testSetterMethodExistsInClass(Class.forName(gamePath), "setCurrentHero", Class.forName(heroPath), false);
		testGetterMethodExistsInClass(Class.forName(gamePath), "getCurrentHero", Class.forName(heroPath), true);
		
		testSetterMethodExistsInClass(Class.forName(gamePath), "setOpponent", Class.forName(heroPath), false);
		testGetterMethodExistsInClass(Class.forName(gamePath), "getOpponent", Class.forName(heroPath), true);
	}


	@Test(timeout = 1000)
	public void testSetterForInstanceVariableCurrentHPExistsInClassHero() throws Exception
	{
		testSetterMethodExistsInClass(Class.forName(heroPath), "setCurrentHP", int.class, true);
	}


	@Test(timeout = 1000)
	public void testSetterForInstanceVariableHeroPowerUsedExistsInClassHero() throws Exception
	{
		testSetterMethodExistsInClass(Class.forName(heroPath), "setHeroPowerUsed", boolean.class, true);
	}


	@Test(timeout = 1000)
	public void testSetterForInstanceVariableCurrentManaCrystalsExistsInClassHero() throws Exception
	{
		testSetterMethodExistsInClass(Class.forName(heroPath), "setCurrentManaCrystals", int.class, true);
	}


	@Test(timeout = 1000)
	public void testSetterForInstanceVariableNameExistsInClassCard() throws Exception
	{
		testSetterMethodExistsInClass(Class.forName(cardPath), "setName", String.class, true);
	}


	@Test(timeout = 1000)
	public void testSetterForInstanceVariableAttackExistsInClassMinion() throws Exception
	{
		testSetterMethodExistsInClass(Class.forName(minionPath), "setAttack", int.class, true);
	}


	@Test(timeout = 1000)
	public void testSetterForInstanceVariableMaxHPExistsInClassMinion() throws Exception
	{
		testSetterMethodExistsInClass(Class.forName(minionPath), "setMaxHP", int.class, true);
	}


	@Test(timeout = 1000)
	public void testSetterForInstanceVariableTauntExistsInClassMinion() throws Exception
	{
		testSetterMethodExistsInClass(Class.forName(minionPath), "setTaunt", boolean.class, true);
	}


	@Test(timeout = 1000)
	public void testSetterForInstanceVariableDivineExistsInClassMinion() throws Exception
	{
		testSetterMethodExistsInClass(Class.forName(minionPath), "setDivine", boolean.class, true);
	}


	@Test(timeout = 1000)
	public void testSetterForInstanceVariableAttackedExistsInClassMinion() throws Exception
	{
		testSetterMethodExistsInClass(Class.forName(minionPath), "setAttacked", boolean.class, true);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableNamewarlock00guldanStringInClassHero() throws Exception
	{
		Object warlock00 = Class.forName(warlockPath).getConstructor().newInstance();

		String guldanString = "Gul'dan";

		testGetterLogic(warlock00, "name", guldanString);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableNamepriest00anduinWrynnStringInClassHero() throws Exception
	{
		Object priest00 = Class.forName(priestPath).getConstructor().newInstance();

		String anduinWrynnString = "Anduin Wrynn";

		testGetterLogic(priest00, "name", anduinWrynnString);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableNamemage00jainaProudmooreStringInClassHero() throws Exception
	{
		Object mage00 = Class.forName(magePath).getConstructor().newInstance();

		String jainaProudmooreString = "Jaina Proudmoore";

		testGetterLogic(mage00, "name", jainaProudmooreString);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableNamehunter00rexxarStringInClassHero() throws Exception
	{
		Object hunter00 = Class.forName(hunterPath).getConstructor().newInstance();

		String rexxarString = "Rexxar";

		testGetterLogic(hunter00, "name", rexxarString);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableCurrentHPpriest00int30InClassHero() throws Exception
	{
		Object priest00 = Class.forName(priestPath).getConstructor().newInstance();

		int int30 = 30;

		testGetterLogic(priest00, "currentHP", int30);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableCurrentHPpaladin00int30InClassHero() throws Exception
	{
		Object paladin00 = Class.forName(paladinPath).getConstructor().newInstance();

		int int30 = 30;

		testGetterLogic(paladin00, "currentHP", int30);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableCurrentHPhunter00int30InClassHero() throws Exception
	{
		Object hunter00 = Class.forName(hunterPath).getConstructor().newInstance();

		int int30 = 30;

		testGetterLogic(hunter00, "currentHP", int30);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableHeroPowerUsedwarlock00boolean02InClassHero() throws Exception
	{
		Object warlock00 = Class.forName(warlockPath).getConstructor().newInstance();

		boolean boolean02 = false;

		testGetterLogic(warlock00, "heroPowerUsed", boolean02);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableHeroPowerUsedpaladin00boolean02InClassHero() throws Exception
	{
		Object paladin00 = Class.forName(paladinPath).getConstructor().newInstance();

		boolean boolean02 = false;

		testGetterLogic(paladin00, "heroPowerUsed", boolean02);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableHeroPowerUsedmage00boolean02InClassHero() throws Exception
	{
		Object mage00 = Class.forName(magePath).getConstructor().newInstance();

		boolean boolean02 = false;

		testGetterLogic(mage00, "heroPowerUsed", boolean02);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableTotalManaCrystalswarlock00int00InClassHero() throws Exception
	{
		Object warlock00 = Class.forName(warlockPath).getConstructor().newInstance();

		int int00 = 0;

		testGetterLogic(warlock00, "totalManaCrystals", int00);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableTotalManaCrystalspriest00int00InClassHero() throws Exception
	{
		Object priest00 = Class.forName(priestPath).getConstructor().newInstance();

		int int00 = 0;

		testGetterLogic(priest00, "totalManaCrystals", int00);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableTotalManaCrystalsmage00int00InClassHero() throws Exception
	{
		Object mage00 = Class.forName(magePath).getConstructor().newInstance();

		int int00 = 0;

		testGetterLogic(mage00, "totalManaCrystals", int00);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableTotalManaCrystalshunter00int00InClassHero() throws Exception
	{
		Object hunter00 = Class.forName(hunterPath).getConstructor().newInstance();

		int int00 = 0;

		testGetterLogic(hunter00, "totalManaCrystals", int00);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableCurrentManaCrystalspriest00int00InClassHero() throws Exception
	{
		Object priest00 = Class.forName(priestPath).getConstructor().newInstance();

		int int00 = 0;

		testGetterLogic(priest00, "currentManaCrystals", int00);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableCurrentManaCrystalspaladin00int00InClassHero() throws Exception
	{
		Object paladin00 = Class.forName(paladinPath).getConstructor().newInstance();

		int int00 = 0;

		testGetterLogic(paladin00, "currentManaCrystals", int00);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableCurrentManaCrystalshunter00int00InClassHero() throws Exception
	{
		Object hunter00 = Class.forName(hunterPath).getConstructor().newInstance();

		int int00 = 0;

		testGetterLogic(hunter00, "currentManaCrystals", int00);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableFieldwarlock00arrayInClassHero() throws Exception
	{
		Object warlock00 = Class.forName(warlockPath).getConstructor().newInstance();

		ArrayList<Object> array = new ArrayList<Object>();

		testGetterLogic(warlock00, "field", array);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableFieldpaladin00arrayInClassHero() throws Exception
	{
		Object paladin00 = Class.forName(paladinPath).getConstructor().newInstance();

		ArrayList<Object> array = new ArrayList<Object>();

		testGetterLogic(paladin00, "field", array);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableFieldmage00arrayInClassHero() throws Exception
	{
		Object mage00 = Class.forName(magePath).getConstructor().newInstance();

		ArrayList<Object> array = new ArrayList<Object>();

		testGetterLogic(mage00, "field", array);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableHandwarlock00arrayInClassHero() throws Exception
	{
		Object warlock00 = Class.forName(warlockPath).getConstructor().newInstance();

		ArrayList<Object> array = new ArrayList<Object>();

		testGetterLogic(warlock00, "hand", array);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableHandpriest00arrayInClassHero() throws Exception
	{
		Object priest00 = Class.forName(priestPath).getConstructor().newInstance();

		ArrayList<Object> array = new ArrayList<Object>();

		testGetterLogic(priest00, "hand", array);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableHandmage00arrayInClassHero() throws Exception
	{
		Object mage00 = Class.forName(magePath).getConstructor().newInstance();

		ArrayList<Object> array = new ArrayList<Object>();

		testGetterLogic(mage00, "hand", array);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableHandhunter00arrayInClassHero() throws Exception
	{
		Object hunter00 = Class.forName(hunterPath).getConstructor().newInstance();

		ArrayList<Object> array = new ArrayList<Object>();

		testGetterLogic(hunter00, "hand", array);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableNamesiphonSoul00siphonSoulStringInClassCard() throws Exception
	{
		Object siphonSoul00 = Class.forName(siphonSoulPath).getConstructor().newInstance();

		String siphonSoulString = "Siphon Soul";

		testGetterLogic(siphonSoul00, "name", siphonSoulString);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableNameshadowWordDeath00shadowWordDeathStringInClassCard() throws Exception
	{
		Object shadowWordDeath00 = Class.forName(shadowWordDeathPath).getConstructor().newInstance();

		String shadowWordDeathString = "Shadow Word: Death";

		testGetterLogic(shadowWordDeath00, "name", shadowWordDeathString);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableNamepyroblast00pyroblastStringInClassCard() throws Exception
	{
		Object pyroblast00 = Class.forName(pyroblastPath).getConstructor().newInstance();

		String pyroblastString = "Pyroblast";

		testGetterLogic(pyroblast00, "name", pyroblastString);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableNamepolymorph00polymorphStringInClassCard() throws Exception
	{
		Object polymorph00 = Class.forName(polymorphPath).getConstructor().newInstance();

		String polymorphString = "Polymorph";

		testGetterLogic(polymorph00, "name", polymorphString);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableNamelevelUp00levelUpStringInClassCard() throws Exception
	{
		Object levelUp00 = Class.forName(levelUpPath).getConstructor().newInstance();

		String levelUpString = "Level Up!";

		testGetterLogic(levelUp00, "name", levelUpString);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableNamekillCommand00killCommandStringInClassCard() throws Exception
	{
		Object killCommand00 = Class.forName(killCommandPath).getConstructor().newInstance();

		String killCommandString = "Kill Command";

		testGetterLogic(killCommand00, "name", killCommandString);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableNameflamestrike00flamestrikeStringInClassCard() throws Exception
	{
		Object flamestrike00 = Class.forName(flamestrikePath).getConstructor().newInstance();

		String flamestrikeString = "Flamestrike";

		testGetterLogic(flamestrike00, "name", flamestrikeString);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableNamedivineSpirit00divineSpiritStringInClassCard() throws Exception
	{
		Object divineSpirit00 = Class.forName(divineSpiritPath).getConstructor().newInstance();

		String divineSpiritString = "Divine Spirit";

		testGetterLogic(divineSpirit00, "name", divineSpiritString);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableNameminion00string00InClassCard() throws Exception
	{
		String string00 = "d02";
		int int75 = 75;
		Object rarity04 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		int int83 = 83;
		int int41 = 41;
		boolean boolean01 = true;
		boolean boolean02 = false;
		Object minion00 = Class.forName(minionPath).getConstructor(String.class, int.class, Class.forName(rarityPath), int.class, int.class, boolean.class, boolean.class, boolean.class).newInstance(string00, int75, rarity04, int83, int41, boolean01, boolean01, boolean02);


		testGetterLogic(minion00, "name", string00);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableNameicehowl00icehowlStringInClassCard() throws Exception
	{
		Object icehowl00 = Class.forName(icehowlPath).getConstructor().newInstance();

		String icehowlString = "Icehowl";

		testGetterLogic(icehowl00, "name", icehowlString);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableManaCostsiphonSoul00int06InClassCard() throws Exception
	{
		Object siphonSoul00 = Class.forName(siphonSoulPath).getConstructor().newInstance();

		int int06 = 6;

		testGetterLogic(siphonSoul00, "manaCost", int06);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableManaCostshadowWordDeath00int03InClassCard() throws Exception
	{
		Object shadowWordDeath00 = Class.forName(shadowWordDeathPath).getConstructor().newInstance();

		int int03 = 3;

		testGetterLogic(shadowWordDeath00, "manaCost", int03);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableManaCostpyroblast00int10InClassCard() throws Exception
	{
		Object pyroblast00 = Class.forName(pyroblastPath).getConstructor().newInstance();

		int int10 = 10;

		testGetterLogic(pyroblast00, "manaCost", int10);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableManaCostpolymorph00int04InClassCard() throws Exception
	{
		Object polymorph00 = Class.forName(polymorphPath).getConstructor().newInstance();

		int int04 = 4;

		testGetterLogic(polymorph00, "manaCost", int04);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableManaCostlevelUp00int06InClassCard() throws Exception
	{
		Object levelUp00 = Class.forName(levelUpPath).getConstructor().newInstance();

		int int06 = 6;

		testGetterLogic(levelUp00, "manaCost", int06);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableManaCostkillCommand00int03InClassCard() throws Exception
	{
		Object killCommand00 = Class.forName(killCommandPath).getConstructor().newInstance();

		int int03 = 3;

		testGetterLogic(killCommand00, "manaCost", int03);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableManaCostflamestrike00int07InClassCard() throws Exception
	{
		Object flamestrike00 = Class.forName(flamestrikePath).getConstructor().newInstance();

		int int07 = 7;

		testGetterLogic(flamestrike00, "manaCost", int07);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableManaCostdivineSpirit00int03InClassCard() throws Exception
	{
		Object divineSpirit00 = Class.forName(divineSpiritPath).getConstructor().newInstance();

		int int03 = 3;

		testGetterLogic(divineSpirit00, "manaCost", int03);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableManaCostminion00int75InClassCard() throws Exception
	{
		String string00 = "d02";
		int int75 = 75;
		Object rarity04 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		int int83 = 83;
		int int41 = 41;
		boolean boolean01 = true;
		boolean boolean02 = false;
		Object minion00 = Class.forName(minionPath).getConstructor(String.class, int.class, Class.forName(rarityPath), int.class, int.class, boolean.class, boolean.class, boolean.class).newInstance(string00, int75, rarity04, int83, int41, boolean01, boolean01, boolean02);


		testGetterLogic(minion00, "manaCost", int75);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableManaCosticehowl00int09InClassCard() throws Exception
	{
		Object icehowl00 = Class.forName(icehowlPath).getConstructor().newInstance();

		int int09 = 9;

		testGetterLogic(icehowl00, "manaCost", int09);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableRaritysiphonSoul00rarity02InClassCard() throws Exception
	{
		Object siphonSoul00 = Class.forName(siphonSoulPath).getConstructor().newInstance();

		Object rarity02 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "RARE");

		testGetterLogic(siphonSoul00, "rarity", rarity02);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableRarityshadowWordDeath00rarity00InClassCard() throws Exception
	{
		Object shadowWordDeath00 = Class.forName(shadowWordDeathPath).getConstructor().newInstance();

		Object rarity00 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "BASIC");

		testGetterLogic(shadowWordDeath00, "rarity", rarity00);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableRaritypyroblast00rarity03InClassCard() throws Exception
	{
		Object pyroblast00 = Class.forName(pyroblastPath).getConstructor().newInstance();

		Object rarity03 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "EPIC");

		testGetterLogic(pyroblast00, "rarity", rarity03);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableRaritypolymorph00rarity00InClassCard() throws Exception
	{
		Object polymorph00 = Class.forName(polymorphPath).getConstructor().newInstance();

		Object rarity00 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "BASIC");

		testGetterLogic(polymorph00, "rarity", rarity00);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableRaritylevelUp00rarity03InClassCard() throws Exception
	{
		Object levelUp00 = Class.forName(levelUpPath).getConstructor().newInstance();

		Object rarity03 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "EPIC");

		testGetterLogic(levelUp00, "rarity", rarity03);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableRaritykillCommand00rarity01InClassCard() throws Exception
	{
		Object killCommand00 = Class.forName(killCommandPath).getConstructor().newInstance();

		Object rarity01 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "COMMON");

		testGetterLogic(killCommand00, "rarity", rarity01);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableRarityflamestrike00rarity00InClassCard() throws Exception
	{
		Object flamestrike00 = Class.forName(flamestrikePath).getConstructor().newInstance();

		Object rarity00 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "BASIC");

		testGetterLogic(flamestrike00, "rarity", rarity00);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableRaritydivineSpirit00rarity00InClassCard() throws Exception
	{
		Object divineSpirit00 = Class.forName(divineSpiritPath).getConstructor().newInstance();

		Object rarity00 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "BASIC");

		testGetterLogic(divineSpirit00, "rarity", rarity00);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableRarityminion00rarity04InClassCard() throws Exception
	{
		String string00 = "d02";
		int int75 = 75;
		Object rarity04 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		int int83 = 83;
		int int41 = 41;
		boolean boolean01 = true;
		boolean boolean02 = false;
		Object minion00 = Class.forName(minionPath).getConstructor(String.class, int.class, Class.forName(rarityPath), int.class, int.class, boolean.class, boolean.class, boolean.class).newInstance(string00, int75, rarity04, int83, int41, boolean01, boolean01, boolean02);


		testGetterLogic(minion00, "rarity", rarity04);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableRarityicehowl00rarity04InClassCard() throws Exception
	{
		Object icehowl00 = Class.forName(icehowlPath).getConstructor().newInstance();

		Object rarity04 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "LEGENDARY");

		testGetterLogic(icehowl00, "rarity", rarity04);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableMaxHPminion00int41InClassMinion() throws Exception
	{
		String string00 = "d02";
		int int75 = 75;
		Object rarity04 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		int int83 = 83;
		int int41 = 41;
		boolean boolean01 = true;
		boolean boolean02 = false;
		Object minion00 = Class.forName(minionPath).getConstructor(String.class, int.class, Class.forName(rarityPath), int.class, int.class, boolean.class, boolean.class, boolean.class).newInstance(string00, int75, rarity04, int83, int41, boolean01, boolean01, boolean02);


		testGetterLogic(minion00, "maxHP", int41);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableCurrentHPminion00int00InClassMinion() throws Exception
	{
		String string00 = "d02";
		int int75 = 75;
		Object rarity04 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		int int83 = 83;
		int int41 = 41;
		boolean boolean01 = true;
		boolean boolean02 = false;
		Object minion00 = Class.forName(minionPath).getConstructor(String.class, int.class, Class.forName(rarityPath), int.class, int.class, boolean.class, boolean.class, boolean.class).newInstance(string00, int75, rarity04, int83, int41, boolean01, boolean01, boolean02);

		int int00 = 0;

		testGetterLogic(minion00, "currentHP", int00);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableDivineminion00boolean01InClassMinion() throws Exception
	{
		String string00 = "d02";
		int int75 = 75;
		Object rarity04 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		int int83 = 83;
		int int41 = 41;
		boolean boolean01 = true;
		boolean boolean02 = false;
		Object minion00 = Class.forName(minionPath).getConstructor(String.class, int.class, Class.forName(rarityPath), int.class, int.class, boolean.class, boolean.class, boolean.class).newInstance(string00, int75, rarity04, int83, int41, boolean01, boolean01, boolean02);


		testGetterLogic(minion00, "divine", boolean01);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableSleepingminion00boolean01InClassMinion() throws Exception
	{
		String string00 = "d02";
		int int75 = 75;
		Object rarity04 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		int int83 = 83;
		int int41 = 41;
		boolean boolean01 = true;
		boolean boolean02 = false;
		Object minion00 = Class.forName(minionPath).getConstructor(String.class, int.class, Class.forName(rarityPath), int.class, int.class, boolean.class, boolean.class, boolean.class).newInstance(string00, int75, rarity04, int83, int41, boolean01, boolean01, boolean02);


		testGetterLogic(minion00, "sleeping", boolean01);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableCurrentHPwarlock00int54InClassHero() throws Exception
	{
		Object warlock00 = Class.forName(warlockPath).getConstructor().newInstance();

		int int54 = 54;
		int int30 = 30;

		testSetterLogic(warlock00, "currentHP", int54, int30, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableCurrentHPpriest00int12InClassHero() throws Exception
	{
		Object priest00 = Class.forName(priestPath).getConstructor().newInstance();

		int int12 = 12;

		testSetterLogic(priest00, "currentHP", int12, int12, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableCurrentHPmage00int26InClassHero() throws Exception
	{
		Object mage00 = Class.forName(magePath).getConstructor().newInstance();

		int int26 = 26;

		testSetterLogic(mage00, "currentHP", int26, int26, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableCurrentHPhunter00int68InClassHero() throws Exception
	{
		Object hunter00 = Class.forName(hunterPath).getConstructor().newInstance();

		int int68 = 68;
		int int30 = 30;

		testSetterLogic(hunter00, "currentHP", int68, int30, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableHeroPowerUsedpriest00boolean02InClassHero() throws Exception
	{
		Object priest00 = Class.forName(priestPath).getConstructor().newInstance();

		boolean boolean02 = false;

		testSetterLogic(priest00, "heroPowerUsed", boolean02, boolean02, boolean.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableHeroPowerUsedpaladin00boolean01InClassHero() throws Exception
	{
		Object paladin00 = Class.forName(paladinPath).getConstructor().newInstance();

		boolean boolean01 = true;

		testSetterLogic(paladin00, "heroPowerUsed", boolean01, boolean01, boolean.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableHeroPowerUsedhunter00boolean01InClassHero() throws Exception
	{
		Object hunter00 = Class.forName(hunterPath).getConstructor().newInstance();

		boolean boolean01 = true;

		testSetterLogic(hunter00, "heroPowerUsed", boolean01, boolean01, boolean.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableTotalManaCrystalswarlock00int47InClassHero() throws Exception
	{
		Object warlock00 = Class.forName(warlockPath).getConstructor().newInstance();

		int int47 = 47;
		int int10 = 10;

		testSetterLogic(warlock00, "totalManaCrystals", int47, int10, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableTotalManaCrystalspaladin00int36InClassHero() throws Exception
	{
		Object paladin00 = Class.forName(paladinPath).getConstructor().newInstance();

		int int36 = 36;
		int int10 = 10;

		testSetterLogic(paladin00, "totalManaCrystals", int36, int10, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableTotalManaCrystalsmage00int67InClassHero() throws Exception
	{
		Object mage00 = Class.forName(magePath).getConstructor().newInstance();

		int int67 = 67;
		int int10 = 10;

		testSetterLogic(mage00, "totalManaCrystals", int67, int10, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableCurrentManaCrystalswarlock00int28InClassHero() throws Exception
	{
		Object warlock00 = Class.forName(warlockPath).getConstructor().newInstance();

		int int28 = 28;
		int int10 = 10;

		testSetterLogic(warlock00, "currentManaCrystals", int28, int10, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableCurrentManaCrystalspriest00int04InClassHero() throws Exception
	{
		Object priest00 = Class.forName(priestPath).getConstructor().newInstance();

		int int04 = 4;

		testSetterLogic(priest00, "currentManaCrystals", int04, int04, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableCurrentManaCrystalsmage00int52InClassHero() throws Exception
	{
		Object mage00 = Class.forName(magePath).getConstructor().newInstance();

		int int52 = 52;
		int int10 = 10;

		testSetterLogic(mage00, "currentManaCrystals", int52, int10, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableCurrentManaCrystalshunter00int27InClassHero() throws Exception
	{
		Object hunter00 = Class.forName(hunterPath).getConstructor().newInstance();

		int int27 = 27;
		int int10 = 10;

		testSetterLogic(hunter00, "currentManaCrystals", int27, int10, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableNamesiphonSoul00anduinWrynnStringInClassCard() throws Exception
	{
		Object siphonSoul00 = Class.forName(siphonSoulPath).getConstructor().newInstance();

		String anduinWrynnString = "Anduin Wrynn";

		testSetterLogic(siphonSoul00, "name", anduinWrynnString, anduinWrynnString, String.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableNameshadowWordDeath00levelUpStringInClassCard() throws Exception
	{
		Object shadowWordDeath00 = Class.forName(shadowWordDeathPath).getConstructor().newInstance();

		String levelUpString = "Level Up!";

		testSetterLogic(shadowWordDeath00, "name", levelUpString, levelUpString, String.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableNamepyroblast00guldanStringInClassCard() throws Exception
	{
		Object pyroblast00 = Class.forName(pyroblastPath).getConstructor().newInstance();

		String guldanString = "Gul'dan";

		testSetterLogic(pyroblast00, "name", guldanString, guldanString, String.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableNamepolymorph00pyroblastStringInClassCard() throws Exception
	{
		Object polymorph00 = Class.forName(polymorphPath).getConstructor().newInstance();

		String pyroblastString = "Pyroblast";

		testSetterLogic(polymorph00, "name", pyroblastString, pyroblastString, String.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableNamelevelUp00anduinWrynnStringInClassCard() throws Exception
	{
		Object levelUp00 = Class.forName(levelUpPath).getConstructor().newInstance();

		String anduinWrynnString = "Anduin Wrynn";

		testSetterLogic(levelUp00, "name", anduinWrynnString, anduinWrynnString, String.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableNamekillCommand00polymorphStringInClassCard() throws Exception
	{
		Object killCommand00 = Class.forName(killCommandPath).getConstructor().newInstance();

		String polymorphString = "Polymorph";

		testSetterLogic(killCommand00, "name", polymorphString, polymorphString, String.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableNameflamestrike00shadowWordDeathStringInClassCard() throws Exception
	{
		Object flamestrike00 = Class.forName(flamestrikePath).getConstructor().newInstance();

		String shadowWordDeathString = "Shadow Word: Death";

		testSetterLogic(flamestrike00, "name", shadowWordDeathString, shadowWordDeathString, String.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableNamedivineSpirit00guldanStringInClassCard() throws Exception
	{
		Object divineSpirit00 = Class.forName(divineSpiritPath).getConstructor().newInstance();

		String guldanString = "Gul'dan";

		testSetterLogic(divineSpirit00, "name", guldanString, guldanString, String.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableNameminion00polymorphStringInClassCard() throws Exception
	{
		String string00 = "d02";
		int int75 = 75;
		Object rarity04 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		int int83 = 83;
		int int41 = 41;
		boolean boolean01 = true;
		boolean boolean02 = false;
		Object minion00 = Class.forName(minionPath).getConstructor(String.class, int.class, Class.forName(rarityPath), int.class, int.class, boolean.class, boolean.class, boolean.class).newInstance(string00, int75, rarity04, int83, int41, boolean01, boolean01, boolean02);

		String polymorphString = "Polymorph";

		testSetterLogic(minion00, "name", polymorphString, polymorphString, String.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableNameicehowl00jainaProudmooreStringInClassCard() throws Exception
	{
		Object icehowl00 = Class.forName(icehowlPath).getConstructor().newInstance();

		String jainaProudmooreString = "Jaina Proudmoore";

		testSetterLogic(icehowl00, "name", jainaProudmooreString, jainaProudmooreString, String.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableManaCostsiphonSoul00int73InClassCard() throws Exception
	{
		Object siphonSoul00 = Class.forName(siphonSoulPath).getConstructor().newInstance();

		int int73 = 73;
		int int10 = 10;

		testSetterLogic(siphonSoul00, "manaCost", int73, int10, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableManaCostshadowWordDeath00int86InClassCard() throws Exception
	{
		Object shadowWordDeath00 = Class.forName(shadowWordDeathPath).getConstructor().newInstance();

		int int86 = 86;
		int int10 = 10;

		testSetterLogic(shadowWordDeath00, "manaCost", int86, int10, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableManaCostpyroblast00int56InClassCard() throws Exception
	{
		Object pyroblast00 = Class.forName(pyroblastPath).getConstructor().newInstance();

		int int56 = 56;
		int int10 = 10;

		testSetterLogic(pyroblast00, "manaCost", int56, int10, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableManaCostpolymorph00int95InClassCard() throws Exception
	{
		Object polymorph00 = Class.forName(polymorphPath).getConstructor().newInstance();

		int int95 = 95;
		int int10 = 10;

		testSetterLogic(polymorph00, "manaCost", int95, int10, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableManaCostlevelUp00int08InClassCard() throws Exception
	{
		Object levelUp00 = Class.forName(levelUpPath).getConstructor().newInstance();

		int int08 = 8;

		testSetterLogic(levelUp00, "manaCost", int08, int08, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableManaCostkillCommand00int13InClassCard() throws Exception
	{
		Object killCommand00 = Class.forName(killCommandPath).getConstructor().newInstance();

		int int13 = 13;
		int int10 = 10;

		testSetterLogic(killCommand00, "manaCost", int13, int10, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableManaCostflamestrike00int59InClassCard() throws Exception
	{
		Object flamestrike00 = Class.forName(flamestrikePath).getConstructor().newInstance();

		int int59 = 59;
		int int10 = 10;

		testSetterLogic(flamestrike00, "manaCost", int59, int10, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableManaCostdivineSpirit00int30InClassCard() throws Exception
	{
		Object divineSpirit00 = Class.forName(divineSpiritPath).getConstructor().newInstance();

		int int30 = 30;
		int int10 = 10;

		testSetterLogic(divineSpirit00, "manaCost", int30, int10, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableManaCostminion00int85InClassCard() throws Exception
	{
		String string00 = "d02";
		int int75 = 75;
		Object rarity04 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		int int83 = 83;
		int int41 = 41;
		boolean boolean01 = true;
		boolean boolean02 = false;
		Object minion00 = Class.forName(minionPath).getConstructor(String.class, int.class, Class.forName(rarityPath), int.class, int.class, boolean.class, boolean.class, boolean.class).newInstance(string00, int75, rarity04, int83, int41, boolean01, boolean01, boolean02);

		int int85 = 85;
		int int10 = 10;

		testSetterLogic(minion00, "manaCost", int85, int10, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableManaCosticehowl00int47InClassCard() throws Exception
	{
		Object icehowl00 = Class.forName(icehowlPath).getConstructor().newInstance();

		int int47 = 47;
		int int10 = 10;

		testSetterLogic(icehowl00, "manaCost", int47, int10, int.class);
	}
	
	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableManaCosticehowl00int50InClassCard() throws Exception
	{
		Object icehowl00 = Class.forName(icehowlPath).getConstructor().newInstance();

		int int50 = -50;
		int int0 = 0;

		testSetterLogic(icehowl00, "manaCost", int50, int0, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableMaxHPminion00int42InClassMinion() throws Exception
	{
		String string00 = "d02";
		int int75 = 75;
		Object rarity04 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		int int83 = 83;
		int int41 = 41;
		boolean boolean01 = true;
		boolean boolean02 = false;
		Object minion00 = Class.forName(minionPath).getConstructor(String.class, int.class, Class.forName(rarityPath), int.class, int.class, boolean.class, boolean.class, boolean.class).newInstance(string00, int75, rarity04, int83, int41, boolean01, boolean01, boolean02);

		int int42 = 42;

		testSetterLogic(minion00, "maxHP", int42, int42, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableCurrentHPminion00int40InClassMinion() throws Exception
	{
		String string00 = "d02";
		int int75 = 75;
		Object rarity04 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		int int83 = 83;
		int int41 = 41;
		boolean boolean01 = true;
		boolean boolean02 = false;
		Object minion00 = Class.forName(minionPath).getConstructor(String.class, int.class, Class.forName(rarityPath), int.class, int.class, boolean.class, boolean.class, boolean.class).newInstance(string00, int75, rarity04, int83, int41, boolean01, boolean01, boolean02);

		int int40 = 40;

		testSetterLogic(minion00, "currentHP", int40, int40, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableDivineminion00boolean01InClassMinion() throws Exception
	{
		String string00 = "d02";
		int int75 = 75;
		Object rarity04 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		int int83 = 83;
		int int41 = 41;
		boolean boolean01 = true;
		boolean boolean02 = false;
		Object minion00 = Class.forName(minionPath).getConstructor(String.class, int.class, Class.forName(rarityPath), int.class, int.class, boolean.class, boolean.class, boolean.class).newInstance(string00, int75, rarity04, int83, int41, boolean01, boolean01, boolean02);


		testSetterLogic(minion00, "divine", boolean01, boolean01, boolean.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableSleepingminion00boolean02InClassMinion() throws Exception
	{
		String string00 = "d02";
		int int75 = 75;
		Object rarity04 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		int int83 = 83;
		int int41 = 41;
		boolean boolean01 = true;
		boolean boolean02 = false;
		Object minion00 = Class.forName(minionPath).getConstructor(String.class, int.class, Class.forName(rarityPath), int.class, int.class, boolean.class, boolean.class, boolean.class).newInstance(string00, int75, rarity04, int83, int41, boolean01, boolean01, boolean02);


		testSetterLogic(minion00, "sleeping", boolean02, boolean02, boolean.class);
	}


	@Test(timeout = 10000)
	public void testSetterForInstanceVariableExistsInClassFatigueDamageHandFieldDeckManaCrystalsCurrentTotalHeroPowerUsedHPName() throws Exception
	{
		testSetterMethodExistsInClass(Class.forName(warlockPath), "setName", String.class, false);
		testSetterMethodExistsInClass(Class.forName(priestPath), "setName", String.class, false);
		testSetterMethodExistsInClass(Class.forName(paladinPath), "setName", String.class, false);
		testSetterMethodExistsInClass(Class.forName(magePath), "setName", String.class, false);
		testSetterMethodExistsInClass(Class.forName(hunterPath), "setName", String.class, false);
		testSetterMethodExistsInClass(Class.forName(warlockPath), "setCurrentHP", int.class, false);
		testSetterMethodExistsInClass(Class.forName(priestPath), "setCurrentHP", int.class, false);
		testSetterMethodExistsInClass(Class.forName(paladinPath), "setCurrentHP", int.class, false);
		testSetterMethodExistsInClass(Class.forName(magePath), "setCurrentHP", int.class, false);
		testSetterMethodExistsInClass(Class.forName(hunterPath), "setCurrentHP", int.class, false);
		testSetterMethodExistsInClass(Class.forName(warlockPath), "setHeroPowerUsed", boolean.class, false);
		testSetterMethodExistsInClass(Class.forName(priestPath), "setHeroPowerUsed", boolean.class, false);
		testSetterMethodExistsInClass(Class.forName(paladinPath), "setHeroPowerUsed", boolean.class, false);
		testSetterMethodExistsInClass(Class.forName(magePath), "setHeroPowerUsed", boolean.class, false);
		testSetterMethodExistsInClass(Class.forName(hunterPath), "setHeroPowerUsed", boolean.class, false);
		testSetterMethodExistsInClass(Class.forName(warlockPath), "setTotalManaCrystals", int.class, false);
		testSetterMethodExistsInClass(Class.forName(priestPath), "setTotalManaCrystals", int.class, false);
		testSetterMethodExistsInClass(Class.forName(paladinPath), "setTotalManaCrystals", int.class, false);
		testSetterMethodExistsInClass(Class.forName(magePath), "setTotalManaCrystals", int.class, false);
		testSetterMethodExistsInClass(Class.forName(hunterPath), "setTotalManaCrystals", int.class, false);
		testSetterMethodExistsInClass(Class.forName(warlockPath), "setCurrentManaCrystals", int.class, false);
		testSetterMethodExistsInClass(Class.forName(priestPath), "setCurrentManaCrystals", int.class, false);
		testSetterMethodExistsInClass(Class.forName(paladinPath), "setCurrentManaCrystals", int.class, false);
		testSetterMethodExistsInClass(Class.forName(magePath), "setCurrentManaCrystals", int.class, false);
		testSetterMethodExistsInClass(Class.forName(hunterPath), "setCurrentManaCrystals", int.class, false);
		testSetterMethodExistsInClass(Class.forName(warlockPath), "setDeck", ArrayList.class, false);
		testSetterMethodExistsInClass(Class.forName(priestPath), "setDeck", ArrayList.class, false);
		testSetterMethodExistsInClass(Class.forName(paladinPath), "setDeck", ArrayList.class, false);
		testSetterMethodExistsInClass(Class.forName(magePath), "setDeck", ArrayList.class, false);
		testSetterMethodExistsInClass(Class.forName(hunterPath), "setDeck", ArrayList.class, false);
		testSetterMethodExistsInClass(Class.forName(warlockPath), "setField", ArrayList.class, false);
		testSetterMethodExistsInClass(Class.forName(priestPath), "setField", ArrayList.class, false);
		testSetterMethodExistsInClass(Class.forName(paladinPath), "setField", ArrayList.class, false);
		testSetterMethodExistsInClass(Class.forName(magePath), "setField", ArrayList.class, false);
		testSetterMethodExistsInClass(Class.forName(hunterPath), "setField", ArrayList.class, false);
		testSetterMethodExistsInClass(Class.forName(warlockPath), "setHand", ArrayList.class, false);
		testSetterMethodExistsInClass(Class.forName(priestPath), "setHand", ArrayList.class, false);
		testSetterMethodExistsInClass(Class.forName(paladinPath), "setHand", ArrayList.class, false);
		testSetterMethodExistsInClass(Class.forName(magePath), "setHand", ArrayList.class, false);
		testSetterMethodExistsInClass(Class.forName(hunterPath), "setHand", ArrayList.class, false);
		testSetterMethodExistsInClass(Class.forName(warlockPath), "setFatigueDamage", int.class, false);
		testSetterMethodExistsInClass(Class.forName(priestPath), "setFatigueDamage", int.class, false);
		testSetterMethodExistsInClass(Class.forName(paladinPath), "setFatigueDamage", int.class, false);
		testSetterMethodExistsInClass(Class.forName(magePath), "setFatigueDamage", int.class, false);
		testSetterMethodExistsInClass(Class.forName(hunterPath), "setFatigueDamage", int.class, false);
	}


	@Test(timeout = 10000)
	public void testSetterForInstanceVariableExistsInClassRarityManaCostName() throws Exception
	{
		testSetterMethodExistsInClass(Class.forName(spellPath), "setName", String.class, false);
		testSetterMethodExistsInClass(Class.forName(twistingNetherPath), "setName", String.class, false);
		testSetterMethodExistsInClass(Class.forName(siphonSoulPath), "setName", String.class, false);
		testSetterMethodExistsInClass(Class.forName(shadowWordDeathPath), "setName", String.class, false);
		testSetterMethodExistsInClass(Class.forName(sealOfChampionsPath), "setName", String.class, false);
		testSetterMethodExistsInClass(Class.forName(pyroblastPath), "setName", String.class, false);
		testSetterMethodExistsInClass(Class.forName(polymorphPath), "setName", String.class, false);
		testSetterMethodExistsInClass(Class.forName(multiShotPath), "setName", String.class, false);
		testSetterMethodExistsInClass(Class.forName(levelUpPath), "setName", String.class, false);
		testSetterMethodExistsInClass(Class.forName(killCommandPath), "setName", String.class, false);
		testSetterMethodExistsInClass(Class.forName(holyNovaPath), "setName", String.class, false);
		testSetterMethodExistsInClass(Class.forName(flamestrikePath), "setName", String.class, false);
		testSetterMethodExistsInClass(Class.forName(divineSpiritPath), "setName", String.class, false);
		testSetterMethodExistsInClass(Class.forName(curseOfWeaknessPath), "setName", String.class, false);
		testSetterMethodExistsInClass(Class.forName(minionPath), "setName", String.class, false);
		testSetterMethodExistsInClass(Class.forName(icehowlPath), "setName", String.class, false);
		testSetterMethodExistsInClass(Class.forName(spellPath), "setManaCost", int.class, false);
		testSetterMethodExistsInClass(Class.forName(twistingNetherPath), "setManaCost", int.class, false);
		testSetterMethodExistsInClass(Class.forName(siphonSoulPath), "setManaCost", int.class, false);
		testSetterMethodExistsInClass(Class.forName(shadowWordDeathPath), "setManaCost", int.class, false);
		testSetterMethodExistsInClass(Class.forName(sealOfChampionsPath), "setManaCost", int.class, false);
		testSetterMethodExistsInClass(Class.forName(pyroblastPath), "setManaCost", int.class, false);
		testSetterMethodExistsInClass(Class.forName(polymorphPath), "setManaCost", int.class, false);
		testSetterMethodExistsInClass(Class.forName(multiShotPath), "setManaCost", int.class, false);
		testSetterMethodExistsInClass(Class.forName(levelUpPath), "setManaCost", int.class, false);
		testSetterMethodExistsInClass(Class.forName(killCommandPath), "setManaCost", int.class, false);
		testSetterMethodExistsInClass(Class.forName(holyNovaPath), "setManaCost", int.class, false);
		testSetterMethodExistsInClass(Class.forName(flamestrikePath), "setManaCost", int.class, false);
		testSetterMethodExistsInClass(Class.forName(divineSpiritPath), "setManaCost", int.class, false);
		testSetterMethodExistsInClass(Class.forName(curseOfWeaknessPath), "setManaCost", int.class, false);
		testSetterMethodExistsInClass(Class.forName(minionPath), "setManaCost", int.class, false);
		testSetterMethodExistsInClass(Class.forName(icehowlPath), "setManaCost", int.class, false);
		testSetterMethodExistsInClass(Class.forName(spellPath), "setRarity", Class.forName(rarityPath), false);
		testSetterMethodExistsInClass(Class.forName(twistingNetherPath), "setRarity", Class.forName(rarityPath), false);
		testSetterMethodExistsInClass(Class.forName(siphonSoulPath), "setRarity", Class.forName(rarityPath), false);
		testSetterMethodExistsInClass(Class.forName(shadowWordDeathPath), "setRarity", Class.forName(rarityPath), false);
		testSetterMethodExistsInClass(Class.forName(sealOfChampionsPath), "setRarity", Class.forName(rarityPath), false);
		testSetterMethodExistsInClass(Class.forName(pyroblastPath), "setRarity", Class.forName(rarityPath), false);
		testSetterMethodExistsInClass(Class.forName(polymorphPath), "setRarity", Class.forName(rarityPath), false);
		testSetterMethodExistsInClass(Class.forName(multiShotPath), "setRarity", Class.forName(rarityPath), false);
		testSetterMethodExistsInClass(Class.forName(levelUpPath), "setRarity", Class.forName(rarityPath), false);
		testSetterMethodExistsInClass(Class.forName(killCommandPath), "setRarity", Class.forName(rarityPath), false);
		testSetterMethodExistsInClass(Class.forName(holyNovaPath), "setRarity", Class.forName(rarityPath), false);
		testSetterMethodExistsInClass(Class.forName(flamestrikePath), "setRarity", Class.forName(rarityPath), false);
		testSetterMethodExistsInClass(Class.forName(divineSpiritPath), "setRarity", Class.forName(rarityPath), false);
		testSetterMethodExistsInClass(Class.forName(curseOfWeaknessPath), "setRarity", Class.forName(rarityPath), false);
		testSetterMethodExistsInClass(Class.forName(minionPath), "setRarity", Class.forName(rarityPath), false);
		testSetterMethodExistsInClass(Class.forName(icehowlPath), "setRarity", Class.forName(rarityPath), false);
	}


	@Test(timeout = 10000)
	public void testGetterForInstanceVariableExistsInClassFatigueDamageHandFieldDeckManaCrystalsCurrentTotalHeroPowerUsedHPName() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(warlockPath), "getName", String.class, false);
		testGetterMethodExistsInClass(Class.forName(priestPath), "getName", String.class, false);
		testGetterMethodExistsInClass(Class.forName(paladinPath), "getName", String.class, false);
		testGetterMethodExistsInClass(Class.forName(magePath), "getName", String.class, false);
		testGetterMethodExistsInClass(Class.forName(hunterPath), "getName", String.class, false);
		testGetterMethodExistsInClass(Class.forName(warlockPath), "getCurrentHP", int.class, false);
		testGetterMethodExistsInClass(Class.forName(priestPath), "getCurrentHP", int.class, false);
		testGetterMethodExistsInClass(Class.forName(paladinPath), "getCurrentHP", int.class, false);
		testGetterMethodExistsInClass(Class.forName(magePath), "getCurrentHP", int.class, false);
		testGetterMethodExistsInClass(Class.forName(hunterPath), "getCurrentHP", int.class, false);
		testGetterMethodExistsInClass(Class.forName(warlockPath), "isHeroPowerUsed", boolean.class, false);
		testGetterMethodExistsInClass(Class.forName(priestPath), "isHeroPowerUsed", boolean.class, false);
		testGetterMethodExistsInClass(Class.forName(paladinPath), "isHeroPowerUsed", boolean.class, false);
		testGetterMethodExistsInClass(Class.forName(magePath), "isHeroPowerUsed", boolean.class, false);
		testGetterMethodExistsInClass(Class.forName(hunterPath), "isHeroPowerUsed", boolean.class, false);
		testGetterMethodExistsInClass(Class.forName(warlockPath), "getTotalManaCrystals", int.class, false);
		testGetterMethodExistsInClass(Class.forName(priestPath), "getTotalManaCrystals", int.class, false);
		testGetterMethodExistsInClass(Class.forName(paladinPath), "getTotalManaCrystals", int.class, false);
		testGetterMethodExistsInClass(Class.forName(magePath), "getTotalManaCrystals", int.class, false);
		testGetterMethodExistsInClass(Class.forName(hunterPath), "getTotalManaCrystals", int.class, false);
		testGetterMethodExistsInClass(Class.forName(warlockPath), "getCurrentManaCrystals", int.class, false);
		testGetterMethodExistsInClass(Class.forName(priestPath), "getCurrentManaCrystals", int.class, false);
		testGetterMethodExistsInClass(Class.forName(paladinPath), "getCurrentManaCrystals", int.class, false);
		testGetterMethodExistsInClass(Class.forName(magePath), "getCurrentManaCrystals", int.class, false);
		testGetterMethodExistsInClass(Class.forName(hunterPath), "getCurrentManaCrystals", int.class, false);
		testGetterMethodExistsInClass(Class.forName(warlockPath), "getDeck", ArrayList.class, false);
		testGetterMethodExistsInClass(Class.forName(priestPath), "getDeck", ArrayList.class, false);
		testGetterMethodExistsInClass(Class.forName(paladinPath), "getDeck", ArrayList.class, false);
		testGetterMethodExistsInClass(Class.forName(magePath), "getDeck", ArrayList.class, false);
		testGetterMethodExistsInClass(Class.forName(hunterPath), "getDeck", ArrayList.class, false);
		testGetterMethodExistsInClass(Class.forName(warlockPath), "getField", ArrayList.class, false);
		testGetterMethodExistsInClass(Class.forName(priestPath), "getField", ArrayList.class, false);
		testGetterMethodExistsInClass(Class.forName(paladinPath), "getField", ArrayList.class, false);
		testGetterMethodExistsInClass(Class.forName(magePath), "getField", ArrayList.class, false);
		testGetterMethodExistsInClass(Class.forName(hunterPath), "getField", ArrayList.class, false);
		testGetterMethodExistsInClass(Class.forName(warlockPath), "getHand", ArrayList.class, false);
		testGetterMethodExistsInClass(Class.forName(priestPath), "getHand", ArrayList.class, false);
		testGetterMethodExistsInClass(Class.forName(paladinPath), "getHand", ArrayList.class, false);
		testGetterMethodExistsInClass(Class.forName(magePath), "getHand", ArrayList.class, false);
		testGetterMethodExistsInClass(Class.forName(hunterPath), "getHand", ArrayList.class, false);
		testGetterMethodExistsInClass(Class.forName(warlockPath), "getFatigueDamage", int.class, false);
		testGetterMethodExistsInClass(Class.forName(priestPath), "getFatigueDamage", int.class, false);
		testGetterMethodExistsInClass(Class.forName(paladinPath), "getFatigueDamage", int.class, false);
		testGetterMethodExistsInClass(Class.forName(magePath), "getFatigueDamage", int.class, false);
		testGetterMethodExistsInClass(Class.forName(hunterPath), "getFatigueDamage", int.class, false);
	}


	@Test(timeout = 10000)
	public void testGetterForInstanceVariableExistsInClassRarityManaCostName() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(spellPath), "getName", String.class, false);
		testGetterMethodExistsInClass(Class.forName(twistingNetherPath), "getName", String.class, false);
		testGetterMethodExistsInClass(Class.forName(siphonSoulPath), "getName", String.class, false);
		testGetterMethodExistsInClass(Class.forName(shadowWordDeathPath), "getName", String.class, false);
		testGetterMethodExistsInClass(Class.forName(sealOfChampionsPath), "getName", String.class, false);
		testGetterMethodExistsInClass(Class.forName(pyroblastPath), "getName", String.class, false);
		testGetterMethodExistsInClass(Class.forName(polymorphPath), "getName", String.class, false);
		testGetterMethodExistsInClass(Class.forName(multiShotPath), "getName", String.class, false);
		testGetterMethodExistsInClass(Class.forName(levelUpPath), "getName", String.class, false);
		testGetterMethodExistsInClass(Class.forName(killCommandPath), "getName", String.class, false);
		testGetterMethodExistsInClass(Class.forName(holyNovaPath), "getName", String.class, false);
		testGetterMethodExistsInClass(Class.forName(flamestrikePath), "getName", String.class, false);
		testGetterMethodExistsInClass(Class.forName(divineSpiritPath), "getName", String.class, false);
		testGetterMethodExistsInClass(Class.forName(curseOfWeaknessPath), "getName", String.class, false);
		testGetterMethodExistsInClass(Class.forName(minionPath), "getName", String.class, false);
		testGetterMethodExistsInClass(Class.forName(icehowlPath), "getName", String.class, false);
		testGetterMethodExistsInClass(Class.forName(spellPath), "getManaCost", int.class, false);
		testGetterMethodExistsInClass(Class.forName(twistingNetherPath), "getManaCost", int.class, false);
		testGetterMethodExistsInClass(Class.forName(siphonSoulPath), "getManaCost", int.class, false);
		testGetterMethodExistsInClass(Class.forName(shadowWordDeathPath), "getManaCost", int.class, false);
		testGetterMethodExistsInClass(Class.forName(sealOfChampionsPath), "getManaCost", int.class, false);
		testGetterMethodExistsInClass(Class.forName(pyroblastPath), "getManaCost", int.class, false);
		testGetterMethodExistsInClass(Class.forName(polymorphPath), "getManaCost", int.class, false);
		testGetterMethodExistsInClass(Class.forName(multiShotPath), "getManaCost", int.class, false);
		testGetterMethodExistsInClass(Class.forName(levelUpPath), "getManaCost", int.class, false);
		testGetterMethodExistsInClass(Class.forName(killCommandPath), "getManaCost", int.class, false);
		testGetterMethodExistsInClass(Class.forName(holyNovaPath), "getManaCost", int.class, false);
		testGetterMethodExistsInClass(Class.forName(flamestrikePath), "getManaCost", int.class, false);
		testGetterMethodExistsInClass(Class.forName(divineSpiritPath), "getManaCost", int.class, false);
		testGetterMethodExistsInClass(Class.forName(curseOfWeaknessPath), "getManaCost", int.class, false);
		testGetterMethodExistsInClass(Class.forName(minionPath), "getManaCost", int.class, false);
		testGetterMethodExistsInClass(Class.forName(icehowlPath), "getManaCost", int.class, false);
		testGetterMethodExistsInClass(Class.forName(spellPath), "getRarity", Class.forName(rarityPath), false);
		testGetterMethodExistsInClass(Class.forName(twistingNetherPath), "getRarity", Class.forName(rarityPath), false);
		testGetterMethodExistsInClass(Class.forName(siphonSoulPath), "getRarity", Class.forName(rarityPath), false);
		testGetterMethodExistsInClass(Class.forName(shadowWordDeathPath), "getRarity", Class.forName(rarityPath), false);
		testGetterMethodExistsInClass(Class.forName(sealOfChampionsPath), "getRarity", Class.forName(rarityPath), false);
		testGetterMethodExistsInClass(Class.forName(pyroblastPath), "getRarity", Class.forName(rarityPath), false);
		testGetterMethodExistsInClass(Class.forName(polymorphPath), "getRarity", Class.forName(rarityPath), false);
		testGetterMethodExistsInClass(Class.forName(multiShotPath), "getRarity", Class.forName(rarityPath), false);
		testGetterMethodExistsInClass(Class.forName(levelUpPath), "getRarity", Class.forName(rarityPath), false);
		testGetterMethodExistsInClass(Class.forName(killCommandPath), "getRarity", Class.forName(rarityPath), false);
		testGetterMethodExistsInClass(Class.forName(holyNovaPath), "getRarity", Class.forName(rarityPath), false);
		testGetterMethodExistsInClass(Class.forName(flamestrikePath), "getRarity", Class.forName(rarityPath), false);
		testGetterMethodExistsInClass(Class.forName(divineSpiritPath), "getRarity", Class.forName(rarityPath), false);
		testGetterMethodExistsInClass(Class.forName(curseOfWeaknessPath), "getRarity", Class.forName(rarityPath), false);
		testGetterMethodExistsInClass(Class.forName(minionPath), "getRarity", Class.forName(rarityPath), false);
		testGetterMethodExistsInClass(Class.forName(icehowlPath), "getRarity", Class.forName(rarityPath), false);
	}


	String exceptionPath = "java.lang.Exception";
	
	@Test(timeout = 1000)
	public void testExceptionIsSuperClassOfHearthstoneException() throws Exception
	{
		testClassIsSubclass(Class.forName(hearthstoneExceptionPath), Class.forName(exceptionPath));
	}
	
	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableAttackminion00int00InClassMinion() throws Exception
	{
		String string00 = "x36";
		int int52 = 52;
		Object rarity01 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "COMMON");
		int int65 = 65;
		int int18 = 18;
		boolean boolean02 = false;
		boolean boolean01 = true;
		Object minion00 = Class.forName(minionPath).getConstructor(String.class, int.class, Class.forName(rarityPath), int.class, int.class, boolean.class, boolean.class, boolean.class).newInstance(string00, int52, rarity01, int65, int18, boolean02, boolean01, boolean01);

		int int68 = -68;

		testSetterLogic(minion00, "attack", int68, 0, int.class);
	}

	private void testGetAllNeutralMinionsMethod(
			ArrayList<Object> correctMinions, ArrayList<Object> minions)
			throws IOException, ClassNotFoundException,
			IllegalArgumentException, IllegalAccessException,
			NoSuchFieldException, SecurityException {

		assertEquals(
				"Wrong number of Minions read from the CSV file using getAllNeutralMinion()",
				correctMinions.size(), minions.size());
		for (int i = 0; i < correctMinions.size(); i++) {
			equals(correctMinions.get(i), minions.get(i));
		}
	}

	public void equals(Object correct, Object check)
			throws ClassNotFoundException, IllegalArgumentException,
			IllegalAccessException, NoSuchFieldException, SecurityException {
		Class minion = Class.forName("model.cards.minions.Minion");
		Field name = minion.getSuperclass().getDeclaredField("name");
		Field manaCost = minion.getSuperclass().getDeclaredField("manaCost");
		Field rarity = minion.getSuperclass().getDeclaredField("rarity");
		Field attack = minion.getDeclaredField("attack");
		Field maxHP = minion.getDeclaredField("maxHP");
		Field currentHP = minion.getDeclaredField("currentHP");
		Field taunt = minion.getDeclaredField("taunt");
		Field divine = minion.getDeclaredField("divine");
		Field sleeping = minion.getDeclaredField("sleeping");
		Field attacked = minion.getDeclaredField("attacked");

		name.setAccessible(true);
		manaCost.setAccessible(true);
		rarity.setAccessible(true);
		attack.setAccessible(true);
		maxHP.setAccessible(true);
		currentHP.setAccessible(true);
		taunt.setAccessible(true);
		divine.setAccessible(true);
		sleeping.setAccessible(true);
		attacked.setAccessible(true);

		Field[] fields = minion.getDeclaredFields();
		Field[] fields2 = minion.getSuperclass().getDeclaredFields();
		for (Field f : fields)
			f.setAccessible(true);
		for (Field f : fields2)
			f.setAccessible(true);

		assertEquals("Minion " + name.get(correct)
				+ " initialised with the wrong name.", name.get(correct),
				name.get(check));
		assertEquals("Minion " + name.get(correct)
				+ " initialised with the wrong manaCost.",
				manaCost.get(correct), manaCost.get(check));
		assertEquals("Minion " + name.get(correct)
				+ " initialised with the wrong rarity.", rarity.get(correct),
				rarity.get(check));
		assertEquals("Minion " + name.get(correct)
				+ " initialised with the wrong attack.", attack.get(correct),
				attack.get(check));
		assertEquals("Minion " + name.get(correct)
				+ " initialised with the wrong maxHP.", maxHP.get(correct),
				maxHP.get(check));
		assertEquals("Minion " + name.get(correct)
				+ " initialised with the wrong currentHP.",
				currentHP.get(correct), currentHP.get(check));
		assertEquals("Minion " + name.get(correct)
				+ " initialised with the wrong taunt.", taunt.get(correct),
				taunt.get(check));
		assertEquals("Minion " + name.get(correct)
				+ " initialised with the wrong divine.", divine.get(correct),
				divine.get(check));
		assertEquals("Minion " + name.get(correct)
				+ " initialised with the wrong sleeping.",
				sleeping.get(correct), sleeping.get(check));
		assertEquals("Minion " + name.get(correct)
				+ " initialised with the wrong attacked.",
				attacked.get(correct), attacked.get(check));
	}

	@Test(timeout = 1000)
	public void testIceHowlInstantiation() throws FileNotFoundException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		PrintWriter minionWriter = new PrintWriter("test_minion.csv");
		minionWriter.println("Icehowl,9,l,10,10,f,f,t");
		minionWriter.flush();
		minionWriter.close();

		Class heroClass = Class.forName(heroPath);
		Class icehowlClass = Class.forName(icehowlPath);
		ArrayList<Object> testMinions = (ArrayList<Object>) heroClass
				.getDeclaredMethod("getAllNeutralMinions", String.class)
				.invoke(heroClass, "test_minion.csv");
		if (testMinions.get(0).getClass() != icehowlClass)
			fail("You should create an instance from Icehowl class while reading from the CSV file.");

	}

	@Test(timeout = 1000)
	public void testCountOfCardsFromGetNeutralMinionsMethod()
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException {
		Object rarityBasic = Enum.valueOf(
				(Class<Enum>) Class.forName(rarityPath), "BASIC");
		Object rarityRare = Enum.valueOf(
				(Class<Enum>) Class.forName(rarityPath), "RARE");
		Object rarityLegendary = Enum.valueOf(
				(Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		Class[] minionCons = { String.class, int.class,
				Class.forName(rarityPath), int.class, int.class, boolean.class,
				boolean.class, boolean.class };
		Class heroClass = Class.forName(heroPath);
		Class minionClass = Class.forName(minionPath);

		Object goldshire = minionClass.getConstructor(minionCons).newInstance(
				"Goldshire Footman", 1, rarityBasic, 1, 2, true, false, false);
		Object stonetusk = minionClass.getConstructor(minionCons).newInstance(
				"Stonetusk Boar", 1, rarityBasic, 1, 1, false, false, true);
		Object bloodfen = minionClass.getConstructor(minionCons).newInstance(
				"Bloodfen Raptor", 2, rarityBasic, 3, 2, false, false, false);
		Object frostwolf = minionClass.getConstructor(minionCons).newInstance(
				"Frostwolf Grunt", 2, rarityBasic, 2, 2, true, false, false);
		Object wolfrider = minionClass.getConstructor(minionCons).newInstance(
				"Wolfrider", 3, rarityBasic, 3, 1, false, false, true);
		Object chilwind = minionClass.getConstructor(minionCons).newInstance(
				"Chilwind Yeti", 4, rarityBasic, 4, 5, false, false, false);
		Object boulderfist = minionClass.getConstructor(minionCons)
				.newInstance("Boulderfist Ogre", 6, rarityBasic, 6, 7, false,
						false, false);
		Object core = minionClass.getConstructor(minionCons).newInstance(
				"Core Hound", 7, rarityBasic, 9, 5, false, false, false);
		Object argent = minionClass.getConstructor(minionCons).newInstance(
				"Argent Commander", 6, rarityRare, 4, 2, false, true, true);
		Object sunwalker = minionClass.getConstructor(minionCons).newInstance(
				"Sunwalker", 6, rarityRare, 4, 5, true, true, false);
		Object chromaggus = minionClass.getConstructor(minionCons).newInstance(
				"Chromaggus", 8, rarityLegendary, 6, 8, false, false, false);
		Object lichKing = minionClass.getConstructor(minionCons).newInstance(
				"The LichKing", 8, rarityLegendary, 10, 10, true, false, false);
		Object icehowl = minionClass.getConstructor(minionCons).newInstance(
				"Icehowl", 9, rarityLegendary, 10, 10, false, false, true);
		Object colossus = minionClass.getConstructor(minionCons).newInstance(
				"Colossus of the Moon", 10, rarityLegendary, 10, 10, false,
				true, false);

		ArrayList<Object> correctMinions = new ArrayList<Object>();
		correctMinions.add(goldshire);
		correctMinions.add(stonetusk);
		correctMinions.add(bloodfen);
		correctMinions.add(frostwolf);
		correctMinions.add(wolfrider);
		correctMinions.add(chilwind);
		correctMinions.add(boulderfist);
		correctMinions.add(core);
		correctMinions.add(argent);
		correctMinions.add(sunwalker);
		correctMinions.add(chromaggus);
		correctMinions.add(lichKing);
		correctMinions.add(icehowl);
		correctMinions.add(colossus);

		assertEquals(
				"GetNeutralMinions method returns the wrong count of cards",
				15,
				((ArrayList<Object>) heroClass.getDeclaredMethod(
						"getNeutralMinions", ArrayList.class, int.class)
						.invoke(heroClass, correctMinions, 15)).size());
	}

	@Test(timeout = 1000)
	public void testBasicRareCopiesCount() throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException {

		Object rarityBasic = Enum.valueOf(
				(Class<Enum>) Class.forName(rarityPath), "BASIC");
		Object rarityRare = Enum.valueOf(
				(Class<Enum>) Class.forName(rarityPath), "RARE");
		Object rarityLegendary = Enum.valueOf(
				(Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		Class[] minionCons = { String.class, int.class,
				Class.forName(rarityPath), int.class, int.class, boolean.class,
				boolean.class, boolean.class };
		Class heroClass = Class.forName(heroPath);
		Class minionClass = Class.forName(minionPath);

		Object goldshire = minionClass.getConstructor(minionCons).newInstance(
				"Goldshire Footman", 1, rarityBasic, 1, 2, true, false, false);
		Object stonetusk = minionClass.getConstructor(minionCons).newInstance(
				"Stonetusk Boar", 1, rarityBasic, 1, 1, false, false, true);
		Object bloodfen = minionClass.getConstructor(minionCons).newInstance(
				"Bloodfen Raptor", 2, rarityBasic, 3, 2, false, false, false);
		Object frostwolf = minionClass.getConstructor(minionCons).newInstance(
				"Frostwolf Grunt", 2, rarityBasic, 2, 2, true, false, false);
		Object wolfrider = minionClass.getConstructor(minionCons).newInstance(
				"Wolfrider", 3, rarityBasic, 3, 1, false, false, true);
		Object chilwind = minionClass.getConstructor(minionCons).newInstance(
				"Chilwind Yeti", 4, rarityBasic, 4, 5, false, false, false);
		Object boulderfist = minionClass.getConstructor(minionCons)
				.newInstance("Boulderfist Ogre", 6, rarityBasic, 6, 7, false,
						false, false);
		Object core = minionClass.getConstructor(minionCons).newInstance(
				"Core Hound", 7, rarityBasic, 9, 5, false, false, false);
		Object argent = minionClass.getConstructor(minionCons).newInstance(
				"Argent Commander", 6, rarityRare, 4, 2, false, true, true);
		Object sunwalker = minionClass.getConstructor(minionCons).newInstance(
				"Sunwalker", 6, rarityRare, 4, 5, true, true, false);
		Object chromaggus = minionClass.getConstructor(minionCons).newInstance(
				"Chromaggus", 8, rarityLegendary, 6, 8, false, false, false);
		Object lichKing = minionClass.getConstructor(minionCons).newInstance(
				"The LichKing", 8, rarityLegendary, 10, 10, true, false, false);
		Object icehowl = minionClass.getConstructor(minionCons).newInstance(
				"Icehowl", 9, rarityLegendary, 10, 10, false, false, true);
		Object colossus = minionClass.getConstructor(minionCons).newInstance(
				"Colossus of the Moon", 10, rarityLegendary, 10, 10, false,
				true, false);

		ArrayList<Object> correctMinions = new ArrayList<Object>();
		correctMinions.add(goldshire);
		correctMinions.add(stonetusk);
		correctMinions.add(bloodfen);
		correctMinions.add(frostwolf);
		correctMinions.add(wolfrider);
		correctMinions.add(chilwind);
		correctMinions.add(boulderfist);
		correctMinions.add(core);
		correctMinions.add(argent);
		correctMinions.add(sunwalker);
		correctMinions.add(chromaggus);
		correctMinions.add(lichKing);
		correctMinions.add(icehowl);
		correctMinions.add(colossus);

		ArrayList<Object> deck = (ArrayList<Object>) heroClass
				.getDeclaredMethod("getNeutralMinions", ArrayList.class,
						int.class).invoke(heroClass, correctMinions, 15);

		int copiesGoldshire = 0;
		int copiesStonetusk = 0;
		int copiesBloodfen = 0;
		int copiesFrostwolf = 0;
		int copiesWolfrider = 0;
		int copiesChilwind = 0;
		int copiesBoulderfist = 0;
		int copiesCore = 0;
		int copiesArgent = 0;
		int copiesSunwalker = 0;

		for (Object o : deck) {
			switch ((String) (o.getClass().getSuperclass()
					.getDeclaredMethod("getName").invoke(o))) {
			case "Goldshire Footman":
				copiesGoldshire++;
				break;
			case "Stonetusk Boar":
				copiesStonetusk++;
				break;
			case "Bloodfen Raptor":
				copiesBloodfen++;
				break;
			case "Frostwolf Grunt":
				copiesFrostwolf++;
				break;
			case "Wolfrider":
				copiesWolfrider++;
				break;
			case "Chilwind Yeti":
				copiesChilwind++;
				break;
			case "Boulderfist Ogre":
				copiesBoulderfist++;
				break;
			case "Core Hound":
				copiesCore++;
				break;
			case "Argent Commander":
				copiesArgent++;
				break;
			case "Sunwalker":
				copiesSunwalker++;
				break;
			}

			if (copiesGoldshire > 2 || copiesStonetusk > 2
					|| copiesBloodfen > 2 || copiesFrostwolf > 2
					|| copiesWolfrider > 2 || copiesChilwind > 2
					|| copiesBoulderfist > 2 || copiesCore > 2
					|| copiesArgent > 2 || copiesSunwalker > 2)
				fail("The returned deck from GetNeutralMinions method should NOT contain more than 2 copies of Basic or Rare Minions");
		}
	}

	@Test(timeout = 1000)
	public void testLegendaryCopies() throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException {
		Object rarityBasic = Enum.valueOf(
				(Class<Enum>) Class.forName(rarityPath), "BASIC");
		Object rarityRare = Enum.valueOf(
				(Class<Enum>) Class.forName(rarityPath), "RARE");
		Object rarityLegendary = Enum.valueOf(
				(Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		Class[] minionCons = { String.class, int.class,
				Class.forName(rarityPath), int.class, int.class, boolean.class,
				boolean.class, boolean.class };
		Class heroClass = Class.forName(heroPath);
		Class minionClass = Class.forName(minionPath);

		Object goldshire = minionClass.getConstructor(minionCons).newInstance(
				"Goldshire Footman", 1, rarityBasic, 1, 2, true, false, false);
		Object stonetusk = minionClass.getConstructor(minionCons).newInstance(
				"Stonetusk Boar", 1, rarityBasic, 1, 1, false, false, true);
		Object bloodfen = minionClass.getConstructor(minionCons).newInstance(
				"Bloodfen Raptor", 2, rarityBasic, 3, 2, false, false, false);
		Object frostwolf = minionClass.getConstructor(minionCons).newInstance(
				"Frostwolf Grunt", 2, rarityBasic, 2, 2, true, false, false);
		Object wolfrider = minionClass.getConstructor(minionCons).newInstance(
				"Wolfrider", 3, rarityBasic, 3, 1, false, false, true);
		Object chilwind = minionClass.getConstructor(minionCons).newInstance(
				"Chilwind Yeti", 4, rarityBasic, 4, 5, false, false, false);
		Object boulderfist = minionClass.getConstructor(minionCons)
				.newInstance("Boulderfist Ogre", 6, rarityBasic, 6, 7, false,
						false, false);
		Object core = minionClass.getConstructor(minionCons).newInstance(
				"Core Hound", 7, rarityBasic, 9, 5, false, false, false);
		Object argent = minionClass.getConstructor(minionCons).newInstance(
				"Argent Commander", 6, rarityRare, 4, 2, false, true, true);
		Object sunwalker = minionClass.getConstructor(minionCons).newInstance(
				"Sunwalker", 6, rarityRare, 4, 5, true, true, false);
		Object chromaggus = minionClass.getConstructor(minionCons).newInstance(
				"Chromaggus", 8, rarityLegendary, 6, 8, false, false, false);
		Object lichKing = minionClass.getConstructor(minionCons).newInstance(
				"The LichKing", 8, rarityLegendary, 10, 10, true, false, false);
		Object icehowl = minionClass.getConstructor(minionCons).newInstance(
				"Icehowl", 9, rarityLegendary, 10, 10, false, false, true);
		Object colossus = minionClass.getConstructor(minionCons).newInstance(
				"Colossus of the Moon", 10, rarityLegendary, 10, 10, false,
				true, false);

		ArrayList<Object> correctMinions = new ArrayList<Object>();
		correctMinions.add(goldshire);
		correctMinions.add(stonetusk);
		correctMinions.add(bloodfen);
		correctMinions.add(frostwolf);
		correctMinions.add(wolfrider);
		correctMinions.add(chilwind);
		correctMinions.add(boulderfist);
		correctMinions.add(core);
		correctMinions.add(argent);
		correctMinions.add(sunwalker);
		correctMinions.add(chromaggus);
		correctMinions.add(lichKing);
		correctMinions.add(icehowl);
		correctMinions.add(colossus);

		ArrayList<Object> deck = (ArrayList<Object>) heroClass
				.getDeclaredMethod("getNeutralMinions", ArrayList.class,
						int.class).invoke(heroClass, correctMinions, 15);

		int copiesChroma = 0;
		int copiesLich = 0;
		int copiesIcehowl = 0;
		int copiesMoon = 0;

		for (Object o : deck) {
			switch ((String) (o.getClass().getSuperclass()
					.getDeclaredMethod("getName").invoke(o))) {
			case "Chromaggus":
				copiesChroma++;
				break;
			case "The LichKing":
				copiesLich++;
				break;
			case "Icehowl":
				copiesIcehowl++;
				break;
			case "Colossus of the Moon":
				copiesMoon++;
				break;
			}
			if (copiesChroma > 1 || copiesLich > 1 || copiesIcehowl > 1
					|| copiesMoon > 1)
				fail("The returned deck from GetNeutralMinions method should NOT contain more than 1 copy of Legendary Minions");
		}
	}

	@Test(timeout = 1000)
	public void testGetNeutralMinionRandomization()
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		Object rarityBasic = Enum.valueOf(
				(Class<Enum>) Class.forName(rarityPath), "BASIC");
		Object rarityRare = Enum.valueOf(
				(Class<Enum>) Class.forName(rarityPath), "RARE");
		Object rarityLegendary = Enum.valueOf(
				(Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		Class[] minionCons = { String.class, int.class,
				Class.forName(rarityPath), int.class, int.class, boolean.class,
				boolean.class, boolean.class };
		Class heroClass = Class.forName(heroPath);
		Class minionClass = Class.forName(minionPath);

		Object goldshire = minionClass.getConstructor(minionCons).newInstance(
				"Goldshire Footman", 1, rarityBasic, 1, 2, true, false, false);
		Object stonetusk = minionClass.getConstructor(minionCons).newInstance(
				"Stonetusk Boar", 1, rarityBasic, 1, 1, false, false, true);
		Object bloodfen = minionClass.getConstructor(minionCons).newInstance(
				"Bloodfen Raptor", 2, rarityBasic, 3, 2, false, false, false);
		Object frostwolf = minionClass.getConstructor(minionCons).newInstance(
				"Frostwolf Grunt", 2, rarityBasic, 2, 2, true, false, false);
		Object wolfrider = minionClass.getConstructor(minionCons).newInstance(
				"Wolfrider", 3, rarityBasic, 3, 1, false, false, true);
		Object chilwind = minionClass.getConstructor(minionCons).newInstance(
				"Chilwind Yeti", 4, rarityBasic, 4, 5, false, false, false);
		Object boulderfist = minionClass.getConstructor(minionCons)
				.newInstance("Boulderfist Ogre", 6, rarityBasic, 6, 7, false,
						false, false);
		Object core = minionClass.getConstructor(minionCons).newInstance(
				"Core Hound", 7, rarityBasic, 9, 5, false, false, false);
		Object argent = minionClass.getConstructor(minionCons).newInstance(
				"Argent Commander", 6, rarityRare, 4, 2, false, true, true);
		Object sunwalker = minionClass.getConstructor(minionCons).newInstance(
				"Sunwalker", 6, rarityRare, 4, 5, true, true, false);
		Object chromaggus = minionClass.getConstructor(minionCons).newInstance(
				"Chromaggus", 8, rarityLegendary, 6, 8, false, false, false);
		Object lichKing = minionClass.getConstructor(minionCons).newInstance(
				"The LichKing", 8, rarityLegendary, 10, 10, true, false, false);
		Object icehowl = minionClass.getConstructor(minionCons).newInstance(
				"Icehowl", 9, rarityLegendary, 10, 10, false, false, true);
		Object colossus = minionClass.getConstructor(minionCons).newInstance(
				"Colossus of the Moon", 10, rarityLegendary, 10, 10, false,
				true, false);

		ArrayList<Object> correctMinions = new ArrayList<Object>();
		correctMinions.add(goldshire);
		correctMinions.add(stonetusk);
		correctMinions.add(bloodfen);
		correctMinions.add(frostwolf);
		correctMinions.add(wolfrider);
		correctMinions.add(chilwind);
		correctMinions.add(boulderfist);
		correctMinions.add(core);
		correctMinions.add(argent);
		correctMinions.add(sunwalker);
		correctMinions.add(chromaggus);
		correctMinions.add(lichKing);
		correctMinions.add(icehowl);
		correctMinions.add(colossus);

		if (((ArrayList<Object>) heroClass.getDeclaredMethod(
				"getNeutralMinions", ArrayList.class, int.class).invoke(
				heroClass, correctMinions, 15))
				.equals(((ArrayList<Object>) heroClass.getDeclaredMethod(
						"getNeutralMinions", ArrayList.class, int.class)
						.invoke(heroClass, correctMinions, 15))))
			fail("GetNeutralMinions method should return random chosen minions");
	}

	@Test(timeout = 1000)
	public void testNumberOfNeutralMinionsForHunter()
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException, InstantiationException {
		Class minionClass = Class.forName(minionPath);
		Class iceHowlClass = Class.forName(icehowlPath);
		Class hunterClass = Class.forName(hunterPath);

		Object oHunter = hunterClass.getConstructor().newInstance();

		Method getDeck = oHunter.getClass().getMethod("getDeck");
		ArrayList<Object> hunterDeck = (ArrayList<Object>) getDeck
				.invoke(oHunter);

		int neutralMinionscount = 0;

		for (Object card : hunterDeck) {
			if (card.getClass() == iceHowlClass
					|| (card.getClass() == minionClass && !(card.getClass()
							.getMethod("getName").invoke(card)
							.equals("King Krush"))))
				neutralMinionscount++;
		}
		assertEquals("A Hunter's deck should contatin 15 neutral minions", 15,
				neutralMinionscount);

	}

	@Test(timeout = 1000)
	public void testNumberOfNeutralMinionsForMage()
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException, InstantiationException {
		Class minionClass = Class.forName(minionPath);
		Class iceHowlClass = Class.forName(icehowlPath);
		Class hunterClass = Class.forName(hunterPath);
		Class mageClass = Class.forName(magePath);

		Object oHunter = hunterClass.getConstructor().newInstance();
		Object oMage = mageClass.getConstructor().newInstance();

		Method getDeck = oHunter.getClass().getMethod("getDeck");
		ArrayList<Object> mageDeck = (ArrayList<Object>) getDeck.invoke(oMage);

		int neutralMinionscount = 0;

		for (Object card : mageDeck) {
			if (card.getClass() == iceHowlClass
					|| (card.getClass() == minionClass && !(card.getClass()
							.getMethod("getName").invoke(card)
							.equals("Kalycgos"))))
				neutralMinionscount++;
		}
		assertEquals("A Mage's deck should contatin 13 neutral minions", 13,
				neutralMinionscount);
	}

	@Test(timeout = 1000)
	public void testNumberOfNeutralMinionsForPaladin()
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException, InstantiationException {
		Class minionClass = Class.forName(minionPath);
		Class iceHowlClass = Class.forName(icehowlPath);
		Class hunterClass = Class.forName(hunterPath);
		Class paladinClass = Class.forName(paladinPath);

		Object oHunter = hunterClass.getConstructor().newInstance();
		Object oPaladin = paladinClass.getConstructor().newInstance();

		Method getDeck = oHunter.getClass().getMethod("getDeck");
		ArrayList<Object> paladinDeck = (ArrayList<Object>) getDeck
				.invoke(oPaladin);

		int neutralMinionscount = 0;

		for (Object card : paladinDeck) {
			if (card.getClass() == iceHowlClass
					|| (card.getClass() == minionClass && !(card.getClass()
							.getMethod("getName").invoke(card)
							.equals("Tirion Fordring"))))
				neutralMinionscount++;
		}
		assertEquals("A Paladin's deck should contatin 15 neutral minions", 15,
				neutralMinionscount);
	}

	@Test(timeout = 1000)
	public void testNumberOfNeutralMinionsForPriest()
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException, InstantiationException {
		Class minionClass = Class.forName(minionPath);
		Class iceHowlClass = Class.forName(icehowlPath);
		Class hunterClass = Class.forName(hunterPath);
		Class priestClass = Class.forName(priestPath);

		Object oHunter = hunterClass.getConstructor().newInstance();
		Object oPriest = priestClass.getConstructor().newInstance();

		Method getDeck = oHunter.getClass().getMethod("getDeck");
		ArrayList<Object> priestDeck = (ArrayList<Object>) getDeck
				.invoke(oPriest);

		int neutralMinionscount = 0;

		for (Object card : priestDeck) {
			if (card.getClass() == iceHowlClass
					|| (card.getClass() == minionClass && !(card.getClass()
							.getMethod("getName").invoke(card)
							.equals("Prophet Velen"))))
				neutralMinionscount++;
		}
		assertEquals("A Priest's deck should contatin 13 neutral minions", 13,
				neutralMinionscount);
	}

	@Test(timeout = 1000)
	public void testNumberOfNeutralMinionsForWarlock()
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException, InstantiationException {
		Class minionClass = Class.forName(minionPath);
		Class iceHowlClass = Class.forName(icehowlPath);
		Class hunterClass = Class.forName(hunterPath);
		Class warlockClass = Class.forName(warlockPath);

		Object oHunter = hunterClass.getConstructor().newInstance();
		Object oWarlock = warlockClass.getConstructor().newInstance();

		Method getDeck = oHunter.getClass().getMethod("getDeck");
		ArrayList<Object> warlockDeck = (ArrayList<Object>) getDeck
				.invoke(oWarlock);

		int neutralMinionscount = 0;

		for (Object card : warlockDeck) {
			if (card.getClass() == iceHowlClass
					|| (card.getClass() == minionClass && !(card.getClass()
							.getMethod("getName").invoke(card)
							.equals("Wilfred Fizzlebang"))))
				neutralMinionscount++;
		}
		assertEquals("A Warlock's deck should contatin 13 neutral minions", 13,
				neutralMinionscount);
	}

	@Test(timeout = 1000)
	public void testSpecialLegendaryMinionForHunter()
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException {
		Class minionClass = Class.forName(minionPath);
		Class hunterClass = Class.forName(hunterPath);
		Object rarityLegendary = Enum.valueOf(
				(Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		Class[] minionCons = { String.class, int.class,
				Class.forName(rarityPath), int.class, int.class, boolean.class,
				boolean.class, boolean.class };

		Object oHunter = hunterClass.getConstructor().newInstance();
		Object krush = minionClass.getConstructor(minionCons).newInstance(
				"King Krush", 9, rarityLegendary, 8, 8, false, false, true);

		Method getDeck = oHunter.getClass().getMethod("getDeck");

		ArrayList<Object> hunterDeck = (ArrayList<Object>) getDeck
				.invoke(oHunter);

		boolean found = false;
		for (Object card : hunterDeck) {
			if (card.getClass() == minionClass) {
				if (equalMinion(krush, card))
					found = true;
			}
		}
		if (!found)
			fail("Hunter's deck should contain a minion called 'King Krush'.");
	}

	@Test(timeout = 1000)
	public void testSpecialLegendaryMinionForMage()
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		Class minionClass = Class.forName(minionPath);
		Class hunterClass = Class.forName(hunterPath);
		Class mageClass = Class.forName(magePath);

		Object rarityLegendary = Enum.valueOf(
				(Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		Class[] minionCons = { String.class, int.class,
				Class.forName(rarityPath), int.class, int.class, boolean.class,
				boolean.class, boolean.class };

		Object oHunter = hunterClass.getConstructor().newInstance();
		Object oMage = mageClass.getConstructor().newInstance();

		Object kalycgos = minionClass.getConstructor(minionCons).newInstance(
				"Kalycgos", 10, rarityLegendary, 4, 12, false, false, false);

		Method getDeck = oHunter.getClass().getMethod("getDeck");

		ArrayList<Object> mageDeck = (ArrayList<Object>) getDeck.invoke(oMage);

		boolean found = false;
		for (Object card : mageDeck) {
			if (card.getClass() == minionClass) {
				if (equalMinion(kalycgos, card))
					found = true;
			}
		}
		if (!found)
			fail("Mage's deck should contain a minion called 'Kalycgos'.");

	}

	@Test(timeout = 1000)
	public void testSpecialLegendaryMinionForPaladin()
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		Class minionClass = Class.forName(minionPath);
		Class hunterClass = Class.forName(hunterPath);
		Class paladinClass = Class.forName(paladinPath);

		Object rarityLegendary = Enum.valueOf(
				(Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		Class[] minionCons = { String.class, int.class,
				Class.forName(rarityPath), int.class, int.class, boolean.class,
				boolean.class, boolean.class };

		Object oHunter = hunterClass.getConstructor().newInstance();

		Object oPaladin = paladinClass.getConstructor().newInstance();

		Object tirion = minionClass.getConstructor(minionCons).newInstance(
				"Tirion Fordring", 4, rarityLegendary, 6, 6, true, true, false);

		Method getDeck = oHunter.getClass().getMethod("getDeck");

		ArrayList<Object> paladinDeck = (ArrayList<Object>) getDeck
				.invoke(oPaladin);

		boolean found = false;
		for (Object card : paladinDeck) {
			if (card.getClass() == minionClass) {
				if (equalMinion(tirion, card))
					found = true;
			}
		}
		if (!found)
			fail("Paladin's deck should contain a minion called 'Tirion Fordring'.");
	}

	@Test(timeout = 1000)
	public void testSpecialLegendaryMinionForPriest()
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		Class minionClass = Class.forName(minionPath);
		Class hunterClass = Class.forName(hunterPath);
		Class priestClass = Class.forName(priestPath);

		Object rarityLegendary = Enum.valueOf(
				(Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		Class[] minionCons = { String.class, int.class,
				Class.forName(rarityPath), int.class, int.class, boolean.class,
				boolean.class, boolean.class };

		Object oHunter = hunterClass.getConstructor().newInstance();

		Object oPriest = priestClass.getConstructor().newInstance();

		Object velen = minionClass.getConstructor(minionCons).newInstance(
				"Prophet Velen", 7, rarityLegendary, 7, 7, false, false, false);

		Method getDeck = oHunter.getClass().getMethod("getDeck");

		ArrayList<Object> priestDeck = (ArrayList<Object>) getDeck
				.invoke(oPriest);

		boolean found = false;
		for (Object card : priestDeck) {
			if (card.getClass() == minionClass) {
				if (equalMinion(velen, card))
					found = true;
			}
		}
		if (!found)
			fail("Priest's deck should contain a minion called 'Prophet Velen'.");
	}

	@Test(timeout = 1000)
	public void testSpecialLegendaryMinionForWarlock()
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		Class minionClass = Class.forName(minionPath);
		Class hunterClass = Class.forName(hunterPath);
		Class warlockClass = Class.forName(warlockPath);

		Object rarityLegendary = Enum.valueOf(
				(Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		Class[] minionCons = { String.class, int.class,
				Class.forName(rarityPath), int.class, int.class, boolean.class,
				boolean.class, boolean.class };

		Object oHunter = hunterClass.getConstructor().newInstance();
		Object oWarlock = warlockClass.getConstructor().newInstance();
		Object wilfred = minionClass.getConstructor(minionCons).newInstance(
				"Wilfred Fizzlebang", 6, rarityLegendary, 4, 4, false, false,
				false);

		Method getDeck = oHunter.getClass().getMethod("getDeck");
		ArrayList<Object> warlockDeck = (ArrayList<Object>) getDeck
				.invoke(oWarlock);

		boolean found = false;
		for (Object card : warlockDeck) {
			if (card.getClass() == minionClass) {
				if (equalMinion(wilfred, card))
					found = true;
			}
		}
		if (!found)
			fail("Warlock's deck should contain a minion called 'Wilfred Fizzlebang'.");
	}

	public boolean equalMinion(Object correct, Object check)
			throws ClassNotFoundException, IllegalArgumentException,
			IllegalAccessException {
		Class<?> minion = Class.forName("model.cards.minions.Minion");
		Field[] fields = minion.getDeclaredFields();
		Field[] fields2 = minion.getSuperclass().getDeclaredFields();
		for (Field f : fields)
			f.setAccessible(true);
		for (Field f : fields2)
			f.setAccessible(true);

		for (Field f : fields) {
			if (!(f.get(correct).equals(f.get(check))))
				return false;
		}

		for (Field f : fields2) {
			if (!(f.get(correct).equals(f.get(check))))
				return false;
		}

		return true;
	}

	@Test(timeout = 1000)
	public void testCorrectSpellsForHunter() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		Class hunterClass = Class.forName(hunterPath);
		Class cureOfWeaknessClass = Class.forName(curseOfWeaknessPath);
		Class divineSpiritClass = Class.forName(divineSpiritPath);
		Class flameStrikeClass = Class.forName(flamestrikePath);
		Class holyNovaClass = Class.forName(holyNovaPath);
		Class killCommandClass = Class.forName(killCommandPath);
		Class levelUpClass = Class.forName(levelUpPath);
		Class multiShotClass = Class.forName(multiShotPath);
		Class polymorphClass = Class.forName(polymorphPath);
		Class pyroblastClass = Class.forName(pyroblastPath);
		Class sealofChampionsClass = Class.forName(sealOfChampionsPath);
		Class shadowWordDeathClass = Class.forName(shadowWordDeathPath);
		Class siphonSoulClass = Class.forName(siphonSoulPath);
		Class twistingNetherClass = Class.forName(twistingNetherPath);

		Object oHunter = hunterClass.getConstructor().newInstance();
		Method getDeck = oHunter.getClass().getMethod("getDeck");

		ArrayList<Object> hunterDeck = (ArrayList<Object>) getDeck
				.invoke(oHunter);

		int killCommandcount = 0;
		int multiShotcount = 0;
		for (Object card : hunterDeck) {
			if (card.getClass() == killCommandClass)
				killCommandcount++;
			else if (card.getClass() == multiShotClass)
				multiShotcount++;

			else if (card.getClass() == cureOfWeaknessClass
					|| card.getClass() == divineSpiritClass
					|| card.getClass() == flameStrikeClass
					|| card.getClass() == holyNovaClass
					|| card.getClass() == levelUpClass
					|| card.getClass() == polymorphClass
					|| card.getClass() == pyroblastClass
					|| card.getClass() == sealofChampionsClass
					|| card.getClass() == shadowWordDeathClass
					|| card.getClass() == siphonSoulClass
					|| card.getClass() == twistingNetherClass)
				fail("Hunter's deck should contain ONLY KillCommand and MultiShot spells "
						+ card.getClass().getMethod("getName")
						+ " spell is found in the Hunter's deck");

		}
		assertEquals(
				"Hunter's deck contains wrong number of KillCommand Spell", 2,
				killCommandcount);
		assertEquals("Hunter's deck contains wrong number of MultiShot Spell",
				2, multiShotcount);
	}

	@Test(timeout = 1000)
	public void testCorrectSpellsForMage() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		Class hunterClass = Class.forName(hunterPath);
		Class mageClass = Class.forName(magePath);
		Class cureOfWeaknessClass = Class.forName(curseOfWeaknessPath);
		Class divineSpiritClass = Class.forName(divineSpiritPath);
		Class flameStrikeClass = Class.forName(flamestrikePath);
		Class holyNovaClass = Class.forName(holyNovaPath);
		Class killCommandClass = Class.forName(killCommandPath);
		Class levelUpClass = Class.forName(levelUpPath);
		Class multiShotClass = Class.forName(multiShotPath);
		Class polymorphClass = Class.forName(polymorphPath);
		Class pyroblastClass = Class.forName(pyroblastPath);
		Class sealofChampionsClass = Class.forName(sealOfChampionsPath);
		Class shadowWordDeathClass = Class.forName(shadowWordDeathPath);
		Class siphonSoulClass = Class.forName(siphonSoulPath);
		Class twistingNetherClass = Class.forName(twistingNetherPath);

		Object oHunter = hunterClass.getConstructor().newInstance();
		Object oMage = mageClass.getConstructor().newInstance();
		Method getDeck = oHunter.getClass().getMethod("getDeck");

		ArrayList<Object> mageDeck = (ArrayList<Object>) getDeck.invoke(oMage);
		int polymorphcount = 0;
		int flameStrikecount = 0;
		int pyroBlastcount = 0;
		for (Object card : mageDeck) {
			if (card.getClass() == polymorphClass)
				polymorphcount++;
			else if (card.getClass() == flameStrikeClass)
				flameStrikecount++;
			else if (card.getClass() == pyroblastClass)
				pyroBlastcount++;
			else if (card.getClass() == cureOfWeaknessClass
					|| card.getClass() == divineSpiritClass
					|| card.getClass() == killCommandClass
					|| card.getClass() == holyNovaClass
					|| card.getClass() == levelUpClass
					|| card.getClass() == multiShotClass
					|| card.getClass() == sealofChampionsClass
					|| card.getClass() == shadowWordDeathClass
					|| card.getClass() == siphonSoulClass
					|| card.getClass() == twistingNetherClass)
				fail("Mage's deck should contain ONLY Polymorph, FlameStrike and PyroBlast spells "
						+ card.getClass().getMethod("getName")
						+ " spell is found in the Mage's deck");
		}

		assertEquals("Mage's deck contains wrong number of Polymorph Spell", 2,
				polymorphcount);
		assertEquals("Mage's deck contains wrong number of FlameStrike Spell",
				2, flameStrikecount);
		assertEquals("Mage's deck contains wrong number of PyroBlast Spell", 2,
				pyroBlastcount);

	}

	@Test(timeout = 1000)
	public void testCorrectSpellsForPaladin() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {

		Class hunterClass = Class.forName(hunterPath);
		Class paladinClass = Class.forName(paladinPath);
		Class cureOfWeaknessClass = Class.forName(curseOfWeaknessPath);
		Class divineSpiritClass = Class.forName(divineSpiritPath);
		Class flameStrikeClass = Class.forName(flamestrikePath);
		Class holyNovaClass = Class.forName(holyNovaPath);
		Class killCommandClass = Class.forName(killCommandPath);
		Class levelUpClass = Class.forName(levelUpPath);
		Class multiShotClass = Class.forName(multiShotPath);
		Class polymorphClass = Class.forName(polymorphPath);
		Class pyroblastClass = Class.forName(pyroblastPath);
		Class sealofChampionsClass = Class.forName(sealOfChampionsPath);
		Class shadowWordDeathClass = Class.forName(shadowWordDeathPath);
		Class siphonSoulClass = Class.forName(siphonSoulPath);
		Class twistingNetherClass = Class.forName(twistingNetherPath);

		Object oHunter = hunterClass.getConstructor().newInstance();

		Object oPaladin = paladinClass.getConstructor().newInstance();

		Method getDeck = oHunter.getClass().getMethod("getDeck");

		ArrayList<Object> paladinDeck = (ArrayList<Object>) getDeck
				.invoke(oPaladin);

		int sealOfChampioncount = 0;
		int levelUpcount = 0;
		for (Object card : paladinDeck) {
			if (card.getClass() == sealofChampionsClass)
				sealOfChampioncount++;
			else if (card.getClass() == levelUpClass)
				levelUpcount++;
			else if (card.getClass() == cureOfWeaknessClass
					|| card.getClass() == divineSpiritClass
					|| card.getClass() == killCommandClass
					|| card.getClass() == holyNovaClass
					|| card.getClass() == flameStrikeClass
					|| card.getClass() == multiShotClass
					|| card.getClass() == polymorphClass
					|| card.getClass() == shadowWordDeathClass
					|| card.getClass() == siphonSoulClass
					|| card.getClass() == twistingNetherClass
					|| card.getClass() == pyroblastClass)
				fail("Paladin's deck should contain ONLY SealOfChampions and LevelUp spells "
						+ card.getClass().getMethod("getName")
						+ " spell is found in the Paladin's deck");
		}

		assertEquals(
				"Paladin's deck contains wrong number of SealOfChampions Spell",
				2, sealOfChampioncount);
		assertEquals("Paladin's deck contains wrong number of LevelUps Spell",
				2, levelUpcount);
	}

	@Test(timeout = 1000)
	public void testCorrectSpellsForPriest() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		Class hunterClass = Class.forName(hunterPath);
		Class priestClass = Class.forName(priestPath);
		Class cureOfWeaknessClass = Class.forName(curseOfWeaknessPath);
		Class divineSpiritClass = Class.forName(divineSpiritPath);
		Class flameStrikeClass = Class.forName(flamestrikePath);
		Class holyNovaClass = Class.forName(holyNovaPath);
		Class killCommandClass = Class.forName(killCommandPath);
		Class levelUpClass = Class.forName(levelUpPath);
		Class multiShotClass = Class.forName(multiShotPath);
		Class polymorphClass = Class.forName(polymorphPath);
		Class pyroblastClass = Class.forName(pyroblastPath);
		Class sealofChampionsClass = Class.forName(sealOfChampionsPath);
		Class shadowWordDeathClass = Class.forName(shadowWordDeathPath);
		Class siphonSoulClass = Class.forName(siphonSoulPath);
		Class twistingNetherClass = Class.forName(twistingNetherPath);

		Object oHunter = hunterClass.getConstructor().newInstance();
		Object oPriest = priestClass.getConstructor().newInstance();

		Method getDeck = oHunter.getClass().getMethod("getDeck");

		ArrayList<Object> priestDeck = (ArrayList<Object>) getDeck
				.invoke(oPriest);

		int divineSpiritcount = 0;
		int holyNovacount = 0;
		int shadowWordcount = 0;
		for (Object card : priestDeck) {
			if (card.getClass() == divineSpiritClass)
				divineSpiritcount++;
			else if (card.getClass() == holyNovaClass)
				holyNovacount++;
			else if (card.getClass() == shadowWordDeathClass)
				shadowWordcount++;
			else if (card.getClass() == cureOfWeaknessClass
					|| card.getClass() == levelUpClass
					|| card.getClass() == killCommandClass
					|| card.getClass() == sealofChampionsClass
					|| card.getClass() == flameStrikeClass
					|| card.getClass() == multiShotClass
					|| card.getClass() == polymorphClass
					|| card.getClass() == siphonSoulClass
					|| card.getClass() == twistingNetherClass
					|| card.getClass() == pyroblastClass)
				fail("Priest's deck should contain ONLY DivineSpirit, HolyNova and ShadowWordDeath spells. "
						+ card.getClass().getMethod("getName")
						+ " spell is found in the Priest's deck");
		}

		assertEquals(
				"Priest's deck contains wrong number of DivineSpirit Spell", 2,
				divineSpiritcount);
		assertEquals("Priest's deck contains wrong number of HolyNova Spell",
				2, holyNovacount);
		assertEquals(
				"Priest's deck contains wrong number of ShadowWordDeath Spell",
				2, shadowWordcount);
	}

	@Test(timeout = 1000)
	public void testCorrectSpellsForWarlock() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {

		Class hunterClass = Class.forName(hunterPath);
		Class warlockClass = Class.forName(warlockPath);
		Class cureOfWeaknessClass = Class.forName(curseOfWeaknessPath);
		Class divineSpiritClass = Class.forName(divineSpiritPath);
		Class flameStrikeClass = Class.forName(flamestrikePath);
		Class holyNovaClass = Class.forName(holyNovaPath);
		Class killCommandClass = Class.forName(killCommandPath);
		Class levelUpClass = Class.forName(levelUpPath);
		Class multiShotClass = Class.forName(multiShotPath);
		Class polymorphClass = Class.forName(polymorphPath);
		Class pyroblastClass = Class.forName(pyroblastPath);
		Class sealofChampionsClass = Class.forName(sealOfChampionsPath);
		Class shadowWordDeathClass = Class.forName(shadowWordDeathPath);
		Class siphonSoulClass = Class.forName(siphonSoulPath);
		Class twistingNetherClass = Class.forName(twistingNetherPath);

		Object oHunter = hunterClass.getConstructor().newInstance();
		Object oWarlock = warlockClass.getConstructor().newInstance();

		Method getDeck = oHunter.getClass().getMethod("getDeck");

		ArrayList<Object> warlockDeck = (ArrayList<Object>) getDeck
				.invoke(oWarlock);

		int curseOfWeaknesscount = 0;
		int siphonSoulcount = 0;
		int tswistingNethercount = 0;
		for (Object card : warlockDeck) {
			if (card.getClass() == cureOfWeaknessClass)
				curseOfWeaknesscount++;
			else if (card.getClass() == siphonSoulClass)
				siphonSoulcount++;
			else if (card.getClass() == twistingNetherClass)
				tswistingNethercount++;
			else if (card.getClass() == divineSpiritClass
					|| card.getClass() == levelUpClass
					|| card.getClass() == killCommandClass
					|| card.getClass() == sealofChampionsClass
					|| card.getClass() == flameStrikeClass
					|| card.getClass() == multiShotClass
					|| card.getClass() == polymorphClass
					|| card.getClass() == pyroblastClass
					|| card.getClass() == holyNovaClass
					|| card.getClass() == shadowWordDeathClass)
				fail("Warlock's deck should contain ONLY CurseOfWeakness, SiphonSoul and TwistingNether spells. "
						+ card.getClass().getMethod("getName")
						+ " spell is found in the Warlock's deck");
		}

		assertEquals(
				"Warlock's deck contains wrong number of CurseOfWeakness Spell",
				2, curseOfWeaknesscount);
		assertEquals(
				"Warlock's deck contains wrong number of SiphonSoul Spell", 2,
				siphonSoulcount);
		assertEquals(
				"Warlock's deck contains wrong number of TwistingNether Spell",
				2, tswistingNethercount);

	}

	@Test(timeout = 1000)
	public void testHunterDeckShuffle() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		Class spellClass = Class.forName(spellPath);
		Class minionClass = Class.forName(minionPath);
		Class iceHowlClass = Class.forName(icehowlPath);
		Class hunterClass = Class.forName(hunterPath);

		Object oHunter = hunterClass.getConstructor().newInstance();
		Method getDeck = oHunter.getClass().getMethod("getDeck");

		ArrayList<Object> hunterDeck1 = (ArrayList<Object>) getDeck
				.invoke(oHunter);
		boolean[] change = new boolean[20];
		for (int i = 0; i < 10; i++) {
			Object oHunter2 = hunterClass.getConstructor().newInstance();
			ArrayList<Object> hunterDeck2 = (ArrayList<Object>) getDeck
					.invoke(oHunter2);
			for (int j = 0; j < hunterDeck1.size(); j++) {
				if (((hunterDeck1.get(j).getClass() == minionClass || hunterDeck1
						.get(j).getClass() == iceHowlClass))
						&& (hunterDeck2.get(j).getClass().getSuperclass() == spellClass)) {
					change[j] = true;
				}
			}
		}
		boolean out = false;
		for (boolean b : change) {
			out = out || b;
		}
		if (!out)
			fail("The Hunter's deck should be shuffled");

	}

	@Test
	public void testMageDeckShuffle() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		Class spellClass = Class.forName(spellPath);
		Class minionClass = Class.forName(minionPath);
		Class iceHowlClass = Class.forName(icehowlPath);
		Class hunterClass = Class.forName(hunterPath);
		Class mageClass = Class.forName(magePath);

		Object oHunter = hunterClass.getConstructor().newInstance();
		Object oMage = mageClass.getConstructor().newInstance();

		Method getDeck = oHunter.getClass().getMethod("getDeck");
		boolean[] change = new boolean[20];
		ArrayList<Object> mageDeck1 = (ArrayList<Object>) getDeck.invoke(oMage);

		for (int i = 0; i < 10; i++) {
			Object oMage2 = mageClass.getConstructor().newInstance();
			ArrayList<Object> mageDeck2 = (ArrayList<Object>) getDeck
					.invoke(oMage2);
			for (int j = 0; j < mageDeck1.size(); j++) {
				if (((mageDeck1.get(j).getClass() == minionClass || mageDeck1
						.get(j).getClass() == iceHowlClass))
						&& (mageDeck2.get(j).getClass().getSuperclass() == spellClass)) {
					change[j] = true;
				}
			}
		}
		boolean out = false;
		for (boolean b : change) {
			out = out || b;
		}
		if (!out)
			fail("The Mage's deck should be shuffled");

	}

	@Test
	public void testPaladinDeckShuffle() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		Class spellClass = Class.forName(spellPath);
		Class minionClass = Class.forName(minionPath);
		Class iceHowlClass = Class.forName(icehowlPath);
		Class hunterClass = Class.forName(hunterPath);
		Class paladinClass = Class.forName(paladinPath);

		Object oHunter = hunterClass.getConstructor().newInstance();
		Object oPaladin = paladinClass.getConstructor().newInstance();

		Method getDeck = oHunter.getClass().getMethod("getDeck");
		boolean[] change = new boolean[20];
		ArrayList<Object> paladinDeck1 = (ArrayList<Object>) getDeck
				.invoke(oPaladin);
		for (int i = 0; i < 10; i++) {
			Object oPaladin2 = paladinClass.getConstructor().newInstance();
			ArrayList<Object> paladinDeck2 = (ArrayList<Object>) getDeck
					.invoke(oPaladin2);
			for (int j = 0; j < paladinDeck1.size(); j++) {
				if (((paladinDeck1.get(j).getClass() == minionClass || paladinDeck1
						.get(j).getClass() == iceHowlClass))
						&& (paladinDeck2.get(j).getClass().getSuperclass() == spellClass)) {
					change[j] = true;
				}
			}
		}
		boolean out = false;
		for (boolean b : change) {
			out = out || b;
		}
		if (!out)
			fail("The Paladin's deck should be shuffled");
	}

	@Test
	public void testPriestDeckShuffle() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		Class spellClass = Class.forName(spellPath);
		Class minionClass = Class.forName(minionPath);
		Class iceHowlClass = Class.forName(icehowlPath);
		Class hunterClass = Class.forName(hunterPath);
		Class priestClass = Class.forName(priestPath);

		Object oHunter = hunterClass.getConstructor().newInstance();
		Object oPriest = priestClass.getConstructor().newInstance();

		Method getDeck = oHunter.getClass().getMethod("getDeck");
		boolean[] change = new boolean[20];
		ArrayList<Object> priestDeck1 = (ArrayList<Object>) getDeck
				.invoke(oPriest);
		for (int i = 0; i < 10; i++) {
			Object oPriest2 = priestClass.getConstructor().newInstance();
			ArrayList<Object> priestDeck2 = (ArrayList<Object>) getDeck
					.invoke(oPriest2);
			for (int j = 0; j < priestDeck1.size(); j++) {
				if (((priestDeck1.get(j).getClass() == minionClass || priestDeck1
						.get(j).getClass() == iceHowlClass))
						&& (priestDeck2.get(j).getClass().getSuperclass() == spellClass)) {
					change[j] = true;
				}
			}
		}
		boolean out = false;
		for (boolean b : change) {
			out = out || b;
		}
		if (!out)
			fail("The Priest's deck should be shuffled");
	}

	@Test
	public void testWarlockDeckShuffle() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		Class spellClass = Class.forName(spellPath);
		Class minionClass = Class.forName(minionPath);
		Class iceHowlClass = Class.forName(icehowlPath);
		Class hunterClass = Class.forName(hunterPath);
		Class warlockClass = Class.forName(warlockPath);

		Object oHunter = hunterClass.getConstructor().newInstance();
		Object oWarlock = warlockClass.getConstructor().newInstance();

		Method getDeck = oHunter.getClass().getMethod("getDeck");
		boolean[] change = new boolean[20];
		ArrayList<Object> warlockDeck1 = (ArrayList<Object>) getDeck
				.invoke(oWarlock);
		for (int i = 0; i < 10; i++) {
			Object oWarlock2 = warlockClass.getConstructor().newInstance();
			ArrayList<Object> warlockDeck2 = (ArrayList<Object>) getDeck
					.invoke(oWarlock2);
			for (int j = 0; j < warlockDeck1.size(); j++) {
				if (((warlockDeck1.get(j).getClass() == minionClass || warlockDeck1
						.get(j).getClass() == iceHowlClass))
						&& (warlockDeck2.get(j).getClass().getSuperclass() == spellClass)) {
					change[j] = true;
				}
			}
		}
		boolean out = false;
		for (boolean b : change) {
			out = out || b;
		}
		if (!out)
			fail("The Warlock's deck should be shuffled");
	}



// ############################################# Special Game

	@Test(timeout = 1000)
	public void testGetAllNeutralMinionsisFinal()
			throws ClassNotFoundException, NoSuchMethodException, SecurityException {
		Class hero = Class.forName(heroPath);
		Method m = hero.getDeclaredMethod("getAllNeutralMinions", String.class);

		assertTrue("Method \"getAllNeutralMinions\" should not be overridden by any subclass.",
				Modifier.isFinal(m.getModifiers()));
	}

	@Test(timeout = 1000)
	public void testGetNeutralMinionsisFinal() throws ClassNotFoundException, NoSuchMethodException, SecurityException {
		Class hero = Class.forName(heroPath);
		Method m = hero.getDeclaredMethod("getNeutralMinions", ArrayList.class, int.class);

		assertTrue(
				"Method \"getNeutralMinions\" should not be overridden by any subclass.",
				Modifier.isFinal(m.getModifiers()));
	}

	@Test(timeout = 1000)
	public void testGetAllNeutralMinionsMethod()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, IOException {
		PrintWriter minionWriter = new PrintWriter("test_minion.csv");
		minionWriter.println("Batman,1,b,1,2,TRUE,FALSE,FALSE");
		minionWriter.println("Superman,1,c,1,1,FALSE,FALSE,TRUE");
		minionWriter.println("Joker,2,r,3,2,FALSE,FALSE,FALSE");
		minionWriter.println("Bane,1,e,3,2,TRUE,FALSE,FALSE");
		minionWriter.println("Riddler,2,l,1,2,FALSE,FALSE,TRUE");

		minionWriter.flush();
		minionWriter.close();

		Object rarityBasic = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "BASIC");
		Object rarityCommon = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "COMMON");
		Object rarityRare = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "RARE");
		Object rarityEpic = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "EPIC");
		Object rarityLegendary = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		Class[] minionCons = { String.class, int.class, Class.forName(rarityPath), int.class, int.class, boolean.class,
				boolean.class, boolean.class };
		Class heroClass = Class.forName(heroPath);
		Class minionClass = Class.forName(minionPath);
		Object batman = minionClass.getConstructor(minionCons).newInstance("Batman", 1, rarityBasic, 1, 2, true, false,
				false);
		Object superman = minionClass.getConstructor(minionCons).newInstance("Superman", 1, rarityCommon, 1, 1, false,
				false, true);
		Object joker = minionClass.getConstructor(minionCons).newInstance("Joker", 2, rarityRare, 3, 2, false, false,
				false);
		Object bane = minionClass.getConstructor(minionCons).newInstance("Bane", 1, rarityEpic, 3, 2, true, false,
				false);
		Object riddler = minionClass.getConstructor(minionCons).newInstance("Riddler", 2, rarityLegendary, 1, 2, false,
				false, true);

		try {
			ArrayList<Object> testMinions = (ArrayList<Object>) heroClass
					.getDeclaredMethod("getAllNeutralMinions", String.class).invoke(heroClass, "test_minion.csv");
			ArrayList<Object> csvMinions = new ArrayList<Object>();
			csvMinions.add(batman);
			csvMinions.add(superman);
			csvMinions.add(joker);
			csvMinions.add(bane);
			csvMinions.add(riddler);
			testGetAllNeutralMinionsMethod(csvMinions, testMinions);

			File test = new File("test_minion.csv");
			test.delete();
		} catch (Exception e) {
			File test = new File("test_minion.csv");
			test.delete();
		}

	}

@Test(timeout = 10000)
	public void testConstructorInitialization4900Game() throws Exception
	{
		Object paladin00 = Class.forName(paladinPath).getConstructor().newInstance();

		Object warlock00 = Class.forName(warlockPath).getConstructor().newInstance();

		int c1 = 0;
		int c2 = 0;
		for(int i = 0 ; i < 50 ; i++)
		{
			Object game00 = Class.forName(gamePath).getConstructor(Class.forName(heroPath), Class.forName(heroPath)).newInstance(paladin00, warlock00);
			
			String[] names = {"firstHero", "secondHero", "currentHero", "opponent"};
			Object[] values = {paladin00, warlock00, warlock00, paladin00};

			boolean r1 = testConstructorInitializationWithRandom(game00, names, values);
		
			String[] names2 = {"firstHero", "secondHero", "opponent" , "currentHero"};
			Object[] values2 = {paladin00, warlock00, warlock00, paladin00};
			boolean r2 = testConstructorInitializationWithRandom(game00, names2, values2);
			if(!(r1 || r2))
				fail("Class Game should assign heroes to current and opponent.");
			if(r1)
				c1++;
			if(r2)
				c2++;
			if(c1 > 0 && c2 > 0 )
				break;
		}
		if(c1 == 0 || c2 == 0)
			fail("Class Game should assign heroes to current and opponent randomly.");
		
	}
	
	


// ############################################# Helper methods


	private boolean testConstructorInitializationWithRandom(Object createdObject, String[] names, Object[] values) throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException 
	{
		boolean res = true;
		for (int i = 0; i < names.length; i++) 
		{
			Field f = null;
			Class curr = createdObject.getClass();
			String currName = names[i];
			Object currValue = values[i];
			while (f == null) 
			{
				if (curr == Object.class)
					fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \"" + currName + "\".");
				try 
				{
					f = curr.getDeclaredField(currName);
				} 
				catch (NoSuchFieldException e)
				{
					curr = curr.getSuperclass();
				}
			}
			f.setAccessible(true);
			res = res &&  currValue.equals( f.get(createdObject));
		}
		return res;
	}
	
	private void testEnumValues(Class aClass, String [] value) throws ClassNotFoundException 
	{
		for(int i=0;i<value.length;i++)
		{
			try 
			{
				Enum.valueOf((Class<Enum>) aClass, value[i]);
			} 
			catch (IllegalArgumentException e) 
			{
				fail(aClass.getSimpleName()+" enum can be " + value[i]);
			}
		}
	}

	private void testInstanceVariableIsPresent(Class aClass, String varName, boolean implementedVar) throws SecurityException 
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
	
	private void testInstanceVariableIsPrivate(Class aClass, String varName) throws NoSuchFieldException, SecurityException 
	{
		Field f = aClass.getDeclaredField(varName);
		assertEquals("The \""+ varName + "\" instance variable in class " + aClass.getSimpleName() + " should not be accessed outside that class.", 2, f.getModifiers());
	}
	
	private void testGetterMethodExistsInClass(Class aClass, String methodName, Class returnedType, boolean writeVariable)
	{
		Method m = null;
		boolean found = true;
		try
		{
			m = aClass.getDeclaredMethod(methodName);
		} catch (Exception e)
		{
			found = false;
		}
		String varName = "";
		if (returnedType == boolean.class)
			varName = methodName.substring(2);
		else
			varName = methodName.substring(3);
		varName = Character.toLowerCase(varName.charAt(0))
				+ varName.substring(1);
		if (writeVariable)
		{
			assertTrue("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + " is a READ variable.", found);
			assertTrue("Incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + " class.", m.getReturnType()
					.isAssignableFrom(returnedType));
		}
		else
		{
			assertFalse("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + " is not a READ variable.", found);
		}
	}
	
	private void testSetterMethodExistsInClass(Class aClass, String methodName, Class inputType, boolean writeVariable) 
	{
		Method[] methods = aClass.getDeclaredMethods();
		String varName = methodName.substring(3);
		varName = Character.toLowerCase(varName.charAt(0))
				+ varName.substring(1);
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
	
	private void testConstructorExists(Class aClass, Class[] inputs) 
	{
		boolean thrown = false;
		try 
		{
			aClass.getConstructor(inputs);
		} 
		catch (NoSuchMethodException e) 
		{
			thrown = true;
		}
		if (inputs.length > 0) 
		{
			String msg = "";
			int i = 0;
			do 
			{
				msg += inputs[i].getSimpleName() + " and ";
				i++;
			} while (i < inputs.length);
			msg = msg.substring(0, msg.length() - 4);
			assertFalse("Missing constructor with " + msg + " parameter" + (inputs.length > 1 ? "s" : "") + " in " + aClass.getSimpleName() + " class.", thrown);
		} 
		else
			assertFalse("Missing constructor with zero parameters in " + aClass.getSimpleName() + " class.", thrown);
	}
	
	private void testClassIsAbstract(Class aClass) 
	{
		assertTrue("You should not be able to create new instances from " + aClass.getSimpleName() + " class.", Modifier.isAbstract(aClass.getModifiers()));
	}

	private void testIsInterface(Class aClass) 
	{
		assertEquals(aClass.getName() + " should be an Interface", aClass.isInterface(), true);
	}
	
	private void testIsEnum(Class aClass) 
	{
		assertEquals(aClass.getName() + " should be an Enum", aClass.isEnum(), true);
	}

	private void testClassIsSubclass(Class subClass, Class superClass) 
	{
		assertEquals(subClass.getSimpleName() + " class should be a subclass from " + superClass.getSimpleName() + ".", superClass, subClass.getSuperclass());
	}
	
	private void testConstructorInitializationException(Object createdObject, String message) throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException 
	{
		Class throwableClass = Throwable.class;
		Field messageFiled = throwableClass.getDeclaredField("detailMessage");
		messageFiled.setAccessible(true);
		assertEquals("The Exception constructor of the " + createdObject.getClass().getSimpleName() + " class should initialize the message correctly.", message, messageFiled.get(createdObject));
	}
	
	private void testConstructorInitialization(Object createdObject, String[] names, Object[] values) throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException 
	{
		for (int i = 0; i < names.length; i++) 
		{
			Field f = null;
			Class curr = createdObject.getClass();
			String currName = names[i];
			Object currValue = values[i];
			while (f == null) 
			{
				if (curr == Object.class)
					fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \"" + currName + "\".");
				try 
				{
					f = curr.getDeclaredField(currName);
				} 
				catch (NoSuchFieldException e)
				{
					curr = curr.getSuperclass();
				}
			}
			f.setAccessible(true);
			assertEquals("The constructor of the " + createdObject.getClass().getSimpleName() + " class should initialize the instance variable \"" + currName + "\" correctly.", currValue, f.get(createdObject));
		}
	}
	
	private void testGetterLogic(Object createdObject, String name, Object value) throws Exception 
	{
		Field f = null;
		Class curr = createdObject.getClass();
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
		f.set(createdObject, value);
		Character c = name.charAt(0);
		String methodName = "get" + Character.toUpperCase(c) + name.substring(1, name.length());
		if (value.getClass().equals(Boolean.class))
			methodName = "is" + Character.toUpperCase(c) + name.substring(1, name.length());
		Method m = createdObject.getClass().getMethod(methodName);
		assertEquals("The method \"" + methodName + "\" in class " + createdObject.getClass().getSimpleName() + " should return the correct value of variable \"" + name + "\".", value, m.invoke(createdObject));
	}
	
	private void testSetterLogic(Object createdObject, String name, Object valueIn, Object valueOut, Class type) throws Exception 
	{
		Field f = null;
		Class curr = createdObject.getClass();
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
	
	private void testClassImplementsInterface(Class aClass, Class interfaceName)
	{
		Class[] interfaces = aClass.getInterfaces();
		boolean implement = false;
		for (Class i : interfaces)
		{
			if (i.toString().equals(interfaceName.toString()))
				implement = true;
		}
		assertTrue(aClass.getSimpleName() + " class should implement " + interfaceName.getSimpleName() + " interface.", implement);
	}
}
