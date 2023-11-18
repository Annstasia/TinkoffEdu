package edu.hw6;

import edu.hw6.task3.Task3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task3Test {
    static final Path DIR_PATH = Path.of("src/test/resources/hw6/task3");
    @BeforeAll static void setModifiers() throws IOException {
        Files.setPosixFilePermissions(Path.of("src/test/resources/hw6/task3/cat-cat.txt"),
                                      Set.of(PosixFilePermission.OWNER_WRITE));

    }
    @Test
    void testReadable() throws IOException {
        DirectoryStream.Filter<Path> filter = Task3.readable;
        Set<String> answer = new HashSet<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(DIR_PATH, filter)) {
            entries.forEach((path -> answer.add(path.getFileName().toString())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(Set.of("cat-cat.png", "catcat.png", "empty.png"),
                                answer);
    }
    @Test
    void testAll() {
        DirectoryStream.Filter<Path> filter = Task3.regularFile
            .and(Task3.readable)
            .and(Task3.largerThan(100_000))
            .and(Task3.magicNumber(0x89, 'P', 'N', 'G'))
            .and(Task3.globMatches("*.png"))
            .and(Task3.regexContains("[-]"));
        Set<String> answer = new HashSet<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(DIR_PATH, filter)) {
            entries.forEach((path -> answer.add(path.getFileName().toString())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(Set.of("cat-cat.png"),
                                answer);
    }
}
