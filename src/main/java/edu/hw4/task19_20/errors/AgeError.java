package edu.hw4.task19_20.errors;

public class AgeError extends ValidationError {
    public AgeError(Throwable cause) {
        super("Age error", cause);
    }
}
