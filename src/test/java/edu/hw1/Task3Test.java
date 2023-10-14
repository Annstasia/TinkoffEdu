package edu.hw1;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import java.util.ArrayDeque;
import static org.junit.jupiter.api.Assertions.*;

class Task3Test {
    @Test
    void isNestableBase() {
        assert Task3.isNestable(new int[]{1, 2, 3, 4}, new int[]{0, 6});
        assert Task3.isNestable(new int[]{3, 1}, new int[]{4, 0});
    }

    @Test
    void isNotNestableBase() {
        assert !Task3.isNestable(new int[]{1, 2, 3, 4}, new int[]{1, 6});
        assert !Task3.isNestable(new int[]{1, 2, 3, 4}, new int[]{0, 4});
        assert !Task3.isNestable(new int[]{1, 2, 3, 4}, new int[]{2, 3});
    }

    @Test
    void isNestableEmpty() {
        assert Task3.isNestable(new int[]{}, new int[]{1, 2, 3});
        assert Task3.isNestable(new int[]{}, new int[]{});
    }

    @Test
    void isNotNestableEmpty() {
        assert !Task3.isNestable(new int[]{1, 2, 3}, new int[]{});
    }





}
