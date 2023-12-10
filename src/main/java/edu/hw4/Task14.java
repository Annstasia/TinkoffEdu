package edu.hw4;

import java.util.Collection;

public class Task14 {
    private Task14() {
    }

    public static Boolean containsHigherKDog(Collection<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(animal -> animal.type().equals(Animal.Type.DOG) && animal.height() > k);
    }
}
