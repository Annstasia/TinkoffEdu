package edu.hw4;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class Task6 {
    private Task6() {
    }

    public static Map<Animal.Type, Animal> heaviestByType(Collection<Animal> animals) {
        return animals.stream().collect(Collectors.toMap(Animal::type, (an) -> an,
            (an1, an2) -> (an1.weight() < an2.weight() ? an2 : an1)
        ));
    }
}
