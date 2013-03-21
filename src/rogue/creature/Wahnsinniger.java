package rogue.creature;

import jade.util.Dice;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Direction;

import java.awt.Color;
import java.util.Arrays;

public class Wahnsinniger extends Monster {
	public Wahnsinniger() 
	{
	super(ColoredChar.create('W', new Color(255,127,80)));
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
				 case 1:displayText("Wahnsinniger: Was ist das? Ein Flugzeug? Nein, meine Rakete!");
				 		break;
				 case 2:displayText("Wahnsinniger: Spiderman hat eine Affäre mit Batman.");
				 		break;
				 case 3:displayText("Wahnsinniger: Was machst du hier? Ich? Nein du! Ich? Nein du!...");
						break;
				 case 4:displayText("Wahnsinniger: BBRRRMMMM, ich bin ein Auto.");
				 		break;
				 case 5:displayText("Wahnsinniger: LOL, er hat Rofl gesagt.");
					 	break;
				 case 6:displayText("Wahnsinniger: Rofl, er hat LOL gesagt.");
				 	break;
				} 
		  }
			  
		}
	}
}
