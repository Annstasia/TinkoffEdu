package edu.hw5;

public class Task8 {
    private Task8() {
    }

    public static String regexOddLength() {
        return "^(?:[01]{2})*[01]$";
    }

    public static String regexOddStart0EvenStarts1() {
        return "^(?:0(?:[01]{2})*|1(?:[01]{2})*[01])$";
    }

    public static String regexZeroCountDivisibleBy3() {
        return "^1*(?:01*01*01*)*$";
    }

    public static String regexNot11Not111() {
        return "^(?:111[01]+|110[01]*|10[01]*|1|0[01]*|)$";
    }

    public static String regexOnesOnOddPositions() {
//        return "^([01]1)*[01]?$"; - если нумерация с нуля
        return "^(1[01])*1?$";
    }

    public static String regexMoreThan1ZerosLessThan2Ones() {
        return "^(?:0{2,}|0{2,}10*|010{1,}|10{2,})$";
    }

    public static String regexNoSequentialOnes() {
        return "^1?(?:0+1?)*$";
    }
}
