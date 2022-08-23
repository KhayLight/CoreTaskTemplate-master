package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String userName = "root";
    private static final String password = "root";
    private static final String connectionURL = "jdbc:mysql://localhost:3306/mydbtest";
    private static SessionFactory sessionFactory;

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionURL, userName,
                    password);
            /*if(!conn.isClosed()){
                System.out.println("Connection to the database is successful");
            }*/
        } catch (SQLException e) {
            System.err.println("Failed to load driver class");
        }
        return conn;
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            try {
                Properties properties = new Properties();
                properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                properties.setProperty("hibernate.connection.url", connectionURL);
                properties.setProperty("hibernate.connection.username", userName);
                properties.setProperty("hibernate.connection.password", password);
                properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
                /*properties.setProperty("hbm2ddl.auto", "validate");*/

                Configuration configuration = new Configuration()
                        .addProperties(properties).addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder =
                        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("sessionFactory is fall");
                // e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}

