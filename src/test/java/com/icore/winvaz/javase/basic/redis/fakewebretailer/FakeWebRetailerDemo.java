package com.icore.winvaz.javase.basic.redis.fakewebretailer;

import redis.clients.jedis.Jedis;

/**
 * @Author wdq
 * @Create 2019-10-09 09:39
 */
public class FakeWebRetailerDemo {

    // Jedis客户端对象
    private static Jedis jedis;

    public FakeWebRetailerDemo() {
        this.jedis = new Jedis("127.0.0.1");
        System.out.println("Redis服务器连接成功》》》:" + jedis.ping());
    }

    public String checkToken(Token token) {
        return jedis.hget("login:", String.valueOf(token.getId()));
    }
}
