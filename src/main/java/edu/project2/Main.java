package edu.project2;

import edu.project2.labyrint_bulding.InvalidSizeException;
import edu.project2.labyrint_bulding.Rect2IntLabyrinth;
import java.util.Collection;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// В консоли некрасиво отображается из-за отступов между строками
// рекомендуется при запуске поставить отступ 0
// (В jetbrains settings->console font -> light height)
public class Main {
    private Main() {
    }

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String NEW = "лабиринт";
    private final static String SOLVE = "пройти";
    private final static String SOLVE_CUSTOM = "путь";
    private final static String PRINT = "печать";

    private final static String REGENERATE = "другой";
    private final static int MIN_HEIGHT = 3;
    private final static int MIN_WIDTH = 3;

    public static void main(String[] args) {
        Rect2IntLabyrinth labyrinth;
        try {
            labyrinth = new Rect2IntLabyrinth(MIN_HEIGHT, MIN_WIDTH);
        } catch (InvalidSizeException e) {
            LOGGER.error(e); // не вызовется. честно
            return;
        }
        Rect2IntConsoleMapper mapper = new Rect2IntConsoleMapper();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String command = scanner.next();
                if (command.equalsIgnoreCase(NEW)) {
                    int height = scanner.nextInt();
                    int width = scanner.nextInt();
                    labyrinth = new Rect2IntLabyrinth(height, width);
                    labyrinth.regenerate();
                } else if (command.equalsIgnoreCase(SOLVE)) {
                    LOGGER.info(mapper.toConsoleString(labyrinth, Rect2IntSolver.solve(labyrinth)));
                } else if (command.equalsIgnoreCase(SOLVE_CUSTOM)) {
                    int startY = scanner.nextInt();
                    int startX = scanner.nextInt();
                    int endY = scanner.nextInt();
                    int endX = scanner.nextInt();
                    Collection<Rect2IntCoord> ans = Rect2IntSolver.solve(
                        labyrinth,
                        new Rect2IntCoord(startY, startX),
                        new Rect2IntCoord(endY, endX)
                    );
                    if (ans != null) {
                        LOGGER.info(mapper.toConsoleString(labyrinth, ans));
                    } else {
                        LOGGER.info("Выхода нет(");
                    }
                } else if (command.equalsIgnoreCase(PRINT)) {
                    LOGGER.info(mapper.toConsoleString(labyrinth));
                } else if (command.equalsIgnoreCase(REGENERATE)) {
                    labyrinth.regenerate();
                }

            }
        } catch (InvalidSizeException e) {
            LOGGER.error(e.getMessage());
        }
    }
}


