package game;

import game.ai.AI1;
import game.ai.AI2;
import game.Player;

public class Game {
    private Board board;
    private Player player1, player2;
    private AI1 ai1;
    private AI2 ai2;
    private int level;

    public Game(int level) {
        this.level = level;
        this.board = new Board();
        this.player1 = new Player("Caballo Blanco", true);
        this.player2 = new Player("Caballo Negro", false);
        this.ai1 = new AI1(level);
        this.ai2 = new AI2(level);
    }

    public void start() {
        board.initialize();
        boolean gameOver = false;

        while (!gameOver) {
            board.display();
            ai1.makeMove(board, player1);
            board.display();
            ai2.makeMove(board, player2);
            gameOver = board.isGameOver();
        }

        board.display();
        System.out.println("Juego terminado. Ganador: " + board.getWinner());
    }
}

