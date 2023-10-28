package edu.project1;

import org.jetbrains.annotations.NotNull;

public interface WordGeneratorByLength {
    @NotNull String randomWord(DictionaryByLength dictionary, int start, int end);
}
