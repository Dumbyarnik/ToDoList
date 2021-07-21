package client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientInterface extends Remote {
    public void receiveNewData(String username, String message) throws RemoteException;

    public void receiveTodo(ArrayList<String> todo) throws RemoteException;
}
