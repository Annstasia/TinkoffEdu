package edu.hw1;

public class Task7 {
    private Task7() {
    }

    private static int countBitCount(int n) {
        int power = 0;
        while ((n >> power) != 0) {
            ++power;
        }
        return power;
    }

    private static int countNumberBorder(int bitCount) {
        return (1 << bitCount) - 1;
    }

    public static int rotateRight(int n, int shift) {
        int bitCount = countBitCount(n);
        int cycled = (n << (bitCount - shift)) & countNumberBorder(bitCount);
        return cycled | (n >> shift);
    }

    public static int rotateLeft(int n, int shift) {
        int bitCount = countBitCount(n);
        int cycled = n >> (bitCount - shift);
        return ((n << shift) & countNumberBorder(bitCount)) | cycled;
    }

}
