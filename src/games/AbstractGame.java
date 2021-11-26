package games;

import players.Player;

public abstract class AbstractGame implements Game {

    protected Player player1;
    protected Player player2;
    protected Player currentPlayer;

    public AbstractGame(Player p1, Player p2) {
        player1 = p1;
        player2 = p2;
        currentPlayer = p1;
    }

    protected abstract void doExecute(int c);

    public void execute(int c) {
        doExecute(c);
        currentPlayer = (currentPlayer.equals(player1)) ? player2 : player1;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

}
