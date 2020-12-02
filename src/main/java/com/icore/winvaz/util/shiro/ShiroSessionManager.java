package com.icore.winvaz.util.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.Objects;

/**
 * @Deciption 自定义获取Token
 * @Author wdq
 * @Create 2020/7/31 15:50
 * @Version 1.0.0
 */
public class ShiroSessionManager extends DefaultWebSessionManager {

    //定义常量
    private static final String AUTHORIZATION = "token";

    //重写构造器
    public ShiroSessionManager() {
        // 调用父类构造方法设置会话Cookie
        super();
        this.setDeleteInvalidSessions(true);
    }

    /**
     * 这段代码是生成session最后调用自己实现的sessionDao去redis中查找,redis中没有对应value会报错
     * @author wdq
     * @create 2020/7/31 16:17
     * @param sessionKey
     * @Return org.apache.shiro.session.Session
     * @exception
     */
    @Override
    public Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        ServletRequest request = null;

        Serializable sessionId = getSessionId(sessionKey);
        if (sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }
        boolean b = Objects.nonNull(request) && Objects.nonNull(sessionId);

        if (b) {
            Object sessionObject = request.getAttribute(sessionId.toString());
            if (Objects.nonNull(sessionObject)) {
                return (Session) sessionObject;
            }
        }
        Session session = super.retrieveSession(sessionKey);
        if (b) {
            request.setAttribute(sessionId.toString(), session);
        }
        return session;
    }

    /**
     * 重写方法实现从请求头获取Token便于接口统一
     * 每次请求进来,Shiro会去从请求头找Authorization这个key对应的Value(Token)
     * @author wdq
     * @create 2020/7/31 15:53
     * @param request
     * @param response
     * @Return java.io.Serializable
     * @exception
     */
    @Override
    public Serializable getSessionId(ServletRequest request, ServletResponse response) {
        // 如果请求头中存在token 则从请求头中获取token，如果请求头中有AUTHORIZATION，则其值为sessionId，Shiro就是通过sessionId来控制的
        String token = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        if (!StringUtils.isEmpty(token)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, ShiroHttpServletRequest.URL_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID,token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);

            return token;
        } else {
            // 这里禁用掉Cookie获取方式
            // 按默认规则从Cookie获取Token
            // return super.getSessionId(request, response);
            return null;
        }
    }
}
