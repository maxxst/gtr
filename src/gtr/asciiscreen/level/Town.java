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

import rogue.creature.*;

public class Town extends Level {

	private final static ArrayList<String> leveldesign = ReadFile
			.readScreenFile("res/maps/map.txt");
	Monster junkie;

	public Town(Player player) {
		super(gtr.asciiscreen.AsciiScreen.getWidth(leveldesign),
				gtr.asciiscreen.AsciiScreen.getHeight(leveldesign));

		updateLevelVariables();

		Messenger.player = player;

		createAsciiScreen(leveldesign, this, player.getTerm());

		// int x = TermPanel.DEFAULT_COLS / 2;
		// int y = TermPanel.DEFAULT_ROWS / 2;
		if (currentLevel.getCoordinate().equals(new Coordinate(-1, -1)))
			addActor(player);
		else
			addActor(player, currentLevel.getCoordinate());

		if (getMappingLevelActor().containsKey(currentLevel.getLevelEnum()) == false) {

			for (int i = 0; i < 1; i++) {
				addActor(new Junkie());

			}
			for (int i = 0; i < 1; i++) {
				addActor(new Ninja());
			}
			for (int i = 0; i < 50; i++) {
				addActor(new Dealer());
			}

			//
			// Auskommentiert. Solange Karte noch nicht fertig, lohnt es sich
			// nicht,
			// Dooractors anzulegen, da sich die Koordinaten der Türen beim
			// Überarbeiten der Karten noch ändern können.
			//
			Door door = new Door(LevelEnum.Room, 5, 5);
			addActor(door, 35, 119);

			door = new Door(LevelEnum.Room, 5, 5);
			addActor(door, 55, 119);

			gtr.actor.other.Door.completeDoors(this);

		} else
			setActorsInWorld();

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
