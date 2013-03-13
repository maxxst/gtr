package gtr.keys;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Keys {

	private static ArrayList<Character> keys = setUniversalKeys();
	
	public static ArrayList<Character> setUniversalKeys() {
		keys = new ArrayList<Character>();
		keys.add((char)KeyEvent.VK_ESCAPE);
		return keys;
	}
	
	public static boolean isUniversalKey(char ch) {
		return keys.contains(ch);
	}
}
