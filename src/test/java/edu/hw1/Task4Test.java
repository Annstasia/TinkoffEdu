package edu.hw1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class Task4Test {
    @Test
    void fixEvenString() {
        Assertions.assertEquals(Task4.fixString("123456"), ("214365"));
        Assertions.assertEquals(Task4.fixString("hTsii  s aimex dpus rtni.g"), ("This is a mixed up string."));
        Assertions.assertEquals(Task4.fixString("badce"), ("abcde" ));
    }

    @Test
    void fixOddString() {
        Assertions.assertEquals(Task4.fixString("badce"), ("abcde"));
    }

    @Test
    void fixEmptyString() {
        Assertions.assertEquals(Task4.fixString(""), ("" ));
    }




}
