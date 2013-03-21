package gtr.asciiscreen.level;

import gtr.util.datatype.Location;

import java.awt.Color;

import jade.core.Messenger;
import jade.gen.Generator;
import jade.gen.map.Cellular;
import jade.ui.Terminal;
import jade.util.datatype.ColoredChar;
import rogue.creature.Monster;
import rogue.creature.Player;
import rogue.creature.Yakuza;

public class Dungeon extends Level {
	private final static Generator gen = getLevelGenerator();
	private Yakuza y;

	public Dungeon(int width, int height, Player player) {
		super(width, height);
		
		updateLevelVariables();
		
		Messenger.player = player;
		gen.generate(this);
		addActor(player);

		y = new Yakuza();
		addActor(y);
	}

	private static Generator getLevelGenerator() {
		return new Cellular();
	}

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
