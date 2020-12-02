package com.icore.winvaz.javase.basic.collection;

import com.icore.winvaz.javase.Person;
import org.junit.jupiter.api.Test;

import java.util.BitSet;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Set集合
 * Set集合派系的特点：
 * 	不允许存储重复的元素
 * 	没有下标
 * 	无序的集合，存储和取出的顺序不一定的
 * Set接口中的方法，和Collection接口中的方法一致
 */
public class SetTest {
    /**
     * Set的add方法
     */
    @Test
    public void setAdd() {
        // 定义Set集合,存储几个字符串
        Set set = new HashSet();

        set.add("abc1");
        set.add("abc1");
        set.add("abc2");
        set.add("abc3");
        set.add("abc4");
        set.add("abc4");
        // HashSet允许存储null
        set.add(null);

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**=====================================*/
    /**
     * HashSet类的特点
     * 	底层数据结构哈希表
     * 	HashSet本身没有功能，功能来自于HashMap
     * 	HashSet调用的是HashMap的功能
     * 	无序的
     * 	允许存储null
     * 	线程不安全，不同步，执行效率高
     */
    /**
     * 10.	哈希值的目的为了集合
     * 每一个对象，在建立的时候存储在内存堆中，JVM对每一个对象，
     * 根据底层的一个算法，哈希算法，计算出来一个十进制数,十进制就是哈希值。
     * 程序人员开发时候的一个参考，仅此而已
     * Object类，定义了一个方法  public int hashCode()计算对象的哈希值的，
     * 所有的子类都有这个哈希值.出现是为了哈希表而来的，本身，没有地址意义
     * HashSet，存储的其实是对象的哈希值
     * HashSet集合的存储过程，存储对象的时候，先调用对象的hashCode方法，
     * 获取该对象的哈希值(十进制数)，HashSet看看这个数以前存储过没有，
     * 如果没有存储过，就将这个哈希值存储到自己的哈希表中
     * 实现去掉重复的元素，让姓名和年龄相同的对象，具有相同的哈希值。
     * 覆盖hashCode方法
     * 存储到HashSet中的对象，保证对象的唯一性，必须重写hashCode()和equals()方法
     */
    /**
     * 如果两个对象具有相同的哈希值，两个对象进行equals比较，一定返回true吗 No
     * 如果两个对象进行equals比较，返回真，两个对象具有相同的哈希值吗,必须相同
     */
    @Test
    public void hashCodeTest() {
        String s = "abc";
        System.out.println(s.hashCode());

        String s1 = new String("abc");
        System.out.println(s == s1); // false 比较的是真实地址，而哈希值不是地址，所以哈希值一样，==也不一样相等
        System.out.println(s1.hashCode());
    }

    /**
     * Set存储自定义对象
     * 并且用迭代器取出
     * * 存储Person
     */
    @Test
    public void hashSetTest() {
        HashSet hashSet = new HashSet();

        hashSet.add(new Person("zhangsan", 12));
        hashSet.add(new Person("zhangsan", 12));
        hashSet.add(new Person("lisi", 13));
        hashSet.add(new Person("wanglaowu", 14));
        hashSet.add(new Person("wanglaowu", 14));
        hashSet.add(new Person("yanxiaoliu", 15));

        Iterator iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            Person person = (Person) iterator.next();
            System.out.println(person);
        }
    }

    /**
     * 12.	LinkedHashSet类
     * 特点：有序的，基于哈希表的链表，线程不安全的，执行效率高
     */
    @Test
    public void linkedHashSetTest() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();

        linkedHashSet.add("abc1");
        linkedHashSet.add("abc2");
        linkedHashSet.add("abc2");
        linkedHashSet.add("abc3");
        linkedHashSet.add("abc3");
        linkedHashSet.add("abc4");

        Iterator iterator = linkedHashSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * 13.	TreeSet类
     * 底层数据结构二叉树
     * 线程不安全的，执行效率高
     * 对存储到集合的对象，进行排序(对象的自然顺序 0-9 a-z)
     * JDK中，提供了另外的一种排序方式，比较器排序，比较器接口java..util.Comparator
     * <p>
     * 元素的比较
     * Comparable：自己和自己相比较，自营性质的比较器。比较方法compareTo()
     * Comparator：第三方比较器，平台性质的比较器。比较方法compare()
     * <p>
     * 约定成俗，小于的情况返回-1，等于的情况返回0， 大于的情况返回1
     */
    @Test
    public void treeSetTest() {
        TreeSet treeSet = new TreeSet();
        // 对存储到集合的对象，进行排序(对象的自然顺序 0-9 a-z)
        /*
        treeSet.add("fdsa");
        treeSet.add("fdasdfsa");
        treeSet.add("wer");
        treeSet.add("adsf");
        treeSet.add("pwer");
        */
        treeSet.add(6543);
        treeSet.add(3245);
        treeSet.add(7654);
        treeSet.add(123);

        Iterator iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * TreeSet存储自定义对象
     * 将自定义的对象Person存储到TreeSet集合，出现了类型转换异常
     * * ClassCastException :原因，Person不能被转成java.lang.Comparable
     * * TreeSet中，需要对Person进行排序，可是你的Person对象，不具备自然顺序。
     * * 可以让Person具备自然顺序，实现Comparable接口.
     */
    @Test
    public void treeSetAddPerson() {
        /**
         * 返回值是整型，它是先比较对应字符的大小(ASCII码顺序)，
         * 如果第一个字符和参数的第一个字符不等，结束比较，返回他们之间的差值，
         * 如果第一个字符和参数的第一个字符相等，则以第二个字符和参数的第二个字符做比较，
         * 以此类推,直至比较的字符或被比较的字符有一方结束。
         *
         * 如果参数字符串等于此字符串，则返回值 0；
         * 如果此字符串小于字符串参数，则返回一个小于 0 的值；
         * 如果此字符串大于字符串参数，则返回一个大于 0 的值。
         */
        /*
        String s1 = "a";
        String s2 = "b";
        //String类中，有一个方法，可以获取到字符串的字典顺序
        // 调用者小，返回负数，调用者大，返回正数，相等，返回0
        int i = s1.compareTo(s2); // -1
        System.out.println(i);

        Integer x1 = 1;
        Integer x2 = 2;
        int i1 = x1.compareTo(x2);
        System.out.println(i1); // -1
        */

        TreeSet treeSet = new TreeSet();

        treeSet.add(new Person("zhangsan", 12));
        treeSet.add(new Person("zhangsan", 12));
        treeSet.add(new Person("lisi", 13));
        treeSet.add(new Person("wanglaowu", 14));
        treeSet.add(new Person("wanglaowu", 14));
        treeSet.add(new Person("yanxiaoliu", 15));
        System.out.println(treeSet);

        TreeSet<Person> people = new TreeSet<>(Comparator.comparing(Person::getAge));
        people.addAll(treeSet);
        System.out.println(people);

        Iterator iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            Person person = (Person) iterator.next();
            System.out.println(person);
        }
    }

    /**
     *  Java核心技术 卷I 集合 426页
     *  位集合
     */
    @Test
    public void sieve() {
        // 从200W个数中找出素数(只能被1和它自己本身整除的数)
        int n = 2000000;
        long start = System.currentTimeMillis();
        BitSet bitSet = new BitSet(n + 1);
        int count = 0;
        int i;
        for (i = 2; i <= n; i++) {
            bitSet.set(i);
        }
        i = 2;
        while (i * i <= n) {
            if (bitSet.get(i)) {
                count++;
                int k = 2 * i;
                while (k <= n) {
                    bitSet.clear(k);
                    k += i;
                }
            }
            i++;
        }
        while (i <= n) {
            if (bitSet.get(i)) {
                count++;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(count + " primes");
        System.out.println((end - start) + " milliseconds");
    }
}
