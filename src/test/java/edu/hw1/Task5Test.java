package edu.hw1;

import org.junit.jupiter.api.Test;

public class Task5Test {
    @Test
    void isPalindromeDescendant() {
        assert Task5.isPalindromeDescendant(11211230);
        assert Task5.isPalindromeDescendant(13001120);
        assert Task5.isPalindromeDescendant(23336014);
        assert Task5.isPalindromeDescendant(11);
        assert Task5.isPalindromeDescendant(1);
    }

    @Test
    void isNotPalindromeDescendant() {
        assert !Task5.isPalindromeDescendant(237);
        assert !Task5.isPalindromeDescendant(16);
    }


}
