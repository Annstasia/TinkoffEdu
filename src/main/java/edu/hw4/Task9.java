package edu.hw4;

import java.util.Collection;

public class Task9 {
    private Task9() {
    }

    public static Integer pawSum(Collection<Animal> animals) {
        return animals.stream().map(Animal::paws).reduce(Integer::sum).orElse(0);
    }
}
