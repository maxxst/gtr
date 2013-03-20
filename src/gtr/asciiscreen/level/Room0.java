package gtr.asciiscreen.level;

import jade.core.Messenger;
import jade.ui.Terminal;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Coordinate;

import java.awt.Color;
import java.util.ArrayList;

import rogue.creature.Human;
import rogue.creature.Katze;
import rogue.creature.Player;

import gtr.actor.fix.Door;
import gtr.util.ReadFile;
import gtr.util.datatype.Location;

public class Room0 extends Level {

	private final static ArrayList<String> leveldesign = ReadFile
			.readScreenFile("res/maps/room0.txt");

	public Room0(Player player) {
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
				if (leveldesign.get(y).charAt(x) == exit) {
					posDoor = new Coordinate(x, y);
					break;
				}
		addActor(new Door(lastLevel, exit), posDoor);
		addActor(new Door(lastLevel, exit), posDoor.x() + 1, posDoor.y());

		
		ArrayList<String> messages = new ArrayList<String>();
		messages.add("Es gibt fünf Yakuza-Häuser auf dieser Insel.");
		messages.add("In jedem befindet sich ein Boss der Yakuza.");
		messages.add("Zuerst sei es Wo Fatt.");
		messages.add("Dann sei es Tao Bai Bai.");
		messages.add("Dann sei es Wing Zang.");
		messages.add("Dann sei es Muten Roschi.");
		messages.add("Und zum Schluss sei es Xing Po.");
		
		Human person2 = new Human(ColoredChar.create('!', Color.red), messages);
		Katze k = new Katze();
		addActor(person2, 10, 5);
		addActor(k, 11, 5);
		
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
