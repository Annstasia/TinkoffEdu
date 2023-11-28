package edu.project2;

import edu.project2.labyrint_bulding.Labyrinth;
import java.util.Collection;

public interface ConsoleMapper<C extends Coordinates, L extends Labyrinth<C>> {
    String toConsoleString(L labyrinth);

    String toConsoleString(L labyrinth, Collection<C> path);

}
