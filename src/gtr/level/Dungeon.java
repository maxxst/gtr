package gtr.level;

import java.awt.Color;

import jade.core.World;
import jade.gen.Generator;
import jade.gen.map.Cellular;
import jade.ui.Terminal;
import jade.util.datatype.ColoredChar;
import rogue.creature.Monster;
import rogue.creature.Player;

public class Dungeon extends World
{
    private final static Generator gen = getLevelGenerator();
    private Monster monster;
	private Player player;

    public Dungeon(int width, int height, Player player)
    {
        super(width, height);
        this.player = player;
        gen.generate(this);
        addActor(this.player);
        
        monster = new Monster(ColoredChar.create('D', Color.red));
    	addActor(monster);
    }

    private static Generator getLevelGenerator()
    {
        return new Cellular();
    }
    
    public String inLevel() {
		Terminal term = player.getTerm();
		updateLevelVariables();
		while (!player.expired()) {
			
			if (player.x() == monster.x() && player.y() == monster.y()) {
        		this.removeActor(player);
        		this.removeActor(monster);
        		
        		nextLevel = "StartLevel";
        		break; // Verlassen der while-Schleife
        	}
			
			changeAndRefreshScreenAndTick(term);
		}
		return nextLevel;
	}
}
