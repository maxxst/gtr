package gtr.item.weapon;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Klasse für die Waffen
 * 
 * @author anti
 * 
 */
public class Weapon {

	public final static ArrayList<?> weaponList = gtr.util.ReadFile
			.readYamlFile("res/weapons/weapons.yml");

	public String name;
	public Range range;
	public int speed;
	public boolean area;
	public int breakthrough;
	public boolean only_rare;

	/**
	 * Innere Klasse für die Reichweite der Waffen
	 * 
	 * @author anti
	 * 
	 */
	public class Range {

		public int from;
		public int to;

		public Range(HashMap<?, ?> range) {
			from = Integer.parseInt((String) range.get("from"));
			to = Integer.parseInt((String) range.get("to"));
		}
	}

	/**
	 * Erstellt eine Waffe.
	 * 
	 * @param weapon
	 *            Name der Waffe, die erstellt werden soll
	 */
	public Weapon(String weapon) {

		for (int i = 0; i < weaponList.size(); i++)
			if (HashMap.class.cast(weaponList.get(i)).get("name")
					.equals(weapon)) {
				HashMap<?, ?> hashMap = HashMap.class.cast(weaponList.get(i));

				name = weapon;
				speed = Integer.parseInt((String) hashMap.get("speed"));
				area = Boolean.parseBoolean((String) hashMap.get("area"));
				breakthrough = Integer.parseInt((String) hashMap
						.get("breakthrough"));
				only_rare = Boolean.parseBoolean((String) hashMap
						.get("only_rare"));
				range = new Range((HashMap<?, ?>) hashMap.get("range"));

				break;
			}
	}
}
