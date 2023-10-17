package edu.hw1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task3Test {
    @Test
    void isNestableBase() {
        Assertions.assertTrue(Task3.isNestable(new int[]{1, 2, 3, 4}, new int[]{0, 6}));
        Assertions.assertTrue(Task3.isNestable(new int[]{3, 1}, new int[]{4, 0}));
    }

    @Test
    void isNotNestableBase() {
        Assertions.assertFalse(Task3.isNestable(new int[]{1, 2, 3, 4}, new int[]{1, 6}));
        Assertions.assertFalse(Task3.isNestable(new int[]{1, 2, 3, 4}, new int[]{0, 4}));
        Assertions.assertFalse(Task3.isNestable(new int[]{1, 2, 3, 4}, new int[]{2, 3}));
    }

    @Test
    void isNestableEmpty() {
        Assertions.assertTrue(Task3.isNestable(new int[]{}, new int[]{1, 2, 3}));
        Assertions.assertTrue(Task3.isNestable(new int[]{}, new int[]{}));
    }

    @Test
    void isNotNestableEmpty() {
        Assertions.assertFalse(Task3.isNestable(new int[]{1, 2, 3}, new int[]{}));
    }





}
