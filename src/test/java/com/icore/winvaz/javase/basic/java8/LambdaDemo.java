package com.icore.winvaz.javase.basic.java8;

import lombok.Data;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.stream.Collectors;

/**
 * Description: ${DESCRIPTION}
 *
 * @author wendq
 * @create 2018/11/8 11:37
 */
public class LambdaDemo {
    public static void main(String[] args) {

        String[] planets = new String[]{"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"};
        System.out.println(Arrays.toString(planets));
        System.out.println("Sorted in dictionary order:");
        Arrays.sort(planets);
        System.out.println(Arrays.toString(planets));
        System.out.println("Sorted by length:");
        Arrays.sort(planets, (first, second) -> first.length() - second.length());
        System.out.println(Arrays.toString(planets));
        Arrays.sort(planets, Comparator.comparingInt(String::length));
        System.out.println(Arrays.toString(planets));

        LambdaDemo lambdaDemo = new LambdaDemo();

        List<Pserson> psersons = new ArrayList<>();
        Pserson pserson1 = new Pserson();

        pserson1.setName("张三");
        pserson1.setAge(10);
        psersons.add(pserson1);

        Pserson pserson2 = new Pserson();
        pserson2.setName("李四");
        pserson2.setAge(11);
        psersons.add(pserson2);

        List<Pserson> psersonList = new ArrayList<>();
        Pserson pserson3 = new Pserson();
        pserson3.setName("王老五");
        pserson3.setAge(11);
        psersonList.add(pserson3);

        Pserson pserson4 = new Pserson();
        pserson4.setName("燕小六");
        pserson4.setAge(12);
        psersonList.add(pserson4);

        psersons.forEach(pserson -> {
            psersonList.forEach(pserson5 -> {
                if (pserson.getAge().equals(pserson5.getAge())) {
                    List<Pserson> collect =
                            psersons.stream().filter(pserson6 -> pserson6.equals(pserson)).collect(Collectors.toList());
                    System.out.println(collect.toString());
                }
                return;
            });
        });

        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;

        // 不用声明类型
        MathOperation subtraction = (int a, int b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + lambdaDemo.operate(10, 5, addition));
        System.out.println("10 - 5 = " + lambdaDemo.operate(10, 5, subtraction));
        System.out.println("10 * 5 = " + lambdaDemo.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + lambdaDemo.operate(10, 5, division));

        // 不用括号
        GreetingService greetingService1 = message ->
                System.out.println("Hello" + message);

        // 用括号
        GreetingService greetingService2 = (message) ->
                System.out.println("Hello" + message);

        greetingService1.sayMessage("World !");
        greetingService2.sayMessage("Good !");
    }

    public static void countDown(int start, int delay) {
        ActionListener listener = event -> {
            // 变量的作用域
            // start--; // ERROR: Can't mutate captured variable Variable used in lambda expression should be final or
            // effectively final
            System.out.println(start);
        };
    }

    public static void repeat(String text, int count) {
        for (int i = 0; i <= count ; i++) {
            ActionListener listener = event -> {
                // System.out.println(i + ": " + text); // ERROR: Cannot refer to changing i
            };
        }
    }


    interface MathOperation {

        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }
}

@Data
class Pserson {
    private String name;
    private Integer age;
}