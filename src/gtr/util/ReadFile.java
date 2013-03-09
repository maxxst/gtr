package gtr.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {

	/**
	 * Liest eine Textdatei ein, in der das Aussehen der Karte geschrieben
	 * steht.
	 * 
	 * @param file
	 *            Dateipfad zur Datei
	 * @return Eine
	 */
	public static ArrayList<String> readScreenFile(String file) {

		int width = Integer.MIN_VALUE;

		// Lese Datei ein
		ArrayList<String> asciiScreen = new ArrayList<String>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(
					file));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				asciiScreen.add(line);
				if (line.length() > width)
					width = line.length();
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Fülle zu kurze Zeilen mit Leerzeichen auf, damit alle Zeilen gleich
		// lang sind
		for (int i = 0; i < asciiScreen.size(); i++) {
			while (asciiScreen.get(i).length() < width)
				asciiScreen.set(i, asciiScreen.get(i) + " ");
		}

		return asciiScreen;
	}
}
