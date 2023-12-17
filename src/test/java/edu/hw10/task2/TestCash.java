package edu.hw10.task2;

public interface TestCash {
    int getInitInfo();
    @Cache
    CacheValidator cacheNoPersist();

    @Cache
    CacheValidator cacheNoPersist(int ignore);
    @Cache(persist = true)
    CacheValidator cachePersist();
}
