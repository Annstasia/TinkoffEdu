package edu.hw10;

import edu.hw10.task1.Pojo;
import edu.hw10.task1.SimpleRecord;
import edu.hw10.task1.objectGenerator.RandomObjectGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;

public class TestTask1 {
    @Test
    void testConstructor()
        throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Pojo pojoByConstructor = RandomObjectGenerator.nextObject(Pojo.class);
        Assertions.assertNull(pojoByConstructor.getInteger());
        Assertions.assertTrue(pojoByConstructor.getIntInRange() < 10 && pojoByConstructor.getIntInRange() >= -10);
        SimpleRecord record = RandomObjectGenerator.nextObject(SimpleRecord.class);
        Assertions.assertNull(record.someInteger());
        Assertions.assertTrue(record.bigInteger() >= 10);
        Assertions.assertTrue(record.someChar() <= 'z' && record.someChar() >= 'a');
    }

    @Test
    void testCreatorMethod() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Pojo pojoByMethod = RandomObjectGenerator.nextObject(Pojo.class, "create");
        Assertions.assertNull(pojoByMethod.getInteger());
        Assertions.assertTrue(pojoByMethod.getIntInRange() < 10 && pojoByMethod.getIntInRange() >= -10);
    }
}
