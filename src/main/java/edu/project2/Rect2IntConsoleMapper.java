package edu.project2;

import edu.project2.labyrint_bulding.Labyrinth;
import edu.project2.labyrint_bulding.Rect2IntLabyrinth;
import java.util.ArrayList;
import java.util.Collection;

public class Rect2IntConsoleMapper implements ConsoleMapper<Rect2IntCoord, Rect2IntLabyrinth> {
    private final static char PATH = '*';
    private final static char WALL = '▆';
    private final static char ROAD = '.';
    private final static char HORIZONTAL = '—';
    private final static char VERTICAL = '|';
    private final static char SPACE = ' ';
    private final static char START = 's';
    private final static char END = 'e';

    private final static int BORDER_DELTA = 1;
    private final static int BORDER_LINES = 2;

    @Override public String toConsoleString(Rect2IntLabyrinth rect2IntLabyrinth) {
        return charArrayToString(toCharArray(rect2IntLabyrinth));
    }

    @Override public String toConsoleString(Rect2IntLabyrinth labyrinth, Collection<Rect2IntCoord> path) {
        ArrayList<ArrayList<Character>> charArray = toCharArray(labyrinth);
        for (Rect2IntCoord coord : path) {
            charArray.get(coord.y() + BORDER_DELTA).set(coord.x() + BORDER_DELTA, PATH);
        }
        return charArrayToString(charArray);
    }

    private ArrayList<ArrayList<Character>> toCharArray(Rect2IntLabyrinth rect2IntLabyrinth) {
        final int rowsCnt = rect2IntLabyrinth.getHeight() + BORDER_LINES * BORDER_DELTA;
        final int colsCnt = rect2IntLabyrinth.getWidth() + BORDER_DELTA * BORDER_LINES;

        ArrayList<ArrayList<Character>> answer = new ArrayList<>(rowsCnt);
        ArrayList<Character> row = new ArrayList<>(colsCnt);
        for (int i = 0; i < rowsCnt; ++i) {
            row.add(HORIZONTAL);
        }
        answer.add(row);
        for (int i = 0; i < rect2IntLabyrinth.getHeight(); ++i) {
            row = new ArrayList<>(colsCnt);
            row.add(VERTICAL);
            for (int j = 0; j < rect2IntLabyrinth.getWidth(); ++j) {
                row.add(rect2IntLabyrinth.getType(new Rect2IntCoord(i, j))
                    .equals(Labyrinth.Type.ROAD) ? ROAD : WALL);
            }
            row.add(VERTICAL);
            answer.add(row);
        }
        for (Rect2IntCoord start : rect2IntLabyrinth.getStart()) {
            answer.get(start.y() + BORDER_DELTA).set(start.x() + BORDER_DELTA, START);
        }
        for (Rect2IntCoord aim : rect2IntLabyrinth.getAim()) {
            answer.get(aim.y() + BORDER_DELTA).set(aim.x() + BORDER_DELTA, END);
        }
        answer.add(new ArrayList<>(answer.get(0)));
        return answer;
    }

    private String charArrayToString(ArrayList<ArrayList<Character>> charArray) {
        StringBuilder builder = new StringBuilder();
        for (var row : charArray) {
            for (var c : row) {
                builder.append(c);
            }
            builder.append("\n");
        }
        return new String(builder);

    }

}
