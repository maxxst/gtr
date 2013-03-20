package gtr.asciiscreen.level;

import gtr.actor.fix.Door;
import gtr.util.ReadFile;
import gtr.util.datatype.Location;
import jade.core.Messenger;
import jade.ui.Terminal;
import jade.util.datatype.Coordinate;

import java.util.ArrayList;

import rogue.creature.*;

public class Town extends Level {

	private final static ArrayList<String> leveldesign = ReadFile
			.readScreenFile("res/maps/main_map.txt");

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

		if (!getMappingLevelActor().containsKey(currentLevel.getLevelEnum())) {

			for (int i = 0; i < 100; i++) {
				addActor(new Junkie());

			}
			for (int i = 0; i < 15; i++) {
				addActor(new Ninja());
			}
			for (int i = 0; i < 50; i++) {
				addActor(new Dealer());
			}
			for (int i = 0; i < 20; i++) {
				Katze k = new Katze();
				addActor(k);
			}
			for (int i = 0; i < 200; i++) {
				Yakuza Y = new Yakuza();
				addActor(Y);
			}
			
			// Tür des ersten Hauses, wenn man von der Insel kommt
			Door door = new Door(LevelEnum.Room0, 15, 5);
			addActor(door, 35, 108);

			// Tür des Hauses rechts neben dem ersten Haus, wenn man von der Insel kommt
			door = new Door(LevelEnum.Room2, 5, 5);
			addActor(door, 55, 108);
			
			// Tür des nordöstlichen Hauses im ersten Distrikt
			door = new Door(LevelEnum.Room1, 5, 5);
			addActor(door, 71, 111);

			// Tür des Evilhauses
			door = new Door(LevelEnum.Dungeon, 1, 1);
			addActor(door, 122, 110);

			// Tür zum orientalischen Haus
			door = new Door(LevelEnum.Dungeon, 1, 1);
			addActor(door, 123, 90);

			// Tür der Bad Bank
			door = new Door(LevelEnum.Dungeon, 1, 1);
			addActor(door, 120, 62);

			// Tür der Pagode
			door = new Door(LevelEnum.Dungeon, 1, 1);
			addActor(door, 122, 44);
			
			// Tür des Hauses, was südlich dem nordöstlichen Haus des ersten Distrikts steht.
			door = new Door(LevelEnum.Room2, 1, 1);
			addActor(door, 117, 63);
			
			//

			gtr.actor.fix.Door.completeDoors(this);

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

