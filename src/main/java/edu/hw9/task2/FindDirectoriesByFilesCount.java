package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FindDirectoriesByFilesCount extends RecursiveTask<Integer> {
    Path directoryToAnalyze;
    Predicate<Integer> barrier;
    ConcurrentLinkedDeque<Path> filteredDirectories;

    FindDirectoriesByFilesCount(
        Path directory,
        Predicate<Integer> barrier,
        ConcurrentLinkedDeque<Path> filteredDirectories
    ) {
        this.directoryToAnalyze = directory;
        this.barrier = barrier;
        this.filteredDirectories = filteredDirectories;
    }

    @Override
    protected Integer compute() {
        AtomicInteger currentDirectoryFilesCount = new AtomicInteger(0);
        List<FindDirectoriesByFilesCount> children = new ArrayList<>();
        try (Stream<Path> content = Files.list(directoryToAnalyze)) {
            content.parallel().forEach(path -> {
                if (Files.isDirectory(path)) {
                    FindDirectoriesByFilesCount child =
                        new FindDirectoriesByFilesCount(path, barrier, filteredDirectories);
                    children.add(child);
                    child.fork();
                } else {
                    currentDirectoryFilesCount.incrementAndGet();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int filesCountRecursive = currentDirectoryFilesCount.intValue();
        for (FindDirectoriesByFilesCount child : children) {
            int childResult = child.join();
            filesCountRecursive += childResult;
        }
        if (barrier.test(filesCountRecursive)) {
            filteredDirectories.add(directoryToAnalyze);
        }
        return filesCountRecursive;
    }
}
