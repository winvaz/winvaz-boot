package com.icore.winvaz.javase.basic.coretechnology;

import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Deciption 实现Cloneable克隆接口
 * @Author wdq
 * @Create 2020/9/28 16:48
 * @Version 1.0.0
 */
public class Employee implements Cloneable {
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public Employee(String name, double salary, int year, int month, int day) {
        this(name, salary);
        hireDay = LocalDate.of(year, month, day);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void setHireDay(LocalDate hireDay) {
        this.hireDay = hireDay;
    }

    /*
    @Override
    public Employee clone() throws CloneNotSupportedException {
        // call object.clone()
        Employee clone = (Employee) super.clone();

        // clone mutable fields
        clone.hireDay = (LocalDate) hireDay.clone();

        return clone;
    }
    */
    /*public void setHireDay(int year, int month, int day) {
        Date time = new GregorianCalendar(year, month - 1, day).getTime();

        hireDay.setTime(time.getTime());
    }*/

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    @Override
    public String toString() {
        return "Employee[name=" + name + ", salary=" + salary + ", hireDay=" + hireDay + "]";
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        /**
         *  深拷贝测试
         */
        Employee original = new Employee("John Q。Public", 5000);
        // original.setHireDay(2020, 1, 1);
        // Employee clone = original.clone();
        // clone.raiseSalary(10);
        // clone.setHireDay(2021, 1, 1);
        // System.out.println("original=" + original);
        // System.out.println("clone=" + clone);
    }
}