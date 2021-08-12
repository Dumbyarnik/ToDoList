package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    String getRoom() throws RemoteException;
    void setRoom(String room) throws RemoteException;

    String getUsername() throws RemoteException;
    void setUsername(String username) throws RemoteException;
}
