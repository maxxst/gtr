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

/**
 * Klasse für das Inventar
 * 
 * @author anti
 * 
 */
public class Inventar {

	/*
	 * Anzahl der sichtbaren Items in der Itemliste
	 */
	private static final int itemListHeight = 6;

	/*
	 * Variablen, die nicht geändert werden dürfen
	 */
	private static final int itemList_ArrayListSize = itemListHeight * 2 + 1;
	private static int cursorAt = 0;
	private static final Coordinate cursorInRow = new Coordinate(1,
			cursorAt * 2 + 4);
	private static final ColoredChar standardCursor = ColoredChar.create('▶',
			Color.red);
	private static int width = TermPanel.DEFAULT_COLS;
	private static int height = TermPanel.DEFAULT_ROWS_WITHOUT_OUTPUT;
	private static Terminal term;
	private static ArrayList<String> itemList;
	private static String countFormat = "%03d";

	private static char equippedSign = '*';
	private static int equippedItem;

	/*
	 * ENDE: Variablen, die nicht geändert werden dürfen
	 */

	/**
	 * Legt die Itemliste an. (Damit ist nicht nur die Aufzählung der Items
	 * gemeint, sondern das ganze Aussehen der Liste (mit Formatierung der
	 * Einträge usw.)
	 * 
	 * @param player
	 *            Spieler, dessen Items angezeigt werden sollen
	 */
	private static void createItemList(Player player) {
		itemList = new ArrayList<String>();
		cursorAt = 0;
		// Erstellt Rahmen für Einträge der Liste aller Items im Inventar
		String borderTop = "╔════╦";
		while (borderTop.length() < width - 2)
			borderTop += "═";
		borderTop += "╗";

		String borderBetween = "╠════╬";
		while (borderBetween.length() < width - 2)
			borderBetween += "═";
		borderBetween += "╣";

		String borderBottom = "╚════╩";
		while (borderBottom.length() < width - 2)
			borderBottom += "═";
		borderBottom += "╝";

		itemList.add(borderTop);

		// Erstelle Aussehen der Itemliste
		for (int i = 0; i < player.getItems().size(); i++) {
			Item item = player.getItems().get(i);
			String lineWithItem = "║";
			char c = player.getWeapon().getName().equals(item.getName()) ? equippedSign
					: ' ';
			if (c == equippedSign)
				equippedItem = i;
			lineWithItem += " " + String.format(countFormat, item.getCount())
					+ "║" + item.getName();
			while (lineWithItem.length() < width - 2)
				lineWithItem += " ";
			lineWithItem += "║" + Character.toString(c);

			itemList.add(lineWithItem);
			itemList.add(borderBetween);
		}
		itemList.set(itemList.size() - 1, borderBottom);
	}

	/**
	 * Erstellt den Inventarbildschirm
	 * 
	 * @param term
	 *            Terminal, wo man sich befindet
	 * @param player
	 *            Spieler, dessen Inventar angezeigt werden soll
	 * @return Aussehen des Inventars
	 */
	private static ArrayList<String> createInventoryScreen(Terminal term,
			Player player) {

		term.clearBuffer();

		gtr.asciiscreen.other.Inventar.term = term;
		ArrayList<String> inventoryScreen = new ArrayList<String>();
		createItemList(player);

		inventoryScreen.add("Inventar"); // erste Zeile des Inventars
		inventoryScreen.add(""); // zweite Zeile des Inventars

		String tableHead = " Anz. Item";
		while (tableHead.length() < width)
			tableHead += " ";

		inventoryScreen.add(tableHead);

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

		while (inventoryScreen.size() < height - 2)
			inventoryScreen.add("");

		inventoryScreen.add("Tastenbelegung:");
		inventoryScreen
				.add("w: ↑ | s: ↓ | Eingabe oder Enter: Item benutzen | r: Inventar verlassen");

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

	/**
	 * Funktion, die benutzt wird, um das Inventar aufzurufen.
	 * 
	 * @param term
	 * @param player
	 */
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
					case 'w':
						if (cursorAt >= 1) {
							cursorAt -= 1;
							showItemList();
						}

						break;
					case 's':
						if (cursorAt < player.getItems().size() - 1) {
							cursorAt += 1;
							showItemList();
						}
						break;
					case KeyEvent.VK_ENTER:
						Item selectedItem = player.getItems().get(cursorAt);
						player.selectItem(selectedItem.getName());

						int p = cursorAt * 2 + 1; // Index ausgewähltes Item in
													// itemList<String>

						if (selectedItem.getCount() != 0) {
							String s = itemList.get(p);

							String updatedLine = s.substring(0, 2)
									+ String.format(countFormat,
											selectedItem.getCount())
									+ s.substring(5, s.length() - 1);
							if (selectedItem.getClass().getSimpleName()
									.equals("Weapon")
									&& s.charAt(width - 1) != equippedSign) {

								String formerlyEquipped = itemList
										.get(equippedItem * 2 + 1);
								formerlyEquipped = formerlyEquipped.substring(
										0, formerlyEquipped.length() - 1) + " ";
								itemList.set(equippedItem * 2 + 1,
										formerlyEquipped);
								
								updatedLine += Character.toString(equippedSign);
							}

							else
								updatedLine += " ";

							itemList.set(p, updatedLine);
						} else {
							itemList.remove(p);
							itemList.remove(p);
						}

						showItemList();

					default:
						break;

					}

					key = 0;

				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Zeigt die Itemliste an. Wird immer wieder aufgerufen, wenn man in der
	 * Liste scrollt, da ja nur ein Teil der Itemliste angezeigt wird.
	 */
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
					term.bufferChar(cursorInRow, standardCursor);
				}
				term.bufferChar(x, y + 3, coloredChar);
			}
		term.bufferChar(cursorInRow, standardCursor);
		term.refreshScreen();
	}
}
