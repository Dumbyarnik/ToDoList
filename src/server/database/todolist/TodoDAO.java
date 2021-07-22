package server.database.todolist;
/*
* Class created on 22.07.2021
* Class is used to retrieve and put information about to Todos
* from and to database and move it further to server
* */

import server.database.DatabaseConnection;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TodoDAO {

    // returns the list of all todo items
    public boolean getTodo() throws ClassNotFoundException {

        ArrayList<Todo> todos = new ArrayList<>();

        boolean status = false;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DatabaseConnection.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection
                     .prepareStatement("select * from todos ")) {

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            // Condiion check
            while (rs.next()) {

                int id = rs.getInt("ToDoID");
                String item = rs.getString("ToDoName");
                System.out.println(id + "\t\t" + item);
            }

            //status = rs.next();

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
