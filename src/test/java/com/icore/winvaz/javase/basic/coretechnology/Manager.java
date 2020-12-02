package com.icore.winvaz.javase.basic.coretechnology;

import java.util.Objects;

/**
 * @Deciption Java核心技术 卷I 第5章 继承 185页
 * @Author wdq
 * @Create 2020/11/17 11:23
 * @Version 1.0.0
 */
public class Manager extends Employee{

    private double bonus;

    public Manager(String name, double salary) {
        super(name, salary);
        bonus = 0;
    }

    public Manager(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
        bonus = 0;
    }

    public double getSalary() {
        double salary = super.getSalary();
        return salary + bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Manager manager = (Manager) obj;
        return  bonus == manager.bonus;
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), bonus);
    }

    public String toString() {
        return super.toString() + "[bonus=" + bonus + "]";
    }
}
