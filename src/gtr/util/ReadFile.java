package gtr.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import com.esotericsoftware.yamlbeans.YamlReader;

public class ReadFile {

	/**
	 * Liest eine Textdatei ein, in der das Aussehen der Karte geschrieben
	 * steht.
	 * 
	 * @param file
	 *            Dateipfad zur Datei
	 * @return Eine ArrayList<String>, bei der die einzelnen Strings die Zeilen
	 *         der Karte sind.
	 */
	public static ArrayList<String> readScreenFile(String file) {
		int width = Integer.MIN_VALUE;

		// Lese Datei ein
		ArrayList<String> layout = new ArrayList<String>();
		try {
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(
							ReadFile.class.getResourceAsStream(file), "UTF-8"));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				layout.add(line);
				if (line.length() > width)
					width = line.length();
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Fülle zu kurze Zeilen mit Leerzeichen auf, damit alle Zeilen gleich
		// lang sind
		for (int i = 0; i < layout.size(); i++) {
			while (layout.get(i).length() < width)
				layout.set(i, layout.get(i) + " ");
		}

		return layout;
	}

	/**
	 * Liest eine YAML-Datei ein.
	 * 
	 * @param file
	 *            relativer Pfad zur einzulesenden YAML-Datei
	 * @return Eine ArrayList<?>
	 */
	public static ArrayList<?> readYamlArrayList(String file) {

		// Lese Datei ein
		Object object = null;
		try {
			object = new YamlReader(new InputStreamReader(
					ReadFile.class.getResourceAsStream(file), "UTF-8")).read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ArrayList.class.cast(object);
	}

	public static HashMap<?, ?> readYamlHashMap(String file) {
		// Lese Datei ein
		Object object = null;
		try {
			object = new YamlReader(new InputStreamReader(
					ReadFile.class.getResourceAsStream(file), "UTF-8")).read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return HashMap.class.cast(object);
	}

	/**
	 * Liest eine YAML-Datei ein.
	 * 
	 * @param file
	 *            relativer Pfad zur einzulesenden YAML-Datei
	 * @return Eine ArrayList<?>
	 * @deprecated
	 */
	public static ArrayList<?> readYamlFile(String file) {

		// Lese Datei ein
		Object object = null;
		try {
			object = new YamlReader(new InputStreamReader(
					ReadFile.class.getResourceAsStream(file), "UTF-8")).read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ArrayList.class.cast(object);
	}
}
