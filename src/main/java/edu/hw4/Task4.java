package edu.hw4;

import java.util.Collection;

public class Task4 {
    private Task4() {
    }

    public static Animal longestName(Collection<Animal> animals) {
        return animals.stream().max((animal1, animal2) -> Integer.compare(
            animal1.name().length(),
            animal2.name().length()
        )).orElse(null);
    }
}
