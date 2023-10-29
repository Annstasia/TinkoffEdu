package edu.hw3;

import edu.hw3.task5.Contact;
import edu.hw3.task5.Task5;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.IntStream;

public class Task5Test {
    @Test void nameContactViewTest() {
        String string = "John";
        Contact contact = new Contact(string);
        Assertions.assertEquals(contact.getName(), string);
        Assertions.assertNull(contact.getSurname());
        Assertions.assertNull(contact.getSecondName());
        Assertions.assertEquals(contact.toString(), string);
    }

    @Test void nameSurnameContactViewTest() {
        String string = "John Snow";
        Contact contact = new Contact(string);
        Assertions.assertEquals(contact.getName(), "John");
        Assertions.assertEquals(contact.getSurname(), "Snow");
        Assertions.assertNull(contact.getSecondName());
        Assertions.assertEquals(contact.toString(), string);
    }

    @Test void nameSecondNameSurnameViewTest() {
        String string = "Anna Maria Bietti Sestieri";
        Contact contact = new Contact(string);
        Assertions.assertEquals(contact.getName(), "Anna");
        Assertions.assertEquals(contact.getSurname(), "Sestieri");
        Assertions.assertEquals(contact.getSecondName(), "Maria Bietti");
        Assertions.assertEquals(contact.toString(), string);
        Assertions.assertEquals(contact.toString(), string);
    }

    @Test void surnamesASCTest() {
        List<String> inputList =
            List.of("John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes", "Albus Dumbledore");
        List<Integer> orderIndexes = List.of(1, 3, 4, 2, 0);
        List<Contact> outputList = orderIndexes.stream().map(index -> new Contact(inputList.get(index))).toList();
        Assertions.assertEquals(Task5.parseContacts(inputList, "ASC"), outputList);
    }

    @Test void surnamesDESCTest() {
        List<String> inputList =
            List.of("John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes", "Albus Dumbledore");
        List<Integer> orderIndexes = List.of(1, 3, 4, 2, 0);
        List<Contact> outputList = IntStream.range(0, orderIndexes.size())
            .mapToObj(index -> new Contact(inputList.get(orderIndexes.get(orderIndexes.size() - index - 1)))).toList();
        Assertions.assertEquals(Task5.parseContacts(inputList, "DESC"), outputList);
    }

    @Test void mixedNamesTest() {
        List<String> inputList = List.of("Michael AKenji Shinoda", "Chester", "Bradford Delson");
        List<Integer> orderIndexes = List.of(1, 2, 0);
        List<Contact> outputListASC = IntStream.range(0, orderIndexes.size())
            .mapToObj(index -> new Contact(inputList.get(orderIndexes.get(index)))).toList();
        List<Contact> outputListDESC = IntStream.range(0, orderIndexes.size())
            .mapToObj(index -> new Contact(inputList.get(orderIndexes.get(orderIndexes.size() - index - 1)))).toList();
        Assertions.assertEquals(Task5.parseContacts(inputList, "ASC"), outputListASC);
        Assertions.assertEquals(Task5.parseContacts(inputList, "DESC"), outputListDESC);


    }

    @Test void emptyTest() {
        List<String> inputList = List.of();
        Assertions.assertEquals(Task5.parseContacts(inputList, "DESC"), List.of());

        Assertions.assertEquals(Task5.parseContacts(inputList, "ASC"), List.of());
    }

    @Test void nullList() {
        Assertions.assertEquals(Task5.parseContacts(null, "DESC"), List.of());

        Assertions.assertEquals(Task5.parseContacts(null, "ASC"), List.of());
    }

}
