package com.icore.winvaz.util.shiro;

import com.icore.winvaz.constant.RedisConstant;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;

/**
 * @Deciption 自定义SessionId生成器
 * @Author wdq
 * @Create 2020/7/31 17:04
 * @Version 1.0.0
 */
public class ShiroSessionIdGenerator implements SessionIdGenerator {
    /**
     * @Description 实现SessionId生成
     * @author wdq
     * @create 2020/7/31 17:05
     * @param session
     * @Return java.io.Serializable
     * @exception
     */
    @Override
    public Serializable generateId(Session session) {
        Serializable sessionId = new JavaUuidSessionIdGenerator().generateId(session);
        return String.format(RedisConstant.REDIS_PREFIX_LOGIN, sessionId);
    }
}
