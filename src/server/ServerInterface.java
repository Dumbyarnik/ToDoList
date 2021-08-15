package server;
/*
 * Class created on 10.07.2021
 * Class is used to manage the server side of the application
 * */

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

    // client - server part
    void subscribeClient(ClientInterface clientInterface) throws RemoteException;
    void unsubscribeClient(ClientInterface clientInterface) throws RemoteException;

    ArrayList<Todo> getTodoDatabase(String room) throws RemoteException, ClassNotFoundException;
    Todo getOneTodo(int id) throws RemoteException, ClassNotFoundException, SQLException;
    int addTodoDatabase(String item, String status, String date, String room, String username) throws RemoteException, ClassNotFoundException, SQLException;
    int deleteTodoDatabase(int id, String room, String username) throws RemoteException, ClassNotFoundException, SQLException;
    int updateTodoDatabase(int id, String item, String status,
                           String date, String room, String username, String old_item) throws RemoteException, ClassNotFoundException, SQLException;
}
