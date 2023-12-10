package edu.hw9.task3;

import edu.hw9.task3.labyrint_bulding.Rect2IntLabyrinth;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicBoolean;

public class Rect2IntSolver {
    private Rect2IntSolver() {
    }

    public static Collection<Rect2IntCoord> solve(Rect2IntLabyrinth labyrinth, Rect2IntCoord start, Rect2IntCoord end) {
        if (!inBorders(start, labyrinth.getHeight(), labyrinth.getWidth())
            || !inBorders(end, labyrinth.getHeight(), labyrinth.getWidth())) {
            return null;
        }
        return solve(labyrinth, List.of(start), List.of(end));
    }

    public static Collection<Rect2IntCoord> solve(Rect2IntLabyrinth labyrinth) {
        return solve(labyrinth, labyrinth.getStart(), labyrinth.getAim());
    }

    private static Collection<Rect2IntCoord> solve(
        Rect2IntLabyrinth labyrinth, Collection<Rect2IntCoord> starts, Collection<Rect2IntCoord> ends
    ) {
        ArrayList<ArrayList<Boolean>> used = new ArrayList<>(labyrinth.getHeight());
        for (int i = 0; i < labyrinth.getHeight(); ++i) {
            used.add(new ArrayList<>());
            for (int j = 0; j < labyrinth.getWidth(); ++j) {
                used.get(i).add(false);
            }
        }
        ArrayDeque<Rect2IntCoord> path = new ArrayDeque<>();
        for (Rect2IntCoord start : starts) {
            if (dfs(start, used, path, labyrinth, ends)) {
                return path;
            }
        }
        return null;
    }

    static boolean dfs(
        Rect2IntCoord coords,
        ArrayList<ArrayList<Boolean>> used,
        ArrayDeque<Rect2IntCoord> path,
        Rect2IntLabyrinth labyrinth,
        Collection<Rect2IntCoord> ends
    ) {
        ArrayList<ArrayList<AtomicBoolean>> usedAtomic = new ArrayList<>(used.size());
        for (ArrayList<Boolean> usedRow : used) {
            ArrayList<AtomicBoolean> atomicRow = new ArrayList<>(usedRow.size());
            usedAtomic.add(atomicRow);
            for (boolean usedCell : usedRow) {
                atomicRow.add(new AtomicBoolean(usedCell));
            }
        }
        usedAtomic.get(coords.y()).get(coords.x()).set(true);
        DFSTask searcher = new DFSTask(coords, usedAtomic, labyrinth, ends);
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            DFSTaskAnswer ans = forkJoinPool.invoke(searcher);
            if (ans.solved()) {
                path.addAll(ans.path().toList());
            }
            return ans.solved();
        }
    }
// Было раньше:
//    static boolean dfs(
//        Rect2IntCoord coords,
//        ArrayList<ArrayList<Boolean>> used,
//        ArrayDeque<Rect2IntCoord> path,
//        Rect2IntLabyrinth labyrinth,
//        Collection<Rect2IntCoord> ends
//    ) {
//        used.get(coords.y()).set(coords.x(), true);
//        path.add(coords);
//        if (ends.contains(coords)) {
//            return true;
//        }
//        for (Rect2IntCoord delta : List.of(
//            new Rect2IntCoord(-1, 0),
//            new Rect2IntCoord(1, 0),
//            new Rect2IntCoord(0, -1),
//            new Rect2IntCoord(0, 1)
//        )) {
//
//            Rect2IntCoord neighbour = coords.add(delta);
//            if (!inBorders(neighbour, used.size(), used.get(0).size())) {
//                continue;
//            }
//            if (!used.get(neighbour.y()).get(neighbour.x())
//                && labyrinth.getType(neighbour).equals(Labyrinth.Type.ROAD)) {
//                if (dfs(neighbour, used, path, labyrinth, ends)) {
//                    return true;
//                }
//            }
//        }
//        path.removeLast();
//        return false;
//    }

    private static boolean inBorders(Rect2IntCoord coords, int maxHeight, int maxWidth) {
        return coords.x() >= 0 && coords.x() < maxWidth && coords.y() >= 0
            && coords.y() < maxHeight;
    }
}
