package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

    @Test
    void minutesToSecondCorrect() {
        assert Task1.minutesToSecond("13:28") == 808;
    }

    @Test
    void minutesToSecondTooBigSecond() {
        System.out.println(Task1.minutesToSecond("13:68"));
        assert Task1.minutesToSecond("13:68") == -1;
    }

    @Test
    void minutesToSecondMinus() {
        assert Task1.minutesToSecond("-13:68") == -1;
    }

    @Test
    void minutesToSecondDirtyString() {
        assert Task1.minutesToSecond("ndeon 13:68 odjoiw") == -1;
    }

}
