package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    void simpleStringTest() {
        Assertions.assertEquals(Task1.atbash("Hello"), "Svool");
    }

    @Test
    void notOnlyEnglishLetters() {
        Assertions.assertEquals(
            Task1.atbash("Any Fool ... - Martin Фаулер"),
            "Zmb Ullo ... - Nzigrm Фаулер"
        );
    }
}
