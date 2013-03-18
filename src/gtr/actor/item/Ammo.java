package gtr.actor.item;

import gtr.actor.item.weapon.Weapon;
import java.util.Random;

import jade.util.datatype.ColoredChar;

public class Ammo extends Item {
	private String type;
	
	public Ammo(ColoredChar face, Weapon weapon) {
		super(face);
		Random randomGenerator = new Random();
		setCount(randomGenerator.nextInt(99) + 1);
		type = weapon.getType();
	}

	public Ammo(Weapon weapon) {
		this(ColoredChar.create('a'), weapon);
	}
	
	public String getType(){
		return type;
	}
	
	public void addAmmo(int n){
		setCount(getCount() + n);
	}
	
	public void useAmmo(){
		use();
	}
	
	public boolean shoot(){
		useAmmo();
		return getAmmo() >= 0;
	}
	
	public int getAmmo(){
		return getCount();
	}
	
	public void add(Ammo ammo){
		addAmmo(ammo.getAmmo());
	}
	
	public boolean equals(Ammo ammo){
		return this.getType() == ammo.getType();
		
	}

}
