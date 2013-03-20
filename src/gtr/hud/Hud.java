package gtr.hud;

import java.util.ArrayList;

import gtr.actor.item.Item;
import jade.core.Messenger;

public class Hud {
	
	private static String hud = createHud();
	
	private static String createHud() {
		String statusBar = " LP: " + Integer.toString(Messenger.getPlayer().getHp()); // die ersten fünf Zeichen
		statusBar += "	";
		statusBar += " Ammo: " + Integer.toString(Messenger.getPlayer().getWeapon().getCount());
		statusBar += " (" + Messenger.getPlayer().getWeapon().getType() + ")";
		statusBar += "	";
		
		int healthPotionCount = 0;
		ArrayList<Item> items = Messenger.getPlayer().getItems();
		for (int i = 0; i < items.size(); i++)
			if ("Heiltrank".equals(items.get(i).getName())) {
				healthPotionCount = items.get(i).getCount();
				break;
			}
		statusBar += " Heiltränke: " + Integer.toString(healthPotionCount);
		
		statusBar += "	";
		statusBar += " Waffe: " + Messenger.getPlayer().getWeapon().getName();
		
		return statusBar;
		
	}
	
	public static String getHud() {
		return createHud();
	}
		
		

}
