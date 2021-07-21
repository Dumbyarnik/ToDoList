package server.database;



import server.database.Login;
import server.database.DatabaseConnection;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    public boolean validate(String username, String password) throws ClassNotFoundException {
        boolean status = false;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DatabaseConnection.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection
                     .prepareStatement("select * from user where userName = ? and password = ? ")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
             status = rs.next();

            if(status){
                System.out.println("User wurde eingeloggt");
            }
            if(!status){
                System.out.println("Username oder passwort ist falsch");
            }


        } catch (SQLException e) {
            // process sql exception
            //testConnection.printSQLException(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return status;
    }
}


