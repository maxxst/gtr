package rogue;

import gtr.level.Dungeon;
import gtr.level.StartLevel;
import jade.core.World;
import jade.ui.TiledTermPanel;
import rogue.creature.Player;

public class Rogue
{
    public static void main(String[] args) throws InterruptedException
    {
        
    	TiledTermPanel term = TiledTermPanel.getFramedTerminal("GTR");
    	Player player = new Player(term);
    	World world = new Dungeon(20, 20, player);
    	String nextLevel = world.inLevel();
    	
    	/* WOZU ALLES IST DIES HIER GUT?
        term.registerTile("dungeon.png", 5, 59, ColoredChar.create('#'));
        term.registerTile("dungeon.png", 3, 60, ColoredChar.create('.'));
        term.registerTile("dungeon.png", 5, 20, ColoredChar.create('@'));
        term.registerTile("dungeon.png", 14, 30, ColoredChar.create('D', Color.red));
        */
        
        term.registerCamera(player, 5, 5);
        
        while(!player.expired())
        {
        	if (nextLevel.equals("Dungeon")) {
        		world = new Dungeon(20, 20, player);
            	nextLevel = world.inLevel();
        	}
        	if (nextLevel.equals("StartLevel")) {
        		world = new StartLevel(player);
        		nextLevel = world.inLevel();
        	}
        	
        	
        }

        System.exit(0);
    }
}
