package com.icore.winvaz.javase.basic.collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 数组还是集合都是容器，容器就是用来存储的！
 * * 数组的长度是固定的，集合的长度是可变的 (数组是定长的，集合是变长的)
 * * 数组可以存储对象，也可以存储基本数据类型，但是集合只存对象，不存储基本数据类型，集合存储的都是引用类型
 * <p>
 * 3.	学习集合框架，到底需要学的是什么
 * 掌握，如何把对象存储到集合这个容器中
 * 掌握，如何把对象中集合容器中获取出来
 * 掌握，集合中每一个类的自身特点
 * 4.	集合的框架图
 * 集合是一个继承体系，学习的是这个体系中的最顶层，建立最底层类的对象
 * 集合有一个顶层的接口 java.util.Collection,定义了这个体系的最共性的内容
 */
public class CollectionTest {
    /**
     * * 集合中的获取交集的方法
     * * boolean retainAll(Collection c)
     * * 调用者,调用retainAll方法的集合，集合中的元素发生改变，返回true，没有变过返回false;
     */
    @Test
    public void retainAll() {
        Collection col1 = new ArrayList();
        Collection col2 = new ArrayList();

        col1.add("abc1");
        col1.add("abc2");

        col2.add("abc3");
        col2.add("abc4");
        col2.add("abc2");
        col2.add("abc1");
        //调用方法，获取两个集合的交集
        //将col1,col2中,相同元素，存储到col1中
        // 调用者,调用retainAll方法的集合，集合中的元素发生改变，返回true，没有变过返回fals
        boolean b = col1.retainAll(col2);
        System.out.println(b); // false
        System.out.println("col1=" + col1); // col1=[abc1, abc2]
        System.out.println("col2=" + col2); // col2=[abc3, abc4, abc2, abc1]
    }

    /**
     * 集合中的包含方法
     * boolean contains(Object o)
     * boolean containsAll(Collectoin c)
     */
    @Test
    public void contains() {
        Collection collection1 = new ArrayList();
        Collection collection2 = new ArrayList();
        // 在collection1中存储123,456
        collection1.add("abc1");
        collection1.add("123");
        // 在collection2中，存储abc1,abc2
        collection2.add("abc1");
        collection2.add("123");
        collection2.add(123);

        // Returns true if this collection contains the specified element.More formally,
        // returns true if and only if this collection contains at least one element e such that Objects.equals(o, e).
        boolean contains = collection1.contains("abc1");
        System.out.println(contains); // true
        // containsAll(Collection c)两集合的交集，调用者里元素全包含返回true
        boolean containsAll = collection1.containsAll(collection2);
        System.out.println(containsAll); // false
    }

    /**
     * 移除集合中的元素
     * void clear()
     * * 返回集合中存储元素个数的方法int size()
     * * 数组.length 数组长度，属性
     * * String.length()字符串长度，方法
     * * 集合.size()集合长度，方法
     */
    @Test
    public void clear() {
        Collection collection = new ArrayList();
        // 在collection1中存储123,456
        collection.add(123);
        collection.add(456);

        System.out.println(collection.size()); // 2
        collection.clear(); // 只是移除集合的元素，集合还存在
        System.out.println(collection); // []
        System.out.println(collection.size()); // 0
    }

    /**
     * 定义一个方法，移除所有
     * removeAll()
     */
    @Test
    public void removeAll() {
        Collection collection1 = new ArrayList();
        Collection collection2 = new ArrayList();

        // 在collection1中存储123,456
        collection1.add(123);
        collection1.add(456);
        collection1.add("abc1");

        // 在collection2中，存储abc1,abc2
        collection2.add("abc1");
        collection2.add("abc2");

        System.out.println("移除前collection1=" + collection1);
        System.out.println("移除前collection2=" + collection2);
        // 移除两个集合中共性内容。以方法中的参数为准。
        boolean removeAll = collection1.removeAll(collection2);
        System.out.println(removeAll); // true
        System.out.println("移除后collection1=" + collection1); // 移除后collection1=[123, 456]
        System.out.println("移除后collection2=" + collection2); // 移除后collection2=[abc1, abc2]
    }


    /**
     * 定义方法，将一个集合装进另一个集合
     * addAll(Collection c)
     * 定义两个集合
     */
    @Test
    public void addAll() {
        Collection collection1 = new ArrayList();
        Collection collection2 = new ArrayList();

        // 在collection1中存储123,456
        collection1.add(123);
        collection1.add(456);

        // 在collection2中，存储abc1,abc2
        collection2.add("abc1");
        collection2.add("abc2");

        System.out.println("collection1=" + collection1);
        System.out.println("collection2=" + collection2);
        // 将collection2存储到collection1中，相同的元素也会添加
        collection1.addAll(collection2);
        System.out.println("存储之后的集合:" + collection1); // col1 + col2
        System.out.println("存储之后的集合:" + collection2); // col2不变
    }

    /**
     * 定义一个方法，建立集合对象，存储对象
     */
    @Test
    public void add() {
        // 用DOS命令会出现不安全的隐患，是因为泛型的问题
        // 创建集合对象接口=new 子类的方式
        Collection collection = new ArrayList();
        // 调用add方法，将对象存储到集合,任意对象都可以
        collection.add("abc");
        collection.add(false);
        collection.add(456); // 456基本数据类型自动装箱成Integer类型

        // 输出语句打印集合，只是为了演示方便
        System.out.println(collection);
    }

    /**
     * Collection中的移除方法
     * 建立集合对象，存储元素，删除
     */
    @Test
    public void remove() {
        Collection collection = new ArrayList();
        collection.add("abc1");
        collection.add("abc2");
        collection.add("abc3");
        collection.add("abc4");
        System.out.println("移除前:" + collection);
        boolean remove = collection.remove("abc3");
        System.out.println(remove); // true
        System.out.println("移除后:" + collection);
    }

    /**
     *  Java核心技术 卷I 集合 404 小集合
     */
    public void collections() {
        // Java 9 引入了一些静态方法，可以生成给定元素的集或列表，以及戈丁键/值对的映射。
        // JDK 9
        // List<String> names = List.of("Peter", "Paul", "Mary");
        // System.out.println(names);
        // Set.of()
        // Map.of();
        // ofEntries();

        // 创建100个字符串的List，每个串设置为"DEFAULT"
        List<String> settings = Collections.nCopies(100, "DEFAULT");
        System.out.println(settings);
    }
}
