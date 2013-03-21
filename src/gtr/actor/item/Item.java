package gtr.actor.item;


import jade.core.Actor;
import jade.util.datatype.ColoredChar;

/**
 * Basic item form which weapons, potions and other things can extend
 * @author maxx
 * @version 0.1
 */
public class Item extends Actor{
    
	private String name = "Item";
	private int count = 1;
	
	public Item(ColoredChar face) {
		super(face);
		// TODO Auto-generated constructor stub
	}
	
	public Item(){
		this(ColoredChar.create('i'));
	}

	@Override
	public void act() {
	}
	
	public void use(){
		count--; 
	}
	
	public void use(int n){
		count -= n;
	}
	
	public String toString() {
		return this.getName();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void add(Item item){
		this.count += item.getCount();
		
		//if(item.getName() != null)
			//world().eventText("Vorrat an " + item.getName() + " erhöht");
	}
	
	/**
	 * Zählt, wie viele Einheiten (bei Waffen Munition) davon vorhanden sind
	 * @return Anzahl der Einheiten
	 */
	public int getCount(){
		return count;
	}
	
	public void setCount(int count){
		this.count = count;
	}

	/**
	 * Überprüft, ob man sich mit diesem Item ausrüsten kann
	 * @return true, wenn Spieler mit diesem Item ausrüstbar
	 */
	public boolean isEquippable() {
		return false;
	}
	
	public boolean equals(Item item){
		return getName().equals(item.getName());
	}
	
	public String getDescription(){
		return "";
	}
}
