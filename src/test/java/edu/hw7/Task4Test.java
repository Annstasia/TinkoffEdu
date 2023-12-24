package edu.hw7;

import edu.hw7.task4.PiCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task4Test {
    private final static int N = 1000000;

    @Test
    void testOneThread() {
        double pi = PiCounter.oneThread(N);
        Assertions.assertTrue(pi < 3.5 && pi > 2.8);
    }

    @Test
    void testDefaultThread() throws InterruptedException {
        double pi = PiCounter.multiThread(N);
        Assertions.assertTrue(pi < 3.5 && pi > 2.8);
    }

    @Test
    void testKThread() throws InterruptedException {
        double pi = PiCounter.kThread(10, N);
        Assertions.assertTrue(pi < 3.5 && pi > 2.8);
    }
}
