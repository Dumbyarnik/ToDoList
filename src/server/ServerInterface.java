package server;
/*
 * Class created on 10.07.2021
 * Class is used to manage the server side of the application
 * */

import broadcast.BroadcastInterface;
import client.ClientInterface;
import server.database.todolist.Todo;

import java.io.NotSerializableException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ServerInterface extends Remote{

    int subscribeUserDatabase(String firstName, String lastName, String userName, String password) throws ClassNotFoundException, RemoteException, NotSerializableException;
    int loginUser(String username, String password) throws RemoteException;

    ArrayList<Todo> getTodoDatabase(String room) throws RemoteException, ClassNotFoundException;
    Todo getOneTodo(int id) throws RemoteException, ClassNotFoundException, SQLException;
    int addTodoDatabase(String item, String status, String date, String room) throws RemoteException, ClassNotFoundException, SQLException;
    int deleteTodoDatabase(int id) throws RemoteException, ClassNotFoundException, SQLException;
    int updateTodoDatabase(int id, String item, String status, String date) throws RemoteException, ClassNotFoundException, SQLException;
}
