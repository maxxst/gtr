package rogue.level;

import jade.core.Actor;
import jade.core.World;
import jade.gen.Generator;
import jade.gen.map.Cellular;
import jade.ui.Terminal;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Direction;
import rogue.creature.Player;

public class AsciiScreen extends World {

	private static Generator gen;

	public AsciiScreen(AsciiScreenInput _screenInput, AsciiScreenActor _asciiScreenActor) {
		super(_screenInput.getWidth(), _screenInput.getHeight());
		
		gen = new Cellular(_screenInput, this);
		addActor(_asciiScreenActor);
	}

	

	

}
