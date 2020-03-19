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

@SuppressWarnings({ "rawtypes", "unchecked" })
public class M1PrivateTests
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
	public void testClassIsInterfaceHeroTargetSpell() throws Exception
	{
		testIsInterface(Class.forName(heroTargetSpellPath));
	}


	@Test(timeout = 1000)
	public void testClassIsAbstractSpell() throws Exception
	{
		testClassIsAbstract(Class.forName(spellPath));
	}


	@Test(timeout = 1000)
	public void testHeroIsSuperClassOfPaladin() throws Exception
	{
		testClassIsSubclass(Class.forName(paladinPath), Class.forName(heroPath));
	}


	@Test(timeout = 1000)
	public void testCardIsSuperClassOfSpell() throws Exception
	{
		testClassIsSubclass(Class.forName(spellPath), Class.forName(cardPath));
	}


	@Test(timeout = 1000)
	public void testSpellIsSuperClassOfShadowWordDeath() throws Exception
	{
		testClassIsSubclass(Class.forName(shadowWordDeathPath), Class.forName(spellPath));
	}


	@Test(timeout = 1000)
	public void testSpellIsSuperClassOfPolymorph() throws Exception
	{
		testClassIsSubclass(Class.forName(polymorphPath), Class.forName(spellPath));
	}


	@Test(timeout = 1000)
	public void testSpellIsSuperClassOfKillCommand() throws Exception
	{
		testClassIsSubclass(Class.forName(killCommandPath), Class.forName(spellPath));
	}


	@Test(timeout = 1000)
	public void testSpellIsSuperClassOfDivineSpirit() throws Exception
	{
		testClassIsSubclass(Class.forName(divineSpiritPath), Class.forName(spellPath));
	}


	@Test(timeout = 1000)
	public void testMinionIsSuperClassOfIcehowl() throws Exception
	{
		testClassIsSubclass(Class.forName(icehowlPath), Class.forName(minionPath));
	}


	@Test(timeout = 1000)
	public void testHearthstoneExceptionIsSuperClassOfNotSummonedException() throws Exception
	{
		testClassIsSubclass(Class.forName(notSummonedExceptionPath), Class.forName(hearthstoneExceptionPath));
	}


	@Test(timeout = 1000)
	public void testHearthstoneExceptionIsSuperClassOfHeroPowerAlreadyUsedException() throws Exception
	{
		testClassIsSubclass(Class.forName(heroPowerAlreadyUsedExceptionPath), Class.forName(hearthstoneExceptionPath));
	}


	@Test(timeout = 1000)
	public void testHearthstoneExceptionIsSuperClassOfCannotAttackException() throws Exception
	{
		testClassIsSubclass(Class.forName(cannotAttackExceptionPath), Class.forName(hearthstoneExceptionPath));
	}


	@Test(timeout = 1000)
	public void testInterfaceMinionTargetSpellIsImplementedByClassShadowWordDeath() throws Exception
	{
		testClassImplementsInterface(Class.forName(shadowWordDeathPath), Class.forName(minionTargetSpellPath));
	}


	@Test(timeout = 1000)
	public void testInterfaceMinionTargetSpellIsImplementedByClassPyroblast() throws Exception
	{
		testClassImplementsInterface(Class.forName(pyroblastPath), Class.forName(minionTargetSpellPath));
	}


	@Test(timeout = 1000)
	public void testInterfaceFieldSpellIsImplementedByClassLevelUp() throws Exception
	{
		testClassImplementsInterface(Class.forName(levelUpPath), Class.forName(fieldSpellPath));
	}


	@Test(timeout = 1000)
	public void testInterfaceAOESpellIsImplementedByClassHolyNova() throws Exception
	{
		testClassImplementsInterface(Class.forName(holyNovaPath), Class.forName(aOESpellPath));
	}


	@Test(timeout = 1000)
	public void testInterfaceAOESpellIsImplementedByClassCurseOfWeakness() throws Exception
	{
		testClassImplementsInterface(Class.forName(curseOfWeaknessPath), Class.forName(aOESpellPath));
	}


	@Test(timeout = 1000)
	public void testConstructor0Priest() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(priestPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0Hunter() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(hunterPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0TwistingNether() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(twistingNetherPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0SealOfChampions() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(sealOfChampionsPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0MultiShot() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(multiShotPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0HolyNova() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(holyNovaPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0CurseOfWeakness() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(curseOfWeaknessPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0HearthstoneException() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(hearthstoneExceptionPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor1TauntBypassException() throws Exception
	{
		Class[] inputs = {String.class};
		testConstructorExists(Class.forName(tauntBypassExceptionPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0NotSummonedException() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(notSummonedExceptionPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor1NotEnoughManaException() throws Exception
	{
		Class[] inputs = {String.class};
		testConstructorExists(Class.forName(notEnoughManaExceptionPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0HeroPowerAlreadyUsedException() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(heroPowerAlreadyUsedExceptionPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor1FullHandException() throws Exception
	{
		Class[] inputs = {String.class, Class.forName(cardPath)};
		testConstructorExists(Class.forName(fullHandExceptionPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructor0CannotAttackException() throws Exception
	{
		Class[] inputs = {};
		testConstructorExists(Class.forName(cannotAttackExceptionPath), inputs);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization1900Paladin() throws Exception
	{
		Object paladin00 = Class.forName(paladinPath).getConstructor().newInstance();

		String utherLightbringerString = "Uther Lightbringer";
		int int30 = 30;
		boolean boolean02 = false;
		int int00 = 0;
		String[] names = {"name", "currentHP", "heroPowerUsed", "totalManaCrystals", "currentManaCrystals", "fatigueDamage"};
		Object[] values = {utherLightbringerString, int30, boolean02, int00, int00, int00};

		testConstructorInitialization(paladin00, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization2400TwistingNether() throws Exception
	{
		Object twistingNether00 = Class.forName(twistingNetherPath).getConstructor().newInstance();

		String twistingNetherString = "Twisting Nether";
		int int08 = 8;
		Object rarity03 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "EPIC");
		String[] names = {"name", "manaCost", "rarity"};
		Object[] values = {twistingNetherString, int08, rarity03};

		testConstructorInitialization(twistingNether00, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization2700SealOfChampions() throws Exception
	{
		Object sealOfChampions00 = Class.forName(sealOfChampionsPath).getConstructor().newInstance();

		String sealofChampionsString = "Seal of Champions";
		int int03 = 3;
		Object rarity01 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "COMMON");
		String[] names = {"name", "manaCost", "rarity"};
		Object[] values = {sealofChampionsString, int03, rarity01};

		testConstructorInitialization(sealOfChampions00, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization3000MultiShot() throws Exception
	{
		Object multiShot00 = Class.forName(multiShotPath).getConstructor().newInstance();

		String multiShotString = "Multi-Shot";
		int int04 = 4;
		Object rarity00 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "BASIC");
		String[] names = {"name", "manaCost", "rarity"};
		Object[] values = {multiShotString, int04, rarity00};

		testConstructorInitialization(multiShot00, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization3300HolyNova() throws Exception
	{
		Object holyNova00 = Class.forName(holyNovaPath).getConstructor().newInstance();

		String holyNovaString = "Holy Nova";
		int int05 = 5;
		Object rarity00 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "BASIC");
		String[] names = {"name", "manaCost", "rarity"};
		Object[] values = {holyNovaString, int05, rarity00};

		testConstructorInitialization(holyNova00, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization3600CurseOfWeakness() throws Exception
	{
		Object curseOfWeakness00 = Class.forName(curseOfWeaknessPath).getConstructor().newInstance();

		String curseofWeaknessString = "Curse of Weakness";
		int int02 = 2;
		Object rarity02 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "RARE");
		String[] names = {"name", "manaCost", "rarity"};
		Object[] values = {curseofWeaknessString, int02, rarity02};

		testConstructorInitialization(curseOfWeakness00, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization4000TauntBypassException() throws Exception
	{
		String string00 = "d02";
		Object tauntBypassException00 = Class.forName(tauntBypassExceptionPath).getConstructor(String.class).newInstance(string00);

		String[] names = {};
		Object[] values = {};

		testConstructorInitialization(tauntBypassException00, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization4101NotYourTurnException() throws Exception
	{
		Object notYourTurnException01 = Class.forName(notYourTurnExceptionPath).getConstructor().newInstance();

		String[] names = {};
		Object[] values = {};

		testConstructorInitialization(notYourTurnException01, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization4300NotEnoughManaException() throws Exception
	{
		String string00 = "d02";
		Object notEnoughManaException00 = Class.forName(notEnoughManaExceptionPath).getConstructor(String.class).newInstance(string00);

		String[] names = {};
		Object[] values = {};

		testConstructorInitialization(notEnoughManaException00, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization4401InvalidTargetException() throws Exception
	{
		Object invalidTargetException01 = Class.forName(invalidTargetExceptionPath).getConstructor().newInstance();

		String[] names = {};
		Object[] values = {};

		testConstructorInitialization(invalidTargetException01, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization4600FullHandException() throws Exception
	{
		String string00 = "d02";
		Object multiShot00 = Class.forName(multiShotPath).getConstructor().newInstance();

		Object fullHandException00 = Class.forName(fullHandExceptionPath).getConstructor(String.class, Class.forName(cardPath)).newInstance(string00, multiShot00);

		String[] names = {"burned"};
		Object[] values = {multiShot00};

		testConstructorInitialization(fullHandException00, names, values);
	}


	@Test(timeout = 1000)
	public void testConstructorInitialization4801CannotAttackException() throws Exception
	{
		Object cannotAttackException01 = Class.forName(cannotAttackExceptionPath).getConstructor().newInstance();

		String[] names = {};
		Object[] values = {};

		testConstructorInitialization(cannotAttackException01, names, values);
	}


	@Test(timeout = 1000)
	public void testInstanceVariableHeroPowerUsedInClassIsPresentAndIsPrivateHero() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(heroPath), "heroPowerUsed", true);
		testInstanceVariableIsPrivate(Class.forName(heroPath), "heroPowerUsed");
	}


	@Test(timeout = 1000)
	public void testInstanceVariableDeckInClassIsPresentAndIsPrivateHero() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(heroPath), "deck", true);
		testInstanceVariableIsPrivate(Class.forName(heroPath), "deck");
	}


	@Test(timeout = 1000)
	public void testInstanceVariableFatigueDamageInClassIsPresentAndIsPrivateHero() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(heroPath), "fatigueDamage", true);
		testInstanceVariableIsPrivate(Class.forName(heroPath), "fatigueDamage");
	}


	@Test(timeout = 1000)
	public void testInstanceVariableRarityInClassIsPresentAndIsPrivateCard() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(cardPath), "rarity", true);
		testInstanceVariableIsPrivate(Class.forName(cardPath), "rarity");
	}


	@Test(timeout = 1000)
	public void testInstanceVariableCurrentHPInClassIsPresentAndIsPrivateMinion() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(minionPath), "currentHP", true);
		testInstanceVariableIsPrivate(Class.forName(minionPath), "currentHP");
	}


	@Test(timeout = 1000)
	public void testInstanceVariableSleepingInClassIsPresentAndIsPrivateMinion() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(minionPath), "sleeping", true);
		testInstanceVariableIsPrivate(Class.forName(minionPath), "sleeping");
	}


	@Test(timeout = 1000)
	public void testInstanceVariableFirstHeroInClassIsPresentAndIsPrivateGame() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(gamePath), "firstHero", true);
		testInstanceVariableIsPrivate(Class.forName(gamePath), "firstHero");
	}


	@Test(timeout = 1000)
	public void testInstanceVariableOpponentInClassIsPresentAndIsPrivateGame() throws Exception
	{
		
		
		testInstanceVariableIsPresent(Class.forName(gamePath), "opponent", true);
		testInstanceVariableIsPrivate(Class.forName(gamePath), "opponent");
	}


	@Test(timeout = 10000)
	public void testInstanceVariablePresentInClassHPAttackIsNotAndMaxIsNotAndCurrentIsNotAndTauntIsNotAndDivineIsNotAndSleepingIsNotAndAttackedIsNotIcehowl() throws Exception
	{
		testInstanceVariableIsPresent(Class.forName(icehowlPath), "attack", false);
		testInstanceVariableIsPresent(Class.forName(icehowlPath), "maxHP", false);
		testInstanceVariableIsPresent(Class.forName(icehowlPath), "currentHP", false);
		testInstanceVariableIsPresent(Class.forName(icehowlPath), "taunt", false);
		testInstanceVariableIsPresent(Class.forName(icehowlPath), "divine", false);
		testInstanceVariableIsPresent(Class.forName(icehowlPath), "sleeping", false);
		testInstanceVariableIsPresent(Class.forName(icehowlPath), "attacked", false);
	}


	@Test(timeout = 1000)
	public void testGetterForInstanceVariableHeroPowerUsedExistsInClassHero() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(heroPath), "isHeroPowerUsed", boolean.class, true);
	}


	@Test(timeout = 1000)
	public void testGetterForInstanceVariableDeckExistsInClassHero() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(heroPath), "getDeck", ArrayList.class, true);
	}


	@Test(timeout = 1000)
	public void testGetterForInstanceVariableNameExistsInClassCard() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(cardPath), "getName", String.class, true);
	}


	@Test(timeout = 1000)
	public void testGetterForInstanceVariableAttackExistsInClassMinion() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(minionPath), "getAttack", int.class, true);
	}


	@Test(timeout = 1000)
	public void testGetterForInstanceVariableTauntExistsInClassMinion() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(minionPath), "isTaunt", boolean.class, true);
	}


	@Test(timeout = 1000)
	public void testGetterForInstanceVariableAttackedExistsInClassMinion() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(minionPath), "isAttacked", boolean.class, true);
	}


	@Test(timeout = 1000)
	public void testSetterForInstanceVariableTotalManaCrystalsExistsInClassHero() throws Exception
	{
		testSetterMethodExistsInClass(Class.forName(heroPath), "setTotalManaCrystals", int.class, true);
	}


	@Test(timeout = 1000)
	public void testSetterForInstanceVariableManaCostExistsInClassCard() throws Exception
	{
		testSetterMethodExistsInClass(Class.forName(cardPath), "setManaCost", int.class, true);
	}


	@Test(timeout = 1000)
	public void testSetterForInstanceVariableCurrentHPExistsInClassMinion() throws Exception
	{
		testSetterMethodExistsInClass(Class.forName(minionPath), "setCurrentHP", int.class, true);
	}


	@Test(timeout = 1000)
	public void testSetterForInstanceVariableSleepingExistsInClassMinion() throws Exception
	{
		testSetterMethodExistsInClass(Class.forName(minionPath), "setSleeping", boolean.class, true);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableNamepaladin00utherLightbringerStringInClassHero() throws Exception
	{
		Object paladin00 = Class.forName(paladinPath).getConstructor().newInstance();

		String utherLightbringerString = "Uther Lightbringer";

		testGetterLogic(paladin00, "name", utherLightbringerString);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableCurrentHPwarlock00int30InClassHero() throws Exception
	{
		Object warlock00 = Class.forName(warlockPath).getConstructor().newInstance();

		int int30 = 30;

		testGetterLogic(warlock00, "currentHP", int30);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableCurrentHPmage00int30InClassHero() throws Exception
	{
		Object mage00 = Class.forName(magePath).getConstructor().newInstance();

		int int30 = 30;

		testGetterLogic(mage00, "currentHP", int30);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableHeroPowerUsedpriest00boolean02InClassHero() throws Exception
	{
		Object priest00 = Class.forName(priestPath).getConstructor().newInstance();

		boolean boolean02 = false;

		testGetterLogic(priest00, "heroPowerUsed", boolean02);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableHeroPowerUsedhunter00boolean02InClassHero() throws Exception
	{
		Object hunter00 = Class.forName(hunterPath).getConstructor().newInstance();

		boolean boolean02 = false;

		testGetterLogic(hunter00, "heroPowerUsed", boolean02);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableTotalManaCrystalspaladin00int00InClassHero() throws Exception
	{
		Object paladin00 = Class.forName(paladinPath).getConstructor().newInstance();

		int int00 = 0;

		testGetterLogic(paladin00, "totalManaCrystals", int00);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableCurrentManaCrystalswarlock00int00InClassHero() throws Exception
	{
		Object warlock00 = Class.forName(warlockPath).getConstructor().newInstance();

		int int00 = 0;

		testGetterLogic(warlock00, "currentManaCrystals", int00);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableCurrentManaCrystalsmage00int00InClassHero() throws Exception
	{
		Object mage00 = Class.forName(magePath).getConstructor().newInstance();

		int int00 = 0;

		testGetterLogic(mage00, "currentManaCrystals", int00);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableFieldpriest00arrayInClassHero() throws Exception
	{
		Object priest00 = Class.forName(priestPath).getConstructor().newInstance();

		ArrayList<Object> array = new ArrayList<Object>();

		testGetterLogic(priest00, "field", array);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableFieldhunter00arrayInClassHero() throws Exception
	{
		Object hunter00 = Class.forName(hunterPath).getConstructor().newInstance();

		ArrayList<Object> array = new ArrayList<Object>();

		testGetterLogic(hunter00, "field", array);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableHandpaladin00arrayInClassHero() throws Exception
	{
		Object paladin00 = Class.forName(paladinPath).getConstructor().newInstance();

		ArrayList<Object> array = new ArrayList<Object>();

		testGetterLogic(paladin00, "hand", array);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableNametwistingNether00twistingNetherStringInClassCard() throws Exception
	{
		Object twistingNether00 = Class.forName(twistingNetherPath).getConstructor().newInstance();

		String twistingNetherString = "Twisting Nether";

		testGetterLogic(twistingNether00, "name", twistingNetherString);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableNamesealOfChampions00sealofChampionsStringInClassCard() throws Exception
	{
		Object sealOfChampions00 = Class.forName(sealOfChampionsPath).getConstructor().newInstance();

		String sealofChampionsString = "Seal of Champions";

		testGetterLogic(sealOfChampions00, "name", sealofChampionsString);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableNamemultiShot00multiShotStringInClassCard() throws Exception
	{
		Object multiShot00 = Class.forName(multiShotPath).getConstructor().newInstance();

		String multiShotString = "Multi-Shot";

		testGetterLogic(multiShot00, "name", multiShotString);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableNameholyNova00holyNovaStringInClassCard() throws Exception
	{
		Object holyNova00 = Class.forName(holyNovaPath).getConstructor().newInstance();

		String holyNovaString = "Holy Nova";

		testGetterLogic(holyNova00, "name", holyNovaString);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableNamecurseOfWeakness00curseofWeaknessStringInClassCard() throws Exception
	{
		Object curseOfWeakness00 = Class.forName(curseOfWeaknessPath).getConstructor().newInstance();

		String curseofWeaknessString = "Curse of Weakness";

		testGetterLogic(curseOfWeakness00, "name", curseofWeaknessString);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableManaCosttwistingNether00int08InClassCard() throws Exception
	{
		Object twistingNether00 = Class.forName(twistingNetherPath).getConstructor().newInstance();

		int int08 = 8;

		testGetterLogic(twistingNether00, "manaCost", int08);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableManaCostsealOfChampions00int03InClassCard() throws Exception
	{
		Object sealOfChampions00 = Class.forName(sealOfChampionsPath).getConstructor().newInstance();

		int int03 = 3;

		testGetterLogic(sealOfChampions00, "manaCost", int03);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableManaCostmultiShot00int04InClassCard() throws Exception
	{
		Object multiShot00 = Class.forName(multiShotPath).getConstructor().newInstance();

		int int04 = 4;

		testGetterLogic(multiShot00, "manaCost", int04);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableManaCostholyNova00int05InClassCard() throws Exception
	{
		Object holyNova00 = Class.forName(holyNovaPath).getConstructor().newInstance();

		int int05 = 5;

		testGetterLogic(holyNova00, "manaCost", int05);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableManaCostcurseOfWeakness00int02InClassCard() throws Exception
	{
		Object curseOfWeakness00 = Class.forName(curseOfWeaknessPath).getConstructor().newInstance();

		int int02 = 2;

		testGetterLogic(curseOfWeakness00, "manaCost", int02);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableRaritytwistingNether00rarity03InClassCard() throws Exception
	{
		Object twistingNether00 = Class.forName(twistingNetherPath).getConstructor().newInstance();

		Object rarity03 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "EPIC");

		testGetterLogic(twistingNether00, "rarity", rarity03);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableRaritysealOfChampions00rarity01InClassCard() throws Exception
	{
		Object sealOfChampions00 = Class.forName(sealOfChampionsPath).getConstructor().newInstance();

		Object rarity01 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "COMMON");

		testGetterLogic(sealOfChampions00, "rarity", rarity01);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableRaritymultiShot00rarity00InClassCard() throws Exception
	{
		Object multiShot00 = Class.forName(multiShotPath).getConstructor().newInstance();

		Object rarity00 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "BASIC");

		testGetterLogic(multiShot00, "rarity", rarity00);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableRarityholyNova00rarity00InClassCard() throws Exception
	{
		Object holyNova00 = Class.forName(holyNovaPath).getConstructor().newInstance();

		Object rarity00 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "BASIC");

		testGetterLogic(holyNova00, "rarity", rarity00);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableRaritycurseOfWeakness00rarity02InClassCard() throws Exception
	{
		Object curseOfWeakness00 = Class.forName(curseOfWeaknessPath).getConstructor().newInstance();

		Object rarity02 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "RARE");

		testGetterLogic(curseOfWeakness00, "rarity", rarity02);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableAttackminion00int83InClassMinion() throws Exception
	{
		String string00 = "d02";
		int int75 = 75;
		Object rarity04 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		int int83 = 83;
		int int41 = 41;
		boolean boolean01 = true;
		boolean boolean02 = false;
		Object minion00 = Class.forName(minionPath).getConstructor(String.class, int.class, Class.forName(rarityPath), int.class, int.class, boolean.class, boolean.class, boolean.class).newInstance(string00, int75, rarity04, int83, int41, boolean01, boolean01, boolean02);


		testGetterLogic(minion00, "attack", int83);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableTauntminion00boolean01InClassMinion() throws Exception
	{
		String string00 = "d02";
		int int75 = 75;
		Object rarity04 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		int int83 = 83;
		int int41 = 41;
		boolean boolean01 = true;
		boolean boolean02 = false;
		Object minion00 = Class.forName(minionPath).getConstructor(String.class, int.class, Class.forName(rarityPath), int.class, int.class, boolean.class, boolean.class, boolean.class).newInstance(string00, int75, rarity04, int83, int41, boolean01, boolean01, boolean02);


		testGetterLogic(minion00, "taunt", boolean01);
	}


	@Test(timeout = 1000)
	public void testGetterLogicForInstanceVariableAttackedminion00boolean02InClassMinion() throws Exception
	{
		String string00 = "d02";
		int int75 = 75;
		Object rarity04 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		int int83 = 83;
		int int41 = 41;
		boolean boolean01 = true;
		boolean boolean02 = false;
		Object minion00 = Class.forName(minionPath).getConstructor(String.class, int.class, Class.forName(rarityPath), int.class, int.class, boolean.class, boolean.class, boolean.class).newInstance(string00, int75, rarity04, int83, int41, boolean01, boolean01, boolean02);


		testGetterLogic(minion00, "attacked", boolean02);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableCurrentHPpaladin00int83InClassHero() throws Exception
	{
		Object paladin00 = Class.forName(paladinPath).getConstructor().newInstance();

		int int83 = 83;
		int int30 = 30;

		testSetterLogic(paladin00, "currentHP", int83, int30, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableHeroPowerUsedwarlock00boolean01InClassHero() throws Exception
	{
		Object warlock00 = Class.forName(warlockPath).getConstructor().newInstance();

		boolean boolean01 = true;

		testSetterLogic(warlock00, "heroPowerUsed", boolean01, boolean01, boolean.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableHeroPowerUsedmage00boolean01InClassHero() throws Exception
	{
		Object mage00 = Class.forName(magePath).getConstructor().newInstance();

		boolean boolean01 = true;

		testSetterLogic(mage00, "heroPowerUsed", boolean01, boolean01, boolean.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableTotalManaCrystalspriest00int92InClassHero() throws Exception
	{
		Object priest00 = Class.forName(priestPath).getConstructor().newInstance();

		int int92 = 92;
		int int10 = 10;

		testSetterLogic(priest00, "totalManaCrystals", int92, int10, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableTotalManaCrystalshunter00int38InClassHero() throws Exception
	{
		Object hunter00 = Class.forName(hunterPath).getConstructor().newInstance();

		int int38 = 38;
		int int10 = 10;

		testSetterLogic(hunter00, "totalManaCrystals", int38, int10, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableCurrentManaCrystalspaladin00int77InClassHero() throws Exception
	{
		Object paladin00 = Class.forName(paladinPath).getConstructor().newInstance();

		int int77 = 77;
		int int10 = 10;

		testSetterLogic(paladin00, "currentManaCrystals", int77, int10, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableNametwistingNether00rexxarStringInClassCard() throws Exception
	{
		Object twistingNether00 = Class.forName(twistingNetherPath).getConstructor().newInstance();

		String rexxarString = "Rexxar";

		testSetterLogic(twistingNether00, "name", rexxarString, rexxarString, String.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableNamesealOfChampions00divineSpiritStringInClassCard() throws Exception
	{
		Object sealOfChampions00 = Class.forName(sealOfChampionsPath).getConstructor().newInstance();

		String divineSpiritString = "Divine Spirit";

		testSetterLogic(sealOfChampions00, "name", divineSpiritString, divineSpiritString, String.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableNamemultiShot00siphonSoulStringInClassCard() throws Exception
	{
		Object multiShot00 = Class.forName(multiShotPath).getConstructor().newInstance();

		String siphonSoulString = "Siphon Soul";

		testSetterLogic(multiShot00, "name", siphonSoulString, siphonSoulString, String.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableNameholyNova00sealofChampionsStringInClassCard() throws Exception
	{
		Object holyNova00 = Class.forName(holyNovaPath).getConstructor().newInstance();

		String sealofChampionsString = "Seal of Champions";

		testSetterLogic(holyNova00, "name", sealofChampionsString, sealofChampionsString, String.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableNamecurseOfWeakness00guldanStringInClassCard() throws Exception
	{
		Object curseOfWeakness00 = Class.forName(curseOfWeaknessPath).getConstructor().newInstance();

		String guldanString = "Gul'dan";

		testSetterLogic(curseOfWeakness00, "name", guldanString, guldanString, String.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableManaCosttwistingNether00int35InClassCard() throws Exception
	{
		Object twistingNether00 = Class.forName(twistingNetherPath).getConstructor().newInstance();

		int int35 = 35;
		int int10 = 10;

		testSetterLogic(twistingNether00, "manaCost", int35, int10, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableManaCostsealOfChampions00int61InClassCard() throws Exception
	{
		Object sealOfChampions00 = Class.forName(sealOfChampionsPath).getConstructor().newInstance();

		int int61 = 61;
		int int10 = 10;

		testSetterLogic(sealOfChampions00, "manaCost", int61, int10, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableManaCostmultiShot00int92InClassCard() throws Exception
	{
		Object multiShot00 = Class.forName(multiShotPath).getConstructor().newInstance();

		int int92 = 92;
		int int10 = 10;

		testSetterLogic(multiShot00, "manaCost", int92, int10, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableManaCostholyNova00int75InClassCard() throws Exception
	{
		Object holyNova00 = Class.forName(holyNovaPath).getConstructor().newInstance();

		int int75 = 75;
		int int10 = 10;

		testSetterLogic(holyNova00, "manaCost", int75, int10, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableManaCostcurseOfWeakness00int33InClassCard() throws Exception
	{
		Object curseOfWeakness00 = Class.forName(curseOfWeaknessPath).getConstructor().newInstance();

		int int33 = 33;
		int int10 = 10;

		testSetterLogic(curseOfWeakness00, "manaCost", int33, int10, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableAttackminion00int53InClassMinion() throws Exception
	{
		String string00 = "d02";
		int int75 = 75;
		Object rarity04 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		int int83 = 83;
		int int41 = 41;
		boolean boolean01 = true;
		boolean boolean02 = false;
		Object minion00 = Class.forName(minionPath).getConstructor(String.class, int.class, Class.forName(rarityPath), int.class, int.class, boolean.class, boolean.class, boolean.class).newInstance(string00, int75, rarity04, int83, int41, boolean01, boolean01, boolean02);

		int int53 = 53;

		testSetterLogic(minion00, "attack", int53, int53, int.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableTauntminion00boolean01InClassMinion() throws Exception
	{
		String string00 = "d02";
		int int75 = 75;
		Object rarity04 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		int int83 = 83;
		int int41 = 41;
		boolean boolean01 = true;
		boolean boolean02 = false;
		Object minion00 = Class.forName(minionPath).getConstructor(String.class, int.class, Class.forName(rarityPath), int.class, int.class, boolean.class, boolean.class, boolean.class).newInstance(string00, int75, rarity04, int83, int41, boolean01, boolean01, boolean02);


		testSetterLogic(minion00, "taunt", boolean01, boolean01, boolean.class);
	}


	@Test(timeout = 1000)
	public void testSetterLogicForInstanceVariableAttackedminion00boolean02InClassMinion() throws Exception
	{
		String string00 = "d02";
		int int75 = 75;
		Object rarity04 = Enum.valueOf((Class<Enum>) Class.forName(rarityPath), "LEGENDARY");
		int int83 = 83;
		int int41 = 41;
		boolean boolean01 = true;
		boolean boolean02 = false;
		Object minion00 = Class.forName(minionPath).getConstructor(String.class, int.class, Class.forName(rarityPath), int.class, int.class, boolean.class, boolean.class, boolean.class).newInstance(string00, int75, rarity04, int83, int41, boolean01, boolean01, boolean02);


		testSetterLogic(minion00, "attacked", boolean02, boolean02, boolean.class);
	}


	@Test(timeout = 10000)
	public void testSetterForInstanceVariableExistsInClassHPAttackAndMaxAndCurrentAndTauntAndDivineAndSleepingAndAttackedIcehowl() throws Exception
	{
		testSetterMethodExistsInClass(Class.forName(icehowlPath), "setAttack", int.class, false);
		testSetterMethodExistsInClass(Class.forName(icehowlPath), "setMaxHP", int.class, false);
		testSetterMethodExistsInClass(Class.forName(icehowlPath), "setCurrentHP", int.class, false);
		testSetterMethodExistsInClass(Class.forName(icehowlPath), "setTaunt", boolean.class, false);
		testSetterMethodExistsInClass(Class.forName(icehowlPath), "setDivine", boolean.class, false);
		testSetterMethodExistsInClass(Class.forName(icehowlPath), "setSleeping", boolean.class, false);
		testSetterMethodExistsInClass(Class.forName(icehowlPath), "setAttacked", boolean.class, false);
	}


	@Test(timeout = 10000)
	public void testGetterForInstanceVariableExistsInClassHPAttackAndMaxAndCurrentAndTauntAndDivineAndSleepingAndAttackedIcehowl() throws Exception
	{
		testGetterMethodExistsInClass(Class.forName(icehowlPath), "getAttack", int.class, false);
		testGetterMethodExistsInClass(Class.forName(icehowlPath), "getMaxHP", int.class, false);
		testGetterMethodExistsInClass(Class.forName(icehowlPath), "getCurrentHP", int.class, false);
		testGetterMethodExistsInClass(Class.forName(icehowlPath), "isTaunt", boolean.class, false);
		testGetterMethodExistsInClass(Class.forName(icehowlPath), "isDivine", boolean.class, false);
		testGetterMethodExistsInClass(Class.forName(icehowlPath), "isSleeping", boolean.class, false);
		testGetterMethodExistsInClass(Class.forName(icehowlPath), "isAttacked", boolean.class, false);
	}

	@Test(timeout = 10000)
	public void testManaCrystalsForCurrentGame() throws Exception
	{
		Object currentHero = Class.forName(paladinPath).newInstance();
		
		Object opponentHero = Class.forName(magePath).newInstance();
		
		Object game = Class.forName(gamePath).getConstructor(Class.forName(heroPath),Class.forName(heroPath)).newInstance(currentHero, opponentHero);
		
		Field gameCurrentHero = Class.forName(gamePath).getDeclaredField("currentHero");
		gameCurrentHero.setAccessible(true);
		
		Field gameOpponent = Class.forName(gamePath).getDeclaredField("opponent");
		gameOpponent.setAccessible(true);
		
		currentHero = gameCurrentHero.get(game);
		
		opponentHero = gameOpponent.get(game);
		
		Field heroCurrentManaCrystals = Class.forName(heroPath).getDeclaredField("currentManaCrystals");
		heroCurrentManaCrystals.setAccessible(true);
		
		Field heroTotalManaCrystals = Class.forName(heroPath).getDeclaredField("totalManaCrystals");
		heroTotalManaCrystals.setAccessible(true);
		
		int currentHeroCurrentManaCrystals = heroCurrentManaCrystals.getInt(currentHero);
		int currentHeroTotalManaCrystals = heroTotalManaCrystals.getInt(currentHero);
		
		int opponentHeroCurrentManaCrystals = heroCurrentManaCrystals.getInt(opponentHero);
		int opponentHeroTotalManaCrystals = heroTotalManaCrystals.getInt(opponentHero);
		
		if(currentHeroCurrentManaCrystals!=1)
			fail("CurrentManaCrystals of the currentHero should be assigned to 1.");
		
		if(currentHeroTotalManaCrystals!=1)
			fail("TotalManaCrystals of the currentHero should be assigned to 1.");
		
		if(opponentHeroCurrentManaCrystals!=0)
			fail("CurrentManaCrystals of the opponentHero should be assigned to 0.");
		
		if(opponentHeroTotalManaCrystals!=0)
			fail("TotalManaCrystals of the opponentHero should be assigned to 0.");
	}
	
	
	// ############################################# Helper methods

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
			varName = methodName.substring(2).toLowerCase();
		else
			varName = methodName.substring(3).toLowerCase();
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

	private void testClassIsSubclass(Class subClass, Class superClass) 
	{
		assertEquals(subClass.getSimpleName() + " class should be a subclass from " + superClass.getSimpleName() + ".", superClass, subClass.getSuperclass());
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
