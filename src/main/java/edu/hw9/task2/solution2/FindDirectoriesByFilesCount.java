package edu.hw9.task2.solution2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FindDirectoriesByFilesCount extends RecursiveTask<ResultInOneDirectory> {
    Path directoryToAnalyze;
    Predicate<Integer> barrier;

    FindDirectoriesByFilesCount(Path directory, Predicate<Integer> barrier) {
        this.directoryToAnalyze = directory;
        this.barrier = barrier;
    }

    @Override
    protected ResultInOneDirectory compute() {
        AtomicInteger currentDirectoryFilesCount = new AtomicInteger(0);
        List<FindDirectoriesByFilesCount> children = new ArrayList<>();
        try (Stream<Path> content = Files.list(directoryToAnalyze)) {
            content.parallel().forEach(path -> {
                if (Files.isDirectory(path)) {
                    FindDirectoriesByFilesCount child = new FindDirectoriesByFilesCount(path, barrier);
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
        SpeedLinkedListMVP<Path> allBigDirectories = new SpeedLinkedListMVP<>();
        for (FindDirectoriesByFilesCount child : children) {
            ResultInOneDirectory childResult = child.join();
            filesCountRecursive += childResult.filesInDirectory();
            allBigDirectories.move(childResult.allBigDirectories());
        }
        if (barrier.test(filesCountRecursive)) {
            allBigDirectories.add(directoryToAnalyze);
        }
        return new ResultInOneDirectory(filesCountRecursive, allBigDirectories);
    }
}
