package server.database;
/*
 * Class created on 22.07.2021
 * Class is used to manage the database connection
 * */

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class DatabaseConnection {

    // link to the database connection
    //private static String CONN = "jdbc:mysql://localhost:3306/TODO";

    // Logins
    static Connection connection = null;
    static final String USER = "root"; // hier User
    static final String PASS = "root"; // hier Passwort

    // for testing database
    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String url = "jdbc:mysql://localhost:3306/TODO?characterEncoding=latin1&useConfigs=maxPerformance";
        Class.forName ("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
        connection = DriverManager.getConnection(url,USER,PASS);
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException,
            NoSuchMethodException, InvocationTargetException, InstantiationException,
            IllegalAccessException {
        String url = "jdbc:mysql://localhost:3306/ToDo?characterEncoding=latin1&useConfigs=maxPerformance";
        Class.forName ("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
        connection=DriverManager.getConnection(url,USER,PASS);
        return connection;
    }
}

