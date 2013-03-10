package gtr.asciiscreen.level;

import gtr.asciiscreen.AsciiScreen;
import gtr.asciiscreen.ScreenType;

public abstract class Level extends AsciiScreen {
	
	public Level(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
		
		screenType = ScreenType.Level;
	}

}
