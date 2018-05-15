package demo.localcache;

import java.io.Serializable;

public class CacheElement implements Serializable {
    private long createTime;
    private transient Object value;
    //为了以后实施新的策略进行数据支持
    private Integer useTimes;
    private long lastAccessTime;

    public CacheElement() {
        this.createTime = System.currentTimeMillis();
        useTimes = 1;
        lastAccessTime = System.currentTimeMillis();
    }

    public CacheElement(Object v) {
        value = v;
        createTime = System.currentTimeMillis();
        useTimes = 0;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final CacheElement that = (CacheElement) o;

        if (createTime != that.createTime) return false;
        if (lastAccessTime != that.lastAccessTime) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        return useTimes != null ? useTimes.equals(that.useTimes) : that.useTimes == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (createTime ^ (createTime >>> 32));
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (useTimes != null ? useTimes.hashCode() : 0);
        result = 31 * result + (int) (lastAccessTime ^ (lastAccessTime >>> 32));
        return result;
    }
}
