package edu.hw9.task2;

import edu.hw9.task2.solution2.SpeedLinkedListMVP;
import org.junit.jupiter.api.Test;

public class TestSpeedList {
    @Test
    void testMove() {
        SpeedLinkedListMVP<Integer> list1 = new SpeedLinkedListMVP<>();
        SpeedLinkedListMVP<Integer> list2 = new SpeedLinkedListMVP<>();
        SpeedLinkedListMVP<Integer> list3 = new SpeedLinkedListMVP<>();
        list1.add(1);
        list2.add(2);
        list1.move(list3);
        list1.move(list2);
        System.out.println(list1.toList());

    }
}
