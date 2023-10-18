package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task6Test {
    @Test
    void countK() {
        Assertions.assertEquals(Task6.countK(3524), 3);
        Assertions.assertEquals(Task6.countK(6621), 5);
        Assertions.assertEquals(Task6.countK(6554), 4);
        Assertions.assertEquals(Task6.countK(1234), 3);
    }

    @Test
    void countZeroK() {
        Assertions.assertEquals(Task6.countK(6174), 0);
    }

}
