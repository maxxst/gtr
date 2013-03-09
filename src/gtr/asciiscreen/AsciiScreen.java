package gtr.asciiscreen;

import jade.core.World;
import jade.ui.Terminal;
import jade.util.datatype.ColoredChar;

import java.util.ArrayList;

public class AsciiScreen {

	public static void showAsciiScreen(ArrayList<String> leveldesign,
			World world, Terminal term) {

		term.clearBuffer();
		for (int x1 = 0; x1 < getWidth(leveldesign); x1++)
			for (int y1 = 0; y1 < getHeight(leveldesign); y1++) {
				char c = leveldesign.get(y1).charAt(x1);
				term.bufferChar(x1, y1, ColoredChar.create(c));
			}
		term.bufferCameras();
		term.refreshScreen();

		world.tick();
	}

	public static void createAsciiScreen(ArrayList<String> leveldesign,
			World world, Terminal term) {
		for (int x1 = 0; x1 < getWidth(leveldesign); x1++)
			for (int y1 = 0; y1 < getHeight(leveldesign); y1++) {
				char c = leveldesign.get(y1).charAt(x1);
				world.setTile(ColoredChar.create(c), (c == '#') ? false : true, x1, y1);
			}
	}

	public static int getHeight(ArrayList<String> leveldesign) {
		return leveldesign.size();
	}

	public static int getWidth(ArrayList<String> leveldesign) {
		return leveldesign.get(0).length();
	}
}
