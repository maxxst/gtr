package gtr.asciiscreen.level;

import jade.core.Messenger;
import jade.ui.Terminal;

import java.util.ArrayList;
import java.util.Random;

import gtr.asciiscreen.AsciiScreen;
import gtr.util.ReadFile;
import gtr.util.datatype.Location;
import rogue.creature.Player;

public class Room extends Level {

	private static ArrayList<String> leveldesign;
	
	private static void createRoom(int width, int height) {
		// TODO Auto-generated method stub
		leveldesign = new ArrayList<String>();
		String wall = "";
		for (int i = 0; i < width; i++)
			wall += AsciiScreen.standardWall;
		leveldesign.add(wall);
		for (int y = 1; y < height - 1; y++) {
			String s = "#";
			for (int x = 1; x < width - 1; x++)
				s += ".";
			s += "#";
			leveldesign.add(s);
		}
		leveldesign.add(wall);
	}
	
	public Room(Player player, int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
		System.out.println("w: " + width + " h: " + height);
		updateLevelVariables();
		
		Messenger.player = player;
		
		createRoom(width, height);
		System.out.println("height: " + gtr.asciiscreen.AsciiScreen.getHeight(leveldesign) + "width: " + gtr.asciiscreen.AsciiScreen.getWidth(leveldesign));
		createAsciiScreen(leveldesign, this, player.getTerm());
		
		addActor(player);
		
	}

	public Room(Player player) {
		// Erzeugt Raum mit zufälliger Größe.
		// Mindestgröße: 15 * 15, Höchstgröße: 49 * 49
		this(player, new Random().nextInt(35) + 15, new Random().nextInt(35) + 15);
	}

	@Override
	public Location inLevel() {
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
