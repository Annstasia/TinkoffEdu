package edu.hw9.task3.labyrint_bulding;

public class InvalidSizeException extends Exception {
    InvalidSizeException(int height, int width) {
        super("Размер лабиринта должен быть больше 2 по каждой оси. Задан размер " + height + " на " + width);
    }
}
