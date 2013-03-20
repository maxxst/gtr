package rogue.creature;

import gtr.actor.fading.DeadBody;
import gtr.actor.item.Weapon;
import jade.core.Actor;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Direction;

public abstract class Creature extends Actor
{
	private int hp = 1;
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
    	expire();
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

	public int getHp() {
		return hp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}

	public void addHp(int hp) {
		this.hp += hp;
	}
	
	public void loseHp(int hp) {
		this.hp -= hp;
	}
	
}
