package com.icore.winvaz.javase.basic.collection;

import com.icore.winvaz.javase.Person;
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.HashedMap;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 5.	Map集合
 * 映射，键映射到值的集合，Map体系的集合，存储对象的时候，
 * 一次存储两个对象，一个称作键，一个称作值，双列集合
 * 	一个键，最多只能映射一个值
 * 	不允许出现重复键
 */
public class MapTest {

    /**
     * boolean containsKey(K)判断有没有这个键
     * boolean containsValue(V)判断有没有这个值
     */
    @Test
    public void containsMap() {
        Map<String, Object> map = new HashMap<>();

        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);

        boolean key = map.containsKey("1");
        System.out.println(key); // true
        boolean value = map.containsValue(2);
        System.out.println(value); // true
        System.out.println(13 & 17);
    }

    /**
     * 移除键值对
     * V remove(K)
     */
    @Test
    public void removeMap() {
        Map<String, Object> map = new HashMap<>();

        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);

        // 移除键返回值
        Object remove = map.remove("1");
        System.out.println(remove); // 1

        System.out.println(map);
    }

    /**
     * 通过键，获取值
     * V get(K)
     */
    @Test
    public void getMap() {
        Map<String, Object> map = new HashMap<>();

        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);

        // 通过键，获取值
        Object value = map.get("1");
        System.out.println(value);
    }


    /**
     * 第一种方式：keySet()方法
     * 	第一种，利用Map中的一个方法keySet()，Map集合中的键，存储到Set集合
     * 	迭代Set集合，获取出来的是所有的键
     * 	通过键获取值，Map接口中的get方法
     */
    @Test
    public void keySet() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);

        // 调用Map接口中keySet()，将键值存储到Set集合
        Set<String> set = map.keySet();
        // 迭代set集合
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            // iterator.next()方法，返回的是string类，是map集合的键
            String key = iterator.next();
            // 通过map集合的get()方法，传递键，获取值
            Integer value = map.get(key);
            System.out.println(key + "==" + value);
        }
        // HashMap通过不存在的键所获取的值为null
        Integer e = map.get("e");
        System.out.println("e: " + e); // null
    }

    /**
     * 第二种entrySet()方法
     * 	第二种利用Map集合中的键值对映射关系获取
     * 	Map接口中有一个方法entrySet(),获取键值对的映射关系对象Entry,将这个对象Entry存储到了Set集合
     * 	迭代Set集合，获取出来的Set集合中存储的是映射关系对象Entry
     * 	通过关系对象的方法 getKey  getValu
     */
    @Test
    public void entrySet() {
        Map<String, Integer> map = new HashMap<>();

        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        // map.put("Four", 4);
        // map.put("Five", 5);
        // map.put("six", 6);
        // map.put("seven", 7);
        // map.put("eight", 8);
        // map.put("nine", 9);
        // map.put("ten", 10);

        // 调用Map接口的entrySet()方法，获取键值对映射关系对象Entry，存储到Set集合
        Set<Map.Entry<String, Integer>> set = map.entrySet();
        // 迭代器，迭代Set集合
        Iterator<Map.Entry<String, Integer>> iterator = set.iterator();
        while (iterator.hasNext()) {
            // iterator.next()取出来的是什么，映射关系对象map.entry()
            Map.Entry<String, Integer> entry = iterator.next();
            /*
            // 利用键值对映射关系对象中的方法，getKey() getValue()
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + "==" + value);
            */
            if ("one".equals(entry.getKey())) {
                // set.remove(entry.getKey()); // 正常执行
                // iterator.remove(); // 正常执行

                // 并发修改异常
                // Exception in thread "main" java.util.ConcurrentModificationException(并发修改异常)
                // 原因:使用集合的方法修改了集合的长度，而迭代器不知道，因此出现并发修改异常。在迭代器的过程中，不要使用集合的方法，改变集合的长度。
                // map.remove(entry.getKey()); // 抛异常
                //map.put("aa", 11); // 抛异常
            }
        }
        System.out.println(map);
        System.out.println(set);
    }

    /**
     * @Description org.apache.commons.collections4
     */
    @Test
    public void iteratorMap() {
        IterableMap<String, Object> iterableMap = new HashedMap<>();

        // 设置
        iterableMap.put("One", "");
        iterableMap.put("Two", 2);
        iterableMap.put("Three", 3);
        iterableMap.put("Four", 4);
        iterableMap.put("Five", 5);
        iterableMap.put("six", 6);
        iterableMap.put("seven", 7);
        iterableMap.put("eight", 8);
        iterableMap.put("nine", 9);
        iterableMap.put("ten", 10);
        System.out.println(iterableMap);
        // 迭代
        MapIterator<String, Object> iterator = iterableMap.mapIterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = iterator.getValue();
            // 无序
            System.out.println("key = " + key + "，value = " + value);
        }
    }

    /**
     * HashMap类
     * 底层也是哈希表，允许存储null值，null键
     * 不同步，线程不安全，执行效率高
     * 保证：存储到HashMap集合中的键，唯一性
     * 自定义对象，保证唯一性，hashCode equals方法
     * HashMap存储自定义对象，当作键，两种方式获取
     */
    @Test
    public void hashMapTest() {
        /**
         * 数据结构，学校容器，老师容器，学生容器
         * 老师容器存储，001，张三；002 李四；
         * 学生容器存储，0011，张小三；0022 李小四
         */
        // 定义学校集合
        Map<String, Object> school = new HashMap<>();
        // 定义老师集合
        Map<String, Object> teacher = new HashMap<>();
        // 定义学生集合
        Map<String, Object> student = new HashMap<>();

        // 存储老师数据
        teacher.put("001", "张三");
        teacher.put("002", "李四");
        // 存储学生数据
        student.put("0011", "张小三");
        student.put("0022", "张小四");
        // 将老师和学生集合存储到学校集合
        school.put("teacher", teacher);
        school.put("student", student);
        // 打印一下
        System.out.println(school);
        // 迭代器遍历
        // 获取学校大集合，键值对关系，存储到Set集合
        Set<Map.Entry<String, Object>> entries = school.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            // it.next()获取到的是大集合学校的键值对关系对象
            Map.Entry<String, Object> next = iterator.next();
            // 通过关系对象 getKey班级名字   getValue是一个map集合
            String key = next.getKey();
            Map<String, Object> map = (Map<String, Object>) next.getValue();
            // 将小集合map的键值对关系对象获取，存储Set集合
            Set<Map.Entry<String, Object>> entrySet = map.entrySet();
            Iterator<Map.Entry<String, Object>> entryIterator = entrySet.iterator();
            while (entryIterator.hasNext()) {
                // entryIterator.next获取到的是小集合的键值对关系
                Map.Entry<String, Object> entry = entryIterator.next();
                //获取小集合键值对，键学号，值是姓名
                String entryKey = entry.getKey();
                Object value = entry.getValue();
                System.out.println(key + "..." + entryKey + "..." + value);
            }
        }
    }

    /**
     *  Java核心技术 卷I 集合 395
     */
    @Test
    public void mapTest() {
        Map<String, Person> personMap = new HashMap<>();
        personMap.put("144-24-5464", new Person("Amy Lee"));
        personMap.put("567-24-2546", new Person("Harry Hacker"));
        personMap.put("157-62-7935", new Person("Gary Cooper"));
        personMap.put("456-62-5527", new Person("Francesca Cruz"));
        // 打印
        System.out.println(personMap);
        // 移除
        personMap.remove("567-24-2546");
        // 替换
        personMap.put("456-62-5527", new Person("Francesca Miller"));
        // 获取值
        System.out.println(personMap.get("157-62-7935"));
        // 迭代
        personMap.forEach((k, v) -> System.out.println("key=" + k + ", value=" + v));
    }

    /**
     * 2.	LinkedHashMap
     * 是HashMap的子类，特点保证键的顺序，存储的键，怎么存的，怎么取出来，不同步执行效率高
     * 底层结构基于哈希表的链表实现
     */
    @Test
    public void linkedHashMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("adfa", 1);
        map.put("fads", 2);
        map.put("qreq", 3);
        System.out.println(map);
    }

    /**
     * 3.	TreeMap集合
     * 底层数据结构红黑树，自然平衡二叉树
     * 线程不同步的，不安全，执行效率高
     * 存储键，对键进行自然顺序的排序，要求的作为键的对象，必须具备自然顺序
     * 或者自定义比较器
     * TreeMap使用方式，原来的TreeSet是一致的，保证对象的自然顺序，或者自定义比较器就可以了，
     * 程序代码，存储的时候和TreeSet几乎一致。Set使用add,Map使用put
     * <p>
     * HashMap：使用hashcode和equals实现去重的。
     * TreeMap：依靠Comparable或Comparator来实现key的去重。
     */
    @Test
    public void treeMap() {
        Map<Person, String> map = new TreeMap<Person, String>();
        map.put(new Person("lisi", 18), "摩纳哥");
        map.put(new Person("zhangsan", 28), "马尔代夫");
        map.put(new Person("wangwu", 19), "迪拜");
        map.put(new Person("zhaoliu", 38), "巴黎");
        map.put(new Person("wangcai", 24), "北京");
        map.put(new Person("xiaoqiang", 56), "塞班岛");
        Set<Map.Entry<Person, String>> set = map.entrySet();
        Iterator<Map.Entry<Person, String>> it = set.iterator();
        while (it.hasNext()) {
            Map.Entry<Person, String> me = it.next();
            Person p = me.getKey();
            String value = me.getValue();
            System.out.println(p + "..." + value);
        }
    }

    /**
     * 如果
     */

    /**
     * 统计字符串中单个字符的出现次数
     * // 可以使用TreeMap
     */
    @Test
    public void treeMapTest() {
        String s = "asrrfegrr4gw34g";
        // 定义一个TreeMap集合，键为字符，值为字符出现的次数
        Map<Character, Integer> map = new TreeMap<>();
        // 字符串转字符数组遍历
        char[] chars = s.toCharArray();
        // 遍历字符数组
        for (char c : chars) {
            // 遍历到的字符作为集合的键去获取值，如果没有就存储，并记录次数，如果有，记录次数加一
            Integer integer = map.get(c);
            if (integer == null) {
                // 不存在
                map.put(c, 1);
            } else {
                // 存在
                map.put(c, ++integer);
            }
        }
        // 打印输出
        System.out.println(map);
    }

    /**
     * 4.	Hashtable
     * JDK1.0出现的集合，存储键值对，底层数据结构也是哈希表，Hashtable JDK1.2后实现Map接口。线程安全，不允许存null值，null键。除了线程，null，和HashMap是一致的。已经被HashMap取代。但是Hashtable虽然不用了，但是有一个子类，至今活跃在开发的舞台中。Properties是Hashtable的子类
     * 5.	Properties
     * 存储键值对的集合，线程安全，IO流配合使用，这个集合的泛型，定好了，键值的泛型都是String
     * Prperties类的两个自己的特性方法
     * 	setProperty(String key,String value)将键值对存储到集合
     * 	String getProperty(String key)根据键，获取值
     */
    @Test
    public void properties() {
        // 系统配置
        // System.out.println(System.getProperties());

        // set/get Properties
        Properties properties = new Properties();
        properties.setProperty("a", "A"); // HashTable.put()
        properties.setProperty("b", "B");
        System.out.println(properties.getProperty("aa")); // null，获取不到返回null
        System.out.println(properties); // {b=B, a=A}
    }

    @Test
    public void propertiesIo() {
        // 使用load方法，让集合和IO关联起来
        Properties properties = new Properties();
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        try {
            fileReader = new FileReader("/Users/wdq/Documents/test.config");
            properties.load(fileReader);
            System.out.println(properties);
            /*
            // 只获取一个值
            String value = properties.getProperty("name");
            System.out.println(value);
            properties.setProperty("address", "shenzhen");
            System.out.println(properties);
            */

            /*
            // 使用load方法，把文本中的键值对，存储到集合，修改集合，将修改后的键值对，store写回文本文件
            properties.setProperty("name", "lisi");
            properties.setProperty("age", "19");
            System.out.println(properties);
            // 输出流
            fileWriter = new FileWriter("/Users/wdq/Documents/test.config");
            // 集合的store()方法
            properties.store(fileWriter, "修改文件"); // 中文注释转成Unicode编码
            */

            // 集合中的list方法
            // 创建打印流对象
            printWriter = new PrintWriter(System.out);
            // 调用集合的list方法，传递打印流
            properties.list(printWriter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileReader != null) {
                        fileReader.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (printWriter != null) {
                            printWriter.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 7.	JDK1.5后的新特性
     * 增强for循环，出现，简化了传统意义上的for循环，遍历数组或者集合
     * 如果你的程序，不需要改变数组或者集合，只需要遍历的话，优先考虑使用增强for循环
     * 格式:
     * for(数据类型 变量 : 集合或者数组){
     * 输出(变量);
     * }
     * 弊端：增强的for循环，只能遍历，只能看，不能摸
     */
    @Test
    public void foreachInterator() {
        // 遍历数组
        int[] ints = new int[]{1, 3, 5, 7};
        for (int i : ints) {
            System.out.println(i);
        }
        System.out.println("========忧郁的分割线======");
        String[] str = {"www", "baidu", "com"};
        for (String s : str) {
            //遍历的时候，变量s,能否调用String类的方法
            System.out.println(s.substring(0, 1));
        }
        System.out.println("========遍历集合==========");
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("bbc");
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("========忧郁的分割线============");
        Set<Integer> set = new HashSet<>();
        set.add(123);
        set.add(789);
        for (Integer i : set) {
            System.out.println(i);
        }
        System.out.println("=========遍历Map集合=========");
        //增强for能遍历Map吗，可以，但是不能直接遍历，间接遍历
        Map<String, String> map = new HashMap<>();
        map.put("a", "qqq");
        map.put("b", "www");
        //使用for循环，间接遍历Map集合，
        //Set<String> set = map.keySet();
        for (String s : map.keySet()) {
            // String value = map.get(s);
            System.out.println(s + "..." + map.get(s));
        }
        System.out.println("========忧郁的分割线============");
        //Set<Map.Entry<String, String>> set = map.entrySet();
        for (Map.Entry<String, String> me : map.entrySet()) {
            System.out.println(me.getKey() + "..." + me.getValue());
        }
    }

    /**
     * 8.	JDK1.5后的新特性
     * 可变参数，方法的参数，是可变的
     * static void a()
     * 可变参数的数据类型，必须统一
     * 方法名(数据类型...变量名)
     * 多个参数，可变参数，必须写在最后面
     * 方法中的可变参数，只能写一个
     */
    @Test
    public void changeParamsTest() {
        System.out.println(changeParams(2, 3, 4, 5));
    }

    private Integer changeParams(int... ints) {
        int sum = 0;
        for (int i : ints) {
            sum += i;
        }
        return sum;
    }

    /**
     * 集合的折半查找
     * int binarySearch(list, index)
     * 查找下标，找不到，返回的数，负数(不是-1)
     */
    @Test
    public void binarySearch() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(5);
        list.add(8);
        list.add(9);
        // Collections集合工具类
        // 没找找到，返回(-(插入点)-1)
        // 无序集合不能使用折半查找
        int i = Collections.binarySearch(list, 1);
        System.out.println(i);
    }

    public static void main(String[] args) {
        // HashMap 和 Hashtable 对Null key 和Null value的支持
        Map<Object, Object> hashMap = new ConcurrentHashMap<>();
        hashMap.put("null", null);
        hashMap.put(null, "1");
        System.out.println(hashMap);
        Map<Object, Object> hashtable = new Hashtable<>();
        // hashtable.put("null", null);
        hashtable.put(null, "1");
        System.out.println(hashtable);
    }
}
