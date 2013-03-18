package gtr.asciiscreen.other;

import jade.ui.TermPanel;
import jade.ui.Terminal;

import java.util.ArrayList;

import rogue.creature.Player;

public class Inventar {

	private static ArrayList<String> inventarScreen = new ArrayList<String>();
	
	
	private static ArrayList<String> createInventarScreen(Terminal term, Player player) {
		term.clearBuffer();
		
		int width = TermPanel.DEFAULT_COLS;
		int height = TermPanel.DEFAULT_ROWS;
		
		ArrayList<String> inventarScreen = new ArrayList<String>();
		
		inventarScreen.add("Inventar");
		inventarScreen.add("");
		
		for (int i = 0; i < player.getItems().size(); i++) {
			inventarScreen.add(player.getItems().get(i).toString());
		}
		
		for (int i = 0; i < height; i++) {
			String s = "";
			try {
				s = inventarScreen.get(i);
			}
			catch (IndexOutOfBoundsException e) { 
				inventarScreen.add(s);
			}
			
			while (s.length() < width)
				s += " ";
			
			inventarScreen.set(i, s);
			
			
		}
		
		System.out.print(inventarScreen.size());
		
		return inventarScreen;
	}
	
	public static ArrayList<String> getInventarScreen(Terminal term, Player player) {
		return createInventarScreen(term, player);
	}
	
	
	
}
