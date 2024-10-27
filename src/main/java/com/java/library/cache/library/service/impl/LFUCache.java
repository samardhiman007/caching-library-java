package com.java.library.cache.library.service.impl;

import com.java.library.cache.library.domain.CacheItem;
import com.java.library.cache.library.service.Cache;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LFUCache<K,V> implements Cache<K,V> {
    private final int capacity;
    private final Map<K, CacheItem<V>> cache;
    private final PriorityQueue<K> frequencyQueue;


    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.frequencyQueue = new PriorityQueue<>((k1, k2) -> cache.get(k1).getUsageCount() - cache.get(k2).getUsageCount());
    }
    @Override
    public void put(K key, V value) {
        if (cache.size() >= capacity) {
            K leastUsedKey = frequencyQueue.poll();
            cache.remove(leastUsedKey);
        }
        CacheItem<V> item = new CacheItem<>(value, Long.MAX_VALUE);
        cache.put(key, item);
        frequencyQueue.offer(key);
    }

    @Override
    public V get(K key) {
        CacheItem<V> item = cache.get(key);
        if (item != null) {
            item.incrementUsageCount();
            frequencyQueue.remove(key);
            frequencyQueue.offer(key);
            return item.getValue();
        }
        return null;    }

    @Override
    public void remove(K key) {
        cache.remove(key);
        frequencyQueue.remove(key);

    }

    @Override
    public void clear() {
        cache.clear();
        frequencyQueue.clear();
    }
}
