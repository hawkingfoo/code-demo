package demo.localcache;

import java.util.concurrent.ConcurrentHashMap;

public class LocalCacheManager {
    private static final ConcurrentHashMap<String, LocalCache> manager = new ConcurrentHashMap<String, LocalCache>();

    private LocalCacheManager() {
        // nothing
    }

    public static synchronized LocalCache createLocalCache(String name) {
        if (manager.containsKey(name)) {
            return manager.get(name);
        }
        LocalCache localCache = new LocalCache(name);
        manager.put(name, localCache);
        return localCache;
    }

    public static LocalCache getLocalCache(String cacheName) {
        return manager.get(cacheName);
    }

    public static boolean contains(String cacheName) {
        return manager.contains(cacheName);
    }

    public static LocalCache removeCache(String cacheName) {
        return manager.remove(cacheName);
    }

    public static int size() {
        return manager.size();
    }

}
