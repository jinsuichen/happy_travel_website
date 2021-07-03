package xyz.chenjinsui.travel.util;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.assertEquals;

public class TestJedisUtils {
    @Test
    public void test(){
        Jedis jedis = JedisUtils.getJedis();
        jedis.set("Hello", "world");
        assertEquals("world", jedis.get("Hello"));
    }
}
