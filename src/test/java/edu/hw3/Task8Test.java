package edu.hw3;

import edu.hw3.task8.BackwardIterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class Task8Test {
    @Test
    void listTest() {
        List<Integer> list = List.of(1, 2, 3);
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(list);
        Assertions.assertTrue(backwardIterator.hasNext());
        Assertions.assertEquals(backwardIterator.next(), 3);
        Assertions.assertEquals(backwardIterator.next(), 2);
        Assertions.assertEquals(backwardIterator.next(), 1);
        Assertions.assertFalse(backwardIterator.hasNext());
        Assertions.assertNull(backwardIterator.next());
    }

    @Test
    void removeTest() {

    }
}
