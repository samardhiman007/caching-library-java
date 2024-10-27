package com.java.library.cache.library;

import com.java.library.cache.library.manager.CacheManager;
import com.java.library.cache.library.service.Cache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CacheLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheLibraryApplication.class, args);

		CacheManager<String, String> cacheManager = new CacheManager<>("LRU", 5, 0);
		Cache<String, String> cache = cacheManager.getCache();

		cache.put("key1", "samar");
		System.out.println(cache.get("key1"));
	}
}
