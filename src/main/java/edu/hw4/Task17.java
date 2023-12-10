package edu.hw4;

import java.util.Collection;

public class Task17 {
    private Task17() {
    }

    public static Boolean spidersBitesMoreThanDogs(Collection<Animal> animals) {
        return animals.stream().map(animal -> {
            if (animal.type().equals(Animal.Type.SPIDER) && animal.bites()) {
                return 1;
            } else if (animal.type().equals(Animal.Type.DOG) && animal.bites()) {
                return -1;
            }
            return 0;
        }).reduce(Integer::sum).orElse(0) > 0;
    }
}
