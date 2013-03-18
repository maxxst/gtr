package gtr.hud;

import jade.core.Messenger;

public class Hud {
	
	private static String hud = createHud();
	
	private static String createHud() {
		String statusBar = " LP: " + Integer.toString(Messenger.getPlayer().getHp()); // die ersten fünf Zeichen
		statusBar += "	";
		statusBar += " Ammo: " + Integer.toString(Messenger.getPlayer().getWeapon().getCount());
		statusBar += "	";
		statusBar += " Heiltränke: " + Integer.toString(Messenger.getPlayer().getItems().get(2).getCount());
		
		return statusBar;
		
	}
	
	public static String getHud() {
		return createHud();
	}
		
		

}
