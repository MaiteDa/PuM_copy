package Übung7.Snake;
//Enum Cell
enum Cell {
    EMPTY,
    APPLE,
    SNAKE
}
//Enum Direction
enum Direction {
    UP, DOWN, LEFT, RIGHT, none;
    // Check if the given direction is opposite of the current direction
    public boolean isOpposite(Direction other) {
        // Check if the opposite direction matches the given direction
        return getOpposite() == other;
    }
    // Get the opposite direction
    public Direction getOpposite() {
        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            default:
                return none;
        }
    }
}
record Coordinate(int x, int y) {
    // Getter for x-coordinate
    public int getX() {
        return x;
    }
    // Getter for y-coordinate
    public int getY() {
        return y;
    }
}
public class Snake {
    private Coordinate[] segments; // Array, um die Koordinaten der Schlange zu speichern

    // Konstruktor: Erstellt eine Schlange mit den gegebenen Kopf- und Schwanzkoordinaten
    public Snake(Coordinate head, Coordinate tail) {
        segments = new Coordinate[] {head, tail};
        // Später können weitere Segmente hinzugefügt werden, wenn die Schlange wächst
    }
    // Getter für die Länge der Schlange
    public int getSize() {
        return segments.length;
    }
    // Getter für den Kopf der Schlange
    public Coordinate getHead() {
        return segments[0];
    }
    // Getter für das segments-Array
    public Coordinate[] getSegments() {
        return segments;
    }
    // Überprüft, ob ein Segment der Schlange auf der gegebenen Koordinate liegt
    // Ausnahme: Die Schlange kollidiert nie mit ihrem letzten Segment
    public boolean collidesWith(Coordinate c) {
        for (int i = 0; i < segments.length - 1; i++) {
            if (segments[i].equals(c)) {
                return true;
            }
        }
        return false;
    }
    // Verschiebt alle Segmente im Array, sodass c der neue Kopf der Schlange ist
    public void moveIntoCell(Coordinate c) {
        // Verschieben der Segmente:
        // 1. Verschieben des Schwanzsegments an die Position des neuen Kopfes
        for (int i = segments.length - 1; i > 0; i--) {
            segments[i] = segments[i - 1];
        }
        // 2. Setzen des neuen Kopfsegments auf die gegebene Koordinate
        segments[0] = c;
    }
    // Lässt die Schlange wachsen, sodass c der neue Kopf der Schlange ist
    public void growIntoCell(Coordinate c) {
        // Erstellen eines neuen Arrays mit der Länge um 1 größer als das aktuelle segments-Array
        Coordinate[] newSegments = new Coordinate[segments.length + 1];

        // Kopieren der aktuellen Segmente in das neue Array
        System.arraycopy(segments, 0, newSegments, 1, segments.length);

        // Setzen des neuen Kopfsegments auf die gegebene Koordinate
        newSegments[0] = c;

        // Aktualisieren des segments-Arrays
        segments = newSegments;
    }
}

