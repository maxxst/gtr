package rogue.creature;

import jade.util.datatype.ColoredChar;
import jade.util.datatype.Direction;

import java.awt.Color;

//Ninjas tarnen sich als schwarzes Zeichen und handeln nicht, bis der Spieler in Reichweite ist, dann springen 
//Sie gnadenlos zu ihrem ziel und stossen zu.

public class Ninja extends Monster 
	{
	public Ninja() {
	super(ColoredChar.create('N', new Color(235,235,235)));
	// TODO Auto-generated constructor stub
	}
	
	public void act() 
	{
		int x = Math.abs(player.x()-x());
		int y = Math.abs(player.y()-y());
	 if (x+y<this.getMove())
	 {
		 if (player.x()<x())
		 {
			if (player.y()<y())
			{
				int i = (int) (Math.random()*3+1);
				switch (i)
				{
				case 1:move((-1)*x-1,(-1)*y+1);break;
				case 2:move((-1)*x,(-1)*y+1);break;
				case 3:move((-1)*x+1,(-1)*y+1);break;
				}
				
			}
			else
			{
				int i = (int) (Math.random()*3+1);
				switch (i)
				{
				case 1:move((-1)*x+1,y+1);break;
				case 2:move((-1)*x+1,y);break;
				case 3:move((-1)*x+1,y-1);break;
				}
			}
		 }
		 else
		 {
			if (player.y()<y())
			{
				int i = (int) (Math.random()*3+1);
				switch (i)
				{
				case 1:move(x-1,(-1)*y-1);break;
				case 2:move(x-1,(-1)*y);break;
				case 3:move(x-1,(-1)*y+1);break;
				}
				}
				else
				{
					int i = (int) (Math.random()*3+1);
					switch (i)
					{
					case 1:move(x-1,y-1);break;
					case 2:move(x,y-1);break;
					case 3:move(x+1,y-1);break;
					}
				} 
		 }
	  Direction dir = findPlayerInRange();
	  if(dir != null)
		{ 
		 //angreifbar
		 attack(dir, weapon);
		 // Immer wenn angegriffen wird soll ein Text kommen der zur Unterhaltung beiträgt. 
		 // int i = (int) (Math.random()*x+y); erzeugt eine Zufallszahl in den grenzen y bis x
		 int i = (int) (Math.random()*10+1);
		 if (i<=5)
		 {
		 System.out.print("Ninja: ");
	     switch (i)
			{
			 case 1:displayText("Ninja: Hayjaaaaaa!"); 
			 		break;
			 case 2:displayText("Ninja: Banzai!");  
				 	break;
			 case 3:displayText("Ninja: Requiescat in Pache.");
			 		break;
			 case 4:displayText("Ninja: KAMIKAZE!");
			 		break;
			 case 5:displayText("Ninja: Du noch viel lernen musst, junger Padawan.");
			 		break;
			}
		 }
		}
	 }
	 else
	 {
	  move(0,0);
	 }

}
}
