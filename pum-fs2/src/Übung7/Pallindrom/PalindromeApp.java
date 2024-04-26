package Übung7.Pallindrom;

import java.util.Scanner;
public class PalindromeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for comma-separated list of words
        System.out.println("Bitte geben Sie eine komma-separierte Liste von Wörtern ein:");
        String inputLine = scanner.nextLine();

        // Check if input contains a comma
        /*
        if (!inputLine.contains(",")) {
            System.out.println("Ungültige Eingabe. Bitte verwenden Sie ein Komma, um die Wörter zu trennen.");
            return;
        }

         */

        // Split input into an array of words
        String[] inputWords = inputLine.split(",");

        // Create a Palindrome object
        Palindrome palindrome = new Palindrome(inputWords);

        // Ask user if they want to generate new palindromes
        boolean continueGenerating = true;
        while (continueGenerating) {
            System.out.println("Möchten Sie ein neues Palindrom generieren? (J/N)");
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("j")) {
                System.out.println("Bitte geben Sie die gewünschte Länge des Palindroms ein:");
                int length = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                palindrome.addPalindrome(Palindrome.addNewPalindrome(length));

            } else if (response.equals("n")) {
                continueGenerating = false;
            } else {
                System.out.println("Ungültige Eingabe. Bitte antworten Sie mit J oder N.");
            }
        }

        // Print all valid palindromes
        String[] palindromes = palindrome.getPalindromes();
        if (palindromes.length > 0) {
            System.out.println("Gültige Palindrome: " + String.join(", ", palindromes));
        } else {
            System.out.println("Keine gültigen Palindrome gefunden.");
        }
    }
}

