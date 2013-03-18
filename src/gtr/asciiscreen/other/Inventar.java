package gtr.asciiscreen.other;

import jade.core.World;
import jade.ui.TermPanel;
import jade.ui.Terminal;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import rogue.creature.Player;

public class Inventar {

	private static ArrayList<String> inventarScreen = new ArrayList<String>();

	private static ArrayList<String> createInventarScreen(Terminal term,
			Player player) {
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
			} catch (IndexOutOfBoundsException e) {
				inventarScreen.add(s);
			}

			while (s.length() < width)
				s += " ";

			inventarScreen.set(i, s);

		}

		return inventarScreen;
	}

	public static void showInventar(Terminal term, Player player) {
		gtr.asciiscreen.AsciiScreen.showAsciiScreen(
				createInventarScreen(term, player), player.world(), term);

		try {

			char key = 0;

			onInventoryScreen: while (gtr.keys.Keys.isInventoryKey(key) || key == 0) {

				key = term.getKey();
				
				switch (key) {
				case '<':

					break onInventoryScreen;

				default:
					key = 0;
					break;
				}

				
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
