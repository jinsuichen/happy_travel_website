package xyz.chenjinsui.travel.util;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author FengLing
 */
public class JedisUtils {

    private static JedisPool jedisPool;

    static{
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        //TODO 提取配置文件
        jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

    public static void close(){
        //这里使用的close不代表关闭连接，指的是归还资源
        jedisPool.close();
    }

}
