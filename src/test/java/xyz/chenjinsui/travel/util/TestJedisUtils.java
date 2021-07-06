package xyz.chenjinsui.travel.util;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.assertEquals;

public class TestJedisUtils {
    @Test
    public void testGetJedis(){
        Jedis jedis = JedisUtils.getJedis();
        jedis.set("HelloTest", "world");
        assertEquals("world", jedis.get("HelloTest"));
        jedis.del("HelloTests");
        jedis.close();
    }
}
