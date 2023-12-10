package edu.hw9.task3;

import edu.hw9.task3.labyrint_bulding.Labyrinth;
import edu.hw9.task3.labyrint_bulding.Rect2IntLabyrinth;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicBoolean;

// Тут используется SpeedLinkedListMVP, предложенный во второй задаче
// Способ с ссылкой на общее хранилище не сработал бы, так как для каждой ветки dfs путь отличается
public class DFSTask extends RecursiveTask<DFSTaskAnswer> {
    Rect2IntCoord coords;
    ArrayList<ArrayList<AtomicBoolean>> used;
    Rect2IntLabyrinth labyrinth;
    Collection<Rect2IntCoord> ends;

    public DFSTask(
        Rect2IntCoord coords,
        ArrayList<ArrayList<AtomicBoolean>> used,
        Rect2IntLabyrinth labyrinth,
        Collection<Rect2IntCoord> ends
    ) {
        this.coords = coords;
        this.used = used;
        this.labyrinth = labyrinth;
        this.ends = ends;
    }

    private static boolean inBorders(Rect2IntCoord coords, int maxHeight, int maxWidth) {
        return coords.x() >= 0 && coords.x() < maxWidth && coords.y() >= 0
            && coords.y() < maxHeight;
    }

    @Override
    protected DFSTaskAnswer compute() {
        List<DFSTask> children = new ArrayList<>();
        if (ends.contains(coords)) {
            SpeedLinkedListMVP<Rect2IntCoord> path = new SpeedLinkedListMVP<>();
            path.add(coords);
            return new DFSTaskAnswer(path, true);
        }
        for (Rect2IntCoord delta : List.of(
            new Rect2IntCoord(-1, 0),
            new Rect2IntCoord(1, 0),
            new Rect2IntCoord(0, -1),
            new Rect2IntCoord(0, 1)
        )) {

            Rect2IntCoord neighbour = coords.add(delta);
            if (!inBorders(neighbour, used.size(), used.get(0).size())) {
                continue;
            }
            if (labyrinth.getType(neighbour).equals(Labyrinth.Type.ROAD)
                && !used.get(neighbour.y()).get(neighbour.x()).getAndSet(true)
            ) {
                DFSTask child = new DFSTask(neighbour, used, labyrinth, ends);
                children.add(child);
                child.fork();
            }
        }
        for (DFSTask child : children) {
            DFSTaskAnswer childAnswer = child.join();
            if (childAnswer.solved()) {
                SpeedLinkedListMVP<Rect2IntCoord> path = new SpeedLinkedListMVP<>();
                path.add(coords);
                path.move(childAnswer.path());
                return new DFSTaskAnswer(path, true);
            }
        }
        return new DFSTaskAnswer(null, false);
    }
}
