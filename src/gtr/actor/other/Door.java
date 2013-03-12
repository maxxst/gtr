package gtr.actor.other;

import gtr.asciiscreen.AsciiScreen.LevelEnum;
import gtr.util.datatype.Location;
import jade.core.Actor;
import jade.core.World;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Coordinate;

public class Door extends Actor {

	private Location doorDestination;

	public Door(Location doorDestination) {
		super(ColoredChar.create('['));
		this.doorDestination = doorDestination;
	}

	public Door(LevelEnum doorDestinationMap, Coordinate coordinate) {
		this(new Location(doorDestinationMap, coordinate));
	}

	public Door(LevelEnum doorDestinationMap, int destinationX, int destinationY) {
		this(new Location(doorDestinationMap, new Coordinate(destinationX,
				destinationY)));
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		if (player.x() == this.x() && player.y() == this.y()) {
			nextLevel = doorDestination;
			System.out.println(this.getDestination().toString());
		}
	}

	public Location getDestination() {
		return doorDestination;
	}

	/**
	 * Legt zu allen [-Dooractern zugehörige ]-Dooractor an, wenn ] rechts neben
	 * [ steht.
	 * 
	 * @param world
	 *            Level, in dem man sich befindet (und wo die [-Dooractor sind
	 *            und auch die neuen hinsollen).
	 */
	public static void completeDoors(World world) {
		Door[] doors = world.getActors(Door.class).toArray(new Door[0]);
		for (int i = 0; i < doors.length; i++) {
			Door door = doors[i];
			Coordinate coordinate = door.getDestination().getCoordinate();

			int x = door.x() + 1; // x-Koordinate des rechten Teils der Tür
			int y = door.y(); // y-Koordinate des rechten Teils der Tür
			world.addActor(new Door(door.getDestination().getLevelEnum(),
					coordinate), x, y);

			Actor door_rightPart = world.getActorAt(Door.class, x, y);
			door_rightPart.setFace(ColoredChar.create(']'));
		}
	}
}
