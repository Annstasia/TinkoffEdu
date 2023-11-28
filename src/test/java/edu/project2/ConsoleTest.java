package edu.project2;

import edu.project2.labyrint_bulding.InvalidSizeException;
import edu.project2.labyrint_bulding.Labyrinth;
import edu.project2.labyrint_bulding.Rect2IntLabyrinth;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.List;

public class ConsoleTest {

    private static final Labyrinth.Type R = Labyrinth.Type.ROAD;
    private static final Labyrinth.Type W = Labyrinth.Type.WALL;
    @Test
    public void viewTest() throws InvalidSizeException {
        List<List<Labyrinth.Type>> fields = List.of(
            List.of(R, R, R),
            List.of(W, W, R),
            List.of(R, R, R)
        );
        Rect2IntLabyrinth labyrinth = new TestLabyrinth(fields);
        Collection<Rect2IntCoord> path = Rect2IntSolver.solve(labyrinth);
        Rect2IntConsoleMapper mapper = new Rect2IntConsoleMapper();


        Assertions.assertEquals(mapper.toConsoleString(labyrinth),
            """
                —————
                |s..|
                |▆▆.|
                |e..|
                —————
                """
        );
        Assertions.assertEquals(mapper.toConsoleString(labyrinth, path),
            """
                —————
                |***|
                |▆▆*|
                |***|
                —————
                """
        );

    }
}
