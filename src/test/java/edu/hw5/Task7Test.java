package edu.hw5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task7Test {
    static List<Arguments> longer2ZeroAt3LongString() {
        return generateArguments(3, 10);
    }

    static List<Arguments> longer2ZeroAt3ShortString() {
        return generateArguments(0, 3);
    }

    static List<Arguments> startEqualEndString() {
        return generateArguments(0, 10);
    }

    static List<Arguments> length1To3String() {
        return generateArguments(0, 10);
    }

    static List<Arguments> generateArguments(int lengthStart, int lengthEnd) {
        List<Arguments> answer = new ArrayList<>();
        for (int length = lengthStart; length < lengthEnd; ++length) {
            char[][] arr = new char[(int) Math.pow(2, length)][length];
            int row = arr.length;
            for (int i = 0; i < arr[0].length; ++i) {
                row /= 2;
                int filler = 0;
                for (int iterations = 0; iterations < arr.length / row; ++iterations) {
                    for (int j = 0; j < row; ++j) {
                        arr[row * iterations + j][i] = (char) ('0' + filler);
                    }
                    filler = (filler + 1) % 2;
                }
            }
            answer.addAll(Arrays.stream(arr)
                                .map((charString) -> Arguments
                                    .of(new String(charString))).toList());
        }
        return answer;
    }

    @ParameterizedTest
    @MethodSource("longer2ZeroAt3LongString")
    void regexLonger2ZeroAt3LongTest(String input) {
        Assertions.assertEquals(
            input.charAt(2) == '0',
            input.matches(Task7.regexLonger2ZeroAt3())
        );
    }

    @ParameterizedTest
    @MethodSource("longer2ZeroAt3ShortString")
    void regexLonger2ZeroAt3ShortTest(String input) {
        Assertions.assertFalse(input.matches(Task7.regexLonger2ZeroAt3()));
    }

    @ParameterizedTest
    @MethodSource("startEqualEndString")
    void regexStartEqualEnd(String input) {
        if (!input.isEmpty() && input.charAt(0) != input.charAt(input.length() - 1)) {
            Assertions.assertFalse(input.matches(Task7.regexStartEqualEnd()));
        } else {
            Assertions.assertTrue(input.matches(Task7.regexStartEqualEnd()));
        }
    }

    @ParameterizedTest
    @MethodSource("length1To3String")
    void regexLengthFrom1To3(String input) {
        Assertions.assertEquals(
            input.length() <= 3 && !input.isEmpty(),
            input.matches(Task7.regexLengthFrom1To3())
        );
    }

}
