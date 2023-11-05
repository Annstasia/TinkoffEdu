package edu.project2;

import edu.project2.labyrint_bulding.InvalidSizeException;
import edu.project2.labyrint_bulding.Rect2IntLabyrinth;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LabyrinthTest {
    @Test
    void invalidSizeTest() {
        Assertions.assertThrows(InvalidSizeException.class, ()->new Rect2IntLabyrinth(2, 2));
        Assertions.assertThrows(InvalidSizeException.class, ()->new Rect2IntLabyrinth(2, 3));
        Assertions.assertThrows(InvalidSizeException.class, ()->new Rect2IntLabyrinth(100, 2));
        Assertions.assertThrows(InvalidSizeException.class, ()->new Rect2IntLabyrinth(2, 100));
        Assertions.assertDoesNotThrow(()->new Rect2IntLabyrinth(4, 4));
    }

    @Test
    void generateTest() throws InvalidSizeException {
        int height = 100;
        int width = 20;

        Rect2IntLabyrinth labyrinth = new Rect2IntLabyrinth(height, width);
        labyrinth.regenerate();
        Assertions.assertEquals(labyrinth.getWidth(), width);
        Assertions.assertEquals(labyrinth.getHeight(), height);
        Assertions.assertNotNull(Rect2IntSolver.solve(labyrinth));
    }
}
