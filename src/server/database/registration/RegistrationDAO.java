package server.database.registration;
/*
 * Class created on 30.07.2021
 * Class is used to register a new user
 * */

import server.database.DatabaseConnection;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationDAO implements Serializable {

    public RegistrationDAO(){}

    public int registerUser(String firstName, String lastName, String userName, String password) throws ClassNotFoundException {
        int result = 0;
        if(checkingDuplicateUser(userName)) {
            String INSERT_USERS_SQL = "INSERT INTO user" +
                    "  (firstName, lastName, userName, password) VALUES " +
                    " (?, ?, ?, ?);";

            try (Connection connection = DatabaseConnection.getConnection();
                 // Step 2:Create a statement using connection object
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {

                    preparedStatement.setString(1, firstName);
                    preparedStatement.setString(2, lastName);
                    preparedStatement.setString(3, userName);
                    preparedStatement.setString(4, password);

                    // Step 3: Execute the query or update query
                    result = preparedStatement.executeUpdate();
                } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                // process sql exception
                // JDBCUtils.printSQLException(e);
            }
        }
        return result;
    }

    private boolean checkingDuplicateUser(String userName) {
        String UserCheck="select * from user where userName=?";
        ResultSet result=null;
        try (Connection connection = DatabaseConnection.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(UserCheck)) {
            preparedStatement.setString(1, userName);
            System.out.println("Result before : " + result);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeQuery();
            if (!result.next() ) {
                return true;
            }
        } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
           e.printStackTrace();
        }
        return false;
    }
}
