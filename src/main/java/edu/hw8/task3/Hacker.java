package edu.hw8.task3;

import java.io.IOException;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Hacker {
    private static final String ALGORITHM = "MD5";
    private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();
    private final static String DEFAULT_PATH = "src/main/resources/hw8/task3/password_db";

    private final static int THREAD_COUNT = 4;

    private final static int NEGATIVE_TO_POSITIVE_MASK = 0xFF;
    private final static int SECOND_BYTE_MASK = 0x0F;
    private final static int FIRST_BYTE_OFFSET = 4;

    private Hacker() {
    }

    public static Map<String, String> multiThreadHack() throws IOException {
        return multiThreadHack(Path.of(DEFAULT_PATH));
    }

    public static Map<String, String> multiThreadHack(Path path) throws IOException {
        DB db = new SimpleFileDB(path);
        GeneratorManager generator = new GeneratorManager();
        ConcurrentMap<String, String> personPasswordMap = new ConcurrentHashMap<>();
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; ++i) {
            pool.execute(() -> {
                MessageDigest md = null;
                try {
                    md = MessageDigest.getInstance(ALGORITHM);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                while (personPasswordMap.size() < db.count()) {
                    PasswordGenerator localGenerator = generator.getGenerator();
                    String password;
                    while (((password = localGenerator.nextPassword()) != null)
                        && personPasswordMap.size() < db.count()) {
                        byte[] hashBytes = md.digest(password.getBytes());
                        String hash = bytesToHex(hashBytes);
                        Collection<String> personsWithSuchPassword = db.getByHash(hash);
                        for (String name : personsWithSuchPassword) {
                            personPasswordMap.put(name, password);
                        }
                    }
                }
            });
        }
        pool.close();
        return personPasswordMap;
    }

    public static Map<String, String> hack() throws IOException {
        return hack(Path.of(DEFAULT_PATH));
    }

    // bytesToHex - копипаст со StackOverFlow, но с осознанием в каком формате
    // ответ digest и как конвертировать
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int hexByte = bytes[j] & NEGATIVE_TO_POSITIVE_MASK;
            hexChars[j * 2] = HEX_ARRAY[hexByte >>> FIRST_BYTE_OFFSET];
            hexChars[j * 2 + 1] = HEX_ARRAY[hexByte & SECOND_BYTE_MASK];
        }
        return new String(hexChars);
    }

    public static Map<String, String> hack(Path path) throws IOException {

        MessageDigest md;
        try {
            md = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        DB db = new SimpleFileDB(path);
        int foundCounter = 0;
        UnboundedGenerator generator = new UnboundedGenerator();
        Map<String, String> personPasswordMap = new HashMap<>();
        while (foundCounter < db.count()) {
            String password = generator.nextPassword();
            byte[] hashBytes = md.digest(password.getBytes());
            String hash = bytesToHex(hashBytes);
            Collection<String> personsWithSuchPassword = db.getByHash(hash);
            foundCounter += personsWithSuchPassword.size();
            for (String name : personsWithSuchPassword) {
                personPasswordMap.put(name, password);
            }
        }
        return personPasswordMap;
    }
}
