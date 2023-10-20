package edu.hw2.task3;

import java.util.concurrent.ThreadLocalRandom;

public class DefaultConnectionManager implements ConnectionManager {
    private final static int PROBABILITY_CONFIGURATOR = 100;

    @Override
    public Connection getConnection() {
        if (ThreadLocalRandom.current().nextInt() % PROBABILITY_CONFIGURATOR == 0) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
