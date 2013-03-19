package gtr.actor.fading;

import gtr.actor.item.Ammo;
import gtr.actor.item.HealthPotion;
import gtr.actor.item.Item;
import gtr.actor.item.Potion;
import gtr.actor.item.weapon.Weapon;
import gtr.util.DropType;
import gtr.util.ItemType;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import jade.core.Actor;
import jade.util.datatype.ColoredChar;

/**
 * When a NPCreature dies it leaves lootable ramains 
 * @author maxx
 * 
 * @version 0.1
 */
public class DeadBody extends Fading {
	ArrayList<Item> loot = new ArrayList<Item>();
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
		
		
		
	    ItemType itemType = ItemType.getRandomItemType();
	    if (itemType.equals(Item.ammo)){
	    	drop = new Ammo(dropType);
	    } else if(itemType.equals(Item.potion)){
	    	drop = new Potion();
	    } else {
	    	drop = new Weapon(dropType);
	    }
		
	}
}
