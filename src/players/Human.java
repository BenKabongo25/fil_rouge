package players;

import java.util.Scanner;
import games.Game;

public class Human implements Player {

	protected String name;
	protected Scanner sc;

	public Human(String name, Scanner sc) {
		this.name = name;
		this.sc = sc;
	}

	public String toString() {
		return name;
	}

	public int chooseMove(Game game) {
		System.out.println("Coups valides :");
		for (Integer c: game.validMoves()) {
			System.out.println(game.moveToString(c));
		}

		int c = -1;
		do {
			System.out.println("Votre choix ? :");
			try {
				c = Integer.parseInt(sc.next());
			} catch (Exception e) {
				System.out.println("Veuillez saisir un nombre.");
			}
		} while (!game.isValid(c));
		return c;
	}

}
