package games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import players.Player;

public class TicTacToe extends AbstractGame {

    protected Player[][] board;

    public TicTacToe(Player p1, Player p2) {
        super(p1, p2);
        board = new Player[3][3];
    }

    protected void doExecute(int c) {
        int i = c/3, j = c%3;
        board[i][j] = currentPlayer;
    }

    public boolean isValid(int c) {
        if (c < 0 || c >= 9) return false;
        int i = c/3, j = c%3;
        return (board[i][j] != player1 && board[i][j] != player2);
    }

    public ArrayList<Integer> validMoves() {
        ArrayList<Integer> moves = new ArrayList<>();
        for (int c=0; c < 9; c++)
            if (isValid(c)) moves.add(c);
        return moves;
    }

    public boolean wins(Player p, int row, int col, int dr, int dc) {
        try {
            return row*3 + col != ((row+dr)%3)*3 + (col+dc)%3
                && row*3 + col != ((row+2*dr)%3)*3 + (col+2*dc)%3
                && ((row+dr)%3)*3 + (col+dc)%3 != ((row+2*dr)%3)*3 + (col+2*dc)%3
                && board[row%3][col%3] == p
                && board[(row+dr)%3][(col+dc)%3] == p
                && board[(row+2*dr)%3][(col+2*dc)%3] == p;
        } catch (Exception e) {
            return false;
        }
    }

    // Teste si le Player p a gagné
    private boolean isPlayerWins(Player p) {
        return wins(p, 0, 0, 0, 1)
            || wins(p, 0, 0, 1, 0)
            || wins(p, 0, 0, 1, 1)
            || wins(p, 1, 0, 0, 1)
            || wins(p, 2, 0, 0, 1)
            || wins(p, 0, 1, 1, 0)
            || wins(p, 0, 2, 1, 0)
            || wins(p, 0, 2, 1, -1);
    }

    public Player getWinner() {
        if (isPlayerWins(player1)) return player1;
        else if (isPlayerWins(player2)) return player2;
        else return null;
    }

    public boolean isOver() {
        if (getWinner() != null) return true;
        // vérification pour les matchs nuls
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Player p = board[i][j];
                if (p != player1 && p != player2)
                    return false;
            }
        } return true;
    }

    public String moveToString(int c) {
        return c + " = (" + (c/3 +1) + "," + (c%3 +1) + ")";
    }

    public String situationToString() {
        String s = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 0 || j == 0) {
                    if (i == 0 && j == 0) s += " \t";
                    else if (j != 0) s += j + "\t";
                    else s += i + "\t";
                } else {
                    Player p = board[i-1][j-1];
                    if (p == player1) s += "X\t";
                    else if (p == player2) s += "O\t";
                    else s += ".\t";
                }
            }
            s += "\n";
        }
        return s;
    }

    public TicTacToe copy() {
        TicTacToe other = new TicTacToe(player1, player2);
        other.currentPlayer = currentPlayer;
        other.board = new Player[3][3];
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                other.board[i][j] = board[i][j];
            }
        }
        return other;
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof TicTacToe)) return false;
        TicTacToe ticTacToe = (TicTacToe)o;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((board[i][j] == null && ticTacToe.board[i][j] != null) ||
                    (board[i][j] != null && !board[i][j].equals(ticTacToe.board[i][j])))
                    return false;
            }
        }
        return currentPlayer.equals(ticTacToe.currentPlayer);
    }

    public int hashCode() {
        return Objects.hash(this.player1, this.player2, this.currentPlayer, Arrays.deepHashCode(this.board));
    }

}
