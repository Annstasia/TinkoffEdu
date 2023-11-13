package edu.hw5;

import java.util.regex.Pattern;

public class Task5 {
    private final static String RU_LETTERS = "[АВЕКМНОРСТУХ]";
    private final static String NORMAL_PEOPLE = RU_LETTERS + "\\d\\d\\d" + RU_LETTERS
                                                + RU_LETTERS + "\\d\\d\\d?";

    private final static Pattern PASSWORD_PATTERN = Pattern.compile(
        "^" + NORMAL_PEOPLE + "$");

    private Task5() {
    }

    public static boolean check(String number) {
        return PASSWORD_PATTERN.matcher(number).find();
    }
}
