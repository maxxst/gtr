package gtr.textbox;

import jade.ui.TermPanel;
import jade.ui.Terminal;
import jade.util.datatype.Coordinate;

public class TextBox {
	
	private static String horizontalLine = createHorizontalLine();
	
	public static String getHorizontalLine() {
		return horizontalLine;
	}
	
	public static void displayText(String text, Terminal term) {
		displayText(term, text);
	}
	
	private static String createHorizontalLine() {
		char horChar = '-';
		String horLine = Character.toString(horChar);
		while (horLine.length() < TermPanel.DEFAULT_COLS)
			horLine += Character.toString(horChar);
		return horLine;
	}

	public static void displayText(Terminal term, String text) {
		
		String textWithSpaces = text;
		while (textWithSpaces.length() < TermPanel.DEFAULT_COLS)
			textWithSpaces += " ";
		
		
		String s30 = term.stringAt(new Coordinate(0, 29), TermPanel.DEFAULT_COLS);
		String s31 = term.stringAt(new Coordinate(0, 30), TermPanel.DEFAULT_COLS);
		String s32 = term.stringAt(new Coordinate(0, 31), TermPanel.DEFAULT_COLS);
		
		term.bufferString(new Coordinate(0, 28), s30);
		term.bufferString(new Coordinate(0, 29), s31);
		term.bufferString(new Coordinate(0, 30), s32);
		term.bufferString(new Coordinate(0, 31), textWithSpaces);
		
		term.refreshScreen();
	}
	
	public static void displayEventText(Terminal term, String text) {
		
		String textWithSpaces = text;
		while (textWithSpaces.length() < TermPanel.DEFAULT_COLS)
			textWithSpaces += " ";
		
		String s35 = term.stringAt(new Coordinate(0, 34), TermPanel.DEFAULT_COLS);
		String s36 = term.stringAt(new Coordinate(0, 35), TermPanel.DEFAULT_COLS);
		String s37 = term.stringAt(new Coordinate(0, 36), TermPanel.DEFAULT_COLS);
		
		term.bufferString(new Coordinate(0, 33), s35);
		term.bufferString(new Coordinate(0, 34), s36);
		term.bufferString(new Coordinate(0, 35), s37);
		term.bufferString(new Coordinate(0, 36), textWithSpaces);
		
		term.refreshScreen();
	}
}
