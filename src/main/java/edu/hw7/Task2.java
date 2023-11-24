package edu.hw7;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class Task2 {
    private Task2() {
    }

    public static BigInteger notParallelFactorial(int n) {
        return LongStream.range(1, n + 1)
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger::multiply)
            .orElseGet(() -> BigInteger.ZERO);
    }

    public static BigInteger factorial(int n) {
        return LongStream.range(1, n + 1).parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger::multiply)
            .orElseGet(() -> BigInteger.ZERO);
    }
}
