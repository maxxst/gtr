package rogue.creature;

import jade.util.Dice;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Direction;

import java.awt.Color;
import java.util.Arrays;


/**
 * 
 * @author mitamo
 *
 */
public class Iwan extends Monster {
	public Iwan() 
	{
	super(ColoredChar.create('Ï', new Color(255,060,0)));
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
				 case 1:displayText("Verrückter Iwan: Du nicht nehmen Kerze!");
				 		break;
				 case 2:displayText("Verrückter Iwan: My heart goes BOOMBOOM!");
				 		break;
				 case 3:displayText("Verrückter Iwan: Mein Geheimnis? Ich bin immer wütend!");
						break;
				 case 4:displayText("Verrückter Iwan: Shut up woman and get on my horse!");
				 		break;
				 case 5:displayText("Verrückter Iwan: Bist du wütend, zähl bis vier, hilft das nicht, dann EXPLODIER!");
					 	break;
				} 
		  }
			  
		}
	}

}
