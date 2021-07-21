package server.database;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {



    public int registerUser(User u) throws ClassNotFoundException {

        String INSERT_USERS_SQL = "INSERT INTO user" +
                "  (firstName, lastName, userName, password) VALUES " +
                " (?, ?, ?, ?);";

        int result = 0;

        try (Connection connection = testConnection.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, u.getFirstName());
            preparedStatement.setString(2, u.getLastName());
            preparedStatement.setString(3, u.getUserName());
            preparedStatement.setString(4, u.getPassword());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();
            if(result==1){
                System.out.println("User wurde angemeldet");
            }
        } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            // process sql exception
           // JDBCUtils.printSQLException(e);
        }
        return result;
    }
}
