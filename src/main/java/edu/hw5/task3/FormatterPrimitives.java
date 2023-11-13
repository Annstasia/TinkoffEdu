package edu.hw5.task3;

public class FormatterPrimitives {
    private FormatterPrimitives() {
    }

    static final String YYYY = "\\d\\d\\d\\d";
    static final String YY = "\\d\\d";
    static final String MONTH = "(?:0?[1-9])|(?:1[0-2])";
    static final String DAY = "(?:3[0-1])|(?:[1-2]?[0-9])|(?:0?[0-9])";
}
