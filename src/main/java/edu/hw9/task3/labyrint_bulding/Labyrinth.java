package edu.hw9.task3.labyrint_bulding;

import edu.hw9.task3.Coordinates;
import java.util.List;

public interface Labyrinth<C extends Coordinates> {
    List<C> getStart();

    List<C> getAim();

    void regenerate();

    Type getType(C coordinates);

    enum Type {
        ROAD,
        WALL
    }
}
