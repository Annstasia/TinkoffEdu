package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task2Test {
    @Test
    void countDigits() {
        Assertions.assertEquals(Task2.countDigits(4666), 4);
    }

    @Test
    void countDigitsOfZero() {
        assert Task2.countDigits(0) == 1;
    }

    @Test
    void countDigitsOfNegative() {
        Assertions.assertEquals(Task2.countDigits(-4666), 4);
    }

}
