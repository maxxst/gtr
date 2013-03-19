package gtr.asciiscreen.level;

import jade.core.Messenger;
import jade.ui.Terminal;
import jade.util.datatype.Coordinate;

import java.util.ArrayList;
import java.util.Random;

import gtr.actor.fix.Door;
import gtr.asciiscreen.AsciiScreen;
import gtr.util.datatype.Location;
import rogue.creature.Player;

public class RandomRoom extends Level {

	private ArrayList<String> leveldesign;
	private Coordinate posDoor;
	
	private void createRoom(int width, int height) {
		// TODO Auto-generated method stub
		leveldesign = new ArrayList<String>();
		String wall = "";
		for (int i = 0; i < width; i++)
			wall += AsciiScreen.standardWall;
		leveldesign.add(wall);
		for (int y = 1; y < height - 1; y++) {
			String s = Character.toString(AsciiScreen.standardWall);
			for (int x = 1; x < width - 1; x++)
				s += ".";
			s += AsciiScreen.standardWall;
			leveldesign.add(s);
		}
		wall = "";
		for (int i = 0; i < width / 2 - 1; i++)
			wall += AsciiScreen.standardWall;
		posDoor = new Coordinate(wall.length(), height - 1);
		wall += "▒▒";
		while (wall.length() < width)
			wall += AsciiScreen.standardWall;
		leveldesign.add(wall);
	}
	
	public RandomRoom(Player player, int width, int height) {
		super(width, height);
		System.out.println("Größe des Raumes: w: " + width + " h: " + height);
		updateLevelVariables();
		
		Messenger.player = player;
		
		createRoom(width, height);
		createAsciiScreen(leveldesign, this, player.getTerm());
		addActor(player, width / 2, height - 2);
		System.out.println(lastLevel.toString());
		addActor(new Door(lastLevel, '▒'), posDoor);
		addActor(new Door(lastLevel, '▒'), posDoor.x() + 1, posDoor.y());
		
	}

	public RandomRoom(Player player) {
		// Erzeugt Raum mit zufälliger Größe.
		// Mindestgröße: 15 * 15, Höchstgröße: 49 * 49
		this(player, new Random().nextInt(35) + 15, new Random().nextInt(35) + 15);
	}

	@Override
	public Location inLevel() {
		Terminal term = player.getTerm();

		while (!player.expired()) {

			if (nextLevel != null) {
				this.removeActor(player);
				break; // Verlassen der while-Schleife
			}
			changeAndRefreshScreenAndTick(term);
		}
		return nextLevel;
	}
}
