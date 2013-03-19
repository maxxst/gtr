package gtr.actor.item;

import gtr.actor.item.weapon.Weapon;
import gtr.util.DropType;
import gtr.util.WeaponType;

import java.util.Random;

import jade.util.datatype.ColoredChar;

public class Ammo extends Item {
	private String type;
	
	public Ammo(ColoredChar face) {
		super(face);
		setAmmoType();
	}
	
	public Ammo(ColoredChar face, DropType dropType) {
		super(face);
		setAmmoType(dropType);
	}

	public Ammo() {
		this(ColoredChar.create('a'));
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
	private void setAmmoType(){
		setType(WeaponType.getRandom().toString());
	}
	
	private void setType(String type){
		if (type.equals("melee"))
			type = "light";
		this.type = type;
	}

}
