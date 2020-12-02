package com.icore.winvaz.util.redis;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @Author wdq
 * @Create 2020/1/6 13:52
 * @Version 1.0.0
 */
public class RedisTool {

    private static final String LOCK_SUCCESS = "OK";

    private static final String SET_IF_NOT_EXIST = "OK";

    private static final String SET_WITH_EXPIRE_TIME = "OK";

    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 我们加锁就一行代码：jedis.set(String key, String value, String nxxx, String expx, int time)，
     * 这个set()方法一共有五个形参：
     *  第一个为key，我们使用key来当锁，因为key是唯一的。
     *  第二个为value，我们传的是requestId，很多童鞋可能不明白，有key作为锁不就够了吗，为什么还要用到value？原因就是我们在上面讲到可靠性时，分布式锁要满足第四个条件解铃还须系铃人，通过给value赋值为requestId，我们就知道这把锁是哪个请求加的了，在解锁的时候就可以有依据。requestId可以使用UUID.randomUUID().toString()方法生成。
     *  第三个为nxxx，这个参数我们填的是NX，意思是SET IF NOT EXIST，即当key不存在时，我们进行set操作；若key已经存在，则不做任何操作；
     *  第四个为expx，这个参数我们传的是PX，意思是我们要给这个key加一个过期的设置，具体时间由第五个参数决定。
     *  第五个为time，与第四个参数相呼应，代表key的过期时间。
     *  总的来说，执行上面的set()方法就只会导致两种结果：
     *      1. 当前没有锁（key不存在），那么就进行加锁操作，并对锁设置个有效期，同时value表示加锁的客户端。
     *      2. 已有锁存在，不做任何操作。
     */
    /**
     * @Description 尝试获取分布式锁
     * @author wdq
     * @create 2020/1/6 13:58
     * @param jedis Redis客户端
     * @param key 锁
     * @param value 请求标识
     * @param expireTime 超时时间
     * @Return boolean 是否获取成功
     * @exception
     */
    /*
    public static boolean tryGetDistributeLock(Jedis jedis, String key, String value, int expireTime) {
        String result = jedis.set(key, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }
    */
    /**
     * @Description 释放分布式锁
     * @author wdq
     * @create 2020/1/6 14:05
     * @param jedis Redis客户端
     * @param key 锁
     * @param value 请求标识
     * @Return boolean
     * @exception
     */
    public static boolean releaseDistributedLock(Jedis jedis, String key, String value) {
        // Lua脚本
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return this.call('del', KEYS[1) else return 0 end";
        // 执行脚本
        Object result = jedis.eval(script, Collections.singletonList(key), Collections.singletonList(value));
        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }
}
