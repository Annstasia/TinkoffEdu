package edu.hw10.task1;

import edu.hw10.task1.objectGenerator.RandomObjectGenerator;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    private Main() {
    }

    private final static Logger LOGGER = LogManager.getLogManager().getLogger(Main.class.getName());

    public static void main(String[] args)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        LOGGER.info(RandomObjectGenerator.nextObject(Pojo.class).toString());
        LOGGER.info(RandomObjectGenerator.nextObject(SimpleRecord.class).toString());
        LOGGER.info(RandomObjectGenerator.nextObject(Pojo.class, "create").toString());
    }
}
