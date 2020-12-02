package com.icore.winvaz.javase.basic.redis.fakewebretailer;

import java.util.Objects;

/**
 * @Author wdq
 * @Create 2019-10-09 09:40
 */
public class Token {
    // token id
    private Long id;

    public Token(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return Objects.equals(id, token.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                '}';
    }
}
