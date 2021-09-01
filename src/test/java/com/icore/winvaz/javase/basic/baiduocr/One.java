package com.icore.winvaz.javase.basic.baiduocr;

import lombok.Data;

import java.util.List;

/**
 * @Author wdq
 * @Create 2021/6/23 16:49
 * @Version 1.0.0
 */
@Data
public class One {

    private List<Probability> probability;

    private List<Location> location;

    private String word_name;

    private String word;

    @Data
    private static class Probability {
        private double average;
        private  double min;
        private double variance;
    }

    @Data
    private static class Location {
        private double top;
        private double left;
        private double width;
        private double height;
    }
}
