package gtr.actor.item;

import jade.core.Actor;
import jade.util.datatype.ColoredChar;

public class Item extends Actor{

	public Item(ColoredChar face) {
		super(face);
		// TODO Auto-generated constructor stub
	}
	
	public Item(){
		super(ColoredChar.create('i'));
	}

	@Override
	public void act() {
	}

}
