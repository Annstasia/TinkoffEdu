package edu.hw5.task3;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseDashedYYYYMD extends DataParserChain {
    // Не могу представить, когда требуется различать dd и d, MM и M,
    // поэтому заношу в 1 класс.
    private final static Pattern PATTERN =
        Pattern.compile("(" + FormatterPrimitives.YYYY + ")\\-("
                        + FormatterPrimitives.MONTH + ")-("
                        + FormatterPrimitives.DAY + ")");

    private final static int YEAR_GROUP_INDEX = 1;
    private final static int MONTH_GROUP_INDEX = 2;
    private final static int DAY_GROUP_INDEX = 3;


    @Override
    public Optional<LocalDate> parseDate(String string) {
        try {
            Matcher matcher = PATTERN.matcher(string);
            if (matcher.find()) {
                return Optional.of(LocalDate.of(
                    Integer.parseInt(matcher.group(YEAR_GROUP_INDEX)),
                    Integer.parseInt(matcher.group(MONTH_GROUP_INDEX)),
                    Integer.parseInt(matcher.group(DAY_GROUP_INDEX))
                ));
            }
        } catch (DateTimeException ignored) {
        }
        return callNext(string);
    }
}
