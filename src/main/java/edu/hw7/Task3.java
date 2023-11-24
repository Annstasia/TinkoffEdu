package edu.hw7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Task3 implements PersonDatabase {
    private final Map<Integer, Person> personById = new HashMap<>();
    private final Map<String, Set<Person>> personByAddress = new HashMap<>();
    private final Map<String, Set<Person>> personByName = new HashMap<>();
    private final Map<String, Set<Person>> personByPhone = new HashMap<>();

    private void addPerson(
        Map<String, Set<Person>> map,
        String key, Person person
    ) {
        Set<Person> byKey = map.get(key);
        if (byKey == null) {
            map.put(key, new HashSet<>(Set.of(person)));
        } else {
            byKey.add(person);
        }
    }

    @Override public synchronized void add(Person person) {
        personById.put(person.id(), person);
        addPerson(personByName, person.name(), person);
        addPerson(personByAddress, person.address(), person);
        addPerson(personByPhone, person.phoneNumber(), person);
    }

    @Override public synchronized void delete(int id) {
        Person personTODelete = personById.remove(id);
        if (personTODelete != null) {
            personByName.get(personTODelete.name()).remove(personTODelete);
            personByAddress.get(personTODelete.address())
                .remove(personTODelete);
            personByPhone.get(personTODelete.phoneNumber())
                .remove(personTODelete);
        }
    }

    @Override public synchronized List<Person> findByName(String name) {
        return new ArrayList<>(personByName.getOrDefault(name, Set.of()));
    }

    @Override public synchronized List<Person> findByAddress(String address) {
        return new ArrayList<>(personByAddress.getOrDefault(address, Set.of()));
    }

    @Override public synchronized List<Person> findByPhone(String phone) {
        return new ArrayList<>(personByPhone.getOrDefault(phone, Set.of()));
    }
}
