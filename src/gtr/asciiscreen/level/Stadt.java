package gtr.asciiscreen.level;

import gtr.util.ReadFile;

import jade.ui.TermPanel;
import jade.ui.Terminal;
import jade.util.datatype.ColoredChar;

import java.util.ArrayList;

import rogue.creature.Player;

public class Stadt extends Level {

	private final static ArrayList<String> leveldesign = ReadFile
			.readScreenFile("res/maps/main_map.txt");

	public Stadt(Player player) {
		super(gtr.asciiscreen.AsciiScreen.getWidth(leveldesign),
				gtr.asciiscreen.AsciiScreen.getHeight(leveldesign));

		this.player = player;
		createAsciiScreen(leveldesign, this, player.getTerm());

		int x = TermPanel.DEFAULT_COLS / 2;
		int y = TermPanel.DEFAULT_ROWS / 2;
		addActor(this.player, x, y);
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
