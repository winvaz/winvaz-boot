package com.icore.winvaz.javase.basic.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @Deciption Java 8 新特性--方法引用
 * @Author wdq
 * @Create 2019-10-12 09:17
 */
public class MethodQuoteDemo {

    /**
     * @Description 静态内部类
     * @Author wdq
     * @Params
     * @Return
     * @Create 2019-10-12 09:20
     */
    static class Car {
        public static Car create(final Supplier<Car> supplier) {
            return supplier.get();
        }

        public static void collide(final Car car) {
            System.out.println("Collide " + car.toString());
        }

        public void follow(final Car car) {
            System.out.println("Follow the " + car.toString());
        }

        public void repair() {
            System.out.println("Repaired " + this.toString());
        }
    }

    public static void main(String[] args) {
        // 静态方法引用：它的语法是Class::static_method
        Car car = Car.create(Car::new);
        List<Car> cars = Arrays.asList(car);
        //System.out.println(cars);

        // 特定类的任意对象的方法引用：它的语法是Class::method
        cars.forEach(Car::collide);

        // 特定对象的方法引用：它的语法是instance::method
        cars.forEach(Car::repair);

        // 构造器引用：它的语法是Class::new，或者更一般的Class< T >::new
        cars.forEach(car::follow);

        List<String> strings = new ArrayList<>();
        strings.add("你好啊");
        strings.add("好什么好");
        strings.add("那不好得了");
        strings.add("不好什么不好");

        strings.forEach(System.out::println);
    }
}