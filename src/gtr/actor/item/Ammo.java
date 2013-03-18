package gtr.actor.item;

import gtr.actor.item.weapon.Weapon;

import java.util.Random;

import jade.util.datatype.ColoredChar;

public class Ammo extends Item {
	private int ammo;
	public Ammo(ColoredChar face, Weapon weapon) {
		super(face);
		Random randomGenerator = new Random();
		this.ammo = randomGenerator.nextInt(99) + 1;
		this.attach(weapon);
	}

	public Ammo(Weapon weapon) {
		this(ColoredChar.create('a'), weapon);
	}
	
	public void addAmmo(int n){
		ammo += n;
	}
	
	public void useAmmo(){
		ammo -= 1;
	}
	
	public boolean shoot(){
		useAmmo();
		return getAmmo() >= 0;
	}
	
	public int getAmmo(){
		return ammo;
	}

}
