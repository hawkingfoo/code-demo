package demo;

import redis.clients.jedis.Jedis;

public class RedisDemo {
    private Jedis jedis;

    public void setUp(String host, int port) {
        jedis = new Jedis(host, port);
        jedis.connect();
    }

    public boolean isAdmin(String user) {
        String ret = jedis.get("name");
        if (ret.equals(user)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        RedisDemo demo = new RedisDemo();
        demo.setUp("127.0.0.1", 6379);
        boolean isAdmin = demo.isAdmin("test");
        System.out.println(isAdmin);
    }
}
