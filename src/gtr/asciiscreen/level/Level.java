package gtr.asciiscreen.level;

import jade.ui.Terminal;
import jade.util.datatype.Coordinate;

import java.util.ArrayList;

import gtr.actor.fix.Door;
import gtr.asciiscreen.AsciiScreen;
import gtr.asciiscreen.ScreenType;
import gtr.util.datatype.Location;

public abstract class Level extends AsciiScreen {

	protected static final char standardWall = '#';
	protected static final char exit = '▒';
	protected static final char upstairs = '⇗';
	protected static final char downstairs = '⇘';

	public Level(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub

		screenType = ScreenType.Level;
	}

	public static char getExitChar() {
		return exit;
	}

	protected static void createExitAndAddDoorActorsAndPlayer(Level l,
			ArrayList<String> leveldesign) {
		// finde ▒ und erstelle Dooractor
		Coordinate posDoor = null;
		for (int y = 0; y < gtr.asciiscreen.AsciiScreen.getHeight(leveldesign); y++)
			for (int x = 0; x < gtr.asciiscreen.AsciiScreen
					.getWidth(leveldesign); x++)
				if (leveldesign.get(y).charAt(x) == exit) {
					posDoor = new Coordinate(x, y);
					break;
				}
		if (!lastLevel.getLevelEnum().equals(LevelEnum.Dungeon)) {

			l.addActor(new Door(lastLevel, exit), posDoor);
			l.addActor(new Door(lastLevel, exit), posDoor.x() + 1, posDoor.y());
		} else if (lastLevel.getLevelEnum().equals(LevelEnum.Dungeon) && currentLevel.getLevelEnum().equals(LevelEnum.BossRoom)) {
			l.addActor(new Door(lastLevel.getLevelEnum(), -1, -1, exit), posDoor);
			l.addActor(new Door(lastLevel.getLevelEnum(), -1, -1, exit), posDoor.x() + 1, posDoor.y());
		}
		
		
		l.addActor(player, posDoor.x(), posDoor.y() - 1);
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
