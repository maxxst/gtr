package gtr.asciiscreen;

import jade.core.World;
import jade.ui.Terminal;
import jade.util.datatype.ColoredChar;

import java.util.ArrayList;

/**
 * Extension of the world by the GTR team
 * @author anti
 *
 */
public abstract class AsciiScreen extends World {
	
	private static ArrayList<Character> floorChars = setFloorChars();
	
	public AsciiScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Sets the Chars a player can walk on
	 * @return List of accesable Characters
	 */
	private static ArrayList<Character> setFloorChars() {
		ArrayList<Character> c = new ArrayList<Character>();
		c.add('.');
		c.add('=');
		c.add('+');
		c.add('±');
		c.add('[');
		c.add(']');
		c.add('▒');
		c.add('{'); //testweise
		return c;
	}
	
	/**
	 * Finds out if a char belongs to the Floor
	 * @param char that possibly is a floorcharacter
	 * @return true if it's a floorcharacter
	 */
	private static boolean isFloorChar(char c) {
		return floorChars.contains(c);
	}

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
	}

	/**
	 * Aus einer {@code ArrayList<String>}, was das Aussehen einer Karte enthält, wird
	 * die Karte in die Welt gezeichnet.
	 * 
	 * @param leveldesign
	 * @param world
	 * @param term
	 */
	protected static void createAsciiScreen(ArrayList<String> leveldesign,
			World world, Terminal term) {
		for (int x1 = 0; x1 < getWidth(leveldesign); x1++)
			for (int y1 = 0; y1 < getHeight(leveldesign); y1++) {
				char c = leveldesign.get(y1).charAt(x1);
				boolean b = isFloorChar(c);
				world.setTile(ColoredChar.create(c), b,
						x1, y1);
			}
	}

	/**
	 * Gibt die Höhe der Karte aus.
	 * 
	 * @param leveldesign
	 *            Karte
	 * @return
	 */
	public static int getHeight(ArrayList<String> leveldesign) {
		return leveldesign.size();
	}

	/**
	 * Gibt die Breite der Karte aus.
	 * 
	 * @param leveldesign
	 *            Karte
	 * @return Breite der Karte
	 */
	public static int getWidth(ArrayList<String> leveldesign) {
		return leveldesign.get(0).length();
	}
	
	public enum LevelEnum {
		StartScreen,
		Prologue,
		Dungeon,
		Town,
		RandomRoom,
		Room0,
		Room1,
		Room2,
		Room3
	}
	
}
