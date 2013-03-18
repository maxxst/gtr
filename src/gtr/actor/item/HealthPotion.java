package gtr.actor.item;

import rogue.creature.Creature;
import jade.util.datatype.ColoredChar;

public class HealthPotion extends Potion {
	private int heals = 10;
	
	public HealthPotion(ColoredChar face) {
		super(face);
		setName("Heiltrank");
	}

	public HealthPotion(Creature creature) {
		this();
		attach(creature);
		
	}
	public HealthPotion() {
		this(ColoredChar.create('¿'));
	}
	
	public void use(){
		if(holder() != null)
			((Creature) holder()).addHp(heals);
		super.use();
	}

}
