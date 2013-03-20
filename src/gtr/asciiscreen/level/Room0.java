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
		
		addActor(player, posDoor.x(), posDoor.y() - 1);

		if (!getMappingLevelActor().containsKey(currentLevel.getLevelEnum())) {
			ArrayList<String> messages = new ArrayList<String>();
			messages.add("Sie brachten dich, als es noch dunkel war.");
			messages.add("Du kannst von Glück reden, dass du überlebtest.");
			messages.add("Junge, merk dir eines:");
			messages.add("Xing Po wird sein Urteil fällen,");
			messages.add("ob du willst oder nicht!");

			Human person = new Human(ColoredChar.create('!', Color.red),
					messages);

			Katze k = new Katze();
			addActor(person, 22, 10);
			addActor(k, 11, 5);
		} else
			setActorsInWorld();
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