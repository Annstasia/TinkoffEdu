package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task5Test {
    @Test
    void testCorrectRu() {
        Assertions.assertTrue(Task5.check("А123ВЕ777"));
        Assertions.assertTrue(Task5.check("А123ВЕ77"));
    }

    @Test
    void testIncorrectRu() {
        Assertions.assertFalse(Task5.check("123АВЕ777"));
        Assertions.assertFalse(Task5.check("А123ВГ77"));
        Assertions.assertFalse(Task5.check("А123ВЕ7777"));
    }

    @Test void testMixedString() {
        Assertions.assertFalse(Task5.check("А123ВЕ77:(){ :|:& };:"));
    }
}
