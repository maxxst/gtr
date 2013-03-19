package gtr.asciiscreen.other;

import java.util.ArrayList;

import jade.ui.TermPanel;
import jade.util.datatype.ColoredChar;
import gtr.asciiscreen.AsciiScreen;

public abstract class OtherScreen extends AsciiScreen {

	public OtherScreen(int width, int height) {
		super(width, height);
		// TODO Auto-generated constructor stub
	}
	
	protected void setPlayerOnScreen(ArrayList<String> leveldesign) {
		int x = TermPanel.DEFAULT_COLS / 2;
		int y = TermPanel.DEFAULT_ROWS / 2;
		player.setFace(ColoredChar.create(leveldesign.get(y).charAt(x)));
		addActor(player, x, y);
	}

}
