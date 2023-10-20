package edu.hw2.task3;

import java.util.concurrent.ThreadLocalRandom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    final static Logger LOGGER = LogManager.getLogger(FaultyConnection.class.getName());

    @Override public void execute(String command) throws ConnectionException {
        final int PROBABILITY_CONFIGURATOR = 100;
        if (ThreadLocalRandom.current().nextInt() % PROBABILITY_CONFIGURATOR != 0) {
            Server.execute(command);
        } else {
            throw new ConnectionException("unable to execute command \"" + command + "\"");
        }
    }

    @Override public void close() {
        LOGGER.info("close");
    }
}
