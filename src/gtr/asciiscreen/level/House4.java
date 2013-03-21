package gtr.asciiscreen.level;

import jade.core.Messenger;
import jade.ui.Terminal;

import java.util.ArrayList;

import rogue.creature.Player;

import gtr.util.ReadFile;
import gtr.util.datatype.Location;

public class House4 extends Level {

	private final static ArrayList<String> leveldesign = ReadFile
			.readScreenFile("/res/maps/room2.txt");

	public House4(Player player) {
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
