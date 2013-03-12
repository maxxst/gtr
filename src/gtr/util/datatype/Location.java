package gtr.util.datatype;

import jade.util.datatype.Coordinate;
import gtr.asciiscreen.AsciiScreen.LevelEnum;

public class Location {

	private LevelEnum levelEnum;
	private Coordinate coordinate;
	
	public Location(LevelEnum levelEnum, Coordinate coordinate) {
		this.setLevelEnum(levelEnum);
		this.setCoordinate(coordinate);
	}
	
	public Location(LevelEnum levelEnum) {
		this(levelEnum, new Coordinate(-1, -1));
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public LevelEnum getLevelEnum() {
		return levelEnum;
	}

	public void setLevelEnum(LevelEnum levelEnum) {
		this.levelEnum = levelEnum;
	}
}
