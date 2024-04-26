package Übung8.Binärzahlen;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

    public class BinMathTest {
        /*
        Test
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
        Test
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
        Test
         */
        @Test
        void testCountOccupiedNeighborsSingleEmptySeat() {
            Auditorium auditorium = new Auditorium(3, 4);
            auditorium.getSeats()[1][1] = true; // Ein Sitz ist besetzt
            int occupiedNeighbors = auditorium.countOccupiedNeighbors(0, 0);
            assertEquals(1, occupiedNeighbors); // Erwartet wird, dass nur ein Nachbar besetzt ist
        }

        /*
        Test
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
        Test
         */
        @Test
        void testOutOfBoundsSeatCoordinates() {
            Auditorium auditorium = new Auditorium(3, 3);
            assertThrows(IndexOutOfBoundsException.class, () -> auditorium.getSeats()[3][3] = true);
        }
    }


