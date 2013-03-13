package rogue.creature;

import java.util.Random;

import gtr.item.weapon.Weapon.Range;
import jade.core.Actor;
import jade.util.datatype.ColoredChar;
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
    
    public void attack(Direction dir, Range range, float dmg){
    	attack(dir, range, dmg, 0.6F);
    }
    
    
    public void attack(Direction dir, Range range, float dmg, float hitProb){
    	Random randomGenerator = new Random();
    	for(int i=range.getFrom(); i<=range.getTo();i++){
    		if(world.getActorAt(Creature.class, x()+dir.dx()*i,y()+dir.dy()*i) != null){
				if(randomGenerator.nextFloat() < hitProb){
					System.out.println("Treffer"); //TODO Schaden zufuegen!
				} else {
					i = range.getTo() + 1;
				}
			} 
    	}
    }
}
