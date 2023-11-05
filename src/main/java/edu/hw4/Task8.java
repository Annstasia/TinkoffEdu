package edu.hw4;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

public class Task8 {
    private Task8() {
    }

    public static Optional<Animal> heaviestFromSmallerK(Collection<Animal> animals, int k) {
        return animals.stream().filter(animal -> (animal.height() < k)).max(Comparator.comparingInt(Animal::weight));
    }
}
