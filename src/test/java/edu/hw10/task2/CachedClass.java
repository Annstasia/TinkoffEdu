package edu.hw10.task2;

public class CachedClass implements TestCash {
    private final int initInfo;
    public CachedClass(int init) {
        initInfo = init;
    }

    @Override
    public int getInitInfo() {
        return initInfo;
    }
    @Override
    @Cache
    public CacheValidator cacheNoPersist() {
        return new CacheValidator();
    }

    @Override
    @Cache
    public CacheValidator cacheNoPersist(int ignore) {
        return new CacheValidator();
    }

    @Override
    @Cache(persist = true)
    public CacheValidator cachePersist() {
        return new CacheValidator();
    }
}
