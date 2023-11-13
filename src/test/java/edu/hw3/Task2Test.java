package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Task2Test {
    @Test
    void simpleClusterTest() {
        Assertions.assertEquals(Task2.clusterize("()()()"), List.of("()", "()", "()"));
    }

    @Test
    void nestedClusterTest() {
        Assertions.assertEquals(Task2.clusterize("((()))(())"), List.of("((()))", "(())"));
    }

    @Test
    void emptyCLusterTest() {
        Assertions.assertEquals(Task2.clusterize(""), List.of());
    }
}
