package edu.hw5;

import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task2Test {
    @Test void fridays13InYearTest() {
        Assertions.assertArrayEquals(
            new String[] {"2024-09-13", "2024-12-13"},
            Task2.allFridays13(2024).toArray()
        );
    }

    @Test
    void nextFriday13InSameYearTest() {
        Assertions.assertEquals(
            LocalDate.of(2024, 12, 13),
            Task2.nextFriday13(LocalDate.of(2024, 9, 13))
        );
        Assertions.assertEquals(
            LocalDate.of(2024, 9, 13),
            Task2.nextFriday13(LocalDate.of(2024, 9, 12))
        );

    }

    @Test
    void nextFriday13InNextYearTest() {
        Assertions.assertEquals(
            LocalDate.of(2024, 9, 13),
            Task2.nextFriday13(LocalDate.of(2023, 12, 31))
        );
    }

}
