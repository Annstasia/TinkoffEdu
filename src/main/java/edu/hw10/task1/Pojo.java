package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;

public class Pojo {
    private final Integer integer;
    private final int intInRange;

    public Pojo(@Max(value = 10) Integer integer, @Max(value = 10) @Min(value = -10) int intInRange) {
        this.integer = integer;
        this.intInRange = intInRange;
    }

    public static Pojo create(@Max(value = 10) Integer integer, @Max(value = 10) @Min(value = -10) int intInRange) {
        return new Pojo(integer, intInRange);
    }

    @Override
    public String toString() {
        return integer + " " + intInRange;
    }

    public Integer getInteger() {
        return integer;
    }

    public int getIntInRange() {
        return intInRange;
    }
}
