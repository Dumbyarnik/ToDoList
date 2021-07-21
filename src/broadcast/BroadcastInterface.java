package broadcast;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BroadcastInterface extends Remote {
    public void sendMessage (String message) throws RemoteException;

    // Todo Functionality
    public void broadcastTodo () throws RemoteException;
}
