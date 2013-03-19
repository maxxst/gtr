	package gtr.actor.item.weapon;

import gtr.actor.item.Ammo;
import gtr.actor.item.Item;
import gtr.actor.moving.Projectile;
import gtr.util.DropType;
import gtr.util.WeaponType;
import jade.util.datatype.Coordinate;
import jade.util.datatype.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import rogue.creature.Creature;

/**
 * Weapons that can be equipped by monsters or
 * the player and also can be droped or just lay around
 * 
 * @author anti
 * @author maxx
 * 
 */
public class Weapon extends Item {
	
	public final static ArrayList<?> weaponList = gtr.util.ReadFile
			.readYamlArrayList("res/weapons/weapons.yml");

	private String type;
	private Range range;
	private int speed;
	private boolean area;
	private int breakthrough;
	private boolean only_rare;
	private boolean boss_drop;
	private float dmg;

	/**
	 * Innere Klasse für die Reichweite der Waffen
	 * 
	 * @author anti
	 * 
	 */
	public class Range {

		private int from;
		private int to;

		public Range(HashMap<?, ?> range) {
			from = Integer.parseInt((String) range.get("from"));
			to = Integer.parseInt((String) range.get("to"));
		}

		public Range(Integer f, Integer t) {
			from = f;
			to = t;
		}

		public int getFrom() {
			return from;
		}

		public int getTo() {
			return to;
		}
	}

	/**
	 * finds a matching weapon from "res/weapons/weapons.yml"
	 * 
	 * @param string Name oder Typ der Waffe
	 * @return a Weapon to generate
	 */
	private static HashMap<?, ?> matching_weapon(String string) {
		ArrayList<HashMap<?, ?>> possible_weapons = new ArrayList<HashMap<?, ?>>();
		for (int i = 0; i < weaponList.size(); i++) {
			if ((HashMap.class.cast(weaponList.get(i)).get("type")
					.equals(string))
					|| (HashMap.class.cast(weaponList.get(i)).get("name")
							.equals(string))) {

				// adds to possible weapons
				possible_weapons.add(HashMap.class.cast(weaponList.get(i)));
			}
		}

		Random randomGenerator = new Random();
		return possible_weapons.get(randomGenerator.nextInt(possible_weapons
				.size()));

	}

	/**
	 * Creates a weapon an binds it to a creature
	 * 
	 * @param string
	 * 		name or type of a weapon from "res/weapons/weapons.yml"
	 * @param holder
	 *   	A {@code Creature} that then is able to use this Weapon
	 */
	public Weapon(String string, Creature holder) {
		// get a weapon-hashmap
		this(matching_weapon(string), holder);
	}
	
	public Weapon(String string) {
		// get a weapon-hashmap
		this(matching_weapon(string));
	}
	
	
	/**
	 * Creates a weapon an binds it to a creature
	 * 
	 * @param hashMap
	 * 		a hashMap describing the weapon
	 * @param holder
	 *   	A {@code Creature} that then is able to use this Weapon
	 */
	public Weapon(HashMap<?, ?> hashMap, Creature holder){
		this(hashMap);
		this.attach(holder);
	}
	
	/**
	 * Creates a weapon but doesn't binds it to a creature
	 * 
	 * @param hashMap
	 * 		a hashMap describing the weapon
	 */ 
	public Weapon(HashMap<?, ?> hashMap) {
		// these attributes must be set
		setName((String) hashMap.get("name"));
		setType((String) hashMap.get("type"));
		setRange(new Range((HashMap<?, ?>) hashMap.get("range")));

		// dmg multiplicator default: 1.0

		// default ist -1
		setSpeed(hashMap.containsKey("speed") ? Integer
				.parseInt((String) hashMap.get("speed")) : -1);

		// default false - no area damage
		setArea(hashMap.containsKey("area") ? Boolean
				.parseBoolean((String) hashMap.get("area")) : false);

		// default breakthrough 0
		setBreakthrough(hashMap.containsKey("breakthrough") ? Integer
				.parseInt((String) hashMap.get("breakthrough")) : 0);

		// only_rare default false
		setOnly_rare(hashMap.containsKey("only_rare") ? Boolean
				.parseBoolean((String) hashMap.get("only_rare")) : false);

		// boss_drop default false
		setBoss_drop(hashMap.containsKey("boss_drop") ? Boolean
				.parseBoolean((String) hashMap.get("boss_drop")) : false);

		dmg = (float) (hashMap.containsKey("dmg") ? Float.parseFloat((String) hashMap
				.get("dmg")) : 1.0F);
		
		//sets random Count at creation
		Random randomGenerator = new Random();
		
		setCount(randomGenerator.nextInt(79) + 21);
		//System.out.println(type == melee);
		if (type.equals(WeaponType.melee.toString()))
			setCount(1);
	}
	
	public Weapon(DropType dropType) {
		this(WeaponType.getRandom().toString());
		// TODO Auto-generated constructor stub
	}

	/**
	 * Merges two equal weapons by adding up the Ammo 
	 * @param weapon
	 */
	public void add(Weapon weapon){
		setCount(getCount() + weapon.getCount());
		weapon.expire();
	}
	
	public void add(Ammo ammo){
		if (ammo.getType() == type){
			setCount(getCount() + ammo.getAmmo());
			ammo.expire();
		}
	}
	
	
	/**
	 * allows a creature to use a weapon (attack an enemy)
	 * 
	 * @param direction Direction in which the weapon is used/fired 
	 * @param hitProbability Chance to hit an enemy in range 
	 */
	public void use(Direction dir, float hitProb){
		if (holder() != null) {
			if(getCount() > 0 || (type.equals(WeaponType.melee.toString()))){
				Random randomGenerator = new Random();
		    	System.out.println(((Creature) holder()).attackText());
		    	if (!isProjectile()){
		    		for(int i=getRangeFrom(); i<=getRangeTo();i++){
			    		Coordinate coord = new Coordinate( (holder().x()+dir.dx()*i), (holder().y()+dir.dy()*i) );
			    		Creature enemy = null;
			    		try {
			    			enemy = world().getActorsAt(Creature.class, coord).toArray(new Creature[0])[0];
			    		}
			    		catch (ArrayIndexOutOfBoundsException e) {
			    		}
			    		if( enemy != null){
			    			if(randomGenerator.nextFloat() < hitProb){
								System.out.println(((Creature) holder()).hitText()); //TODO Schaden zufuegen!
								enemy.getDamage();
							} else {
								i = range.getTo() + 1;
								System.out.println(((Creature) holder()).missText()); //TODO Schaden zufuegen!
							}
						} else {
							System.out.println(((Creature) holder()).missText());
						}
			    	}
		    	} else {
		    		world().addActor(new Projectile(dir, this), pos());
		    	}
		    	if (!type.equals(WeaponType.melee.toString()))
		    		super.use();
		    	else
		    		setCount(1);
			} else
				System.out.println(getName() + " is empty!");
		} else {
			System.out.println("no weapon equipped");
		}
	 
	}

	public Range getRange() {
		return range;
	}
	
	public int getRangeFrom(){
		return range.getFrom();
	}

	public int getRangeTo(){
		return range.getTo();
	}
	
	public void setRange(Range range) {
		this.range = range;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isArea() {
		return area;
	}

	public void setArea(boolean area) {
		this.area = area;
	}

	public int getBreakthrough() {
		return breakthrough;
	}

	public void setBreakthrough(int breakthrough) {
		this.breakthrough = breakthrough;
	}

	public boolean isOnly_rare() {
		return only_rare;
	}

	public void setOnly_rare(boolean only_rare) {
		this.only_rare = only_rare;
	}

	public boolean isBoss_drop() {
		return boss_drop;
	}

	public void setBoss_drop(boolean boss_drop) {
		this.boss_drop = boss_drop;
	}
	
	/**
	 * getterMethod to find out if the Weapon is a "Projetile-Weapon"
	 * @return True if it's a Projectile-Weapon
	 */
	public boolean isProjectile(){
		return (getSpeed() != -1);		
	}
	
	public boolean isEquippable() {
		return true;
	}
	
	public boolean equals(Weapon weapon){
		return this.getName() == weapon.getName();
	}
}
