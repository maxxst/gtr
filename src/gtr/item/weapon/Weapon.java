package gtr.item.weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Klasse für die Waffen
 * 
 * @author anti, mxst
 * 
 */
public class Weapon {

	public final static ArrayList<?> weaponList = gtr.util.ReadFile
			.readYamlArrayList("res/weapons/weapons.yml");

	private String name;
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
	 * finds a matching weapon
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
	 * Erstellt eine Waffe.
	 * 
	 * @param string
	 *            Name oder Typ der Waffe, die erstellt werden soll. Existieren
	 *            mehrere mögliche Waffen, wird zufällig ausgewählt, welche
	 *            erstellt wird.
	 */
	public Weapon(String string) {

		// get a weapon-hashmap
		this(matching_weapon(string));
	}

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
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Range getRange() {
		return range;
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
}
