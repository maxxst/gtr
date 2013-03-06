package rogue.level;

import jade.core.Actor;
import jade.ui.Terminal;
import jade.ui.TiledTermPanel;
import jade.util.datatype.ColoredChar;

public class AsciiScreenActor extends Actor {

	private Terminal term;

	public AsciiScreenActor(ColoredChar face) {
		super(face);
		// TODO Auto-generated constructor stub
	}

	public AsciiScreenActor(ColoredChar face, TiledTermPanel _terminal) {
		super(face);
		term = _terminal;
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub
		try {
			char key;
			key = term.getKey();
			switch (key) {
			case 'q':
				System.out.println("ttt");
				expire();
				break;
			default:
				
				break;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
