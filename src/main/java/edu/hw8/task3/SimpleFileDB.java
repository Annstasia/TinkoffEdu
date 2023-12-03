package edu.hw8.task3;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleFileDB implements DB {
    Map<String, List<String>> personByHash = new HashMap<>();
    Map<String, String> hashByPerson;

    public SimpleFileDB() throws IOException {
        this(Path.of("src/main/resources/hw8/task3/password_db"));
    }

    public SimpleFileDB(Path dbPath) throws IOException {
        hashByPerson = FileParser.parse(dbPath);
        for (Map.Entry<String, String> kv : hashByPerson.entrySet()) {
            personByHash
                .computeIfAbsent(kv.getValue(), (hash) ->
                    new ArrayList<>()).add(kv.getKey());
        }
    }

    @Override
    public Collection<String> getByHash(String hash) {
        return new ArrayList<>(personByHash.getOrDefault(hash, List.of()));
    }

    @Override
    public int count() {
        return hashByPerson.size();
    }
}
