package com.icore.winvaz.javase.basic.coretechnology;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/9/15 11:19
 * @Version 1.0.0
 */
public class ObjectAnalyzerTest {
    public static void main(String[] args) throws IllegalAccessException {
        List<Integer> squares = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            squares.add(i * i);
            System.out.println(new ObjectAnalyzer().toString(squares));
        }
    }
}

class ObjectAnalyzer {
    private ArrayList<Object> visited = new ArrayList<>();

    public String toString(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return "null";
        }
        if (visited.contains(obj)) {
            return "...";
        }
        visited.add(obj);
        Class<?> cl = obj.getClass();
        if (cl == String.class) {
            return (String) obj;
        }
        if (cl.isArray()) {
            String r = cl.getComponentType() + "[]{";
            for (int i = 0; i < Array.getLength(obj); i++) {
                if (i > 0) {
                    r += ",";
                }
                Object val = Array.get(obj, i);
                if (cl.getComponentType().isPrimitive()) {
                    r += val;
                } else {
                    r += toString(val);
                }
            }
            return r + "}";
        }

        String r = cl.getName();
        do {
            r += "[";
            Field[] fields = cl.getDeclaredFields();
            for (Field field : fields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    if (!r.endsWith("[")) {
                        r += ",";
                    }
                    r += field.getName() + "=";
                    Class<?> t = field.getType();
                    Object val = field.get(obj);
                    if (t.isPrimitive()) {
                        r += val;
                    } else {
                        r += toString(val);
                    }
                }
            }
            r += "]";
            cl = cl.getSuperclass();
        } while (cl != null);

        return r;
    }
}
