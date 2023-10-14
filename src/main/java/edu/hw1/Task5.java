package edu.hw1;

public class Task5 {
    private Task5() {
    }

    public static boolean isPalindromeDescendant(int number) {
        // если нечетная длина, то считается как строка 0number
        final int BASE = 10;
        int reversed = 0;
        int rest = number;
        while (rest != 0) {
            reversed = reversed * BASE + rest % BASE;
            rest /= BASE;
        }
        if (reversed == number) {
            return true;
        }
        int candidate = 0;
        rest = number;
        while (rest != 0) {
            int first = rest % BASE;
            rest /= BASE;
            int second = rest % BASE;
            rest /= BASE;
            candidate = candidate * BASE + first + second;
        }
        if (candidate / BASE == 0) {
            return false;
        }
        return isPalindromeDescendant(candidate);
    }
}
