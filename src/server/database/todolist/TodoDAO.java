package server.database.todolist;
/*
* Class created on 22.07.2021
* Class is used to retrieve and put information about to Todos
* from and to database and move it further to server
* */

import server.database.DatabaseConnection;

import javax.xml.transform.Result;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;

public class TodoDAO {

    // returns the list of all todo items
    public ArrayList<Todo> getTodo() throws ClassNotFoundException {

        ArrayList<Todo> todos = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection
                     .prepareStatement("select * from todos ")) {

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

    // adds a new todo item
    public int addTodo(String item, String status, String date) throws ClassNotFoundException, SQLException {
        int result = 0;

        long now = System.currentTimeMillis();

        Date sqlDate = new Date(now);

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("INSERT INTO todos (ToDoName, Status, ToDoDate)\n" +
                             "VALUES ('" + item + "','" + status + "','" + date +"');")) {
            result = preparedStatement.executeUpdate();


        } catch (InvocationTargetException invocationTargetException) {
            invocationTargetException.printStackTrace();
        } catch (NoSuchMethodException noSuchMethodException) {
            noSuchMethodException.printStackTrace();
        } catch (InstantiationException instantiationException) {
            instantiationException.printStackTrace();
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }

        if(result == 1){
            System.out.println("Todo was added");
        } else {
            System.out.println("Todo was not added");
        }

        return result;

    }

    // adds a new todo item
    public Todo getOneTodo(int id) throws ClassNotFoundException, SQLException {
        int result = 0;

        Todo tmp = new Todo();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT ToDoID, ToDoName, Status, ToDoDate\n" +
                             "from todos where ToDoID=" + id + ";")) {
            ResultSet rs = preparedStatement.executeQuery();

            rs.next();
            tmp.setId(rs.getInt("ToDoID"));
            tmp.setItem(rs.getString("ToDoName"));
            tmp.setStatus(rs.getString("Status"));
            tmp.setDate(rs.getDate("ToDoDate"));

        } catch (InvocationTargetException invocationTargetException) {
            invocationTargetException.printStackTrace();
        } catch (NoSuchMethodException noSuchMethodException) {
            noSuchMethodException.printStackTrace();
        } catch (InstantiationException instantiationException) {
            instantiationException.printStackTrace();
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }

        if(result == 1){
            System.out.println("We got todo");
        } else {
            System.out.println("We didnt get todo");
        }

        return tmp;

    }

    // updates a todo item
    public int updateTodo(int id, String item, String status, String date) throws ClassNotFoundException, SQLException {

        int result = 0;

        try (Connection connection = DatabaseConnection.getConnection();) {
            String query =
                    "UPDATE todos\n" +
                            "SET ToDoName = '" + item + "'" + ", " +
                            "Status = '" + status + "'" + ", " +
                            "ToDoDate = '" + date + "'" +
                            "WHERE ToDoID=" + "'" + id + "';";

            PreparedStatement ps = connection.prepareStatement(query);
            result = ps.executeUpdate();
            if (result == 1) {
                System.out.println("Great Update!");
            }

        } catch (SQLException se) {
            System.out.println(se);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return result;
    }
}
