package demo.localcache;

import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LocalCache<K, V> implements LocalCacheInterface<K, V> {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();
    private final TreeMap<K, CacheElement> map = new TreeMap<K, CacheElement>();
    private String name;
    private CacheConfiguration cacheConfiguration;

    public LocalCache(String name) {
        this.name = name;
        cacheConfiguration = new CacheConfiguration();
    }

    public LocalCache(String name, CacheConfiguration cacheConfiguration) {
        this.name = name;
        this.cacheConfiguration = cacheConfiguration;
    }


    public String getName() {
        return name;
    }

    public V get(K key) {
        writeLock.lock();
        try {
            CacheElement value = map.get(key);
            if (value == null) {
                return null;
            }
            if (!isExpiry(value)) {
                map.remove(key);
                return null;
            }

            CacheElement element = map.get(key);
            return (V) element.getValue();
        } finally {
            writeLock.unlock();
        }
    }


    public boolean contains(K key) {
        readLock.lock();
        try {
            return map.containsKey(key);
        } finally {
            readLock.unlock();
        }
    }


    public void put(K key, V value) {
        writeLock.lock();
        CacheElement cacheElement;
        try {
            if (map.containsKey(key)) {
                cacheElement = map.get(key);
                cacheElement.setValue(value);
            } else {
                cacheElement = new CacheElement(value);
            }
            cacheElement.setCreateTime(System.currentTimeMillis());
            map.put(key, cacheElement);
        } finally {
            writeLock.unlock();
        }
    }


    public boolean isEmpty() {
        return map.isEmpty();
    }


    public int size() {
        return map.size();
    }


    public void clear() {
        writeLock.lock();
        try {
            map.clear();
        } finally {
            writeLock.unlock();
        }
    }


    public void remove(K key) {
        writeLock.lock();
        try {
            CacheElement cacheElement = map.get(key);
            if (cacheElement == null) {
                return;
            }
            map.remove(key);
        } finally {
            writeLock.unlock();
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public CacheConfiguration getCacheConfiguration() {
        return cacheConfiguration;
    }

    public void setCacheConfiguration(CacheConfiguration cacheConfiguration) {
        this.cacheConfiguration = cacheConfiguration;
    }

    public boolean isExpiry(CacheElement cacheElement) {
        long expiryTime;
        if (cacheConfiguration.isEternal()) {
            expiryTime = Long.MAX_VALUE;
        } else {
            expiryTime = cacheConfiguration.getExpiryTime();
        }
        return (System.currentTimeMillis() - cacheElement.getCreateTime() < expiryTime);
    }
}
