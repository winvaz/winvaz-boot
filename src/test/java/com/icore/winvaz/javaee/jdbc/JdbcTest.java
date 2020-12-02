package com.icore.winvaz.javaee.jdbc;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Deciption JDBC测试
 * @Author wdq
 * @Create 2020/6/21 17:12
 * @Version 1.0.0
 */
public class JdbcTest {

    /*
     * 设置参数
     */
    private static Connection connection = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    // 结果集
    private static ResultSet resultSet = null;

    private static Properties properties = new Properties();

    /**
     * 构造方法，连接数据库(构造方法连接数据库有隐患，不建议使用。)
     *
     * @throws SQLException 抛SQL异常
     */
    /*
    public JDBCTest(String driver, String url, String user, String password) throws SQLException {

        logger.info("正在连接数据库......");
        try {
            // 加载数据库驱动
            Class.forName(driver);

            // 通过DriverManager的静态方法得到Connection
            connection = DriverManager.getConnection(url, user, password);

            logger.info("连接数据库成功！！！！！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    */

    /**
     * 静态代码块初始化数据库连接
     */
    static {
        try {
            // 1.读取配置文件的方式加载
            InputStream resourceAsStream = JdbcTest.class.getResourceAsStream("calsspath:/dbconfig.properties");
            // 2.把对象给prpperties对象的load()方法
            properties.load(resourceAsStream);

            // 3.加载驱动类
            Class.forName(properties.getProperty("jdbc.driver-class"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getConnection() throws SQLException {
        // 4.获取连接对象
        connection = DriverManager.getConnection(properties.getProperty("jdbc.url")
                , properties.getProperty("jdbc.user")
                , properties.getProperty("jdbc.password"));
    }

    /**
     * statement查询
     */
    public void statementQuery(String sql) {
        try {
            // 1.获取连接
            getConnection();
            // 2.得到statement
            Statement statement = connection.createStatement();
            // 3.执行
            ResultSet resultSet = statement.executeQuery(sql);
            // 4.遍历结果
            while (resultSet.next()) {
                int empno = resultSet.getInt(1);//获取当前行第1列的数据
                String ename = resultSet.getString("ename");//获取当前行名为ename的列数据
                System.out.println(empno + ", " + ename);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }


    /**
     * preparedStatement查询
     */
    public void preparedStatementQuery(String sql) {
        try {
            // 1.获取连接
            getConnection();
            // 2.准备SQL模板
            // 3.使用SQL模板创建preparedStatement对象
            // String sql = "select * from tbl_student where s_num=?";
            preparedStatement = connection.prepareStatement(sql);

            // 4.给参数赋值
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "zhangsan");
            preparedStatement.setInt(3, 18);
            preparedStatement.setString(4, "4");

            // 5.调用executeUpdate()方法执行，它没有参数
            resultSet = preparedStatement.executeQuery();
            // 6.遍历输出
            while (resultSet.next()) {
                int empno = resultSet.getInt(1);//获取当前行第1列的数据
                String ename = resultSet.getString("ename");//获取当前行名为ename的列数据
                System.out.println(empno + ", " + ename);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }


    /**
     * 把图片保存到数据库
     */
    // 数据库的列类型为Blob
    public int blobAdd(String sql) {
        int i = 0;
        try {
            // 1.获取连接
            getConnection();
            // 2.得到PreparedStatement(1.SQL模板，2.得到)
            preparedStatement = connection.prepareStatement(sql);

            // 设置参数
            byte[] bytes = FileUtils.readFileToByteArray(new File("/User/wdq/Documents"));
            Blob blob = new SerialBlob(bytes);
            preparedStatement.setBlob(1, blob);

            // 3.执行
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return i;
    }

    /**
     * 把图片从数据库读取出来
     */
    public void blobQuery(String sql) {
        try {
            // 1.获取连接
            getConnection();
            // 2.得到PreparedStatement(1.SQL模板，2.得到)
            preparedStatement = connection.prepareStatement(sql);
            // 3.执行
            resultSet = preparedStatement.executeQuery();
            // 从resultSet中获取数据
            while (resultSet.next()) {
                Blob blob = resultSet.getBlob(1);
                // 把blob转换成图片，保存到硬盘上
                InputStream inputStream = blob.getBinaryStream();
                IOUtils.copy(inputStream, new FileOutputStream("/User/wdq/Document"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    // 关流方法
    public static void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC&useSSL=false";
        String user = "root";
        String password = "12345678";
    }
}
