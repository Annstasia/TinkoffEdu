package edu.project1;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.jetbrains.annotations.NotNull;

// генерация через ThreadLocalRandom
public class WordGeneratorByLengthTL implements WordGeneratorByLength {
    @Override public @NotNull String randomWord(DictionaryByLength dictionary, int start, int end) {
        int length = ThreadLocalRandom.current().nextInt(start, end);
        List<String> words = dictionary.getByLength(length);
        return words.get(ThreadLocalRandom.current().nextInt(0, words.size()));
    }

}
