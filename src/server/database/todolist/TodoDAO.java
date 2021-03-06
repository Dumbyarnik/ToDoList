package server.database.todolist;
/*
* Class created on 22.07.2021
* Class is used to retrieve and put information about to Todos
* from and to database and move it further to server
* */

import server.database.DatabaseConnection;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TodoDAO {

    // returns the list of all todo items in the room
    public ArrayList<Todo> getTodo(String room) throws ClassNotFoundException {

        ArrayList<Todo> todos = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection
                     .prepareStatement("select * from todos " +
                             "where Room = " + "'" + room + "'")) {

            ResultSet rs = preparedStatement.executeQuery();

            // Going through the todo table
            while (rs.next()) {
                int id = rs.getInt("ToDoID");
                String item = rs.getString("ToDoName");
                String status = rs.getString("Status");
                Date date = rs.getDate("ToDoDate");
                long days=calculatedaysBetween(date);
                todos.add(new Todo(id, item, status, date,days));
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
    private long calculatedaysBetween(Date date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy");
        date.toLocalDate().getDayOfWeek();

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date currentDate = new Date(System.currentTimeMillis());
        long diff = date.getTime() - currentDate.getTime();
        long days= TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        if(date.toLocalDate().equals(currentDate.toLocalDate())){
                days=0;
            } else{
            days=days+1;
            //Der naechste Tag
            if(days==-1){
                days=1;
            }
        }
        if(days<0){
            days=0;
        }
            return days;
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

        return status;
    }

    // adds a new todo item
    public int addTodo(String item, String status, String date, String room) throws ClassNotFoundException, SQLException {

        int result = 0;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("INSERT INTO todos (ToDoName, Status, ToDoDate, Room)\n" +
                             "VALUES ('" + item + "','" + status + "','" + date
                             + "','" + room + "');")) {
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

        return result;
    }

    // gets one todo item (for editing)
    public Todo getOneTodo(int id) throws ClassNotFoundException, SQLException {

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

    // gets name of todo with the help of id
    public String getTodoName(int id){
        String name = "";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT ToDoName\n" +
                             "from todos where ToDoID=" + id + ";")) {
            ResultSet rs = preparedStatement.executeQuery();

            rs.next();
            name = rs.getString("ToDoName");
        } catch (InvocationTargetException invocationTargetException) {
            invocationTargetException.printStackTrace();
        } catch (NoSuchMethodException noSuchMethodException) {
            noSuchMethodException.printStackTrace();
        } catch (InstantiationException instantiationException) {
            instantiationException.printStackTrace();
        } catch (IllegalAccessException | ClassNotFoundException | SQLException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }

        return name;
    }
}
