package edu.hw7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Task35 implements PersonDatabase {
    private final Map<Integer, Person> personById = new HashMap<>();
    private final Map<String, Set<Person>> personByAddress = new HashMap<>();
    private final Map<String, Set<Person>> personByName = new HashMap<>();
    private final Map<String, Set<Person>> personByPhone = new HashMap<>();
    ReadWriteLock lock = new ReentrantReadWriteLock(true);

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

    @Override public void add(Person person) {
        lock.writeLock().lock();
        personById.put(person.id(), person);
        addPerson(personByName, person.name(), person);
        addPerson(personByAddress, person.address(), person);
        addPerson(personByPhone, person.phoneNumber(), person);
        lock.writeLock().unlock();
    }

    @Override public void delete(int id) {
        lock.writeLock().lock();
        Person personTODelete = personById.remove(id);
        if (personTODelete != null) {
            personByName.get(personTODelete.name()).remove(personTODelete);
            personByAddress.get(personTODelete.address())
                .remove(personTODelete);
            personByPhone.get(personTODelete.phoneNumber())
                .remove(personTODelete);
        }
        lock.writeLock().unlock();
    }

    @Override public List<Person> findByName(String name) {
        try {
            lock.readLock().lock();
            return new ArrayList<>(personByName.getOrDefault(
                name,
                Set.of()
            ));
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override public List<Person> findByAddress(String address) {
        try {
            lock.readLock().lock();
            return new ArrayList<>(personByAddress.getOrDefault(
                address,
                Set.of()
            ));
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override public List<Person> findByPhone(String phone) {
        try {
            lock.readLock().lock();
            return new ArrayList<>(personByPhone.getOrDefault(
                phone,
                Set.of()
            ));
        } finally {
            lock.readLock().unlock();
        }
    }
}
