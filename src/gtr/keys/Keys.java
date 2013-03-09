package gtr.keys;

import java.util.ArrayList;

public class Keys {

	private static ArrayList<Character> keys = new ArrayList<Character>();
	
	public static ArrayList<Character> setUniversalKeys() {
		keys = new ArrayList<Character>();
		keys.add('q');
		return keys;
	}
	
	public static boolean isUniversalKey(char ch) {
		return keys.contains(ch);
	}
}
