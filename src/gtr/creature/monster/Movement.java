package gtr.creature.monster;

import java.util.HashMap;

public class Movement {
	private int start;
	private int multiplikator;
	
	Movement(int start, int multiplikator){
		this.start = start;
		this.multiplikator = multiplikator;
	}
	
	public Movement(HashMap<?, ?> movement) {
		start = Integer.parseInt((String) movement.get("start"));
		multiplikator = Integer.parseInt((String) movement.get("multiplikator"));
	}

	public int getStart(){
		return this.start;
	}
	
	public int getMultiplikator(){
		return this.multiplikator;
	}
	
	/**
	 * 
	 * @param level der level in dem wir gerade sind
	 * @return wie viele Felder gelaufen werden duerfen
	 */
	public int getMove(int level){
		return multiplikator * start * level;
	}
}
