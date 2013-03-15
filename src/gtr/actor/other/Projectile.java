package gtr.actor.other;

import java.awt.Color;
import java.util.ArrayList;

import rogue.creature.Creature;

import gtr.actor.item.weapon.Weapon;
import jade.core.Actor;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Coordinate;
import jade.util.datatype.Direction;

public class Projectile extends Actor {
	
	private Weapon weapon;
	private Direction dir;
	private int stepCount;
	
	public Projectile(Direction dir, Weapon fromWeapon) {
		this(ColoredChar.create(arrowForDirection(dir), new Color(100,149,237)), dir, fromWeapon);
	}
	
	public Projectile(ColoredChar face, Direction dir, Weapon fromWeapon) {
		super(face);
		this.dir = dir;
		this.weapon = fromWeapon;
		this.stepCount = weapon.getRangeTo();
	}

	@Override
	public void act() {
		int steps = weapon.getSpeed();
		while(steps > 0){
			Coordinate coord = new Coordinate(x()+ dir.dx(), y()+dir.dy());
			if(world().passableAt(coord) && stepCount > 0 && (world().getActorAt(Creature.class, coord) == null)){
				move(dir);
			} else {
				if(!impact(coord)){ //if projectile goes not through
					world().removeActor(this);
					break;
				}
			}
			
			steps--;
		}

	}
	
	private boolean impact(Coordinate coord) {
		Creature creature;
		System.out.print("boom.");
		if (weapon.isArea()) {
			for(Direction dir: Direction.values()){
				creature = world().getActorAt(Creature.class, coord.getTranslated(dir));
				if(creature != null)
					creature.getDamage();
			}
		} else {
			creature = world().getActorAt(Creature.class, coord.getTranslated(dir));
			if(creature != null){
				return creature.getDamage();
			}
		}
		return false;
	}

	private static char arrowForDirection(Direction dir){
		switch (dir) {
		case NORTH:
			return '↑';
		case NORTHEAST:
			return '↗';
		case EAST:
			return '→';
		case SOUTHEAST:
			return '↘';
		case SOUTH:
			return '↓';
		case SOUTHWEST:
			return '↙';
		case WEST:
			return '←';
		case NORTHWEST:
			return '↖';

		default: 
			return '•';
		}
	}
}
