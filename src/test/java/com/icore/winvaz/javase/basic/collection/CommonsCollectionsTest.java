package com.icore.winvaz.javase.basic.collection;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.collections4.map.LazyMap;
import org.apache.commons.collections4.map.LinkedMap;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Commons Collections增强了Java集合框架。 它提供了几个功能来简化收集处理。 它提供了许多新的接口，实现和实用程序。
 * Commons Collections的主要特点如下 -
 * <p>
 * Bag - Bag接口简化了每个对象具有多个副本的集合。
 * BidiMap - BidiMap接口提供双向映射，可用于使用键或键使用的值来查找值。
 * MapIterator - MapIterator接口为映射提供了简单和易于迭代方法。
 * 转换装饰器 - 转换装饰器(Transforming Decorators)可以在集合添加到集合时改变集合的每个对象。
 * 复合集合 - 复合集合用于要求统一处理多个集合的情况。
 * 有序映射 - 有序映射保留元素添加的顺序。
 * 有序集 - 有序集保留元素添加的顺序。
 * 参考映射 - 参考映射允许在密切控制下对键/值进行垃圾收集。
 * 比较器实现 - 许多比较器实现都可用。
 * 迭代器实现 - 许多迭代器实现都可用。
 * 适配器类 - 适配器类可用于将数组和枚举转换为集合。
 * 实用程序 - 实用程序可用于测试测试或创建集合的典型集合理论属性，如联合，交集。 支持关闭。
 */
public class CommonsCollectionsTest {

    /**
     * 新接口被添加到支持双向映射。 使用双向映射，可以使用值查找键，并且可以使用键轻松查找值。
     */
    @Test
    public void bidiMapTest() {

        System.out.println(StringUtils.center("TreeBidiMap", 40, "="));
        /**
         * BidiMap双向Map，可以通过key找到value，也可以通过value找到key，key和value不能重复
         */
        BidiMap<String, String> treeBidiMap = new TreeBidiMap<>();

        // 设置
        treeBidiMap.put("BJ", "Beijing");
        treeBidiMap.put("SH", "Shanghai");
        treeBidiMap.put("GZ", "Guangzhou");
        treeBidiMap.put("CD", "Chengdu");

        // 获取
        System.out.println("BJ = " + treeBidiMap.get("BJ"));
        System.out.println("Chengdu = " + treeBidiMap.getKey("Chengdu"));
        System.out.println("Original Map: " + treeBidiMap);

        // 移除
        treeBidiMap.removeValue("Beijing");
        System.out.println("Modified Map: " + treeBidiMap);

        // 获取该映射的键和值视图
        BidiMap<String, String> stringStringBidiMap = treeBidiMap.inverseBidiMap();
        System.out.println("Inversed Map: " + stringStringBidiMap);

    }

    /**
     * JDK Map接口很难作为迭代在EntrySet或KeySet对象上迭代。
     * MapIterator提供了对Map的简单迭代。下面的例子说明了这一点。
     */
    @Test
    public void iterableMapTest() {
        IterableMap<String, Object> map = new HashedMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);

        MapIterator<String, Object> iterator = map.mapIterator();
        while (iterator.hasNext()) {
            /*
            String key = iterator.next();
            Object value = iterator.getValue();
            System.out.println("key:" + key + "," + "value:" + value);
            */
            /**
             *
             */
            String key = iterator.next();
            Object value = map.get(key);
            System.out.println("key:" + key + "," + "value:" + value);

            iterator.setValue(value + "_");
        }
        System.out.println(map);
    }

    /**
     * OrderedMap是映射的新接口，用于保留添加元素的顺序。 LinkedMap和ListOrderedMap是两种可用的实现。
     * 此接口支持Map的迭代器，并允许在Map中向前或向后两个方向进行迭代。
     */
    @Test
    public void orderedMapTest() {
        OrderedMap<String, Object> map = new LinkedMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        System.out.println(map.firstKey());
        System.out.println(map.nextKey("one"));
        System.out.println(map.nextKey("two"));
    }

    /**
     * Apache Commons Collections库的CollectionUtils类提供各种实用方法，用于覆盖广泛用例的常见操作。
     * 它有助于避免编写样板代码。 这个库在jdk 8之前是非常有用的，但现在Java 8的Stream API提供了类似的功能。
     */
    @Test
    public void collectionUtilsTest() {
        /**
         * 检查是否为空元素
         *      * CollectionUtils的addIgnoreNull()方法可用于确保只要非空(null)值被添加到集合中
         *      * 声明
         *      * public static <T> boolean addIgnoreNull(Collection<T> collection, T object)
         *      * 参数
         *      *      collection - 要添加到的集合，不能为null值。
         *      *      object - 要添加的对象，如果为null，则不会添加
         *      * 返回值
         *      *      如果集合已更改，则返回true
         */
        /*
        List<String> list = new LinkedList<>();

        CollectionUtils.addIgnoreNull(list, null);
        CollectionUtils.addIgnoreNull(list, "null");

        System.out.println(list);
        */

        /**
         *  合并两个排序列表
         *  CollectionUtils的collate()方法可用于合并两个已排序的列表
         *  声明
         *  public static <O extends Comparable<? super O>> List<O>
         *      collate(Iterable<? extends 0> a, Iterable<? extends O> b)
         *  参数
         *      a - 第一个集合，不能为null。
         *      b - 第二个集合不能为null。
         *  返回值
         *      一个新的排序列表，其中包含集合a和b的元素
         *  异常
         *      NullPointerException - 如果其中一个集合为null。
         */
        /*
        List<String> a = Arrays.asList("A", "C", "E", "F");
        List<String> b = Arrays.asList("B", "D", "F");
        // 合并两个排序集合，不去重
        List<String> collate = CollectionUtils.collate(a, b);
        System.out.println(collate);
        */

        /**
         * 转换对象
         * CollectionUtils的collect()方法可用于将一种类型的对象列表转换为不同类型的对象列表。
         * 声明
         *    public static <I, O> Collection<O> collect(Iterable<T> inputCollection,
         *      Transformer<? super I, ? extends O> transformer)
         * 参数
         *      inputCollection - 从中获取输入的集合可能不为null。
         *      transformer - 要使用的transformer可能为null。
         * 返回值
         *       换结果(新列表)
         */
        /*
        List<String> list = Arrays.asList("1", "2", "3");
        *//*CollectionUtils.collect(list, new Transformer<String, Integer>() {
            @Override
            public Integer transform(String s) {
                return Integer.parseInt(s);
            }
        });*//*
        // 1.8
        Collection<Integer> collect = CollectionUtils.collect(list, (s) -> Integer.parseInt(s));
        System.out.println(collect);
        */

        /**
         * 使用filter()方法过滤列表
         * CollectionUtils的filter()方法可用于过滤列表以移除不满足由谓词传递提供的条件的对象
         * 声明
         *     public static <T> boolean filter(Iterable<T> collection, Predicate<? super T> predicate)
         * 参数
         *    collection - 从中获取输入的集合可能不为null。
         *    predicate - 用作过滤器的predicate可能为null。
         * 返回值
         *      如果通过此调用修改了集合，则返回true，否则返回false
         *
         * ---------------------------------------------------
         * CollectionUtils的filterInverse()方法可用于过滤列表以移除不满足由谓词传递提供的条件的对象
         * 声明
         *    public static <T> boolean filterInverse(Iterable<T> collection,
         *      Predicate<? super T> predicate)
         * 参数
         *    collection - 从中获取输入的集合，可能不为null。
         *    predicate - 用作过滤器的predicate可能为null。
         * 返回值
         *    如果通过此调用修改了集合，则返回true，否则返回false。
         */
        /*
        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

        System.out.println("Original List:" + list);
        // CollectionUtils.filter(list, (s) -> s.intValue() % 2 == 0 ? true : false);
        // System.out.println("Filtered List:" + list); // [2, 4, 6, 8]
        CollectionUtils.filterInverse(list, (s) -> s.intValue() % 2 == 0 ? true : false);
        System.out.println("Filtered List:" + list); // [1, 3, 5, 7]
        */

        /**
         * 检查非空列表
         * CollectionUtils的isNotEmpty()方法可用于检查列表是否为null而不用单行null列表。
         * 因此在检查列表大小之前，不需要将无效检查放在任何地方。
         * 声明
         *    public static boolean isNotEmpty(Collection<?> coll)
         *    public static boolean isEmpty(Collection<?> coll)
         * 参数
         *    coll - 要检查的集合，可能为null。
         * 返回值
         *    如果非空且非null， 则返回true。
         */
        /*
        List<String> list = null;
        System.out.println(list == null || list.isEmpty()); // true
        System.out.println(CollectionUtils.isNotEmpty(list)); // false
        System.out.println(CollectionUtils.isEmpty(list)); // true
        */

        /**
         * 检查子列表
         * CollectionUtils的isSubCollection()方法可用于检查集合是否包含给定集合
         * 声明
         *    public static boolean isSubCollection(Collection<?> a, Collection<?> b)
         * 参数
         *    a - 第一个(子)集合不能为空。
         *    b - 第二个(子)集合不能为空。
         *  当且仅当a是b的子集合时才为true
         */
        List<String> a = Arrays.asList("A", "A", "A", "C", "B", "B");
        List<String> b = Arrays.asList("A", "A", "B", "B");
        // System.out.println("Is a contained in List a :" + CollectionUtils.isSubCollection(b, a)); // true

        /**
         * 检查相交
         * CollectionUtils的intersection()方法可用于获取两个集合(交集)之间的公共对象部分。
         * 声明
         *    public static <O> Collection<O> intersection(Iterable<? extends O> a,
         *      Iterable<? extends O> b)
         * 参数
         *    a - 第一个(子)集合不能为null。
         *    b - 第二个(超集)集合不能为null。
         * 返回值
         *    两个集合的交集
         */
        // System.out.println(CollectionUtils.intersection(a, b)); // [A, A, B, B]

        /**
         * 求差集
         * CollectionUtils的subtract()方法可用于通过从其他集合中减去一个集合的对象来获取新集合。
         * 声明
         *    public static <O> Collection<O> subtract(Iterable<? extends O> a,
         *    Iterable<? extends O> b)
         * 参数
         *    a - 要从中减去的集合，不能为null。
         *    b - 要减去的集合，不能为null。
         * 返回值
         * 两个集合的差集(新集合)。
         */
        // System.out.println(CollectionUtils.subtract(a, b)); // [A, C]


        /**
         * 求联合集
         * CollectionUtils的union()方法可用于获取两个集合的联合。
         * 声明
         *    public static <O> Collection<O> union(Iterable<? extends O> a,
         *    Iterable<? extends O> b)
         * 参数
         *    a - 第一个集合，不能为null。
         *    b - 第二个集合，不能为null。
         * 返回值
         * 两个集合的联合。
         */
        System.out.println(CollectionUtils.union(a, b)); // [A, A, A, B, B, C]
    }

    @Test
    public void lazyMapTest() {
        //
        System.out.println(StringUtils.center("LazyMap", 40, "="));

        Factory factory = new Factory() {
            @Override
            public Object create() {
                return new Date();
            }
        };

        /**
         * LazyMap，这个Map中的键/值对一开始并不存在，当被调用到时才创建。
         */
        LazyMap lazyMap = LazyMap.lazyMap(new HashMap<>(), () -> new Date());
        System.out.println("lazyMap: " + lazyMap.get("NOW"));
    }

    /**
     * 新的接口被添加到支持bag。 Bag接口定义了一个集合，它可以计算一个对象出现在集合中的次数。
     * 例如，如果Bag包含{a，a，b，c}，则getCount("a")方法将返回2，而uniqueSet()返回唯一值。
     */
    @Test
    public void bagTest() {
        Bag<String> bag = new HashBag<>();

        // 添加元素，添加a元素2两次
        bag.add("a", 2);

        // 添加b元素1一次
        bag.add("b");

        //
        bag.add("c");
        bag.add("d", 3);

        System.out.println("d is present " + bag.getCount("d") + " times");
        System.out.println("bag " + bag); // [2:a,1:b,1:c,3:d]

        // 唯一
        System.out.println("Unique Set " + bag.uniqueSet()); // [a, b, c, d]

        // 移除
        bag.remove("d", 2);
        System.out.println("d is present " + bag.getCount("d") + " times");
    }
}
