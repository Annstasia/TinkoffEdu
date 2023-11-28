package edu.project2.labyrint_bulding;

import edu.project2.Coordinates;
import java.util.List;

public interface Labyrinth<C extends Coordinates> {
    enum Type {
        ROAD,
        WALL
    }

    List<C> getStart();

    List<C> getAim();

    void regenerate();

    Type getType(C coordinates);
}
