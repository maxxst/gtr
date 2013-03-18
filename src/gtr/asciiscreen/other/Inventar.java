package gtr.asciiscreen.other;

import jade.ui.TermPanel;
import jade.ui.Terminal;

import java.util.ArrayList;

import rogue.creature.Player;

public class Inventar {

	// private static ArrayList<String> inventoryScreen = new
	// ArrayList<String>();

	private static ArrayList<String> createInventoryScreen(Terminal term,
			Player player) {
		term.clearBuffer();

		int width = TermPanel.DEFAULT_COLS;
		int height = TermPanel.DEFAULT_ROWS;

		ArrayList<String> inventoryScreen = new ArrayList<String>();

		inventoryScreen.add("Inventar"); // erste Zeile des Inventars
		inventoryScreen.add(""); // zweite Zeile des Inventars

		// Erstellt Rahmen für Einträge der Liste aller Items im Inventar
		String borderTop = "╔═══╦";
		while (borderTop.length() < width - 1)
			borderTop += "═";
		borderTop += "╗";

		String borderBetween = "╠═══╬";
		while (borderBetween.length() < width - 1)
			borderBetween += "═";
		borderBetween += "╣";

		String borderBottom = "╚═══╩";
		while (borderBottom.length() < width - 1)
			borderBottom += "═";
		borderBottom += "╝";

		inventoryScreen.add(borderTop);

		for (int i = 0; i < player.getItems().size(); i++) {
			inventoryScreen.add(player.getItems().get(i).toString());
			inventoryScreen.add(borderBetween);
		}

		inventoryScreen.add(borderBottom);

		while (inventoryScreen.size() < height)
			inventoryScreen.add("");

		for (int i = 0; i < height; i++) {
			String s = "";
			try {
				s = inventoryScreen.get(i);
			} catch (IndexOutOfBoundsException e) {
				inventoryScreen.add(s);
			}

			while (s.length() < width)
				s += " ";

			inventoryScreen.set(i, s);

		}

		return inventoryScreen;
	}

	public static void showInventory(Terminal term, Player player) {
		gtr.asciiscreen.AsciiScreen.showAsciiScreen(
				createInventoryScreen(term, player), player.world(), term);

		try {

			char key = 0;

			while (gtr.keys.Keys.isInventoryKey(key) || key == 0) {

				key = term.getKey();

				if (key == gtr.keys.Keys.getOpenInventoryKey())
					break;
				else
					key = 0;
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
