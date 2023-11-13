package edu.hw3.task4;

public class Task4 {
    private final static int MAX_TO_ROMAIN = 3999;
    private final static int MIN_TO_ROMAIN = 1;
    private final static char[] ROMAIN_LETTERS = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
    private final static int DELTA = 2;
    private final static int DELTA_HALF = 1;
    private final static int ONES_BOUNDARY = 3;
    private final static int HALF_DIGIT = 5;
    private final static int BASE = 10;
    private final static int MAX_RANK = 3;
    private final static int MAX_MOD = 1000;


    private Task4() {
    }

    public static String convertToRoman(int input) throws InvalidNumberToConvert {
        int arabic = input;
        if (arabic < MIN_TO_ROMAIN || arabic > MAX_TO_ROMAIN) {
            throw new InvalidNumberToConvert(arabic);
        }
        // Таблица перевода цифры арабской k-го разряда:
        // 1, 2, 3 - разрядные единицы
        // 4 - разрядная единица, разрядная пятерка
        // 5 - разрядная пятерка
        // 6, 7, 8 - разрядная пятерка, разрядные единицы
        // 9 - разрядная единица, единица следущего разряда

        // при обработке k-го разряда, индекс letterIndex стоит на единице для k-го разряда
        // letterIndex+deltaHalf - 5-ка k-го разряда
        // letterIndex+delta - единица k+1-го разрда
        int mod = MAX_MOD;
        int letterIndex = ROMAIN_LETTERS.length - 1;
        StringBuilder numberBuilder = new StringBuilder();
        for (int i = MAX_RANK; i >= 0; --i) {
            int digit = arabic / mod;
            if (digit % HALF_DIGIT <= ONES_BOUNDARY) {
                if (digit >= HALF_DIGIT) {
                    numberBuilder.append(ROMAIN_LETTERS[letterIndex + DELTA_HALF]);
                }
                numberBuilder.append(String.valueOf(ROMAIN_LETTERS[letterIndex]).repeat(digit % HALF_DIGIT));
            } else {
                numberBuilder.append(ROMAIN_LETTERS[letterIndex]);
                numberBuilder.append(ROMAIN_LETTERS[letterIndex + (digit < HALF_DIGIT ? DELTA_HALF : DELTA)]);
            }
            letterIndex -= DELTA;
            arabic = arabic % mod;
            mod /= BASE;
        }
        return new String(numberBuilder);
    }
}
