package edu.hw4;

import java.util.Collection;
import java.util.List;

public class Task13 {
    private Task13() {
    }

    public static List<Animal> complexNames(Collection<Animal> animals) {
        return animals.stream().filter(animal -> animal.name().contains(" ")).toList();
    }
}
