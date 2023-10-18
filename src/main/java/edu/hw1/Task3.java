package edu.hw1;

import java.util.Arrays;
import java.util.OptionalInt;

public class Task3 {
    private Task3() {
    }

    public static boolean isNestable(int[] arr1, int[] arr2) {
        OptionalInt arr1Min = Arrays.stream(arr1).min();
        OptionalInt arr1Max = Arrays.stream(arr1).max();
        OptionalInt arr2Min = Arrays.stream(arr2).min();
        OptionalInt arr2Max = Arrays.stream(arr2).max();
        // считается, что пустой массив вложен в любой, в том числе пустой
        return arr1Min.isEmpty()
            || (arr2Min.isPresent()
            && arr1Min.getAsInt() > arr2Min.getAsInt()
            && arr1Max.getAsInt() < arr2Max.getAsInt());
    }
}
