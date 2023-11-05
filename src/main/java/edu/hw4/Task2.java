package edu.hw4;

import java.util.Collection;
import java.util.List;

public class Task2 {
    private Task2() {
    }

    public static List<Animal> sortWeightFirstK(Collection<Animal> animals, int k) {
        return animals.stream().sorted((animal1, animal2) -> Integer.compare(animal2.weight(), animal1.weight()))
            .limit(k).toList();
    }
}
