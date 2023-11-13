package edu.hw5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;

public class Task1 {
    private final static SimpleDateFormat DATE_PARSER = new SimpleDateFormat("yyyy-MM-dd, hh:mm");

    private Task1() {
    }

    public static String averageDuration(String[] sessions) throws ParseException {
        Duration duration = Duration.ZERO;
        for (String session : sessions) {
            String[] startEnd = session.split(" - ");
            duration = duration.plusMillis(DATE_PARSER.parse(startEnd[1]).getTime()
                                           - DATE_PARSER.parse(startEnd[0]).getTime());
        }
        if (sessions.length != 0) {
            duration = duration.dividedBy(sessions.length);
        }
        return duration.toHours() + "ч "
               + duration.toMinutesPart() + "м";

    }

}
