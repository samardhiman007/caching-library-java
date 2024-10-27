package com.java.library.cache.library.domain;

import lombok.Data;

@Data
public class CacheItem<V> {
    private V value;
    private Long expirationTime;
    private int usageCount;

    public CacheItem(V value, long expirationTime) {
        this.value = value;
        this.expirationTime = expirationTime;
        this.usageCount = 0;
    }

    public void incrementUsageCount() {
        usageCount++;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expirationTime;
    }
}
