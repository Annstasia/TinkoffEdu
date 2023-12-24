package edu.hw7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Task35Test extends Task3Test{
    static final List<Person> testList;
    static {
        testList = new ArrayList<>();
        int index = 0;
        for (int name = 0; name < 5; ++name) {
            for (int addr = 0; addr < 5; ++addr) {
                for (int phone = 0; phone < 5; ++phone) {
                    ++index;
                    testList.add(new Person(index, String.valueOf(name),
                        String.valueOf(addr), String.valueOf(phone)));
                }
            }
        }
    }
    @Override @BeforeEach
    public void setDatabase() {
        database = new Task35();
    }

    long getTime(PersonDatabase database) throws InterruptedException {
        long start = System.nanoTime();
        List<Thread> threads = new ArrayList<>();
        threads.add(new Thread(()->{
            for (Person person : testList) {
                database.add(person);
            };
        }));
        Runnable consume = ()-> {
            for (Person person : testList) {
                List<String> keys = List.of(person.name(), person.address(),
                    person.phoneNumber());
                List<Function<String, List<Person>>> finders =
                    List.of(database::findByName, database::findByAddress,
                        database::findByPhone);
                for (int k = 0; k < 1000; ++k) {
                    for (int i = 0; i < 3; ++i) {
                        for (int j = 0; j < 3; ++j) {
                            if (i == j) {
                                continue;
                            }
                            finders.get(i).apply(keys.get(i));
                            finders.get(j).apply(keys.get(j));
                        }
                    }
                }

            }
        };
        threads.add(new Thread(consume));
        threads.add(new Thread(consume));
        threads.add(new Thread(consume));
        threads.add(new Thread(consume));
        threads.add(new Thread(consume));
        threads.add(new Thread(consume));
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
        return System.nanoTime() - start;
    }

    @Test
    void compareTimeTest() throws InterruptedException {
        System.out.println("synchronized " + getTime(new Task3()));
        System.out.println("readwritelock " + getTime(new Task35()));
    }
}
