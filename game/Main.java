package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Bienvenido al juego de caballos con IA");
        System.out.println("Seleccione un nivel de dificultad:");
        System.out.println("1. Principiante");
        System.out.println("2. Amateur");
        System.out.println("3. Experto");

        int level = scanner.nextInt();
        Game game = new Game(level);
        game.start();
    }
}
