package gtr.asciiscreen.level;

import jade.core.Messenger;
import jade.ui.Terminal;
import jade.util.datatype.Coordinate;

import java.util.ArrayList;

import rogue.creature.Monster;
import rogue.creature.Player;
import rogue.creature.Yakuza;

import gtr.asciiscreen.AsciiScreen.LevelEnum;
import gtr.util.ReadFile;
import gtr.util.datatype.Location;

public class EvilBossRoom extends Level {

	private final static ArrayList<String> leveldesign = ReadFile
			.readScreenFile("res/maps/bossroom.txt");

	public EvilBossRoom(Player player) {
		super(gtr.asciiscreen.AsciiScreen.getWidth(leveldesign),
				gtr.asciiscreen.AsciiScreen.getHeight(leveldesign));

		updateLevelVariables();
		Messenger.player = player;
		createAsciiScreen(leveldesign, this, player.getTerm());

		createExitAndAddDoorActorsAndPlayer(this, leveldesign);

		if (!getMappingLevelActor().containsKey(currentLevel.getLevelEnum())) {
			Yakuza y = new Yakuza();
			addActor(y, new Coordinate(17, 4));
			
		} else
			setActorsInWorld();

	}

	@Override
	public Location inLevel() {
		Terminal term = player.getTerm();

		while (!player.expired()) {

			if (getActor(Monster.class) == null)
				nextLevel = new Location(LevelEnum.HappyEndScreen,
						new Coordinate(1, 1));
			
			if (nextLevel != null) {
				this.removeActor(player);
				break; // Verlassen der while-Schleife
			}
			changeAndRefreshScreenAndTick(term);
		}
		return nextLevel;
	}

}
