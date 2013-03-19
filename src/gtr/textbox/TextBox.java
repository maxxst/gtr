package gtr.textbox;

import jade.ui.TermPanel;
import jade.ui.Terminal;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Coordinate;
import rogue.creature.Player;

public class TextBox {

	
	public static void displayText(Terminal term, String text) {
		
		String textWithSpaces = text;
		while (textWithSpaces.length() < TermPanel.DEFAULT_COLS)
			textWithSpaces += " ";
		
		String s29 = stringAt(term, new Coordinate(0, 28), TermPanel.DEFAULT_COLS);
		String s30 = stringAt(term, new Coordinate(0, 29), TermPanel.DEFAULT_COLS);
		String s31 = stringAt(term, new Coordinate(0, 30), TermPanel.DEFAULT_COLS);
		
		term.bufferString(new Coordinate(0, 27), s29);
		term.bufferString(new Coordinate(0, 28), s30);
		term.bufferString(new Coordinate(0, 29), s31);
		term.bufferString(new Coordinate(0, 30), textWithSpaces);
		
		term.refreshScreen();
	}
	
	
    public static String stringAt(Terminal term, Coordinate coord, int length) {
    	String s = "";
    	for (int i = 0; i < length; i++)
    		try {
    			s += term.charAt(coord.x() + i, coord.y()).toString();
    		} catch (NullPointerException e) {
    			s += " ";
    		}
    		
		return s;
    }
}
