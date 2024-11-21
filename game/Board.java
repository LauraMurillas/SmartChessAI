package game;

import game.utils.Constants;
import game.utils.Randomizer;

public class Board {
    private int[][] grid;
    private int player1Score;
    private int player2Score;

    public void initialize() {
        grid = Randomizer.generateRandomBoard();
        player1Score = 0;
        player2Score = 0;
    }

    public void display() {
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println("Puntos Jugador 1: " + player1Score);
        System.out.println("Puntos Jugador 2: " + player2Score);
    }

    public boolean isGameOver() {
        // Lógica para verificar si no quedan casillas con puntos
        return false;
    }

    public String getWinner() {
        if (player1Score > player2Score) return "Jugador 1";
        if (player2Score > player1Score) return "Jugador 2";
        return "Empate";
    }
}

