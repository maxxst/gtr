package rogue.creature;

import jade.util.Dice;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Direction;

import java.awt.Color;
import java.util.Arrays;

public class Exsoldat extends Monster {

	
	public Exsoldat() 
	{
	super(ColoredChar.create('E', new Color(139,134,076)));
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
 int i = (int) (Math.random()*2+1);
 int x = Math.abs(player.x() - x());
 int y = Math.abs(player.y() - y());
 if (x+y<=18)
 	{
		 switch (getQuadrant())
		 {
		 case 1:if(i==1){if(y<=r){angriff();}move(-2,0);}else{if(y<=r){angriff();}move(2,0);}break;
		 case 2:if (x==y) {if(x>weapon.getRangeTo()){move(-2,-2);angriff();} else if (x==weapon.getRangeTo()){angriff();}else if (x==weapon.getRangeTo()-1){move(1,1);angriff();} else if(x<=weapon.getRangeTo()-2){move(2,2);angriff();}}
		 		else if (x>y){if(x-y==1){move(0,1);angriff();}else{move(0,2);angriff();}} else {if(x-y==1){move(1,0);angriff();}else{move(2,0);angriff();}}
			 	break;
		 case 3:if(i==1){if(x<=r){angriff();}move(0,2);}else{if(x<=r){angriff();}move(0,-2);}break;
		 case 4:		if (x==y) {if(x>weapon.getRangeTo()){move(-2,2);angriff();} else if (x==weapon.getRangeTo()){angriff();}else if (x==weapon.getRangeTo()-1){move(1,-1);angriff();} else if(x<=weapon.getRangeTo()-2){move(2,-2);angriff();}}
	 			else if (x>y){if(x-y==1){move(0,-1);angriff();}else{move(0,-2);angriff();}} else {if(x-y==1){move(1,0);angriff();}else{move(2,0);angriff();}}
			 break;
		 case 5:if(i==1){if(y<=r){angriff();}move(-2,0);}else{if(y<=r){angriff();}move(2,0);}break;
		 case 6:		if (x==y) {if(x>weapon.getRangeTo()){move(2,2);angriff();} else if (x==weapon.getRangeTo()){angriff();}else if (x==weapon.getRangeTo()-1){move(-1,-1);angriff();} else if(x<=weapon.getRangeTo()-2){move(-2,-2);angriff();}}
	 			else if (x>y){if(x-y==1){move(0,-1);angriff();}else{move(0,-2);angriff();}} else {if(x-y==1){move(-1,0);angriff();}else{move(-2,0);angriff();}}
			 	break;
		 case 7:if(i==1){if(x<=r){angriff();}move(0,2);}else{if(x<=r){angriff();}move(0,-2);}break;
		 case 8:		if (x==y) {if(x>weapon.getRangeTo()){move(2,-2);angriff();} else if (x==weapon.getRangeTo()){angriff();}else if (x==weapon.getRangeTo()-1){move(-1,1);angriff();} else if(x<=weapon.getRangeTo()-2){move(-2,2);angriff();}}
	 			else if (x>y){if(x-y==1){move(0,1);angriff();}else{move(0,2);angriff();}} else {if(x-y==1){move(-1,0);angriff();}else{move(-2,0);angriff();}}
			 
			 	break;
		 case 9:move(Dice.global.choose(Arrays.asList(Direction.values())));angriff();break;
		 }
	 	} 		 	
 else									//Spieler ausserhalb der Reichweite
{
	move(0, 0);
}

}
}
