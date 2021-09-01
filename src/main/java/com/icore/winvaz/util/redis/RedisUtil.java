package com.icore.winvaz.util.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toSet;

/**
 * @Deciption Redis工具类
 * @Author wdq
 * @Create 2019/12/20 10:55
 */
@Slf4j
@Component
public class RedisUtil {

    @Autowired
    private static RedisTemplate<String, Object> redisTemplate;

    private static final String LOCK_LUA;

    private static final String UNLOCK_LUA;

    static {
        StringBuilder lsb = new StringBuilder();
        lsb.append("if redis.call('setNx', KEYS[1], ARGV[1]) then");
        lsb.append("if redis.call('get', KEYS[1]) == ARGV[1] then");
        lsb.append("redis.call('expire', KEYS[1], ARGV[2]) ");
        lsb.append("    return 'OK'  ");
        lsb.append("else  ");
        lsb.append("    return nil end ");
        lsb.append("end  ");
        LOCK_LUA = lsb.toString();

        // 清空
        lsb.delete(0, lsb.length());

        lsb.append("local cliVal = redis.call('get', KEYS[1]) ");
        lsb.append("if(cliVal == ARGV[1]) then");
        lsb.append("redis.call('del', KEYS[1]) ");
        lsb.append("    return 'OK' ");
        lsb.append("else    ");
        lsb.append("    return nil ");
        lsb.append("end ");
        UNLOCK_LUA = lsb.toString();
    }

    /**
     * ========================= Shiro ========================
     */
    private static final String KEY = "shiroSessionMap";

    public void hadd(String sessionId, Session session) {
        redisTemplate.boundHashOps(KEY).put(sessionId, session);
    }

    public void hdelete(String sessionId) {
        redisTemplate.boundHashOps(KEY).delete(sessionId);
    }

    public Session hget(String sessionId) {
        return (Session) redisTemplate.boundHashOps(KEY).get(sessionId);
    }

    public List<Session> hmget() {
        List<Session> sessions = new ArrayList<>();
        List<Object> values = redisTemplate.boundHashOps(KEY).values();
        if (Objects.nonNull(values) && !CollectionUtils.isEmpty(values)) {
            for (Object obj : values) {
                sessions.add((Session) obj);
            }
        }
        return sessions;
    }

    /**========================= Shiro ========================*/


    /**=======================common==========================*/

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public static boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error("redis expire failure key = {}，time = {}，error = {}", key, time, e);
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效，失效时间为负数，说明该主键未设置失效时间(失效时间默认为-1)
     */
    public static long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public static boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("redis haskey failure key = {}，error = {}", key, e);
            return false;
        }
    }

    public static Set hKeys(final String hashTableName, final String fieldPattern) {
        try {
            Set<String> fields = redisTemplate.keys(hashTableName);
            if (CollectionUtils.isEmpty(fields)) return fields;

            return fields.parallelStream()
                    .filter(c -> c.contains(fieldPattern))
                    .collect(toSet());
        } catch (Exception e) {
            log.error("redis hKeys failure tableName = {}", hashTableName);
            return new HashSet<>(0);
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public static void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(String.valueOf(CollectionUtils.arrayToList(key)));
            }
        }
    }

    //============================String=============================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public static Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public static boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("redis set failure key = {}，value = {}，error = {}", key, value, e);
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public static boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error("redis set failure key = {}，value = {}，time = {}，error = {}", key, value, time, e);
            return false;
        }
    }

    /**
     * 递增，此时value值必须为int类型，否则报错
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return
     */
    public static long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return
     */
    public static long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    //================================Map=================================

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public static Object hGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public static Map<Object, Object> hMGet(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public static boolean hMSet(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            log.error("redis hMSet failure key = {}，map = {}，error = {}", key, map, e);
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public static boolean hMSet(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("redis hMSet failure key = {}， map = {}，time = {}， error = {}", key, map, time, e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public static boolean hSet(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            log.error("redis hSet failure key = {}，item = {}，value = {}，error = {}", key, item, value, e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public static boolean hSet(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("redis hSet failure key = {}，item = {}，value = {}，time = {}，error = {}", key, item, value, time
                    , e);
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public static void hDel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public static boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public static double hIncr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    public static double hDecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    //============================set=============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public static Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            log.error("redis sGet failure key = {}，error = {}", key, e);
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public static boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            log.error("redis sHasKey failure key = {}，value = {}，error = {}", key, value, e);
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public static long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            log.error("redis sSet failure key = {}，values = {}，error = {}", key, values, e);
            return 0;
        }
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public static long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            log.error("redis sSetAndTime failure key = {}，time = {}，values = {}，error = {}", key, time, values, e);
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    public static long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            log.error("redis sGetSetSize failure key = {}，error = {}", key, e);
            return 0;
        }
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public static long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            log.error("redis setRemove failure key = {}，values = {}，error = {}", key, values, e);
            return 0;
        }
    }
    //===============================list=================================

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     * @return
     */
    public static List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            log.error("redis lGet failure key = {}，start = {}，end = {}，error = {}", key, start, end, e);
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return
     */
    public static long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            log.error("redis lGetListSize failure key = {}，error = {}", key, e);
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public static Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            log.error("redis lGetIndex failure key = {}，index = {}，error = {}", key, index, e);
            return null;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public static boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            log.error("redis lSet failure key = {}，value = {}，error = {}", key, value, e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public static boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("redis lSet failure key = {}，value = {}，time = {}，error = {}", key, value, time, e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public static boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            log.error("redis lSet failure key = {}，value = {}，error = {}", key, value, e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public static boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("redis lSet failure key = {}，value = {}，time = {}，error = {}", key, value, time, e);
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    public static boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            log.error("redislUpdateIndex failure key = {}，index = {}，value = {}，error = {}", key, index, value, e);
            return false;
        }
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public static long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            log.error("redis lRemove failure key = {}，count = {}，value = {}， error = {}", key, count, value, e);
            return 0;
        }
    }

    /**
     * redis发布
     *
     * @param channel
     * @param message
     * @throws
     * @author wdq
     * @create 2021/3/27 18:18
     * @Return void
     */
    public static void publish(String channel, Object message) {
        redisTemplate.convertAndSend(channel, message);
    }

    /**
     * redis 新key替换老key
     *
     * @param oldKey
     * @param newKey
     * @throws
     * @author wdq
     * @create 2021/3/27 18:20
     * @Return void
     */
    public static void rename(String oldKey, String newKey) {
        if (redisTemplate.hasKey(oldKey)) {
            redisTemplate.rename(oldKey, newKey);
        }
    }

    /**
     * Redis + 锁
     *
     * @param key
     * @param value
     * @param expireTime
     * @throws
     * @author wdq
     * @create 2021/3/27 18:21
     * @Return boolean
     */
    public boolean lock(String key, String value, long expireTime) {
        Object execute = redisTemplate.execute((RedisCallback) connection ->
                connection.execute("set", key.getBytes(), value.getBytes(), "NX".getBytes(), "EX".getBytes(),
                        String.valueOf(expireTime).getBytes()));

        return Objects.equals("OK", execute);
    }

    /**
     * Redis + 锁
     *
     * @param key
     * @param value
     * @throws
     * @author wdq
     * @create 2021/3/27 18:21
     * @Return boolean
     */
    public boolean lock(String key, String value) {
        Object execute = redisTemplate.execute((RedisCallback) connection ->
                connection.execute("set", key.getBytes(), value.getBytes(), "NX".getBytes()));

        return Objects.equals("OK", execute);
    }

    /**
     * Redis + 释放锁
     * @author wdq
     * @create 2021/3/27 18:31
     * @param key
     * @param value
     * @Return boolean
     * @exception
     */
    public boolean unLock(String key, String value) {
        if (Objects.equals(value, String.valueOf(redisTemplate.opsForValue().get(key)))) {
            Boolean delete = redisTemplate.delete(key);
            return delete == null ? false : delete.booleanValue();
        }
        return false;
    }

    /**
     * Redis Lua脚本上锁
     * @author wdq
     * @create 2021/5/31 15:02
     * @param key
     * @param value
     * @param expireTime 秒
     * @Return boolean
     * @exception
     */
    public boolean lockLua(String key, String value, long expireTime) {
        RedisScript<String> redisScript = new DefaultRedisScript<>(LOCK_LUA, String.class);
        Object result = redisTemplate.execute(redisScript, new StringRedisSerializer(), new StringRedisSerializer(),
                Arrays.asList(key), value, String.valueOf(expireTime));
        return "OK".equals(result);
    }

    /**
     * Redis Lua脚本解锁
     * @author wdq
     * @create 2021/5/31 15:02
     * @param key
     * @param value
     * @Return boolean
     * @exception
     */
    public boolean unLockLua(String key, String value) {
        if (value.equals(String.valueOf(redisTemplate.opsForValue().get(key)))) {
            RedisScript<String> redisScript = new DefaultRedisScript<>(UNLOCK_LUA, String.class);
            Object result = redisTemplate.execute(redisScript, Arrays.asList(key), value);
            return "OK".equals(result);
        }
        return false;
    }



    /**
     * 自增序列号
     * @author wdq
     * @create 2021/3/27 18:41
     * @param key
     * @param length
     * @Return java.lang.String
     * @exception
     */
    public synchronized String seqGeneratorByRedisAtomicLong(String key, int length) {
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        long result = counter.incrementAndGet();
        String upCoder = String.format("%0" + length + "d", result);// 后缀不够length长，前补0
        return key + upCoder;
    }

}