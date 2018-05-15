package demo;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Field;

public class RedisTest {
    @Rule
    public ExpectedException exception = ExpectedException.none(); // 不允许有异常

    @Test
    public void mockTest() throws Exception {
        Jedis mockJedis = Mockito.mock(Jedis.class);
        Mockito.when(mockJedis.get("name")).thenReturn("admin");

        RedisDemo redisDemo = new RedisDemo();
        Field daoField = redisDemo.getClass().getDeclaredField("jedis");
        daoField.setAccessible(true);
        daoField.set(redisDemo, mockJedis);

        Assert.assertSame(true, redisDemo.isAdmin("admin"));
    }
}
