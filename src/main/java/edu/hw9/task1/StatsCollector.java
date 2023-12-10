package edu.hw9.task1;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StatsCollector {
    final Map<String, Deque<Double>> collector = new HashMap<>();

    public void push(String key, Double[] value) {
        synchronized (collector) {
            Deque<Double> stats = collector.get(key);
            if (stats == null) {
                stats = new ArrayDeque<>();
                collector.put(key, stats);
            }
            stats.addAll(Arrays.asList(value));
        }
    }

    public Set<Map.Entry<String, Deque<Double>>> stats() {
        synchronized (collector) {
            return collector.entrySet();
        }
    }

}
