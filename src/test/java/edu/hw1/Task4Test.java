package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task4Test {

    @Test
    void fixEvenString() {
        assert Task4.fixString("123456").equals("214365");
        assert Task4.fixString("hTsii  s aimex dpus rtni.g").equals("This is a mixed up string.");
        assert Task4.fixString("badce").equals("abcde" );
    }

    @Test
    void fixOddString() {
        assert Task4.fixString("badce").equals("abcde" );
    }

    @Test
    void fixEmptyString() {
        assert Task4.fixString("").equals("" );
    }




}
