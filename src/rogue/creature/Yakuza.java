package rogue.creature;

import java.awt.Color;
import java.util.Arrays;

import jade.util.Dice;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Direction;

public class Yakuza extends Monster
{

	
		public Yakuza() 
		{
		super(ColoredChar.create('Y', new Color(139,026,026)));
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
			 case 2:if(x>=y){if(y>=2){move (0,-2);angriff();} else {move(0,-1);angriff();}} else {if(x>=2){move (-2,0);angriff();} else {move(-1,0);angriff();}} break;
			 case 3:if((x)<=r-2){move(2,0); angriff();}else if((x)==r-1){move(1,0); angriff();}else if((x)==r){move(0,0); angriff();}else if((x)>r){move(-2,0);}break;
			 case 4:if(x>=y){if(y>=2){move (0,2);angriff();} else {move(0,1);angriff();}} else {if(x>=2){move (-2,0);angriff();} else {move(-1,0);angriff();}} break;
			 case 5:if((y)<=r-2){move(0,-2); angriff();}else if((y)==r-1){move(0,-1); angriff();}else if((y)==r){move(0,0); angriff();}else if((y)>r){move(0,2);}break;
			 case 6:if(x>=y){if(y>=2){move (0,2);angriff();} else {move(0,1);angriff();}} else {if(x>=2){move (2,0);angriff();} else {move(-1,0);angriff();}} break;
			 case 7:if((x)<=r-2){move(-2,0); angriff();}else if((x)==r-1){move(-1,0); angriff();}else if((x)==r){move(0,0); angriff();}else if((x)>r){move(3,0);}break;
			 case 8:if(x>=y){if(y>=2){move (0,-2);angriff();} else {move(0,-1);angriff();}} else {if(x>=2){move (2,0);angriff();} else {move(1,0);angriff();}} break;
			 case 9:move(Dice.global.choose(Arrays.asList(Direction.values())));angriff();break;
			 }
		 	} 		 	
	 else									//Spieler ausserhalb der Reichweite
	{
		move(0, 0);
	}

}
}

