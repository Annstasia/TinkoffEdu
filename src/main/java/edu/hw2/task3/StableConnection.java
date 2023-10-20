package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {
    private static final Logger LOGGER = LogManager.getLogger(StableConnection.class.getName());

    @Override
    public void execute(String command) {
        Server.execute(command);
    }

    @Override
    public void close() {
        LOGGER.info("close");
    }
}
