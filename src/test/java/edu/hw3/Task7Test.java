package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;

public class Task7Test {
    class NotComparable {
        private final String name;
        public NotComparable(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
    @Test
    void stringTest() {
        Task7<String, String> tree = new Task7<>();
        tree.add(null, "test");
        Assertions.assertTrue(tree.contains(null));
        Assertions.assertEquals(tree.get(null), "test");
    }

    @Test
    void comparatorTest() {
        Task7<NotComparable, String> tree = new Task7<>((first, second)->first.getName().compareTo(second.getName()));
        tree.add(null, "test");
        NotComparable obj = new NotComparable("name");
        tree.add(obj, "not comp");
        Assertions.assertTrue(tree.contains(null));
        Assertions.assertEquals(tree.get(obj), "not comp");
    }

    @Test
    void notComparableNoComparatorTest() {
        Task7<NotComparable, String> tree = new Task7<>();
        tree.add(null, "test");
        Assertions.assertTrue(tree.contains(null));
        NotComparable obj = new NotComparable("name");
        NotComparable obj2 = new NotComparable("name2");
        tree.add(obj, "not1 comp");
        Assertions.assertThrows(ClassCastException.class, ()->tree.add(obj2, "not2 comp"));
    }


}
