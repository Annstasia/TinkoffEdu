package edu.hw2.task2;

public class Square extends Rectangle {
    public Square(int x) {
        super(x, x);
    }

    @Override
    public boolean setWidth(int width) {
        return false;
    }

    @Override
    public boolean setHeight(int height) {
        return false;
    }
}
