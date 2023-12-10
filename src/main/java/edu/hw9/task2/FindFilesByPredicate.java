package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FindFilesByPredicate extends RecursiveTask<Object> {
    Path directoryToAnalyze;
    Predicate<Path> predicate;
    ConcurrentLinkedDeque<Path> filteredFiles;

    public FindFilesByPredicate(Path source, Predicate<Path> predicate, ConcurrentLinkedDeque<Path> filteredFiles) {
        directoryToAnalyze = source;
        this.predicate = predicate;
        this.filteredFiles = filteredFiles;
    }

    @Override
    public Object compute() {
        List<FindFilesByPredicate> children = new ArrayList<>();
        try (Stream<Path> content = Files.list(directoryToAnalyze)) {
            content.parallel().forEach(path -> {
                if (Files.isDirectory(path)) {
                    FindFilesByPredicate child = new FindFilesByPredicate(path, predicate, filteredFiles);
                    children.add(child);
                    child.fork();
                } else if (predicate.test(path)) {
                    filteredFiles.add(path);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
