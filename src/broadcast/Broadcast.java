package broadcast;

import server.Server;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Broadcast implements BroadcastInterface {

    Server server;
    String username;

    public Broadcast(Server server, String username) {
        this.server = server;
        this.username = username;
    }

    @Override
    public void sendMessage(String message) throws RemoteException {
        ArrayList<String> deadClients = new ArrayList<>();
        System.out.print("Message: "+ message + " from " + username + " to ");
        server.getClients().forEach((key, value) -> {
            try {
                value.receiveNewData(username, message);
                System.out.print(key + ", ");
            } catch (RemoteException remoteException) {
                System.out.println();
                System.err.println("Can't send to " + key + ".");
                deadClients.add(key);
            }
        });
        deadClients.forEach((value) -> {
            server.getClients().remove(value);
        });
        System.out.println();
    }

    // Todo Functionaloity
    @Override
    public void broadcastTodo() throws RemoteException {
        ArrayList<String> deadClients = new ArrayList<>();
        ArrayList<String> todo = server.getTodo();
        server.getClients().forEach((key, value) -> {
            try {
                value.receiveTodo(todo);
                //System.out.print(key + ", ");
                //for (String s : todo){
                  //  System.out.println(s);
                //}
            } catch (RemoteException remoteException) {
                System.out.println();
                System.err.println("Can't send to " + key + ".");
                deadClients.add(key);
            }
        });
        deadClients.forEach((value) -> {
            server.getClients().remove(value);
        });
        System.out.println();
    }

}
