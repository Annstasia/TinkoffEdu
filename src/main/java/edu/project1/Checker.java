package edu.project1;

public class Checker {
    private Checker() {
    }

    public static GuessResult check(Session session, char letter) {
        if (session.getAttempts().contains(letter)) {
            return new GuessResult.UselessGuess(session.getAttempts().size(),
                session.getMaxAttempts(), session.getState()
            );
        }
        int index = 0;
        int count = 0;
        while ((index = session.getPrivateWord().indexOf(letter, index)) != -1) {
            session.getState()[index++] = letter;
            ++count;
        }
        session.getAttempts().add(letter);
        session.setGuessedCount(session.getGuessedCount() + count);
        int attemptsCnt = session.getAttempts().size();
        GuessResult result;
        if (attemptsCnt == session.getMaxAttempts()) {
            result = new GuessResult.Defeat(attemptsCnt, session.getMaxAttempts(), session.getState());
        } else if (count == 0) {
            result = new GuessResult.FailedGuess(attemptsCnt, session.getMaxAttempts(), session.getState());
        } else if (session.getGuessedCount() == session.getPrivateWord().length()) {
            result = new GuessResult.Win(attemptsCnt, session.getMaxAttempts(), session.getState());
        } else {
            result = new GuessResult.SuccessfulGuess(attemptsCnt, session.getMaxAttempts(), session.getState());
        }
        return result;
    }
}
