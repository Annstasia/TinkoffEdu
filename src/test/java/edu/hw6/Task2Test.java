package edu.hw6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;

public class Task2Test {
    final static String DIR_NAME = "cat_house";
    final static String FILE_NAME = "temp";
    final static String FILE_SUFFIX = ".cat";
    final static String WRONG_SUFFIX = ".dog";
    final static String COPY = " - копия";
    final static Function<Integer, String> COPY_N = (i)-> COPY + " (" + i + ")";
    Path tempDir;

    static String getCopyName(Path file, int number) {
        String[] parts = file.getFileName().toString().split("\\.");
        return parts[0] + COPY + (number == 0? "" : " (" + number + ")") + "." + parts[1];
    }

    @BeforeEach
    void setDirectory() throws IOException {
        tempDir = Files.createTempDirectory(DIR_NAME);
    }
    @Test
    void baseTest() throws IOException {
        Path tempFile = Files.createTempFile(tempDir, FILE_NAME, FILE_SUFFIX);
        Assertions.assertEquals(getCopyName(tempFile, 0),
                                Task2.cloneFile(tempFile).getFileName().toString());
        Assertions.assertEquals(getCopyName(tempFile, 2),
                                Task2.cloneFile(tempFile).getFileName().toString());
    }

    // 1. "file - копия (k)", ["file", "file - копия (k)"] -> "file - копия (n)"
    // 3. "file - копия (1)", ["file - копия (1)"] -> "file - копия (2)"
    @Test
    void testRule1_3KEquals1() throws IOException {
        Path copyFile = Files.createTempFile(tempDir, FILE_NAME, " - копия" + FILE_SUFFIX);
        String[] parts = copyFile.getFileName().toString().split("\\.");
        Assertions.assertEquals(parts[0] + " (2)." + parts[1], Task2.cloneFile(copyFile).getFileName().toString());
    }

    @Test
    void testRule1_3KMore1() throws IOException {
        Path copyFile = Files.createTempFile(tempDir, FILE_NAME, " - копия (2)" + FILE_SUFFIX);
        String[] parts = copyFile.getFileName().toString().split("\\.");
        Assertions.assertEquals(parts[0].split("\\(")[0] + "(3)." + parts[1],
                                Task2.cloneFile(copyFile).getFileName().toString());
    }

    // 2. "file", ["file - копия (2)", "file - копия (4)"] -> "file - копия (3)"
    @Test
    void testRule2() throws IOException {
        Path tempFile = Files.createTempFile(tempDir, FILE_NAME, FILE_SUFFIX);
        Path copy = Task2.cloneFile(tempFile);
        Path copy2 = Task2.cloneFile(tempFile);
        Path copy3 = Task2.cloneFile(tempFile);
        String copy3Name = copy3.getFileName().toString();
        Path copy4 = Task2.cloneFile(tempFile);
        Files.delete(copy3);
        Path newCopy = Task2.cloneFile(tempFile);
        Assertions.assertEquals(copy3Name, newCopy.getFileName().toString());
    }



}
