package game.ai;

import game.Board;
import game.Player;

public class AI1 {
    private int depth;

    public AI1(int level) {
        this.depth = level * 2;
    }

    public void makeMove(Board board, Player player) {
        Move bestMove = minimax(board, player, depth, true);
        if (bestMove != null) {
            player.makeMove(board, bestMove);
        }
    }

    private Move minimax(Board board, Player player, int depth, boolean maximizingPlayer) {
        if (depth == 0 || board.isGameOver()) {
            return new Move(null, evaluateMaterialAdvantage(board, player));
        }

        if (maximizingPlayer) {
            Move bestMove = new Move(null, Integer.MIN_VALUE);
            for (Move move : board.getAvailableMoves(player)) {
                board.makeTemporaryMove(player, move);
                int eval = minimax(board, player, depth - 1, false).getScore();
                board.undoMove(move);

                if (eval > bestMove.getScore()) {
                    bestMove = new Move(move.getPosition(), eval);
                }
            }
            return bestMove;
        } else {
            Move bestMove = new Move(null, Integer.MAX_VALUE);
            for (Move move : board.getAvailableMoves(player.getOpponent())) {
                board.makeTemporaryMove(player.getOpponent(), move);
                int eval = minimax(board, player, depth - 1, true).getScore();
                board.undoMove(move);

                if (eval < bestMove.getScore()) {
                    bestMove = new Move(move.getPosition(), eval);
                }
            }
            return bestMove;
        }
    }

    private int evaluateMaterialAdvantage(Board board, Player player) {
        return board.getScore(player) - board.getScore(player.getOpponent());
    }
}
