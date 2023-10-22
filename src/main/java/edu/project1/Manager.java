package edu.project1;

import java.util.Arrays;
import java.util.HashSet;

public class Manager {
    private final DictionaryByLength ruDictionary =
        new DictionaryByLengthFromFile(Constants.RU);
    private final WordGeneratorByLength generatorByLength =
        new WordGeneratorByLengthTL();

    public Session newGame(int userId, Level level, Language language) {
        String word;
        int maxAttempts;
        switch (language) {
            case RU:
                maxAttempts = switch (level) {
                    case SIMPLE -> {
                        word = generatorByLength.randomWord(ruDictionary,
                            Constants.SIMPLE_MIN_RU, Constants.SIMPLE_MAX_RU
                        );
                        yield Constants.SIMPLE_ATTEMPT_RU;
                    }
                    case MIDDLE -> {
                        word = generatorByLength.randomWord(ruDictionary,
                            Constants.MIDDLE_MIN_RU,
                            Constants.MIDDLE_MAX_RU
                        );
                        yield Constants.MIDDLE_ATTEMPT_RU;
                    }
                    case HARD -> {
                        word = generatorByLength.randomWord(ruDictionary, Constants.HARD_MIN_RU, Constants.HARD_MAX_RU);
                        yield Constants.HARD_ATTEMPT_RU;
                    }
                };
                break;
            default: {
                word = "今生不";
                maxAttempts = 1;
            }
        }
        char[] state = new char[word.length()];
        Arrays.fill(state, '*');
        return new Session(userId, word, maxAttempts, state,
            new HashSet<Character>(), 0
        );
    }

    public GuessResult guessLetter(Session session, char letter) {
        return Checker.check(session, letter);
    }

}
