package edu.hw9.task2;

import edu.hw9.task2.Task2;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Task2Test {
    Path source;
    Path dir1;
    Path dir2;
    List<Path> allFiles;

    @BeforeEach
    void initSource() throws IOException {
        source = Files.createTempDirectory("temp");
        dir1 = Files.createTempDirectory(source, "dir1");
        dir2 = Files.createTempDirectory(source, "dir2");
        allFiles = new ArrayList<>();
        for (Path dir : List.of(dir1, dir2)) {
            for (int i = 0; i < 100; ++i) {
                for (int j = 0; j < 10; ++j) {
                    allFiles.add(Files.createTempFile(dir, "" + i, "" + j));
                }
            }
        }
        Files.createTempFile(dir2, "1001", "wow");
    }

    @Test
    void testFindLargeDirectories() {
        List<Path> answer = Task2.findLargeDirectories(source);
        List<Path> expectedAnswer = new ArrayList<>(List.of(source, dir2));
        expectedAnswer.sort(Path::compareTo);
        answer.sort(Path::compareTo);
        Assertions.assertEquals(2, answer.size());
        Assertions.assertEquals(expectedAnswer, answer);
    }

    @Test
    void testFindFilesByPredicate() {
        Predicate<Path> predicate = path -> path.getFileName().endsWith(".0");
        List<Path> answer = Task2.findFilesByPredicate(source, predicate);
        List<Path> expectedAnswer = allFiles.stream().filter(predicate).sorted().toList();
        answer.sort(Path::compareTo);
        Assertions.assertEquals(expectedAnswer, answer);

    }

}
