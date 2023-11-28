package edu.hw4.task19_20.errors;

public class NameError extends ValidationError {
    public NameError(String message) {
        super("Name error: " + message);
    }

    public NameError(Throwable cause) {
        super("Name error", cause);
    }

}
