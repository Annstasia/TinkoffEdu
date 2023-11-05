package edu.hw4;

import java.util.Collection;

public class Task7 {
    private Task7() {
    }

    public static Animal kOldest(Collection<Animal> animals, int k) {
        return animals.stream().sorted((an1, an2) -> Integer.compare(an2.age(), an1.age())).limit(k).toList()
            .get(k - 1);
    }
}
