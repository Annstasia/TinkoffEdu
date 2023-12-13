package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseDaysAgo extends DataParserChain {
    Pattern daysAgoPattern = Pattern.compile("(\\d+) days? ago");

    @Override
    public Optional<LocalDate> parseDate(String date) {
        Matcher matcher = daysAgoPattern.matcher(date);
        if (matcher.find()) {
            return Optional.of(LocalDate.now().minusDays(
                Long.parseLong(matcher.group(1))));
        }
        return Optional.empty();
    }
}
