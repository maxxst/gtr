package gtr.keys;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Klasse zur Tastenbelegung
 * 
 * @author anti
 * 
 */
public class Key {

	/**
	 * Tasten, die in allen Spielbildschirmen dieselbe Belegung aufweisen
	 */
	private static ArrayList<Character> universalKeys = setUniversalKeys();

	/**
	 * Tasten für das Inventarsystem
	 */
	private static ArrayList<Character> inventoryKeys = setInventoryKeys();

	/*
	 * Standardtasten
	 */
	public final static char openInventoryKey = 'i';
	public final static char reloadKey = 'r';
	public final static char healPotionKey = 'h';
	public final static char skipKey = 's';
	

	/**
	 * Alle Tasten, die eine Belegung aufweisen. (Muss von allen Klassenvariablen
	 * am Ende stehen, da auf die anderen Klassenvariablen zugegriffen wird.)
	 */
	private static ArrayList<Character> keys = setKeys();

	public static ArrayList<Character> setUniversalKeys() {
		ArrayList<Character> universalKeys = new ArrayList<Character>();
		universalKeys.add((char) KeyEvent.VK_ESCAPE);
		return universalKeys;
	}

	public static boolean isUniversalKey(char ch) {
		return universalKeys.contains(ch);
	}

	public static ArrayList<Character> setInventoryKeys() {
		ArrayList<Character> inventoryKeys = new ArrayList<Character>();
		inventoryKeys.add(openInventoryKey);
		inventoryKeys.add('b');
		return inventoryKeys;
	}

	public static boolean isInventoryKey(char ch) {
		return inventoryKeys.contains(ch);

	}

	public static ArrayList<Character> setKeys() {
		ArrayList<Character> allKeys = new ArrayList<Character>();
		allKeys.addAll(universalKeys);
		allKeys.add(openInventoryKey);
		allKeys.addAll(inventoryKeys);
		return allKeys;
	}

	public static boolean isKey(char ch) {
		return keys.contains(ch);
	}

	public static char getOpenInventoryKey() {
		return openInventoryKey;
	}
}
