package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public abstract class DataParserChain {
    DataParserChain next;

    public DataParserChain setNext(DataParserChain next) {
        this.next = next;
        return next;
    }

    public abstract Optional<LocalDate> parseDate(String date);

    Optional<LocalDate> callNext(String date) {
        if (next == null) {
            return Optional.empty();
        } else {
            return next.parseDate(date);
        }
    }
}
