package client;
/*
 * Class created on 10.08.2021
 * Class is used to create subscription system for client
 * */

import server.ServerInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Client implements  ClientInterface{
    private ServerInterface serverInterface;
    // list for showing updates on the client (subscribe system)
    ArrayList<String> updates;
    String room;
    String username;


    public Client() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        updates = new ArrayList<String>();
    }

    public void startClient() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry();
        serverInterface = (ServerInterface) registry.lookup("ChatServer");
    }

    public ArrayList<String> getUpdates(){
        return  updates;
    }

    @Override
    public void addUpdate(String update){
        updates.add(update);
        System.out.println(update);
    }

    @Override
    public void unsubscribeClient() throws RemoteException {
        serverInterface.unsubscribeClient(this);
    }

    @Override
    public String getRoom() throws RemoteException {
        return room;
    }
    @Override
    public void setRoom(String room) throws RemoteException {
        this.room = room;
    }
    @Override
    public String getUsername() throws RemoteException {
        return username;
    }
    @Override
    public void setUsername(String username) throws RemoteException {
        this.username = username;
    }
}
