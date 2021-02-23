package com.icore.winvaz.javase.basic.coretechnology;

import lombok.Data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2021/2/4 18:35
 * @Version 1.0.0
 */
public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        /**
         *  浅拷贝
         */
        /*
        C c = new C("张三", 18, new Address("北京", "育新建材市场"));
        C cloneC = (C) c.clone();
        System.out.println(c.hashCode());
        System.out.println(cloneC.hashCode());

        // 信息完全一样
        System.out.println(c);
        System.out.println(cloneC);
        System.out.println("信息完全一样");

        System.out.println("原始年龄：" + c.getAge());
        System.out.println("克隆后原始年龄：" + cloneC.getAge());
        System.out.println("年龄完全一样");

        System.out.println("原始名字哈希值：" + c.getName().hashCode());
        System.out.println("克隆名字哈希值：" + cloneC.getName().hashCode());
        System.out.println("字符串哈希值完全一样");

        // 设置姓名
        cloneC.setName("李四");
        cloneC.setAge(20);
        cloneC.getAddress().setStreet("西三旗");

        System.out.println(cloneC);
        System.out.println(c);
        System.out.println("年龄跟姓名 是完全的深拷贝 副本跟原值无关的！");
        System.out.println("地址信息的修改是浅拷贝");
        */
        /**
         *  深拷贝
         *  Object 类提供的 clone 是只能实现 浅拷贝的。，即将「引用类型的属性内容也拷贝一份新的」。
         *  那么，实现深拷贝我这里收集到两种方式：
         *  「 第一种」是给需要拷贝的引用类型也实现Cloneable接口并覆写clone方法；
         *  「第二种」则是利用序列化。接下来分别对两种方式进行演示。
         */
        /**
         * 深拷贝-clone方式
         * 利用clone方式进行深拷贝无非就是将Address类也实现Cloneable，然后对Person的clone方法进行调整。让每个引用类型属性内部都重写clone() 方法,
         * 既然引用类型不能实现深拷贝，那么我们将每个引用类型都拆分为基本类型，分别进行浅拷贝。比如上面的例子，Person 类有一个引用类型 Address(其实String
         * 也是引用类型，但是String类型有点特殊，后面会详细讲解)，我们在 Address 类内部也重写 clone 方法
         */
        /**
         *  弊端
         *  这里我们Person 类只有一个 Address 引用类型，而 Address 类没有，所以我们只用重写 Address 类的clone 方法，但是如果 Address
         *  类也存在一个引用类型，那么我们也要重写其clone 方法，这样下去，有多少个引用类型，我们就要重写多少次，如果存在很多引用类型，那么代码量显然会很大，所以这种方法不太合适。
         */
        /*
        C c = new C("张三", 19, new Address("北京", "育新建材市场"));
        C c1 = c;
        c1.setAge(21);

        System.out.println(c1.hashCode());
        System.out.println(c.hashCode());

        System.out.println(c1);
        System.out.println(c);
        System.out.println("----------------");

        C cloneC = (C) c.clone();
        System.out.println(c.hashCode());
        System.out.println(cloneC.hashCode());

        // 信息完全一样
        System.out.println(c);
        System.out.println(cloneC);
        System.out.println("信息完全一样");

        System.out.println("原始年龄：" + c.getAge());
        System.out.println("克隆后原始年龄：" + cloneC.getAge());
        System.out.println("年龄完全一样");

        System.out.println("原值名字哈希值：" + c.getName().hashCode());
        System.out.println("克隆后名字哈希值：" + cloneC.getName().hashCode());
        System.out.println("字符串哈希值完全一样");

        cloneC.setName("李四");
        cloneC.setAge(22);
        cloneC.getAddress().setStreet("西二旗");
        System.out.println(cloneC);
        System.out.println(c);
        System.out.println("年龄跟姓名 是完全的深拷贝 副本跟原值无关的！");
        System.out.println("地址信息的修改是深拷贝  ");
        */

        /**
         *  深拷贝-序列化方式
         *  序列化是将对象写到流中便于传输，而反序列化则是把对象从流中读取出来。这里写到流中的对象则是原始对象的一个拷贝，因为原始对象还存在 JVM
         *  中，所以我们可以利用对象的序列化产生克隆对象，然后通过反序列化获取这个对象。注意每个需要序列化的类都要实现 Serializable 接口，如果有某个属性不需要序列化，可以将其声明为
         *  transient，即将其排除在克隆属性之外。
         */
        C c = new C("张三", 19, new Address("北京", "育新建材市场"));
        C cloneC = (C) c.deepClone();
        System.out.println(c.hashCode());
        System.out.println(cloneC.hashCode());

        System.out.println(c);
        System.out.println(cloneC);

        cloneC.setName("李四");
        cloneC.setAge(20);
        Address address = cloneC.getAddress();
        address.setStreet("中山路");

        System.out.println(cloneC);
        System.out.println(c);
    }
}

/**
 * 利用序列化与反序列化进行对象的深拷贝
 */
class DeepClone implements Serializable {
    private static final long serialVersionUID = 1412L;

    public Object deepClone() throws IOException, ClassNotFoundException {
        // 序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        // 反序列化
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }
}

@Data
// class C implements Cloneable {
// 序列化深拷贝
class C extends DeepClone {

    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private Address address;

    public C(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    /*
    @Override
    public Object clone() throws CloneNotSupportedException {
        // 浅拷贝
        // return super.clone();

        // 深拷贝
        C clone = (C) super.clone();
        // 手动对address属性进行clone，并赋值给新的C对象
        clone.address = (Address) address.clone();

        return clone;
    }
    */
}

@Data
// class Address implements Cloneable {
    // 序列化深拷贝
class Address extends DeepClone {

    private static final long serialVersionUID = 1412L;

    private String province;
    private String street;

    public Address(String province, String street) {
        this.province = province;
        this.street = street;
    }

    /*
    // 深拷贝时添加
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    */

    @Override
    public String toString() {
        return "Address [province=" + province + ", street=" + street + "]";
    }
}
