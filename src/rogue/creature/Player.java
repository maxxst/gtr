package rogue.creature;

import java.util.Collection;
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
	
	private static final ColoredChar standardFace = ColoredChar.create('@');

	public Player(Terminal term) {
		super(standardFace);
		this.term = term;
		fov = new RayCaster();
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
			
			if (currentLevel.equals("StartLevel"))
				switch (key) {
				case 's':
					nextLevel = "Dungeon";
					break;
				}
			else if (currentLevel.equals("Dungeon"))
				switch (key) {
				case 'q':
					expire();
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

	@Override
	public Collection<Coordinate> getViewField() {
		return fov.getViewField(world(), pos(), 5);
	}
}
