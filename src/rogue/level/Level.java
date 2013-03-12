package rogue.level;

import gtr.util.datatype.Location;
import jade.core.World;
import jade.gen.Generator;
import jade.gen.map.Cellular;
import rogue.creature.Player;

public class Level extends World
{
    private final static Generator gen = getLevelGenerator();

    public Level(int width, int height, Player player)
    {
        super(width, height);
        gen.generate(this);
        addActor(player);
    }

    private static Generator getLevelGenerator()
    {
        return new Cellular();
    }

	@Override
	public Location inLevel() {
		// TODO Auto-generated method stub
		return null;
	}
}
