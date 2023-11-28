package edu.hw4.task19_20;

import edu.hw4.Animal;
import edu.hw4.task19_20.errors.ValidationError;
import java.util.Optional;

public interface Validator {
    Optional<? extends ValidationError> validate(Animal animal);
}
