package rogue.creature;

import jade.util.Dice;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Direction;

import java.awt.Color;
import java.util.Arrays;

//Junkies sind Kanonenfutter die sich aufgrund ihres Konsums vollkommen verwirrt herumbewegen.

public class Junkie extends Monster {
	public Junkie() 
	{
	super(ColoredChar.create('J', new Color(0,200,0)));
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
		  int i = (int) (Math.random()*12+1);
		  if (i <= 6){
			  switch (i)
				{
				 case 1:displayText("Junkie: Das Zeug, das blaue Zeug!");
				 		break;
				 case 2:displayText("Junkie: Ey man, hast du was?");
				 		break;
				 case 3:displayText("Junkie: Science Bitch!");
						break;
				 case 4:displayText("Junkie: Ich brauche Drogen den ganzen Tag... *trälla*");
				 		break;
				 case 5:displayText("Junkie: Ich brauch was vom blauen Crystal, haste was für mich?");
					 	break;
				 case 6:displayText("Junkie: Heisenberg ist ein Gott!");
						break;
				} 
		  }
			  
		}
	}

}
