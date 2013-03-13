package test.gtr.weapon;

import static org.junit.Assert.*;

import gtr.item.weapon.Weapon;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class ReadWeaponFromFileTest {
	public final static ArrayList<?> weaponList = gtr.util.ReadFile
			.readYamlArrayList("specs/yaml/weapons.yml");
	
	public final static int speedDefault = -1;
	public final static boolean areaDefault = false;
	public final static int breakthroughDefault = 0;
	public final static boolean only_rareDefault = false;
	public final static boolean boss_dropDefault = false;
	
	private Weapon weapon;
	
	@Test
	public void testFirstWeaponFromSpec() {
		// create weapon from spec
		weapon = new Weapon(HashMap.class.cast(weaponList.get(0)));
		
		assertEquals("first Weapon is Sword", "Schwert", weapon.getName());
		assertEquals("Swords type is melee", "melee", weapon.getType());
		assertEquals("Swords range from 1", 1, weapon.getRange().getFrom());
		assertEquals("Swords range to 1", 1, weapon.getRange().getTo());
		
		//defaults
		assertEquals(speedDefault, weapon.getSpeed());
		assertEquals(areaDefault, weapon.isArea());
		assertEquals(breakthroughDefault, weapon.getBreakthrough());
		assertEquals(only_rareDefault, weapon.isOnly_rare());
		assertEquals(boss_dropDefault, weapon.isBoss_drop());
	}

}
