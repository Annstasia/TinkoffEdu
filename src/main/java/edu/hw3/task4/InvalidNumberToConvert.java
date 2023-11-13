package edu.hw3.task4;

public class InvalidNumberToConvert extends Exception {
    public InvalidNumberToConvert(int number) {
        super("Invalud number to convert: " + number + ". Should be number should be natural less than 4000");
    }
}
