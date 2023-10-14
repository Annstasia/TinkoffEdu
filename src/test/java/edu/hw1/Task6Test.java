package edu.hw1;

import org.junit.jupiter.api.Test;

class Task6Test {
    @Test
    void countK() {
        assert Task6.countK(3524) == 3;
        assert Task6.countK(6621) == 5;
        assert Task6.countK(6554) == 4;
        assert Task6.countK(1234) == 3;
    }

    @Test
    void countZeroK() {
        assert Task6.countK(6174) == 0;
    }

}
