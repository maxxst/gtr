package gtr.actor.other;

import gtr.asciiscreen.AsciiScreen.LevelEnum;
import gtr.util.datatype.Location;
import jade.core.Actor;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Coordinate;

public class Door extends Actor {

	private LevelEnum destination;
	private Coordinate coordinate;
	
	public Door(LevelEnum destination, int destinationX, int destinationY) {
		this(destination, new Coordinate(destinationX, destinationY));
	}
	
	public Door(LevelEnum destination, Coordinate coordinate) {
		super(ColoredChar.create('['));
		this.destination = destination;
		this.coordinate = coordinate;
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		if (player.x() == this.x() && player.y() == this.y()) {
			nextLevel = new Location(destination, coordinate);
		}
	}

}
