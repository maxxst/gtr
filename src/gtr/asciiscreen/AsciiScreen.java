package gtr.asciiscreen;

import jade.core.World;
import jade.ui.Terminal;
import jade.util.datatype.ColoredChar;

import java.util.ArrayList;

import gtr.util.ReadFile;

public class AsciiScreen {
	
	public static void showAsciiScreen(ArrayList<String> asciiScreen, World world, Terminal term) {
		
    	//addActor(player, x, y);
//		int height = getHeight(asciiScreen);
//		int width = getWidth(asciiScreen);
		term.clearBuffer();
		for (int x1 = 0; x1 < getWidth(asciiScreen); x1++)
			for (int y1 = 0; y1 < getHeight(asciiScreen); y1++) {
				char c = asciiScreen.get(y1).charAt(x1);
				term.bufferChar(x1, y1, ColoredChar.create(c));
//				this.setTile(ColoredChar.create(c),false, x1, y1);
			}
//			char c = asciiScreen.getScreen().get(1).charAt(1);
//			this.setTile(ColoredChar.create(c), true, 1, 1);
//			term.bufferChar(1, 1, ch)
		term.bufferCameras();
		term.refreshScreen();
		
		world.tick();
	}
	
	public static int getHeight(ArrayList<String> asciiScreen) {
		return asciiScreen.size();
	}
	
	public static int getWidth(ArrayList<String> asciiScreen) {
		return asciiScreen.get(0).length();
	}
}
