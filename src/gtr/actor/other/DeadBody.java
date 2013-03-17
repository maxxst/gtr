package gtr.actor.other;

import gtr.actor.item.Item;

import java.awt.Color;
import java.util.ArrayList;

import jade.core.Actor;
import jade.util.datatype.ColoredChar;

/**
 * When a NPCreature dies it leaves lootable ramains 
 * @author maxx
 * 
 * @version 0.1
 */
public class DeadBody extends Actor {
	ArrayList<Item> loot = new ArrayList<Item>();
	
	public DeadBody() {
		super(ColoredChar.create('%', new Color(139,0,0)));
		// TODO add itemdrop
	}

	@Override
	public void act() {
		// TODO Auto-generated method stud
	}

}
