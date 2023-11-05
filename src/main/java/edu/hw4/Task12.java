package edu.hw4;

import java.util.Collection;

public class Task12 {
    private Task12() {
    }

    public static Integer countFat(Collection<Animal> animals) {
        return Math.toIntExact(animals.stream().filter(animal -> animal.weight() > animal.height()).count());
    }
}
