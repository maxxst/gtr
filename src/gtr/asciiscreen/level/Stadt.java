package gtr.asciiscreen.level;

import gtr.actor.other.Door;
import gtr.util.ReadFile;
import gtr.util.datatype.Location;

import jade.core.Messenger;
import jade.ui.Terminal;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Coordinate;

import java.awt.Color;
import java.util.ArrayList;

import rogue.creature.Monster;
import rogue.creature.Player;

public class Stadt extends Level {

	private final static ArrayList<String> leveldesign = ReadFile
			.readScreenFile("res/maps/main_map.txt");
	Monster monster;

	public Stadt(Player player) {
		super(gtr.asciiscreen.AsciiScreen.getWidth(leveldesign),
				gtr.asciiscreen.AsciiScreen.getHeight(leveldesign));

		Messenger.player = player;
		
		createAsciiScreen(leveldesign, this, player.getTerm());

		// int x = TermPanel.DEFAULT_COLS / 2;
		// int y = TermPanel.DEFAULT_ROWS / 2;
		if (nextLevel.getCoordinate().equals(new Coordinate(-1, -1)))
			addActor(player);
		else
			addActor(player, nextLevel.getCoordinate());

		monster = new Monster(ColoredChar.create('D', Color.red));
		addActor(monster);

		Door door = new Door(LevelEnum.Stadt, 150, 106);
		addActor(door, 35, 92);

		updateLevelVariables();
	}

	@Override
	public Location inLevel() {
		Terminal term = player.getTerm();

		while (!player.expired()) {

			// if (player.x() ==
			// monster.x() &&
			// player.y() ==
			// monster.y())
			// nextLevel = LevelEnum.StartScreen;
			//
			if (nextLevel != null) {
				this.removeActor(player);
				break; // Verlassen der while-Schleife
			}

			changeAndRefreshScreenAndTick(term);
		}

		return nextLevel;
	}

}
