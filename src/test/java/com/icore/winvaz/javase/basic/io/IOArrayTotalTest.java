package com.icore.winvaz.javase.basic.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * 提供一个文本文件，文件中保存的是姓名和成绩
 * 读取文件，对姓名和成绩按照成绩高低排序
 * 将排序后的结果，保存到另一个文件中
 * 数据中姓名，成绩可能重复
 * 对于文件有要求，提供你的文件
 * studentscore.txt 排序后另存的文件sortstudentscore.txt.
 * abc.txt    sortabc.txt
 * <p>
 * IO，字符，File
 * 集合List Collections
 */
/**
 * 张三  10
 * 李四  11
 * 王五  12
 * 赵六  12
 * 田七  15
 * 周八  0
 * 吴九  20
 * 郑十  100
 */
public class IOArrayTotalTest {
    public static void main(String[] args) {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            // 把文件封装成File对象
            File file = new File("/Users/wdq/Documents/studentscore.txt");
            // 字符缓冲流对象读取文件
            bufferedReader = new BufferedReader(new FileReader(file));
            // 定义一个数组存储读取到的学生信息
            List<Student> students = new ArrayList<>();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                // 对读到的数据用空格切割
                String[] split = line.split(" +");
                // 数组0下标为学生姓名，数组1小标为学生成绩,存入集合中
                students.add(new Student(split[0], Integer.parseInt(split[1])));
            }
            // 使用Collections工具类中的方法 sort,排序的是自定义对象
            Collections.sort(students, new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    // 先按成绩，成绩之后再按姓名
                    /*
                    double different = 1e-6d;
                    int flag;
                    if (Math.abs(o1.getScore() - o2.getScore()) < different) {
                        flag = 0;
                    } else if (Math.abs(o1.getScore() - o2.getScore()) > different) {
                        flag = 1;
                    } else {
                        flag = -1;
                    }
                    */
                    int x = o1.getScore() - o2.getScore();
                    return x == 0 ? o1.getName().compareTo(o2.getName()) : x;
                }
            });
            // 将排序后的内存，存储到新的文本文件中，要求文件名前面加sort
            // 获取源文件的文件名
            String fileName = "sort" + file.getName();
            // 获取源文件的父路径
            String fileParent = file.getParent();
            // 父路径和文件名组成File对象，传递给字符输出流写文件
            File desFile = new File(fileParent, fileName);
            // 字符缓冲流输出
            bufferedWriter = new BufferedWriter(new FileWriter(desFile));
            // 循环文件写入
            for (Student student : students) {
                bufferedWriter.write(student.getName() + "  " + student.getScore());
                // 换行
                bufferedWriter.newLine();
                // 刷新
                bufferedWriter.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，请检查");
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("字符缓冲输出流资源关闭失败.");
            } finally {
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("字符缓冲输入流资源关闭失败.");
                }
            }
        }
    }
}

/**
 * 定义一个学生类
 */
class Student {
    private String name;
    // private Double score;
    // 把分数改成Integer
    private Integer score;

    // 构造函数
    public Student() {

    }

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // get/set方法
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    // equals/hashcode
    @Override
    public boolean equals(Object o) {
        // 如果this==o返回true
        if (this == o) {
            return true;
        }
        // 判断是否等于null和是否是Student类型
        if (o != null || getClass() != o.getClass()) {
            return false;
        }
        // 对参数o进行向下转型
        Student student = (Student) o;
        /*
        // 对于浮点类型数据不能用基本数据类型不能==包装数据类型不能equals比较,让成绩小于某个区间则为相等。
        double different = 1e-6d;
        boolean flag = false;
        if (Math.abs(score - student.score) < different) {
            flag = true;
        }
        */
        return name.equals(student.name) && score.equals(student.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score);
    }

    // 覆盖toString()
    @Override
    public String toString() {
        return "Student{" +
                "name=" + name + '\'' +
                "socre=" + score +
                "}";
    }
}
