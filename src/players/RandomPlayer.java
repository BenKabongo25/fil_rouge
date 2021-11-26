package players;

import java.util.Random;
import games.Game;

public class RandomPlayer implements Player {

    protected Random random;

    public RandomPlayer(Random random) {
        this.random = random;
    }

    public int chooseMove(Game game) {
        if (game.validMoves().isEmpty())
            return -1;
        return game.validMoves().get(random.nextInt(game.validMoves().size()));
    }

    public String toString() {
        return "Random-Player-" + hashCode();
    }

}
