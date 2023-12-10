package edu.hw4.task19_20;

import edu.hw4.Animal;
import edu.hw4.task19_20.errors.ValidationError;
import edu.hw4.task19_20.validators.AgeValidator;
import edu.hw4.task19_20.validators.NameValidator;
import edu.hw4.task19_20.validators.WeightValidator;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Task19 {
    private Task19() {
    }

    public static Map<String, Set<ValidationError>> validate(Collection<Animal> animals) {
        return animals.stream()
            .map(animal -> {
                Set<ValidationError> answer = new HashSet<>();
                for (Validator validator
                    : List.of(new AgeValidator(), new WeightValidator(), new NameValidator())) {
                    validator.validate(animal).ifPresent(answer::add);
                }
                return new AbstractMap.SimpleImmutableEntry<>(animal.name(), answer);
            })
            .filter(stringSetEntry -> !stringSetEntry.getValue().isEmpty())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
