package edu.hw3;

import java.util.Comparator;
import java.util.TreeMap;

public class Task7<T, U> extends TreeMap<T, U> {
    Comparator<? super T> comparator = null;

    public Task7() {
        this((first, second) -> ((Comparable<T>) first).compareTo(second));
    }

    public Task7(Comparator<? super T> comparator) {
        super(Comparator.nullsFirst(comparator));
    }

    public void add(T key, U value) {
        put(key, value);
    }

    public boolean contains(T key) {
        return containsKey(key);
    }
}
