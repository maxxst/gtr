package gtr.actor.item;

import gtr.util.DropType;
import rogue.creature.Creature;
import jade.util.datatype.ColoredChar;

/**
 * Klasse für Heiltränke
 * @author maxx
 *
 */
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
	
	public HealthPotion(DropType dropType) {
		this(ColoredChar.create('¿'));
		// TODO Auto-generated constructor stub
	}

	/**
	 * Heiltrank verwenden
	 */
	public void use(){
		if(holder() != null)
			((Creature) holder()).addHp(heals);
		super.use();
	}
	
	public String getDescription(){
		return "+ " + heals + " Lebenspunkte";
	}
}
