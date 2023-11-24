package edu.hw7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

public class Task2Test {
    @Test
    void task2Test() {
        BigInteger answer = BigInteger.ONE;
        int N = 100000;
        for (int i = 2; i < N + 1; ++i) {
            answer = answer.multiply(BigInteger.valueOf(i));
        }
        Assertions.assertEquals(Task2.factorial(N), answer);
    }

    /*
    Непосредственно к тестированию работы не относится.
    Как минимум на моем устройстве, при подсчете 100! эффективнее
    использовать один поток. При подсчете (10^5)! время работы примерно одинаково
    При подсчете (10^7)! раза в 4 эффективнее многопоточный подсчет
    Все три значения не влезают в long.
    */

    /*
    void timeCounter(int n) {
        List<Function<Integer, Long>> functions = new ArrayList<>();
        functions.add(Task2::factorial);
        functions.add(Task2::notParallelFactorial);
        List<String> titles = List.of("parallel    ", "not parallel");
        for (int j = 0; j < 5; ++j) {
            for (int k = 0; k < 2; ++k) {
                long time = 0;
                int N = 100;
                for (int i = 0; i < N; ++i) {
                    long start = System.nanoTime();
                    functions.get(k).apply(n);
                    time += System.nanoTime() - start;
                }
                time /= N;
                System.out.println(titles.get(k) + " " + time);
            }
        }
    }


    @Test
    void testSmallNTime() {
        timeCounter(100);
    }

    @Test
    void testBigNTime() {
        timeCounter(100000);
    }

    @Test
    void testReallyBigNTime() {
        timeCounter(10000000);
    }

    */



}
