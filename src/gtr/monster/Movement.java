package gtr.monster;

import java.util.HashMap;

public class Movement {
	private int start;
	private int multiplikator;
	
	Movement(int start, int multiplikator){
		this.start = start;
		this.multiplikator = multiplikator;
	}
	
	public Movement(HashMap<?, ?> movement) {
		start = (Integer) movement.get("start");
		multiplikator = (Integer) movement.get("multiplikator");
	}

	public int getStart(){
		return this.start;
	}
	
	public int getMultiplikator(){
		return this.multiplikator;
	}
	
	public int getMove(int level){
		return multiplikator * start * level;
	}
}
