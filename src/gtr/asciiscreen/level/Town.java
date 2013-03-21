package gtr.asciiscreen.level;

import gtr.actor.fix.Door;
import gtr.util.ReadFile;
import jade.core.Messenger;
import jade.util.datatype.Coordinate;

import java.util.ArrayList;

import rogue.creature.*;

public class Town extends Level {

	private final static ArrayList<String> leveldesign = ReadFile
			.readScreenFile("/res/maps/main_map.txt");

	public Town(Player player) {
		super(gtr.asciiscreen.AsciiScreen.getWidth(leveldesign),
				gtr.asciiscreen.AsciiScreen.getHeight(leveldesign));

		updateLevelVariables();
		Messenger.player = player;

		createAsciiScreen(leveldesign, this, player.getTerm());
		
		gtr.textbox.TextBox.displayEventText(player.getTerm(), "Drücke o zum Aufrufen der Hilfe.");

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
			for (int i = 0; i < 5; i++) {
				Yakuza Y = new Yakuza();
				addActor(Y);

			}
			for (int i = 0; i < 5; i++) {
				Exsoldat E = new Exsoldat();
				addActor(E);
			}

			// Tür des ersten Hauses, wenn man von der Insel kommt
			Door door = new Door(LevelEnum.House0, 15, 5);
			addActor(door, 35, 108);

			// Tür des Hauses rechts neben dem ersten Haus, wenn man von der
			// Insel kommt
			door = new Door(LevelEnum.House2, 5, 5);
			addActor(door, 55, 108);

			// Tür des nordöstlichen Hauses im ersten Distrikt
			door = new Door(LevelEnum.House1, 5, 5);
			addActor(door, 71, 111);

			// Tür des Evilhauses
			door = new Door(LevelEnum.Boss_empty_room, 1, 1);
			addActor(door, 122, 110);

			// Tür zum orientalischen Haus
			door = new Door(LevelEnum.Boss_empty_room, 1, 1);
			addActor(door, 123, 90);

			// Tür der Bad Bank
			door = new Door(LevelEnum.Boss_empty_room, 1, 1);
			addActor(door, 120, 62);

			// Tür der Pagode
			door = new Door(LevelEnum.Boss_empty_room, 1, 1);
			addActor(door, 122, 44);

			// Tür der Pagode
			door = new Door(LevelEnum.Boss_empty_room, 1, 1);
			addActor(door, 73, 20);

			// Tür des Hauses, was südlich dem nordöstlichen Haus des ersten
			// Distrikts steht.
			door = new Door(LevelEnum.House3, 1, 1);
			addActor(door, 63, 117);

			// Tür zum Asyl
			door = new Door(LevelEnum.Asylum, 1, 1);
			addActor(door, 20, 83);

			// Tür zum südöstlichen Haus im ersten Distrikt
			door = new Door(LevelEnum.House4, 1, 1);
			addActor(door, 84, 119);

			// Tür zum südwestlichen Haus im zweiten Distrikt
			door = new Door(LevelEnum.House5, 1, 1);
			addActor(door, 5, 99);

			// Tür zum südwestlichen Haus im zweiten Distrikt
			door = new Door(LevelEnum.House6, 1, 1);
			addActor(door, 57, 87);

			gtr.actor.fix.Door.completeDoors(this);

		} else
			setActorsInWorld();
	}
}
