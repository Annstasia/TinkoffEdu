package edu.hw3;

public class Task1 {
    private Task1() {
    }

    public static String atbash(String input) {
        char[] charArr = input.toCharArray();
        for (int i = 0; i < charArr.length; ++i) {
            if (charArr[i] <= 'Z' && charArr[i] >= 'A') {
                // delta = letter - 'A'
                // new_letter = 'Z' - delta
                charArr[i] = (char) ('A' + 'Z' - charArr[i]);
            } else if (charArr[i] <= 'z' && charArr[i] >= 'a') {
                charArr[i] = (char) ('a' + 'z' - charArr[i]);
            }
        }
        return new String(charArr);
    }
}
