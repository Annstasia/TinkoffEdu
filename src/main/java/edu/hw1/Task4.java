package edu.hw1;

public class Task4 {
    private Task4() {
    }

    public static String fixString(String string) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < string.length() - string.length() % 2; i += 2) {
            builder.append(string.charAt(i + 1));
            builder.append(string.charAt(i));
        }
        if (string.length() % 2 == 1) {
            builder.append(string.charAt(string.length() - 1));
        }
        return builder.toString();
    }
}
