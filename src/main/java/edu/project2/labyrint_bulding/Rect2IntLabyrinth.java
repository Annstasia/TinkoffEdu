package edu.project2.labyrint_bulding;

import edu.project2.Rect2IntCoord;
import java.util.ArrayList;
import java.util.List;

public class Rect2IntLabyrinth implements Labyrinth<Rect2IntCoord> {
    final ArrayList<Rect2IntCoord> exit = new ArrayList<>();
    final ArrayList<Rect2IntCoord> start = new ArrayList<>();

    final static int MIN_WIDTH = 3;
    final static int MIN_HEIGHT = 3;
    private final List<List<Type>> labyrinth;
    private final Generator generator;
    private final int width;
    private final int height;

    public Rect2IntLabyrinth(int height, int width) throws InvalidSizeException {
        if (height < MIN_HEIGHT || width < MIN_WIDTH) {
            throw new InvalidSizeException(height, width);
        }
        labyrinth = new ArrayList<>(height);
        for (int i = 0; i < height; ++i) {
            labyrinth.add(new ArrayList<>(width));
            for (int j = 0; j < width; ++j) {
                labyrinth.get(i).add(Type.WALL);
            }
        }
        this.height = height;
        this.width = width;
        this.generator = new Rect2MixedDFSGenerator1start1exit(this);
    }

    void setType(Rect2IntCoord coord, Type type) {
        setType(coord.y(), coord.x(), type);
    }

    void setType(int y, int x, Type type) {
        labyrinth.get(y).set(x, type);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override public void regenerate() {
        generator.generate();
    }

    @Override public List<Rect2IntCoord> getStart() {
        return new ArrayList<>(start);
    }

    @Override public List<Rect2IntCoord> getAim() {
        return new ArrayList<>(exit);
    }

    @Override public Type getType(Rect2IntCoord coordinates) {
        return labyrinth.get(coordinates.y()).get(coordinates.x());
    }
}
