package edu.hw8.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HackerTest {
    static Map<String, String> secretInfo = new HashMap<>(Map.of(
        "secret_hero1", "I",
        "secret_hero2", "love",
        "secret_hero3", "cats",
        "secret_hero4.a", "cats",
        "secret_hero4.b", "will",
        "secret_hero4.c", "rule",
        "secret_hero4.d", "the",
        "secret_hero4.e", "world",
        "manul.1", "42is",
        "manul.2", "the")
    );

    // На моем устройстве 1 минута 3 секунды
    @Test
    void oneThreadTest() throws IOException {
        Map<String, String> hackedDB = Hacker.hack();
        Assertions.assertEquals(secretInfo, hackedDB);
    }

    // На моем устройстве 30 секунд
    // Ускорение от разделения на 4 потока в 2 раза.
    // Скорее всего, основное замедление в GeneratorManager
    @Test
    void multyThreadTest() throws IOException {
        Map<String, String> hackedDB = Hacker.multiThreadHack();
        Assertions.assertEquals(secretInfo, hackedDB);
    }
}
