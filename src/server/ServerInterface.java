package server;

import broadcast.BroadcastInterface;
import client.ClientInterface;
import server.database.User;

import java.io.NotSerializableException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInterface extends Remote{
    public BroadcastInterface subscribeUser (String username, ClientInterface handle) throws RemoteException;
    public boolean unsubscribeUser (String username) throws RemoteException;

    // To Do Functionality
    ArrayList<String> getTodo() throws RemoteException;
    boolean addTodo(String item) throws RemoteException;
    boolean deleteTodo(int item) throws RemoteException;
    boolean updateTodo(int i, String s) throws RemoteException;

    // Ready Database Functionality
    int subscribeUserDatabase(String firstName, String lastName, String userName, String password) throws ClassNotFoundException, RemoteException, NotSerializableException;
    int loginUser(String username, String password) throws RemoteException;
}
