package gtr.asciiscreen.level;

import gtr.util.ReadFile;

import jade.ui.Terminal;

import java.util.ArrayList;

import rogue.creature.Player;

public class Stadt extends Level {

	private final static ArrayList<String> leveldesign = ReadFile
			.readScreenFile("res/maps/main_map.txt");
	private Player player;

	public Stadt(Player player) {
		super(gtr.asciiscreen.AsciiScreen.getWidth(leveldesign),
				gtr.asciiscreen.AsciiScreen.getHeight(leveldesign));

		this.player = player;
		createAsciiScreen(leveldesign, this, player.getTerm());

		addActor(this.player, 2, 12);
		updateLevelVariables();
	}

	@Override
	public String inLevel() {
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
