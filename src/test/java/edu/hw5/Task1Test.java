package edu.hw5;

import java.text.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task1Test {
    @Test void simpleTest() throws ParseException {
        Assertions.assertEquals(
            "3ч 40м",
            Task1.averageDuration(
                new String[] {"2022-03-12, 20:20 - 2022-03-12, 23:50",
                    "2022-04" + "-01, " + "21:30 - " + "2022-04" + "-02, " + "01:20"})
        );
    }

    @Test
    void emptyTest() throws ParseException {
        Assertions.assertEquals(
            "0ч 0м",
            Task1.averageDuration(
                new String[] {})
        );
    }
}
