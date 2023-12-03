package edu.hw8.task3;

public class UnboundedGenerator implements PasswordGenerator {
    static char[] alphabet = new char['z' - 'a' + 1 + 'Z' - 'A' + 1 + '9' - '0' + 1];

    static {
        int index = 0;
        for (char[] letterRange : new char[][] {{'a', 'z'}, {'A', 'Z'}, {'0', '9'}}) {
            for (char letter = letterRange[0]; letter <= letterRange[1]; ++letter) {
                alphabet[index++] = letter;
            }
        }
    }

    StringBuilder lastPassword;
    int indexToChange = -1;

    UnboundedGenerator() {
        this("");
    }

    UnboundedGenerator(String initPassword) {
        lastPassword = new StringBuilder(initPassword);
    }

    private void add(int addCount) {
        for (int i = 0; i < addCount; ++i) {
            incrementInIndex(lastPassword.length() - 1);
        }
    }

    private void incrementInIndex(int indexToChange) {
        if (indexToChange == -1) {
            lastPassword.insert(0, 'a');
        } else {
            char letter = lastPassword.charAt(indexToChange);
            if (letter == 'z') {
                letter = 'A';
            } else if (letter == 'Z') {
                letter = '0';
            } else if (letter == '9') {
                letter = 'a';
            } else {
                ++letter;
            }
            lastPassword.setCharAt(indexToChange, letter);
            if (letter == 'a') {
                incrementInIndex(indexToChange - 1);
            }
        }

    }

    public String nextPassword() {
        incrementInIndex(lastPassword.length() - 1);
        return new String(lastPassword);
    }
}
