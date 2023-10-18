package edu.hw1;

public class Task2 {
    private Task2() {
    }

    public static int countDigits(int number) {
        int rest = number;
        final int BASE = 10;
        if (rest == 0) {
            return 1;
        }
        int digitsNumber = 0;
        while (rest != 0) {
            ++digitsNumber;
            rest /= BASE;
        }
        return digitsNumber;
    }
}
