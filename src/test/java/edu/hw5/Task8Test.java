package edu.hw5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task8Test {
    static List<Arguments> commonArgument() {
        return generateArguments(0, 6);
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
    @MethodSource("commonArgument")
    void regexOddLengthTest(String input) {
        Assertions.assertEquals(input.length() % 2 != 0, input.matches(Task8.regexOddLength()));
    }

    @ParameterizedTest
    @MethodSource("commonArgument")
    void regexOddStart0EvenStarts1Test(String input) {
        if (input.isEmpty()) {
            Assertions.assertFalse(input.matches(Task8.regexOddStart0EvenStarts1()));
        } else if (input.charAt(0) == '0') {
            Assertions.assertEquals(input.length() % 2 != 0, input.matches(Task8.regexOddStart0EvenStarts1()));
        } else if (input.charAt(0) == '1') {
            Assertions.assertEquals(input.length() % 2 == 0, input.matches(Task8.regexOddStart0EvenStarts1()));
        }
    }

    @ParameterizedTest
    @MethodSource("commonArgument")
    void regexZeroCountDivisibleBy3(String input) {
        int zeros = 0;
        for (char c : input.toCharArray()) {
            if (c == '0') {
                zeros += 1;
            }
        }

        Assertions.assertEquals(zeros % 3 == 0, input.matches(Task8.regexZeroCountDivisibleBy3()));
    }

    @ParameterizedTest
    @MethodSource("commonArgument")
    void regexNot11Not111(String input) {
        Assertions.assertEquals(
            !input.equals("11") && !input.equals("111"),
            input.matches(Task8.regexNot11Not111())
        );
    }

    @ParameterizedTest
    @MethodSource("commonArgument")
    void regexOnesOnOddPositions(String input) {
        boolean onesOnOddPositions = true;
        for (int i = 0; i < input.length(); i += 2) {
            onesOnOddPositions &= (input.charAt(i) == '1');
        }
        Assertions.assertEquals(
            onesOnOddPositions,
            input.matches(Task8.regexOnesOnOddPositions())
        );
    }

    @ParameterizedTest
    @MethodSource("commonArgument")
    void regexMoreThan1ZerosLessThan2Ones(String input) {
        int zeros = 0;
        int ones = 0;
        for (int i = 0; i < input.length(); ++i) {
            if (input.charAt(i) == '0') {
                ++zeros;
            } else {
                ++ones;
            }
        }
        Assertions.assertEquals(zeros >= 2 && ones <= 1, input.matches(Task8.regexMoreThan1ZerosLessThan2Ones()));
    }

    @ParameterizedTest
    @MethodSource("commonArgument")
    void regexNoSequentialOnesTest(String input) {
        boolean sequentialOnes = input.contains("11");
        Assertions.assertEquals(!sequentialOnes, input.matches(Task8.regexNoSequentialOnes()));
    }
}
