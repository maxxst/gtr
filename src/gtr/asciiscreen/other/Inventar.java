package gtr.asciiscreen.other;

import gtr.actor.item.Item;
import jade.ui.TermPanel;
import jade.ui.Terminal;
import jade.util.datatype.ColoredChar;

import java.util.ArrayList;

import rogue.creature.Player;

public class Inventar {

	private static final int differentItemsToSee = 4;
	private static int chosenItem = 1;

	private static ArrayList<String> createInventoryScreen(Terminal term,
			Player player) {
		term.clearBuffer();

		int width = TermPanel.DEFAULT_COLS;
		int height = TermPanel.DEFAULT_ROWS;

		ArrayList<String> inventoryScreen = new ArrayList<String>();

		inventoryScreen.add("Inventar"); // erste Zeile des Inventars
		inventoryScreen.add(""); // zweite Zeile des Inventars

		// Erstellt Rahmen für Einträge der Liste aller Items im Inventar
		String borderTop = "╔════╦";
		while (borderTop.length() < width - 1)
			borderTop += "═";
		borderTop += "╗";

		String borderBetween = "╠════╬";
		while (borderBetween.length() < width - 1)
			borderBetween += "═";
		borderBetween += "╣";

		String borderBottom = "╚════╩";
		while (borderBottom.length() < width - 1)
			borderBottom += "═";
		borderBottom += "╝";

		inventoryScreen.add(borderTop);

		boolean itemListTooBig = player.getItems().size() > differentItemsToSee ? true : false;
		int listHeight = itemListTooBig ? differentItemsToSee
				: player.getItems().size();

		for (int i = 0; i < listHeight; i++) {
			Item item = player.getItems().get(i);
			String lineWithItem = "║";
			lineWithItem += " " + String.format("%03d", item.getCount()) + "║"
					+ item.getName();
			while (lineWithItem.length() < width - 1)
				lineWithItem += " ";
			lineWithItem += "║";

			inventoryScreen.add(lineWithItem);
			inventoryScreen.add(borderBetween);
		}
		
		

		if (!itemListTooBig)
			inventoryScreen.set(inventoryScreen.size() - 1, borderBottom);

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
