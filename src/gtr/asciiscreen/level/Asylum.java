package gtr.asciiscreen.level;

import jade.core.Messenger;

import java.util.ArrayList;

import rogue.creature.Irrer;
import rogue.creature.Iwan;
import rogue.creature.Player;
import rogue.creature.Wahnsinniger;

import gtr.util.ReadFile;

public class Asylum extends Level {

	private final static ArrayList<String> leveldesign = ReadFile
			.readScreenFile("/res/maps/asylum.txt");

	public Asylum(Player player) {
		super(gtr.asciiscreen.AsciiScreen.getWidth(leveldesign),
				gtr.asciiscreen.AsciiScreen.getHeight(leveldesign));

		updateLevelVariables();
		Messenger.player = player;
		createAsciiScreen(leveldesign, this, player.getTerm());

		createExitAndAddDoorActorsAndPlayer(this, leveldesign);

		if (!getMappingLevelActor().containsKey(currentLevel.getLevelEnum())) {

			// Hier die Monster erstellen
			for (int i = 0; i < 25; i++) {
				Iwan Ï = new Iwan();
				addActor(Ï);
			}
			for (int i = 0; i < 25; i++) {
				Irrer I = new Irrer();
				addActor(I);
			}
			for (int i = 0; i < 25; i++) {
				Wahnsinniger W = new Wahnsinniger();
				addActor(W);
			}
			
			
		} else
			setActorsInWorld();
	}

}
