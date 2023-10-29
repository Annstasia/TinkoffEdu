package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private Task2() {
    }

    public static List<String> clusterize(String brackets) {
        int startOfCluster = 0;
        int balance = 0;
        ArrayList<String> answer = new ArrayList<>();
        for (int i = 0; i < brackets.length(); ++i) {
            balance += (brackets.charAt(i) == '(' ? 1 : -1);
            if (balance == 0) {
                answer.add(brackets.substring(startOfCluster, i + 1));
                startOfCluster = i + 1;
            }
        }
        return answer;
    }
}
