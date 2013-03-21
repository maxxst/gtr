package gtr.asciiscreen.level;

import gtr.actor.fix.Door;
import gtr.util.datatype.Location;

import java.util.ArrayList;
import java.util.Arrays;

import jade.core.Actor;
import jade.core.Messenger;
import jade.gen.Generator;
import jade.gen.map.Cellular;
import jade.ui.Terminal;
import jade.util.datatype.Coordinate;
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

		// alte Treppe entfernen
		try {
			Actor[] actors = getMappingLevelActor().get(
					player.getCurrentLevel().getLevelEnum());

			ArrayList<Actor> actorList = new ArrayList<Actor>(
					Arrays.asList(actors));
			int tmp = 0;
			for (int i = 0; i < actorList.size(); i++)
				if (actorList.get(i).face().ch() == upstairs
						|| actorList.get(i).face().ch() == downstairs) {
					actorList.remove(i);
					tmp++;
					if (tmp == 2)
						break;
				}
			getMappingLevelActor().put(player.getCurrentLevel().getLevelEnum(),
					actorList.toArray(new Actor[0]));
		} catch (NullPointerException e) {

		}

		// neue Treppe und Position des Spielers
		Coordinate actorPos = null;
		Coordinate openTile = null;
		while (actorPos == null) {
			openTile = getOpenTile();
			if (!(passableAt(openTile.x(), openTile.y() + 1)
					&& passableAt(openTile.x(), openTile.y() + 1)
					&& passableAt(openTile.x(), openTile.y() - 1)
					&& passableAt(openTile.x() + 1, openTile.y())
					&& passableAt(openTile.x() - 1, openTile.y())
					&& passableAt(openTile.x() - 1, openTile.y() + 1)
					&& passableAt(openTile.x() + 1, openTile.y() + 1)
					&& passableAt(openTile.x() + 1, openTile.y() - 1) && passableAt(
						openTile.x() - 1, openTile.y() - 1)))
				continue;
			else
				actorPos = new Coordinate(openTile.x(), openTile.y() + 1);

		}

		// Treppe
		addActor(new Door(new Location(LevelEnum.Boss_empty_room,
				new Coordinate(8, 14)), upstairs), openTile);
		addActor(player, actorPos);

		Coordinate openTile2 = null;
		// Treppe zum {Zwischen, End}gegner
		while (openTile2 == null) {
			openTile2 = getOpenTile();
			if (!(passableAt(openTile2.x(), openTile2.y() + 1)
					&& passableAt(openTile2.x(), openTile2.y() + 1)
					&& passableAt(openTile2.x(), openTile2.y() - 1)
					&& passableAt(openTile2.x() + 1, openTile2.y())
					&& passableAt(openTile2.x() - 1, openTile2.y())
					&& passableAt(openTile2.x() - 1, openTile2.y() + 1)
					&& passableAt(openTile2.x() + 1, openTile2.y() + 1)
					&& passableAt(openTile2.x() + 1, openTile2.y() - 1) && passableAt(
						openTile2.x() - 1, openTile2.y() - 1))) {
				openTile2 = null;
			}

		}
		// Treppe
		addActor(new Door(
				new Location(LevelEnum.BossRoom, new Coordinate(1, 1)),
				downstairs), openTile2);

		if (!getMappingLevelActor().containsKey(currentLevel.getLevelEnum())) {

			y = new Yakuza();

			addActor(y);

		} else
			setActorsInWorld();
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
