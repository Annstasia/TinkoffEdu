package edu.hw9.task1;

import java.util.Arrays;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static final Double[] DATA_PORTION = new Double[] {1.0, 2.0, 3.0};
    static final int DEFAULT_THREADS_NUMBER = 6;
    static final int DEFAULT_PRODUCERS_NUMBER = 10000;
    static final int DEFAULT_CONSUMERS_NUMBER = 10000;
    static final double DOUBLE_ERR = 0.001;
    static final String METRIC_NAME = "name";

    private Main() {
    }

    public static void main(String[] args) {
        StatsCollector collector = new StatsCollector();
        ExecutorService pool = Executors.newFixedThreadPool(DEFAULT_THREADS_NUMBER);
        ExecutorService pool2 = Executors.newFixedThreadPool(DEFAULT_THREADS_NUMBER);
        collector.push(METRIC_NAME, DATA_PORTION);
        AtomicInteger cnt = new AtomicInteger(0);
        for (int i = 0; i < DEFAULT_PRODUCERS_NUMBER; ++i) {
            int ind = i;
            pool.execute(() -> {
                collector.push(METRIC_NAME, DATA_PORTION);
            });
        }
        AtomicInteger prevSum = new AtomicInteger(0);
        AtomicInteger prevAverage = new AtomicInteger(0);
        double prevMin = Arrays.stream(DATA_PORTION).min(Double::compareTo).get();
        double prevMax = Arrays.stream(DATA_PORTION).max(Double::compareTo).get();
        for (int i = 0; i < DEFAULT_CONSUMERS_NUMBER; ++i) {
            pool2.execute(() -> {
                for (Map.Entry<String, Deque<Double>> keyValue : collector.stats()) {
                    assert keyValue.getKey().equals(METRIC_NAME);
                    double statSum = keyValue.getValue().stream().reduce(Double::sum).orElse(0.0);
                    assert statSum >= prevSum.getAndSet((int) statSum);
                    int n = keyValue.getValue().size();
                    assert Math.abs(statSum / n - prevAverage.getAndSet((int) statSum / n)) < DOUBLE_ERR;
                    assert keyValue.getValue().stream().reduce(Double::min).orElse(0.0) == prevMin;
                    assert keyValue.getValue().stream().reduce(Double::max).orElse(0.0) == prevMax;
                }
            });
        }

        pool.close();
        pool2.close();

    }
}
