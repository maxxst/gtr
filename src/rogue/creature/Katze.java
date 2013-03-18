package rogue.creature;

import jade.util.Dice;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Direction;

import java.awt.Color;
import java.util.Arrays;

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
		

		
		public void act() 
		{
		/*
		 * Wenn hund in nähe, dann andere richtung (wegrennen);
		 * wenn hund nicht in nähe "runden" laufen alle auf streife, sprich am gleichen punkt nach x zügen wieder sein
		move();
		*/
		Direction dir = findPlayerInRange();
		if(dir != null)
			{ //angreifbar
			  attack(dir, weapon);}}
}
