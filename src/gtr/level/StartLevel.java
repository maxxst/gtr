package gtr.level;

import java.awt.Color;
import java.util.ArrayList;

import rogue.creature.Monster;
import rogue.creature.Player;
import jade.core.World;
import jade.ui.Terminal;
import jade.util.datatype.ColoredChar;

public class StartLevel extends World {

	private final static ArrayList<String> leveldesign = gtr.util.ReadFile
			.readScreenFile("res/screens/start_screen_gtr.txt");
	private Monster monster;
	private Player player;

	public StartLevel(Player player) {
		super(gtr.asciiscreen.AsciiScreen.getWidth(leveldesign),
				gtr.asciiscreen.AsciiScreen.getHeight(leveldesign));
		this.player = player;
		gtr.asciiscreen.AsciiScreen.createAsciiScreen(leveldesign, this,
				player.getTerm());
		addActor(this.player);
		
		monster = new Monster(ColoredChar.create('D', Color.red));
    	addActor(monster);
	}

	public String inLevel() {
		Terminal term = player.getTerm();
		updateLevelVariables();
		while (!player.expired()) {
			
			if (player.x() == monster.x() && player.y() == monster.y()) {
        		this.removeActor(player);
        		this.removeActor(monster);
        		
        		nextLevel = "Dungeon";
        		break; // Verlassen der while-Schleife
        	}
			
			changeAndRefreshScreenAndTick(term);
		}
		return nextLevel;
	}
}
