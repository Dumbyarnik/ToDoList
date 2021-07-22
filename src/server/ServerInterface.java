package server;

import broadcast.BroadcastInterface;
import client.ClientInterface;
import server.database.todolist.Todo;

import java.io.NotSerializableException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ServerInterface extends Remote{

    // Ready Database Functionality
    int subscribeUserDatabase(String firstName, String lastName, String userName, String password) throws ClassNotFoundException, RemoteException, NotSerializableException;
    int loginUser(String username, String password) throws RemoteException;

    ArrayList<Todo> getTodoDatabase() throws RemoteException, ClassNotFoundException;
    Todo getOneTodo(int id) throws RemoteException, ClassNotFoundException, SQLException;
    int addTodoDatabase(String item, String status, String date) throws RemoteException, ClassNotFoundException, SQLException;
    int deleteTodoDatabase(int id) throws RemoteException, ClassNotFoundException, SQLException;


    BroadcastInterface subscribeUser (String username, ClientInterface handle) throws RemoteException;
    boolean unsubscribeUser (String username) throws RemoteException;

    // To Do Functionality
    ArrayList<String> getTodo() throws RemoteException;
    boolean addTodo(String item) throws RemoteException;
    boolean deleteTodo(int item) throws RemoteException;
    boolean updateTodo(int i, String s) throws RemoteException;


}
