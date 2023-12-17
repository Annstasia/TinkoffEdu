package edu.hw10.task2;

import java.io.Serializable;

public class CacheValidator implements Serializable {
    static int counter = 0;

    CacheValidator() {
        ++counter;
    }

    public int getCounter() {
        return counter;
    }

    public static void setCounter(int newValue) {
        counter = 0;
    }
}
