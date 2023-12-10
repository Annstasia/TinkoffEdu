package edu.hw4.task19_20.errors;

public class NegativeException extends Exception {
    public NegativeException(int value) {
        super(value + " < 0");
    }

}
