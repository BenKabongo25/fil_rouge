package games;

import java.util.ArrayList;
import java.util.Objects;
import players.Player;

public class Nim extends AbstractGame {

	protected int initialNbMatches;
	protected int maxMatches;
	protected int currentNbMatches;

	public Nim(int initialNbMatches, int maxMatches, Player player1, Player player2) {
		super(player1, player2);
		this.initialNbMatches = initialNbMatches;
		this.maxMatches = maxMatches;
		currentNbMatches = initialNbMatches;
	}

  	protected void doExecute(int k) {
		currentNbMatches -= k;
	}

  	public ArrayList<Integer> validMoves() {
		ArrayList<Integer> moves = new ArrayList<>();
		int matches = Math.min(currentNbMatches, maxMatches);
		for (int k = 0; k <= matches; k++)
			if (isValid(k)) moves.add(k);
		return moves;
	}

  	public boolean isValid(int k) {
		return (k > 0 && k <= maxMatches && k <= currentNbMatches);
	}

  	public boolean isOver() {
		return (currentNbMatches == 0);
	}

  	public Player getWinner() {
		return (isOver() ? currentPlayer : null);
	}

  	public String situationToString() {
		return "Il reste " + currentNbMatches + " allumettes";
	}

  	public String moveToString(int k) {
		return k + " allumettes";
	}

	public int getInitialNbMatches() {
		return initialNbMatches;
	}

	public int getCurrentNbMatches() {
		return currentNbMatches;
	}

	public Nim copy() {
		Nim other = new Nim(initialNbMatches, maxMatches, player1, player2);
		other.currentPlayer = currentPlayer;
		other.currentNbMatches = currentNbMatches;
		return other;
	}

	public boolean equals(Object o) {
		if (o == null || !(o instanceof Nim)) return false;
		Nim nim = (Nim)o;
		return currentPlayer.equals(nim.currentPlayer)
				&& maxMatches == nim.maxMatches
				&& currentNbMatches == nim.currentNbMatches
				&& initialNbMatches == nim.initialNbMatches;
	}

	public int hashCode() {
		return Objects.hash(this.player1, this.player2, this.currentPlayer,
				this.currentNbMatches, this.initialNbMatches, this.maxMatches);
	}
}
