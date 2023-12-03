package edu.hw8.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class OneThreadGeneratorTest {
    @Test
    void simpleTest() {
        List<String> passwords = new ArrayList<>();
        List<String> alphabet = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; ++c) {
            alphabet.add(String.valueOf(c));
        }
        for (char c = 'A'; c <= 'Z'; ++c) {
            alphabet.add(String.valueOf(c));
        }
        for (char c = '0'; c <= '9'; ++c) {
            alphabet.add(String.valueOf(c));
        }
        List<String> alphabetWithNothing = new ArrayList<>();
        alphabetWithNothing.add("");
        alphabetWithNothing.addAll(alphabet);
        for (String last : alphabetWithNothing) {
            for (String first : alphabet) {
                passwords.add(new String(new StringBuilder().append(last).append(first)));
            }
        }
        UnboundedGenerator generator = new UnboundedGenerator();
        for (String password : passwords) {
            Assertions.assertEquals(password, generator.nextPassword());
        }
    }
}
