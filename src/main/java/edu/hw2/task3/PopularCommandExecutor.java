package edu.hw2.task3;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;
    private final static int DEFAULT_ATTEMPTS = 100;

    public PopularCommandExecutor() {
        this(new DefaultConnectionManager());
    }

    public PopularCommandExecutor(ConnectionManager manager) {
        this(manager, DEFAULT_ATTEMPTS);
    }

    public PopularCommandExecutor(int maxAttempts) {
        this(new DefaultConnectionManager(), maxAttempts);
    }

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() throws ConnectionException {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) {
        int attemps = 0;
        String exceedErrorString = "number of attempts exceeded max number (" + maxAttempts + ")";
        if (maxAttempts < 1) {
            throw new ConnectionException(exceedErrorString);
        }
        try (Connection connection = manager.getConnection()) {
            while (attemps++ < maxAttempts) {
                try {
                    connection.execute(command);
                    break;
                } catch (ConnectionException e) {
                    if (attemps >= maxAttempts) {
                        throw new ConnectionException(exceedErrorString, e);
                    }
                }
            }
        } catch (ConnectionException e) {
            throw e;
        } catch (Exception ignore) {
            // Верим, что ошибок с закрытием соединения пока нет.
        }

    }
}
