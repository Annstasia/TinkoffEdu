package edu.hw2;

import edu.hw1.Task1;
import edu.hw2.task2.Rectangle;
import edu.hw2.task2.Square;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    static Arguments[] rectangles() {
        return new Arguments[]{
            Arguments.of(new Rectangle(10, 10)),
            Arguments.of(new Square(10))
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {
        if (rect.setWidth(20) && rect.setHeight(10)) {
            assertThat(rect.area()).isEqualTo(200.0);
        }
    }
}