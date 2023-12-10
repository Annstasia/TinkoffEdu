package edu.hw9.task2.solution2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FindFilesByPredicate extends RecursiveTask<SpeedLinkedListMVP<Path>> {
    Path directoryToAnalyze;
    Predicate<Path> predicate;

    public FindFilesByPredicate(Path source, Predicate<Path> predicate) {
        directoryToAnalyze = source;
        this.predicate = predicate;
    }

    @Override
    public SpeedLinkedListMVP<Path> compute() {
        SpeedLinkedListMVP<Path> filteredFiles = new SpeedLinkedListMVP<>();
        List<FindFilesByPredicate> children = new ArrayList<>();
        try (Stream<Path> content = Files.list(directoryToAnalyze)) {
            content.parallel().forEach(path -> {
                if (Files.isDirectory(path)) {
                    FindFilesByPredicate child = new FindFilesByPredicate(path, predicate);
                    children.add(child);
                    child.fork();
                } else if (predicate.test(path)) {
                    filteredFiles.add(path);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (FindFilesByPredicate child : children) {
            filteredFiles.move(child.join());
        }
        return filteredFiles;
    }
}
