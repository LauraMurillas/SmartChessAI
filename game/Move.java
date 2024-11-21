package game;

public class Move {
    private final int[] position; // Posición del movimiento (fila, columna)
    private final int score;      // Puntaje estimado o evaluado del movimiento

    public Move(int[] position, int score) {
        this.position = position;
        this.score = score;
    }

    // Getter para la posición
    public int[] getPosition() {
        return position;
    }

    // Getter para el puntaje
    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Move [Position=(" + position[0] + "," + position[1] + "), Score=" + score + "]";
    }
}

