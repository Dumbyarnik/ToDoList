package server;

import broadcast.Broadcast;
import broadcast.BroadcastInterface;
import client.ClientInterface;
import server.database.LoginDAO;
import server.database.RegistrationDAO;
import server.database.User;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Server extends UnicastRemoteObject implements ServerInterface, Serializable {

    Map<String, ClientInterface> clients = new HashMap<>();
    // Beta To Do Version
    static ArrayList<String> todo = new ArrayList<String>();

    public Server() throws RemoteException { }


    // New Database Code
    @Override
    public int subscribeUserDatabase(String firstName, String lastName, String userName, String password) throws ClassNotFoundException, RemoteException, NotSerializableException {

        try {
            RegistrationDAO regDAO = new RegistrationDAO();

            if (regDAO.registerUser(firstName, lastName, userName, password) == 1)
                return 1;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("Error");
            e.printStackTrace();
        }

        return 0;

    }

    @Override
    public int loginUser(String username, String password) throws RemoteException {
        try {
            LoginDAO logDAO = new LoginDAO();

            if (logDAO.validate(username, password))
                return 1;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("Error");
            e.printStackTrace();
        }

        return 0;
    }


    // Old code
    @Override
    public BroadcastInterface subscribeUser(String username, ClientInterface handle) {

        clients.put(username, handle);
        System.out.println(username + " registered.");

        Broadcast broadcast = new Broadcast(this, username);
        BroadcastInterface broadcastInterface;

        try {
            broadcastInterface = (BroadcastInterface) UnicastRemoteObject.exportObject(broadcast,0);
        } catch (RemoteException remoteException) {
            remoteException.printStackTrace();
            System.out.println("Can't register " + username);
            return null;
        }
        return broadcastInterface;
    }
    @Override
    public boolean unsubscribeUser(String username) {
        return clients.remove(username) != null;
    }
    public Map<String, ClientInterface> getClients() {
        return clients;
    }

    // Todo Functionality
    @Override
    public ArrayList<String> getTodo() {
        return todo;
    }
    @Override
    public boolean addTodo(String item) {
        todo.add(item);
        return true;
    }
    @Override
    public boolean deleteTodo(int i) {
        todo.remove(i);
        return true;
    }
    @Override
    public boolean updateTodo(int i, String s) {
        todo.set(i, s);
        return true;
    }

    public static void main(String[] args) {


        try {
            // Creating the server
            Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            Naming.rebind("ChatServer", new Server());
            System.out.println("Server started");
        } catch (RemoteException e) {
            System.err.println("Exception occurred while registering the server.");
            e.printStackTrace();
            System.exit(-1);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
