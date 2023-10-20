package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
    private Server() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static void execute(String command) {
        LOGGER.info("execute " + command);
    }
}
