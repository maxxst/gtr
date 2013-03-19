package gtr.asciiscreen.level;

import jade.core.Messenger;
import jade.ui.Terminal;
import jade.util.datatype.Coordinate;

import java.util.ArrayList;

import rogue.creature.Player;

import gtr.actor.fix.Door;
import gtr.util.ReadFile;
import gtr.util.datatype.Location;

public class Room1 extends Level {

	private final static ArrayList<String> leveldesign = ReadFile
			.readScreenFile("res/maps/room1.txt");

	public Room1(Player player) {
		super(gtr.asciiscreen.AsciiScreen.getWidth(leveldesign),
				gtr.asciiscreen.AsciiScreen.getHeight(leveldesign));

		int width = gtr.asciiscreen.AsciiScreen.getWidth(leveldesign);
		int height = gtr.asciiscreen.AsciiScreen.getHeight(leveldesign);

		updateLevelVariables();
		Messenger.player = player;
		createAsciiScreen(leveldesign, this, player.getTerm());

		// finde ▒ und erstelle Dooractor
		Coordinate posDoor = null;
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				if (leveldesign.get(y).charAt(x) == '▒') {
					posDoor = new Coordinate(x, y);
					break;
				}
		addActor(new Door(lastLevel, '▒'), posDoor);
		addActor(new Door(lastLevel, '▒'), posDoor.x() + 1, posDoor.y());

		addActor(player, posDoor.x(), posDoor.y() - 1);

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
