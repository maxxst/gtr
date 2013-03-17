package gtr.hud;

import jade.core.Messenger;

public class Hud {
	
	private static String hud = createHud();
	
	private static String createHud() {
		String statusBar = " KP: "; // die ersten fünf Zeichen
		statusBar += Integer.toString(Messenger.getPlayer().getHp());
		
		
		return statusBar;
		
	}
	
	public static String getHud() {
		return createHud();
	}
		
		

}
