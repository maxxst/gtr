package gtr.util;

import java.util.Random;

public enum ItemType {
	Ammo, HealthPotion, Weapon;
	
	public static ItemType getRandomItemType(){
		Random randomGenerator = new Random();
		float p = randomGenerator.nextFloat();
		if(p < 0.70F){
			return Ammo;
		} else if(p < 0.9F){
			return HealthPotion;
		} else {
			return Weapon;
		}
	}
}
