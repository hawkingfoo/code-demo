package demo.localcache;

public class CacheConfiguration {
    private boolean eternal;
    private long timeToLive;
    private TimeUnit timeUnits;
    private int maxEntryNum;

    public CacheConfiguration() {
        eternal = true;
        timeToLive = 3;
        timeUnits = TimeUnit.SECONDS;
        maxEntryNum = 2000;
    }

    public void setEternal(boolean eternal) {
        this.eternal = eternal;
    }

    public void setTimeToLive(long timeToLive) {
        this.timeToLive = timeToLive;
    }

    public void setTimeUnits(TimeUnit timeUnits) {
        this.timeUnits = timeUnits;
    }

    public void setMaxEntryNum(int maxEntryNum) {
        this.maxEntryNum = maxEntryNum;
    }

    public static class CacheConfigurationBuilder {
        private CacheConfiguration cacheConfiguration;

        public CacheConfigurationBuilder() {
            cacheConfiguration = new CacheConfiguration();
        }

        public CacheConfigurationBuilder eternal(boolean eternal) {
            cacheConfiguration.setEternal(eternal);
            return this;
        }

        public CacheConfigurationBuilder timeTolive(long timeTolive) {
            cacheConfiguration.setTimeToLive(timeTolive);
            return this;
        }

        public CacheConfigurationBuilder timeUnit(TimeUnit timeUnit) {
            cacheConfiguration.setTimeUnits(timeUnit);
            return this;
        }

        public CacheConfiguration builder() {
            return cacheConfiguration;
        }

    }

    public long getExpiryTime() {
        switch (timeUnits) {
            case DAYS:
                return timeToLive * 24 * 60 * 60 * 1000;
            case HOURS:
                return timeToLive * 60 * 60 * 1000;
            case MINITES:
                return timeToLive * 60 * 1000;
            case SECONDS:
                return timeToLive * 1000;
        }
        return 0;
    }

    public boolean isEternal() {
        return eternal;
    }

    public long getTimeToLive() {
        return timeToLive;
    }

    public TimeUnit getTimeUnits() {
        return timeUnits;
    }

    public int getMaxEntryNum() {
        return maxEntryNum;
    }
}
