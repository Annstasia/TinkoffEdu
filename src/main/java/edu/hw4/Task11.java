package edu.hw4;

import java.util.Collection;
import java.util.List;

public class Task11 {
    private final static int MIN_HEIGHT = 100;

    private Task11() {
    }

    public static List<Animal> bigBites(Collection<Animal> animals) {
        return animals.stream().filter(animal -> animal.bites() && (animal.height() > MIN_HEIGHT)).toList();
    }
}
