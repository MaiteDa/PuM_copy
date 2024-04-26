package Übung7.Auditorium;

import java.util.Arrays;

public class Auditorium {

    private final int rows;
    private final int columns;
    private boolean[][] seats;

    /*
    Konstruktor
     */
    Auditorium (int rows, int columns){
        this.rows=rows;
        this.columns=columns;
        this.seats=new boolean[rows][columns];
    }

    /*
    Methode zum Anwenden des gesuchten Verfahrens der Sitzordnung
     */
    public int solveSeatProblem(){
        int rounds = 0;
        boolean changes;

        do {
            changes = false;
            boolean[][] newSeats = new boolean[rows][columns];

            for(int i = 0; i < rows; i++){
                for(int j = 0; j < columns; j++){
                    int neighbors = countOccupiedNeighbors(i, j);

                    if(!seats[i][j] && neighbors == 0){
                        newSeats[i][j] = true;
                        changes = true;
                    } else if (seats[i][j] && neighbors >= 4){
                        newSeats[i][j] = false;
                        changes = true;
                    } else{
                        newSeats[i][j] = seats[i][j];
                    }
                }
            }
            seats = newSeats;
            rounds++;

        }while (changes);

        return rounds;
    }

    /*
    Hilfsmethode, um umliegende Nachbarn zu zählen
     */
    public int countOccupiedNeighbors(int row, int col){
        int count = 0;
        for(int i = row - 1; i <=row + 1; i++){
            for(int j = col - 1; j <= col + 1; j++){
                if (isValidPosition(i, j) && !(i == row && j == col) && seats[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    /*
    Methode um Sitzordnung auszugeben
     */
    public void print(){
        for (boolean[] row : seats){
            for (boolean seat : row){
                System.out.print(seat ? '#' : '.');
            }
            System.out.println();
        }
    }

    /*
    Hilfsmethode zur Überprüfung, ob eine Position gültig ist
     */
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < columns;
    }

    //Getter für Testzwecke
    public boolean[][] getSeats() {
        return seats;
    }
}

