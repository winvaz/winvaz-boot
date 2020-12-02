package com.icore.winvaz.entity.model.user;

import org.apache.commons.lang3.StringUtils;

/**
 * @Deciption 响应给客户端的令牌
 * @Author wdq
 * @Create 2020/7/23 13:46
 * @Version 1.0.0
 */
public final class Token {

    private String token;

    public Token(String token) {

        if (StringUtils.isBlank(token)) {
            throw new IllegalStateException("Invalid token");
        }

        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Token that = (Token) obj;

        return this.token.equals(that.token);

    }

    @Override
    public int hashCode() {
        return this.token.hashCode();
    }

    @Override
    public String toString() {
        return token;
    }
}