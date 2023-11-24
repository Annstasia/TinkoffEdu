package edu.hw7;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {
    private Task1() {
    }

    public static int task1(int aimCount) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        Queue<Thread> threads = new ArrayDeque<>();
        for (int i = 0; i < aimCount; ++i) {
            Thread thread = new Thread(() -> counter.addAndGet(1));
            threads.add(thread);
            thread.start();
        }
        while (!threads.isEmpty()) {
            threads.poll().join();
        }
        return counter.get();
    }
}
