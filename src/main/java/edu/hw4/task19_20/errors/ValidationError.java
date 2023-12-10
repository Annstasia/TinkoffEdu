package edu.hw4.task19_20.errors;

public class ValidationError extends Exception {
    public ValidationError(String message) {
        super(message);
    }

    public ValidationError(String message, Throwable cause) {
        super(message, cause);
    }

    public String toPrettyString() {
        if (getCause() == null) {
            return super.getMessage();
        } else {
            return super.getMessage() + ". Cause: " + super.getCause().toString();
        }
    }

}
