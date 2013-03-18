package gtr.keys;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Keys {

	private static ArrayList<Character> keys = setUniversalKeys();
	private static ArrayList<Character> inventoryKeys = setInventoryKeys();
	
	public static ArrayList<Character> setUniversalKeys() {
		keys = new ArrayList<Character>();
		keys.add((char)KeyEvent.VK_ESCAPE);
		return keys;
	}
	
	public static boolean isUniversalKey(char ch) {
		return keys.contains(ch);
	}
	
	public static ArrayList<Character> setInventoryKeys() {
		inventoryKeys = new ArrayList<Character>();
		inventoryKeys.add('<');
		inventoryKeys.add('b');
		return inventoryKeys;
	}
	
	public static boolean isInventoryKey(char ch) {
		return inventoryKeys.contains(ch);
		
	}
}
