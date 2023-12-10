package edu.hw9.task3.labyrint_bulding;

import edu.hw9.task3.Rect2IntCoord;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Rect2MixedDFSGenerator1start1exit implements Generator {
    public final static List<Rect2IntCoord> DELTA_X = List.of(new Rect2IntCoord(0, -1), new Rect2IntCoord(0, 1));
    public final static List<Rect2IntCoord> DELTA_Y = List.of(new Rect2IntCoord(-1, 0), new Rect2IntCoord(1, 0));
    Rect2IntLabyrinth labyrinth;
    List<Rect2IntCoord> start;
    List<Rect2IntCoord> exit;

    Rect2MixedDFSGenerator1start1exit(Rect2IntLabyrinth labyrinth) {
        this.labyrinth = labyrinth;
        start = labyrinth.start;
        exit = labyrinth.exit;
    }

    @Override public void generate() {
        clear();

        start.add(new Rect2IntCoord(0, 0));
        labyrinth.setType(start.get(0), Labyrinth.Type.ROAD);
        Rect2IntCoord lowest = start.get(0);
        while (lowest.y() + 1 < labyrinth.getHeight()) {
            lowest = dfs(lowest, labyrinth, lowest, new Rect2IntCoord(-1, 0), 0);
        }
        exit.add(lowest);
    }

    private boolean inBorders(Rect2IntCoord coords) {
        return coords.x() >= 0 && coords.x() < labyrinth.getWidth()
            && coords.y() >= 0 && coords.y() < labyrinth.getHeight();
    }

    private boolean checkAvailableToRoad(Rect2IntCoord coords) {
        if (!inBorders(coords)) {
            return false;
        }
        if (labyrinth.getType(coords).equals(Labyrinth.Type.ROAD)) {
            return false;
        }

        for (Rect2IntCoord dx : DELTA_X) {
            for (Rect2IntCoord dy : DELTA_Y) {
                if (inBorders(dx.add(coords)) && inBorders(dy.add(coords))
                    && labyrinth.getType(dx.add(coords)).equals(Labyrinth.Type.ROAD)
                    && labyrinth.getType(dy.add(coords)).equals(Labyrinth.Type.ROAD)
                    && labyrinth.getType(dy.add(coords).add(dx)).equals(Labyrinth.Type.ROAD)) {
                    return false;
                }
            }
        }
        return true;
    }

    private Rect2IntCoord dfs(
        Rect2IntCoord vertex,
        Rect2IntLabyrinth labyrinth,
        Rect2IntCoord prevLowest,
        Rect2IntCoord prevDelta,
        int duplicateNumber
    ) {
        Rect2IntCoord lowest = prevLowest;
        ArrayList<Rect2IntCoord> deltas = new ArrayList<>();
        ArrayList<Integer> duplicates = new ArrayList<>();
        if (duplicateNumber <= 0 || !checkAvailableToRoad(vertex.add(prevDelta))) {
            deltas.addAll(DELTA_X);
            deltas.addAll(DELTA_Y);
            deltas.remove(prevDelta);
            Collections.shuffle(deltas, ThreadLocalRandom.current());
            int bound = Math.min(labyrinth.getHeight(), labyrinth.getWidth()) / 2 + 1;
            for (int i = 0; i < deltas.size(); ++i) {
                duplicates.add(ThreadLocalRandom.current().nextInt(1, bound));
            }
        } else {
            deltas.add(prevDelta);
            duplicates.add(duplicateNumber);
        }
        for (int i = 0; i < deltas.size(); ++i) {
            Rect2IntCoord neighbour = deltas.get(i).add(vertex);
            if (checkAvailableToRoad(neighbour)) {
                labyrinth.setType(neighbour, Labyrinth.Type.ROAD);
                if (neighbour.y() > lowest.y()) {
                    lowest = neighbour;
                }
                lowest = dfs(neighbour, labyrinth, lowest, deltas.get(i), duplicates.get(i) - 1);
            }
        }
        return lowest;
    }

    private void clear() {
        for (int i = 0; i < labyrinth.getHeight(); ++i) {
            for (int j = 0; j < labyrinth.getWidth(); ++j) {
                labyrinth.setType(i, j, Labyrinth.Type.WALL);
            }
        }
        start.clear();
        exit.clear();
    }
}
