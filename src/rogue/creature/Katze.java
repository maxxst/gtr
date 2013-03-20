package rogue.creature;

import jade.util.datatype.ColoredChar;
import jade.util.datatype.Direction;

import java.awt.Color;

public class Katze extends Monster 
{
	private static Color getColor (){
		Color Farbe = null; int i = (int) (Math.random()*5+1);
		switch (i)
		{
		case 1: Farbe=new Color(139,69,19);break;
		case 2:  Farbe=new Color(238,203,173);break;
		case 3:  Farbe=new Color(205,133,63);break;
		case 4: Farbe=new Color(218,165,32);break;
		case 5:  Farbe=new Color(240,230,140);break;
		}
		return Farbe;
	} 
		public Katze() 
		{
		 this(getColor());
		// TODO Auto-generated constructor stub
		}
		
		public Katze(Color color){
			super(ColoredChar.create('K', color));
		}
	
		int i =1;
		
		public void act() 
		{
		/*
		 * Wenn hund in nähe, dann andere richtung (wegrennen);
		 */
			switch (i)
			{
			case 1:movecare(3,0); i=i+1;break;
			case 2:movecare(2,2);i=i+1;break;
			case 3:movecare(0,3);i=i+1;break;
			case 4:movecare(-2,2);i=i+1; if ( Math.abs(player.x() - x())+Math.abs(player.y() - y())<=10){displayText("Katze: Miau!");}break;
			case 5:movecare(-3,0);i=i+1;break;
			case 6:movecare(-2,-2);i=i+1;break;
			case 7:movecare(0,-3);i=i+1;break;
			case 8:movecare(2,-2);i=1; if  (Math.abs(player.x() - x())+Math.abs(player.y() - y())<=10){displayText("Katze: Meew.");};break;
			
			}
		Direction dir = findPlayerInRange();
		if(dir != null)
			{ //angreifbar
			  attack(dir, weapon);}}
}
