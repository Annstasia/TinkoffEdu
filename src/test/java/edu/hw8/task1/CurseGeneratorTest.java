package edu.hw8.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class CurseGeneratorTest {
    @Test
    void testCurse() throws IOException {
        CurseGenerator generator = new MVPFileCurseGenerator();
        Assertions.assertEquals("А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто " +
            "бог идиотизма.",
            generator.curse("глупый"));
    }
}
