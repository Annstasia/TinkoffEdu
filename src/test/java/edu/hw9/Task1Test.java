package edu.hw9;

import edu.hw9.task1.StatsCollector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Task1Test {
    @Test void concurrentAddTest() {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        StatsCollector collector = new StatsCollector();
        int metricNamesCount = 1000;
        int addsPerMetric = 10000;
        for (int i = 0; i < metricNamesCount; ++i) {
            for (int j = 0; j < addsPerMetric; ++j) {
                collector.push("name" + i, new Double[]{0.0, 1.0, 2.0, 3.0, 4.0, 5.0});
            }
        }
        pool.close();
        Assertions.assertEquals(collector.stats().size(), metricNamesCount);
        for (var keyValue : collector.stats()) {
            Assertions.assertEquals(keyValue.getValue().size(), addsPerMetric * 6);
        }
    }
    @Test
    void concurrentAndAndAggregateTest() {
        StatsCollector collector = new StatsCollector();
        ExecutorService pool = Executors.newFixedThreadPool(6);
        ExecutorService pool2 = Executors.newFixedThreadPool(6);
        collector.push("name", new Double[]{1.0, 2.0, 3.0});
        for (int i = 0; i < 10000; ++i) {
            int ind = i;
            pool.execute(()->{
                collector.push("name", new Double[]{1.0, 2.0, 3.0});
            });
        }
        double prevSum = 0.0;
        double prevAverage = 2.0;
        double prevMin = 0.0;
        double prevMax = 0.0;
        for (int i = 0; i < 10000; ++i) {
            double sum = prevSum;
            double average = prevAverage;
            double min = prevMin;
            double max = prevMax;
            pool2.execute(()->{
                Assertions.assertEquals(1, collector.stats().size());
                for (Map.Entry<String, Deque<Double>> keyValue : collector.stats()) {
                    Assertions.assertEquals("name", keyValue.getKey());
                    double statSum = keyValue.getValue().stream().reduce(Double::sum).orElse(0.0);
                    Assertions.assertTrue(statSum >= sum);
                    int n = keyValue.getValue().size();
                    Assertions.assertTrue(Math.abs(statSum / n - average) < 0.001);
                    Assertions.assertTrue(Math.abs(
                        keyValue.getValue().stream().reduce(Double::min).orElse(1000.0) - 1) < 0.001);
                    Assertions.assertTrue(keyValue.getValue().stream().reduce(Double::max).orElse(0.0) - 3 < 0.001);
                }
            });
        }

        pool.close();
        pool2.close();
    }
}
