package Übung7.Auditorium;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class AuditoriumTest {
    /*
    Test für die Funktionalität der Methode print()
     */
    @Test
    void testPrint() {
        Auditorium auditorium = new Auditorium(3, 4);
        auditorium.getSeats()[0][0] = true;
        auditorium.getSeats()[0][1] = true;
        auditorium.getSeats()[1][0] = true;
        auditorium.print();
    }

    /*
    Test für leeres Auditorium: Überprüfen, ob ein neues Auditorium mit Nullen
    initialisiert wird, wenn keine Sitze besetzt sind.
     */
    @Test
    void testEmptyAuditorium() {
        Auditorium auditorium = new Auditorium(3, 4);
        boolean[][] seats = auditorium.getSeats();
        for (boolean[] row : seats) {
            for (boolean seat : row) {
                assertFalse(seat); // Erwartet wird, dass alle Sitze leer sind
            }
        }
    }

    /*
    Test für einzelnen leeren Sitz: Überprüfen,
    ob ein einzelner leerer Sitz korrekt gezählt wird.
     */
    @Test
    void testCountOccupiedNeighborsSingleEmptySeat() {
        Auditorium auditorium = new Auditorium(3, 4);
        auditorium.getSeats()[1][1] = true; // Ein Sitz ist besetzt
        int occupiedNeighbors = auditorium.countOccupiedNeighbors(0, 0);
        assertEquals(1, occupiedNeighbors); // Erwartet wird, dass nur ein Nachbar besetzt ist
    }

    /*
    Test für benachbarte besetzte Sitze:
    Überprüfen, ob die Methode countOccupiedNeighbors()
    die Anzahl der besetzten Nachbarn korrekt zählt,
    wenn mehrere Sitze besetzt sind.
     */
    @Test
    void testCountOccupiedNeighborsMultipleOccupiedSeats() {
        Auditorium auditorium = new Auditorium(3, 4);
        auditorium.getSeats()[0][0] = true;
        auditorium.getSeats()[0][1] = true;
        auditorium.getSeats()[1][0] = true;
        int occupiedNeighbors = auditorium.countOccupiedNeighbors(1, 1);
        assertEquals(3, occupiedNeighbors); // Erwartet wird, dass drei Nachbarn besetzt sind
    }

    /*
    Erstellt ein Auditorium mit 3 Reihen und 3 Spalten.
    Der Test versucht dann, auf einen Sitz außerhalb der Grenzen
    des Auditoriums (bei Zeile 3 und Spalte 3) zuzugreifen und erwartet,
    dass eine IndexOutOfBoundsException ausgelöst wird.
     */
    @Test
    void testOutOfBoundsSeatCoordinates() {
        Auditorium auditorium = new Auditorium(3, 3);
        assertThrows(IndexOutOfBoundsException.class, () -> auditorium.getSeats()[3][3] = true);
    }
}
