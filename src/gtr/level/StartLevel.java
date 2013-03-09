package gtr.level;

import java.util.ArrayList;

import rogue.creature.Player;
import jade.core.World;
import jade.ui.Terminal;
import jade.util.datatype.ColoredChar;

public class StartLevel extends World {

	private final static ArrayList<String> leveldesign = gtr.util.ReadFile
			.readScreenFile("res/screens/start_screen_gtr.txt");
	private Player player;

	public StartLevel(Player player) {
		super(gtr.asciiscreen.AsciiScreen.getWidth(leveldesign),
				gtr.asciiscreen.AsciiScreen.getHeight(leveldesign));
		this.player = player;
		gtr.asciiscreen.AsciiScreen.createAsciiScreen(leveldesign, this,
				player.getTerm());
		int i = 1;
		this.player.setFace(ColoredChar.create(leveldesign.get(i).charAt(i)));
		addActor(this.player, i, i);

		currentLevel = "StartLevel";

		// steht nur da, da momentan StartLevel nicht nur am Anfang, sondern
		// nochmals erreichbar ist
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
