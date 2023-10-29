package edu.hw3.task5;

import java.util.ArrayList;
import java.util.List;

public class Task5 {
    private Task5() {
    }

    public static List<Contact> parseContacts(List<String> contactStrings, String compareType) {
        if (contactStrings == null) {
            return new ArrayList<>();
        }
        return contactStrings.stream().map(Contact::new).sorted((first, second) -> {
            return (compareType.equals("ASC") ? first.compareTo(second) : second.compareTo(first));
        }).toList();
    }
}
