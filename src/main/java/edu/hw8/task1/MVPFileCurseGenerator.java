package edu.hw8.task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MVPFileCurseGenerator implements CurseGenerator {
    String curses;

    public MVPFileCurseGenerator() throws IOException {
        curses = Files.readString(Path.of("src/main/resources/hw8/task1/curses.txt"));
    }

    @Override
    public String curse(String keyWord) {
        int wordIndex = curses.indexOf(keyWord);
        if (wordIndex == -1) {
            return "в Банке Тинькофф у разработчиков и менеджеров мир и любовь <3";
        }
        int start = curses.lastIndexOf('\n', wordIndex) + 1;
        int end = curses.indexOf('\n', wordIndex + keyWord.length());
        return curses.substring(start, end);
    }
}
