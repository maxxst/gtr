package gtr.asciiscreen.level;

import jade.core.Messenger;
import jade.util.datatype.ColoredChar;

import java.awt.Color;
import java.util.ArrayList;

import rogue.creature.Human;
import rogue.creature.Katze;
import rogue.creature.Player;

import gtr.util.ReadFile;

public class House0 extends Level {

	private final static ArrayList<String> leveldesign = ReadFile
			.readScreenFile("res/maps/room0.txt");

	public House0(Player player) {
		super(gtr.asciiscreen.AsciiScreen.getWidth(leveldesign),
				gtr.asciiscreen.AsciiScreen.getHeight(leveldesign));

		updateLevelVariables();
		Messenger.player = player;
		createAsciiScreen(leveldesign, this, player.getTerm());

		createExitAndAddDoorActorsAndPlayer(this, leveldesign);

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

	

}
