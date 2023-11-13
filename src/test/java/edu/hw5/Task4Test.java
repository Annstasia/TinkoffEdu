package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task4Test {
    @Test
    void containsTest() {
        Assertions.assertTrue(Task4.check("a~rfef"));
        Assertions.assertTrue(Task4.check("a!rfef"));
        Assertions.assertTrue(Task4.check("a*rfef"));
        Assertions.assertTrue(Task4.check("a@rfef"));
        Assertions.assertTrue(Task4.check("a#rfef"));
        Assertions.assertTrue(Task4.check("a$rfef"));
        Assertions.assertTrue(Task4.check("a^rfef"));
        Assertions.assertTrue(Task4.check("a&&rfef"));
        Assertions.assertTrue(Task4.check("a|rfef"));
        Assertions.assertTrue(Task4.check(":(){ :|:& };:"));
    }
}
