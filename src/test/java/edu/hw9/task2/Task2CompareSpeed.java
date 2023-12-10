package edu.hw9.task2;

import edu.hw9.task2.Task2;
//import edu.hw9.task2.solution2;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Task2CompareSpeed {
    Path source;
    Path dir1;
    Path dir2;
    List<Path> allFiles;
    void initSource() throws IOException {
        source = Files.createTempDirectory("temp");
        dir1 = Files.createTempDirectory(source, "dir1");
        dir2 = Files.createTempDirectory(source, "dir2");
        allFiles = new ArrayList<>();
        for (Path dir : List.of(dir1, dir2)) {
            for (int i = 0; i < 1000; ++i) {
                for (int j = 0; j < 500; ++j) {
                    allFiles.add(Files.createTempFile(dir, "" + i, "" + j));
                }
            }
        }
    }

    void clear() throws IOException {
        for (Path path : allFiles) {
            Files.deleteIfExists(path);
        }

        Files.deleteIfExists(dir1);
        Files.deleteIfExists(dir2);
        Files.deleteIfExists(source);
    }
    @Test
    void testFindFilesByPredicate() throws IOException {
        try {
            initSource();
            long start = System.currentTimeMillis();
            Predicate<Path> predicate = path -> path.getFileName().endsWith(".0");
            List<Path> answer = Task2.findFilesByPredicate(source, predicate);
            List<Path> expectedAnswer = allFiles.stream().filter(predicate).sorted().toList();
            answer.sort(Path::compareTo);
            Assertions.assertEquals(expectedAnswer, answer);
            LogManager.getLogger().info(System.currentTimeMillis() - start);
        } finally {
            clear();
        }
    }

    @Test
    void testFindFilesByPredicate2() throws IOException {
        try {
            initSource();
            long start = System.currentTimeMillis();
            Predicate<Path> predicate = path -> path.getFileName().endsWith(".0");
            List<Path> answer = edu.hw9.task2.solution2.Task2.findFilesByPredicate(source, predicate);
            List<Path> expectedAnswer = allFiles.stream().filter(predicate).sorted().toList();
            answer.sort(Path::compareTo);
            Assertions.assertEquals(expectedAnswer, answer);
            LogManager.getLogger().info(System.currentTimeMillis() - start);
        }
        finally {
            clear();
        }
    }


}
