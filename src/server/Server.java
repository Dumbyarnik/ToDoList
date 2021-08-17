package server;
/*
 * Class created on 10.07.2021
 * Class is used to manage the server side of the application
 * */

import client.ClientInterface;
import server.database.login.LoginDAO;
import server.database.registration.RegistrationDAO;
import server.database.todolist.Todo;
import server.database.todolist.TodoDAO;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements ServerInterface, Serializable {

    ArrayList<ClientInterface> clients;

    public Server() throws RemoteException {
        clients = new ArrayList<>();
    }

    private void updateClients(String room, String update) throws RemoteException {
        for (ClientInterface client : clients){
            // if it is the right room, then we update all clients there
            if (client.getRoom().equals(room)){
                try { client.addUpdate(update); }
                catch(ConnectException e) {
                    clients.remove(client);
                }

            }
        }
    }

    @Override
    public int subscribeUserDatabase(String firstName, String lastName, String userName, String password) throws ClassNotFoundException, RemoteException, NotSerializableException {

        try {
            RegistrationDAO regDAO = new RegistrationDAO();
            if (regDAO.registerUser(firstName, lastName, userName, password) == 1)
                return 1;
        } catch (Exception e) {
            // TODO Auto-generated catch block
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
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void subscribeClient(ClientInterface clientInterface) throws RemoteException {
        clients.add(clientInterface);
        System.out.println(clientInterface.getUsername() + " was subscribed");
    }

    @Override
    public void unsubscribeClient(ClientInterface clientInterface) throws RemoteException {
        clients.remove(clientInterface);
        System.out.println(clientInterface.getUsername() + " was removed");

        System.out.println("Remained clients are: ");
        for (ClientInterface tmp : clients)
            System.out.println(tmp.getUsername());
    }

    @Override
    public ArrayList<Todo> getTodoDatabase(String room) throws RemoteException, ClassNotFoundException {
        TodoDAO dao = new TodoDAO();
        return dao.getTodo(room);
    }

    @Override
    public Todo getOneTodo(int id) throws RemoteException, ClassNotFoundException, SQLException {
        Todo todo = new Todo();
        TodoDAO todoDAO = new TodoDAO();
        todo = todoDAO.getOneTodo(id);
        return todo;
    }

    @Override
    public int addTodoDatabase(String item, String status, String date, String room, String username) throws RemoteException, ClassNotFoundException, SQLException {
        TodoDAO todoDAO = new TodoDAO();
        int result = todoDAO.addTodo(item, status, date, room);

        // if the item was added then update every client in the room
        if (result == 1){
            // updating every client in the room
            String update = "user " + username + " created todo '" + item + "'";
            updateClients(room, update);
        }

        return result;
    }

    @Override
    public int deleteTodoDatabase(int id, String room, String username) throws RemoteException, ClassNotFoundException, SQLException {
        TodoDAO todoDAO = new TodoDAO();

        String name = todoDAO.getTodoName(id);
        int status = todoDAO.deleteTodo(id);

        // updating every client in the room
        String update = "user " + username + " deleted todo '" + name + "'";
        updateClients(room, update);

        return status;
    }

    @Override
    public int updateTodoDatabase(int id, String item, String status, String date,
                                  String room, String username, String old_item) throws RemoteException, ClassNotFoundException, SQLException {
        TodoDAO todoDAO = new TodoDAO();
        int result = todoDAO.updateTodo(id, item, status, date);

        // updating every client in the room
        if (result == 1){
            String update = "user " + username + " updated todo '" + old_item + "'";
            updateClients(room, update);
        }

        return result;
    }

    public static void main(String[] args) throws RemoteException, ClassNotFoundException, SQLException {
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
