package gtr.monster;

import gtr.item.weapon.Weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import jade.util.Dice;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Direction;
import rogue.creature.Creature;


/**
 * Monster class creates a generic dumb monster
 * 
 * 
 * @author mxst
 */
public class Monster extends Creature {
	Random randomGenerator = new Random();
	

	public final static ArrayList<?> monsterList = gtr.util.ReadFile
			.readYamlArrayList("res/monsters/monsters.yml");
	public final static HashMap<?,?> monsterWeaponList = gtr.util.ReadFile
			.readYamlHashMap("res/monsters/weapons.yml");
	
	private String name;
	private Movement movement;
	private Weapon weapon;
	private String dropRareness;
	private String dropType;
	
	/**
	 * finds (randomized) monster with matching face
	 * @param face
	 * @return a HashMap to generate a monster
	 */
	private HashMap<?,?> findMonsterByChar(char face){
		ArrayList<HashMap<?, ?>> possible_monsters = new ArrayList<HashMap<?, ?>>();
		for (int i=0; i<monsterList.size();i++) {
			if (HashMap.class.cast(monsterList.get(i)).get("face").equals(face)){
				// adds to possible monsters
				possible_monsters.add(HashMap.class.cast(monsterList.get(i)));
			}
		}
		return possible_monsters.get(randomGenerator.nextInt(possible_monsters.size()));
	}
	
	public Monster(ColoredChar face) {
		super(face);
		// TODO Auto-generated constructor stub
	}
	
	// if its not a coloredChar
	public Monster(char face) {
		super(ColoredChar.create(face));
		HashMap<?,?> monster = findMonsterByChar(face);
		
		name = (String) monster.get("name");
		movement = new Movement((HashMap<?, ?>) monster.get("movement"));
		
		// gives one of the possible weapons to the mob
		ArrayList<?> possible_weapons = (ArrayList<?>) monster.get("weapons");
		weapon = new Weapon((HashMap<?,?>) monsterWeaponList
			.get((String) possible_weapons
					.get(randomGenerator.nextInt(possible_weapons.size()))
			)
		);
		// easy to access drop type
		dropType = weapon.getType();
		dropRareness = (String) monster.get("drops");
	}

	@Override
	
	//TODO Schadensberechnung
	public void act() {
		move(Dice.global.choose(Arrays.asList(Direction.values())));
	}

	public String getName() {
		return name;
	}

	public int getMove() {
		return movement.getMove(1);
	}
	
	//TODO rareness des drops einstellen
	public Weapon drop(){
		return new Weapon(dropType);
		
		//TODO not implemented in weapon.class
		// return new Weapon(dropType, dropRareness);
	}
}
