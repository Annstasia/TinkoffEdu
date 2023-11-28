package edu.hw4.task19_20.validators;

import edu.hw4.Animal;
import edu.hw4.task19_20.Validator;
import edu.hw4.task19_20.errors.AgeError;
import edu.hw4.task19_20.errors.NegativeException;
import java.util.Optional;

public class AgeValidator implements Validator {
    @Override
    public Optional<AgeError> validate(Animal animal) {
        if (animal.age() < 0) {
            return Optional.of(new AgeError(new NegativeException(animal.age())));
        }
        return Optional.empty();
    }
}
