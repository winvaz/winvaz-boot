package com.icore.winvaz.javase.basic.collection;

import com.icore.winvaz.javase.Person;
import com.icore.winvaz.javase.basic.coretechnology.Employee;
import com.icore.winvaz.javase.basic.coretechnology.Manager;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;

import javax.sound.midi.Soundbank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * 7.	List 接口派系
 * List接口派系的特点：
 * 	有序的:存储的时候，和取出的时候顺序一致
 * 	List派系是一个有索引的，具有下标
 * 	允许存储重复元素
 * List接口中的特有方法
 * 	add(int index, Object obj)在指定的索引上，插入元素
 * 	Object get(int index)指定下标，获取这个下标上的元素
 * 	Object remove(int index)指定下标，移除这个下标上的元素，返回删除之前的
 * 	Object set(int index ,Object obj)执行下标，修改这个下标上的元素，返回修改之前的
 * 	List subList(int start,int end)获取集合中的一部分，包含头，不包含尾
 * <p>
 * 8.	List接口派系的特有迭代器ListIterator
 * List接口中定义了一个方法  listIterator,所有的子类都具备的，返回一个接口的实现类的对象，接口是就ListIterator，List的特有迭代器
 * 使用List特有迭代器，可以实现在迭代的过程中，对集合中的元素，进行添加，修改，和删除。
 * ListIterator接口方法
 * add(Object obj)使用迭代器，遍历集合的过程中，添加
 * set( Object obj)使用迭代器，遍历集合的过程中，修改
 * boolean hasPrevious()  看成是hasNext()
 * Object previous() 看成是next()
 */
public class ListTest {

    /**
     * 6.	迭代器
     *  * 什么是迭代器：取出集合中存储的对象的方式，接口Collection中，定义了一个方法，是集合中所有的子类都具备的， Iterator iterator(),返回一个Iterator类型的对象
     *  * 由于每个集合存储对象的方式不同，取出方法也不同
     *  * iterator方法返回的是一个接口的类型，接口本身不能创建对象的，返回的肯定是接口的实现类对象,
     *  迭代器的原理，其实就是每个集合中的内部类的事情，集合中的内部类，实现了Iterator接口，使用iterator方法，获取的就是内部类的对象
     *  * Iterator接口中的三个方法
     *  * 	boolean hasNext() 判断集合中还没有没有下一个被取出的元素，有返回true
     *  * 	Object next() 获取集合中的元素 ，看成是arr[i]
     *  * 	void remove() 移除遍历到的集合中的元素
     *  * 实现迭代器取出集合对象的三个步骤
     *  * 	通过集合对象的iterator方法，获取迭代器对象，返回的是一个接口的实现类
     *  * 	使用迭代器对象的hasNext()方法，判断集合中还有没有对象可以被取出
     *  * 	使用迭代器对象的next()方法直接获取存储到集合中的对象
     *  * 迭代器是获取存储到集合的对象的通用方式
     *  * 注意，如果迭代器已经获取完了，再次获取，出现没有元素被取出异常
     *  * java.util.NoSuchElementException
     *  * 注意，迭代过程中，出现了并发修改异常ConcurrentModificationException
     *  。出现异常的原因：使用集合的方法修改了集合的长度，而迭代器不知道，因此出现并发修改异常。在迭代的过程中，不要使用集合的方法，改变集合长度
     *  * 一次迭代中，不能出现多次next方法，否则可能出现异常，出现数据错乱
     */
    /**
     * 定义方法，实现迭代器取出集合中存储的对象
     */
    @Test
    public void iteratorTest() {
        // 创建集合对象，并存储一些字符串
        Collection collection = new ArrayList();
        collection.add("a");
        collection.add("b");
        collection.add("c");
        collection.add("d");

        // 用迭代器获取集合中的对象
        // 第一步，iterator方法是集合所有子类都有的，调用集合的这个方法，获取迭代对象。
        Iterator iterator = collection.iterator();
        /*
        iterator.forEachRemaining(str -> {
            System.out.print(str + ",");
        });
        System.out.println("=======1.8 default first========");
        */
        // 第二步，使用迭代器的hasNext()方法，判断集合中是否还有对象可以被取出。
        // hasNext()方法返回的是布尔值，重复获取动作，写成循环的方式
        while (iterator.hasNext()) {
            // 如果hasNext()方法返回true，还有下一个被取出
            // 第三步，调用迭代器的next()方法获取对象
            Object next = iterator.next();
            System.out.println(next);

            // 并发修改异常
            // Exception in thread "main" java.util.ConcurrentModificationException(并发修改异常)
            // 原因:使用集合的方法修改了集合的长度，而迭代器不知道，因此出现并发修改异常。在迭代器的过程中，不要使用集合的方法，改变集合的长度。
            /*
            if ("b".equals(iterator.next())) {
                collection.add("b1");
            }
            */
        }
        System.out.println(collection);

        // 注意，如果迭代器已经获取完了，再次获取，出现没有元素被取出异常
        // Object next = iterator.next();
        // System.out.println(next); // java.util.NoSuchElementException
        System.out.println("=============");

        // 采用for循环来实现迭代器取出
        for (Iterator iterator1 = collection.iterator(); iterator1.hasNext(); ) {
            System.out.println(iterator1.next());
        }
        System.out.println("=======1.8 default second========");
        // Lambda
        // forEachRemaining()，第一次调用有输出，第二次调用什么也没有，因为没有下一个元素被取出
        iterator.forEachRemaining(str -> {
            System.out.println(str);
        });
    }

    /**
     * List的ListIterator方法
     * 迭代中，添加、修改集合中的元素
     */
    @Test
    public void listIterator() {
        List list = new ArrayList();
        list.add("abc1");
        list.add("abc2");
        list.add("abc3");
        list.add("abc4");
        list.add("abc5");

        // for循环倒序输出
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.println(list.get(i));
        }
        System.out.println("=============");
        // 使用List接口中的方法ListIterator()获取List的特有迭代器
        ListIterator listIterator = list.listIterator();
        // 正向遍历
        while (listIterator.hasNext()) {
            if ("abc5".equals(listIterator.next())) {
                // listIterator.add(E e)添加
                //listIterator.add("abc6");

                // listIterator.set(E e)修改
                listIterator.set("abc55");
            }
        }
        System.out.println(list);
        System.out.println("=======逆向遍历=======");
        // 逆向遍历
        // 前提是正向遍历一次
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }
    }

    @Test
    public void arrayToCollectionTest() {
        String[] strings = new String[3];
        strings[0] = "one";
        strings[1] = "two";
        strings[2] = "three";

        /** 参考《码出高效》第163页
         * Arrays.asList()体现的是适配器模式，后台的数据仍是原有数组，asList的返回对象是一个Arrays的内部类对象。
         * 它并没有实现集合个数的相关修改方法。
         * 把数组转成集合，不能使用其修改集合相关方法(add/remove/clear)会抛出UnsupportOperationException异常
         */
        /*
        List<String> list = Arrays.asList(strings);
        // 修改转换之后的集合，成功的把第一个元素"one"改成"oneList"
        list.set(0, "oneList");
        // 运行结果也是oneList，数组的值随之改变
        System.out.println(list); // [oneList, two, three]

        // 这里是重点，以下三行编译正确，但都会抛出运行时异常
        // list.add("four"); // java.lang.UnsupportedOperationException
        // list.remove(2);
        // list.clear();
        */

        /**
         * 数组转集合时，建议使用java.util.ArrayList直接创建一个对象，参数就是Arrays.asList返回的不可变集合
         */
        List<String> lists = new ArrayList<>(Arrays.asList(strings));
        lists.set(0, "oneList");
        System.out.println(lists); // [oneList, two, three]

        // 修改集合
        lists.add("four");
        lists.remove(2);
        lists.clear();
        System.out.println(lists); // []
    }

    @Test
    public void collectionToArrayTest() {
        List list = new ArrayList();
        list.add("one");
        list.add("two");
        list.add("three");

        // 泛型丢失，无法使用String[]接受无参方法返回的结果
        Object[] array1 = list.toArray(); // 泛型丢失，不建议使用

        // array2数组长度小于元素个数
        String[] array2 = new String[2];
        list.toArray(array2);
        System.out.println(Arrays.asList(array2)); // [null, null]

        // array3数组长度等于元素个数
        String[] array3 = new String[3];
        list.toArray(array3);
        System.out.println(Arrays.asList(array3)); // [one, two, three]
    }

    @Test
    public void collectionToArraySpeedTest() {
        final int COUNT = 100 * 100 * 100;
        List<Double> list = new ArrayList<>(COUNT);
        // 构造一个100万个元素的测试集合
        for (int i = 0; i < COUNT; i++) {
            list.add(i * 1.0);
        }
        long start = System.nanoTime();

        Double[] notEnoughArray = new Double[COUNT - 1];
        list.toArray(notEnoughArray);

        long middle1 = System.nanoTime();

        Double[] equalArray = new Double[COUNT];
        list.toArray(equalArray);

        long middle2 = System.nanoTime();

        Double[] doubleArray = new Double[COUNT * 2];
        list.toArray(doubleArray);

        long end = System.nanoTime();

        long notEnoughArrayTime = middle1 - start;
        long equalArrayTime = middle2 - middle1;
        long doubleArrayTime = end - middle2;

        // 测试有一定差异，但数组容量等于集合大小时，运行总是最快的(《码出高效》第168页)，空间消耗也是最少的
        // 建议使用集合的toArray(T[] array)，转为数组时，注意需要传入类型完全一样的数组，并且它的容量大小为list.size()
        System.out.println("数组容量小于集合大小：notEnoughArrayTime：" + notEnoughArrayTime / (1000.0 * 1000.0) + "ms"); // 7
        // .456019ms
        System.out.println("数组容量等于集合大小：equalArrayTime：" + equalArrayTime / (1000.0 * 1000.0) + "ms"); // 8.159193ms
        System.out.println("数组容量是集合的两倍：doubleArrayTime：" + doubleArrayTime / (1000.0 * 1000.0) + "ms"); // 7.687235ms

    }

    /**
     * list subList(int start,int end)获取集合中的一部分
     * * 返回一个的新的集合
     * <p>
     * 主列表的任何关于元素个数的修改操作都会导致子列表的"增删改查"抛出java.util.ConcurrentModificationException异常
     * subList子列表无法序列化，子列表的修改会导致主列表的修改
     * subList()方法返回的是内部类SubList的对象，SubList类是ArrayList的内部类。SubList没有实现序列化接口，无法网络传输
     */
    @Test
    public void subList() {
        List list = new ArrayList();
        list.add("abc1");
        list.add("abc2");
        list.add("abc3");
        list.add("abc4");
        list.add("abc5");
        System.out.println("截取之前：" + list);
        // 返回一个新集合
        List subList = list.subList(0, 3);
        System.out.println("截取之后：" + subList); // 截取之后：[abc1, abc2, abc3]

        // 测试fail-fast
        // 以下三行不注释则子列表的"增删改查"抛出java.util.ConcurrentModificationException异常
        // list.remove(0);
        // list.add("abc7");
        // list.clear();

        // 下方四行全部能够正确执行
        subList.clear();
        subList.add("abc8");
        subList.add("abc9");
        subList.remove(0); // abc8

        // 正常遍历
        for (Object obj : subList) {
            System.out.println(obj); // abc9
        }

        // 子列表修改导致主列表也被改动
        System.out.println(list); // [abc9, abc4, abc5]
    }

    /**
     * COW(奶牛)家族
     */
    @Test
    public void copyOnWrite() {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");
        /*
        // foreach遍历
        for (String str: list) {
            if ("two".equals(str)) {
                // 执行remove这个元素后，所有元素往前拷贝，size = size - 1 == 2，这是cursor也等于2
                // 在执行hasNext()时，结果为false，退出循环。
                list.remove(str);
            }
        }
        // 三个元素返回[one, three]，参考《码出高效》182页
        // 集合遍历时维护一个初始值为0的游标cursor，从头到尾地进行扫描，当cursor等于size，退出遍历
        // 四个元素抛异常：java.util.ConcurrentModificationException
        System.out.println(list);
        */
        /**
         * fail-fast机制
         * 当前线程会维护一个计数比较器，即expectedModCount，记录已经修改的次数。
         * 在进入遍历前，会把实时修改次数modCount赋值给expectedModCount。
         * 如果这两个数不相等，则抛出异常，java.util下的所有集合类都是fail-fast。
         * 而concurrent包中的集合类都是fail-safe。
         *
         */

        // 多线程并发遍历加锁
        /*ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()) {
            synchronized (this) {
                String next = iterator.next();
                if ("two".equals(next)) {
                    iterator.remove();
                }
            }
        }*/
        /**
         *  或者使用并发容器CopyOnWriteArrayList替代ArrayList
         *  COW(奶牛)家族，即Copy-On-Write。
         *  它是并发的一种新思路，实行读写分离。
         *  注意：尽量设置合理地容量初始值。
         *       使用批量添加或删除的方法
         */
        // 或者使用并发容器CopyOnWriteArrayList替代ArrayList
        List<Long> des = new CopyOnWriteArrayList<>(); // 11178.328757 ms
        List<Long> src = new ArrayList<>(); // 20.811054 ms
        long start = System.nanoTime();
        for (int i = 0; i < 20 * 10000; i++) {
            src.add(System.nanoTime());
        }
        // 要初始化COW集合，建议先将数据填充到ArrayList，再把ArrayList当成COW的参数
        // des.addAll(src);
        long end = System.nanoTime();
        long time = end - start;
        System.out.println(time / (1000.0 * 1000.0) + " ms");
    }

    /**
     * Object set(int index,Object obj)指定下标，修改下标上的元素
     * * 返回修改之前的
     */
    @Test
    public void set() {
        List list = new ArrayList();
        list.add("abc1");
        list.add("abc2");
        list.add("abc3");
        System.out.println("修改之前：" + list);
        // 返回修改之前的元素
        Object set = list.set(1, "QQ");
        System.out.println(set); // abc2
        System.out.println("修改之后：" + list); // 修改之后：[abc1, QQ, abc3]
    }

    /**
     * List的remove(int index),移除下标上的元素
     * 返回移除之前的元素
     */
    @Test
    public void listRemove() {
        List list = new ArrayList();
        list.add("abc1");
        list.add("abc2");
        list.add("abc3");
        System.out.println("移除之前：" + list);
        // 返回移除之前的元素
        Object remove = list.remove(1);
        System.out.println(remove); // abc2
        System.out.println("移除之后：" + list);
    }

    /**
     * Object get(int index)传递一个下标，返回这个下标上的元素
     * *既然可以使用下标，访问list集合，用遍历数组一样的方式，遍历List集合)
     */
    @Test
    public void listGet() {
        List list = new ArrayList();
        list.add("abc1");
        list.add("abc2");
        list.add("abc3");
        // for循环，不适用迭代器，get方法实现遍历
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        // 倒序输出
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.println(list.get(i));
        }
    }

    /**
     * add(int index, E element)指定位置，插入元素
     */
    @Test
    public void add() {
        List list = new ArrayList();
        list.add("abc1");
        list.add("abc2");
        System.out.println(list);
        list.add(1, "QQ");
        System.out.println(list);
        // Exception in thread "main" java.lang.IndexOutOfBoundsException(角标越界异常): Index: 10, Size: 3
        // list.add(10, "XO");
    }

    /**
     * 定义一个方法，实现将Person对象存储到ArrayList,并迭代器和for取出
     */
    @Test
    public void arrayListAddPerson() {
        ArrayList arrayList = new ArrayList();

        arrayList.add(new Person("张三", 15));
        arrayList.add(new Person("李四", 25));
        arrayList.add(new Person("王老五", 30));
        arrayList.add(new Person("燕小六", 35));
        arrayList.add(new Person("田七", 40));

        LinkedList linkedList = new LinkedList();

        linkedList.add(new Person("张三", 15));
        linkedList.add(new Person("李四", 25));
        linkedList.add(new Person("王老五", 30));
        linkedList.add(new Person("燕小六", 35));
        linkedList.add(new Person("田七", 40));

        // 获取地带器对象，用集合的iterator方法
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            //System.out.println(iterator.next());
            // 定义一个Person变量，接受iterator.next()返回值
            Person person = (Person) iterator.next();
            System.out.println(person.getName() + "==" + person.getAge());
        }
        System.out.println("=============");
        // for循环
        for (int i = 0; i < arrayList.size(); i++) {
            Person person = (Person) arrayList.get(i);
            System.out.println(person.getName() + "====" + person.getAge());
        }
    }

    /**
     * 去除ArrayList重复元素
     */
    @Test
    public void removeRepeat() {
        List oldList = new ArrayList();
        oldList.add("123");
        oldList.add("123");
        oldList.add("223");
        oldList.add("223");

        /*
        // 定义新集合，最终存储的是去掉老集合的元素
        List newList = new ArrayList();
        // 迭代老集合
        Iterator iterator = oldList.iterator();
        while (iterator.hasNext()) {
            // 获取老集合的元素，获取一个到新集合中判断。
            Object object = iterator.next();
            // 新的集合，调用contains()方法，判断，object是否存在
            if (!newList.contains(object)) {
                newList.add(object);
            }
        }
        // 打印新集合
        System.out.println(newList);
        System.out.println("==========");
        */
        // 1.8去重
        Object collect = oldList.stream().distinct().collect(toList());
        System.out.println(collect);
        // ArrayList存储自定义对象，去除重复元素
        List pOldList = new ArrayList();

        pOldList.add(new Person("张三", 15));
        pOldList.add(new Person("张三", 15));
        pOldList.add(new Person("李四", 25));
        pOldList.add(new Person("李四", 25));
        pOldList.add(new Person("王老五", 30));
        pOldList.add(new Person("王老五", 30));
        pOldList.add(new Person("燕小六", 35));
        pOldList.add(new Person("燕小六", 35));
        pOldList.add(new Person("田七", 40));
        pOldList.add(new Person("田七", 40));

        List pNewList = new ArrayList();
        Iterator pIterator = pOldList.iterator();
        while (pIterator.hasNext()) {
            Object object = pIterator.next();
            // ArrayList去除重复自定义对象需要重写equals()方法，因为contains()方法底层源码是调用equals()
            if (!pNewList.contains(object)) {
                pNewList.add(object);
            }
        }
        System.out.println(pNewList);
    }
    /**============================================*/

    /**
     * LinkedList底层数据结构链表实现，记录对象地址的方式存储
     * 查询慢，增删快，线程也是不安全的，执行效率高
     */
    /**
     * LinkedList特有的添加方法
     * addFirst()--voi
     * addLast()
     */
    @Test
    public void linkedListAdd() {
        LinkedList linkedList = new LinkedList();

        linkedList.addFirst(121);
        linkedList.addLast("QQ");
        linkedList.addFirst(122);
        linkedList.addFirst(123);
        linkedList.addFirst(124);

        System.out.println(linkedList);

        /**
         * 1.6 之后的新添加方法
         * offerFirst()--boolean
         * offerLast()
         */
        linkedList.offerFirst("wode");
        System.out.println(linkedList);
    }

    /**
     * LinkedList特有的获取方法
     * getFirst()
     * getLast()
     */
    @Test
    public void linkedListGet() {
        LinkedList linkedList = new LinkedList();
        /**
         * 1.6 之后的新获取方法
         * peekFirst()--boolean
         * peekLast()
         *
         * getFirst()与peekFirst()区别
         * getFirst()没有元素返回异常NoSuchElementException
         * 与peekFirst()没有元素返回null
         */
        // System.out.println(linkedList.getFirst()); // java.util.NoSuchElementException
        System.out.println(linkedList.peekFirst()); // null

        linkedList.addFirst(121);
        linkedList.addLast("QQ");
        linkedList.addFirst(122);
        linkedList.addFirst(123);
        linkedList.addFirst(124);

        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.getLast());
    }

    /**
     * LinkedList特有的移除方法
     * removeFirst()
     * removeLast()
     */
    @Test
    public void linkedListRemove() {
        LinkedList linkedList = new LinkedList();
        /**
         * 1.6 之后的新移除方法
         * removeFirst()--boolean
         * pollFirst()
         *
         * removeFirst()pollFirst()区别
         * removeFirst()没有元素返回异常NoSuchElementException
         * pollFirst()没有元素返回null
         */
        // System.out.println(linkedList.removeFirst()); // java.util.NoSuchElementException
        System.out.println(linkedList.pollFirst()); // null

        linkedList.addFirst(121);
        linkedList.addLast("QQ");
        linkedList.addFirst(122);
        linkedList.addFirst(123);
        linkedList.addFirst(124);

        System.out.println(linkedList.removeFirst());
        System.out.println(linkedList.removeLast());
    }

    /**
     *  Java核心技术 卷I 集合 378页
     */
    @Test
    public void linkedListListIterator() {
        List<String> staff = new LinkedList<>();
        staff.add("Tom");
        staff.add("Jerry");
        ListIterator<String> iterator = staff.listIterator();
        // iterator.next();
        // iterator.add("Dog");
        // 查看存取顺序
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next); // Jerry
        }
        iterator.add("Dog");
        System.out.println(staff.size());
        // 倒序输出
        while (iterator.hasPrevious()) {
            String previous = iterator.previous();
            System.out.println(previous);
        }
    }

    @Test
    public void linkedListTest() {
        List<String> a = new LinkedList<>();
        a.add("Amy");
        a.add("Carl");
        a.add("Erica");

        List<String> b = new LinkedList<>();
        b.add("Bob");
        b.add("Doug");
        b.add("Frances");
        b.add("Gloria");

        // 合并集合
        ListIterator<String> aIter = a.listIterator();
        Iterator<String> bIter = b.iterator();
        while (bIter.hasNext()) {
            if (aIter.hasNext()) {
                aIter.next();
            }
            aIter.add(bIter.next());
        }
        System.out.println(a);
        // 从第二个列表中，每间隔一个元素删除一个元素
        bIter = b.iterator();
        while (bIter.hasNext()) {
            bIter.next();
            if (bIter.hasNext()) {
                bIter.next();
                bIter.remove();
            }
        }
        System.out.println(b); // [Bob, Frances]
        // a中移除所有b
        a.removeAll(b);
        System.out.println(a); // [Amy, Carl, Doug, Erica, Gloria]
    }

    /**
     * 使用LinkedList模拟堆栈(先进后出)，队列(先进先出)
     */
    @Test
    public void queueStack() {
        LinkedList list = new LinkedList();
        list.add(123);
        list.add(234);
        list.add(345);
        list.add(456);

        // 实现队列先进先出
        /*
        while (!list.isEmpty()) {
            System.out.println(list.pollFirst());
        }
        System.out.println(list);
        */
        // 实现栈先进后出
        while (!list.isEmpty()) {
            System.out.println(list.pollLast());
        }
        System.out.println(list);
    }

    /**
     *  优先队列
     */
    @Test
    public void priorityQueue() {
        PriorityQueue<LocalDate> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(LocalDate.of(1906, 12, 9)); // G Hopper
        priorityQueue.add(LocalDate.of(1815, 12, 10)); // A. Lovelace
        priorityQueue.add(LocalDate.of(1903, 12, 3));
        priorityQueue.add(LocalDate.of(1916, 6, 22));

        System.out.println("Iterating over elements。。。");
        for (LocalDate date : priorityQueue) {
            System.out.println(date);
        }
        System.out.println("Removing elements。。。");
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.remove());
        }
    }

    @Test
    public void test() {
        List<String> list1 = new ArrayList<String>();
        // list1.add("1");
        list1.add("2");
        list1.add("3");
        // list1.add("5");
        // list1.add("6");

        List<String> list2 = new ArrayList<String>();
        list2.add("2");
        list2.add("3");
        // list2.add("7");
        // list2.add("8");

        // 交集
        List<String> intersection = list1.stream().filter(item -> list2.contains(item)).collect(toList());
        System.out.println("---交集 intersection---");
        intersection.parallelStream().forEach(System.out :: println);

        // 差集 (list1 - list2)
        List<String> reduce1 = list1.stream().filter(item -> !list2.contains(item)).collect(toList());
        System.out.println("---差集 reduce1 (list1 - list2)---");
        reduce1.parallelStream().forEach(System.out :: println);

        // 差集 (list2 - list1)
        List<String> reduce2 = list2.stream().filter(item -> !list1.contains(item)).collect(toList());
        System.out.println("---差集 reduce2 (list2 - list1)---");
        reduce2.parallelStream().forEach(System.out :: println);

        // 并集
        List<String> listAll = list1.parallelStream().collect(toList());
        List<String> listAll2 = list2.parallelStream().collect(toList());
        listAll.addAll(listAll2);
        System.out.println("---并集 listAll---");
        listAll.parallelStream().forEachOrdered(System.out :: println);

        // 去重并集
        List<String> listAllDistinct = listAll.stream().distinct().collect(toList());
        System.out.println("---得到去重并集 listAllDistinct---");
        listAllDistinct.parallelStream().forEachOrdered(System.out :: println);

        System.out.println("---原来的List1---");
        list1.parallelStream().forEachOrdered(System.out :: println);
        System.out.println("---原来的List2---");
        list2.parallelStream().forEachOrdered(System.out :: println);
    }

    /**===============================================*/
    /**
     * 2.	泛型程序设计：对多种不同类型的对象重用
     * JDK1.5版本出现的一种安全机制
     * 格式:
     * 集合类<数据类型> 变量 = new 集合类<数据类型>();
     * 数据类型，指定了这个集合，只能存储这个类型的对象
     * <p>
     *     类型参数。
     * 泛型的出现，将安全问题，由运行时期，提前到了编译时期
     * ArrayList<E>  boolean add(E e)  E:看成是一个变量
     * ArrayList<String>  所有的E都变成了String
     * <p>
     * 好处，减少了代码，使用泛型避免类型的强制转换
     * 泛型什么时候该写泛型：当你用一个类的时候，发现类的右边<>使用泛型了
     * 注意：存储基本数据类型，泛型必须写8个对象包装类
     */
    /**
     * 泛型的本质就是类型参数化，解决不确定具体对象类型的问题。
     * 泛型可以定义类，接口，方法中
     * 约定成俗的符号包括：
     * E（Element）用于集合中的元素
     * T (the Type of object) 表示某个类
     * K 代表Key 用于键值对元素
     * V 代表Value 用于键值对元素
     */
    static <String, T, Alibaba> String get(String string, Alibaba alibaba) {
        return string;
    }

    /**
     * 能不能在非泛型类中，定义泛型方法呢？可以
     * 和在泛型类中定义泛型方法不一样
     * 格式
     * public <T> T demo() {}
     * <T>:表示当前方法为泛型方法
     * T:表示当前方法的返回类型
     */
    public static <T> T genericity(T t) {
        return t;
    }

    @Test
    public void generic() {
        Integer first = 222;
        Long second = 333L;
        /**
         * 解析
         *  get()是一个泛型方法，first并非是java.lang.String类型，而是泛型标识<String>，second指代Alibaba。
         *  get()中其他没有被用到的泛型符号并不会导致编译出错。类名后的T与尖括号内的T相同也是合法的。
         *
         *  1.尖括号里的每个元素都指代一种未知类型。
         *  2.尖括号的位置非常讲究，必须在类名之后或方法返回值之前。
         *  3.泛型在定义处只具备执行Object方法的能力。
         *  4.对于编译之后的字节码指令，其实没有这些花头花脑的方法签名，充分说明了泛型只是一个编写代码时的语法检查。
         */
        System.out.println(get(first, second)); // 222
    }

    /**
     * 泛型保证安全性
     * List集合，存储对象，字符串
     * 迭代这个集合，知道存储的字符串的长度
     * 能不能让我们的集合，只存储字符串，其他对象不存
     */
    @Test
    public void listGenericTest() {
        //集合类<数据类型> 变量 = new 集合类<数据类型>();
        List<String> list = new ArrayList<>();

        // 只能存字符串
        // list.add(1); // 编译就已经报错了
        list.add("ewwegg");
        list.add("ed");
        list.add("bv");
        list.add("wsadcxcv");
        list.add("mnb");
        list.add("false");

        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            //迭代器<String> next()方法返回值也是String类型
            //不需要强制转换
            String s = it.next();
            System.out.println(s.length());
        }
    }

    /**
     * 泛型与集合的联合使用，可以把泛型的功能发挥到极致
     */
    @Test
    public void genericCollection() {
        // 第一段，泛型出现之前的定义方式
        List list = new ArrayList();
        list.add(new Object());
        list.add(new Integer(11));
        list.add(new String("Hello World"));

        // 第二段，把list的引用赋值给objectList.
        // 注意，list与objectList的区别是增加了泛型限制<Object>
        List<Object> objectList = list;
        objectList.add(new Object());
        objectList.add(new Integer(22));
        objectList.add(new String("Hello objectList"));

        // 第三段，把list的引用赋值给integerList
        // 注意，list与objectList的区别是增加了泛型限制<Integer>
        List<Integer> integerList = list;
        integerList.add(new Integer(33));
        // 以下两行编译报错，不允许增加费Integer类型进入集合
        // integerList.add(new Object());
        // integerList.add(new String("Hello integerList"));

        // 第四段，把list的引用赋值给wildcardgList
        // 注意，list与wildcardgList的区别是增加了泛型通配符
        List<?> wildcardgList = list;
        // 允许删除和清除元素
        wildcardgList.remove(0);
        wildcardgList.clear();
        // 编译报错，不允许增加任何元素
        // wildcardgList.add(new Object());
    }

    /**
     * 泛型的通配符
     * 定义两个集合一个List，一个Set的
     * List存储String，Set存储Integer，遍历集合
     * 遍历的时候很方便，弊端：不能强制转换
     * <p>
     * 上限限定  ? extends E  传递E类型，E的子类类型
     * 下限限定  ? super E    传递E类型，E的父类类型
     */
    @Test
    public void genericWildcardg() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);

        genericIterator(list);
    }

    //定义一个方法，专门迭代集合的
    // Lis<?>一般作为参数来接受外部的集合，或者返回一个不知道具体元素类型的集合
    private static void genericIterator(Collection<?> col) {
        Iterator<?> it = col.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    /******************************泛型上限下限测试***************************************/
    /**
     * 类型变量的限定
     * 语法：<T extends BoundingType>
     * 表示T应该是限定类型的子类型，T和限定类型可以是类，也可以是接口
     * 一个类型变量或者通配符可以有多个限定
     * T, U extends Comparable & Serializable
     * 限定类型用'&"分隔，逗号用来分隔类型变量
     * 在Java继承中，可以根据需要拥有多个接口超类型，但最多有一个限定可以是类，如果有一个类作为限定，必须是限定列表的第一个限定
     * <p>
     * <p>
     * 上限限定  ? extends E  传递E类型，E的子类类型，是Get First，用于消费集合元素为主的场景
     * 下限限定  ? super E    传递E类型，E的父类类型，是Put First，用于生产集合元素为主的场景。
     */
    @Test
    public void genericExtendsSuper() {
        List<Animal> animals = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();
        List<Garfield> garfields = new ArrayList<>();

        animals.add(new Animal());
        cats.add(new Cat());
        garfields.add(new Garfield());

        // 测试赋值操作
        // 这行编译报错，只能赋值Cat或Cat子类集合
        // List<? extends Cat> extendsCatFromAnimal = animals;
        // 接受Cat或Cat父类集合
        List<? super Cat> superCatFromAnimal = animals;
        System.out.println("====================");

        List<? extends Cat> extendsCatFromCat = cats;
        List<? super Cat> superCatFromCat = cats;
        System.out.println("==================");

        List<? extends Cat> extendsCatFromGarfield = garfields;
        // 这行编译报错，只能赋值Cat或Cat父类集合
        // List<? super Cat> superCatFromGarfield = garfields;

        // 测试add方法
        // 以下三行<? extends T>都无法进行add操作，编译报错
        // extendsCatFromCat.add(new Animal());
        // extendsCatFromCat.add(new Cat());
        // extendsCatFromCat.add(new Garfield());

        // 这行编译报错，只能添加Cat或Cat子类集合
        // superCatFromCat.add(new Animal());
        superCatFromCat.add(new Cat());
        superCatFromCat.add(new Garfield());

        // 第四段，测试get方法
        // 所有的super操作能够返回元素，但是泛型丢失，只能返回Object对象

        // 以下extends操作能够返回元素
        Cat cat = extendsCatFromCat.get(0);
        // 这行编译报错，虽然Cat集合从Garfield赋值而来，但是类型擦除后，是不知道的
        // Garfield garfield = extendsCatFromGarfield.get(0);
    }

    class Animal {

    }

    class Cat extends Animal {

    }

    class Garfield extends Cat {

    }
    /******************************泛型上限下限测试**************************************/

    /**
     * 泛型类
     */
    // 泛型类：class 类名<T>
    // 类型变量E，用尖括号(<>)括起来，放在类名的后面，泛型类可以有多个类型变量
    // 类型变量在整个类定义中用于指定方法的返回类型以及字段和局部变量的类型。
    static class GenericClass<E> {
        public void show(E e) {
            System.out.println(e);
        }

        // 泛型方法(可以在普通类中也可以在泛型类中)：修饰符 <T> 返回值类型 方法名(T t){}；
        //方法上，定义了泛型T，这个T和类上泛型E，没有任何关系的
        //调用method方法的时候，传递任意类型都是可以的
        // 类型变量放在修饰符(这里的修饰符是public)的后面，并在返回值类型的前面
        public <T> void method(T t) {
            System.out.println(t);
        }

        // 普通方法可以访问类上定义的泛型，但是静态方法不行，静态方法只能自己定义；
        // 格式；修饰符 static <T> 返回值类型 方法名(T t){}； <T>千万不能放在static前头；
        //静态方法的泛型，不要和类上的泛型一致
        public static <E> void function(E e) {
            System.out.println(e);
        }
    }

    // 不能抛出或捕获泛型类的实例
    /*static class Problem<T> extends Exception {
    }*/

    static class Pair<T> {
        // 泛型类的静态上下午中类型变量无效
        // private static T staticPairVariable; // error
        /*public static T staticPairMethod() {
            if (staticPairVariable == null) {
                return staticPairVariable;
            }
        }*/ // error

        private T first;
        private T second;

        public Pair() {
            first = null;
            second = null;
        }

        public Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public T getSecond() {
            return second;
        }

        public void setFirst(T newValue) {
            first = newValue;
        }

        public void setSecond(T newValue) {
            second = newValue;
        }

        // 限制与局限性
        // 1.不能用基本数据类型实例化类型参数
        public void genericLimit() {
            // 虚拟机中的对象总有一个特定的非泛型类型。因此，所有的类型查询只产生原始类型。
            Object a = null;
            // 2.运行时查询只适用于原始类型
            // if (a instanceof Pair<String>) {} // error
            // if (a instanceof Pair<T>) {} // error
            // Pair<String> pair = (Pair<String>) a; // warning-can only test that a is a Pair
            // 如果试图查询一个对象是否属于某个泛型类型，你会得到一个编译期错误(使用instanceOf)，或者得到一个警告(使用强制类型转换)
            // getClass()总是返回原始类型
            Pair<String> pair1 = new Pair<>();
            Pair<String> pair2 = new Pair<>();
            // if (pair1.getClass() == pair2.getClass()) return; // they are equal
            // 3.不能创建参数类型数组
            // Pair<String> pair = new Pair<String>()[10]; // error

            // Varargs警告
            Collection<Pair<String>> table = new ArrayList<>();
            new Pair<>().addAll(table, pair1, pair2);

            // 不能实例化类型变量
            Pair<String> pair = Pair.makePair(String::new);
        }

        // java 8之后，最好的解决方法是让调用者提供一个构造器表达式
        public static <T> Pair<T> makePair(Supplier<T> constr) {
            return new Pair<>(constr.get(), constr.get());
        }

        // 传统的解决方式是通过反射调用Constructor.newInstance()方法来构造泛型对象
        public static <T> Pair<T> makePair(Class<T> cl) {
            try {
                return new Pair<>(cl.getConstructor().newInstance(), cl.getConstructor().newInstance());
            } catch (Exception e) {
                return null;
            }
        }

        public static <T extends Throwable> void dowork(Class<T> t) {
            /*try {
                //
            } catch (T e) { // catch字句不能使用类型变量 // error
                e.printStackTrace();
            }*/

            try {
                //
            } catch (Throwable e) { // 在异常规范中使用类型变量是可以的 // error
                e.printStackTrace();
            }
        }

        // 不能构造泛型数组
        public static <T extends Comparable> T[] minmax(T... ts) {
            // 类型擦除导致总是构造Comparable[2]
            // T[] mm = new T[2]; // error

            // 调用String[] a = Pair.minmax("Tom", "Jerry"); 编译不会报错，返回强制类型转换时，ClassCastException异常
            // 解决办法是让用户提供一个数组构造器表达式。String[] a = Pair.minmax(String[]::new, "Tom", "Jerry")
            Comparable[] result = new Comparable[2];
            return (T[]) result; // compiles with warning
        }

        // @SafeVarargs 1.7 只能用于声明为static，final或private的构造器和方法。
        @SafeVarargs
        public final <T> void addAll(Collection<T> coll, T... ts) {
            for (T t : ts) {
                coll.add(t);
            }
        }

        // 注意擦除后的冲突
        // 考虑一个Pair<String>，从概念上讲，有两个equals方法
        // boolean equals(String) // defined in Pair<T>
        // boolean equals(Object) // inherited from Object
        /*public boolean equals(T value) {
            return first.equals(value) && second.equals(value);
        }*/ // error
    }

    // Java核心技术卷I 第8章 泛型程序设计 353页
    public static void printBuddies(Pair<? extends Employee> p) {
        Employee first = p.getFirst();
        Employee second = p.getSecond();
        System.out.println(first.getName() + " and " + second.getName() + " are buddies.");
    }

    public static void minmaxBonus(Manager[] a, Pair<? super Manager> result) {
        if (a.length == 0) return;
        Manager min = a[0];
        Manager max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (min.getBonus() > a[i].getBonus()) min = a[i];
            if (max.getBonus() < a[i].getBonus()) max = a[i];
        }
        result.setFirst(min);
        result.setSecond(max);
    }

    public static void maxminBonus(Manager[] a, Pair<? super Manager> result) {
        minmaxBonus(a, result);
        swapHelper(result);
    }

    public static boolean hasNulls(Pair<?> pair) {
        return pair.getFirst() == null || pair.getSecond() == null;
    }

    public static void swap(Pair<?> pair) {
        swapHelper(pair);
    }

    public static <T> void swapHelper(Pair<T> pair) {
        T t = pair.getFirst();
        pair.setFirst(pair.getSecond());
        pair.setSecond(t);
    }

    // 可以取消对检查型异常的检查
    interface Task {
        void run() throws Exception;

        @SuppressWarnings("unchecked")
        static <T extends Throwable> void throwAs(Throwable t) throws T {
            throw (T) t;
        }

        static Runnable asRunable(Task task) {
            return () -> {
                try {
                    task.run();
                } catch (Exception e) {
                    Task.throwAs(e);
                }
            };
        }
    }

    public static void main(String[] args) {
        // Exception in thread "Thread-0" java.lang.Exception: Check this out
        /*new Thread(Task.asRunable(() -> {
            Thread.sleep(1000);
            System.out.println("Hello, World !");
            throw new Exception("Check this out");
        })).start();*/

        Manager ceo = new Manager("Gus Greedy", 800000, 2003, 12, 15);
        Manager cfo = new Manager("Sid Sneaky", 600000, 2003, 12, 15);
        Pair<Manager> managerPair = new Pair<>(ceo, cfo);
        printBuddies(managerPair);

        ceo.setBonus(1000000);
        cfo.setBonus(500000);

        Manager[] managers = {ceo, cfo};

        Pair<Employee> employeePair = new Pair<>();
        minmaxBonus(managers, employeePair);
        System.out.println("first: " + employeePair.getFirst().getName() + ", second: " + employeePair.getSecond().getName());

        maxminBonus(managers, employeePair);
        System.out.println("first: " + employeePair.getFirst().getName() + ", second: " + employeePair.getSecond().getName());
    }

    // 原始类型用第一个限定来替换类型变量，或者，如果没有给定限定，就替换为Object
    // 为了提高效率，应该将标签接口(即没有方法的接口)放在限定列表的末尾
    class Interval<T extends Comparable & Serializable> {
        private T lower;
        private T uper;

        public Interval(T first, T second) {
            if (first.compareTo(second) <= 0) {
                lower = first;
                uper = second;
            } else {
                lower = second;
                uper = first;
            }
        }
    }


    /**
     * 泛型的接口
     * 实现类实现接口的时候，直接实现泛型
     * 实现类实现接口的时候，不指定泛型
     */
    // 泛型接口：interface 接口名<T>
    interface GenericInterface<T> {
        void show(T t);
    }

    //定义实现类，实现接口的同时，实现泛型
    //实现类，直接写了泛型，new实现类的时候，很多的局限性
    /*
    class MyInterImpl<Integer> implements GenericInterface<Integer> {
        public void show(Integer t) {
            System.out.println(t);
        }
    }
    */

    //定义实现类，实现接口的同时，不指定泛型，等待new实现类对象的时候。
    class MyInterImpl<T> implements GenericInterface<T> {
        public void show(T t) {
            System.out.println(t);
        }
    }
}
