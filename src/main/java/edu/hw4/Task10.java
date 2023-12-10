package edu.hw4;

import java.util.Collection;
import java.util.List;

public class Task10 {
    private Task10() {
    }

    public static List<Animal> animalsAgeDoesNotEqualPaws(Collection<Animal> animals) {
        return animals.stream().filter(animal -> animal.age() != animal.paws()).toList();
    }
}
