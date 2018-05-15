package demo.localcache;

public interface LocalCacheInterface<K, V> {
    String getName();

    V get(K key);

    boolean contains(K key);

    void put(K key, V value);

    boolean isEmpty();

    int size();

    void clear();

    void remove(K key);
}
