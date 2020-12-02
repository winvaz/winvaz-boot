package com.icore.winvaz.javase.basic;

import cn.hutool.core.io.IoUtil;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 反射，获取一个字节码文件对象
 *
 * @Author wdq
 * @Create 2019-02-25 16:34
 */
public class RefllectTest {
    public static void main(String[] args) {
        /**
         * 获取类路径下资源
         */
        try {
            /**
             * Class类的getResourceAsStream(String path)：
             *  路径以“/”开头，相对classes路径；
             *  路径不以“/”开头，相对当前class文件所有路径，例如在cn.icore.servlet.MyServlet中执行，那么相对/classes/cn/icore/servlet/路径；
             *  ClassLoader类的getResourceAsStream(String path)：
             *  相对classes路径；
             */

            // 1.Class
            // /xxx.properties--test/resources/xxx.properties
            // xxx.properties--null--NullPointerException
            InputStream inputStream = RefllectTest.class.getResourceAsStream("/xxx.properties");

            // 2.ClassLoader
            // /xxx.properties--null--NullPointerException
            // xxx.properties--test/resources/xxx.properties
            // InputStream inputStream = RefllectTest.class.getClassLoader().getResourceAsStream("xxx.properties");

            System.out.println(IOUtils.toString(inputStream, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        // 第一种方式，通过对象获取
        Person person = new Person();
        // Object类中有一个方式getClass()返回的是Class类
        Class<? extends Person> personClass = person.getClass();
        // 得到类加载器
        ClassLoader classLoader = personClass.getClassLoader();
        System.out.println(personClass);
        */

        // 第二种方式，通过类的静态属性class获取，返回这个类的class类型，字节码对象
        /*Class<Person> personClass1 = Person.class;
        System.out.println(personClass1);

        System.out.println(personClass == personClass1); // true
        System.out.println(personClass.equals(personClass1)); // true*/

        // 第三种方式，通过Class类的静态方法forName(String 类名)获取
        Class<?> person = null;
        try {
            person = Class.forName("com.icore.winvaz.javase.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //System.out.println(person);

        // 此方法只能获取公共构造方法，并运行
        /*
        Constructor<?>[] constructors = person.getConstructors();
        for (Constructor con : constructors) {
            System.out.println(con);
        }
        */

        /**
         * 获取空参的构造方法
         */
        /*
        try {
            // 通过参数列表获取构造方法，并运行
            Constructor<?> constructor = person.getConstructor();
            System.out.println(constructor);
            // 获取空参的构造方法
            Object o = constructor.newInstance();
            // 调用toString()
            System.out.println(o);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        */

        /*
        try {
            //获取构造方法的简单运行，保证类中有public空参数构造
            // 没有空参的构造函数将抛出一个异常NoSunchMethodException
            Object obj = person.newInstance();
            System.out.println(obj);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        */

        /**
         * 获取公共带参数的构造方法
         */
        /*
        try {
            // 获取带有int类型的构造方法
            Constructor<?> constructor = person.getConstructor(String.class, Integer.class);
            Object object = constructor.newInstance("张三", 19);
            System.out.println(object);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        */

        /**
         * 获取私有构造方法
         */
        // 获取所有构造方法包括私有构造方法，并运行
        /*
        Constructor<?>[] declaredConstructors = person.getDeclaredConstructors();
        for (Constructor con : declaredConstructors) {
            System.out.println(con);
        }
        */

        /**
         * 获取私有带参数的构造方法
         */
        /*
        try {
            // 获取私有，带有int参数构造
            Constructor<?> declaredConstructor = person.getDeclaredConstructor(Integer.class);
            // Constructor的父类AccessibleObject有个方法setAccessible()
            declaredConstructor.setAccessible(true);
            Object object = declaredConstructor.newInstance(19);
            System.out.println(object);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        */

        /*
        // 获取public成员方法,包括继承或实现的方法
        Method[] methods = person.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        */

        /**
         * 获取一个方法
         */
        /*
        try {
            // 获取一个方法，并运行
            Method method = person.getMethod("eat");
            Object o = person.newInstance();
            // invoke()运行方法的方法
            Object object = method.invoke(o);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        */

        /*
        try {
            // 获取一个有参方法，并运行
            Method method = person.getMethod("show", String.class, int.class);
            Object o = person.newInstance();
            method.invoke(o, "zhangsan", 15);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        */

        /**
         * 获取私有方法并运行
         */
        try {
            Method method = person.getDeclaredMethod("speak", String.class, int.class, double.class);
            method.setAccessible(true);
            // int modifiers = method.getModifiers();
            System.out.println(method);
            Object o = person.newInstance();
            method.invoke(o, "zhangsan", 10, 15.12);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        /**
         * 获取成员属性
         */
        // 获取私有属性
        Field[] fields = person.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f);
        }
        /*
        try {
            Field field = person.getDeclaredField("name");
            field.setAccessible(true);
            field.set(person, "zhangsan");
            System.out.println(field.get(person));
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

        // 反射技术--泛型的擦除
        try {
            ArrayList<String> array = new ArrayList<String>();
            array.add("asdf");
            array.add("dgfer");
            //获取ArrayList类的字节码文件对象
            Class clazz = array.getClass();
            // System.out.println(clazz);
            // 获取添加方法add
            Method method = clazz.getMethod("add", Object.class);
            method.invoke(array, 123);
            method.invoke(array, 12.34);
            method.invoke(array, true);
            System.out.println(array);
            Iterator it = array.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
            /*
            for(Object s : array){
                System.out.println(s);
            }
            */
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void method() {
        new AA();
    }
}

/**
 * 泛型基础加强
 */
class A<T> {
    public A() {
        /**
         * 获取子类传递给父类的泛型参数，即String或Integer
         * 1.先得到子类的类型，即Class
         * 2.通过class得到参数化类型，即包含泛型的类型，即A<String>类型
         * 3.通过参数化类型，得到泛型参数！
         */
        // 1.得到子类的类型
        Class<? extends A> aClass = this.getClass();
        // 2.得到子类传递的完整参数化类型
        Type type = aClass.getGenericSuperclass();
        // 3.还需要强转成参数化类型
        ParameterizedType parameterizedType = (ParameterizedType) type;
        // 4.得到真实的参数们
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        for (Type t : actualTypeArguments) {
            // 5.得到参数化类型
            Class clazz = (Class) t;
            System.out.println(clazz.getName());
        }
    }
}

class AA extends A<String> {

}

class AAA extends A<Integer> {

}