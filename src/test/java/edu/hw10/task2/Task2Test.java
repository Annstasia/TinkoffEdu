package edu.hw10.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class Task2Test {
    @Test
    void testNoPersist() throws IOException {
        int init = 42;
        CacheValidator.setCounter(0);
        TestCash testCash = CacheProxy.cache(new CachedClass(init));
        Assertions.assertEquals(init, testCash.getInitInfo());
        Assertions.assertEquals(1, testCash.cacheNoPersist().getCounter());
        Assertions.assertEquals(1, testCash.cacheNoPersist().getCounter());
        Assertions.assertEquals(2, testCash.cacheNoPersist(42).getCounter());
        Assertions.assertEquals(2, testCash.cacheNoPersist(42).getCounter());
        Assertions.assertEquals(2, testCash.cacheNoPersist().getCounter());
        TestCash testCash2 = CacheProxy.cache(new CachedClass(init));
        Assertions.assertEquals(3, testCash2.cacheNoPersist().getCounter());
    }

    @Test
    void testPersist() throws IOException {
        int init = 42;
        CacheValidator.setCounter(0);
        TestCash testCash = CacheProxy.cache(new CachedClass(init));
        Assertions.assertEquals(init, testCash.getInitInfo());
        Assertions.assertEquals(1, testCash.cachePersist().getCounter());
        Assertions.assertEquals(1, testCash.cachePersist().getCounter());
        Assertions.assertEquals(2, testCash.cacheNoPersist().getCounter());
        Assertions.assertEquals(2, testCash.cacheNoPersist().getCounter());
        TestCash testCash2 = CacheProxy.cache(new CachedClass(init));
        Assertions.assertEquals(3, testCash2.cachePersist().getCounter());
    }
}
