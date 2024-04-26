package Übung7.Pallindrom;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PalindromeTest {

    @Test
    void testValidPalindrome8() {
        String[] inputWords = {"rentner"};
        Palindrome palindrome = new Palindrome(inputWords);
        String[] palindromes = palindrome.getPalindromes();
        assertEquals(1, palindromes.length);
        assertEquals("rentner", palindromes[0]);
    }
    @Test
    void testValidPalindrome5() {
        String[] inputWords = {"level"};
        Palindrome palindrome = new Palindrome(inputWords);
        String[] palindromes = palindrome.getPalindromes();
        assertEquals(1, palindromes.length);
        assertEquals("level", palindromes[0]);
    }

    @Test
    void testValidPalindrome3() {
        String[] inputWords = {"bob"};
        Palindrome palindrome = new Palindrome(inputWords);
        String[] palindromes = palindrome.getPalindromes();
        assertEquals(1, palindromes.length);
        assertEquals("bob", palindromes[0]);
    }

    @Test
    void testInvalidPalindrome() {
        String[] inputWords = {"hello"};
        Palindrome palindrome = new Palindrome(inputWords);
        String[] palindromes = palindrome.getPalindromes();
        assertEquals(0, palindromes.length);
    }

    @Test
    void testMixedCasePalindrome() {
        String[] inputWords = {"Racecar"};
        Palindrome palindrome = new Palindrome(inputWords);
        String[] palindromes = palindrome.getPalindromes();
        assertEquals(1, palindromes.length);
        assertEquals("Racecar", palindromes[0]);
    }

    @Test
    void testEmptyInput() {
        String[] inputWords = {};
        Palindrome palindrome = new Palindrome(inputWords);
        String[] palindromes = palindrome.getPalindromes();
        assertEquals(0, palindromes.length);
    }


    @Test
    void testGenerateAndVerifyPalindrome() {
        // Generiere ein neues Palindrom der Länge 6
        String newPalindrome = Palindrome.addNewPalindrome(6);

        // Überprüfe, ob das generierte Palindrom tatsächlich ein Palindrom ist
        assertTrue(Palindrome.isPalindrome(newPalindrome));
    }
}
