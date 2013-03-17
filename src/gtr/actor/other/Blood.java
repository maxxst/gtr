package gtr.actor.other;

import java.awt.Color;

import jade.core.Actor;
import jade.util.datatype.ColoredChar;

/**
 * Places playerblood on the map
 * @author maxx
 *
 * 
 */
public class Blood extends Actor {

	public Blood() {
		super(ColoredChar.create('☍', new Color(139,0,0)));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void act() {
		// TODO make it disappear after a while
		
	}

}
