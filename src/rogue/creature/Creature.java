package rogue.creature;

import java.util.Random;

import gtr.actor.fading.DeadBody;
import gtr.actor.item.weapon.Weapon;
import gtr.actor.item.weapon.Weapon.Range;
import jade.core.Actor;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Coordinate;
import jade.util.datatype.Direction;

public abstract class Creature extends Actor
{
    public Creature(ColoredChar face)
    {
        super(face);
    }

    @Override
    public void setPos(int x, int y)
    {
        if(world().passableAt(x, y))
            super.setPos(x, y);
    }
    
    public void attack(Direction dir,  Weapon weapon){
    	attack(dir, weapon, 0.6F);
    }
    
    
    public void attack(Direction dir, Weapon weapon, float hitProb){
    	weapon.use(dir, hitProb);
    }
    
    public void die(){
    	world().addActor(new DeadBody(), x(), y());
    	world().removeActor(this);
    }
    
    /**
	 * Easy method that handles what happens if someone is attacked
	 * @return isBreaktrough
	 */
	public boolean getDamage(){
		die();
		return false;
	}
	
	public String attackText(){
		return "Creature attacks";
	}
	
	public String hitText(){
		return "Creature hits";
	}
	
	public String missText(){
		return "Creature misses";
	}
	
}
