package server;

import broadcast.BroadcastInterface;
import client.ClientInterface;
import server.database.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInterface extends Remote{
    public BroadcastInterface subscribeUser (String username, ClientInterface handle) throws RemoteException;
    public boolean unsubscribeUser (String username) throws RemoteException;

    // To Do Functionality
    public ArrayList<String> getTodo() throws RemoteException;
    public boolean addTodo(String item) throws RemoteException;
    public boolean deleteTodo(int item) throws RemoteException;
    public boolean updateTodo(int i, String s) throws RemoteException;
    public int subscribeUserDatabase(User user) throws ClassNotFoundException, RemoteException;
}
