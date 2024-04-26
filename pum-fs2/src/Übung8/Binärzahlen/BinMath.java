package Übung8.Binärzahlen;

/*
Erik Engler (5143148)
Alexander Winkler (5141652)
Maite Dalchow (5138885)

Hauptabgabe
 */

import java.util.Arrays;

public class BinMath {

    // Methode zur Konvertierung einer Dezimalzahl in eine Binärzahl mit gegebener Größe
    public static int[] decToBin(int dec, int size) {
        // Überprüfung, ob die Größe gültig ist (maximal 32 Bit für int-Datentyp)
        if (size <= 0 || size > 32) {
            return null; // Rückgabe von null bei ungültiger Größe
        }

        // Überprüfung, ob die Dezimalzahl innerhalb des Wertebereichs der gegebenen Binärgröße liegt
        if (dec < -Math.pow(2, size - 1) || dec >= Math.pow(2, size - 1)) {
            return null; // Rückgabe von null bei ungültigen Eingaben
        }

        // Initialisierung des Binär-Arrays
        int[] binary = new int[size];
        int absDec = Math.abs(dec);

        // Konvertierung der Dezimalzahl in Binärform
        for (int i = size - 1; i >= 0; i--) {
            binary[i] = absDec % 2;
            absDec /= 2;
        }

        // Zweierkomplement für negative Zahlen
        if (dec < 0) {
            for (int i = 0; i < size; i++) {
                binary[i] = (binary[i] == 0) ? 1 : 0;
            }
            int carry = 1;
            for (int i = size - 1; i >= 0; i--) {
                binary[i] += carry;
                if (binary[i] == 2) {
                    binary[i] = 0;
                    carry = 1;
                } else {
                    carry = 0;
                    break;
                }
            }
        }

        return binary; // Rückgabe des Binär-Arrays
    }

    // Methode zur Konvertierung einer Binärzahl in eine Dezimalzahl
    public static int binToDec(int[] bin) {
        int decimal = 0;
        boolean isNegative = bin[0] == 1; // Überprüfung, ob die Zahl negativ ist

        // Konvertierung der Binärzahl in Dezimalform
        for (int i = 0; i < bin.length; i++) {
            decimal += bin[i] * Math.pow(2, bin.length - i - 1);
        }

        return isNegative ? -decimal : decimal; // Rückgabe der Dezimalzahl, ggf. negativ
    }

    // Methode zur Addition zweier Binärzahlen
    public static int[] addBinaryNumbers(int[] first, int[] second) {
        // Vorzeichenerweiterung, um sicherzustellen, dass beide Zahlen dieselbe Länge haben
        int maxLength = Math.max(first.length, second.length);
        int[] extendedFirst = extendBinaryNumber(first, maxLength);
        int[] extendedSecond = extendBinaryNumber(second, maxLength);

        int carry = 0; // Übertragsbit
        int[] result = new int[maxLength + 1]; // Ergebnis-Array mit zusätzlichem Platz für Überlauf

        // Durchführung der Binäraddition
        for (int i = maxLength - 1; i >= 0; i--) {
            int sum = extendedFirst[i] + extendedSecond[i] + carry;
            result[i + 1] = sum % 2; // Aktualisierung des Ergebnis-Arrays
            carry = sum / 2; // Berechnung des Übertrags
        }

        result[0] = carry; // Setzen des Übertrags als erstes Element im Ergebnis-Array

        return result; // Rückgabe des Ergebnis-Arrays
    }

    // Hilfsmethode zur Vorzeichenerweiterung einer Binärzahl
    private static int[] extendBinaryNumber(int[] binary, int length) {
        int[] extendedBinary = new int[length];
        Arrays.fill(extendedBinary, 0); // Initialisierung mit Nullen

        // Kopieren der ursprünglichen Binärzahl in das erweiterte Array
        System.arraycopy(binary, 0, extendedBinary, length - binary.length, binary.length);

        // Vorzeichenerweiterung für negative Zahlen
        if (binary[0] == 1) {
            Arrays.fill(extendedBinary, 0, length - binary.length, 1); // Setzen des Vorzeichens
        }

        return extendedBinary; // Rückgabe der erweiterten Binärzahl
    }
}


/*
import java.util.Arrays;

public class BinMath {

    /*
    Methode zur Konvertierung einer Dezimalzahl in eine Binärzahl (mit gegebener Größe)
     */
/*
    public static int[] decToBin(int dec, int size) {
        // Überprüfung, on Dezimalzahl innerhalb des Wertebereichs der gegebenen Binärgröße liegt
        if (dec < -Math.pow(2, size - 1) || dec >= Math.pow(2, size - 1)) {
            return null; // Rückgabe null für ungültiges Ergebnis
        }

        // Initialisierung des Binär-Arrays
        int[] binary = new int[size];
        int absDec = Math.abs(dec);

        // Konvertierung der Dezimalzahl in Binärform
        for (int i = size - 1; i >= 0; i--) {
            binary[i] = absDec % 2;
            absDec /= 2;
        }

        // Zweierkomplement für negative Zahlen
        if (dec < 0) {
            for (int i = 0; i < size; i++) {
                binary[i] = (binary[i] == 0) ? 1 : 0;
            }
            int carry = 1;
            for (int i = size - 1; i >= 0; i--) {
                binary[i] += carry;
                if (binary[i] == 2) {
                    binary[i] = 0;
                    carry = 1;
                } else {
                    carry = 0;
                    break;
                }
            }
        }

        return binary;
    }

    /*
    Methode zur Konvertierung des übergebenen Zweierkomplements in die Dezimalzahl
     */
/*
    public static int binToDec(int[] bin) {
        int decimal = 0;
        boolean isNegative = bin[0] == 1;   // Überprüfen, ob die Zahl negativ ist

        // Konvertierung der Binärzahl in Dezimalzahl
        for (int i = 0; i < bin.length; i++) {
            decimal += bin[i] * Math.pow(2, bin.length - i - 1);
        }

        return isNegative ? -decimal : decimal; // Rückgabe Dezimalzahl, ggf. negativ
    }

    /*
    Methode zur Addition zweier Binärzahlen (als Zweierkomplement)
     */
/*
    public static int[] addBinaryNumbers(int[] first, int[] second) {

        // Vorzeichenerweiterung, um sicherzustellen, dass beide Zahlen dieselbe Länge haben
        int[] extendedFirst = Arrays.copyOf(first, Math.max(first.length, second.length));
        int[] extendedSecond = Arrays.copyOf(second, Math.max(first.length, second.length));

        int carry = 0; // Übertragsbit
        int[] result = new int[extendedFirst.length];

        // Durchführung der Binäraddition
        for (int i = result.length - 1; i >= 0; i--) {
            int sum = extendedFirst[i] + extendedSecond[i] + carry;
            result[i] = sum % 2;    // Aktualisierung des Eingabe-Arrays
            carry = sum / 2;    // Berechnung des Übertrags
        }

        // Überlaufprüfung
        if (carry == 1) {
            // Wenn das Übertragsbit am Ende der Addition noch gesetzt ist, liegt ein Überlauf vor
            return null;    // Rückgabe von null, da das Ergebnis zu groß für die gegebene Binärgröße ist
        }

        return result;
    }
}
*/