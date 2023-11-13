package edu.hw5.task3;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseSlashedDMYY extends DataParserChain {
    // Не могу представить, когда требуется различать dd и d, MM и M,
    // поэтому заношу в 1 класс.
    private final static int YYYY_GROUP_INDEX = 3;
    private final static int YY_GROUP_INDEX = 4;
    private final static int YEAR_THOUSAND = 2000;
    Pattern pattern = Pattern.compile(
        "(" + FormatterPrimitives.DAY + ")/(" + FormatterPrimitives.MONTH
        + ")/(?:(" + FormatterPrimitives.YYYY
        + ")|(" + FormatterPrimitives.YY + "))");

    @Override public Optional<LocalDate> parseDate(String string) {
        try {
            Matcher matcher = pattern.matcher(string);
            boolean f = matcher.find();
            if (f) {
                int year = 0;
                if (matcher.group(YY_GROUP_INDEX) != null) {
                    year = Integer.parseInt(matcher.group(YY_GROUP_INDEX)) + YEAR_THOUSAND;
                } else if (matcher.group(YYYY_GROUP_INDEX) != null) {
                    year = Integer.parseInt(matcher.group(YYYY_GROUP_INDEX));
                }
                return Optional.of(LocalDate.of(year,
                                                Integer.parseInt(matcher.group(2)),
                                                Integer.parseInt(matcher.group(1))
                ));
            }
        } catch (DateTimeException ignored) {
        }
        return callNext(string);
    }
}
