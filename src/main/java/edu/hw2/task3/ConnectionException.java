package edu.hw2.task3;

public class ConnectionException extends RuntimeException {
    ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

}
