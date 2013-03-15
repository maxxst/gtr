package rogue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import gtr.asciiscreen.AsciiScreen.LevelEnum;
import gtr.asciiscreen.level.Dungeon;
import gtr.asciiscreen.level.Room;
import gtr.asciiscreen.level.Town;
import gtr.asciiscreen.other.StartScreen;
import gtr.util.datatype.Location;
import jade.core.Actor;
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
			case Town:
				world = new Town(player);
				nextLevel = world.inLevel();
				break;
			case Room:
				world = new Room(player);
				nextLevel = world.inLevel();
				break;
			}	
			
			world.getMappingLevelActor().put(player.getCurrentLevel().getLevelEnum(), world.getActors(Actor.class).toArray(new Actor[0]));
			//			world.setMappingLevelActor(new HashMap<world>)
			
			/* Vorbereitungen
			
			LevelEnum levelEnum = nextLevel.getLevelEnum();
			Class<?> c = null;
			Object invoker = null;
			Method objMethod = null;
			Class<?> arguments[] = null;
			
			try {
				c = Class.forName("gtr.asciiscreen.level." + levelEnum.toString());
				Constructor<?> constructor;
				
				switch (levelEnum) {
				case Dungeon:
					constructor = c.getConstructor(new Class[] { Integer.class, Integer.class, Player.class });
					invoker = constructor.newInstance(new Object[] { 20, 20, player });
					break;
				default:
					constructor = c.getConstructor(new Class[] { Player.class });
					invoker = constructor.newInstance(new Object[] { player });
					break;
				}
				 arguments = new Class[] { };
				objMethod = c.getMethod("inLevel", arguments);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				try {
					nextLevel = (Location) objMethod.invoke(invoker, (Object[]) arguments);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			*/
			
		}
		System.exit(0);
	}
}
