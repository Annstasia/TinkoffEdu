package edu.hw7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    void task1Test() throws InterruptedException {
        for (int i = 0; i < 10000; i+=1000) {
            Assertions.assertEquals(Task1.task1(i), i);
        }
    }
}
