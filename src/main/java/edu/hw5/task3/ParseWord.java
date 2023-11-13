package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class ParseWord extends DataParserChain {
    @Override
    public Optional<LocalDate> parseDate(String string) {
        if (string.trim().equalsIgnoreCase("tomorrow")) {
            return Optional.of(LocalDate.now().plusDays(1));
        } else if (string.trim().equalsIgnoreCase("today")) {
            return Optional.of(LocalDate.now());
        } else if (string.trim().equalsIgnoreCase("yesterday")) {
            return Optional.of(LocalDate.now().minusDays(1));
        }
        return callNext(string);
    }
}
