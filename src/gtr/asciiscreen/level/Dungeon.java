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

public class Dungeon extends Level {
	private final static Generator gen = getLevelGenerator();
	private Monster monster;

	public Dungeon(int width, int height, Player player) {
		super(width, height);
		Messenger.player = player;
		gen.generate(this);
		addActor(player);

		monster = new Monster(ColoredChar.create('D', Color.red));
		addActor(monster);

		updateLevelVariables();
	}

	private static Generator getLevelGenerator() {
		return new Cellular();
	}

	public Location inLevel() {
		Terminal term = player.getTerm();

		while (!player.expired()) {

			if (player.x() == monster.x() && player.y() == monster.y()) {
				nextLevel = new Location(LevelEnum.StartScreen);
			}

			if (nextLevel != null) {
				this.removeActor(player);
				this.removeActor(monster);
				break; // Verlassen der while-Schleife
			}

			changeAndRefreshScreenAndTick(term);
		}
		return nextLevel;
	}
}
