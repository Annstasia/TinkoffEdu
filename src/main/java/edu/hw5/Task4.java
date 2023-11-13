package edu.hw5;

import java.util.regex.Pattern;

public class Task4 {
    private Task4() {
    }

    private final static Pattern PASSWORD_PATTERN = Pattern.compile(
        ".*[~!@#$%^&*|].*");

    public static boolean check(String password) {
        return PASSWORD_PATTERN.matcher(password).find();
    }
}
