package edu.hw5;

public class Task7 {
    private Task7() {
    }

    public static String regexLonger2ZeroAt3() {
        return "^[01]{2}0[01]*$";
    }

    public static String regexStartEqualEnd() {
        return "^(?:0[01]*0|1[01]*1|0|1|)$";
    }

    public static String regexLengthFrom1To3() {
        return "^[01]{1,3}$";
    }
}
