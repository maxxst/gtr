package gtr.asciiscreen.other;

import gtr.asciiscreen.ScreenType;
import gtr.util.ReadFile;
import gtr.util.datatype.Location;
import jade.core.Messenger;
import jade.ui.TermPanel;
import jade.ui.Terminal;
import jade.util.datatype.ColoredChar;

import java.util.ArrayList;

import rogue.creature.Player;

public class StartScreen extends OtherScreen {

	private final static ArrayList<String> leveldesign = ReadFile
			.readScreenFile("res/screens/start_screen_gtr.txt");

	public StartScreen(Player player) {
		super(gtr.asciiscreen.AsciiScreen.getWidth(leveldesign),
				gtr.asciiscreen.AsciiScreen.getHeight(leveldesign));

		screenType = ScreenType.StartScreen;

		currentLevel = new Location(LevelEnum.StartScreen);
		// steht nur da, da momentan StartScreen nicht nur am Anfang, sondern
		// nochmals erreichbar ist
		/** !TODO hier */
		if (lastLevel != null
				&& lastLevel.getLevelEnum().equals(nextLevel.getLevelEnum()))
			updateLevelVariables();

		Messenger.player = player;
		createAsciiScreen(leveldesign, this, player.getTerm());

		int x = TermPanel.DEFAULT_COLS / 2;
		int y = TermPanel.DEFAULT_ROWS / 2;
		player.setFace(ColoredChar.create(leveldesign.get(y).charAt(x)));
		addActor(player, x, y);
	}

	public Location inLevel() {
		Terminal term = player.getTerm();

		while (!player.expired()) {

			if (nextLevel != null) {
				this.removeActor(player);
				player.setFace(player.getStandardFace());
				break; // Verlassen der while-Schleife
			}

			changeAndRefreshScreenAndTick(term);
		}
		return nextLevel;
	}
}
