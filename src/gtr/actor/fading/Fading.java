package gtr.actor.fading;

import java.awt.Color;

import jade.core.Actor;
import jade.util.datatype.ColoredChar;

/**
 * 
 * @author maxx
 *
 */
public class Fading extends Actor {
	protected int turnsLeft = 10;
	protected int alpha = 255;
	
	public Fading(ColoredChar face) {
		super(face);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void act() {
		fade();
	}
	
	protected void fade(){
		if (turnsLeft > 0){
			alpha = alpha - (int) (alpha / turnsLeft);
			
			setFace(ColoredChar.create(face().ch(), new Color(139,0,0,alpha)));
			//System.out.print(alpha);
		} else {
			expire();
		}
		
		turnsLeft--;
	}

}
