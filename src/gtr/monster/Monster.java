package gtr.monster;

import java.util.Arrays;

import jade.util.Dice;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Direction;
import rogue.creature.Creature;


/**
 * Monster class creates a generic dumb monster
 * 
 * 
 * @author mxst
 */
public class Monster extends Creature {

	private String name;
	
	
	public Monster(ColoredChar face) {
		super(face);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void act() {
		move(Dice.global.choose(Arrays.asList(Direction.values())));
	}

}
