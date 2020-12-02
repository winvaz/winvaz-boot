package com.icore.winvaz.javase.basic.redis;

import redis.clients.jedis.Jedis;

/**
 * @Deciption 连接Redis
 * @Author wdq
 * @Create 2019-09-17 20:26
 */
public class RedisDemo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1");
        // 测试Redis服务器是否还在运行
        System.out.println("Redis服务运行中。。。" + jedis.ping());

        // Redis操作五种数据类型
        /*
        // 操作String类型
        // set()--添加
        jedis.set("ID", UUID.randomUUID().toString());
        // get()--获取
        System.out.println("获取Redis数据库键为ID的值为:" + jedis.get("ID"));
        // del()--删除(适用于所有数据类型),删除成功返回:1，删除失败返回:0
        System.out.println("删除Redis数据库键为ID:" + jedis.del("ID"));
        */

        /*
        // 操作list类型
        // rpush()--将给定的值推入到列表右端。lpush()--左端
        jedis.rpush("list", "right_list_1");
        jedis.rpush("list", "right_list_2");
        jedis.rpush("list", "right_list_3");
        // 获取列表元素
        // lrange()--获取列表在给定范围上的所有值
        List<String> stringList = jedis.lrange("list", 0, 3);
        // 遍历输出
        Iterator<String> iterator = stringList.iterator();
        while (iterator.hasNext()) {
            System.out.println("获取Redis数据库list列表类型的值为:" + iterator.next());
        }
        // lindex()--获取列表在给定位置上的单个元素
        String string = jedis.lindex("list", 1);
        System.out.println("获取Redis数据库list列表类型第1个位置上的值为:" + string);
        // lpop()--从列表的左端弹出一个值，并返回这个弹出(相当于移除)的值
        String list = jedis.lpop("list");
        System.out.println("从Redis数据库list列表左端获取到的值为:" + list);
        */

        /*
        // 操作set(元素各部相同，无序排列)类型
        jedis.sadd("set", "set_1");
        jedis.sadd("set", "set_1");
        jedis.sadd("set", "set_2");
        jedis.sadd("set", "set_2");
        jedis.sadd("set", "set_3");
        // smembers()--获取set集合中包含的所有元素
        Set<String> set = jedis.smembers("set");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println("获取Redis数据库set列表类型的值为:" + iterator.next());
        }
        // sismember()--判断给点的元素是否存在集合中
        Boolean sismember = jedis.sismember("set", "set_1");
        if (sismember) {
            // srem()--如果给点的元素存在集合中，则移除这个元素，移除成功返回:1，移除失败返回:0
            Long srem = jedis.srem("set", "set_1");
            System.out.println("set_1元素存在，移除:" + srem);
        }
        */

        /*
        // 操作Hash类型(Redis的散列可以存储多个键值对之间的映射)
        // hset()--在散列里面关联起给定的键值对
        Map<String, String> map = new HashMap<>();
        map.put("hash_1", "1");
        map.put("hash_2", "2");
        map.put("hash_3", "3");
        jedis.hset("hash", map);
        //
        map = jedis.hgetAll("hash");
        // 调用Map接口中keySet()，将键值存储到Set集合
        Set<String> stringSet = map.keySet();
        // 迭代set集合
        Iterator<String> iterator = stringSet.iterator();
        while (iterator.hasNext()) {
            // iterator.next()方法，返回的是string类，是map集合的键
            String key = iterator.next();
            // 通过map集合的get()方法，传递键，获取值
            String value = map.get(key);
            System.out.println("Redis数据库存储的Hash类型的键值对为:" + key + "=" + value);
        }
        // 如果给点的键存在于散列中，那么移除这个键
        Long hdel = jedis.hdel("hash", "hash_1");
        System.out.println("从Redis数据库hash类型中删除hash_1元素:" + hdel);
        */

        /*
        // 操作sorted set类型(有序集合和散列一样，都用于存储键值对。)
        // 有序集合的键称为成员(member),每个成员都是各不相同的，而有序集合的值则被称为分值(score),分值必须为浮点数。
        // zadd()--将一个带有给定分值的成员添加到有序集合中
        jedis.zadd("sortedSet", 1, "sortedSet_1");
        jedis.zadd("sortedSet", 2, "sortedSet_2");
        jedis.zadd("sortedSet", 3, "sortedSet_3");
        // zrange()--根据元素在列表中的位置，从有序集合中获取多个元素
        Set<String> sortedSet = jedis.zrange("sortedSet", 0, 3);
        Iterator<String> iterator = sortedSet.iterator();
        while (iterator.hasNext()) {
            System.out.println("获取Redis数据库sortedSet类型的值为:" + iterator.next());
        }
        // zrangeByScore()--将有序集合在给定分值范围内的所有元素
        sortedSet = jedis.zrangeByScore("sortedSet", 0, 2);
        System.out.println("分值在0~2之间的元素有:" + sortedSet);
        // zrem()--如果给定的元素存在于有序集合，那么移除这个成员，移除规则同上
        Long zrem = jedis.zrem("sortedSet", "sortedSet_1");
        System.out.println("从有序集合中移除sortedSet_1成员:" + zrem);
        */
    }
}
