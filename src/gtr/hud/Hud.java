package gtr.hud;

import jade.core.Messenger;

public class Hud {
	
	private static String hud = createHud();
	
	private static String createHud() {
		String statusBar = " LP: "; // die ersten fünf Zeichen
		statusBar += Integer.toString(Messenger.getPlayer().getHp());
		
		statusBar += "	";
		
		statusBar += " Ammo: ∞";
		
		
		return statusBar;
		
	}
	
	public static String getHud() {
		return createHud();
	}
		
		

}
