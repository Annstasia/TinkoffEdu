package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {
    private Task3() {
    }

    public static <T> Map<T, Integer> freqDict(List<T> list) {
        Map<T, Integer> answer = new HashMap<>();
        for (T obj : list) {
            answer.put(obj, answer.getOrDefault(obj, 0) + 1);
        }
        return answer;
    }
}
