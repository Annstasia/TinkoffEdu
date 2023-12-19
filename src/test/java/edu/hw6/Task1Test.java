package edu.hw6;

import edu.hw6.task1.DiskMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class Task1Test {
    final static String key1 = "beautiful key";
    final static String value1 = "beautiful value";
    final static String key2 = "ug1y key";
    final static String value2 = "ug1y value";

    @Test
    void testEmpty() throws IOException {
        File tempFile = File.createTempFile("temp", ".txt");
        Path tempPath = Path.of(tempFile.toURI());
        DiskMap diskMap = new DiskMap(tempPath);
        Map<String, String> map = new HashMap<>();
        map.put(key1, value1);
        map.put(key2, value2);
        diskMap.putAll(map);
        diskMap.save();
        DiskMap diskMap2 = new DiskMap(tempPath);
        Assertions.assertEquals(map, diskMap2);
    }

    @Test
    void testFilled() throws IOException {
        File tempFile = File.createTempFile("temp", ".txt");
        Path tempPath = Path.of(tempFile.toURI());
        Files.writeString(tempPath, key1 + ":" + value1 + "\n");
        Files.writeString(tempPath, key2 + ":" + value2 + "\n", StandardOpenOption.APPEND);
        DiskMap diskMap = new DiskMap(tempPath);
        Map<String, String> map = new HashMap<>();
        map.put(key1, value1);
        map.put(key2, value2);
        diskMap.remove(key2);
        diskMap.save();
        Assertions.assertEquals(Map.of(key1, value1), new DiskMap(tempPath));
    }


}
