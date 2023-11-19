package edu.project3;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    // ((?:\d{1,3}\.){1,4})(\d{1,5})
        static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z");
    public static void main(String[] args) {
        // 18/Nov/2023:17:37:03 +0000
        // 17/May/2015:08:05:32 +0000
        String str2 = "18/Nov/2023:17:37:03 +0000";
        String str1 = "17/May/2015:08:05:32 +0000";
        LocalDateTime dateTime = LocalDateTime.parse(str2, formatter);
        System.out.println(dateTime);
    }
}
