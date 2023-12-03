package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.NotNull;

public class FixedThreadPool implements ThreadPool {
    BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();
    Thread[] threads;
    AtomicBoolean wasShutDown = new AtomicBoolean(false);



    public FixedThreadPool(int threadCount) {
        threads = new Thread[threadCount];
    }

    private void work() {
        try {
            Runnable task;
            do {
                task = tasks.take();
                task.run();
            } while (!(task instanceof Poison));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start() {
        for (int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread(this::work);
            threads[i].start();
        }
    }

    @Override
    public void execute(@NotNull Runnable runnable) throws RejectedExecutionException {
        if (!wasShutDown.get()) {
            tasks.add(runnable);
        } else {
            throw new RejectedExecutionException();
        }
    }

    public void shutDown() {
        wasShutDown.set(true);
    }

    @Override
    public void close() throws InterruptedException {
        shutDown();
        for (int i = 0; i < threads.length; ++i) {
            tasks.add(new Poison());
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }

    static class Poison implements Runnable {
        @Override
        public void run() {
        }
    }
}
