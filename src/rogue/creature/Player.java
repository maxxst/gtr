package rogue.creature;

import gtr.actor.other.Blood;
import gtr.actor.other.DeadBody;
import gtr.asciiscreen.AsciiScreen.LevelEnum;
import gtr.item.weapon.Weapon;
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
		weapon = new Weapon("Pistole"); //TODO besser!
	}

	public Terminal getTerm() {
		return term;
	}
	
	public ColoredChar getStandardFace() {
		return standardFace;
	}

	@Override
	public void act() {
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
					nextLevel = new Location(LevelEnum.Stadt, new Coordinate(35, 93));
					break;
				}
			else if (screenType.name().equals("Level"))
				switch (key) {
				case ' ':
					System.out.println("Leertaste");
					key = term.getKey();
					Direction shootDir = Direction.keyToDir(key);
					if (shootDir != null)
						attack(shootDir, weapon.getRange(), calcDamage());
					break;
				default:
					Direction dir = Direction.keyToDir(key);
					if (dir != null)
						move(dir);
					break;
				}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private float calcDamage() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Collection<Coordinate> getViewField() {
		return fov.getViewField(world(), pos(), 5);
	}
	
	public void die(){
		world.addActor(new Blood(), x(), y());
	}
}