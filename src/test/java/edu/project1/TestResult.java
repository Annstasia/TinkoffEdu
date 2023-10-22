package edu.project1;

import java.util.Arrays;
import java.util.HashSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestResult {
    @Test
    public void testSuccess() {
        Manager manager = new Manager();
        String word = "кот";
        char[] state = new char[word.length()];
        Arrays.fill(state, '*');
        Session session = new Session(0, word, 5, state, new HashSet<>(), 0);
        GuessResult result = manager.guessLetter(session, 'к');
        Assertions.assertInstanceOf(GuessResult.SuccessfulGuess.class, result);
        Assertions.assertEquals(result.attempt(), 1);
        Assertions.assertEquals(result.maxAttempts(), 5);
        Assertions.assertArrayEquals(result.state(), new char[] {'к', '*', '*'});
    }

    @Test
    public void testWin() {
        Manager manager = new Manager();
        String word = "к";
        char[] state = new char[word.length()];
        Arrays.fill(state, '*');
        Session session = new Session(0, word, 5, state, new HashSet<>(), 0);
        GuessResult result = manager.guessLetter(session, 'к');
        Assertions.assertInstanceOf(GuessResult.Win.class, result);
        Assertions.assertEquals(result.attempt(), 1);
        Assertions.assertEquals(result.maxAttempts(), 5);
        Assertions.assertArrayEquals(result.state(), new char[] {'к'});
    }

    @Test
    public void testDefeat() {
        Manager manager = new Manager();
        String word = "кот";
        char[] state = new char[word.length()];
        Arrays.fill(state, '*');
        Session session = new Session(0, word, 1, state, new HashSet<>(), 0);
        GuessResult result = manager.guessLetter(session, 'к');
        Assertions.assertInstanceOf(GuessResult.Defeat.class, result);
        Assertions.assertEquals(result.attempt(), 1);
        Assertions.assertEquals(result.maxAttempts(), 1);
        Assertions.assertArrayEquals(result.state(), new char[] {'к', '*', '*'});
    }

    @Test
    public void testFail() {
        Manager manager = new Manager();
        String word = "кот";
        char[] state = new char[word.length()];
        Arrays.fill(state, '*');
        Session session = new Session(0, word, 5, state, new HashSet<>(), 0);
        GuessResult result = manager.guessLetter(session, '猫');
        Assertions.assertInstanceOf(GuessResult.FailedGuess.class, result);
        Assertions.assertEquals(result.attempt(), 1);
        Assertions.assertEquals(result.maxAttempts(), 5);
        Assertions.assertArrayEquals(result.state(), new char[] {'*', '*', '*'});
    }

    @Test
    public void testUseless() {
        Manager manager = new Manager();
        String word = "кот";
        char[] state = new char[word.length()];
        Arrays.fill(state, '*');
        Session session = new Session(0, word, 5, state, new HashSet<>(), 0);
        GuessResult result = manager.guessLetter(session, '猫');
        result = manager.guessLetter(session, '猫');
        Assertions.assertInstanceOf(GuessResult.UselessGuess.class, result);
        Assertions.assertEquals(result.attempt(), 1);
        Assertions.assertEquals(result.maxAttempts(), 5);
        Assertions.assertArrayEquals(result.state(), new char[] {'*', '*', '*'});
    }

}
