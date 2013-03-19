package gtr.asciiscreen.level;

import gtr.asciiscreen.AsciiScreen;
import gtr.asciiscreen.ScreenType;

public abstract class Level extends AsciiScreen {
	
	protected static final char standardWall = '#';
	protected static final char exit = '▒';
	
	public Level(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
		
		screenType = ScreenType.Level;
	}
	
	public static char getExitChar() {
		return exit;
	}

}
