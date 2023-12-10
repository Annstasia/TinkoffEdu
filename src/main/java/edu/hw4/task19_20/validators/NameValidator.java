package edu.hw4.task19_20.validators;

import edu.hw4.Animal;
import edu.hw4.task19_20.Validator;
import edu.hw4.task19_20.errors.NameError;
import java.util.Optional;

public class NameValidator implements Validator {
    @Override
    public Optional<NameError> validate(Animal animal) {
        if (animal.name() == null) {
            return Optional.of(new NameError(new NullPointerException("null name")));
        } else if (animal.name().isEmpty()) {
            return Optional.of(new NameError("empty stirng"));
        }
        return Optional.empty();
    }
}
