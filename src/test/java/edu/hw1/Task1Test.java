package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task1Test {
    private static final int ERROR = -1;

    @Test
    void minutesToSecondCorrect() {
        Assertions.assertEquals(Task1.minutesToSecond("13:28"), 808);
    }

    @Test
    void minutesToSecondTooBigSecond() {
        Assertions.assertEquals(Task1.minutesToSecond("13:68"), ERROR);
    }

    @Test
    void minutesToSecondMinus() {
        Assertions.assertEquals(Task1.minutesToSecond("-13:68"), ERROR);
    }

    @Test
    void minutesToSecondDirtyString() {
        Assertions.assertEquals(Task1.minutesToSecond("ndeon 13:68 odjoiw"), ERROR);
    }

}
