package gtr.actor.item;

import jade.core.Actor;
import jade.util.datatype.ColoredChar;

/**
 * Basic item form which weapons, potions and other things can extend
 * @author maxx
 * @version 0.1
 */
public class Item extends Actor{
    
	private String name;
	private int count;
	
	public Item(ColoredChar face) {
		super(face);
		// TODO Auto-generated constructor stub
	}
	
	public Item(){
		this(ColoredChar.create('i'));
	}

	@Override
	public void act() {
	}
	
	public String toString() {
		return this.getName();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void add(Item item){
		this.count += item.getCount();
	}
	
	public int getCount(){
		return count;
	}

}
