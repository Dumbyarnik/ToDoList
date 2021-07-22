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
    public ArrayList<Todo> getTodo() throws ClassNotFoundException {

        ArrayList<Todo> todos = new ArrayList<>();

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DatabaseConnection.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection
                     .prepareStatement("select * from todos ")) {

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            // Going through the todo table
            while (rs.next()) {

                int id = rs.getInt("ToDoID");
                String item = rs.getString("ToDoName");

                todos.add(new Todo(id, item));

                //System.out.println(id + "\t\t" + item);
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
        return todos;
    }

    // deletes a todo item with an id
    public int deleteTodo(int id) throws ClassNotFoundException, SQLException {
        int status = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("DELETE FROM todos WHERE ToDoID=+"+ id+"")) {
            status = preparedStatement.executeUpdate();


            } catch (InvocationTargetException invocationTargetException) {
            invocationTargetException.printStackTrace();
        } catch (NoSuchMethodException noSuchMethodException) {
            noSuchMethodException.printStackTrace();
        } catch (InstantiationException instantiationException) {
            instantiationException.printStackTrace();
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }

        if(status == 1){
                System.out.println("Todo was deleted");
        } else {
            System.out.println("Todo was not deleted");
        }

        return status;

    }

}
