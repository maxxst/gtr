package gtr.asciiscreen.other;

import jade.ui.TermPanel;
import jade.ui.Terminal;
import jade.util.datatype.ColoredChar;

import java.util.ArrayList;
import java.util.List;

import rogue.creature.Player;

import gtr.keys.Key;
import gtr.util.datatype.Location;

public class Help extends OtherScreen {

	private static ArrayList<String> helpScreen;
	private static Terminal term;
	private static int firstShownLine = 0;

	public Help(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Location inLevel() {
		// TODO Auto-generated method stub
		return null;
	}

	private static void createHelp() {
		helpScreen = new ArrayList<String>();
		helpScreen.add("Hilfe");
		helpScreen.add("");
		helpScreen.add("Laufen");
		helpScreen.add("");
		helpScreen.add("q: ↖");
		helpScreen.add("w: ↑");
		helpScreen.add("e: ↗");
		helpScreen.add("a: ←");
		helpScreen.add("s: ↓");
		helpScreen.add("d: →");
		helpScreen.add("y, z: ↙");
		helpScreen.add("c: ↘");
		helpScreen.add("");
		helpScreen.add("Schießen");
		helpScreen.add("");
		helpScreen.add("Leertaste, dann q: ↖");
		helpScreen.add("Leertaste, dann w: ↑");
		helpScreen.add("Leertaste, dann e: ↗");
		helpScreen.add("Leertaste, dann a: ←");
		helpScreen.add("Leertaste, dann s: ↓");
		helpScreen.add("Leertaste, dann d: →");
		helpScreen.add("Leertaste, dann y oder z: ↙");
		helpScreen.add("Leertaste, dann c: ↘");
		helpScreen.add("");
		helpScreen.add("Inventar");
		helpScreen.add("");
		helpScreen.add(Character.toString(Key.openInventoryKey) + ": Inventar öffnen, Inventar schließen");
		helpScreen.add("w: ↑ in der Itemliste");
		helpScreen.add("s: ↓ in der Itemliste");
		helpScreen.add("Enter, Eingabe, Leertaste: Item benutzen");
		helpScreen.add("");
		helpScreen.add("Mit Personen reden");
		helpScreen.add("");
		helpScreen.add("„!“ und „?“ sind Personen, mit denen gesprochen werden kann.");
		helpScreen.add("");
		helpScreen.add("p, dann q: Person ↖ von dir ansprechen");
		helpScreen.add("p, dann w: Person ↑ von dir ansprechen");
		helpScreen.add("p, dann e: Person ↗ von dir ansprechen");
		helpScreen.add("p, dann a: Person ← von dir ansprechen");
		helpScreen.add("p, dann s: Person ↓ von dir ansprechen");
		helpScreen.add("p, dann d: Person → von dir ansprechen");
		helpScreen.add("p, dann y oder z: Person ↙ von dir ansprechen");
		helpScreen.add("p, dann c: Person ↘ von dir ansprechen");
		
		helpScreen.add("###");
		helpScreen.add("###");
		helpScreen.add("###");

		while (helpScreen.size() < TermPanel.DEFAULT_ROWS_WITHOUT_OUTPUT)
			helpScreen.add("");

		for (int i = 0; i < helpScreen.size(); i++)
			while (helpScreen.get(i).length() < TermPanel.DEFAULT_COLS)
				helpScreen.set(i, helpScreen.get(i) + " ");
	}

	private static List<String> createHelpScreen(Terminal term, Player player) {

		term.clearBuffer();

		gtr.asciiscreen.other.Help.term = term;
		createHelp();

		return helpScreen.subList(0, TermPanel.DEFAULT_ROWS_WITHOUT_OUTPUT);

	}

	private static void showHelpScreen() {
		term.clearBuffer();
		for (int y = 0; y < TermPanel.DEFAULT_ROWS_WITHOUT_OUTPUT; y++)
			for (int x = 0; x < TermPanel.DEFAULT_COLS; x++) {
				ColoredChar coloredChar = null;
				try {
					coloredChar = ColoredChar.create(helpScreen.get(
							y + firstShownLine).charAt(x));
				} catch (IndexOutOfBoundsException e) {
					// Wenn auf einen Index zugegriffen wird, den es nicht gibt
					// (weil man dem Kartenrand zu nah kommt), wird an dieser
					// Stelle ein Leerzeichen angezeigt.
					coloredChar = ColoredChar.create(' ');
				}
				term.bufferChar(x, y, coloredChar);
			}
		term.refreshScreen();

	}

	public static void showHelp(Terminal term, Player player) {
		gtr.asciiscreen.AsciiScreen.showAsciiScreen(

		createHelpScreen(term, player), player.world(), term);
		term.refreshScreen();

		try {

			char key = 0;

			while (key == 0) {

				key = term.getKey();

				if (key == gtr.keys.Key.help)
					break;
				else {

					switch (key) {
					case 'w':
						if (firstShownLine > 0) {
							firstShownLine -= 1;
							showHelpScreen();
						}
						break;
					case 's':
						if (firstShownLine < helpScreen.size()
								- TermPanel.DEFAULT_ROWS_WITHOUT_OUTPUT) {
							firstShownLine += 1;
							showHelpScreen();
						}
						break;

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
}
