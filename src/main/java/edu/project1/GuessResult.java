package edu.project1;

public sealed interface GuessResult {
    int attempt();

    int maxAttempts();

    char[] state();

    record Defeat(int attempt, int maxAttempts, char[] state) implements GuessResult {
    }

    record Win(int attempt, int maxAttempts, char[] state) implements GuessResult {
    }

    record SuccessfulGuess(int attempt, int maxAttempts, char[] state) implements GuessResult {
    }

    record FailedGuess(int attempt, int maxAttempts, char[] state) implements GuessResult {
    }

    record UselessGuess(int attempt, int maxAttempts, char[] state) implements GuessResult {
    }
}
