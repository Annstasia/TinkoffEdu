package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class Parser {
    private final DataParserChain head;

    public Parser() {
        head = new ParseDashedYYYYMD();
        head.setNext(new ParseSlashedDMYY())
            .setNext(new ParseWord())
            .setNext(new ParseDaysAgo());
    }

    public Optional<LocalDate> parseDate(String date) {
        return head.parseDate(date);
    }
}
