package game.ai;

import game.Board;
import game.Player;

public interface Heuristic {
    /**
     * Evalúa el estado actual del tablero desde la perspectiva del jugador.
     *
     * @param board  Tablero actual.
     * @param player Jugador que está evaluando el tablero.
     * @return Valor heurístico del estado del tablero.
     */
    int evaluate(Board board, Player player);
}

