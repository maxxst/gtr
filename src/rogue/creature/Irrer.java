package rogue.creature;

import jade.util.Dice;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Direction;

import java.awt.Color;
import java.util.Arrays;

public class Irrer extends Monster {
	public Irrer() 
	{
	super(ColoredChar.create('I', new Color(250,128,114)));
	// TODO Auto-generated constructor stub
	}
	
	public void act() 
	{
	move(Dice.global.choose(Arrays.asList(Direction.values())));
	Direction dir = findPlayerInRange();
	if(dir != null)
		{ //angreifbar
		  attack(dir, weapon);
		  // Immer wenn angegriffen wird soll ein Text kommen der zur Unterhaltung beiträgt. 
		  // int i = (int) (Math.random()*x+y); erzeugt eine Zufallszahl in den grenzen y bis x
		  int i = (int) (Math.random()*20+1);
		  if (i <= 6){
			  switch (i)
				{
				 case 1:displayText("Irrer: Ich hab ne Zwiebel aufm Kopf ich bin ein Döner!");
				 		break;
				 case 2:displayText("Irrer: Wir haben einen Hulk, was hast du?");
				 		break;
				 case 3:displayText("Irrer: Hmm, lecker Ohrenschmalzgeschmack.");
						break;
				 case 4:displayText("Irrer: Fününününüh!");
				 		break;
				 case 5:displayText("Irrer: Warum habe ich die rote Pille genommen?");
					 	break;
				} 
		  }
			  
		}
	}

}
