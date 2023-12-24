package edu.hw7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Task3Test {
    static final List<Person> testList;
    PersonDatabase database = new Task3();
    static {
        testList = new ArrayList<>();
        int index = 0;
        for (int name = 0; name < 5; ++name) {
            for (int addr = 0; addr < 5; ++addr) {
                for (int phone = 0; phone < 50; ++phone) {
                    ++index;
                    testList.add(new Person(index, String.valueOf(name),
                        String.valueOf(addr), String.valueOf(phone)));
                }
            }
        }
    }

    @BeforeEach
    public void setDatabase() {
        database = new Task3();
    }
    @Test
    public void testAdd() throws InterruptedException {
        Thread producer = new Thread(()->{
            for (Person person : testList) {
                database.add(person);
            };
        });
        Thread consumer1 = new Thread(()-> {
            for (Person person : testList) {
                List<String> keys = List.of(person.name(), person.address(),
                    person.phoneNumber());
                List<Function<String, List<Person>>> finders =
                    List.of(database::findByName, database::findByAddress,
                        database::findByPhone);
                for (int i = 0; i < 3; ++i) {
                    for (int j = 0; j < 3; ++j) {
                        if (i == j) {
                            continue;
                        }
                        List<Person> findByI =
                            finders.get(i).apply(keys.get(i));
                        List<Person> findByJ =
                            finders.get(j).apply(keys.get(j));
                        Assertions.assertTrue(!findByI.contains(person) || findByJ.contains(person));
                    }
                }
            }
        });

        Thread consumer2 = new Thread(consumer1);
        producer.start();
        consumer1.start();
        consumer2.start();
        producer.join();
        consumer1.join();
        consumer2.join();
    }

    @Test
    void testDelete() throws InterruptedException {
        for (Person person : testList) {
            database.add(person);
        }
        Thread producer = new Thread(()->{
            for (Person person : testList) {
                database.delete(person.id());
            }
        });
        Thread consumer1 = new Thread(()-> {
            for (Person person : testList) {
                List<String> keys = List.of(person.name(), person.address(),
                    person.phoneNumber());
                List<Function<String, List<Person>>> finders =
                    List.of(database::findByName, database::findByAddress,
                        database::findByPhone);
                for (int i = 0; i < 3; ++i) {
                    for (int j = 0; j < 3; ++j) {
                        if (i == j) {
                            continue;
                        }
                        List<Person> findByI =
                            finders.get(i).apply(keys.get(i));
                        List<Person> findByJ =
                            finders.get(j).apply(keys.get(j));
                        Assertions.assertTrue(findByI.contains(person) || !findByJ.contains(person));
                    }
                }
            }
        });

        Thread consumer2 = new Thread(consumer1);
        producer.start();
        consumer1.start();
        consumer2.start();
        producer.join();
        consumer1.join();
        consumer2.join();
    }

}
