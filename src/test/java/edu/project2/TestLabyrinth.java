package edu.project2;

import edu.project2.labyrint_bulding.InvalidSizeException;
import edu.project2.labyrint_bulding.Labyrinth;
import edu.project2.labyrint_bulding.Rect2IntLabyrinth;
import java.util.List;

class TestLabyrinth extends Rect2IntLabyrinth {
    List<List<Type>> labyrinth;

    TestLabyrinth(List<List<Labyrinth.Type>> fields) throws InvalidSizeException {
        super(fields.size(), fields.get(0).size());
        labyrinth = fields;
    }

    @Override
    public List<Rect2IntCoord> getStart() {
        return List.of(new Rect2IntCoord(0, 0));
    }

    @Override
    public List<Rect2IntCoord> getAim() {
        return List.of(new Rect2IntCoord(labyrinth.size() - 1, 0));
    }

    @Override
    public Type getType(Rect2IntCoord coordinates) {
        return labyrinth.get(coordinates.y()).get(coordinates.x());
    }
}
