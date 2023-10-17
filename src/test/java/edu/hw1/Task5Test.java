package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task5Test {
    @Test
    void isPalindromeDescendant() {
        Assertions.assertTrue(Task5.isPalindromeDescendant(11211230));
        Assertions.assertTrue(Task5.isPalindromeDescendant(13001120));
        Assertions.assertTrue(Task5.isPalindromeDescendant(23336014));
        Assertions.assertTrue(Task5.isPalindromeDescendant(11));
        Assertions.assertTrue(Task5.isPalindromeDescendant(1));
    }

    @Test
    void isNotPalindromeDescendant() {
        Assertions.assertFalse(Task5.isPalindromeDescendant(237));
        Assertions.assertFalse(Task5.isPalindromeDescendant(16));
    }
}
