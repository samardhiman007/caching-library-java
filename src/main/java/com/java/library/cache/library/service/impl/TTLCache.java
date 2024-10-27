package com.java.library.cache.library.service.impl;

import com.java.library.cache.library.domain.CacheItem;
import com.java.library.cache.library.service.Cache;

import java.util.HashMap;

import java.util.Iterator;
import java.util.Map;

public class TTLCache<K, V> implements Cache<K, V> {
    private final long ttl;
    private final Map<K, CacheItem<V>> cache;

    public TTLCache(long ttl) {
        this.ttl = ttl;
        this.cache = new HashMap<>();
    }

    @Override
    public void put(K key, V value) {
        CacheItem<V> item = new CacheItem<>(value, System.currentTimeMillis() + ttl);
        cache.put(key, item);
    }

    @Override
    public V get(K key) {
        CacheItem<V> item = cache.get(key);
        if (item == null || item.isExpired()) {
            cache.remove(key);
            return null;
        }
        return item.getValue();
    }

    @Override
    public void remove(K key) {
        cache.remove(key);

    }

    @Override
    public void clear() {
        cache.clear();
    }

    private void evictExpiredItems() {
        Iterator<Map.Entry<K, CacheItem<V>>> iterator = cache.entrySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getValue().isExpired()) {
                iterator.remove();
            }
        }
    }
}
