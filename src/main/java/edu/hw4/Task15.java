package edu.hw4;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class Task15 {
    private Task15() {
    }

    public static Map<Animal.Type, Integer> sumWeightByTypeFilterAge(Collection<Animal> animals, int k, int l) {
        return animals.stream().filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }
}
