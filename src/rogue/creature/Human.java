package rogue.creature;

import java.util.ArrayList;

import jade.util.datatype.ColoredChar;

public class Human extends Creature {

	protected ArrayList<String> messages;
	
	public Human(ColoredChar face, ArrayList<String> messages) {
		super(face);
		this.messages = messages;
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}
}
