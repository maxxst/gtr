package rogue.creature;

import jade.util.Dice;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Coordinate;
import jade.util.datatype.Direction;

import java.awt.Color;
import java.util.Arrays;

public class Dealer extends Monster {
	public Dealer() {
		super(ColoredChar.create('D', new Color(100, 25, 100)));
		// TODO Auto-generated constructor stub
	}

	int i = 1;

	public void act() {
		int x = Math.abs(player.x() - x());
		int y = Math.abs(player.y() - y());
		if (x + y < 25) {

			if (player.x() < x()) {
				if (player.y() < y()) {
					move(-1, -1);
				} else {
					move(-1, 1);
				}
			} else {
				if (player.y() < y()) {
					move(1, -1);
				} else {
					move(1, 1);
				}
			}

			move(Dice.global.choose(Arrays.asList(Direction.values())));
			if (x + y < 3) {
				System.out.print("Dealer: ");
				switch (i) {
				case 1:
					gtr.textbox.TextBox.displayText(player.getTerm(),
							"Hey Kollege, ich hab hier was Feines für dich.");
					i = i + 1;
					break;
				case 2:
					gtr.textbox.TextBox.displayText(player.getTerm(),
							"Warum antwortest du mir nicht, was hast 'n du für'n Problem?");
					i = i + 1;
					break;
				case 3:
					gtr.textbox.TextBox.displayText(player.getTerm(),
							"Du Penner gehst mir auf'n Sack!");
					i = i + 1;
					break;
				case 4:
					gtr.textbox.TextBox.displayText(player.getTerm(),
							"STIRB!");
					Direction dir = findPlayerInRange();
					if (dir != null) { // angreifbar
						attack(dir, weapon);
					}
				}

			}
		}
	}

}