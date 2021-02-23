package com.icore.winvaz.javase.basic;

import com.icore.winvaz.javase.Person;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 序列化
 * 内存中的数据对象只有转换为二进制流才可以进行数据持久化和网络传输。
 * 将数据对象转换为二进制流的过程称为对象的序列化(Serialization)。
 * 将二进制流恢复为数据对象的过程称为反序列化(Deserialization)
 * <p>
 * 序列化的常见使用场景为RPC框架的数据传输。
 * 常见的序列化方式为
 * 1.Java原生序列化。Java类通过实现Serialization接口来实现该类对象的序列化，只起标识作用，兼容性最好，不支持跨语言，性能一般。
 * 注意：实现Serializable接口的类一定要显示地定义serialVersionUID属性值。
 * 2.Hessian序列化。支持动态类型，跨语言，基于对象传输的网络协议。具有自描述序列化类型。语言无关，支持脚本语言。协议简单，比Java原生高效等特性。
 * Hessian会把复杂对象所有属性存储在一个Map中进行序列化，所以在父类，子类存在同名的成员变量的情况下，先序列化子类，再序列化父类。
 * 因此，反序列化时子类的成员变量会被父类成员变量所覆盖(多态的情况下成员也是访问父类成员)
 * 3.JSON序列化
 * 将数据对象转换成JSON对象，相比前两种，可读性比较好，方便调试。
 * <p>
 * 不需要进行序列化传输的属性，可以使用transient关键字。如果非要传输敏感属性，可以使用对称和非对称的加密方式传输。
 * <p>
 * 把成员变量加上了static后，序列化没有问题，但是反序列化的时候，成员变量的值丢失，
 * 原因序列化的时候，文件没有保存静态变量的值。序列化是对象的序列化，静态成员变量，
 * 属于类，不属于对象，因此静态不能序列化
 */
public class SerializableTest {

    private static Logger logger = LoggerFactory.getLogger(SerializableTest.class);

    /**
     * Java原生序列化
     * 序列化，将对象的数据保存到硬盘 -- ObjectOutputStream，字节流
     * ObjectOutputStream构造方法中，传递一个字节输出流对象，字节输出流对象肯定封装一个文件吧，对象就会写进这个文件
     * 写对象的方法 void writeObject(Object obj)
     */
    @Test
    public void serizlizable() {
        Person person = new Person("张三", 10);

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            // 注意： 当序列化一个对象到文件时， 按照 Java 的标准约定是给文件一个 .ser 扩展名。
            fileOutputStream = new FileOutputStream("/Users/wdq/Documents/Person.ser");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(person);
        } catch (Exception e) {
            logger.error("序列化Person对象失败，失败原因：{}", e.getMessage());
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (Exception e) {
                logger.error("objectOutputStream流关闭失败，失败原因：{}", e.getMessage());
            } finally {
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (Exception e) {
                    logger.error("fileOutputStream流关闭失败，失败原因：{}", e.getMessage());
                }
            }
        }
    }

    /**
     * Java原生反序列化
     * 反序列化，将硬盘中的对象数据读取出来 -- ObjectInputStream，字节流
     * ObjectInputStream构造方法中，传递一个字节输入流对象，字节输入流对象封装一个文件，读取封装的文件中的对象
     * 读取对象的方法 Object readObject() 抛出IO异常，抛出类中不到异常
     * 找不到类异常，肯能没有对应的class文件
     * 类必须实现java.io.Serializable接口，启用序列化功能
     */
    @Test
    public void deSerializable() {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            fileInputStream = new FileInputStream("/Users/wdq/Documents/Person.ser");
            objectInputStream = new ObjectInputStream(fileInputStream);
            Person person = (Person) objectInputStream.readObject();
            // System.out.println("id为：" + person.getId());
            System.out.println("name为：" + person.getName());
            System.out.println("age为：" + person.getAge());
            // System.out.println("sex为：" + person.getSex());
        } catch (Exception e) {
            logger.error("反序列化Person对象失败，失败原因：{}", e.getMessage());
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (Exception e) {
                logger.error("objectInputStream流关闭失败，失败原因：{}", e.getMessage());
            } finally {
                try {
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                } catch (Exception e) {
                    logger.error("fileInputStream流关闭失败，失败原因：{}", e.getMessage());
                }
            }
        }
    }

    @Test
    public void serializableCloneTest() throws CloneNotSupportedException {
        C c = new C("张三", 10);
        // Clone c
        C clonec = (C) c.clone();

        // mutate C
        c.setAge(11);

        //
        System.out.println("原始数据:" + c);
        System.out.println("克隆数据:" + clonec);
    }

    /**
     * com.alibaba.fastjons序列化和反序列化
     */
    /*
    @Test
    public void alibabaSerializable() {
        // 将Java对象序列化为Json字符串
        String jsonString = JSON.toJSONString(new Person("李四", 12));
        System.out.println("fastjson将对象序列化为JSON字符串:" + jsonString);
        // 将JSON字符串反序列化为Java对象
        Person person = JSON.parseObject(jsonString, Person.class);
        System.out.println("name为：" + person.getName());
        System.out.println("age为：" + person.getAge());
    }
    */
}

/**
 * 序列化克隆
 * @author wdq
 * @create 2021/1/21 17:00
 */
class SerializableClone implements Cloneable, Serializable {

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            // save the object to a byte array
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            try (ObjectOutputStream oos = new ObjectOutputStream(bout)) {
                oos.writeObject(this);
            }

            // read a clone of the object from the byte array
            try (InputStream in = new ByteArrayInputStream(bout.toByteArray())) {
                ObjectInputStream ois = new ObjectInputStream(in);
                return ois.readObject();
            }
        } catch (Exception e) {
            CloneNotSupportedException cnse = new CloneNotSupportedException();
            cnse.initCause(e);
            throw cnse;
        }
    }
}

@Data
class C extends SerializableClone {
    private String name;
    private Integer age;

    public C(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}