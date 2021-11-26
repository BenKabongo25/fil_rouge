package plays;

import games.Game;
import players.Player;

public class Orchestrator {

	protected Game game;

	public Orchestrator(Game game) {
		this.game = game;
	}

	public void play() {
      	do {
        	System.out.println("Situation actuelle ");
        	System.out.println(game.situationToString() + "\n");
        	System.out.println("C'est à "+ game.getCurrentPlayer() + " de jouer.");
        	int coup = game.getCurrentPlayer().chooseMove(game);
        	System.out.print("Vous avez choisi : " + coup + "\n");
        	game.execute(coup);
      	} while (!game.isOver());
	    System.out.println("Situation actuelle ");
	    System.out.println(game.situationToString() + "\n");
		Player winner = game.getWinner();
		if (winner != null) System.out.println(winner + " a gagné !");
		else System.out.println("Match nul !");
	}

}
