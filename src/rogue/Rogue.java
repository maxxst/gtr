package rogue;

import gtr.asciiscreen.AsciiScreen;
import jade.core.World;
import jade.ui.TiledTermPanel;
import jade.util.datatype.ColoredChar;
import java.awt.Color;
import rogue.creature.Monster;
import rogue.creature.Player;
import rogue.level.Level;

public class Rogue
{
    public static void main(String[] args) throws InterruptedException
    {
        
    	TiledTermPanel term = TiledTermPanel.getFramedTerminal("GTR");
    	Player player = new Player(term);
    	World world = new Level(50, 30, player);
    	world.addActor(new Monster(ColoredChar.create('D', Color.red)));
    	AsciiScreen.showAsciiScreen(gtr.util.ReadFile.readScreenFile("res/screens/start_screen_gtr.txt"), world, term);
    	
//    	player.setFace(' ');
//    	World world = new Level(69, 24, player);
    	
    	
    	
    	
    	/*
        term.registerTile("dungeon.png", 5, 59, ColoredChar.create('#'));
        term.registerTile("dungeon.png", 3, 60, ColoredChar.create('.'));
        term.registerTile("dungeon.png", 5, 20, ColoredChar.create('@'));
        term.registerTile("dungeon.png", 14, 30, ColoredChar.create('D', Color.red));
        Warum ist das gut? 
        */
    	
        
        term.registerCamera(player, 5, 5);
        
        while(!player.expired())
        {
            term.clearBuffer();
            for(int x = 0; x < world.width(); x++)
                for(int y = 0; y < world.height(); y++)
                    term.bufferChar(x + 11, y, world.look(x, y));
            term.bufferCameras();
            term.refreshScreen();

            world.tick();
        }

        System.exit(0);
    }
}
