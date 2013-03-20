package rogue.creature;

import java.util.ArrayList;

import jade.util.datatype.ColoredChar;

public class Human extends Creature {

	private ArrayList<String> messages;
	
	public Human(ColoredChar face, ArrayList<String> messages) {
		super(face);
		this.messages = messages;
		this.messages.set(this.messages.size() - 1, this.messages.get(this.messages.size() - 1) + " ✓");
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<String> getMessages() {
		return messages;
	}
}
