package client;
/*
 * Class created on 10.08.2021
 * Class is used to create subscription system for client
 * */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    void unsubscribeClient() throws RemoteException;
    // method for adding update to ArrayList on the client
    void addUpdate(String update) throws RemoteException;

    String getRoom() throws RemoteException;
    void setRoom(String room) throws RemoteException;

    String getUsername() throws RemoteException;
    void setUsername(String username) throws RemoteException;
}
