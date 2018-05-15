package demo;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class LocalCacheManager {
    private static ConcurrentHashMap<String, Cache> cacheManagerMap = new ConcurrentHashMap<String, Cache>();
    private static final long DURATION = 3;
    private static final int MAX_SIZE = 256;

    private LocalCacheManager() {
    }

    private static Cache createCache() {
        return CacheBuilder.newBuilder()
                .expireAfterAccess(DURATION, TimeUnit.DAYS)
                .maximumSize(MAX_SIZE)
                .build();
    }

    public static Cache getCache(String cacheName) {
        try {
            if (!cacheManagerMap.containsKey(cacheName)) {
                Cache cache = createCache();
                cacheManagerMap.put(cacheName, cache);
            }
            return cacheManagerMap.get(cacheName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
