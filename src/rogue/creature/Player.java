package rogue.creature;

import gtr.actor.fading.Blood;
import gtr.actor.item.Ammo;
import gtr.actor.item.HealthPotion;
import gtr.actor.item.Item;
import gtr.actor.item.weapon.Weapon;
import gtr.asciiscreen.AsciiScreen.LevelEnum;
import gtr.util.datatype.Location;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;

import rogue.creature.Creature;
import jade.fov.RayCaster;
import jade.fov.ViewField;
import jade.ui.Camera;
import jade.ui.Terminal;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Coordinate;
import jade.util.datatype.Direction;

public class Player extends Creature implements Camera {
	private Terminal term;
	private ViewField fov;
	private Weapon weapon;
	private ArrayList<Item> items = new ArrayList<Item>();

	private static final ColoredChar standardFace = ColoredChar.create('@');

	public Player(Terminal term) {
		super(standardFace);
		setHp(20);
		this.term = term;
		fov = new RayCaster();

		// TODO besser! → jetzt besser? → NEIN!
		weapon = new Weapon("Pistole", this);
		weapon.setCount(6); // halbes Magazin und so
		addItem(weapon);

		// testweise
		Weapon weapon2 = new Weapon("Raketenwerfer", this);
		Weapon weapon3 = new Weapon("Schwert", this);
		Weapon weapon4 = new Weapon("Bogen des Robin Hood", this);
		addItem(weapon2);
		System.out.println("Anzahl: " + items.get(0).getCount());
		addItem(new HealthPotion(this));
		addItem(new HealthPotion(this));
		addItem(weapon3);
		addItem(weapon4);

		for (Item item : items)
			System.out.println(item.getName() + " " + item.getCount());
	}

	public Terminal getTerm() {
		return term;
	}

	public ColoredChar getStandardFace() {
		return standardFace;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	@Override
	public void act() {
		Direction dir;

		try {
			currentLevel.setCoordinate(new Coordinate(x(), y()));

			char key = 0; // NULL-Wert für char

			while (key == 0) {
				key = term.getKey();

				if (gtr.keys.Keys.isUniversalKey(key))
					switch (key) {
					case KeyEvent.VK_ESCAPE:
						System.exit(0);
						break;
					}
				else if (screenType.name().equals("StartScreen"))
					switch (key) {
					case 's':
						nextLevel = new Location(LevelEnum.Prologue,
								new Coordinate(1, 1));
						break;
					}
				else if (screenType.name().equals("Prologue"))
					switch (key) {
					case 's':
						// nextLevel = new Location(LevelEnum.Town, new
						// Coordinate(6, 114));
						nextLevel = new Location(LevelEnum.Town,
								new Coordinate(71, 112));
						break;
					}
				else if (screenType.name().equals("Level"))

					if (key == gtr.keys.Keys.getOpenInventoryKey()) {
						gtr.asciiscreen.other.Inventar
								.showInventory(term, this);
						world().changeAndRefreshScreenAndTick(term, false);
						key = 0;
					} else {
						switch (key) {
						case ' ':
							System.out.println("Leertaste");
							key = term.getKey();
							dir = Direction.keyToDir(key);
							if (dir != null)
								attack(dir, weapon, 0.8F);
							else {
								key = 0;
							}
							break;

						case 'h': // HEALs
							selectItem("Heiltrank");
							break;

						case 'f': // RELOAD
							reload();
							break;

						default:
							dir = Direction.keyToDir(key);
							if (dir != null)
								move(dir);
							else
								key = 0;
							break;
						}

					}
				else
					key = 0;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Collection<Coordinate> getViewField() {
		return fov.getViewField(world(), pos(), 5);
	}

	public void die() {
		loseHp(1);
		world().addActor(new Blood(), x(), y());
		if (getHp() <= 0) {

			expire();
		}
		// System.out.println(getHp());
	}

	public void use(Item item) {
		item.use();
		cleanItemList();
	}

	public void equip(Item item) {
		if (item instanceof Weapon) { // Wenn es eine Waffe ist
			Weapon weapon = (Weapon) item;
			if (!this.weapon.equals(weapon)) {
				//addItem(this.weapon);
				this.weapon = weapon;
			} else {
				//this.weapon.add(weapon);
			}
		} else if (item instanceof Ammo) { // Wenn es Munition ist
			Ammo ammo = (Ammo) item;
			if (weapon.getType().equals(ammo.getType())) {
				reload(ammo);
			}
		}

	}

	public String attackText() {
		return "Du greifst an mit: " + weapon.getName();
	}

	public String hitText() {
		return "Du triffst";
	}

	public String missText() {
		return "Du verfehlst";
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void addItem(Item item) {
		boolean added = false;
		for (Item itemInList : items) {
			if (item.equals(itemInList)) {
				itemInList.add(item);
				added = true;
				break;
			}
		}
		if (!added)
			items.add(item);

		System.out.println("Du erhälst: " + item.getName());
	}

	public void cleanItemList() {
		for (int i = 0; i < items.size(); i++) {
			System.out.print(items.get(i).getName());
			System.out.println(items.get(i).getCount());
			if (items.get(i).getCount() <= 0)
				items.remove(i);
		}
	}

	public void selectItem(String name) {
		for (Item itemInList : items) {
			System.out.println(name.equals(itemInList.getName()));
			if (name.equals(itemInList.getName())) {
				System.out.print(itemInList.getName());
				if (itemInList.isEquippable()) {
					System.out.println(" equip");
					equip(itemInList);
				} else {
					System.out.println(" use");
					use(itemInList);
				}
				break;
			}

		}
	}

	private void reload() {
		// System.out.println("Munition ("+weapon.getType()+")");
		selectItem("Munition (" + weapon.getType() + ")"); //TODO besser referenzieren
	}

	private void reload(Ammo ammo) {
		int n = ammo.getCount();
		if (n > 20)
			n = 20;

		ammo.use(n);
		weapon.add(n);

		if (ammo.getCount() <= 0)
			ammo.expire();
	}
}
