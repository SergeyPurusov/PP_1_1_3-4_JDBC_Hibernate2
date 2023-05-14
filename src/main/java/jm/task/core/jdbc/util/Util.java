package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;

import static org.hibernate.cfg.Environment.*;

public class Util {
    private static String userName = "root";
    private static String password = "Zktutylfzktutylf1";
    private static String DB_URL = "jdbc:mysql://localhost:3306/testjava";
    private static String DB_Driver = "com.mysql.cj.jdbc.Driver";
    private static SessionFactory sessionFactory;

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

    public static SessionFactory getSessionFactory() {
            try {
                if (sessionFactory == null || sessionFactory.isClosed()) {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                System.out.println("Соединение создано ");
                settings.put(Environment.DRIVER, DB_Driver);
                settings.put(Environment.URL, DB_URL);
                settings.put(Environment.USER, userName);
                settings.put(Environment.PASS, password);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        return sessionFactory;
    }


}







