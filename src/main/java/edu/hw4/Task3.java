package edu.hw4;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class Task3 {
    private Task3() {
    }

    public static Map<Animal.Type, Integer> groupCountersByType(Collection<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(animal -> 1)));

    }
}
