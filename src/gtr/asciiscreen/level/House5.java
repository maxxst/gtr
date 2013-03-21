package gtr.asciiscreen.level;

import jade.core.Messenger;
import jade.ui.Terminal;
import jade.util.datatype.ColoredChar;

import java.awt.Color;
import java.util.ArrayList;

import rogue.creature.Human;
import rogue.creature.Player;

import gtr.util.ReadFile;
import gtr.util.datatype.Location;

public class House5 extends Level {

	private final static ArrayList<String> leveldesign = ReadFile
			.readScreenFile("res/maps/room0.txt");

	public House5(Player player) {
		super(gtr.asciiscreen.AsciiScreen.getWidth(leveldesign),
				gtr.asciiscreen.AsciiScreen.getHeight(leveldesign));

		updateLevelVariables();
		Messenger.player = player;
		createAsciiScreen(leveldesign, this, player.getTerm());

		createExitAndAddDoorActorsAndPlayer(this, leveldesign);

		if (!getMappingLevelActor().containsKey(currentLevel.getLevelEnum())) {

			ArrayList<String> messages = new ArrayList<String>();
			messages.add("Xing Po wirst du niemals zu Gesicht bekommen.");
			messages.add("Hahahahaha … hahahaha!");
			messages.add("Und jetzt verschwinde!");

			Human person = new Human(ColoredChar.create('!', Color.red),
					messages);
			addActor(person, 12, 6);
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
