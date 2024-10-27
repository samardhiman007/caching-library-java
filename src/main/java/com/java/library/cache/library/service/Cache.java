package com.java.library.cache.library.service;



public interface Cache<K,V> {
    void put(K key, V value);
    V get(K key);
    void remove(K key);
    void clear();
}