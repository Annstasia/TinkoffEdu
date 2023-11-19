package edu.project3;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
    // не нашла простого способа экранизации кавычек.
    // 1-нные названия переменных - плохо, но, кажется, в регулярках только утяжелится чтения при использовании
    // длинного имени
    final static String Q = Pattern.quote("\"");
    final static String NOT_QUOTE_PATTERN = "[^" + Q + "]*";
    final static String REQUEST_REGEX =
        Q + "(\\w*) ([^" + Q + " ]*) (" + NOT_QUOTE_PATTERN + ")" + Q;
    final static String HTTP_REFERER_REGEX = Q + "(" + NOT_QUOTE_PATTERN + ")" + Q;
    final static String USER_AGENT_REGEX = Q + "(" + NOT_QUOTE_PATTERN + ")" + Q;
    final static String ADDRESS_REGEX = "([^ ]*)";
    final static String PORT_REGEX = "(\\d{1,5})";
    final static String REMOTE_USER_REGEX = "(.*?)";
    final static String DATE_TIME_REGEX =
        "\\[(\\d{1,2}\\/\\w*\\/\\d{4}(?::\\d{2}){3} \\+\\d{4})\\]";
    final static String NUMBER_GROUP_REGEX = "(\\d*)";
    final static String STATUS_REGEX = NUMBER_GROUP_REGEX;
    final static String BODY_BYTES_REGEX = NUMBER_GROUP_REGEX;
    final static Pattern LOG_PATTERN = Pattern.compile(ADDRESS_REGEX + " - "
                                                       + REMOTE_USER_REGEX + "- "
                                                       + DATE_TIME_REGEX + " "
                                                       + REQUEST_REGEX + " "
                                                       + STATUS_REGEX + " "
                                                       + BODY_BYTES_REGEX + " "
                                                       + HTTP_REFERER_REGEX + " "
                                                       + USER_AGENT_REGEX);
    final static int ADDRESS_GROUP = 1;
    final static int REMOTE_USER_GROUP = 2;
    final static int DATE_TIME_GROUP = 3;
    final static int REQUEST_TYPE_GROUP = 4;
    final static int RESOURCE_GROUP = 5;
    final static int PROTOCOL_GROUP = 6;
    final static int STATUS_GROUP = 7;
    final static int BODY_BYTES_GROUP = 8;
    final static int HTTP_REFERER_GROUP = 9;
    final static int USER_AGENT_GROUP = 10;
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MMM/uuuu:HH:mm:ss Z");

    private LogParser() {
    }

    public static LogRecord toLogRecord(String stringLog) {
        Matcher matcher = LOG_PATTERN.matcher(stringLog);
        if (!matcher.find()) {
            // TODO: LOG.ERR / EXCEPTION
            return null;
        } else {
            return new LogRecord(
                matcher.group(ADDRESS_GROUP),
                matcher.group(REMOTE_USER_GROUP),
                LocalDateTime.parse(matcher.group(DATE_TIME_GROUP), dateTimeFormatter),
                matcher.group(REQUEST_TYPE_GROUP),
                matcher.group(RESOURCE_GROUP),
                matcher.group(PROTOCOL_GROUP),
                Integer.parseInt(matcher.group(STATUS_GROUP)),
                Integer.parseInt(matcher.group(BODY_BYTES_GROUP)),
                matcher.group(HTTP_REFERER_GROUP),
                matcher.group(USER_AGENT_GROUP)
            );

        }
    }
}
