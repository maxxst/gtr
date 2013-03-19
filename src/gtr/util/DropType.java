package gtr.util;

import java.util.Random;

public enum DropType {
	common, uncommon, rare, mythic;
	
	public static DropType getRandomDropType(){
		Random randomGenerator = new Random();
		float p = randomGenerator.nextFloat();
		if(p < 0.90F){
			return common;
		} else if(p < 0.98F){
			return uncommon;
		} else if(p < 0.99F){
			return rare;
		} else {
			return mythic;
		}
	}
	
	
}
