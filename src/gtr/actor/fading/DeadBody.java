package gtr.actor.fading;

import gtr.actor.item.Ammo;
import gtr.actor.item.HealthPotion;
import gtr.actor.item.Item;
import gtr.actor.item.Weapon;
import gtr.util.DropType;
import gtr.util.ItemType;

import java.awt.Color;

import rogue.creature.Player;
import jade.util.datatype.ColoredChar;

/**
 * When a NPCreature dies it leaves lootable ramains 
 * @author maxx
 * 
 * @version 0.1
 */
public class DeadBody extends Fading {
	//ArrayList<Item> loot = new ArrayList<Item>();
	Item drop;
	
	public DeadBody() {
		this(DropType.getRandomDropType());
	    turnsLeft = 20;
		// TODO add itemdrop
	}
	
	public DeadBody(DropType dropType){
		super(ColoredChar.create('%', new Color(139,0,0)));
		switch(ItemType.getRandomItemType()){
		case Ammo:
			drop = new Ammo(dropType);
			break;
		case HealthPotion:
			drop = new HealthPotion(dropType);
			break;
		case Weapon:
			drop = new Weapon(dropType);
			break;
		default:
			drop = null;
			break;
		
		}
	}
	
	@Override
	public void act() {
		if(!loot())
			super.fade();
		else
			expire();
	}
	
    /**
     * Gives a Player the item
     * @param item
     */
    private boolean loot(){
    	boolean isPlayerThere = (world().getActorAt(Player.class, pos()) != null);
    	if (isPlayerThere) {
    		drop.attach(world().getActorAt(Player.class, pos()));
    		world().getActorAt(Player.class, pos()).addItem(drop);
    	}
    	
    	return isPlayerThere;
    }
}
