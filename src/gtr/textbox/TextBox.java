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
		
		String s29 = term.stringAt(new Coordinate(0, 28), TermPanel.DEFAULT_COLS);
		String s30 = term.stringAt(new Coordinate(0, 29), TermPanel.DEFAULT_COLS);
		String s31 = term.stringAt(new Coordinate(0, 30), TermPanel.DEFAULT_COLS);
		
		term.bufferString(new Coordinate(0, 27), s29);
		term.bufferString(new Coordinate(0, 28), s30);
		term.bufferString(new Coordinate(0, 29), s31);
		term.bufferString(new Coordinate(0, 30), textWithSpaces);
		
		term.refreshScreen();
	}
}
