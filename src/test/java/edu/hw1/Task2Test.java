package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    @Test
    void countDigits() {
        assert Task2.countDigits(4666) == 4;
        // Проверка 544 ничем не отличается от 4666 с точки зрения логики Task2.
        // По-идее она не нужна. Поэтому в отдельный тест не выношу.
        // Вставила только из-за того, что она была в примерах
        assert Task2.countDigits(544) == 3;
    }

    @Test
    void countDigitsOfZero() {
        assert Task2.countDigits(0) == 1;
    }


}
