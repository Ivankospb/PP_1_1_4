package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String DRIVER_DB = "com.mysql.cj.jdbc.Driver";
    private static final String URL_DB = "jdbc:mysql://localhost:3306/users";
    private static final String USERNAME_DB = "root";
    private static final String PASSWORD_DB = "root";

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, DRIVER_DB);
                settings.put(Environment.URL, URL_DB);
                settings.put(Environment.USER, USERNAME_DB);
                settings.put(Environment.PASS, PASSWORD_DB);

                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                throw new ExceptionInInitializerError(e);
            }
        }
        return sessionFactory;
    }

    public static Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER_DB);
            connection = DriverManager.getConnection(URL_DB, USERNAME_DB, PASSWORD_DB);
            System.out.println("Connection OK");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return connection;
    }

    public static void getConnection() {
    }
}