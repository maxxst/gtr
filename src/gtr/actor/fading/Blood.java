package gtr.actor.fading;

import java.awt.Color;

import jade.util.datatype.ColoredChar;

/**
 * Places playerblood on the map
 * @author maxx
 */
public class Blood extends Fading {
	
	public Blood() {
		super(ColoredChar.create('☍', new Color(139,0,0)));
	}

}
