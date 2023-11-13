package edu.hw5;

import edu.hw5.task3.Parser;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task3Test {
    Parser parser = new Parser();

    @Test
    void testDashed() {
        Assertions.assertEquals(
            LocalDate.of(2020, 10, 10),
            parser.parseDate("2020-10-10").get()
        );
        Assertions.assertEquals(
            LocalDate.of(2020, 12, 2),
            parser.parseDate("2020-12-2").get()
        );
    }

    @Test
    void testSlashed() {
        Assertions.assertEquals(
            LocalDate.of(1976, 3, 1),
            parser.parseDate("1/3/1976").get()
        );
        Assertions.assertEquals(
            LocalDate.of(2020, 3, 1),
            parser.parseDate("1/3/20").get()
        );
    }

    @Test
    void testWord() {
        Assertions.assertEquals(
            LocalDate.now().plusDays(1),
            parser.parseDate("tomorrow").get()
        );
        Assertions.assertEquals(
            LocalDate.now(),
            parser.parseDate("today").get()
        );
        Assertions.assertEquals(
            LocalDate.now().minusDays(1),
            parser.parseDate("yesterday").get()
        );
    }

    @Test void testDaysAgo() {
        Assertions.assertEquals(
            LocalDate.now().minusDays(1),
            parser.parseDate("1 day ago").get()
        );
        Assertions.assertEquals(
            LocalDate.now().minusDays(2234),
            parser.parseDate("2234 days ago").get()
        );
    }

    @Test void testNotFound() {
        Assertions.assertFalse(parser.parseDate("1234").isPresent());
        Assertions.assertFalse(parser.parseDate("12").isPresent());
        Assertions.assertFalse(parser.parseDate("12/12").isPresent());
        Assertions.assertFalse(parser.parseDate("12-12").isPresent());
        Assertions.assertFalse(parser.parseDate("1 month ago").isPresent());
        Assertions.assertFalse(parser.parseDate("2020-31-12").isPresent());
    }

}
