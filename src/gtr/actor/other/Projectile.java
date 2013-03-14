package gtr.actor.other;

import java.awt.Color;
import java.util.ArrayList;

import rogue.creature.Creature;

import gtr.item.weapon.Weapon;
import jade.core.Actor;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Coordinate;
import jade.util.datatype.Direction;

public class Projectile extends Actor {
	
	private Weapon weapon;
	Direction dir;
	
	public Projectile(Direction dir, Weapon fromWeapon) {
		this(ColoredChar.create(arrowForDirection(dir), new Color(100,149,237)), dir, fromWeapon);
	}
	
	public Projectile(ColoredChar face, Direction dir, Weapon fromWeapon) {
		super(face);
		this.dir = dir;
		this.weapon = fromWeapon;
	}

	@Override
	public void act() {
		int steps = weapon.getRange().getTo();
		while(steps >= 0){
			Coordinate coord = new Coordinate(x()+ dir.dx(), y()+dir.dy());
			if(world.passableAt(coord)){
				move(dir);
			} else {
				if(!impact(coord)) //if projectile goes not through
					break;
			}
			
			steps--;
		}

	}
	
	private boolean impact(Coordinate coord) {
		Creature creature;
		if (weapon.isArea()) {
			for(Direction dir: Direction.values()){
				creature = world.getActorAt(Creature.class, coord.getTranslated(dir));
				if(creature != null)
					creature.getDamage();
			}
		} else {
			creature = world.getActorAt(Creature.class, coord.getTranslated(dir));
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
