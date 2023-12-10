package edu.hw9.task2;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;

public class Task2 {
    static final int DEFAULT_LARGE = 1000;

    private Task2() {
    }

    public static List<Path> findLargeDirectories(Path source) {
        return findLargeDirectories(source, filesCount -> filesCount > DEFAULT_LARGE);
    }

    public static List<Path> findLargeDirectories(Path source, Predicate<Integer> barrier) {
        ConcurrentLinkedDeque<Path> largeDirectories = new ConcurrentLinkedDeque<>();
        FindDirectoriesByFilesCount searcher = new FindDirectoriesByFilesCount(source, barrier, largeDirectories);
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            forkJoinPool.invoke(searcher);
            return new ArrayList<>(largeDirectories);
        }
    }

    public static List<Path> findFilesByPredicate(Path source, Predicate<Path> predicate) {
        ConcurrentLinkedDeque<Path> files = new ConcurrentLinkedDeque<>();
        FindFilesByPredicate searcher = new FindFilesByPredicate(source, predicate, files);
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            forkJoinPool.invoke(searcher);
            return new ArrayList<>(files);
        }
    }
}
