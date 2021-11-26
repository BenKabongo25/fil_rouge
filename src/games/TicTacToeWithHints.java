package games;

import java.util.ArrayList;
import players.Player;

public class TicTacToeWithHints extends TicTacToe {

    public TicTacToeWithHints(Player player1, Player player2) {
        super(player1, player2);
    }

    public ArrayList<Integer> hints() {
        ArrayList<Integer> coups = new ArrayList<>();
        for (int c : validMoves()) {
            TicTacToe copy = copy();
            copy.currentPlayer = (copy.currentPlayer == copy.player1) ? player2 : player1;
            Player p = copy.currentPlayer;
            copy.execute(c);
            if (copy.getWinner() == p)
                coups.add(c);
        }
        return coups;
    }

    public String situationToString() {
        String s = super.situationToString();
        ArrayList<Integer> h = hints();
        if (h.isEmpty())
            s += "Aucun coups favorables pour " + currentPlayer + "\n";
        else {
            s += "Coups favorables pour " + currentPlayer + ":\n";
            for (Integer c : h) s += moveToString(c) + "\n";
        }
        return s;
    }

}
