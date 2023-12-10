package edu.hw4.task19_20.validators;

import edu.hw4.Animal;
import edu.hw4.task19_20.Validator;
import edu.hw4.task19_20.errors.NegativeException;
import edu.hw4.task19_20.errors.WeightError;
import java.util.Optional;

public class WeightValidator implements Validator {
    @Override
    public Optional<WeightError> validate(Animal animal) {
        if (animal.weight() < 0) {
            return Optional.of(new WeightError(new NegativeException(animal.weight())));
        }
        return Optional.empty();
    }
}
