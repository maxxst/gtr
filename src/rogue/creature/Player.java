package rogue.creature;

import gtr.actor.item.weapon.Weapon;
import gtr.actor.other.Blood;
import gtr.actor.other.DeadBody;
import gtr.asciiscreen.AsciiScreen.LevelEnum;
import gtr.util.datatype.Location;

import java.awt.event.KeyEvent;
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
	
	private static final ColoredChar standardFace = ColoredChar.create('@');

	public Player(Terminal term) {
		super(standardFace);
		this.term = term;
		fov = new RayCaster();
		weapon = new Weapon("Raketenwerfer", this); //TODO besser!
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
					nextLevel = new Location(LevelEnum.Town, new Coordinate(35, 120));
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
		world.addActor(new Blood(), x(), y());
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
}