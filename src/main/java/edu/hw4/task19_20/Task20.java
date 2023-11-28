package edu.hw4.task19_20;

import edu.hw4.Animal;
import edu.hw4.task19_20.errors.ValidationError;
import edu.hw4.task19_20.validators.AgeValidator;
import edu.hw4.task19_20.validators.NameValidator;
import edu.hw4.task19_20.validators.WeightValidator;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Task20 {
    private Task20() {
    }

    // если верим в существование решения из задания 19 и хотим
    // просто конвертировать Set в строку
    public static Map<String, String> validate(Collection<Animal> animals) {
        return Task19.validate(animals).entrySet().stream().map(
            entry -> new AbstractMap.SimpleImmutableEntry<>(
                entry.getKey(),
                entry.getValue().stream().map(ValidationError::toPrettyString)
                    .sorted().collect(Collectors.joining("\n"))
            )
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    // Если реализовывать с нуля
    public static Map<String, String> validate2(Collection<Animal> animals) {
        return animals.stream()
            .map(animal -> {
                PriorityQueue<String> answer = new PriorityQueue<>();
                for (Validator validator
                    : List.of(new AgeValidator(), new WeightValidator(), new NameValidator())) {
                    validator.validate(animal).ifPresent((error) -> answer.add(error.toPrettyString()));
                }
                return new AbstractMap.SimpleImmutableEntry<>(
                    animal.name(),
                    String.join("\n", answer)
                );
            })
            .filter(stringSetEntry -> !stringSetEntry.getValue().isEmpty())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
