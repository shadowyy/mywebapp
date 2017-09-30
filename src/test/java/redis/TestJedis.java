package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author shadowyy
 * @version 2017/9/25 11:09
 */
public class TestJedis {
    private static String host = "192.168.11.3";

    public static void main(String[] args) throws Exception {
        test0();
        test1();
    }

    private static void test0() throws Exception {
        Jedis jedis = new Jedis(host);
        System.out.println(jedis.get("name"));
        jedis.close();
    }

    private static void test1() throws Exception {

        JedisPool pool = new JedisPool(new JedisPoolConfig(), host);
        Jedis jedis = pool.getResource();
        System.out.println(jedis.get("name"));

        jedis.set("java", "http://java.androidwhy.com");
        if(jedis.exists("java")){
            System.out.println(jedis.get("java"));
        }


        jedis.close();
        pool.close();
    }
}
