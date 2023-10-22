package edu.project1;

import java.util.Set;

public class Session {
    private final int userId;
    private final String privateWord;
    private final int maxAttempts;
    private char[] state;
    private Set<Character> attempts;
    private int guessedCount;

    public Session(
        int userId,
        String privateWord,
        int maxAttempts,
        char[] state,
        Set<Character> attempts,
        int guessedCount
    ) {
        this.userId = userId;
        this.privateWord = privateWord;
        this.maxAttempts = maxAttempts;
        this.state = state;
        this.attempts = attempts;
        this.guessedCount = guessedCount;
    }

    public int getUserId() {
        return userId;
    }

    public String getPrivateWord() {
        return privateWord;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public char[] getState() {
        return state;
    }

    public void setState(char[] state) {
        this.state = state;
    }

    public Set<Character> getAttempts() {
        return attempts;
    }

    public void setAttempts(Set<Character> attempts) {
        this.attempts = attempts;
    }

    public int getGuessedCount() {
        return guessedCount;
    }

    public void setGuessedCount(int guessedCount) {
        this.guessedCount = guessedCount;
    }
}
