package gtr.asciiscreen.level;

import jade.core.Messenger;

import java.util.ArrayList;

import rogue.creature.Player;

import gtr.util.ReadFile;

public class House1 extends Level {

	private final static ArrayList<String> leveldesign = ReadFile
			.readScreenFile("/res/maps/room2.txt");

	public House1(Player player) {
		super(gtr.asciiscreen.AsciiScreen.getWidth(leveldesign),
				gtr.asciiscreen.AsciiScreen.getHeight(leveldesign));

		updateLevelVariables();
		Messenger.player = player;
		createAsciiScreen(leveldesign, this, player.getTerm());

		createExitAndAddDoorActorsAndPlayer(this, leveldesign);

		if (!getMappingLevelActor().containsKey(currentLevel.getLevelEnum())) {

		} else
			setActorsInWorld();

	}

}
