package edu.hw8.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FileParser {
    private FileParser() {
    }

    public static Map<String, String> parse(Path filePath) throws IOException {
        Map<String, String> answer = new HashMap<>();
        for (String keyValueLine : Files.readAllLines(filePath)) {
            String[] keyValue = keyValueLine.split("\\s+");
            answer.put(keyValue[0], keyValue[1]);
        }
        return answer;
    }
}
