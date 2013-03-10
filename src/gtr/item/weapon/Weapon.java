package gtr.item.weapon;

import java.util.ArrayList;
import java.util.HashMap;

public class Weapon {

	public final static ArrayList<?> weaponList = gtr.util.ReadFile
			.readYamlFile("res/weapons/weapons.yml");

	public String name;
	public Range range;
	public int speed;
	public boolean area;
	public int breakthrough;
	public boolean only_rare;

	public class Range {

		public int from;
		public int to;

		public Range(HashMap<?, ?> range) {
			from = Integer.parseInt((String) range.get("from"));
			to = Integer.parseInt((String) range.get("to"));
		}
	}

	public Weapon(String weapon) {

		for (int i = 0; i < weaponList.size(); i++)
			if (HashMap.class.cast(weaponList.get(i)).get("name")
					.equals(weapon)) {
				name = weapon;
				speed = Integer.parseInt((String) HashMap.class.cast(
						weaponList.get(i)).get("speed"));
				area = Boolean.parseBoolean((String) HashMap.class.cast(
						weaponList.get(i)).get("area"));
				breakthrough = Integer.parseInt((String) HashMap.class.cast(
						weaponList.get(i)).get("breakthrough"));
				only_rare = Boolean.parseBoolean((String) HashMap.class.cast(
						weaponList.get(i)).get("only_rare"));
				range = new Range((HashMap<?, ?>) HashMap.class.cast(
						weaponList.get(i)).get("range"));

				break;
			}
	}
}
