package rogue.creature;

import gtr.actor.item.Item;
import gtr.actor.item.weapon.Weapon;
import gtr.actor.item.weapon.Weapon.Range;
import gtr.creature.Movement;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import jade.util.Dice;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Direction;

/**
 * Monster class creates a generic dumb monster
 * 
 * 
 * @author mxst
 */
public class Monster extends Creature {
	

	public final static ArrayList<?> monsterList = gtr.util.ReadFile
			.readYamlArrayList("res/monsters/monsters.yml");
	public final static HashMap<?,?> monsterWeaponList = gtr.util.ReadFile
			.readYamlHashMap("res/monsters/weapons.yml");
	
	private String name;
	private Movement movement;
	protected Weapon weapon;
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
			if ( ((String) HashMap.class.cast(monsterList.get(i)).get("face")).charAt(0) == face ){
				// adds to possible monsters
				possible_monsters.add(HashMap.class.cast(monsterList.get(i)));
			}
		}
		Random randomGenerator = new Random();
		return possible_monsters.get(randomGenerator.nextInt(possible_monsters.size()));
	}
	
	public Monster(char face) {
		this(ColoredChar.create(face));
		// TODO Auto-generated constructor stub
	}
	
	public Monster() {
		this(ColoredChar.create('M', new Color(0,200,0)));
		// TODO Auto-generated constructor stub
	}
	
	// if its not a coloredChar
	public Monster(ColoredChar face) {
		super(face);
		HashMap<?,?> monster = findMonsterByChar(face.ch());
		
		name = (String) monster.get("name");
		movement = new Movement((HashMap<?, ?>) monster.get("movement"));
		
		
		Random randomGenerator = new Random();
		// gives one of the possible weapons to the mob
		ArrayList<?> possible_weapons = (ArrayList<?>) monster.get("weapons"); //liste mit waffenbezeichnungen
		weapon = new Weapon((HashMap<?,?>) monsterWeaponList
			.get((String) possible_weapons
					.get(randomGenerator.nextInt(possible_weapons.size()))
			), this
		);
		// easy to access drop type
		dropType = weapon.getType();
		dropRareness = (String) monster.get("drops");
	}

	@Override
	
	//TODO Schadensberechnung
	public void act() {
		move(Dice.global.choose(Arrays.asList(Direction.values())));
		Direction dir = findPlayerInRange();
		if(dir != null){ //angreifbar
			attack(dir, weapon);
		}
		
	}
	
	public Direction findPlayerInRange(){
		return findClassInRange(Player.class);
	}
	
	public Direction findClassInRange(Class<? extends Creature> cls){
		Range range = weapon.getRange();
		for(int i=range.getFrom();i<=range.getTo();i++){
			for(Direction dir: Direction.values()){
				try {
					if(world().getActorAt(cls, x()+dir.dx()*i,y()+dir.dy()*i) != null){
						return dir;
					}
				} catch(ArrayIndexOutOfBoundsException e){
					
				}
				
			}
		}
		return null;
		
	}

	public String getName() {
		return name;
	}

	public int getMove() {
		return movement.getMove(1);
	}
	
	public String attackText(){
		return name + " greift an mit: " + weapon.getName();
	}
	
	public String hitText(){
		return name + " trifft";
	}
	
	public String missText(){
		return name + " verfehlt";
	}
	/*
	 * 8   |1|   2
	 * ____|_|____
	 * 7___|9|___3
	 *     | |
	 * 6   |5|   4
	 */
	public int getQuadrant()
	{	 int i = 0;
		 int x = x()-player.x();
		 int y = y()-player.y();
		 if (x==0){if (y>0){i=1;}if(y==0){i=9;}if(y<0){i=5;}}
		 if (x<0){if(y>0){i=8;}if(y==0){i=7;}if(y<0){i=6;}}
		 if (x>0){if(y>0){i=2;}if(y==0){i=3;}if(y<0){i=4;}}
		return i;
		 
	}
}
