package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static String userName = "root";
    private static String password = "Zktutylfzktutylf1";
    private static String DB_URL = "jdbc:mysql://localhost:3306/testjava";
    private static String DB_Driver = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(DB_URL, userName, password);
            System.out.println("Соединение выполнено.");
        } catch (ClassNotFoundException e) {
            System.out.println("драйвер не найден!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Ошибка SQL");
            e.printStackTrace();
      }
    return connection;

    }
    }







