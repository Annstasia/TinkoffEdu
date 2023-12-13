package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task6Test {
    @Test
    void containsTet() {
        Assertions.assertTrue(Task6.isSubsequence("abc", "achfdbaabgabcaabg"));
    }

    @Test
    void notContainsTest() {
        Assertions.assertFalse(Task6.isSubsequence("abe", "abchfdbaabgabcaabg"));
    }
}
