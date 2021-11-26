package players;

import games.Game;

public class NegamaxPlayer implements Player {

    public String toString() {
        return "Negamax-Player-" + hashCode();
    }

    public int chooseMove(Game game) {
        Integer r = null;
        Integer i = null;
        int v;
        for (int c : game.validMoves()) {
            Game copy = game.copy();
            copy.execute(c);
            v = -evaluate(copy);
            if (r == null || v > r) {
                r = v;
                i = c;
            }
        }
        return i;
    }

    public int evaluate(Game game) {
        if (game.getWinner() == game.getCurrentPlayer()) return +1;
        else if (game.getWinner() != null) return -1;
        else if (game.isOver() && game.getWinner() == null) return 0;
        else {
            Integer r = null;
            int v;
            for (int c : game.validMoves()) {
                Game copy = game.copy();
                copy.execute(c);
                v = -evaluate(copy);
                r = (r == null || v > r) ? v : r;
            } return (r != null) ? r: 0;
        }
    }

}
