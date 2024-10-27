package com.java.library.cache.library.service.impl;

import com.java.library.cache.library.domain.CacheItem;
import com.java.library.cache.library.service.Cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> implements Cache<K, V> {

    private final int capacity;
    private final Map<K, CacheItem<V>> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<K, CacheItem<V>> eldest) {
                return size() > capacity;
            }
        };
    }

    @Override
    public void put(K key, V value) {
        cache.put(key, new CacheItem<>(value, Long.MAX_VALUE));
    }

    @Override
    public V get(K key) {
        CacheItem<V> item = cache.get(key);
        return item != null ? item.getValue() : null;
    }

    @Override
    public void remove(K key) {
        cache.remove(key);

    }

    @Override
    public void clear() {
        cache.clear();
    }
}
