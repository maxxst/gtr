package rogue;

import gtr.asciiscreen.level.Dungeon;
import gtr.asciiscreen.level.Stadt;
import gtr.asciiscreen.other.StartScreen;
import gtr.util.datatype.Location;
import jade.core.World;
import jade.ui.TiledTermPanel;
import rogue.creature.Player;

public class Rogue {
	public static void main(String[] args) throws InterruptedException {

		//Erstellt das neue Fenster:
		TiledTermPanel term = TiledTermPanel.getFramedTerminal("GTR");
		Player player = new Player(term); //setzt den Spieler ins Terminal
		World world = new StartScreen(player);
		Location nextLevel = world.inLevel(); //gtr addon

		/*
		 * WOZU ALLES IST DIES HIER GUT? term.registerTile("dungeon.png", 5, 59,
		 * ColoredChar.create('#')); term.registerTile("dungeon.png", 3, 60,
		 * ColoredChar.create('.')); term.registerTile("dungeon.png", 5, 20,
		 * ColoredChar.create('@')); 
		 */
		//term.registerTile("dungeon.png", 14, 30, ColoredChar.create('D', Color.blue));

//		term.registerCamera(player, 5, 5);

		while (!player.expired()) {
			switch (nextLevel.getLevelEnum()) {
			case Dungeon:
				world = new Dungeon(20, 20, player);
				nextLevel = world.inLevel();
				break;
			case StartScreen:
				world = new StartScreen(player);
				nextLevel = world.inLevel();
				break;
			case Stadt:
				world = new Stadt(player);
				nextLevel = world.inLevel();
				break;
			}	
		}
		System.exit(0);
	}
}
