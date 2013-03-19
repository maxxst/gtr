package gtr.asciiscreen.other;

import gtr.actor.item.Item;
import jade.ui.TermPanel;
import jade.ui.Terminal;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Coordinate;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import rogue.creature.Player;

public class Inventar {

	private static final int itemListHeight = 4;
	private static final int itemList_ArrayListSize = itemListHeight * 2 + 1;

	private static int cursorAt = 1;
	private static final Coordinate cursorInRow = new Coordinate(1,
			cursorAt * 2 + 1);
	private static final ColoredChar standardCursor = ColoredChar.create('▶',
			Color.red);
	private static int width = TermPanel.DEFAULT_COLS;
	private static int height = TermPanel.DEFAULT_ROWS;
	private static Terminal term;
	private static ArrayList<String> itemList;

	private static void createItemList(Player player) {
		itemList = new ArrayList<String>();

		// Erstellt Rahmen für Einträge der Liste aller Items im Inventar
		String borderTop = "╔════╦";
		while (borderTop.length() < width - 3)
			borderTop += "═";
		borderTop += "╦═╗";

		String borderBetween = "╠════╬";
		while (borderBetween.length() < width - 3)
			borderBetween += "═";
		borderBetween += "╬═╣";

		String borderBottom = "╚════╩";
		while (borderBottom.length() < width - 3)
			borderBottom += "═";
		borderBottom += "╩═╝";

		itemList.add(borderTop);

		// Erstelle Aussehen der Itemliste
		for (int i = 0; i < player.getItems().size(); i++) {
			Item item = player.getItems().get(i);
			String lineWithItem = "║";
			lineWithItem += " " + String.format("%03d", item.getCount()) + "║"
					+ item.getName();
			while (lineWithItem.length() < width - 3)
				lineWithItem += " ";
			lineWithItem += "║" + Integer.toString(i) + "║";

			itemList.add(lineWithItem);
			itemList.add(borderBetween);
		}
		itemList.set(itemList.size() - 1, borderBottom);
	}

	private static ArrayList<String> createInventoryScreen(Terminal term,
			Player player) {

		term.clearBuffer();

		gtr.asciiscreen.other.Inventar.term = term;
		ArrayList<String> inventoryScreen = new ArrayList<String>();
		createItemList(player);

		inventoryScreen.add("Inventar"); // erste Zeile des Inventars
		inventoryScreen.add(""); // zweite Zeile des Inventars

		for (int x = 0; x < itemList_ArrayListSize; x++) {
			try {
				inventoryScreen.add(itemList.get(x));
			} catch (IndexOutOfBoundsException e) {
				inventoryScreen.add("");
			}
		}

		while (inventoryScreen.size() < itemList_ArrayListSize + 2)
			inventoryScreen.add("");

		showItemList();

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
		term.bufferChar(cursorInRow, standardCursor);
		term.refreshScreen();

		try {

			char key = 0;

			while (key == 0) {

				key = term.getKey();

				if (key == gtr.keys.Keys.getOpenInventoryKey())
					break;
				else {

					switch (key) {
					case 'o':
						if (cursorAt >= 1) {
							cursorAt -= 1;
							showItemList();
						}

						break;
					case 'l':
						if (cursorAt < itemListHeight) {
							cursorAt += 1;
							showItemList();
						}
						break;
					default:
						break;
					}
					System.out.println("ausgewähltes Item: " + cursorAt);
					key = 0;

				}
				// else

			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void showItemList() {
		for (int y = 0; y < itemList_ArrayListSize; y++)
			for (int x = 0; x < width; x++) {
				ColoredChar coloredChar = null;
				try {
					coloredChar = ColoredChar.create(itemList.get(
							cursorAt * 2 + y).charAt(x));
				} catch (IndexOutOfBoundsException e) {
					// Wenn auf einen Index zugegriffen wird, den es nicht gibt
					// (weil man dem Kartenrand zu nah kommt), wird an dieser
					// Stelle ein Leerzeichen angezeigt.
					coloredChar = ColoredChar.create(' ');
				}
				term.bufferChar(x, y + 2, coloredChar);
			}
		term.bufferChar(cursorInRow, standardCursor);
		term.bufferCameras();
		term.refreshScreen();
	}
}
