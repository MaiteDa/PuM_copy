package Übung7.Snake;

import java.util.Random;

public class Gameplay {
    private boolean loopEdges;
    private Cell[][] field;
    private Snake snake;
    private Direction moveDirection;
    private Coordinate apple;
    public static void main(String[] args) {
        int fieldSize = 20; // Set the desired size of the game field
        boolean loopEdges = true; // Set whether loop edges are allowed

        Game game = new Game(fieldSize, loopEdges); // Create a new game instance

        // The game loop is managed within the Game class
        // You can add any additional logic or user interactions here if needed
    }
    public Gameplay(int fieldSize, boolean loopEdges) {
        // Initialisieren der Attribute entsprechend der Parameter
        this.field = new Cell[fieldSize][fieldSize]; // Erstellen des Spielfelds
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                field[i][j] = Cell.EMPTY; // Füllen des Spielfelds mit Cell.EMPTY
            }
        }
        // Erzeugen der Schlange und Setzen der Startposition (z. B. in der Mitte des Spielfelds)
        int initialX = fieldSize / 2;
        int initialY = fieldSize / 2;
        Coordinate head = new Coordinate(initialX, initialY);
        Coordinate tail = new Coordinate(initialX - 1, initialY); // Beispiel für den Schwanz
        this.snake = new Snake(head, tail);
        // Setzen der Start-Bewegungsrichtung (z. B. nach rechts)
        this.moveDirection = Direction.RIGHT;
        // Initialisieren der Apfelposition (z. B. zufällig, aber nicht auf der Schlange)
        placeApple();
    }
    public void placeApple() {
        // Erzeugen eines zufälligen Generators
        Random random = new Random();
        // Solange eine freie Koordinate für den Apfel gefunden wird
        while (true) {
            int x = random.nextInt(field.length); // Zufällige X-Koordinate
            int y = random.nextInt(field.length); // Zufällige Y-Koordinate
            Coordinate candidate = new Coordinate(x, y);
            // Überprüfen, ob die Koordinate bereits von der Schlange belegt ist
            if (!snake.collidesWith(candidate)) {
                apple = candidate; // Setzen der Apfelposition
                break; // Beenden der Schleife
            }
        }
    }
    public Cell[][] getField() {
        return field;
    }
    public int getFieldSize() {
        return field.length; // Annahme: Das Spielfeld ist quadratisch
    }
    public int calculateSpeed() {
        // Formel für Geschwindigkeit
        int baseSpeed = 15;
        int eatenApples = snake.getSize() -2;
        // Rückgabe der berechneten Geschwindigkeit
        int speed = baseSpeed + eatenApples;
        return speed;
    }
    public boolean update(Direction next){
        updateMoveDirection(next);
        boolean checked = moveSnake();
        updateField();

        return checked;
    }
    public void updateMoveDirection(Direction next) {
        // Überprüfen, ob die nächste Richtung entgegengesetzt zur aktuellen ist
        if (!moveDirection.isOpposite(next)) {
            moveDirection = next; // Aktualisieren der Bewegungsrichtung
        }
    }
    public boolean moveSnake() {
        // Berechnen der Koordinate des neuen Schlangenkopfs
        Coordinate newHead = calculateNewHead();
        // Prüfen, ob die Schlange mit sich selbst kollidieren würde
        if (snake.collidesWith(newHead)) {
            return false; // Spiel verloren
        }
        // Prüfen, ob der neue Kopf innerhalb des Spielfelds liegt
        if (!isInsideField(newHead)) {
            if (loopEdges) {
                // Koordinate entsprechend ersetzen (falls loopEdges gilt)
                newHead = adjustCoordinateForLoopEdges(newHead);
            } else {
                return false; // Spiel verloren
            }
        }
        // Prüfen, ob der Apfel gegessen wurde
        if (ateApple(newHead)) {
            snake.growIntoCell(newHead); // Schlange wachsen lassen
            placeApple(); // Neuen Apfel platzieren
        } else {
            snake.moveIntoCell(newHead); // Schlange bewegen
        }
        return true; // Spiel läuft weiter
    }
    // Prüfen, ob der neue Kopf innerhalb des Spielfelds liegt
    private boolean isInsideField(Coordinate newHead) {
        int x = newHead.getX();
        int y = newHead.getY();
        return x >= 0 && x < field.length && y >= 0 && y < field[0].length;
    }
    // Berechnen der Koordinate des neuen Schlangenkopfs
    private Coordinate calculateNewHead() {
        Coordinate currentHead = snake.getHead();
        int x = currentHead.getX();
        int y = currentHead.getY();

        // Je nach Bewegungsrichtung die Koordinaten anpassen
        switch (moveDirection) {
            case UP:
                x--;
                break;
            case DOWN:
                x++;
                break;
            case LEFT:
                y--;
                break;
            case RIGHT:
                y++;
                break;
            default:
                break;
        }
        return new Coordinate(x, y);
    }
    // Prüfen, ob der neue Kopf innerhalb des Spielfelds liegt (mit Loop-Edges)
    private Coordinate adjustCoordinateForLoopEdges(Coordinate newHead) {
        int x = newHead.getX();
        int y = newHead.getY();
        int fieldSize = field.length;

        // Wenn x außerhalb des Spielfelds, ersetzen durch den gegenüberliegenden Rand
        if (x < 0) {
            x = fieldSize - 1;
        } else if (x >= fieldSize) {
            x = 0;
        }

        // Wenn y außerhalb des Spielfelds, ersetzen durch den gegenüberliegenden Rand
        if (y < 0) {
            y = fieldSize - 1;
        } else if (y >= fieldSize) {
            y = 0;
        }

        return new Coordinate(x, y);
    }
    // Prüfen, ob der Apfel gegessen wurde
    private boolean ateApple(Coordinate newHead) {
        return newHead.equals(apple); // Vergleichen der neuen Kopf-Koordinate mit der Apfel-Koordinate
    }
    public void updateField() {
        // Erzeugen eines neuen 2D-Arrays für das Spielfeld
        Cell[][] newField = new Cell[field.length][field[0].length];

        // Befüllen des neuen Spielfelds mit den aktuellen Werten
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                newField[i][j] = Cell.EMPTY; // Kopieren der vorhandenen Zellen
            }
        }
        // Setzen der Schlange im neuen Spielfeld
        for (Coordinate segment : snake.getSegments()) {
            int x = segment.getX();
            int y = segment.getY();
            newField[y][x] = Cell.SNAKE;
        }
        // Setzen des Apfels im neuen Spielfeld
        int appleX = apple.getX();
        int appleY = apple.getY();
        newField[appleY][appleX] = Cell.APPLE;
        // Aktualisieren des field-Attributs mit dem neuen Spielfeld
        field = newField;
    }
}


