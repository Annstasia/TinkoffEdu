package edu.hw3;

import edu.hw3.task4.InvalidNumberToConvert;
import edu.hw3.task4.Task4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task4Test {
    @Test
    void testSimple() throws InvalidNumberToConvert {
        Assertions.assertEquals(Task4.convertToRoman(1), "I");
        Assertions.assertEquals(Task4.convertToRoman(5), "V");
        Assertions.assertEquals(Task4.convertToRoman(10), "X");
        Assertions.assertEquals(Task4.convertToRoman(50), "L");
        Assertions.assertEquals(Task4.convertToRoman(100), "C");
        Assertions.assertEquals(Task4.convertToRoman(500), "D");
        Assertions.assertEquals(Task4.convertToRoman(1000), "M");
    }

    @Test
    void testIncrement() throws InvalidNumberToConvert {
        Assertions.assertEquals(Task4.convertToRoman(6), "VI");
        Assertions.assertEquals(Task4.convertToRoman(12), "XII");
        Assertions.assertEquals(Task4.convertToRoman(80), "LXXX");
        Assertions.assertEquals(Task4.convertToRoman(150), "CL");
        Assertions.assertEquals(Task4.convertToRoman(600), "DC");
        Assertions.assertEquals(Task4.convertToRoman(1300), "MCCC");
    }

    @Test
    void testDecrement() throws InvalidNumberToConvert {
        Assertions.assertEquals(Task4.convertToRoman(4), "IV");
        Assertions.assertEquals(Task4.convertToRoman(9), "IX");
        Assertions.assertEquals(Task4.convertToRoman(40), "XL");
        Assertions.assertEquals(Task4.convertToRoman(90), "XC");
        Assertions.assertEquals(Task4.convertToRoman(400), "CD");
        Assertions.assertEquals(Task4.convertToRoman(900), "CM");
    }

    @Test
    void complexTest() throws InvalidNumberToConvert {
        Assertions.assertEquals(Task4.convertToRoman(3459), "MMMCDLIX");
    }

    @Test
    void invalidTest() {
        Assertions.assertThrows(InvalidNumberToConvert.class, ()->Task4.convertToRoman(4000));
        Assertions.assertThrows(InvalidNumberToConvert.class, ()->Task4.convertToRoman(-1));

    }


}
