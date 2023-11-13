package edu.hw3;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task3Test {
    static class Custom {
        int value;

        public Custom(int value) {
            this.value = value;
        }

        @Override public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Custom custom = (Custom) o;

            return value == custom.value;
        }

        @Override
        public int hashCode() {
            return value;
        }
    }

    @Test
    void stringTest() {
        Assertions.assertEquals(
            Task3.freqDict(List.of("this", "and", "that", "and")),
            Map.of("that", 1, "and", 2, "this", 1)
        );
    }

    @Test
    void intTest() {
        Assertions.assertEquals(Task3.freqDict(List.of(1, 1, 2, 2, 2, 3)), Map.of(2, 3, 1, 2, 3, 1));
    }

    @Test
    void customTest() {
        Assertions.assertEquals(
            Task3.freqDict(List.of(new Custom(1), new Custom(1), new Custom(2))),
            Map.of(new Custom(1), 2, new Custom(2), 1)
        );
    }
}
