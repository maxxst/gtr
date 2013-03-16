package rogue.creature;

import jade.util.Dice;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Direction;

import java.awt.Color;
import java.util.Arrays;

public class Dealer extends Monster {
	public Dealer() 
	{
	super(ColoredChar.create('D', new Color(100,25,100)));
	// TODO Auto-generated constructor stub
	}
	
	int i = 1;
	
	public void act() 
		{
		Dealer[] d = world().getActors(Dealer.class).toArray(new Dealer[0]);
		System.out.println(d.length);
		int x = Math.abs(player.x()-x());
		int y = Math.abs(player.y()-y());
	   if(x+y<25)
	   {
		   
		   if (player.x()<x())
			 {
			 if (player.y()<y())
				{
				move(-1,-1);	
				}
			 else
				{
				move(-1,1);
				}
			 }
		   else
			 {
			 if (player.y()<y())
				{
				move(1,-1);
				}
			 else
				{
				move(1,1);
				} 
			 }
		   
		move(Dice.global.choose(Arrays.asList(Direction.values())));
		if(x+y<3)
			{
			switch (i)
			{
			case 1:System.out.println("Hey Kollege, ich hab hier was Feines für dich.");i=i+1; break;
			case 2:System.out.println("Warum antwortest du mir nicht, was hast'n du für'n Problem?");i=i+1; break;
			case 3:System.out.println("Du Penner gehst mir auf'n Sack!");i=i+1; break;
			case 4:System.out.println("STIRB!");
				   Direction dir = findPlayerInRange();
				   if(dir != null)
				   	{ //angreifbar
					  attack(dir, weapon);
				   	}
			}

			}	}	
		}

}