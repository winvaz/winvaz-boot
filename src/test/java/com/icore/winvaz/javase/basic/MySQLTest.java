package com.icore.winvaz.javase.basic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Deciption 连接MySQL测试
 * @Author wdq
 * @Create 2019/11/12 14:15
 */
public class MySQLTest {

    private static Logger logger = LogManager.getLogger(MySQLTest.class);

    // DRIVER
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // URL
    private static final String URL = "jdbc:mysql://192.168.208.71:7002/test";

    // USERNAME
    private static final String USERNAME = "proxy";

    // PASSWORD
    private static final String PASSWORD = "proxy1234";

    // 连接对象
    private static Connection connection;

    /*
     * 设置参数
     */
    private static Statement statement;

    // 结果集
    private static ResultSet resultSet;

    /**
     * 构造方法，连接数据库(构造方法连接数据库有隐患，不建议使用。)
     * @throws SQLException 抛SQL异常
     */
    public MySQLTest() throws SQLException {

        logger.info("正在连接数据库......");
        try {
            // 加载数据库驱动
            Class.forName(DRIVER);

            this.connection = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);

            logger.info("连接数据库成功！！！！！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void select(String sql) {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                // 通过字段检索
                int id  = resultSet.getInt("id");
                String name = resultSet.getString("username");
                String url = resultSet.getString("password");
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 站点名称: " + name);
                System.out.print(", 站点 URL: " + url);
                System.out.print("\n");
            }
            // 完成后关闭
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            new MySQLTest().select("select * from user_list");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
