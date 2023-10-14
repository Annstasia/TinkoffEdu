package edu.hw1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
    static Pattern pattern = Pattern.compile("^(\\d*):(\\d*)$");

    private Task1() {
    }

    public static int minutesToSecond(String timeString) {
        final int SECONDS_IN_MINUTE = 60;
        Matcher matcher = pattern.matcher(timeString);
        if (matcher.find() && matcher.groupCount() == 2) {
            int minutes = Integer.parseInt(matcher.group(1));
            int seconds = Integer.parseInt(matcher.group(2));
            if (seconds < SECONDS_IN_MINUTE) {
                return minutes * SECONDS_IN_MINUTE + seconds;
            }
        }
        return -1;
    }
}
