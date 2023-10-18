package edu.hw1;

public class Task6 {
    private Task6() {
    }

    public static int countK(int number) {
        final int godNumber = 6174;
        final int BASE = 10;
        int k = 0;
        int rest = number;
        while (rest != godNumber) {
            int[] digitCounter = new int[BASE];
            while (rest != 0) {
                digitCounter[rest % BASE] += 1;
                rest /= BASE;
            }
            int maxNumber = 0;
            int minNumber = 0;
            int decade = 1;
            for (int i = 0; i < BASE; ++i) {
                for (int j = 0; j < digitCounter[i]; ++j) {
                    minNumber = minNumber * BASE + i;
                    maxNumber = i * decade + maxNumber;
                    decade *= BASE;
                }
            }
            rest = maxNumber - minNumber;
            ++k;
        }
        return k;
    }
}
