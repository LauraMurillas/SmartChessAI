package game.ai;

import game.Board;
import game.Player;
import game.Move;

import java.util.List;

public class Minimax {

    /**
     * Implementa el algoritmo Minimax con una profundidad limitada.
     *
     * @param board            Tablero actual.
     * @param player           Jugador que está realizando el movimiento.
     * @param depth            Profundidad restante del árbol.
     * @param maximizingPlayer Indica si se está maximizando o minimizando la utilidad.
     * @param heuristic        Función heurística para evaluar los nodos.
     * @return La mejor jugada (Move) según el algoritmo.
     */
    public static Move run(Board board, Player player, int depth, boolean maximizingPlayer, Heuristic heuristic) {
        if (depth == 0 || board.isGameOver()) {
            // Evaluar el estado actual del tablero usando la función heurística
            int score = heuristic.evaluate(board, player);
            return new Move(null, score);
        }

        List<Move> moves = board.getAvailableMoves(player);
        if (moves.isEmpty()) {
            // Si no hay movimientos disponibles, evaluar el estado actual
            int score = heuristic.evaluate(board, player);
            return new Move(null, score);
        }

        Move bestMove = null;

        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Move move : moves) {
                // Aplicar temporalmente el movimiento
                board.makeTemporaryMove(player, move);

                // Evaluar recursivamente
                int eval = run(board, player, depth - 1, false, heuristic).getScore();

                // Revertir el movimiento
                board.undoMove(move);

                if (eval > maxEval) {
                    maxEval = eval;
                    bestMove = new Move(move.getPosition(), maxEval);
                }
            }
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Move move : moves) {
                // Aplicar temporalmente el movimiento
                board.makeTemporaryMove(player.getOpponent(), move);

                // Evaluar recursivamente
                int eval = run(board, player, depth - 1, true, heuristic).getScore();

                // Revertir el movimiento
                board.undoMove(move);

                if (eval < minEval) {
                    minEval = eval;
                    bestMove = new Move(move.getPosition(), minEval);
                }
            }
        }

        return bestMove;
    }
}

