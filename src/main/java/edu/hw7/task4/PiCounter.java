package edu.hw7.task4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.LongStream;

public class PiCounter {
    private PiCounter() {
    }

    private final static double R = 0.5;
    private final static double CENTER_X = 0.5;
    private final static double CENTER_Y = 0.5;
    private final static int MY_CORES_NUMBER = 6;
    private final static int PI_COEFFICIENT = 4;
    static TriFunction<AtomicLong, Integer, Integer, Runnable> countFunction =
        (counter, start, end) -> () -> counter.addAndGet((LongStream.range(
            start,
            end
        ).map((i) -> isInCircle(
            ThreadLocalRandom.current().nextDouble(),
            ThreadLocalRandom.current().nextDouble(),
            CENTER_X,
            CENTER_Y,
            R
        ) ? 1 : 0).reduce(Long::sum)).orElse(0));

    static boolean isInCircle(
        double x, double y, double centerX, double centerY, double r
    ) {
        return (x - centerX) * (x - centerX) + (y - centerY) * (y - centerY)
            <= r * r;
    }

    static double countPi(long value, int n) {
        return PI_COEFFICIENT * (double) value / n;
    }

    public static double oneThread(int n) {
        return countPi(LongStream.range(0, n).map((i) -> isInCircle(
            ThreadLocalRandom.current().nextDouble(),
            ThreadLocalRandom.current().nextDouble(),
            CENTER_X,
            CENTER_Y,
            R
        ) ? 1 : 0).reduce(Long::sum).orElse(0), n);
    }

    public static double kThread(int k, int n) throws InterruptedException {
        List<Thread> threads = new ArrayList<>(k);
        int delta = n / k;
        AtomicLong counter = new AtomicLong(0);
        int offset = 0;
        for (int i = 0; i < k; ++i) {
            int startOffset = i * delta;
            int endOffset = (i + 1 != k ? startOffset + delta : n);
            Thread handleDeltaPoints = new Thread(countFunction.apply(
                counter,
                startOffset,
                endOffset
            ));
            handleDeltaPoints.start();
            threads.add(handleDeltaPoints);
        }
        for (Thread thread : threads) {
            thread.join();
        }
        return countPi(counter.get(), n);
    }

    public static double multiThread(int n) throws InterruptedException {
        return kThread(MY_CORES_NUMBER, n);
    }
}
