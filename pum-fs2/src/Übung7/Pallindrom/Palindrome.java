package Übung7.Pallindrom;

public class Palindrome {
    private final String[] input;
    private String[] palindromes;

    public Palindrome(String[] input) {
        this.input = input;
        this.palindromes = new String[0]; // Initialize an empty array for palindromes
        findPalindromes();
    }

    // Private method to check if a string is a palindrome (case-insensitive)
    public static boolean isPalindrome(String str) {
        String cleanedStr = str.toLowerCase().replaceAll("[^a-z]", "");
        int left = 0;
        int right = cleanedStr.length() - 1;
        while (left < right) {
            if (cleanedStr.charAt(left) != cleanedStr.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Private method to find palindromes in the input array
    private void findPalindromes() {
        for (String word : input) {
            if (isPalindrome(word)) {
                addPalindrome(word);
            }
        }
    }

    // Public method to add a new palindrome of a specified length
    public static String addNewPalindrome(int length) {
        StringBuilder palindromeBuilder = new StringBuilder();
        for (int i = 0; i < length / 2; i++) {
            char randomChar = (char) ('a' + Math.random() * 26);
            palindromeBuilder.append(randomChar);
        }
        String halfPalindrome = palindromeBuilder.toString();
        String newPalindrome = halfPalindrome + new StringBuilder(halfPalindrome).reverse().toString();

        return newPalindrome;
    }


    // Private method to add a palindrome to the palindromes array
    public void addPalindrome(String palindrome) {
        String[] updatedPalindromes = new String[palindromes.length + 1];
        System.arraycopy(palindromes, 0, updatedPalindromes, 0, palindromes.length);
        updatedPalindromes[palindromes.length] = palindrome;
        palindromes = updatedPalindromes;
    }

    // Getter methods for input and palindromes
    public String[] getInput() {
        return input;
    }

    public String[] getPalindromes() {
        return palindromes;
    }
}

