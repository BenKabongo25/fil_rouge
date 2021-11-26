package players;

import java.util.HashMap;

import games.Game;

public class NegamaxPlayerWithCache extends NegamaxPlayer {

    protected HashMap<Game, Integer> cache;

    public NegamaxPlayerWithCache() {
        cache = new HashMap<>();
    }

    public int evaluate(Game game) {
        if (cache.containsKey(game))
            return cache.get(game);
        int v = super.evaluate(game);
        cache.put(game, v);
        return v;
    }

}
