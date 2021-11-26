package games;

import java.util.ArrayList;
import players.Player;

public interface Game {
	void execute(int c);
	Player getCurrentPlayer();
	boolean isValid(int c);
	ArrayList<Integer> validMoves();
	Player getWinner();
	boolean isOver();
	String moveToString(int c);
	String situationToString();
	Game copy();
}