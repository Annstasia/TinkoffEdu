package edu.hw4;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class Task5 {
    private Task5() {
    }

    public static Animal.Sex maxSex(Collection<Animal> animals) {

        return animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting())).entrySet().stream()
            .max(Comparator.comparingLong(Map.Entry::getValue))
            .map(Map.Entry::getKey)
            .orElse(Animal.Sex.M);
    }
}
