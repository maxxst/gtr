package rogue;

import javax.swing.JApplet;

import gtr.asciiscreen.level.*;
import gtr.asciiscreen.other.Prologue;
import gtr.asciiscreen.other.StartScreen;
import gtr.util.datatype.Location;
import jade.core.Actor;
import jade.core.World;
import jade.ui.TiledTermPanel;
import rogue.creature.Player;

public class Rogue extends JApplet {

	TiledTermPanel term;
	private Player player;

	public void init() {
		term = TiledTermPanel.getFramedTerminal("GTR");
		player = new Player(term); // setzt den Spieler ins Terminal
	}

	public void start() {

		StartGame("start_screen", player);
		while (player.expired()) {
			World.getMappingLevelActor().clear();
			player = new Player(term); // setzt den Spieler ins Terminal
			StartGame("end_screen", player);
		}

	}

	public void stop() {
		System.out.println("stop");
	}

	public void destroy() {
		System.out.println("destroy");
	}

	public static void main(String[] args) throws InterruptedException {

		Rogue r = new Rogue();
		r.init();
		r.start();

	}

	public static void StartGame(String screen, Player player) {
		// Erstellt das neue Fenster:

		World world = new StartScreen(player, screen);
		Location nextLevel = world.inLevel(); // gtr addon

		/*
		 * WOZU ALLES IST DIES HIER GUT? term.registerTile("dungeon.png", 5, 59,
		 * ColoredChar.create('#')); term.registerTile("dungeon.png", 3, 60,
		 * ColoredChar.create('.')); term.registerTile("dungeon.png", 5, 20,
		 * ColoredChar.create('@'));
		 */
		// term.registerTile("dungeon.png", 14, 30, ColoredChar.create('D',
		// Color.blue));

		// term.registerCamera(player, 5, 5);

		while (!player.expired()) {

			switch (nextLevel.getLevelEnum()) {
			case Dungeon:
				world = new Dungeon(50, 50, player);
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
			case House0:
				world = new House0(player);
				nextLevel = world.inLevel();
				break;
			case House1:
				world = new House1(player);
				nextLevel = world.inLevel();
				break;
			case House2:
				world = new House2(player);
				nextLevel = world.inLevel();
				break;
			case House3:
				world = new House3(player);
				nextLevel = world.inLevel();
				break;
			case Asylum:
				world = new Asylum(player);
				nextLevel = world.inLevel();
			case House4:
				world = new House4(player);
				nextLevel = world.inLevel();
			case House5:
				world = new House5(player);
				nextLevel = world.inLevel();
			case House6:
				world = new House6(player);
				nextLevel = world.inLevel();
			case Boss_empty_room:
				world = new EvilEmptyRoom(player);
				nextLevel = world.inLevel();
			}

			World.getMappingLevelActor().put(
					player.getCurrentLevel().getLevelEnum(),
					world.getActors(Actor.class).toArray(new Actor[0]));

		}
		// System.exit(0);
	}
}
