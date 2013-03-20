package rogue.creature;

import java.awt.Color;

import jade.util.datatype.ColoredChar;
import jade.util.datatype.Direction;

public class Yakuza extends Monster
{

	
		public Yakuza() 
		{
		super(ColoredChar.create('Y', new Color(0,200,0)));
		// TODO Auto-generated constructor stub
		}
		
		public void angriff ()
			{Direction dir = findPlayerInRange();
			if(dir != null)
				{ 
				attack(dir, weapon);
				}}

		public void act() 
		{
		 int x = Math.abs(player.x() - x());
		 int y = Math.abs(player.y() - y());
		 if (x+y<=18)
		 	{
			 if(weapon.getName().equals("MP7"))  //verhalten bei MP7
			 	{
				 switch (getQuadrant())
				 {
				 case 1:if((y)<=4){move(0,2);}if((y)==5){move(0,1);}if((y)==6){move(0,0);}if((y)>6){move(0,-2);}break;
				 case 2:break;
				 case 3:if((x)<=4){move(2,0);}if((x)==5){move(1,0);}if((x)==6){move(0,0);}if((x)>6){move(-2,0);}break;
				 case 4:break;
				 case 5:if((y)<=4){move(0,-2);}if((y)==5){move(0,-1);}if((y)==6){move(0,0);}if((y)>6){move(0,2);}break;
				 case 6:break;
				 case 7:if((x)<=4){move(-2,0);}if((x)==5){move(-1,0);}if((x)==6){move(0,0);}if((x)>6){move(3,0);}break;
				 case 8:break;
				 case 9:break;
				 }
				 angriff();
			 	} 
			 else								//ab hier HK P30
			 	{
				 switch (getQuadrant())
				 {
				 case 1:angriff();break;
				 case 2:break;
				 case 3:break;
				 case 4:break;
				 case 5:break;
				 case 6:break;
				 case 7:break;
				 case 8:break;
				 case 9:break;
				 }
				 angriff();
			    }		 
			 }	
		 else									//Spieler ausserhalb der Reichweite
		 	{move(0,0);}
		 
		
		}
}
