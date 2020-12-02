package com.icore.winvaz.util.web;

/**
 * @Deciption 分页对象
 * @Author wdq
 * @Create 2020/8/5 23:11
 * @Version 1.0.0
 */
public class PageRequest {

    private int page;
    private int size;

    public PageRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }
}
