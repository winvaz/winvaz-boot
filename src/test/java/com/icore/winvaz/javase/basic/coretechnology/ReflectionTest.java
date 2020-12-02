package com.icore.winvaz.javase.basic.coretechnology;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @Deciption 反射
 * @Author wdq
 * @Create 2020/11/10 14:31
 * @Version 1.0.0
 */
public class ReflectionTest {
    public static void main(String[] args) {
        String name;
        // 从控制台或手动输入类名
        if (args.length > 0) name = args[0];
        else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter class name (e.g. java.util.Date): ");
            name = scanner.next();
        }

        //    print class name and
        try {
            Class<?> cl = Class.forName(name);
            printClass(cl);
            for (Method m : cl.getDeclaredMethods()) {
                printMethod(m);
            }
            /*Class<?> superCl = cl.getSuperclass();
            String modifiers = Modifier.toString(cl.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print("class " + name);
            if (superCl != null && superCl != Object.class) System.out.print(" extends " + superCl.getName());
            System.out.print("\n{\n");
            printConstructors(cl);
            System.out.println();
            printMethods(cl);
            System.out.println();
            printFields(cl);
            System.out.println("}");*/
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void printClass(Class<?> cl) {
        System.out.print(cl);
        printTypes(cl.getTypeParameters(), "<", ", ", ">", true);
        Type sc = cl.getGenericSuperclass();
        if (sc != null) {
            System.out.print(" extends ");
            printType(sc, false);
        }
        printTypes(cl.getGenericInterfaces(), " implements", ", ", "", false);
        System.out.println();
    }

    private static void printTypes(Type[] types, String pre, String sep, String suf, boolean isDefinition) {
        if (pre.equals(" extends ") && Arrays.equals(types, new Type[]{Object.class})) return;
        if (types.length > 0) System.out.print(pre);
        for (int i = 0; i < types.length; i++) {
            if (i > 0) System.out.print(pre);
            printType(types[i], isDefinition);
        }
        if (types.length > 0) System.out.print(suf);
    }

    private static void printType(Type type, boolean isDefinition) {
        if (type instanceof Class) {
            Class t = (Class<?>) type;
            System.out.print(t.getName());
        } else if (type instanceof TypeVariable) {
            TypeVariable t = (TypeVariable<?>) type;
            System.out.print(t.getName());
            if (isDefinition)
                printTypes(t.getBounds(), " extends ", " & ", "", false);
        } else if (type instanceof WildcardType) {
            WildcardType t = (WildcardType)type;
            System.out.print("?");
            printTypes(t.getUpperBounds(), " extends ", " & ", "", false);
            printTypes(t.getLowerBounds(), " super ", " & ", "", false);
        } else if (type instanceof ParameterizedType) {
            ParameterizedType t = (ParameterizedType) type;
            Type owner = t.getOwnerType();
            if (owner != null) {
                printType(owner, false);
                System.out.print(".");
            }
            printType(t.getRawType(), false);
            printTypes(t.getActualTypeArguments(), "<", ", ", ">", false);
        } else if (type instanceof GenericArrayType) {
            GenericArrayType t = (GenericArrayType) type;
            System.out.print("");
            printType(t.getGenericComponentType(), isDefinition);
            System.out.println("[]");
        }
    }

    /**
     * 输出构造函数
     *
     * @param cl
     * @throws
     * @author wdq
     * @create 2020/11/10 14:42
     * @Return void
     */
    private static void printConstructors(Class<?> cl) {
        // 获取私有构造
        Constructor<?>[] constructors = cl.getDeclaredConstructors();
        for (Constructor c : constructors) {
            String name = c.getName();
            System.out.print("  ");
            String modifiers = Modifier.toString(c.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print(name + " ");

            // 输出参数类型
            Class[] paramTypes = c.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(paramTypes[i].getName());
            }
            System.out.println(");");
        }
    }

    private static void printMethod(Method m) {
        String name = m.getName();
        System.out.print(Modifier.toString(m.getModifiers()));
        System.out.print("  ");
        printTypes(m.getParameterTypes(), "<", ", ", "", false);

        printType(m.getGenericReturnType(), false);
        System.out.print("  ");
        System.out.print(name);
        System.out.println("(");
        printTypes(m.getGenericParameterTypes(), "", ", ", "", false);
        System.out.println(")");
    }

    /**
     * 输出函数
     *
     * @param cl
     * @throws
     * @author wdq
     * @create 2020/11/10 14:47
     * @Return void
     */
    private static void printMethods(Class<?> cl) {
        // 获取私有方法
        Method[] methods = cl.getDeclaredMethods();
        for (Method m : methods) {
            // 返回类型
            Class<?> returnType = m.getReturnType();
            String name = returnType.getName();
            System.out.print("  ");
            // print modifiers, return type and method name
            String modifiers = Modifier.toString(m.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.println(returnType.getName() + " " + name + "(");
            // 输出参数类型
            Class<?>[] paramTypes = m.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) System.out.print(",  ");
                System.out.print(paramTypes[i].getName());
            }
            System.out.println(");");
        }
    }

    /**
     * 输出字段
     *
     * @param cl
     * @throws
     * @author wdq
     * @create 2020/11/10 14:55
     * @Return void
     */
    private static void printFields(Class<?> cl) {
        // 获取私有字段
        Field[] fields = cl.getDeclaredFields();
        for (Field f : fields) {
            Class<?> type = f.getType();
            String name = f.getName();
            System.out.print("  ");
            String modifiers = Modifier.toString(f.getModifiers());
            if (modifiers.length() > 0) System.out.println(modifiers + " ");
            System.out.println(type.getName() + " " + name + ";");
        }
    }
}