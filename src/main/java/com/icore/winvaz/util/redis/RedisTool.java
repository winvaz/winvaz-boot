package com.icore.winvaz.util.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.commands.JedisCommands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @Author wdq
 * @Create 2020/1/6 13:52
 * @Version 1.0.0
 */
@Slf4j
public class RedisTool {

    @Autowired
    RedisTemplate redisTemplate;

    private ThreadLocal<String> lockFlag = new ThreadLocal<String>();

    /**
     * 用来表示setnx的参数
     */
    private static final String SET_IF_NOT_EXIST = "NX";

    /**
     * EX = seconds（秒）; PX = milliseconds（毫秒）
     */
    private static final String SET_WITH_EXPIRE_TIME = "EX";

    /**
     * 释放锁成功返回值
     */
    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 加锁成功返回值
     */
    private static final String LOCK_SUCCESS = "OK";

    /**
     * 超时时间 10s，单位是由 {@code SET_WITH_EXPIRE_TIME }
     */
    public static final int TIMEOUT_SECONDS= 10;

    /**
     * 常量前缀
     */
    private static final String REDIS_LOCK_KEY_PREFIX = "redis_lock_key_prefix";

    /**
     * 常量连接符
     */
    private static final String REDIS_LOCK_PLUS = "@";

    /**
     * 可用 key前缀
     */
    public static final String REDIS_LOCK_KEY = REDIS_LOCK_KEY_PREFIX + REDIS_LOCK_PLUS;

    public static final long TIMEOUT_MILLIS = 30000;

    public static final int RETRY_TIMES = Integer.MAX_VALUE;

    public static final long SLEEP_MILLIS = 500;

    private static final String LOCK_LUA_SCRIPT;

    public static final String UNLOCK_LUA_SCRIPT;

    static {
        StringBuffer sb = new StringBuffer();
        sb.append("if redis.call('setNx', KEYS[1], ARGV[1]) then ");
        sb.append("if redis.call('get', KEYS[1]) == ARGV[1] then ");
        sb.append("redis.call('expire', KEYS[1], ARGV[2]) ");
        sb.append("  return 'OK' ");
        sb.append("else ");
        sb.append("  return nil end ");
        sb.append("end ");
        LOCK_LUA_SCRIPT = sb.toString();

        StringBuilder unsb = new StringBuilder();
        unsb.append("local cliVal = redis.call('get', KEYS[1) ");
        unsb.append("if cliVal == ARGV[1] then ");
        unsb.append("redis.call('del',KEYS[1]) ");
        unsb.append("    return 'OK' ");
        unsb.append("else ");
        unsb.append("    return nil ");
        unsb.append("end ");
        UNLOCK_LUA_SCRIPT = unsb.toString();
    }

    /**
     *
     * @author wdq
     * @create 2021/4/29 15:44
     * @param
     * @Return java.lang.String
     * @exception
     */
    public String generateRedisLockKey(String key) {
        if (StringUtils.isNoneBlank(key)) {
            // 设置客户端唯一标识
            String s = UUID.randomUUID().toString();

        }
        return null;
    }

    /**
     * Redis 分布式锁
     *
     * @param key         key
     * @param expire      过期时间
     * @param retryTimes  重试次数
     * @param sleepMillis 睡眠时间
     * @throws
     * @author wdq
     * @create 2021/4/30 15:00
     * @Return boolean
     */
    /**
     * LUA脚本上锁
     * @author wdq
     * @create 2021/5/11 14:30
     * @param key
     * @param value
     * @param expireTime
     * @Return boolean
     * @exception
     */
    public boolean lockLua(String key, String value, int expireTime) {
        RedisScript<String> redisScript = new DefaultRedisScript(LOCK_LUA_SCRIPT, String.class);
        Object result = redisTemplate.execute(redisScript, new StringRedisSerializer(), new StringRedisSerializer(),
                Arrays.asList(key)
                , value, String.valueOf(expireTime));

        return "OK".equals(result);
    }
    /**
     * LUA脚本释放锁
     * @author wdq
     * @create 2021/5/11 14:33
     * @param key
     * @param value
     * @Return boolean
     * @exception
     */
    public boolean unlockLua(String key, String value) {
        RedisScript<String> redisScript = new DefaultRedisScript(UNLOCK_LUA_SCRIPT, String.class);
        Object result = redisTemplate.execute(redisScript, Arrays.asList(key), value);

        return "OK".equals(result);
    }

    public boolean lock(String key, long expire, int retryTimes, long sleepMillis) {
        boolean result = setRedis(key, expire);
        // 如果获取锁失败，按照传入的重试次数进行重试
        while((!result) && retryTimes-- > 0){
            try {
                log.debug("lock failed, retrying..." + retryTimes);
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                return false;
            }
            result = setRedis(key, expire);
        }
        return result;
    }
    private boolean setRedis(String key, long expire) {
        try {
            /*
            String result = redisTemplate.execute((RedisCallback) connection -> {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                String value = UUID.randomUUID().toString();
                lockFlag.set(value);
                // return commands.set(key, value, "NX", "PX", expire);
                *//*return commands.set("set", key.getBytes(), value.getBytes(), "NX".getBytes(), "PX".getBytes(),
                        String.valueOf(expire).getBytes());*//*
            });
            return !StringUtils.isEmpty(result);
            */
            return true;
        } catch (Exception e) {
            log.error("set redis occured an exception", e);
        }
        return false;
    }

    public boolean releaseLock(String key) {
        // 释放锁的时候，有可能因为持锁之后方法执行时间大于锁的有效期，此时有可能已经被另外一个线程持有锁，所以不能直接删除
        try {
            List<String> keys = new ArrayList<String>();
            keys.add(key);
            List<String> args = new ArrayList<String>();
            args.add(lockFlag.get());

            // 使用lua脚本删除redis中匹配value的key，可以避免由于方法执行时间过长而redis锁自动过期失效的时候误删其他线程的锁
            // spring自带的执行脚本方法中，集群模式直接抛出不支持执行脚本的异常，所以只能拿到原redis的connection来执行脚本
            Long result = (Long) redisTemplate.execute((RedisCallback<Long>) connection -> {
                Object nativeConnection = connection.getNativeConnection();
                // 集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
                if (nativeConnection instanceof JedisCluster) { // 集群模式
                    return (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA_SCRIPT, keys, args);
                } else if (nativeConnection instanceof Jedis) { // 单机模式
                    return (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA_SCRIPT, keys, args);
                }
                return 0L;
            });
            return result != null && result > 0;
        } catch (Exception e) {
            log.error("release lock occured an exception", e);
        } finally {
            // 清除掉ThreadLocal中的数据，避免内存溢出
            lockFlag.remove();
        }
        return false;
    }

    /**
     * 我们加锁就一行代码：jedis.set(String key, String value, String nxxx, String expx, int time)，
     * 这个set()方法一共有五个形参：
     *  第一个为key，我们使用key来当锁，因为key是唯一的。
     *  第二个为value，我们传的是requestId，很多童鞋可能不明白，有key作为锁不就够了吗，为什么还要用到value？原因就是我们在上面讲到可靠性时，分布式锁要满足第四个条件解铃还须系铃人，通过给value
     *  赋值为requestId，我们就知道这把锁是哪个请求加的了，在解锁的时候就可以有依据。requestId可以使用UUID.randomUUID().toString()方法生成。
     *  第三个为nxxx，这个参数我们填的是NX，意思是SET IF NOT EXIST，即当key不存在时，我们进行set操作；若key已经存在，则不做任何操作；
     *  第四个为expx，这个参数我们传的是PX，意思是我们要给这个key加一个过期的设置，具体时间由第五个参数决定。
     *  第五个为time，与第四个参数相呼应，代表key的过期时间。
     *  总的来说，执行上面的set()方法就只会导致两种结果：
     *      1. 当前没有锁（key不存在），那么就进行加锁操作，并对锁设置个有效期，同时value表示加锁的客户端。
     *      2. 已有锁存在，不做任何操作。
     */



    /**
     * 获取分布式锁
     *
     * @param key        锁
     * @param value      请求唯一标识
     * @param expireTime 超时时间
     * @author wdq
     * @create 2020/1/6 13:58
     * @Return boolean 是否获取成功
     * @exceptiong
     */
    public static boolean getDistributeLock(String key, String value, long expireTime) {
        return false;
    }

    /**
     * RedisTemplate 分布式加锁
     * @author wdq
     * @create 2021/4/29 17:33
     * @param key
     * @param value
     * @param expireTime
     * @Return boolean
     * @exception
     */
    public boolean redisTemplateLock(String key, String value, long expireTime) {
       return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
            long expireAt = System.currentTimeMillis() + expireTime + 1;
            Boolean acquire = connection.setNX(key.getBytes(), String.valueOf(expireAt).getBytes());
            if (!acquire) {
                byte[] bytes = connection.get(key.getBytes());
                if (Objects.nonNull(bytes) && bytes.length > 0) {
                    long expire = Long.parseLong(new String(bytes));
                    // 如果锁已经过期
                    if (expire < System.currentTimeMillis()) {
                        // 重新加锁，防止死锁
                        byte[] oldValue = connection.getSet(key.getBytes(),
                                String.valueOf(System.currentTimeMillis() + expireTime + 1).getBytes());
                        return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                    }
                }
            }
            return true;
        });
    }

    public void test() {
        /*String result = redisTemplate.execute((RedisCallback<String>) connection -> {
            JedisCommands commands = (JedisCommands) connection.getNativeConnection();
            // return commands.set(key, "锁定的资源", "NX", "PX", expire);
        });*/
    }
    /*
    public void redisTemplateUnLock() {
        // 使用Lua脚本删除Redis中匹配value的key，可以避免由于方法执行时间过长而redis锁自动过期失效的时候误删其他线程的锁//
        // spring自带的执行脚本方法中，集群模式直接抛出不支持执行脚本的异常，所以只能拿到原redis的connection来执行脚本
        Long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                // 集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
                Object nativeConnection = connection.getNativeConnection();
                // 集群模式
                if (nativeConnection instanceof JedisCluster) {
                    // return (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA, keys, args);
                } else if (nativeConnection instanceof Jedis) {
                    // return (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA, keys, args);
                }
                return 0L;
            }
        });
    }
    */
    /**
     * @param jedis Redis客户端
     * @param key   锁
     * @param value 请求标识
     * @throws
     * @Description 释放分布式锁
     * @author wdq
     * @create 2020/1/6 14:05
     * @Return boolean
     */
    public static boolean releaseDistributedLock(Jedis jedis, String key, String value) {
        // Lua脚本
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return this.call('del', KEYS[1) else return 0 " +
                "end";
        // 执行脚本
        Object result = jedis.eval(script, Collections.singletonList(key), Collections.singletonList(value));
        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }
}
