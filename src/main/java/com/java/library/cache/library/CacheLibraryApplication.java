package com.java.library.cache.library;

import com.java.library.cache.library.manager.CacheManager;
import com.java.library.cache.library.service.Cache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CacheLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheLibraryApplication.class, args);

		CacheManager<String, String> lru = new CacheManager<>("LRU", 5, 0);
		Cache<String, String> lruCache = lru.getCache();

		lruCache.put("key1", "samar in LRU");
		System.out.println(lruCache.get("key1"));

		CacheManager<String, String> lfu = new CacheManager<>("LFU", 5, 0);
		Cache<String, String> lfuCache = lfu.getCache();

		lfuCache.put("key1", "samar in LFU");
		System.out.println(lfuCache.get("key1"));

		CacheManager<String, String> ttl = new CacheManager<>("TTL", 5, 50000);
		Cache<String, String> ttlCache = ttl.getCache();

		ttlCache.put("key1", "samar in TTL");
		System.out.println(ttlCache.get("key1"));



	}
}
