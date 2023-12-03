package edu.hw8.task3;

import java.util.Collection;

public interface DB {
    Collection<String> getByHash(String hash);

    int count();
}
