package gtr.actor.item;

import gtr.util.DropType;
import gtr.util.WeaponType;

import java.util.Random;

import jade.util.datatype.ColoredChar;

public class Ammo extends Item {
	private String type;
	
	
	public Ammo(ColoredChar face, DropType dropType) {
		super(face);
		setAmmoType(dropType);
		setName("Munition (" + type + ")");
		
		Random randomGenerator = new Random();
		setCount(randomGenerator.nextInt(29) + 11);
	}

	public Ammo() {
		this(ColoredChar.create('a'), DropType.common);
	}
	
	public Ammo(DropType dropType) {
		this(ColoredChar.create('a'), dropType);
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
	
	private void setAmmoType(DropType dropType){
		setType(WeaponType.getRandom(dropType).toString());
	}
	
	private void setType(String type){
		if (type.equals("melee"))
			type = "light";
		this.type = type;
	}
	
	public boolean isEquippable() {
		return true;
	}
	
	public String getDescription(){
		return "+20 " + getType() + " Munition" ;
	}
}
