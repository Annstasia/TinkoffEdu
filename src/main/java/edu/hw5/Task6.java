package edu.hw5;

public class Task6 {
    private Task6() {
    }

    public static boolean isSubsequence(String subsequence, String mainString) {
        StringBuilder regexBuilder = new StringBuilder(".*");
        for (int i = 0; i < subsequence.length(); ++i) {
            regexBuilder.append(subsequence.charAt(i));
            regexBuilder.append(".*");
        }
        return mainString.matches(regexBuilder.toString());
    }
}
