package com.icore.winvaz.javase.basic.coretechnology;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2021/1/20 15:34
 * @Version 1.0.0
 */
public class TextFilesTest {
    public TextFilesTest() throws FileNotFoundException {
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Carl Cracker", 7500, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 5000, 1989, 10, 01);
        staff[2] = new Employee("Tony Tester", 4000, 1990, 03, 15);

        // save all employee records to the file empolyee.dat
        try (PrintWriter printWriter = new PrintWriter("employee.dat", "UTF-8")) {
            writeData(staff, printWriter);
        }

        // retrieve all records into a new array
        try (Scanner in = new Scanner(new FileInputStream("employee.dat"), "UTF-8")) {
            Employee[] newStaff = readData(in);

            // print the newly read employee records
            for (Employee e : newStaff) {
                System.out.println(e);
            }
        }
    }

    private static Employee[] readData(Scanner in) {
        int n = in.nextInt();
        in.nextLine();

        Employee[] employees = new Employee[n];
        for (int i = 0; i < n; i++) {
            employees[i] = readEmployee(in);
        }

        return employees;
    }

    private static Employee readEmployee(Scanner in) {
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        String name = tokens[0];
        double salary = Double.parseDouble(tokens[1]);
        LocalDate hireDate = LocalDate.parse(tokens[2]);
        int year = hireDate.getYear();
        int month = hireDate.getMonthValue();
        int day = hireDate.getDayOfMonth();
        return new Employee(name, salary, year, month, day);
    }


    private static void writeData(Employee[] employees, PrintWriter printWriter) {
        System.out.println(employees.length);

        for (Employee e : employees) {
            writeEmployee(printWriter, e);
        }
    }

    private static void writeEmployee(PrintWriter printWriter, Employee e) {
        printWriter.println(e.getName() + "|" + e.getSalary() + "|" + e.getHireDay());
    }
}
