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
	 int r = weapon.getRangeTo();
	 int x = Math.abs(player.x() - x());
	 int y = Math.abs(player.y() - y());
	 if (x+y<=18)
	 	{
			 switch (getQuadrant())
			 {
			 case 1:if((y)<=r-2){move(0,2); angriff();}else if((y)==r-1){move(0,1); angriff();}else if((y)==r){move(0,0); angriff();}else if((y)>r){move(0,-2);}break;
			 case 2:break;
			 case 3:if((x)<=r-2){move(2,0); angriff();}else if((x)==r-1){move(1,0); angriff();}else if((x)==r){move(0,0); angriff();}else if((x)>r){move(-2,0);}break;
			 case 4:break;
			 case 5:if((y)<=r-2){move(0,-2); angriff();}else if((y)==r-1){move(0,-1); angriff();}else if((y)==r){move(0,0); angriff();}else if((y)>r){move(0,2);}break;
			 case 6:break;
			 case 7:if((x)<=r-2){move(-2,0); angriff();}else if((x)==r-1){move(-1,0); angriff();}else if((x)==r){move(0,0); angriff();}else if((x)>r){move(3,0);}break;
			 case 8:break;
			 case 9:break;
			 }
			 angriff();
		 	} 		 	
	 else									//Spieler ausserhalb der Reichweite
	{
		move(0, 0);
	}

}
}

