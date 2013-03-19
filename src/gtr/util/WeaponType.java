package gtr.util;

import java.util.Random;

public enum WeaponType {
	melee, light, middle, heavy;
	
	public static WeaponType getRandom(DropType dropType){
		Random randomGenerator = new Random();
		float p = randomGenerator.nextFloat();
		switch(dropType){
		case common:
			break;
		case mythic:
			p += 0.7F;
			break;
		case rare:
			p += 0.5F;
			break;
		case uncommon:
			p += 0.2F;
			break;
		}
		return getRandomHelper(p);
	}

	public static WeaponType getRandom() {
		Random randomGenerator = new Random();
		float p = randomGenerator.nextFloat();
		return getRandomHelper(p);
	}
	
	private static WeaponType getRandomHelper(float p){
		if(p < 0.1F){
			return melee;
		} else if(p < 0.55F){
			return light;
		} else if(p < 0.85){
			return middle;
		} else {
			return heavy;
		}
	}
}
