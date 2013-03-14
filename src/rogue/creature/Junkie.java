package rogue.creature;

import jade.util.datatype.ColoredChar;

import java.awt.Color;

public class Junkie extends Monster {
	public Junkie() {
		super(ColoredChar.create('J', new Color(0,200,0)));
		// TODO Auto-generated constructor stub
	}
	
	public void act() {
		if(player.x()<x())
		{move(-1,0);}
		else
		{move(1,0);}
			
	}

}
