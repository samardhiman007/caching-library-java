package com.java.library.cache.library.manager;

import com.java.library.cache.library.service.Cache;
import com.java.library.cache.library.service.impl.LFUCache;
import com.java.library.cache.library.service.impl.LRUCache;
import com.java.library.cache.library.service.impl.TTLCache;

public class CacheManager <K,V>{
    private final Cache<K, V> cache;

    public CacheManager(String policy, int capacity, long ttl) {
        switch (policy) {
            case "LRU":
                cache = new LRUCache<>(capacity);
                break;
            case "LFU":
                cache = new LFUCache<>(capacity);
                break;
            case "TTL":
                cache = new TTLCache<>(ttl);
                break;
            default:
                throw new IllegalArgumentException("Invalid cache policy");
        }
    }


    public Cache<K, V> getCache() {
        return cache;
    }
}
