package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private final static int AIM_DATE = 13;

    private Task2() {
    }

    public static TemporalAdjuster nextFriday13(LocalDate date) {
        TemporalAdjuster nextFriday = TemporalAdjusters.next(DayOfWeek.FRIDAY);
        LocalDate answer = date.with(nextFriday);
        while (answer.getDayOfMonth() != AIM_DATE) {
            answer = answer.with(nextFriday);
        }
        return answer;
    }

    public static List<String> allFridays13(int year) {
        List<String> answer = new ArrayList<>();
        for (LocalDate date = LocalDate.of(year, LocalDate.MIN.getMonth(), AIM_DATE); date.getYear() == year;
             date = date.plusMonths(1)) {
            if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                answer.add(date.toString());
            }
        }
        return answer;
    }
}
