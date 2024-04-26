package Übung7.VerlorenImWald;

import java.util.Scanner;


public class RescueOperator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter coordinates (press Enter for end):");
        StringBuilder coordinatesBuilder = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).isEmpty()) {
            coordinatesBuilder.append(line).append(" ");
        }
        String coordinates = coordinatesBuilder.toString().trim();

        RescueNavigator navigator = new RescueNavigator(10);
        navigator.createMap(coordinates);

        System.out.println("Initial Map:");
        navigator.printMap();

        navigator.estimatePath(0, 0);

        System.out.println("\nPath Estimated Map:");
        navigator.printMap();
    }
}