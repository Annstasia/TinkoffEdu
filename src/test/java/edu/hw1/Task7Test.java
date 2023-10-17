package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task7Test {

    @Test
    void rotateRight() {
        Assertions.assertEquals(Task7.rotateRight(8, 1), 4);
    }

    @Test
    void rotateLeft() {
        Assertions.assertEquals(Task7.rotateLeft(16, 1), 1);
        Assertions.assertEquals(Task7.rotateLeft(17, 2), 6);
    }
}
