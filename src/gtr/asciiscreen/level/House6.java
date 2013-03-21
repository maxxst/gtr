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

public class House6 extends Level {

	private final static ArrayList<String> leveldesign = ReadFile
			.readScreenFile("/res/maps/room3.txt");

	public House6(Player player) {
		super(gtr.asciiscreen.AsciiScreen.getWidth(leveldesign),
				gtr.asciiscreen.AsciiScreen.getHeight(leveldesign));

		updateLevelVariables();
		Messenger.player = player;
		createAsciiScreen(leveldesign, this, player.getTerm());

		createExitAndAddDoorActorsAndPlayer(this, leveldesign);

		if (!getMappingLevelActor().containsKey(currentLevel.getLevelEnum())) {

			ArrayList<String> messages = new ArrayList<String>();
			messages.add("A: Say my name.");
			messages.add("B: I don’t know your name.");
			messages.add("A: I’m the one who killed Fring.");
			messages.add("B: Heisenberg.");

			Human person = new Human(ColoredChar.create('!', Color.red),
					messages);
			Human person2 = new Human(ColoredChar.create('!', Color.blue), messages);
			addActor(person, 13, 5);
			addActor(person2, 14, 5);
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
