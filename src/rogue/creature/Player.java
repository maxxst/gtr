package rogue.creature;

import gtr.actor.fading.Blood;
import gtr.actor.fading.DeadBody;
import gtr.actor.item.Item;
import gtr.actor.item.weapon.Weapon;
import gtr.asciiscreen.AsciiScreen.LevelEnum;
import gtr.util.datatype.Location;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;

import rogue.creature.Creature;
import jade.core.World;
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
	private int hp = 20;
	private ArrayList<Item> items = new ArrayList<Item>();
	
	private static final ColoredChar standardFace = ColoredChar.create('@');

	public Player(Terminal term) {
		super(standardFace);
		this.term = term;
		fov = new RayCaster();
		weapon = new Weapon("Raketenwerfer", this); //TODO besser!
		
		// testweise
		items.add(new Weapon("Raketenwerfer", this));
		items.add(new Weapon("Pistole", this));
		items.add(new Weapon("Pistole", this));
	}

	public Terminal getTerm() {
		return term;
	}
	
	public ColoredChar getStandardFace() {
		return standardFace;
	}

	@Override
	public void act() {
		Direction dir;
		try {
			currentLevel.setCoordinate(new Coordinate(x(), y()));
			char key;
			key = term.getKey();
			
			if (gtr.keys.Keys.isUniversalKey(key))
				switch (key) {
				case KeyEvent.VK_ESCAPE:
					expire();
					break;
				}	
			else if (screenType.name().equals("StartScreen"))
				switch (key) {
				case 's':
//					nextLevel = new Location(LevelEnum.Town, new Coordinate(6, 114));
					nextLevel = new Location(LevelEnum.Town, new Coordinate(35, 109));
					break;
				}
			else if (screenType.name().equals("Level"))
				switch (key) {
				case ' ':
					System.out.println("Leertaste");
					key = term.getKey();
					dir = Direction.keyToDir(key);
					if (dir != null)
						attack(dir, weapon, 0.8F);
					break;
				case 'u':
				case 'i':
				case 'o':
				case 'j':
				case 'k':
				case 'l':
				case 'm':
				case '.':
					dir = Direction.keyToDir(key);
					if (dir != null)
						attack(dir, weapon);
					break;
					
				case '<':
					gtr.asciiscreen.AsciiScreen.showAsciiScreen(gtr.asciiscreen.other.Inventar.getInventarScreen(term, this),
							world(), term);
					
				default:
					dir = Direction.keyToDir(key);
					if (dir != null)
						move(dir);
					break;
				}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Collection<Coordinate> getViewField() {
		return fov.getViewField(world(), pos(), 5);
	}
	
	public void die(){
		hp--;
		world().addActor(new Blood(), x(), y());
		if(hp <= 0){
			expire();
		}
	}
	
	public String attackText(){
		return "Du greifst an mit: " + weapon.getName();
	}
	
	public String hitText(){
		return "Du triffst";
	}
	
	public String missText(){
		return "Du verfehlst";
	}

	public int getHp() {
		return hp;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public void setItems(Item item) {
		items.add(item);
	}
}