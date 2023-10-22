package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Console {
    private final static Logger LOGGER = LogManager.getLogger();
    private static Preview preview = new PreviewImp();

    private Console() {
    }

    private static String resultInfo(GuessResult result) {
        return "Использовано " + result.attempt() + " из " + result.maxAttempts() + " попыток. Слово: "
            + preview.preview(result.state());
    }

    private static String startString(Session session) {
        return "Слово: " + preview.preview(session.getState()) + ". " + session.getMaxAttempts()
            + " попыток.\n Введите букву";
    }

    // main интересный комментарий
    // main интересный комментарий
    // main интересный комментарий
    public static void run() {
        Manager manager = new Manager();
        int userId = 0;
        Level level = Level.SIMPLE;
        Language language = Language.RU;
        Session session = manager.newGame(userId, level, language);
        LOGGER.info(startString(session));
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.next();
            if (input.length() != 1) {
                LOGGER.info("Некорректный ввод. Введите 1 букву");
                continue;
            }
            GuessResult result = manager.guessLetter(session, input.charAt(0));
            if (result instanceof GuessResult.UselessGuess) {
                LOGGER.info("Буква уже была угадана. " + resultInfo(result));
            } else if (result instanceof GuessResult.SuccessfulGuess) {
                LOGGER.info("Верно. " + resultInfo(result));
            } else if (result instanceof GuessResult.FailedGuess) {
                LOGGER.info("Неверно. " + resultInfo(result));
            } else {
                if (result instanceof GuessResult.Win) {
                    LOGGER.info("Победа! " + resultInfo(result));
                } else if (result instanceof GuessResult.Defeat) {
                    LOGGER.info("Поражение. " + resultInfo(result));
                }
                session = manager.newGame(userId, level, language);
                LOGGER.info(startString(session));
            }
        }
    }
}
