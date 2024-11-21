package game.ai;

import game.Player;
import game.Board;
import game.Move;


public class AI2 {
    private int depth;

    public AI2(int level) {
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
            return new Move(null, evaluatePositionalAdvantage(board, player));
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

    private int evaluatePositionalAdvantage(Board board, Player player) {
        int score = 0;
        for (Move move : board.getAvailableMoves(player)) {
            score += board.getCellValue(move.getPosition()); // Valorar puntos en la posición
            score += countAdjacentPoints(board, move.getPosition()) * 2; // Bonus por puntos cercanos
        }
        return score;
    }

    private int countAdjacentPoints(Board board, int[] position) {
        int count = 0;
        for (int[] dir : board.getMoveDirections()) {
            int[] adj = { position[0] + dir[0], position[1] + dir[1] };
            if (board.isWithinBounds(adj) && board.isPointCell(adj)) {
                count++;
            }
        }
        return count;
    }
}

