package rogue.creature;

import gtr.actor.fading.Blood;
import gtr.actor.item.Ammo;
import gtr.actor.item.Item;
import gtr.actor.item.Weapon;
import gtr.asciiscreen.AsciiScreen.LevelEnum;
import gtr.keys.Key;
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
		
		addItem(new Weapon("Raketenwerfer", this));
		addItem(new Weapon("Raketenwerfer", this));

		// testweise
		/*
		Weapon weapon2 = new Weapon("Raketenwerfer", this);
		Weapon weapon3 = new Weapon("Schwert", this);
		Weapon weapon4 = new Weapon("Bogen des Robin Hood", this);
		addItem(new Weapon("M4A1", this));
		addItem(weapon2);
		/*
		 * System.out.println("Anzahl: " + items.get(0).getCount()); addItem(new
		 * HealthPotion(this)); addItem(new HealthPotion(this));
		 * addItem(weapon3); addItem(weapon4);
		 */

		System.out.println("Items:");
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

				if (gtr.keys.Key.isUniversalKey(key))
					switch (key) {
					case KeyEvent.VK_ESCAPE:
						System.exit(0);
						break;
					}
				else if (screenType.name().equals("StartScreen"))
					switch (key) {
					case Key.skipKey:
						nextLevel = new Location(LevelEnum.Prologue,
								new Coordinate(1, 1));
						break;
					}
				else if (screenType.name().equals("Prologue"))
					switch (key) {
					case Key.skipKey:
						nextLevel = new Location(LevelEnum.Town,
								new Coordinate(4, 115));
						// nextLevel = new Location(LevelEnum.Town,
						// new Coordinate(63, 118));
						break;
					}
				else if (screenType.name().equals("Level"))

					switch (key) {
					case Key.openInventoryKey:
						gtr.asciiscreen.other.Inventar
								.showInventory(term, this);
						world().changeAndRefreshScreenAndTick(term, false);
						key = 0;
						break;
					case ' ':
						key = term.getKey();
						dir = Direction.keyToDir(key);
						if (dir != null)
							attack(dir, weapon, 0.8F);
						else {
							key = 0;
						}
						break;

					case Key.healPotionKey: // HEALs
						selectItem("Heiltrank");
						break;

					case Key.reloadKey: // RELOAD
						reload();
						break;
					case Key.speakToPerson: // Person anquatschen
						key = term.getKey();
						dir = Direction.keyToDir(key);
						if (dir != null) {
							speakTo(dir);

						} else {
							key = 0;
						}
						break;
					case Key.help:
						gtr.asciiscreen.other.Help.showHelp(term, this);
						world().changeAndRefreshScreenAndTick(term, false);
						key = 0;
						break;
					default:
						dir = Direction.keyToDir(key);
						if (dir != null)
//							move(dir);
							move(dir, this);
						else
							key = 0;
						break;
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
				// addItem(this.weapon);
				this.weapon = weapon;
				eventText("Du hast " + weapon.getName() + " angelegt.");
			} else {
				// this.weapon.add(weapon);
			}
		} else if (item instanceof Ammo) { // Wenn es Munition ist
			Ammo ammo = (Ammo) item;
			if (weapon.getType().equals(ammo.getType())) {
				reload(ammo);
			}
		}

	}

	public String attackText() {
		return "Du greifst mit " + weapon.getName() + " an";
	}

	public String hitText() {
		return " .. und .. triffst";
	}

	public String missText() {
		return " .. und .. verfehlst";
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void addItem(Item item) {
		// world().eventText("Du erhälst: " + item.getName());
		boolean added = false;
		for (Item itemInList : items) {
			if (item.equals(itemInList)) {
				added = true;
				eventText("Vorrat an " + item.getName() + " erhöht");
				itemInList.add(item);
				break;
			}
		}
		if (!added) {
			eventText("Du erhälst: " + item.getName());
			items.add(item);
		}
	}

	public void cleanItemList() {
		for (int i = 0; i < items.size(); i++) {
			// ! System.out.print(items.get(i).getName());
			// ! System.out.println(items.get(i).getCount());
			if (items.get(i).getCount() <= 0 && !items.get(i).isEquippable())
				items.remove(i);
		}
	}

	public void selectItem(String name) {
		Boolean there = false;
		for (Item itemInList : items) {
			// ! System.out.println(name.equals(itemInList.getName()));
			if (name.equals(itemInList.getName())) {
				there = true;
				// ! System.out.print(itemInList.getName());
				if (itemInList.isEquippable()) {
					// System.out.println(" equip");
					equip(itemInList);
				} else {
					// ! System.out.println(" use");
					use(itemInList);
				}
				break;
			}
		}
		if (!there)
			eventText(name + " nicht im Inventar.");
	}

	private void reload() {
		// System.out.println("Munition ("+weapon.getType()+")");
		selectItem("Munition (" + weapon.getType() + ")"); // TODO besser
															// referenzieren
	}

	private void reload(Ammo ammo) {
		eventText("Du lädst nach ..");
		int n = ammo.getCount();
		if (n > 20)
			n = 20;

		ammo.use(n);
		weapon.add(n);

		if (ammo.getCount() <= 0)
			ammo.expire();
	}
}
