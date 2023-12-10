package edu.hw9.task3;

public class Rect2IntCoord implements Coordinates {
    private final int xCoord;
    private final int yCoord;

    public Rect2IntCoord(Rect2IntCoord another) {
        this.xCoord = another.xCoord;
        this.yCoord = another.yCoord;
    }

    public Rect2IntCoord(int y, int x) {
        this.yCoord = y;
        this.xCoord = x;
    }

    public int x() {
        return xCoord;
    }

    public int y() {
        return yCoord;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Rect2IntCoord coords = (Rect2IntCoord) o;

        if (xCoord != coords.xCoord) {
            return false;
        }
        return yCoord == coords.yCoord;
    }

    @Override public int hashCode() {
        int result = xCoord;
        result = 31 * result + yCoord;
        return result;
    }

    public Rect2IntCoord add(Rect2IntCoord coord2) {
        return new Rect2IntCoord(this.yCoord + coord2.y(), this.xCoord + coord2.x());
    }
}
