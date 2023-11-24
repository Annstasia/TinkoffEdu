package edu.hw7.task4;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SolutionAnalyzer {
    private static final int N = (int) (1e6);
    private static final int THREADS_COUNT = 100;
    private static final int ITERATIONS = 100;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int TEN_MILLIONS = (int) (1e7);
    private static final int HUNDRED_MILLIONS = (int) (1e8);
    private static final int MILLIARD = (int) (1e9);

    private SolutionAnalyzer() {
    }

    public static void speedTest() throws InterruptedException {
        List<List<Long>> times = new ArrayList<>(THREADS_COUNT);
        for (int i = 0; i < THREADS_COUNT; ++i) {
            times.add(new ArrayList<>());
        }
        for (int i = 0; i < ITERATIONS + 1; ++i) {
            for (int k = 0; k < THREADS_COUNT; ++k) {
                if (k == 0) {
                    long start = System.nanoTime();
                    PiCounter.oneThread(N);
                    times.get(k).add(System.nanoTime() - start);
                } else {
                    long start = System.nanoTime();
                    PiCounter.kThread(k + 1, N);
                    times.get(k).add(System.nanoTime() - start);
                }
            }
        }
        // по-хорошему - вывод в файл/анализ в java, но я ленивая, да и
        // задание не про это, поэтому
        // просто копирую в python
        LOGGER.info(times);
    }

    public static void absError() throws InterruptedException {
        for (int n : List.of(TEN_MILLIONS, HUNDRED_MILLIONS, MILLIARD)) {
            LOGGER.info(PiCounter.multiThread(n));
            LOGGER.info("N = " + N + " Square error = " + Math.abs(
                StrictMath.PI - PiCounter.multiThread(n)));
        }
    }
}
