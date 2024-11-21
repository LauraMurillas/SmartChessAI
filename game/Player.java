package game;

import java.util.Objects;

public class Player {
    private final String name;
    private final char symbol; // Representación del jugador en el tablero ('W' o 'B')
    private int[] position;    // Posición actual en el tablero (fila, columna)
    private int score;         // Puntos acumulados
    private boolean hasMultiplier; // Indica si el jugador tiene un x2 activo

    public Player(String name, char symbol, int[] startPosition) {
        this.name = name;
        this.symbol = symbol;
        this.position = startPosition;
        this.score = 0;
        this.hasMultiplier = false;
    }

    // Getter para el nombre del jugador
    public String getName() {
        return name;
    }

    // Getter para el símbolo del jugador
    public char getSymbol() {
        return symbol;
    }

    // Getter para la posición actual
    public int[] getPosition() {
        return position;
    }

    // Actualiza la posición del jugador
    public void setPosition(int[] newPosition) {
        this.position = newPosition;
    }

    // Getter para la puntuación
    public int getScore() {
        return score;
    }

    // Incrementa la puntuación del jugador, considerando si tiene un x2 activo
    public void addScore(int points) {
        if (hasMultiplier) {
            points *= 2;
            hasMultiplier = false; // Se consume el multiplicador
        }
        this.score += points;
    }

    // Activa el multiplicador x2
    public void activateMultiplier() {
        this.hasMultiplier = true;
    }

    // Verifica si tiene un multiplicador activo
    public boolean hasMultiplier() {
        return hasMultiplier;
    }

    // Retorna el oponente (útil para lógica de IA)
    public Player getOpponent(Player[] players) {
        for (Player player : players) {
            if (!Objects.equals(player, this)) {
                return player;
            }
        }
        return null; // Esto no debería ocurrir si hay dos jugadores válidos
    }

    @Override
    public String toString() {
        return name + " (Score: " + score + ", Position: " + position[0] + "," + position[1] + ")";
    }
}
