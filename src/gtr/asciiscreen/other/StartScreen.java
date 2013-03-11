package gtr.asciiscreen.other;

import gtr.asciiscreen.ScreenType;
import gtr.util.ReadFile;
import jade.ui.Terminal;
import jade.util.datatype.ColoredChar;

import java.util.ArrayList;

import rogue.creature.Player;

public class StartScreen extends OtherScreen {

	private final static ArrayList<String> leveldesign = ReadFile
			.readScreenFile("res/screens/start_screen_gtr.txt");
	private Player player;

	public StartScreen(Player player) {
		super(gtr.asciiscreen.AsciiScreen.getWidth(leveldesign),
				gtr.asciiscreen.AsciiScreen.getHeight(leveldesign));
		
		screenType = ScreenType.StartScreen;
		
		this.player = player;
		createAsciiScreen(leveldesign, this,
				player.getTerm());
		int i = 1;
		this.player.setFace(ColoredChar.create(leveldesign.get(i).charAt(i)));
		addActor(this.player, i, i);

		currentLevel = "StartLevel";

		// steht nur da, da momentan StartLevel nicht nur am Anfang, sondern
		// nochmals erreichbar ist
		/**!TODO hier */
		if (lastLevel != null && lastLevel.equals(nextLevel))
			updateLevelVariables();
	}

	public String inLevel() {
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
