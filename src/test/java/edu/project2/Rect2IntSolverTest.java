package edu.project2;

import edu.project2.labyrint_bulding.InvalidSizeException;
import edu.project2.labyrint_bulding.Labyrinth;
import edu.project2.labyrint_bulding.Rect2IntLabyrinth;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Rect2IntSolverTest {
    private static final Labyrinth.Type R = Labyrinth.Type.ROAD;
    private static final Labyrinth.Type W = Labyrinth.Type.WALL;


    @Test
    void solveSquareTest() throws InvalidSizeException {
        List<List<Labyrinth.Type>> fields = List.of(
            List.of(R, R, R),
            List.of(W, W, R),
            List.of(R, R, R)
        );
        Rect2IntLabyrinth labyrinth = new TestLabyrinth(fields);
        Assertions.assertArrayEquals(Rect2IntSolver.solve(labyrinth).toArray(), List.of(
            new Rect2IntCoord(0, 0),
            new Rect2IntCoord(0, 1),
            new Rect2IntCoord(0, 2),
            new Rect2IntCoord(1, 2),
            new Rect2IntCoord(2, 2),
            new Rect2IntCoord(2, 1),
            new Rect2IntCoord(2, 0)
        ).toArray());
        Assertions.assertNull(Rect2IntSolver.solve(labyrinth, new Rect2IntCoord(0, 0), new Rect2IntCoord(1, 0)));
    }

    @Test
    void solveLongRectTest() throws InvalidSizeException {
        List<List<Labyrinth.Type>> fields = List.of(
            List.of(R, R, R, W, R),
            List.of(R, W, R, W, R),
            List.of(W, W, R, W, W),
            List.of(R, R, R, R, R)
        );
        Rect2IntLabyrinth labyrinth = new TestLabyrinth(fields);
        Assertions.assertArrayEquals(Rect2IntSolver.solve(labyrinth).toArray(), List.of(
            new Rect2IntCoord(0, 0),
            new Rect2IntCoord(0, 1),
            new Rect2IntCoord(0, 2),
            new Rect2IntCoord(1, 2),
            new Rect2IntCoord(2, 2),
            new Rect2IntCoord(3, 2),
            new Rect2IntCoord(3, 1),
            new Rect2IntCoord(3, 0)
            ).toArray());
        Assertions.assertArrayEquals(Rect2IntSolver.solve(labyrinth,
            new Rect2IntCoord(0, 4),
            new Rect2IntCoord(1, 4)).toArray(), List.of(
            new Rect2IntCoord(0, 4),
            new Rect2IntCoord(1, 4)).toArray());
    }
}
