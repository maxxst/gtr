package rogue;

import jade.core.World;
import jade.ui.TiledTermPanel;
import jade.util.datatype.ColoredChar;
import java.awt.Color;
import rogue.creature.Monster;
import rogue.creature.Player;
import rogue.level.AsciiScreenActor;
import rogue.level.Level;
import rogue.level.AsciiScreen;
import rogue.level.AsciiScreenInput;

public class Rogue
{
    public static void main(String[] args) throws InterruptedException
    {
        
    	TiledTermPanel term = TiledTermPanel.getFramedTerminal("Jade Rogue");
        
        term.registerTile("dungeon.png", 5, 59, ColoredChar.create('#'));
        term.registerTile("dungeon.png", 3, 60, ColoredChar.create('.'));
        term.registerTile("dungeon.png", 5, 20, ColoredChar.create('@'));
        term.registerTile("dungeon.png", 14, 30, ColoredChar.create('D', Color.red));
        
        Player player = new Player(term);
        
    	
    	/*
        AsciiScreenInput asciiScreenInput = new AsciiScreenInput("res/ascii_screens/start_screen.txt");
        AsciiScreenActor asciiScreenActor = new AsciiScreenActor(ColoredChar.create(' '), term);
        World world = new AsciiScreen(asciiScreenInput, asciiScreenActor);
        */
        
        World world = new Level(69, 24, player);
        
        world.addActor(new Monster(ColoredChar.create('D', Color.red)));
        term.registerCamera(player, 5, 5);
        
//        while(!asciiScreenActor.expired())
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
