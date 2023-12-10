package edu.hw4;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class Task16 {
    private Task16() {
    }

    public static List<Animal> sortTypeSexName(Collection<Animal> animals) {
        return animals.stream().sorted(Comparator.comparing(Animal::name)).sorted(Comparator.comparing(Animal::sex))
            .sorted(Comparator.comparing(Animal::type)).toList();
    }
}
