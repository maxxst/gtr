package rogue;

import gtr.asciiscreen.level.Dungeon;
import gtr.asciiscreen.level.RandomRoom;
import gtr.asciiscreen.level.Room1;
import gtr.asciiscreen.level.Town;
import gtr.asciiscreen.other.Prologue;
import gtr.asciiscreen.other.StartScreen;
import gtr.util.datatype.Location;
import jade.core.Actor;
import jade.core.World;
import jade.ui.TiledTermPanel;
import rogue.creature.Player;

public class Rogue {
	public static void main(String[] args) throws InterruptedException {
		TiledTermPanel term = TiledTermPanel.getFramedTerminal("GTR");
		Player player = new Player(term); //setzt den Spieler ins Terminal
		
		StartGame("start_screen", player);
		while (player.expired()) {
			player = new Player(term); //setzt den Spieler ins Terminal
			StartGame("end_screen", player);
		}
		
	}
	
	public static void StartGame(String screen, Player player){
		//Erstellt das neue Fenster:
		
				World world = new StartScreen(player, screen);
				Location nextLevel = world.inLevel(); //gtr addon

				/*
				 * WOZU ALLES IST DIES HIER GUT? term.registerTile("dungeon.png", 5, 59,
				 * ColoredChar.create('#')); term.registerTile("dungeon.png", 3, 60,
				 * ColoredChar.create('.')); term.registerTile("dungeon.png", 5, 20,
				 * ColoredChar.create('@')); 
				 */
				//term.registerTile("dungeon.png", 14, 30, ColoredChar.create('D', Color.blue));

//				term.registerCamera(player, 5, 5);

				while (!player.expired()) {
					
					
					switch (nextLevel.getLevelEnum()) {
					case Dungeon:
						world = new Dungeon(100, 100, player);
						nextLevel = world.inLevel();
						break;
					case StartScreen:
						world = new StartScreen(player, screen);
						nextLevel = world.inLevel();
						break;
					case Prologue:
						world = new Prologue(player);
						nextLevel = world.inLevel();
					case Town:
						world = new Town(player);
						nextLevel = world.inLevel();
						break;
					case RandomRoom:
						world = new RandomRoom(player);
						nextLevel = world.inLevel();
						break;
					case Room1:
						world = new Room1(player);
						nextLevel = world.inLevel();
						break;
					}	
					
					world.getMappingLevelActor().put(player.getCurrentLevel().getLevelEnum(), world.getActors(Actor.class).toArray(new Actor[0]));
					
						
				}
				//System.exit(0);
	}
}
