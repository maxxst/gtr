package rogue.level;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AsciiScreenInput {

	private ArrayList<String> AsciiScreen;
	private int width;
	private int height;

	public AsciiScreenInput(String _url) {
		width = Integer.MIN_VALUE;

		// Lese Datei ein
		AsciiScreen = new ArrayList<String>();
		try {

			BufferedReader bufferedReader = new BufferedReader(new FileReader(
					_url));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				AsciiScreen.add(line);
				if (line.length() > width)
					width = line.length();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Fülle zu kurze linen mit Leerzeichen auf, damit alle Zeilen gleich
		// lang sind
		for (int i = 0; i < AsciiScreen.size(); i++) {
			while (AsciiScreen.get(i).length() < width)
				AsciiScreen.set(i, AsciiScreen.get(i) + " ");
		}
		
		height = AsciiScreen.size();
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public ArrayList<String> getScreen() {
		return AsciiScreen;
	}

	

}
